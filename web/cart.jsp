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
                <li><a href="profile">Go to my profile</a></li>
                <li><a href="search">Back to list of items</a></li>
            </ul>
        </nav>
        
        <section>
            <table>
                <tr>
                    <th>Item Name</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Points Redeemable</th>
                    <th style="color: red">Items In Cart</th>
                </tr>
                <c:forEach items="${sessionItems}" var="item">
                    <tr>
                        <td>${item.getItemDescription()}</td>
                        <td>${item.getBrand()}</td>
                        <td>$${item.getPrice()}</td>
                        <td>${item.getPoints()}</td>
                        <td style="color: red">${item.getQuantity()}</td>
                        <td>
                            <form action="removeCart" method="post">
                                <input type="hidden" name="itemId" value="${item.getItemId()}"/>
                                <input type="number" id="quantity" name="quantity" required/>
                                <button type="submit">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        
        <section>
            <div>Total Price: $${totalPrice}</div>
            <div>Total Points Redeemable: ${totalPoints}</div>
        </section>
            
        <section>
            <div>
                <a href="checkout.jsp">Proceed to checkout</a>
            </div>
        </section>
    </body>
</html>
