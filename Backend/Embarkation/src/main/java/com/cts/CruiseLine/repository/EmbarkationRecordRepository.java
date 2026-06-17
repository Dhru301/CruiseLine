package com.cts.CruiseLine.repository;

import com.cts.CruiseLine.enums.EmbarkationStatus;
import com.cts.CruiseLine.entity.EmbarkationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmbarkationRecordRepository extends JpaRepository<EmbarkationRecord, Long> {
    Page<EmbarkationRecord> findByVoyageId(Long voyageId, Pageable pageable);
    Page<EmbarkationRecord> findByVoyageIdAndStatus(Long voyageId, EmbarkationStatus status, Pageable pageable);
    Optional<EmbarkationRecord> findByVoyageIdAndPassengerId(Long voyageId, Long passengerId);
    long countByVoyageIdAndStatus(Long voyageId, EmbarkationStatus status);
    long countByVoyageIdAndMusterStationId(Long voyageId, Long musterStationId);
}
