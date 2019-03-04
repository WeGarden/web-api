package com.wegarden.api.observations.payload;

import com.wegarden.api.observations.Observation;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ObservationConverter {

    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public ObservationRequest convertToDTO(Observation observation){
        return modelMapper.map(observation, ObservationRequest.class);
    }

    public Observation convertToEntity(ObservationRequest observationRequest){
        Observation observation=  modelMapper.map(observationRequest,Observation.class);
        observation.setId(0L);
        observation.setDate(new Date());
        return observation;
    }
}
