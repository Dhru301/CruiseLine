package com.cts.CruiseLine.service;

import com.cts.CruiseLine.AuditService;
import com.cts.CruiseLine.PagedResponse;
import com.cts.CruiseLine.enums.NotificationCategory;
import com.cts.CruiseLine.enums.BookingStatus;
import com.cts.CruiseLine.enums.EmbarkationStatus;
import com.cts.CruiseLine.exception.BusinessRuleException;
import com.cts.CruiseLine.exception.ResourceNotFoundException;
import com.cts.CruiseLine.dto.*;
import com.cts.CruiseLine.entity.EmbarkationRecord;
import com.cts.CruiseLine.entity.MusterDrillAttendance;
import com.cts.CruiseLine.entity.MusterStation;
import com.cts.CruiseLine.repository.EmbarkationRecordRepository;
import com.cts.CruiseLine.repository.MusterDrillAttendanceRepository;
import com.cts.CruiseLine.repository.MusterStationRepository;
import com.cts.CruiseLine.repository.CruiseBookingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class EmbarkationService {

    private final EmbarkationRecordRepository embarkationRepository;
    private final MusterStationRepository musterStationRepository;
    private final MusterDrillAttendanceRepository drillRepository;
    private final AuditService auditService;
    private final NotificationService notificationService;
    private final CruiseBookingRepository bookingRepository;

    @Transactional
    public MusterStationResponse createStation(MusterStationRequest req) {
        MusterStation station = MusterStation.builder()
                .voyageId(req.getVoyageId())
                .stationCode(req.getStationCode())
                .deck(req.getDeck())
                .assignedCabinRange(req.getAssignedCabinRange())
                .capacity(req.getCapacity())
                .build();
        return MusterStationResponse.from(musterStationRepository.save(station));
    }

    @Transactional(readOnly = true)
    public List<MusterStationResponse> listStations(Long voyageId) {
        return musterStationRepository.findByVoyageId(voyageId).stream()
                .map(MusterStationResponse::from).toList();
    }

    @Transactional
    public EmbarkationResponse checkIn(CheckInRequest req) {
        if (!bookingRepository.existsByLeadPassengerIdAndVoyageIdAndStatus(
                req.getPassengerId(), req.getVoyageId(), BookingStatus.CONFIRMED)) {
            throw new BusinessRuleException(
                "Passenger has no confirmed (fully paid) booking on this voyage and cannot be checked in");
        }
        embarkationRepository.findByVoyageIdAndPassengerId(req.getVoyageId(), req.getPassengerId())
                .ifPresent(r -> { throw new BusinessRuleException("Passenger already checked in for this voyage"); });

        if (!Boolean.TRUE.equals(req.getDocumentVerified())) {
            throw new BusinessRuleException("Documents must be verified before a boarding pass can be issued");
        }
        MusterStation station = null;
        if (req.getMusterStationId() != null) {
            station = musterStationRepository.findById(req.getMusterStationId())
                    .orElseThrow(() -> new ResourceNotFoundException("MusterStation", "id", req.getMusterStationId()));
            if (station.getCapacity() != null
                    && embarkationRepository.countByVoyageIdAndMusterStationId(req.getVoyageId(), station.getMusterId()) >= station.getCapacity()) {
                throw new BusinessRuleException("Muster station " + station.getStationCode() + " is already at capacity");
            }
        }

        EmbarkationRecord record = EmbarkationRecord.builder()
                .passengerId(req.getPassengerId())
                .voyageId(req.getVoyageId())
                .checkInDateTime(Instant.now())
                .documentVerified(true)
                .boardingPassIssued(true)
                .musterStationId(req.getMusterStationId())
                .status(EmbarkationStatus.CHECKED_IN)
                .build();
        auditService.record("PASSENGER_CHECKED_IN", "EmbarkationRecord");
        EmbarkationResponse resp = EmbarkationResponse.from(embarkationRepository.save(record));
        notificationService.notify(req.getPassengerId(),
            "You're checked in and your boarding pass is issued."
                + (station != null ? " Your muster station is " + station.getStationCode() + "." : ""),
            NotificationCategory.EMBARKATION);
        return resp;
    }

    @Transactional
    public EmbarkationResponse markOnboard(Long recordId) {
        EmbarkationRecord record = embarkationRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("EmbarkationRecord", "id", recordId));
        if (record.getStatus() != EmbarkationStatus.CHECKED_IN) {
            throw new BusinessRuleException(
                "Only a CHECKED_IN passenger can be marked ONBOARD; this record is " + record.getStatus());
        }
        record.setStatus(EmbarkationStatus.ONBOARD);
        return EmbarkationResponse.from(record);
    }

    @Transactional(readOnly = true)
    public PagedResponse<EmbarkationResponse> queue(Long voyageId, EmbarkationStatus status, Pageable pageable) {
        var page = (status == null)
                ? embarkationRepository.findByVoyageId(voyageId, pageable).map(EmbarkationResponse::from)
                : embarkationRepository.findByVoyageIdAndStatus(voyageId, status, pageable).map(EmbarkationResponse::from);
        return PagedResponse.from(page);
    }

    @Transactional
    public DrillAttendanceResponse recordAttendance(Long recordedById, DrillAttendanceRequest req) {
        EmbarkationRecord boarding = embarkationRepository
                .findByVoyageIdAndPassengerId(req.getVoyageId(), req.getPassengerId())
                .orElseThrow(() -> new BusinessRuleException(
                    "Passenger is not checked in for this voyage; drill attendance cannot be recorded"));
        if (boarding.getStatus() == EmbarkationStatus.NO_SHOW) {
            throw new BusinessRuleException(
                "Passenger is a NO_SHOW for this voyage; drill attendance cannot be recorded");
        }
        MusterDrillAttendance drill = MusterDrillAttendance.builder()
                .musterId(req.getMusterId())
                .voyageId(req.getVoyageId())
                .passengerId(req.getPassengerId())
                .drillDate(req.getDrillDate())
                .attendanceStatus(req.getAttendanceStatus())
                .recordedById(recordedById)
                .build();
        auditService.record("DRILL_ATTENDANCE_RECORDED", "MusterDrillAttendance");
        DrillAttendanceResponse resp = DrillAttendanceResponse.from(drillRepository.save(drill));
        notificationService.notify(req.getPassengerId(),
            "Your muster drill attendance on " + req.getDrillDate() + " was recorded as "
                + req.getAttendanceStatus() + ".", NotificationCategory.EMBARKATION);
        return resp;
    }

    @Transactional(readOnly = true)
    public List<DrillAttendanceResponse> drillAttendance(Long voyageId) {
        return drillRepository.findByVoyageId(voyageId).stream()
                .map(DrillAttendanceResponse::from).toList();
    }

    public EmbarkationService(EmbarkationRecordRepository embarkationRepository,
                              MusterStationRepository musterStationRepository,
                              MusterDrillAttendanceRepository drillRepository,
                              AuditService auditService,
                              NotificationService notificationService,
                              CruiseBookingRepository bookingRepository) {
        this.embarkationRepository = embarkationRepository;
        this.musterStationRepository = musterStationRepository;
        this.drillRepository = drillRepository;
        this.auditService = auditService;
        this.notificationService = notificationService;
        this.bookingRepository = bookingRepository;
    }
}