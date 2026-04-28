package com.AquaLifeLine.Backend;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aquarien")
public class AquariumController {

    private final AquariumService aquariumService;
    private final KundenService kundenService;

    public AquariumController(AquariumService aquariumService, KundenService kundenService) {
        this.aquariumService = aquariumService;
        this.kundenService = kundenService;
    }

    @GetMapping
    public List<Aquarium> getAquarienByKunde(Principal principal){
        Kunde kunde = kundenService.getKundeByName(principal.getName());
        return aquariumService.getAquarienByKunde(kunde);
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/serialNumber/{serialNumber}")
    public Aquarium findAquariumBySerialNumber(@PathVariable String serialNumber){
        return this.aquariumService.findBySerialNumber(serialNumber);
    }

    @GetMapping("/{id}")
    public Aquarium getAquariumById(@PathVariable long id) {
        return aquariumService.getAquariumById(id);
    }

    @PostMapping
    public Aquarium createAquarium(@RequestBody Aquarium aquarium, Principal principal) {
        Kunde kunde = kundenService.getKundeByName(principal.getName());
        do {
            aquarium.setSerialNumber(SerialNumberGenerator.generateSerialNumber());
        } while (aquariumService.existsBySerialNumber(aquarium.getSerialNumber()));
        aquariumService.saveAquarium(aquarium);
        kunde.getAquarien().add(aquarium);
        kundenService.saveKunde(kunde);
        return aquarium;
    }

    @DeleteMapping("/{id}")
    public void deleteAquarium(@PathVariable long id) {
        aquariumService.deleteAquarium(id);
    }
}
