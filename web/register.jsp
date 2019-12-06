<%-- 
    Document   : register
    Created on : 6 Dec, 2019, 10:11:34 PM
    Author     : Jimmy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    
    <body>
        <header>
            <h1>Register a new account</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.html">Back to main page</a></li>
                <li><a href="login.jsp">Log in instead</a></li>
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
            <form action="registerValidation" method="post">
                <div>
                    Fields followed by <strong><abbr title="required">*</abbr></strong> are required.
                </div>
                
                <div>
                    <label for="fullName">
                        <span>Full Name:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="fullName" name="fullName">
                </div>
                
                <div>
                    <label for="email">
                        <span>Email:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="email" name="email">
                </div>
                
                <div>
                    <label for="addressLine1">
                        <span>Address Line 1:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="addressLine1" name="addressLine1">
                </div>
                
                <div>
                    <label for="addressLine2">
                        <span>Address Line 2:</span>
                    </label>
                    <input type="text" id="addressLine2" name="addressLine2">
                </div>
                
                <div>
                    <label for="postalCode">
                        <span>Postal Code:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="postalCode" name="postalCode">
                </div>
                
                <div>
                    <label for="mobile">
                        <span>Mobile:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="text" id="mobile" name="mobile">
                </div>
                
                <div>
                    <label for="password">
                        <span>Password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="password" name="password">
                </div>
                
                <div>
                    <label for="confirmPassword">
                        <span>Confirm Password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="confirmPassword" name="confirmPassword">
                </div>
                
                <div>
                    <button type="submit">Register</button>
                </div>
            </form>
        </section>
    </body>
</html>
