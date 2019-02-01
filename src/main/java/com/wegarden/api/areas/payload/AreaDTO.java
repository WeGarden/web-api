package com.wegarden.api.areas.payload;

import com.wegarden.api.coords.Coord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AreaDTO {
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @ApiModelProperty(notes = "The id of the garden where the area is")
    private Long gardenId;

    @ApiModelProperty(notes = "The area name")
    private String name;

    @ApiModelProperty(notes = "A base64 image of the area")
    private String image;

    @ApiModelProperty(notes = "A list of points representing the polygon  of the area")
    private List<Coord> coordList;

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

    public List<Coord> getCoordList() {
        return coordList;
    }

    public void setCoordList(List<Coord> coordList) {
        this.coordList = coordList;
    }
}
