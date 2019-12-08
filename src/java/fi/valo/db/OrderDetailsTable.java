/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.db;

import fi.valo.model.OrderDetails;
import fi.valo.model.Orders;
import fi.valo.model.QuantityItem;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void add(Orders orders, List<OrderDetails> orderDetails) {
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
                int orderId = resultSet.getInt(1);

                for (OrderDetails od : orderDetails) {
                    statement = connection.prepareStatement("INSERT INTO orderdetails "
                            + "(orderid, itemid, quantity) "
                            + "VALUES (?, ?, ?)");

                    statement.setInt(1, orderId);
                    statement.setInt(2, od.getItemId());
                    statement.setInt(3, od.getQuantity());
                    
                    statement.executeUpdate();
                }
                
                commit();
            } else {
                rollback();
            }
        } catch (SQLException ex) {
            rollback();
            System.err.println(ex.getMessage());
        } finally {
            close();
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
        } finally {
            close();
        }

        return null;
    }
}
