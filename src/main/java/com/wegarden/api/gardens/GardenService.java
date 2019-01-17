package com.wegarden.api.gardens;

import com.wegarden.api.exception.ResourceNotFoundException;
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

    public List<GardenResponse> getGardens(){
        return gardenRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<GardenResponse> getGardenCreatedBy(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return gardenRepository.findAllByUser(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GardenResponse addGarden(GardenRequest gardenRequest){
        Long userId = gardenRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        Geolocation geolocation = gardenRequest.getLocation();
        if(geolocation != null){
            geolocation = geolocationRepository.save(geolocation);
        }

        Garden garden = convertToEntity(gardenRequest);
        garden.setLocation(geolocation);
        garden.setUser(user);
        garden = gardenRepository.save(garden);
        GardenResponse gardenResponse = convertToDTO(garden);
        return gardenResponse;
    }

    private GardenResponse convertToDTO(Garden garden){
        GardenResponse gardenResponse=  modelMapper.map(garden, GardenResponse.class);
        if(garden.getImage() != null){
            gardenResponse.setImage(Base64Utils.encodeToString(garden.getImage()));
        }
        return gardenResponse;
    }

    private Garden convertToEntity(GardenRequest gardenRequest){
        Garden garden =  modelMapper.map(gardenRequest,Garden.class);
        if(gardenRequest.getImage() != null){
            String[] data = gardenRequest.getImage().split(",") ; // handle case where base64 has this kind of form:"data:image/jpeg;base64,.."
            String base64Image = (data.length==2)?data[1]:data[0];
            garden.setImage(Base64Utils.decodeFromString(base64Image));
        }
        garden.setId(0L);
        return garden;
    }


}
