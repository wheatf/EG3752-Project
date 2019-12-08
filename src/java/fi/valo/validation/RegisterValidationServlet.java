/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.validation;

import fi.valo.db.CustomerTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Jimmy
 */
@WebServlet("/registerValidation")
public class RegisterValidationServlet extends HttpServlet {

    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        EmailValidator emailValidator = EmailValidator.getInstance();
        IntegerValidator integerValidator = IntegerValidator.getInstance();

        CustomerTable customerTable = new CustomerTable(dataSource);

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String addressLine1 = request.getParameter("addressLine1");
        String postalCode = request.getParameter("postalCode");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (customerTable.findByEmail(email) == null) {
            if (fullName.trim().isEmpty()) {
                errors.add("Full Name must not be empty!");
            }

            if (email.trim().isEmpty()) {
                errors.add("Email must not be empty!");
            } else if (!emailValidator.isValid(email)) {
                errors.add("Email must be a valid email address!");
            }

            if (addressLine1.trim().isEmpty()) {
                errors.add("Address Line 1 must not be empty!");
            }

            if (postalCode.trim().isEmpty()) {
                errors.add("Postal Code must not be empty!");
            } else {
                if (!integerValidator.isValid(postalCode)) {
                    errors.add("Postal Code must consists of only digits!");
                }

                if (postalCode.length() != 6) {
                    errors.add("Postal Code must be exactly 6 digits long!");
                }
            }

            if (mobile.trim().isEmpty()) {
                errors.add("Mobile must not be empty!");
            } else {
                if (!integerValidator.isValid(mobile)) {
                    errors.add("Mobile must consists of only digits!");
                }

                if (mobile.length() != 8) {
                    errors.add("Mobile must be exactly 8 digits long!");
                }
            }

            if (password.trim().isEmpty()) {
                errors.add("Password must not be empty!");
            } else if (!confirmPassword.equals(password)) {
                errors.add("Confirm Password must match Password!");
            }
        } else {
            errors.add("Email already exists! Try login instead.");
        }

        if (errors.size() > 0) {
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/register.jsp");
        } else {
            request.getRequestDispatcher("register").forward(request, response);
        }
    }
}
