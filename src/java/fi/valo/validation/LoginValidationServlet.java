/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Jimmy
 */
@WebServlet("/loginValidation")
public class LoginValidationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        EmailValidator emailValidator = EmailValidator.getInstance();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (email.trim().isEmpty()) {
            errors.add("Email must not be empty!");
        } else if (!emailValidator.isValid(email)) {
            errors.add("Email must be a valid email address!");
        }
        
        if (password.trim().isEmpty()) {
            errors.add("Password must not be empty!");
        }
        
        if (errors.size() > 0) {
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            request.getRequestDispatcher("login").forward(request, response);
        }
    }
}
