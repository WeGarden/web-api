package com.wegarden.api.observations.payload;

import com.wegarden.api.observations.Observation;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ObservationConverter {

    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public ObservationDTO convertToDTO(Observation observation){
        return modelMapper.map(observation, ObservationDTO.class);
    }

    public Observation convertToEntity(ObservationDTO observationDTO){
        Observation observation=  modelMapper.map(observationDTO,Observation.class);
        observation.setId(0L);
        observation.setDate(new Date());
        return observation;
    }
}
