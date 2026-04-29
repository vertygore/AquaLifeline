package com.AquaLifeLine.Backend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final KundenRepository kundenRepository;
    private final AquariumRepository aquariumRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(KundenRepository kundenRepository, AquariumRepository aquariumRepository,
            PasswordEncoder passwordEncoder) {
        this.kundenRepository = kundenRepository;
        this.aquariumRepository = aquariumRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (kundenRepository.count() > 0)
            return;

        // Create aquariums
        Aquarium a1 = new Aquarium();
        a1.setSerialNumber("AQL-0001");

        Aquarium a2 = new Aquarium();
        a2.setSerialNumber("AQL-0002");

        aquariumRepository.save(a1);
        aquariumRepository.save(a2);

        // Create kunde
        Kunde kunde = new Kunde();
        kunde.setName("TestBenutzer");
        kunde.setPassword(passwordEncoder.encode("test123"));
        kunde.getAquarien().add(a1);
        kunde.getAquarien().add(a2);

        kundenRepository.save(kunde);

        System.out.println("Dummy data created");
    }
}
