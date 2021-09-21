<%-- 
    Document   : Signup
    Created on : Sep 9, 2021, 12:53:25 PM
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
                    <p class="title">Welcome to Orgasm</p>
                    <div class="inputs">
                        <div>
                            <input id="email" name="email" placeholder="Email" type="text">
                        </div>
                        <div>
                            <input id="username" name="username" placeholder="Username" type="text">
                        </div>
                        <div>
                            <input id="password" name="password" placeholder="Password" type="password">
                        </div>
                        <p class="error">${error}</p>
                        <p class="description">Already have an account? <a href="login">Login!</a></p>
                    </div>
                    <div class="actions">
                        <button>Sign up</button>
                    </div>
                </form>
            </div>
        </div>
                        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
