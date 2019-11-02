/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public abstract class Table {
    private final DataSource dataSource;
    
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;
    
    public Table(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    protected Connection getConnection() throws SQLException {
        if (connection == null) {
            return dataSource.getConnection();
        } else {
            return connection;
        }
    }
    
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
