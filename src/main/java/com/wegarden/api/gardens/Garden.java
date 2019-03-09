package com.wegarden.api.gardens;

import com.wegarden.api.areas.Area;
import com.wegarden.api.coords.Coord;
import com.wegarden.api.geolocation.Geolocation;
import com.wegarden.api.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @NotBlank
    private String name;

    private String description;

    private String gardenType;

    @OneToOne(cascade = CascadeType.ALL)
    private Geolocation location;

    private boolean isPrivate;

    @Lob
    private byte[] image;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // we need to use lists isntead of sets maintain the order for coordinate
    private List<Coord> coordList = new ArrayList<>();

    @OneToMany(mappedBy = "garden",cascade = CascadeType.ALL)
    private List<Area> areas = new ArrayList<>();

    public Garden(User user, @NotBlank String name, String description, String gardenType, Geolocation location, boolean isPrivate) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.gardenType = gardenType;
        this.location = location;
        this.isPrivate = isPrivate;
    }

    public Garden(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Geolocation getLocation() {
        return location;
    }

    public void setLocation(Geolocation location) {
        this.location = location;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    public void setCoordList(List<Coord> coordList) {
        this.coordList = coordList;
    }

    public Garden addCoord(Coord coord) {
        coordList.add(coord);
        return this;
    }

    public Garden removeCoord(Coord coord) {
        coordList.remove(coord);
        return this;
    }

    public Garden addAllCoords(List<Coord> coords){
        coordList.addAll(coords);
        return this;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
