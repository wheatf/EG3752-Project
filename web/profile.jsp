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
                <li><a href="search_item.jsp">Go to list of items</a></li>
                <li><a href="cart.jsp">Go to my shopping cart</a></li>
            </ul>
        </nav>
        
        <section>
            <div>
                <span>Full Name</span>
                <%--<span>${fullName}</span>--%>
            </div>
            
            <div>
                <span>Email</span>
                <%--<span>${email}}--%>
            </div>
            
            <div>
                <span>Address Line 1</span>
                <%--<span>${addressLine1}</span>--%>
            </div>
            
            <div>
                <span>Address Line 2</span>
                <%--<span>${addressLine2}</span>--%>
            </div>
            
            <div>
                <span>Postal Code</span>
                <%--<span>${postalCode}</span>--%>
            </div>
            
            <div>
                <span>Mobile</span>
                <%--<span>${mobile}</span>--%>
            </div>
        </section>
            
        <section>
            <a href="change_password.html">Change password</a>
        </section>
            
        <section>
            <form action="logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </section>
    </body>
</html>
