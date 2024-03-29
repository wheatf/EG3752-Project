/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo;

import fi.valo.db.CustomerTable;
import fi.valo.db.OrderDetailsTable;
import fi.valo.model.OrderDetails;
import fi.valo.model.Orders;
import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        OrderDetailsTable orderDetailsTable = new OrderDetailsTable(dataSource);
        Orders orders = new Orders();
        
        orders.setCustomerId((int) session.getAttribute("customerId"));
        orders.setOrderPrice((BigDecimal) session.getAttribute("totalPrice"));
        orders.setOrderPoints((int) session.getAttribute("totalPoints"));
        
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<QuantityItem> sessionItems = (List<QuantityItem>) session.getAttribute("sessionItems");
        for (QuantityItem qi : sessionItems) {
            OrderDetails orderDetails = new OrderDetails();
            
            orderDetails.setItemId(qi.getItemId());
            orderDetails.setQuantity(qi.getQuantity());
            
            orderDetailsList.add(orderDetails);
        }
        
        orderDetailsTable.add(orders, orderDetailsList);
        
        CustomerTable customerTable = new CustomerTable(dataSource);
        String customerName = customerTable.find((int) session.getAttribute("customerId")).getFullName();

        session.setAttribute("customerName", customerName);
        session.removeAttribute("sessionItems");

        response.sendRedirect("checkout_success.jsp");
    }
}
