<%-- 
    Document   : profile
    Created on : 26 Oct, 2019, 3:02:02 PM
    Author     : Jimmy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <header>
            <h1>Profile</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="search">Go to list of items</a></li>
                <li><a href="cart.jsp">Go to my shopping cart</a></li>
                <li><a href="showOrders">View placed orders</a></li>
            </ul>
        </nav>
        
        <section>
            <div>
                <span>Full Name: </span>
                <span>${customer.getFullName()}</span>
            </div>
            
            <div>
                <span>Email: </span>
                <span>${customer.getEmail()}</span>
            </div>
            
            <div>
                <span>Address Line 1: </span>
                <span>${customer.getAddressLine1()}</span>
            </div>
            
            <div>
                <span>Address Line 2: </span>
                <span>${customer.getAddressLine2()}</span>
            </div>
            
            <div>
                <span>Postal Code: </span>
                <span>${customer.getPostalCode()}</span>
            </div>
            
            <div>
                <span>Mobile: </span>
                <span>${customer.getMobile()}</span>
            </div>
            
            <div style="color: red">
                <span>Total points received so far: </span>
                <span>${totalPoints}</span>
            </div>
        </section>
            
        <section>
            <a href="change_password.jsp">Change password</a>
        </section>
            
        <section>
            <form action="logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </section>
    </body>
</html>
