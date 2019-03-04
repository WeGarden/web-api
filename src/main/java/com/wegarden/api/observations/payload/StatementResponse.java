package com.wegarden.api.observations.payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wegarden.api.observations.Value;
import com.wegarden.api.protocols.Protocol;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class StatementResponse {

    private Long id;

    private String name;

    @ApiModelProperty(notes = "The date of the the statement", example = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    private Protocol protocol;

    @ApiModelProperty(notes = "List of values to each entry of the protocol")
    private List<Value> values;

    @ApiModelProperty(allowableValues = "observation, action", notes = "type of the statement")
    private String statementType;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }
}
