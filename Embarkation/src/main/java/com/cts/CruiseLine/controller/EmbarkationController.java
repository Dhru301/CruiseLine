package com.cts.CruiseLine.controller;

import com.cts.CruiseLine.ApiResponse;
import com.cts.CruiseLine.PagedResponse;
import com.cts.CruiseLine.dto.*;
import com.cts.CruiseLine.enums.EmbarkationStatus;
import com.cts.CruiseLine.service.EmbarkationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/embarkation")
public class EmbarkationController {

    private final EmbarkationService embarkationService;

    @PostMapping("/muster-stations")
    public ResponseEntity<ApiResponse<MusterStationResponse>> createStation(@RequestBody MusterStationRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Muster station created", embarkationService.createStation(req)));
    }

    @GetMapping("/voyages/{voyageId}/muster-stations")
    public ApiResponse<List<MusterStationResponse>> listStations(@PathVariable Long voyageId) {
        return ApiResponse.ok(embarkationService.listStations(voyageId));
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<EmbarkationResponse>> checkIn(@RequestBody CheckInRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Passenger checked in", embarkationService.checkIn(req)));
    }

    @PatchMapping("/records/{recordId}/onboard")
    public ApiResponse<EmbarkationResponse> markOnboard(@PathVariable Long recordId) {
        return ApiResponse.ok("Marked onboard", embarkationService.markOnboard(recordId));
    }

    @GetMapping("/voyages/{voyageId}/queue")
    public ApiResponse<PagedResponse<EmbarkationResponse>> queue(@PathVariable Long voyageId,
                                                                 @RequestParam(required = false) EmbarkationStatus status,
                                                                 Pageable pageable) {
        return ApiResponse.ok(embarkationService.queue(voyageId, status, pageable));
    }

    @PostMapping("/drills")
    public ResponseEntity<ApiResponse<DrillAttendanceResponse>> recordAttendance(@RequestBody DrillAttendanceRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Attendance recorded",
                        embarkationService.recordAttendance(1L, req)));
    }

    @GetMapping("/voyages/{voyageId}/drills")
    public ApiResponse<List<DrillAttendanceResponse>> drills(@PathVariable Long voyageId) {
        return ApiResponse.ok(embarkationService.drillAttendance(voyageId));
    }

    public EmbarkationController(EmbarkationService embarkationService) {
        this.embarkationService = embarkationService;
    }
}
