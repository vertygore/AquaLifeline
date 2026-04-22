package com.AquaLifeLine.Backend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Aquarium {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;
    
    @OneToMany(mappedBy = "aquarium")
    private List<Data> dataList;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;
}

