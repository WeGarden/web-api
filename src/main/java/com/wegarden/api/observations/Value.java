package com.wegarden.api.observations;

import javax.persistence.Embeddable;

@Embeddable
public class Value {


    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
