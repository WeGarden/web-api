package com.wegarden.api.plants.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wegarden.api.areas.Area;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.ManyToOne;
import java.util.Date;

public class PlantDTO {
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @ApiModelProperty(notes = "The id of the area where the plant is")
    private Long areaId;

    @ApiModelProperty(notes = "The area name")
    private String name;

    @ApiModelProperty(notes = "A base64 image of the plant")
    private String image;

    @ManyToOne
    private Area area;

    @ApiModelProperty(notes = "the species of the plant")
    private String species;

    @ApiModelProperty(notes = "the family of the plant")
    private String family;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
