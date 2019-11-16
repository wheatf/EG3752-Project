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
                <li><a href="profile">Go to my profile</a></li>
                <li><a href="cart.jsp">My shopping cart</a></li>
            </ul>
        </nav>
        
        <section>
            <h2>Search for items</h2>
            
            <form action="search" method="get">
                <div>
                    <label for="searchItemName">
                        <span>Name of item:</span>
                    </label>
                    <input type="text" 
                           id="searchItemName" 
                           name="searchItemName">
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
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.getItemDescription()}</td>
                        <td>${item.getBrand()}</td>
                        <td>$${item.getPrice()}</td>
                        <td>${item.getPoints()}</td>
                        <td>
                            <form action="addCart" method="post">
                                <input type="hidden" name="itemId" value="${item.getItemId()}"/>
                                <input type="number" id="quantity" name="quantity" required/>
                                <button type="submit">Add to cart</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
