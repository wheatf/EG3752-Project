<%-- 
    Document   : ordered_items
    Created on : 17 Nov, 2019, 2:59:47 PM
    Author     : Jimmy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
    </head>
    <body>
        <header>
            <h1>
                Ordered Items for 
                <fmt:formatDate value="${order.getTimestamp()}" pattern="dd/MM/yyyy, hh:mm a" />
            </h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="showOrders">Go back</a></li>
            </ul>
        </nav>
        
        <section>
            <table>
                <tr>
                    <th>Item Description</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Points Redeemable</th>
                    <th>Quantity</th>
                </tr>
                
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.getItemDescription()}</td>
                        <td>${item.getBrand()}</td>
                        <td>$${item.getPrice()}</td>
                        <td>${item.getPoints()}</td>
                        <td>${item.getQuantity()}</td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
