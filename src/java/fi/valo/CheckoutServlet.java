/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.OrderDetailsTable;
import fi.valo.db.OrdersTable;
import fi.valo.model.OrderDetails;
import fi.valo.model.Orders;
import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.math.BigDecimal;
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
        if (session.getAttribute("customerId") != null
                && session.getAttribute("totalPrice") != null 
                && session.getAttribute("totalPoints") != null) {
            
            OrdersTable ordersTable = new OrdersTable(dataSource);
            Orders orders = new Orders();
            
            orders.setCustomerId((int)session.getAttribute("customerId"));
            orders.setOrderPrice((BigDecimal)session.getAttribute("totalPrice"));
            orders.setOrderPoints((int)session.getAttribute("totalPoints"));
            
            int generatedId = ordersTable.add(orders);
            
            ordersTable.close();
            
            if (generatedId >= 0) {
                OrderDetailsTable orderDetailsTable = new OrderDetailsTable(dataSource);
                
                List<QuantityItem> sessionItems = (List<QuantityItem>)session.getAttribute("sessionItems");
                for (QuantityItem qi : sessionItems) {
                    OrderDetails orderDetails = new OrderDetails();
                    
                    orderDetails.setOrderId(generatedId);
                    orderDetails.setItemId(qi.getItemId());
                    orderDetails.setQuantity(qi.getQuantity());
                    
                    orderDetailsTable.add(orderDetails);
                }
                
                orderDetailsTable.close();
            }
        }
        
        session.removeAttribute("sessionItems");
        session.removeAttribute("totalPrice");
        session.removeAttribute("totalPoints");
        
        response.sendRedirect("checkout_success.html");
    }   
}