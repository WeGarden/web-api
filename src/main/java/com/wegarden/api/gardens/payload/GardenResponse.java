package com.wegarden.api.gardens.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wegarden.api.geolocation.Geolocation;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class GardenResponse {
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @ApiModelProperty(notes = "The id of the user who created the garden")
    private Long userId;

    @ApiModelProperty(notes = "The garden name")
    private String name;

    @ApiModelProperty(notes = "The garden description")
    private String description;

    @ApiModelProperty(notes = "The type of garden (closed,open,...)")
    private String gardenType;

    @ApiModelProperty(notes = "The location of the garden")
    private Geolocation location;

    @JsonProperty("private")
    @ApiModelProperty(notes = "Tell if the garden can't only be see by others users")
    private boolean isPrivate;

    @ApiModelProperty(notes = "A base64 image of the garden")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGardenType() {
        return gardenType;
    }

    public void setGardenType(String gardenType) {
        this.gardenType = gardenType;
    }

    public Geolocation getLocation() {
        return location;
    }

    public void setLocation(Geolocation location) {
        this.location = location;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
