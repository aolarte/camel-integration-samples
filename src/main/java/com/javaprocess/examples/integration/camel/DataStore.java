package com.javaprocess.examples.integration.camel;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: andres
 * Date: 10/21/2015
 * Time: 7:55 PM
 */
public class DataStore {

    @Inject
    DataSource dataSource;

    public void put(String id, Object body) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareCall("INSERT INTO DATA (id,data) VALUES (?,?)")) {
            stmt.setString(1, id);
            stmt.setObject(2, body);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object get(String id) {
        Object ret = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareCall("SELECT * FROM  DATA WHERE id=?")) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ret = rs.getObject("data");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void remove(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareCall("DELETE FROM  DATA WHERE id=?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
