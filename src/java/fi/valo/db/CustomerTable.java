/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import fi.valo.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class CustomerTable {
    private final DataSource dataSource;
    
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    public CustomerTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void add(Customer customer) {
        try {
            connection = dataSource.getConnection();
            
            statement = connection.prepareCall("INSERT INTO customer "
                    + "(fullname, email, addressline1, addressline2, postalcode, mobile, password)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddressLine1());
            statement.setString(4, customer.getAddressLine2().isEmpty() ? null : customer.getAddressLine2());
            statement.setString(5, customer.getPostalCode());
            statement.setString(6, customer.getMobile());
            statement.setString(7, customer.getPassword());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
