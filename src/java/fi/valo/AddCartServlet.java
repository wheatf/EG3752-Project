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
        
        if (itemId > -1 && quantity > 0) {
            ItemTable itemTable = new ItemTable(dataSource);
            QuantityItem item = new QuantityItem(itemTable.find(itemId));
            
            itemTable.close();
            
            // TODO: Only allow max of 20 quantity
            item.setQuantity(quantity);

            HttpSession session = request.getSession();

            List<QuantityItem> sessionItems = Optional.
                                                ofNullable((List<QuantityItem>) session.getAttribute("sessionItems")).
                                                orElse((new ArrayList<>()));
            // TODO: Check if item is already in the list.
            sessionItems.add(item);

            session.setAttribute("sessionItems", sessionItems);
        }
        
        request.getRequestDispatcher("calculateTotalPrice").forward(request, response);
    }
    
}