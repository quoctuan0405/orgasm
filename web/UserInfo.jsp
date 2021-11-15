<%-- 
    Document   : UserReport
    Created on : Sep 12, 2021, 5:30:38 AM
    Author     : Admin
--%>

<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">

        <!-- Specific CSS file -->
        
        <link href="${pageContext.request.contextPath}/css/cssUI2.css" rel="stylesheet" type="text/css">

    </head>

    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <p class="title">${user.username}'s Info</p>

        </div>

        <div class="main">
            <div class="pic">
                <div class="left">
                    <div class="avatar" style="background-image: url('${user.avatar}')"></div>

                    <h4>${user.username}</h4>
                    <p>${user.shortDescription}</p>
                    <p>${user.address}</p>
                </div>

                <div class="right">
                    <div class="title-block">
                        <p class="title">Bio</p>

                    </div>

                    <div class="detail">
                        <div class="row">
                            <p class="property">Username</p>
                            <p class="value">${user.username}</p>
                        </div>

                        <div class="row">
                            <p class="property">Info</p>
                            <p class="value">${user.shortDescription}</p>
                        </div>

                        <div class="row">
                            <p class="property">Email</p>
                            <p class="value">${user.email}</p>
                        </div>

                        <div class="row">
                            <p class="property">Phone</p>
                            <p class="value">${user.phone}</p>
                        </div>

                        <div class="row">
                            <p class="property">Gender</p>
                            <p class="value">${user.gender}</p>
                        </div>

                        <div class="row">
                            <p class="property">Status</p>
                            <p class="value">${user.status}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="profile">
                <div class="title-block">
                    <p class="title">Profile</p>

                </div>

                <p class="tus">
                    ${user.profile}
                </p>
            </div>

            <div class="user_blog">
                <div class="title-block">
                    <p class="title">User's Blog</p>                    
                </div>

                <div class="boxBorder">
                    <div class="row">
                        <c:forEach items = "${blog}" var ="o">
                            <a class="box1" href="${pageContext.request.contextPath}/blogdetail?id=${o.id}">
                                <div>
                                    <div class="image1" style="background-image: url('${o.image}')">
                                    </div>
                                    <div class="text"> 
                                        <h4>${o.title}</h4> 
                                        <p> ${o.contentPre}</p>
                                    </div>
                                </div>	
                            </a>
                        </c:forEach>


                    </div>

                </div>


            </div> 
                
            <div class="user_blog">
                <div class="title-block">
                    <p class="title">User's Product</p>                    
                </div>

                <div class="boxBorder">
                    <div class="row">
                        <c:forEach items = "${product}" var ="o">
                            <a class="box1" href="${pageContext.request.contextPath}/product?id=${o.id}">
                                <div>
                                    <div class="image1" style="background-image: url('${o.thumbnail}')">
                                    </div>
                                    <div class="text"> 
                                        <h4>${o.name}</h4> 
                                        <p> ${o.description}</p>
                                    </div>
                                </div>	
                            </a>
                        </c:forEach>


                    </div>

                </div>


            </div>   
                
        </div>

        <%@include file="components/Footer.jsp"%>
    </body>

</html>