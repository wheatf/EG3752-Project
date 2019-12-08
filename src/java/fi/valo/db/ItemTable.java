/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.db;

import fi.valo.model.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
public class ItemTable extends Table {

    public ItemTable(DataSource dataSource) {
        super(dataSource);
    }

    public Item find(int id) {
        try {
            connection = getConnection();

            statement = connection.prepareStatement("SELECT * FROM item "
                    + "WHERE itemId = ?");
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getInt("itemId"));
                item.setItemDescription(resultSet.getString("itemDescription"));
                item.setBrand(resultSet.getString("brand"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setPoints(resultSet.getInt("points"));

                return item;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public List<Item> findByItemDescription(String itemDescription) {
        try {
            connection = getConnection();

            statement = connection.prepareStatement("SELECT * FROM item "
                    + "WHERE itemDescription LIKE ?");
            statement.setString(1, "%" + itemDescription + "%");

            resultSet = statement.executeQuery();

            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getInt("itemId"));
                item.setItemDescription(resultSet.getString("itemDescription"));
                item.setBrand(resultSet.getString("brand"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setPoints(resultSet.getInt("points"));

                items.add(item);
            }

            return items;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }
}
