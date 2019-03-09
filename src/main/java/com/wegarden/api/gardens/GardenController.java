package com.wegarden.api.gardens;

import com.wegarden.api.gardens.payload.GardenRequest;
import com.wegarden.api.gardens.payload.GardenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping("/{gardenId}")
    public GardenResponse getGardenById(@PathVariable(value = "gardenId") Long gardenId){
        return gardenService.getGardenById(gardenId);
    }


    @GetMapping("/user/{userId}")
    public List<GardenResponse> getGardensCreatedByUser(@PathVariable(value = "userId") Long userId){
        return gardenService.getGardenCreatedBy(userId);
    }

    @GetMapping("/user/username/{username}")
    public List<GardenResponse> getGardensCreatedByUser(@PathVariable(value = "username") String username){
        return gardenService.getGardenCreatedBy(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> addGarden(@RequestBody @Valid GardenRequest gardenRequest){
        GardenResponse gardenResponse = gardenService.addGarden(gardenRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/gardens/{id}")
                .buildAndExpand(gardenResponse.getId()).toUri();
        return ResponseEntity.created(location)
                .body(gardenResponse.getId());
    }

    @DeleteMapping("/{gardenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGarden(@PathVariable(value = "gardenId") Long gardenId){
        gardenService.deleteGarden(gardenId);
    }



}
