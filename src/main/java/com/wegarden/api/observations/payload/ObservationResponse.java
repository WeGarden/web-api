package com.wegarden.api.observations.payload;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Map;

public class ObservationResponse {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    private Long protocolId;

    private Map<String,String> entryByValue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Map<String, String> getEntryByValue() {
        return entryByValue;
    }

    public void setEntryByValue(Map<String, String> entryByValue) {
        this.entryByValue = entryByValue;
    }
}
