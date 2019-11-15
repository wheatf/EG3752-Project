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
@WebServlet("/removeCart")
public class RemoveCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                    throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        if (itemId > -1 && quantity > 0) {
            HttpSession session = request.getSession();

            List<QuantityItem> sessionItems = ((List<QuantityItem>) session.getAttribute("sessionItems"));
            if (sessionItems != null && sessionItems.size() > 0) {

                QuantityItem item = null;
                for (QuantityItem qi : sessionItems) {
                    if (qi.getItemId() == itemId) {
                        item = qi;
                        break;
                    }
                }

                // TODO: Remove item from session if it is empty.
                if (item != null && item.getQuantity() - quantity >= 0) {
                    item.setQuantity(item.getQuantity() - quantity);
                }
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
