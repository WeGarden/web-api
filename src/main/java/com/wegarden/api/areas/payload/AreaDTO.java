package com.wegarden.api.areas.payload;

import io.swagger.annotations.ApiModelProperty;

public class AreaDTO {
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @ApiModelProperty(notes = "The id of the garden where the area is")
    private Long gardenId;

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

    public Long getGardenId() {
        return gardenId;
    }

    public void setGardenId(Long gardenId) {
        this.gardenId = gardenId;
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
