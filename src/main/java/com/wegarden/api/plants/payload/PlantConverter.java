package com.wegarden.api.plants.payload;

import com.wegarden.api.plants.Plant;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

@Component
public class PlantConverter {
    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public PlantDTO convertToDTO(Plant plant){
        PlantDTO plantDTO=  modelMapper.map(plant, PlantDTO.class);
        if(!StringUtils.isEmpty(plantDTO.getImage())){
            plantDTO.setImage(Base64Utils.encodeToString(plant.getImage()));
        }
        return plantDTO;
    }

    public Plant convertToEntity(PlantDTO plantDTO){
        Plant plant =  modelMapper.map(plantDTO,Plant.class);
        // check if image is not null
        if(!StringUtils.isEmpty(plantDTO.getImage())){
            String[] data = plantDTO.getImage().split(",") ; // handle case where base64 has this kind of form:"data:image/jpeg;base64,.."
            String base64Image = (data.length==2)?data[1]:data[0];
            plant.setImage(Base64Utils.decodeFromString(base64Image));
        }
        plant.setId(0L);
        return plant;
    }
}
