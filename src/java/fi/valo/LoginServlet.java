/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo;

import fi.valo.db.CustomerTable;
import fi.valo.model.Customer;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author 175272M
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;
    
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        CustomerTable customerTable = new CustomerTable(dataSource);
        
        Customer customer = customerTable.find(request.getParameter("email"));
        customerTable.close();
        
        if (customer != null)
        {
            if (customer.getPassword().equals(request.getParameter("password"))) {
                response.sendRedirect(request.getContextPath() + "/search");
                return;
            }
        }
        
        request.getRequestDispatcher("/login.html").forward(request, response);
    }
}
