<%-- 
    Document   : BlogDetail
    Created on : Sep 20, 2021, 4:38:06 PM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blogdetail.css" rel="stylesheet">
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
                <form action="searchblog" method="post">
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
        </div>
        
        <div class="detail">
            <div class="image">
                <div><img src="${blogdetail.image}"></div> 
            </div>

          <div class="title">
                <h1>${blogdetail.title}</h1>
                <c:if test="${user.id != blog.authorId}">
                    <div class="edit-button-block">
                        <a class="edit-button" href="${pageContext.request.contextPath}/blog/edit?id=${blogdetail.id}">
                            <img src="${pageContext.request.contextPath}/assets/images/Edit.svg"/>
                        </a>
                    </div>
                </c:if>
          </div>

          <div class="content">
              <p>${blogdetail.content}</p>
          </div>
            
        </div>
        
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
