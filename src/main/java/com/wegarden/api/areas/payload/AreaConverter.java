package com.wegarden.api.areas.payload;

import com.wegarden.api.areas.Area;
import com.wegarden.api.gardens.Garden;
import com.wegarden.api.gardens.payload.GardenRequest;
import com.wegarden.api.gardens.payload.GardenResponse;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

@Component
public class AreaConverter {
    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public AreaDTO convertToDTO(Area area){
        AreaDTO areaDTO=  modelMapper.map(area, AreaDTO.class);
        if(!StringUtils.isEmpty(areaDTO.getImage())){
            areaDTO.setImage(Base64Utils.encodeToString(area.getImage()));
        }
        return areaDTO;
    }

    public Area convertToEntity(AreaDTO areaDTO){
        Area area =  modelMapper.map(areaDTO,Area.class);
        // check if image is not null
        if(!StringUtils.isEmpty(areaDTO.getImage())){
            String[] data = areaDTO.getImage().split(",") ; // handle case where base64 has this kind of form:"data:image/jpeg;base64,.."
            String base64Image = (data.length==2)?data[1]:data[0];
            area.setImage(Base64Utils.decodeFromString(base64Image));
        }
        area.setId(0L);
        return area;
    }
}
