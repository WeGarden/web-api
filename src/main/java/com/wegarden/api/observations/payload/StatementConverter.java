package com.wegarden.api.observations.payload;

import com.wegarden.api.observations.Statement;
import com.wegarden.api.util.ModelUtilMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatementConverter {

    private ModelMapper modelMapper = ModelUtilMapper.getModelMapper();
    public StatementResponse convertToDTO(Statement statement){
        return modelMapper.map(statement, StatementResponse.class);
    }

    public Statement convertToEntity(StatementRequest statementRequest){
        Statement statement =  modelMapper.map(statementRequest, Statement.class);
        statement.setId(0L);
        statement.setDate(new Date());
        return statement;
    }
}
