package com.wegarden.api.gardens;

import com.wegarden.api.gardens.payload.GardenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gardens")
public class GardenController {

    private final GardenRepository gardenRepository;

    private final GardenService gardenService;

    @Autowired
    public GardenController(GardenRepository gardenRepository, GardenService gardenService) {
        this.gardenRepository = gardenRepository;
        this.gardenService = gardenService;
    }

    @GetMapping
    public List<Garden> getGardens() {
        return gardenRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Garden> getGardensCreatedBy(@PathVariable(value = "id") Long userId){
        return gardenService.getGardenCreatedBy(userId);
    }

    @PostMapping
    public ResponseEntity<?> addGarden(@RequestBody GardenRequest gardenRequest){
        return gardenService.addGarden(gardenRequest);
    }



}
