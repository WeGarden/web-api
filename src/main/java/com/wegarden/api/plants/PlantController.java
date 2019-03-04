package com.wegarden.api.plants;

import com.wegarden.api.areas.Area;
import com.wegarden.api.areas.AreaRepository;
import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.observations.Statement;
import com.wegarden.api.observations.StatementRepository;
import com.wegarden.api.observations.payload.StatementConverter;
import com.wegarden.api.observations.payload.StatementRequest;
import com.wegarden.api.observations.payload.StatementResponse;
import com.wegarden.api.plants.payload.PlantConverter;
import com.wegarden.api.plants.payload.PlantDTO;
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
    private StatementConverter statementConverter;

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

    @GetMapping("/plants/{plantId}/statements")
    public List<StatementResponse> getStatements(@PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        return plant.getStatements()
                .stream()
                .map(statementConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/plants/{plantId}/observations")
    public List<StatementResponse> getObservations(@PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        List<Statement> statements = plant.getStatements();
        return plant.getStatements()
                .stream()
                .filter(e -> e.getStatementType().equals("observation"))
                .map(statementConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/plants/{plantId}/actions")
    public List<StatementResponse> getActions(@PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        return plant.getStatements()
                .stream()
                .filter(e -> e.getStatementType().equals("action"))
                .map(statementConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/plants/{plantId}/statements")
    public void addSatements(@RequestBody StatementRequest statementRequest, @PathVariable(value = "plantId") Long plantId){
        Plant plant =  plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant","id",plantId));
        Statement statement = statementConverter.convertToEntity(statementRequest);
        plant = plant.addStatement(statement);
        plantRepository.save(plant);
    }
}
