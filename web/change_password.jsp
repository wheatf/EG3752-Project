<%-- 
    Document   : change_password
    Created on : 7 Dec, 2019, 5:10:24 PM
    Author     : Jimmy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <header>
            <h1>Change Password</h1>
        </header>
        
        <c:if test="${errors != null && errors.size() > 0}">
            <section style="color: red">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </section>
        </c:if>
        
        <section>
            <form action="changePasswordValidation" method="post">
                <div>
                    Fields followed by <strong><abbr title="required">*</abbr></strong> are required.
                </div>
                
                <div>
                    <label for="currentPassword">
                        <span>Current password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="currentPassword" name="currentPassword">
                </div>
                
                <div>
                    <label for="newPassword">
                        <span>New password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="newPassword" name="newPassword">
                </div>
                
                <div>
                    <label for="confirmPassword">
                        <span>Confirm new password:</span>
                        <strong><abbr title="required">*</abbr></strong>
                    </label>
                    <input type="password" id="confirmPassword" name="confirmPassword">
                </div>
                
                <div>
                    <button type="submit">Confirm changes</button>
                </div>
            </form>
        </section>
    </body>
</html>
