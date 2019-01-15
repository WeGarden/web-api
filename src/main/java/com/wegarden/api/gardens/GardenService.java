package com.wegarden.api.gardens;

import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.gardens.payload.GardenRequest;
import com.wegarden.api.geolocation.Geolocation;
import com.wegarden.api.geolocation.GeolocationRepository;
import com.wegarden.api.users.User;
import com.wegarden.api.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {

    private static final Logger logger = LoggerFactory.getLogger(GardenService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private GeolocationRepository geolocationRepository;


    public List<Garden> getGardenCreatedBy(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return gardenRepository.findAllByUser(user);
    }

    public ResponseEntity<?> addGarden(GardenRequest gardenRequest){
        Long userId = gardenRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        Geolocation geolocation = gardenRequest.getLocation();
        if(geolocation != null){
            geolocation = geolocationRepository.save(geolocation);
        }

        Garden garden = new Garden(user,
                gardenRequest.getName(),
                gardenRequest.getDescription(),
                gardenRequest.getGardenType(),
                geolocation,
                gardenRequest.isPrivate());

        gardenRepository.save(garden);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
