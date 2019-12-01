<%-- 
    Document   : orders
    Created on : 17 Nov, 2019, 7:05:55 PM
    Author     : Jimmy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
    </head>
    <body>
        <header>
            <h1>Display Order</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="profile">Go to profile</a></li>
            </ul>
        </nav>
        
        <section>
            <table>
                <tr>
                    <th>Total Price</th>
                    <th>Total Points</th>
                    <th>Ordered Date</th>
                    <th>Ordered Time</th>
                </tr>
                
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.getOrderPrice()}</td>
                        <td>${order.getOrderPoints()}</td>
                        <td><fmt:formatDate value="${order.getTimestamp()}" pattern="dd/MM/yyyy" /></td>
                        <td><fmt:formatDate value="${order.getTimestamp()}" pattern="hh:mm a" /></td>
                        <td><a href="showOrderDetails?orderId=${order.getOrderId()}">Show ordered items</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
