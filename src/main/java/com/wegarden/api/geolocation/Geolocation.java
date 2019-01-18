package com.wegarden.api.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Geolocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @ApiModelProperty(notes = "The database generated garden ID")
    private Long id;

    @NotNull
    @ApiModelProperty(notes = "The location latitude")
    private Double latitude;

    @NotNull
    @ApiModelProperty(notes = "The location longitude")
    private Double longitude;

    @ApiModelProperty(notes = "The location address")
    private String address;

    @ApiModelProperty(notes = "The location city")
    private String city;

    @ApiModelProperty(notes = "The location country")
    private String country;

    public Geolocation( Double latitude,  Double longitude, String address, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Geolocation(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
