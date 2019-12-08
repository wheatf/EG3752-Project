/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jimmy
 */
@WebFilter(urlPatterns = {"/showOrders",
    "/calculateTotalPoints",
    "/removeCart",
    "/checkout",
    "/profile",
    "/showOrderDetails",
    "/changePassword",
    "/addCart",
    "/search",
    "/calculateTotalPrice",
    "/addCartValidation",
    "/removeCartValidation",
    "/changePasswordValidation",
    "/checkout_success.jsp",
    "/change_password.jsp",
    "/search_item.jsp",
    "/checkout.jsp",
    "/profile.jsp",
    "/ordered_items.jsp",
    "/orders.jsp",
    "/register_success.html",
    "/change_password_success.html",
    "/cart.jsp"
})
public class AuthenticatedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req,
            ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
