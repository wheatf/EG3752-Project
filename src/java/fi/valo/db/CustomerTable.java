/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import fi.valo.model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class CustomerTable extends Table {

    public CustomerTable(DataSource dataSource) {
        super(dataSource);
    }
    
    public Customer find(int id) {
        try {
            connection = getConnection();
            
            statement = connection.prepareStatement("SELECT * FROM customer "
                                                    + "WHERE customerId = ?");
            statement.setInt(1, id);
            
            resultSet = statement.executeQuery();

            return extractResultSet(resultSet);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
    
    public Customer findByEmail(String email) {
        try {            
            connection = getConnection();
            
            statement = connection.prepareStatement("SELECT * FROM customer "
                                                    + "WHERE email = ?");
            statement.setString(1, email);
            
            resultSet = statement.executeQuery();
            
            return extractResultSet(resultSet);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
    
    public void add(Customer customer) {
        try {  
            connection = getConnection();
            
            statement = connection.prepareStatement("INSERT INTO customer "
                    + "(fullname, email, addressline1, addressline2, postalcode, mobile, password)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddressLine1());
            statement.setString(4, customer.getAddressLine2().trim().isEmpty() ? null : customer.getAddressLine2());
            statement.setString(5, customer.getPostalCode());
            statement.setString(6, customer.getMobile());
            statement.setString(7, customer.getPassword());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void updatePassword(int id, String password) {
        try {
            connection = getConnection();
            
            statement = connection.prepareStatement("UPDATE customer "
                                                    + "SET password = ? "
                                                    + "WHERE customerId = ?");
            statement.setString(1, password);
            statement.setInt(2, id);
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private Customer extractResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customerId"));
            customer.setFullName(resultSet.getString("fullname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setAddressLine1(resultSet.getString("addressline1"));
            customer.setAddressLine2(resultSet.getString("addressline2"));
            customer.setPostalCode(resultSet.getString("postalcode"));
            customer.setMobile(resultSet.getString("mobile"));
            customer.setPassword(resultSet.getString("password"));

            return customer;
        }
        
        return null;
    }
}