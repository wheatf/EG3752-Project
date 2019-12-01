/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jimmy
 */
@WebServlet("/calculateTotalPrice")
public class CalculateTotalPriceServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        BigDecimal totalPrice = BigDecimal.ZERO;
        
        List<QuantityItem> sessionItems = (List<QuantityItem>) session.getAttribute("sessionItems");
        if (sessionItems != null && sessionItems.size() > 0) {
            
            for (QuantityItem qi : sessionItems) {
                BigDecimal price = qi.getPrice().multiply(new BigDecimal(qi.getQuantity()));
                
                totalPrice = totalPrice.add(price);
            }
        }
        
        session.setAttribute("totalPrice", totalPrice);
        
        request.getRequestDispatcher("calculateTotalPoints").forward(request, response);
    }
}
