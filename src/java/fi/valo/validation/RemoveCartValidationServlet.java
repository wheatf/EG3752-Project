/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.validation;

import fi.valo.model.QuantityItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Jimmy
 */
@WebServlet("/removeCartValidation")
public class RemoveCartValidationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        IntegerValidator integerValidator = IntegerValidator.getInstance();

        List<QuantityItem> sessionItems = (List<QuantityItem>) request.
                getSession().
                getAttribute("sessionItems");
        String paramQuantity = request.getParameter("quantity");

        if (paramQuantity.trim().isEmpty()) {
            errors.add("Quantity must not be empty!");
        } else if (!integerValidator.isValid(paramQuantity)) {
            errors.add("Quantity must only consists of whole numbers!");
        } else {
            int itemId = Integer.valueOf(request.getParameter("itemId"));
            int quantity = Integer.valueOf(paramQuantity);

            if (quantity <= 0) {
                errors.add("At least 1 quantity must be selected!");
            } else if (quantity > 20) {
                errors.add("Quantity cannot exceed the maximum of 20!");
            } else {
                if (sessionItems != null && sessionItems.size() > 0 ) {
                    QuantityItem currentItem = null;

                    for (QuantityItem item : sessionItems) {
                        if (item.getItemId() == itemId) {
                            currentItem = item;
                            break;
                        }
                    }

                    if (currentItem != null && currentItem.getQuantity() - quantity < 0) {
                        errors.add("Quantity will be less than 0!\nCurrently there are "
                                + currentItem.getQuantity() + " in the cart.");
                    } else if (currentItem == null) {
                        errors.add("Item does not exists in cart! If problem persists, contact the administrator.");
                    }
                } else {
                    errors.add("Item does not exists in cart! If problem persists, contact the administrator.");
                }
            }
        }

        if (errors.size() > 0) {
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        } else {
            request.getRequestDispatcher("removeCart").forward(request, response);
        }
    }

}
