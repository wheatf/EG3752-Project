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
        
        <c:choose>
            <c:when test="${errors != null && errors.size() > 0}">
                <section style="color: red">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </section>
                
                <c:remove var="errors" />
            </c:when>
            
            <c:when test="${success != null}">
                <section style="color: blue">
                    <ul>
                        <li>${success}</li>
                    </ul>
                </section>
                    
                <c:remove var="success" />
            </c:when>
        </c:choose>
        
        <c:choose>
            <c:when test="${sessionItems != null && sessionItems.size() > 0}">
                <section>
                    <table>
                        <tr>
                            <th>Item Name</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Points Redeemable</th>
                            <th style="color: red">Items In Cart</th>
                            <th style="color: green">Total Price</th>
                            <th style="color: green">Total Points Redeemable</th>
                        </tr>
                        <c:forEach items="${sessionItems}" var="item">
                            <tr>
                                <td>${item.getItemDescription()}</td>
                                <td>${item.getBrand()}</td>
                                <td>$${item.getPrice()}</td>
                                <td>${item.getPoints()}</td>
                                <td style="color: red">${item.getQuantity()}</td>
                                <td style="color: green">$${item.getTotalPrice()}</td>
                                <td style="color: green">${item.getTotalPoints()}</td>
                                <td>
                                    <form action="removeCartValidation" method="post">
                                        <input type="hidden" name="itemId" value="${item.getItemId()}"/>
                                        <input type="text" id="quantity" name="quantity" onpaste="return false;" ondrop="return false;"/>
                                        <button type="submit">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </section>

                <section>
                    <div>Total Price in cart: $${totalPrice}</div>
                    <div>Total Points Redeemable in cart: ${totalPoints}</div>

                    <div>
                        <a href="checkout.jsp">Proceed to checkout</a>
                    </div>
                </section>
            </c:when>
            
            <c:otherwise>
                <section>
                    <span>
                        Your cart is empty! <a href="search">Go to list of items?</a>
                    </span>
                </section>
            </c:otherwise>
        </c:choose>
    </body>
</html>
