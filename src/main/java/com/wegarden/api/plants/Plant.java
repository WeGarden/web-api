package com.wegarden.api.plants;

import com.wegarden.api.areas.Area;
import com.wegarden.api.observations.Statement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Plant.
 */
@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] image;

    @ManyToOne
    private Area area;

    private String species;

    private String family;

    private Date date;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Statement> statements = new ArrayList<>();

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public List<Statement> getStatements() {
        return statements;
    }

    public Plant addStatement(Statement statement) {
        this.statements.add(statement);
        return this;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}

