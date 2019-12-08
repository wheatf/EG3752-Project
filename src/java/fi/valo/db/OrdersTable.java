/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.db;

import fi.valo.model.Orders;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class OrdersTable extends Table {

    public OrdersTable(DataSource dataSource) {
        super(dataSource);
    }

    public Orders find(int ordersId) {
        try {
            connection = getConnection();

            statement = connection.prepareStatement("SELECT * FROM orders "
                    + "WHERE orderid = ?");
            statement.setInt(1, ordersId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("orderid"));
                order.setCustomerId(resultSet.getInt("customerid"));
                order.setOrderPrice(resultSet.getBigDecimal("orderprice"));
                order.setOrderPoints(resultSet.getInt("orderpoints"));
                order.setTimestamp(resultSet.getTimestamp("timestamp"));

                return order;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public List<Orders> findByCustomerId(int customerId) {
        try {
            connection = getConnection();

            statement = connection.prepareStatement("SELECT * FROM orders "
                    + "WHERE customerid = ?");
            statement.setInt(1, customerId);

            resultSet = statement.executeQuery();

            List<Orders> orders = new ArrayList<>();
            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("orderid"));
                order.setCustomerId(resultSet.getInt("customerid"));
                order.setOrderPrice(resultSet.getBigDecimal("orderprice"));
                order.setOrderPoints(resultSet.getInt("orderpoints"));
                order.setTimestamp(resultSet.getTimestamp("timestamp"));

                orders.add(order);
            }

            return orders;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
}