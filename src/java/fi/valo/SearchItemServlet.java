/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jimmy
 */
@WebServlet("/search")
public class SearchItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        request.getRequestDispatcher("/search_item.jsp")
                .forward(request, response);
    }
    
}
