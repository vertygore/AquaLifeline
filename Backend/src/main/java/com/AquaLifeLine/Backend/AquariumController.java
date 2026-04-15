package com.AquaLifeLine.Backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aquarien")
public class AquariumController {
    
    private final AquariumService aquariumService;

    public AquariumController(AquariumService aquariumService) {
        this.aquariumService = aquariumService;
    }

    @GetMapping
    public List<Aquarium> getAllAquariums() {
        return aquariumService.getAllAquariums();
    }

    @GetMapping("/{id}")
    public Aquarium getAquariumById(@PathVariable long id) {
        return aquariumService.getAquariumById(id);
    }

    @PostMapping
    public Aquarium createAquarium(@RequestBody Aquarium aquarium) {
        return aquariumService.saveAquarium(aquarium);
    }

    @DeleteMapping("/{id}")
    public void deleteAquarium(@PathVariable long id) {
        aquariumService.deleteAquarium(id);
    }
}
