<%-- 
    Document   : search_item
    Created on : 23 Oct, 2019, 12:23:13 AM
    Author     : Jimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <header>
            <h1>List of items for sale</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="profile.jsp">Go to my profile</a></li>
                <li><a href="cart.jsp">My shopping cart</a></li>
            </ul>
        </nav>
        
        <section>
            <h2>Search for items</h2>
            
            <form action="search" method="get">
                <div>
                    <label for="search_item_name">
                        <span>Name of item:</span>
                    </label>
                    <input type="text" 
                           id="search_item_name" 
                           name="search_item_name">
                </div>
                
                <div>
                    <button type="submit">Search</button>
                </div>
            </form>
        </section>
        
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
    </body>
</html>
