package com.wegarden.api.util;

import org.modelmapper.ModelMapper;

public class ModelUtilMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ModelMapper getModelMapper(){
        return modelMapper;
    }
}
