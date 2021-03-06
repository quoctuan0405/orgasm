<%-- 
    Document   : Login
    Created on : Sep 9, 2021, 8:17:03 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        
        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/authForm.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        
        <div class="login">
            
            <c:if test="${notificationMessage != null}">
                <div class="notification">
                    ${notificationMessage}
                </div>
            </c:if>
            
            <div class="form">
                <form method="POST">
                    <p class="title">Welcome to Orgasm</p>
                    <div class="inputs">
                        <div>
                            <input id="username" name="username" placeholder="Username" type="text">
                        </div>
                        <div>
                            <input id="password" name="password" placeholder="Password" type="password">
                        </div>
                        <p class="error">${error}</p>
                        <p class="forgot-password"><a href="${pageContext.request.contextPath}/password/forgot">Forgot your password?</a></p>
                        <p class="description">Don't have an account? <a href="signup">Sign up!</a></p>
                    </div>
                    <div class="actions">
                        <button>Login</button>
                    </div>
                </form>
            </div>
        </div>
                        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
