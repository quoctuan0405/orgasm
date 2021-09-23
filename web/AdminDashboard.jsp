<%-- 
    Document   : AdminDashboard
    Created on : Sep 18, 2021, 6:48:14 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">

        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/adminDashboard.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <p class="title">Admin Dashboard</p>
        </div>
        
        <div class="main">
            <div class="user-management">
                <p class="title">User management</p>
                <div class="list-header">
                    <p>Username</p>
                    <p>Delete Account</p>
                </div>
                <div class="user-list">
                    <c:forEach items="${users}" var="user">
                        <div class="user">
                            <p>${user.username}</p>
                            <form action="" method="POST">
                                <input value="${user.id}" name="userId" type="hidden"/>
                                <button>
                                    <img src="${pageContext.request.contextPath}/assets/images/Remove.svg"/>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
