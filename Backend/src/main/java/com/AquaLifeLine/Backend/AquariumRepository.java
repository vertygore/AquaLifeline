package com.AquaLifeLine.Backend;
import org.springframework.data.jpa.repository.jpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AquariumRepository extends org.springframework.data.jpa.repository.JpaRepository<Aquarium, Long> {
    boolean existsBySerialNumber(String serialNumber);
}

