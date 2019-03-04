package com.wegarden.api.observations;

import javax.persistence.*;

@Embeddable
public class Entry {

    private String entryName;

    private String type;

    public String getName() {
        return entryName;
    }

    public void setName(String entryName) {
        this.entryName = entryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
