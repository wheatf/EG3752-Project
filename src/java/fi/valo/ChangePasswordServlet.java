/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo;

import fi.valo.db.CustomerTable;
import java.io.IOException;
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
@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource dataSource;
    
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                    throws ServletException, IOException {
        String newPassword = (String)request.getParameter("newPassword");
        String confirmPassword = (String)request.getParameter("confirmPassword");
        
        if (newPassword.equals(confirmPassword)) {
            HttpSession session = request.getSession();
            int customerId = (int)session.getAttribute("customerId");
            
            String currentPassword = (String)request.getParameter("currentPassword");
            
            CustomerTable customerTable = new CustomerTable(dataSource);
            if (currentPassword.equals(customerTable.find(customerId).getPassword())) {
                customerTable.updatePassword(customerId, newPassword);
                
                response.sendRedirect(request.getContextPath() + "/change_password_success.html");
                return;
            }
        }
        
        request.getRequestDispatcher("change_password.html").forward(request, response);
    }
}
