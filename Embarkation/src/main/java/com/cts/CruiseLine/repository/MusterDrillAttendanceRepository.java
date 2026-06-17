package com.cts.CruiseLine.repository;

import com.cts.CruiseLine.entity.MusterDrillAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusterDrillAttendanceRepository extends JpaRepository<MusterDrillAttendance, Long> {
    List<MusterDrillAttendance> findByVoyageId(Long voyageId);
}
