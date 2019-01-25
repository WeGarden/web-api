package com.wegarden.api.areas;

import com.wegarden.api.areas.payload.AreaConverter;
import com.wegarden.api.areas.payload.AreaDTO;
import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.gardens.Garden;
import com.wegarden.api.gardens.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing Area.
 */
@RestController
@RequestMapping("/api")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private AreaConverter areaConverter;

    @GetMapping("/areas")
    public List<AreaDTO> getAreas(){
        return areaRepository.findAll()
                .stream()
                .map(areaConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/areas/{areaId}")
    public AreaDTO getAreaById(@PathVariable(value = "areaId") Long areaId){
        Area area =  areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("Area","id",areaId));
        return areaConverter.convertToDTO(area);
    }

    @GetMapping("/gardens/{gardenId}/areas")
    public List<AreaDTO> getAreaByGardenId(@PathVariable(value = "gardenId") Long gardenId){
        Garden garden = gardenRepository.getOne(gardenId);
        return areaRepository.findAllByGarden(garden)
                .stream()
                .map(areaConverter::convertToDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/gardens/{gardenId}/areas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createArea(@RequestBody AreaDTO areaDTO, @PathVariable(value = "gardenId") Long gardenId){
        Garden garden = gardenRepository.getOne(gardenId);
        Area area = areaConverter.convertToEntity(areaDTO);
        area.setGarden(garden);
        area = areaRepository.save(area);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/areas/{id}")
                .buildAndExpand(area.getId()).toUri();
        return ResponseEntity.created(location)
                .body(area.getId());
    }




}
