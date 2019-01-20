package com.wegarden.api.gardens.payload;

import com.wegarden.api.gardens.Garden;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

@Component
public class GardenConverter {
    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public GardenResponse convertToDTO(Garden garden){
        GardenResponse gardenResponse=  modelMapper.map(garden, GardenResponse.class);
        if(!StringUtils.isEmpty(garden.getImage())){
            gardenResponse.setImage(Base64Utils.encodeToString(garden.getImage()));
        }
        return gardenResponse;
    }

    public Garden convertToEntity(GardenRequest gardenRequest){
        Garden garden =  modelMapper.map(gardenRequest,Garden.class);
        // check if image is not null
        if(!StringUtils.isEmpty(gardenRequest.getImage())){
            String[] data = gardenRequest.getImage().split(",") ; // handle case where base64 has this kind of form:"data:image/jpeg;base64,.."
            String base64Image = (data.length==2)?data[1]:data[0];
            garden.setImage(Base64Utils.decodeFromString(base64Image));
        }
        garden.setId(0L);
        return garden;
    }
}
