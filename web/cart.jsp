<%-- 
    Document   : cart
    Created on : Oct 23, 2019, 2:30:53 PM
    Author     : 175272M
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
    </head>
    <body>
        <header>
            <h1>My Shopping Cart</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="profile.jsp">Go to my profile</a></li>
                <li><a href="search_item.jsp">Back to list of items</a></li>
            </ul>
        </nav>
        
        <section>
            <table>
                <tr>
                    <th>Item Name</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Points Redeemable</th>
                </tr>
                <%--
                <c:forEach items="items" var="item">
                    <tr>
                        <td>${item.itemDescription}</td>
                        <td>${item.brand}</td>
                        <td>${item.price}</td>
                        <td>${item.points}</td>
                    </tr>
                </c:forEach>
                --%>
            </table>
        </section>
            
        <section>
            <div>
                <a href="checkout.jsp">Proceed to checkout</a>
            </div>
        </section>
    </body>
</html>
