package com.wegarden.api.plants.payload;

import io.swagger.annotations.ApiModelProperty;

public class PlantDTO {
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @ApiModelProperty(notes = "The id of the area where the plant is")
    private Long areaId;

    @ApiModelProperty(notes = "The area name")
    private String name;

    @ApiModelProperty(notes = "A base64 image of the area")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
