/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.valo.validation;

import fi.valo.db.ItemTable;
import fi.valo.model.Item;
import fi.valo.model.QuantityItem;
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
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Jimmy
 */
@WebServlet("/addCartValidation")
public class AddCartValidationServlet extends HttpServlet {

    @Resource(name = "jdbc/velo")
    private DataSource dataSource;

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
            
            ItemTable itemTable = new ItemTable(dataSource);
            Item item = itemTable.find(itemId);
            itemTable.close();
            
            if (item == null) {
                errors.add("Item is not found. If problem persists, contact the administrator.");
            } else if (quantity <= 0) {
                errors.add("At least 1 quantity must be selected!");
            } else if (quantity > 20) {
                errors.add("Only a maximum of 20 quantity is allowed!");
            } else if (sessionItems != null && sessionItems.size() > 0) {
                QuantityItem currentItem = null;

                for (QuantityItem qi : sessionItems) {
                    if (qi.getItemId() == itemId) {
                        currentItem = qi;
                        break;
                    }
                }

                if (currentItem != null && currentItem.getQuantity() + quantity > 20) {
                    errors.add("Quantity will exceed the maximum amount of 20!\nCurrently there are "
                            + currentItem.getQuantity() + " in the cart.");
                }
            }
        }

        if (errors.size() > 0) {
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "/search");
        } else {
            request.getRequestDispatcher("addCart").forward(request, response);
        }
    }
}
