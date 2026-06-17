package com.cts.CruiseLine.controller;

import com.cts.CruiseLine.ApiResponse;
import com.cts.CruiseLine.PagedResponse;
import com.cts.CruiseLine.dto.*;
import com.cts.CruiseLine.service.ExcursionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/excursions")
public class ExcursionController {

    private final ExcursionService excursionService;

    @GetMapping
    public ApiResponse<PagedResponse<ExcursionResponse>> list(@RequestParam(required = false) String portOfCall, Pageable pageable) {
        return ApiResponse.ok(excursionService.listExcursions(portOfCall, pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<ExcursionResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(excursionService.getExcursion(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExcursionResponse>> create(@RequestBody ExcursionRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Excursion created", excursionService.createExcursion(req)));
    }

    @PutMapping("/{id}")
    public ApiResponse<ExcursionResponse> update(@PathVariable Long id, @RequestBody ExcursionRequest req) {
        return ApiResponse.ok("Excursion updated", excursionService.updateExcursion(id, req));
    }

    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse<ExcursionBookingResponse>> book(@RequestBody ExcursionBookingRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Excursion booked", excursionService.book(req)));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ApiResponse<ExcursionBookingResponse> cancelBooking(@PathVariable Long bookingId) {
        return ApiResponse.ok("Booking cancelled", excursionService.cancelBooking(bookingId));
    }

    @GetMapping("/bookings/passenger/{passengerId}")
    public ApiResponse<PagedResponse<ExcursionBookingResponse>> passengerBookings(@PathVariable Long passengerId, Pageable pageable) {
        return ApiResponse.ok(excursionService.passengerBookings(passengerId, pageable));
    }

    @PostMapping("/manifests")
    public ResponseEntity<ApiResponse<ManifestResponse>> generateManifest(@RequestBody ManifestRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Manifest generated", excursionService.generateManifest(req)));
    }

    @PatchMapping("/manifests/{manifestId}/finalise")
    public ApiResponse<ManifestResponse> finalise(@PathVariable Long manifestId) {
        return ApiResponse.ok("Manifest finalised", excursionService.finaliseManifest(manifestId));
    }

    @GetMapping("/manifests/voyage/{voyageId}")
    public ApiResponse<List<ManifestResponse>> manifests(@PathVariable Long voyageId) {
        return ApiResponse.ok(excursionService.manifestsByVoyage(voyageId));
    }

    public ExcursionController(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }
}
