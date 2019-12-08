/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.OrdersTable;
import fi.valo.model.Orders;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
@WebServlet("/showOrders")
public class ShowOrdersServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, 
                            HttpServletResponse response) 
                    throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = (int)session.getAttribute("customerId");
        
        OrdersTable ordersTable = new OrdersTable(dataSource);
        
        List<Orders> orders = ordersTable.findByCustomerId(customerId);
        
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
