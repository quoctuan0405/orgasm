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
        <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
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
            
            <div class="detail">
                <div class="image">
                    <div><img src="${blogdetail.image}" class="product_image"></div> 
                </div>

                <div class="title">
                    <h1>${blogdetail.title}</h1>
                    <c:if test="${user.id == blogdetail.authorid}">
                        <div class="button-group">
                            <div class="edit-button-block">
                                <a class="edit-button" href="${pageContext.request.contextPath}/editblog?id=${blogdetail.id}">
                                    <img src="${pageContext.request.contextPath}/assets/images/Edit.svg"/>
                                </a>
                            </div>
                            <form action="${pageContext.request.contextPath}/deleteblog" method="GET" class="delete-product-form">
                                <input name="id" value="${blogdetail.id}" type="hidden"/>
                                <button type="submit">
                                    <img src="${pageContext.request.contextPath}/assets/images/Remove.svg" >
                                </button>
                            </form>
                        </div>
                    </c:if>
                </div>

                <div class="content">
                    <p>${blogdetail.content}</p>
                </div>
                
                <div class="blog_comment">
                    <div class="comment_title">
                        <h1> Comment </h1>
                    </div>

                    <c:if test="${sessionScope.acc != null}">
                        <form action="add_comment" method="POST">
                            <div class="comment_box">
                                <input name="content" class="comment_input" type="text" placeholder="What do you think?"/>
                                <input name="blogId" value="${blogdetail.id}" type="hidden">
                                <input name="authorId" value="${user.id}" type="hidden">
                            </div>
                        </form>
                    </c:if>

                    <c:forEach items="${blogcomment}" var="o">
                        <div class="comment_content">                    
                            <div class="user_avatar">
                                <img src=${o.avatar} class="Avatar" alt="Avatar">
                            </div>
                            <div class="comment_text">
                                <p>${o.content}</p>
                            </div>                        
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
