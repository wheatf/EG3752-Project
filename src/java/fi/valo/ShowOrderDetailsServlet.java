/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.OrderDetailsTable;
import fi.valo.db.OrdersTable;
import fi.valo.model.Orders;
import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
@WebServlet("/showOrderDetails")
public class ShowOrderDetailsServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request,
                            HttpServletResponse response) 
                    throws ServletException, IOException {
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        
        OrderDetailsTable orderDetailsTable = new OrderDetailsTable(dataSource);
        List<QuantityItem> items = orderDetailsTable.findItems(orderId);
        
        OrdersTable ordersTable = new OrdersTable(dataSource);
        Orders order = ordersTable.find(orderId);
        
        request.setAttribute("order", order);
        request.setAttribute("items", items);
        request.getRequestDispatcher("ordered_items.jsp").forward(request, response);
    }
}
