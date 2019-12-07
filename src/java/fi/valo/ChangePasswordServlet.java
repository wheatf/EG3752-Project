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
        String newPassword = request.getParameter("newPassword");
        
        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customerId");

        CustomerTable customerTable = new CustomerTable(dataSource);

        customerTable.updatePassword(customerId, newPassword);

        customerTable.close();

        response.sendRedirect(request.getContextPath() + "/change_password_success.html");
    }
}
