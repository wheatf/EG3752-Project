/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.validation;

import fi.valo.db.CustomerTable;
import fi.valo.model.Customer;
import fi.valo.utility.Digest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
@WebServlet("/changePasswordValidation")
public class ChangePasswordValidationServlet extends HttpServlet {

    @Resource(name = "jdbc/velo")
    private DataSource dataSource;
    
    @Override
    protected void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (currentPassword.trim().isEmpty()) {
            errors.add("Current Password must not be empty!");
        } else {
            int userId = (int)request.getSession().getAttribute("customerId");
            currentPassword = Base64.getEncoder().encodeToString(Digest.sha256(currentPassword));
            
            CustomerTable customerTable = new CustomerTable(dataSource);
            Customer customer = customerTable.find(userId);
            customerTable.close();

            if (!customer.getPassword().equals(currentPassword)) {
                errors.add("Current Password does not match your actual password!");
            }
        }
        
        if (newPassword.trim().isEmpty()) {
            errors.add("New Password must not be empty!");
        } else if (!confirmPassword.equals(newPassword)) {
            errors.add("Confirm Password must match New Password!");
        }
        
        if (errors.size() > 0) {
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/change_password.jsp");
        } else {
            request.getRequestDispatcher("changePassword").forward(request, response);
        }
    }
}
