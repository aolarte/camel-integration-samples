package com.javaprocess.examples.integration.camel;

import org.apache.commons.dbcp.BasicDataSource;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

/**
 * User: andres
 * Date: 10/21/2015
 * Time: 8:04 PM
 */

@Singleton
public class DataSourceProvider {

    @Inject
    private H2ServerWrapper h2ServerWrapper;

    @Produces
    public BasicDataSource buildDataSource() {


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost:8043/mem:test;INIT=CREATE TABLE IF NOT EXISTS data (ID UUID PRIMARY KEY, DATA OTHER)");
        dataSource.setUsername("sa");

        return dataSource;
    }

    void close(@Disposes BasicDataSource connection) throws SQLException {

        connection.close();

    }
}
