<%-- 
    Document   : ChangePassword
    Created on : Sep 29, 2021, 11:57:38 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">

        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/authForm.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        
        <div class="login">
            <div class="form">
                <form method="POST">
                    <p class="title">Change Password</p>
                    <div class="inputs">
                        <div>
                            <input id="old-password" name="oldPassword" placeholder="Password" type="password">
                        </div>
                        <div>
                            <input id="new-password" name="newPassword" placeholder="New Password" type="password">
                        </div>
                        <p class="error">${error}</p>
                    </div>
                    <div class="actions">
                        <button>Change Password</button>
                    </div>
                </form>
            </div>
        </div>
                        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
