package com.wegarden.api.plants;

import com.wegarden.api.areas.Area;
import com.wegarden.api.areas.AreaRepository;
import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.observations.Observation;
import com.wegarden.api.observations.ObservationRepository;
import com.wegarden.api.observations.payload.ObservationConverter;
import com.wegarden.api.observations.payload.ObservationDTO;
import com.wegarden.api.plants.payload.PlantConverter;
import com.wegarden.api.plants.payload.PlantDTO;
import com.wegarden.api.protocols.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlantController {
    @Autowired
    private  PlantRepository plantRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PlantConverter plantConverter;

    @Autowired
    private ObservationConverter observationConverter;

    @Autowired
    private ObservationRepository observationRepository;

    @GetMapping("/plants")
    public List<PlantDTO> getPlants(){
        return plantRepository.findAll()
                .stream()
                .map(plantConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/plants/{plantId}")
    public PlantDTO getPlantById(@PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        return plantConverter.convertToDTO(plant);
    }

    @GetMapping("/areas/{areaId}/plants")
    public List<PlantDTO> getPlantByAreaId(@PathVariable(value = "areaId") Long areaId){
        Area area = areaRepository.getOne(areaId);
        return plantRepository.findAllByArea(area)
                .stream()
                .map(plantConverter::convertToDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/areas/{areaId}/plants")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createPlant(@RequestBody PlantDTO plantDTO, @PathVariable(value = "areaId") Long areaId){
        Area area = areaRepository.getOne(areaId);
        Plant plant = plantConverter.convertToEntity(plantDTO);
        plant.setArea(area);
        plant = plantRepository.save(plant);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/plants/{id}")
                .buildAndExpand(plant.getId()).toUri();
        return ResponseEntity.created(location)
                .body(plant.getId());
    }

    @GetMapping("/plants/{plantId}/observations")
    public List<Observation> getObservations(@PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        return plant.getObservations();
    }

    @PostMapping("/plants/{plantId}/observations")
    public void addObservation(@RequestBody ObservationDTO observationDTO, @PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        Observation observation = observationConverter.convertToEntity(observationDTO);
        plant = plant.addObservation(observation);
        plantRepository.save(plant);
    }
}
