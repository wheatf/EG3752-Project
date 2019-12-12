/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.CustomerTable;
import fi.valo.db.OrdersTable;
import fi.valo.model.Customer;
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
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = (int)session.getAttribute("customerId");
        
        CustomerTable customerTable = new CustomerTable(dataSource);
        Customer customer = customerTable.find(customerId);
        
        OrdersTable orderTable = new OrdersTable(dataSource);
        List<Orders> orders = orderTable.findByCustomerId(customerId);
        
        int totalPoints = 0;
        for (Orders o : orders) {
            totalPoints += o.getOrderPoints();
        }
        
        request.setAttribute("totalPoints", totalPoints);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
