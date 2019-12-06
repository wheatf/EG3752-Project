<%-- 
    Document   : login
    Created on : 4 Dec, 2019, 11:09:57 PM
    Author     : Jimmy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
    </head>
    
    <body>
        <header>
            <h1>Log in to the portal</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.html">Back to main page</a></li>
                <li><a href="register.jsp">Register a new account</a></li>
            </ul>
        </nav>
        
        <c:if test="${errors != null && errors.size() > 0}">
            <section style="color: red">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </section>
            
            <c:remove var="errors" />
        </c:if>
        
        <section>
            <form action="loginValidation" method="post">
                <div>
                    Fields followed by <strong><abbr title="required">*</abbr></strong> are required.
                </div>
                
                <div>
                    <label for="email">
                        <span>Email:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="email" name="email">
                </div>
                
                <div>
                    <label for="password">
                        <span>Password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="password" name="password">
                </div>
                
                <div>
                    <button type="submit">Login</button>
                </div>
            </form>
        </section>
    </body>
</html>
