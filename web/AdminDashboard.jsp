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
        <link href="${pageContext.request.contextPath}/css/userProfile.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/css/userReport.css" rel="stylesheet" type="text/css">
        
        <!-- Simple-data table -->
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" type="text/css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" type="text/javascript" defer></script>

        <!-- Specific JS file -->
        <script src="${pageContext.request.contextPath}/js/adminDashboard.js" defer></script>

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
                            <p><a href="${pageContext.request.contextPath}/admin/user/edit?userId=${user.id}">${user.username}</a></p>
                            <div class="user-role">
                                <form action="${pageContext.request.contextPath}/admin/role/set" method="POST">
                                    <input name="userId" type="hidden" value="${user.id}"/>
                                    <select name="role" class="select-role">
                                        <c:forEach items="${roles}" var="role">
                                            <option value="${role.id}" <c:if test="${role.name == user.role}">selected="selected"</c:if>>${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                            <form action="${pageContext.request.contextPath}/admin/user/delete" method="POST">
                                <input value="${user.id}" name="userId" type="hidden"/>
                                <button>
                                    <img src="${pageContext.request.contextPath}/assets/images/Remove.svg"/>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <div class="revenue-products">
                <p>Products</p>
                <table id="revenue-products-table">
                    
                </table>
            </div>
            
            <a class="admin-link" href="${pageContext.request.contextPath}/ticketadmin">
                <button class="admin-button">User Support Ticket</button>
            </a>
        </div>
        
        <%@include file="components/Footer.jsp"%>
        
        <!-- Pass data to Javascript -->
        <script defer>
            const productsReportHeader = ["Name", "Price", "Sold", "Remained", "Total"];
            let productsReport = [];
            <c:forEach items="${productsReport}" var="product">
                productsReport.push(["${product.name}", "${product.price}", "${product.sold}", "${product.remained}", "${product.total}"]);
            </c:forEach>
        </script>
    </body>
</html>
