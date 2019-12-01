/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.db;

import fi.valo.model.Item;
import fi.valo.model.OrderDetails;
import fi.valo.model.QuantityItem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<QuantityItem> findItems(int orderId) {
        try {
            connection = getConnection();
            
            statement = connection.prepareStatement("SELECT orderdetails.quantity, item.* FROM orderdetails "
                                                    + "INNER JOIN item "
                                                    + "ON orderdetails.itemid = item.itemId "
                                                    + "WHERE orderid = ?");
            statement.setInt(1, orderId);
            
            resultSet = statement.executeQuery();
            
            List<QuantityItem> items = new ArrayList<>();
            while (resultSet.next()) {
                QuantityItem item = new QuantityItem();
                item.setItemId(resultSet.getInt("itemId"));
                item.setItemDescription(resultSet.getString("itemDescription"));
                item.setBrand(resultSet.getString("brand"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setPoints(resultSet.getInt("points"));
                item.setQuantity(resultSet.getInt("quantity"));
                
                items.add(item);
            }
            
            return items;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
}