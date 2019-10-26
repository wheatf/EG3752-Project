<%-- 
    Document   : checkout
    Created on : 23 Oct, 2019, 11:07:39 PM
    Author     : Jimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
   
    <body>
        <header>
            <h1>Checkout</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="cart.jsp">Back to cart</a></li>
            </ul>
        </nav>
    
        <section>
            <h2>Please check your items before confirming order</h2>
            
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
            <form action="checkout" method="post">
                <div>    
                    <button type="submit">Confirm order</button>
                </div>
            </form>
        </section>
    </body>
</html>
