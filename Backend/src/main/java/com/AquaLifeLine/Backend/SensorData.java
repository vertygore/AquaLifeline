package com.AquaLifeLine.Backend;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
public class SensorData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=true)
    private double Temperatur;
    
    @Column(nullable=true)
    private double PH;

    @Column(nullable=true)
    private double Wasserstand;
    
    @Column(nullable=true)
    private double Wasserqualitaet;

    private LocalDateTime timestamp;



}
