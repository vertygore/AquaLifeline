package com.AquaLifeLine.Backend.Mqtt;

import java.time.LocalDateTime;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.AquaLifeLine.Backend.SensorData;
import com.AquaLifeLine.Backend.SensorDataRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MqttMessageHandler {
    private final ObjectMapper objectMapper;
    private final SensorDataRepository sensorDataRepository;

    public MqttMessageHandler(ObjectMapper objectMapper, SensorDataRepository sensorDataRepository) {
        this.objectMapper = objectMapper;
        this.sensorDataRepository = sensorDataRepository;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        try {
            String payload = message.getPayload();
            System.out.println("MQTT erhalten: " + payload);
            JsonNode json = objectMapper.readTree(payload);

            SensorData data = new SensorData();
            data.setDeviceId(json.path("deviceId").asText("unknown"));
            data.setTemperatur(json.path("Temperatur").asDouble(0));
            data.setPH(json.path("PH").asDouble(0));
            data.setWasserstand(json.path("Wasserstand").asDouble(0));
            data.setWasserqualitaet(json.path("Wasserqualitaet").asDouble(0));
            data.setTimestamp(LocalDateTime.now());
            sensorDataRepository.save(data);
        } catch (Exception e) {
            System.err.println("Fehler beim Verarbeiten der MQTT-Nachricht: " + e.getMessage());
        }
    }
}
