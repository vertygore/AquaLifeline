package com.AquaLifeLine.Backend;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataRepository extends org.springframework.data.jpa.repository.JpaRepository<SensorData, Long> {
    List<SensorData> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<SensorData> findBySensorId(Long sensorId);
}
