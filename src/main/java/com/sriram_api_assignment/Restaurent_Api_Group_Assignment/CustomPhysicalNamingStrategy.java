package com.sriram_api_assignment.Restaurent_Api_Group_Assignment;


import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import org.hibernate.boot.model.naming.Identifier;


public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        // Keep the table name case exactly as it is in the annotation
        return name;
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        // Keep the column name case exactly as it is in the annotation
        return name;
    }
}
