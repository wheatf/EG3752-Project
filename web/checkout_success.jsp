<%-- 
    Document   : checkout_success
    Created on : 7 Dec, 2019, 7:47:50 PM
    Author     : Jimmy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Successful</title>
    </head>
    <body>
        <header>
            <h1>Checkout Successful!</h1>
        </header>

        <nav>
            <ul>
                <li><a href="search">Back to list of item</a></li>
            </ul>
        </nav>
        
        <section>
            <div>
                Name: ${customerName}
                <c:remove var="customerName" />
            </div>
            
            <div>
                Total Price: $${totalPrice}
                <c:remove var="totalPrice" />
            </div>
            
            <div>
                Total Points: ${totalPoints}
                <c:remove var="totalPoints" />
            </div>
        </section>
    </body>
</html>
