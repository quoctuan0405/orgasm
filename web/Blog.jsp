<%-- 
    Document   : blog
    Created on : Sep 10, 2021, 8:05:16 PM
    Author     : Administrator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        
        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet" type="text/css" />
            
    </head>
    <body>
        <%@include file="components/Header.jsp"%>


        <div class="banner">
            <div class="te">
                <span>Blog</span>
            </div>
        </div>

        <div class="main">
            <div class="menu">
                <form action="searchblog" method="get">
                    <input name="search" type="text" placeholder="Search">
                </form>

                <span>Categories</span>
                <ul>
                    <c:forEach items="${listBlogCategory}" var ="o">
                        <li><a href="blogcategory?id=${o.id}">${o.name}</a></li>
                    </c:forEach>
                </ul>
                
                <c:if test="${sessionScope.acc != null}">
                    <span class="username">${user.username}'s Blog</span>
                    <ul>
                        <li><a href="addblog">New Blog</a></li>
                        <li><a href="myblog">My Blog</a></li>
                    </ul>
                </c:if>
            </div>

            <div class="boxBorder">
                <div class="row">
                    <c:forEach items = "${listBlog}" var ="o">
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
                
                <div class="pagination row">
                    <c:forEach begin="1" end="${endPage}" var="o">
                        <a href="blog?page=${o}" <c:if test="${currentPage == o}">class="current"</c:if>>${o}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
                        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
