/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.model.QuantityItem;
import java.io.IOException;
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
@WebServlet("/calculateTotalPoints")
public class CalculateTotalPointsServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        int totalPoints = 0;
        
        List<QuantityItem> sessionItems = (List<QuantityItem>) session.getAttribute("sessionItems");
        if (sessionItems != null && sessionItems.size() > 0) {
            
            for (QuantityItem qi : sessionItems) {
                totalPoints += qi.getPoints() * qi.getQuantity();
            }
        }
        
        session.setAttribute("totalPoints", totalPoints);
    }
}
