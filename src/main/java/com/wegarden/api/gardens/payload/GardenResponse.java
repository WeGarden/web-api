package com.wegarden.api.gardens.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wegarden.api.geolocation.Geolocation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class GardenResponse {
    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private String name;

    @Valid
    private String description;

    @Valid
    private String gardenType;

    private Geolocation location;

    @JsonProperty("private")
    private boolean isPrivate;

    // base64 image
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
