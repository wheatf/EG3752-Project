/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import fi.valo.model.Orders;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class OrdersTable extends Table {
    
    public OrdersTable(DataSource dataSource) {
        super(dataSource);
    }
    
    public int add(Orders orders) {
        try {
            connection = getConnection();
            
            statement = connection.prepareStatement("INSERT INTO orders "
                                                    + "(customerId, orderprice, orderpoints) "
                                                    + "VALUES (?, ?, ?)",
                                                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orders.getCustomerId());
            statement.setBigDecimal(2, orders.getOrderPrice());
            statement.setInt(3, orders.getOrderPoints());
            
            statement.executeUpdate();
            
            resultSet = statement.getGeneratedKeys();
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return -1;
    }
}
