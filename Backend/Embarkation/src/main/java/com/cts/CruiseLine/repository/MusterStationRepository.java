package com.cts.CruiseLine.repository;

import com.cts.CruiseLine.entity.MusterStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusterStationRepository extends JpaRepository<MusterStation, Long> {
    List<MusterStation> findByVoyageId(Long voyageId);
}
