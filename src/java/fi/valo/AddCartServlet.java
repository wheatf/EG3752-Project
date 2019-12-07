/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo;

import fi.valo.db.ItemTable;
import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {

    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ItemTable itemTable = new ItemTable(dataSource);
        QuantityItem item = new QuantityItem(itemTable.find(itemId));

        itemTable.close();

        item.setQuantity(quantity);

        HttpSession session = request.getSession();

        List<QuantityItem> sessionItems = Optional.
                ofNullable((List<QuantityItem>) session.getAttribute("sessionItems")).
                orElse((new ArrayList<>()));

        if (sessionItems.contains(item)) {
            QuantityItem currentItem = sessionItems.get(sessionItems.indexOf(item));

            currentItem.setQuantity(currentItem.getQuantity() + quantity);

            if (currentItem.getQuantity() > 20) {
                currentItem.setQuantity(20);
            }
        } else {
            if (item.getQuantity() > 20) {
                item.setQuantity(20);
            }

            sessionItems.add(item);
        }

        session.setAttribute("sessionItems", sessionItems);

        request.getRequestDispatcher("calculateTotalPrice").include(request, response);
        request.getRequestDispatcher("calculateTotalPoints").include(request, response);
        
        request.getSession().setAttribute("success", quantity + " of " + item.getItemDescription() + " are added to the cart!");
        
        response.sendRedirect(request.getContextPath() + "/search");
    }

}
