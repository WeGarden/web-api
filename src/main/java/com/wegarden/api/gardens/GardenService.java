package com.wegarden.api.gardens;

import com.wegarden.api.coords.Coord;
import com.wegarden.api.coords.CoordRepository;
import com.wegarden.api.exception.ResourceNotFoundException;
import com.wegarden.api.gardens.payload.GardenConverter;
import com.wegarden.api.gardens.payload.GardenRequest;
import com.wegarden.api.gardens.payload.GardenResponse;
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
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

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
    private GardenConverter gardenConverter;

    public List<GardenResponse> getGardens(){
        return gardenRepository.findAll()
                .stream()
                .map(gardenConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getGardenCreatedBy(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return gardenRepository.findAllByUser(user)
                .stream()
                .map(gardenConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getGardenCreatedBy(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User","username",username));
        return gardenRepository.findAllByUser(user)
                .stream()
                .map(gardenConverter::convertToDTO)
                .collect(Collectors.toList());
    }


    public GardenResponse addGarden(GardenRequest gardenRequest){
        Long userId = gardenRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        Garden garden = gardenConverter.convertToEntity(gardenRequest);
        garden.setUser(user);
        garden = gardenRepository.save(garden);
        return gardenConverter.convertToDTO(garden);
    }

    public GardenResponse getGardenById(Long gardenId){
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Garden","id",gardenId));
        return gardenConverter.convertToDTO(garden);
    }


    public void deleteGarden(Long gardenId){
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Garden","id",gardenId));
        gardenRepository.deleteById(gardenId);
    }


}
