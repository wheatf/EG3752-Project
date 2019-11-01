/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Resource(name = "jdbc/velo")
    private DataSource database;
    
    @Override
    protected void doPost(HttpServletRequest request, 
                            HttpServletResponse response) 
                        throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM customer "
                                                    + "WHERE email = ? "
                                                    + "AND password = ?");
            statement.setString(1, request.getParameter("email"));
            statement.setString(2, request.getParameter("password"));
            
            resultSet = statement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                response.sendRedirect(request.getContextPath() + "/search_item.jsp");
            } else {
                request.getRequestDispatcher("/login.html").forward(request, response);
            }
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
            
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
