package com.wegarden.api.gardens;

import com.wegarden.api.geolocation.Geolocation;
import com.wegarden.api.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @OneToOne
    private Geolocation location;

    private boolean isPrivate;

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
}
