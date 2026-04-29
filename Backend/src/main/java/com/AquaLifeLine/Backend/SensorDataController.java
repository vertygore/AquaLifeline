package com.AquaLifeLine.Backend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daten")
public class SensorDataController {
    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService){
        this.sensorDataService = sensorDataService;
    }

    @GetMapping("/{sensor_id}")
    public List<SensorData> findBySensorId(@PathVariable Long sensorId){
        return this.sensorDataService.findBySensorId(sensorId);
    }

    @GetMapping("/timestamp")
    public List<SensorData> findByTimestampBetween(@RequestParam LocalDateTime start,@RequestParam LocalDateTime end){
        return this.sensorDataService.findByTimestampBetween(start, end);
    }
    
    @PatchMapping("/edit/{sensorData_id}")
    public SensorData editSensorData(@PathVariable Long sensorDataId ,@RequestBody SensorData sensorData){
        return sensorDataService.editSensorData(sensorDataId, sensorData);
    }

    @PostMapping("/create")
    public SensorData createSensorData(@RequestBody SensorData sensorData){
        return this.sensorDataService.save(sensorData);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long sensorId){
        this.sensorDataService.deleteById(sensorId);
    }
}
