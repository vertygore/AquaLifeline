package com.AquaLifeLine.Backend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SensorDataService {
    final private SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return this.sensorDataRepository.findByTimestampBetween(start, end);
    }

    public List<SensorData> findBySensorId(Long sensorId) {
        return sensorDataRepository.findBySensorId(sensorId);
    }

    public SensorData save(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    public SensorData editSensorData(Long sensorDataId, SensorData sensorData){
        return this.sensorDataRepository.findById(sensorDataId)
            .map(existing ->{
                existing.setTemperatur(sensorData.getTemperatur());
                existing.setPH(sensorData.getPH());
                existing.setWasserstand(sensorData.getWasserstand());
                existing.setWasserqualitaet(sensorData.getWasserqualitaet());
                existing.setTimestamp(sensorData.getTimestamp());
                return sensorDataRepository.save(existing);
            })
            .orElseThrow(() -> new RuntimeException("Fehler beim bearbeiten."));
    }

    public void deleteById(Long sensorId){
        this.sensorDataRepository.deleteById(sensorId);
    }

}
