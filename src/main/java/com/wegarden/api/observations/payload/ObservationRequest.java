package com.wegarden.api.observations.payload;

import com.wegarden.api.observations.Value;

import java.util.Date;
import java.util.List;

public class ObservationRequest {

    private String name;


    private Long protocolId;

    private List<Value> values;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }
}
