/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.filter;

import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebFilter(urlPatterns = {"/checkout",
    "/checkout.jsp"})
public class CheckoutFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req,
            ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        if (session.getAttribute("sessionItems") == null
                || ((List<QuantityItem>) session.getAttribute("sessionItems")).size() <= 0) {
            List<String> errors = new ArrayList<>();
            errors.add("Unable to perform checkout with empty cart!");

            session.setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
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
