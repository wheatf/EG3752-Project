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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;
    
    @Override
    protected void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        CustomerTable customerTable = new CustomerTable(dataSource);

        Customer customer = new Customer();
        customer.setFullName(request.getParameter("fullName"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddressLine1(request.getParameter("addressLine1"));
        customer.setAddressLine2(request.getParameter("addressLine2"));
        customer.setPostalCode(request.getParameter("postalCode"));
        customer.setMobile(request.getParameter("mobile"));
        customer.setPassword(request.getParameter("password"));

        int customerId = customerTable.add(customer);
        
        request.getSession().setAttribute("customerId", customerId);

        response.sendRedirect(request.getContextPath() + "/register_success.html");
    }
}