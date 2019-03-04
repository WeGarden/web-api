package com.wegarden.api.observations.payload;

import com.wegarden.api.observations.Value;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class StatementRequest {


    @ApiModelProperty(notes = "The given name of the statement")
    private String name;

    @ApiModelProperty(notes = "The id of the protocol")
    private Long protocolId;

    @ApiModelProperty(notes = "List of values to each entry of the protocol")
    private List<Value> values;

    @ApiModelProperty(allowableValues = "observation, action", notes = "type of the statement")
    private String statementType;

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

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }
}
