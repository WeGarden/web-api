package com.wegarden.api.areas;

import com.wegarden.api.coords.Coord;
import com.wegarden.api.gardens.Garden;
import com.wegarden.api.plants.Plant;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Area.
 */
@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Garden garden;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "area")
    private List<Plant> plants = new ArrayList<>();

    @OneToMany(fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    // we need to use lists isntead of sets maintain the order for coordinate
    private List<Coord> coordList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Area name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public Area image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public List<Plant> getPlants() {
        return plants;
    }

    public Area plants(List<Plant> plants) {
        this.plants = plants;
        return this;
    }

    public Area addPlants(Plant plant) {
        this.plants.add(plant);
        plant.setArea(this);
        return this;
    }

    public Area removePlants(Plant plant) {
        this.plants.remove(plant);
        plant.setArea(null);
        return this;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public Garden getGarden() {
        return garden;
    }

    public Area garden(Garden garden) {
        this.garden = garden;
        return this;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    public void setCoordList(List<Coord> coordList) {
        this.coordList = coordList;
    }
}

