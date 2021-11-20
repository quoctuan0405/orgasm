<%-- 
    Document   : ForgotPassword
    Created on : Sep 30, 2021, 11:53:31 AM
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
            <div class="form">
                <form method="POST">
                    <p class="title">Reset your password</p>
                    <div class="inputs">
                        <div>
                            <input id="username" name="username" placeholder="Username" type="text">
                        </div>
                        <p class="error">${error}</p>
                    </div>
                    <div class="actions">
                        <button>Send email verification</button>
                    </div>
                </form>
            </div>
        </div>
                        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
