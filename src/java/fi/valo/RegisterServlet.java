/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private DataSource database;
    
    @Override
    protected void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = database.getConnection();
            statement = connection.prepareStatement("INSERT INTO customer (fullname, email, addressline1, addressline2, postalcode, mobile, password)"
                                                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, request.getParameter("fullName"));
            statement.setString(2, request.getParameter("email"));
            statement.setString(3, request.getParameter("addressLine1"));
            statement.setString(4, request.getParameter("addressLine2")
                                    .isEmpty() ? null : request.getParameter("addressLine2"));
            statement.setString(5, request.getParameter("postalCode"));
            statement.setString(6, request.getParameter("mobile"));
            // TODO: Hash password with SHA-256
            statement.setString(7, request.getParameter("password"));
            statement.executeUpdate();
            
            response.sendRedirect(request.getContextPath() + "/register_success.html");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
}
