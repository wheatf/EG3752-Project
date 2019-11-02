/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.ItemTable;
import fi.valo.model.Item;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Jimmy
 */
@WebServlet("/search")
public class SearchItemServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;
    
    @Override
    protected void doGet(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        ItemTable itemTable = new ItemTable(dataSource);
        
        List<Item> items = itemTable.
                            findContains(Optional.ofNullable(request.
                                                            getParameter("searchItemName")).
                                                            orElse(""));
        
        itemTable.close();
        
        request.setAttribute("items", items);
        request.getRequestDispatcher("/search_item.jsp")
                .forward(request, response);
    }
    
}
