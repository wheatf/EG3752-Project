/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import fi.valo.model.OrderDetails;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class OrderDetailsTable extends Table {
    
    public OrderDetailsTable(DataSource dataSource) {
        super(dataSource);
    }
    
    public void add(OrderDetails orderDetails) {
        try {
            connection = getConnection();
            
            statement = connection.prepareStatement("INSERT INTO orderdetails "
                                                    + "(orderid, itemid, quantity) "
                                                    + "VALUES (?, ?, ?)");
            statement.setInt(1, orderDetails.getOrderId());
            statement.setInt(2, orderDetails.getItemId());
            statement.setInt(3, orderDetails.getQuantity());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}