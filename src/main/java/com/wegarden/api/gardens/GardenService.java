package com.wegarden.api.gardens;

import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.gardens.payload.GardenDTO;
import com.wegarden.api.geolocation.Geolocation;
import com.wegarden.api.geolocation.GeolocationRepository;
import com.wegarden.api.users.User;
import com.wegarden.api.users.UserRepository;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GardenService {

    private static final Logger logger = LoggerFactory.getLogger(GardenService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private GeolocationRepository geolocationRepository;

    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();


    public List<GardenDTO> getGardenCreatedBy(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return gardenRepository.findAllByUser(user)
                .stream()
                .map(garden -> modelMapper.map(garden,GardenDTO.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addGarden(GardenDTO gardenDTO){
        Long userId = gardenDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        Geolocation geolocation = gardenDTO.getLocation();
        if(geolocation != null){
            geolocation = geolocationRepository.save(geolocation);
        }

//        Garden garden = new Garden(user,
//                gardenDTO.getName(),
//                gardenDTO.getDescription(),
//                gardenDTO.getGardenType(),
//                geolocation,
//                gardenDTO.isPrivate());

        Garden garden = modelMapper.map(gardenDTO,Garden.class);
        garden.setId(0L);
        garden.setLocation(geolocation);

        gardenRepository.save(garden);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
