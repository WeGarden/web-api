package com.wegarden.api.gardens;

import com.wegarden.api.gardens.payload.GardenRequest;
import com.wegarden.api.gardens.payload.GardenResponse;
import com.wegarden.api.users.ApiResponse;
import com.wegarden.api.users.RESPONSE_STATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/gardens")
public class GardenController {

    private final GardenService gardenService;

    @Autowired
    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping
    public List<GardenResponse> getGardens() {
        return gardenService.getGardens();
    }

    @GetMapping("/user/{id}")
    public List<GardenResponse> getGardensCreatedBy(@PathVariable(value = "id") Long userId){
        return gardenService.getGardenCreatedBy(userId);
    }

    @PostMapping
    public ResponseEntity<Long> addGarden(@RequestBody GardenRequest gardenRequest){
        GardenResponse gardenResponse = gardenService.addGarden(gardenRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/gardens/{id}")
                .buildAndExpand(gardenResponse.getId()).toUri();
        return ResponseEntity.created(location)
                .body(gardenResponse.getId());
    }



}
