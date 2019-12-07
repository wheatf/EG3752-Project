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

        HttpSession session = request.getSession();

        List<QuantityItem> sessionItems = ((List<QuantityItem>) session.getAttribute("sessionItems"));
        QuantityItem item = null;
        for (QuantityItem qi : sessionItems) {
            if (qi.getItemId() == itemId) {
                item = qi;
                break;
            }
        }
        
        item.setQuantity(item.getQuantity() - quantity);

        if (item.getQuantity() == 0) {
            sessionItems.remove(item);
        }

        request.getRequestDispatcher("calculateTotalPrice").include(request, response);
        request.getRequestDispatcher("calculateTotalPoints").include(request, response);

        request.getSession().setAttribute("success", quantity + " of " + item.getItemDescription() + " are removed from the cart!");
        
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
