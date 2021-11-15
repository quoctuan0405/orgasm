<%-- 
    Document   : PostDetail
    Created on : Oct 31, 2021, 10:37:20 AM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post</title>
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/postdetail2.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/postcomment.css" rel="stylesheet">
    </head>

    <body>
        <%@include file="components/Header.jsp"%>
        <div class="banner">
            <div class="te">
                <span>Post</span>
            </div>
        </div>

        <div class="main">
            <div class="menu">
                <form action="searchblog" method="post">
                    <input name="search" type="text" placeholder="Search">
                </form>

                <div class="category-header">
                    <span>Categories</span>
                </div>


                <ul>
                    <c:forEach items="${listBlogCategory}" var ="o">
                        <li><a href="blogcategory?id=${o.id}">${o.name}</a></li>
                        </c:forEach>
                </ul>
                
                <div class="mypost-header">
                    
                    
                    <ul>
                        <p1>User' Post </p1>
                        <br>
                        <p2>My post</p2>
                    </ul>
                </div>
                
                
            </div> 

            <div class="detail-box">
                <div class="detail">            
                    <div class="title">
                        <h1>${postdetail.title}</h1>

                    </div>

                    <div class="content">
                        <p>${postdetail.content}</p>
                    </div>

                </div>

                <div class="blog_comment">
                    <div class="comment_title">
                        <h1> Comment </h1>
                    </div>

                    <c:if test="${sessionScope.acc != null}">
                        <form action="postcomment" method="POST">
                            <div class="comment_box">
                                <input name="content" class="comment_input" type="text" placeholder="What do you think?"/>
                                <input name="postId" value="${postdetail.id}" type="hidden">
                                <input name="authorId" value="${user.id}" type="hidden">
                            </div>
                        </form>
                    </c:if>


                    <c:forEach items="${PostCommentList}" var="o">
                        <div class="comment_content">                    
                            <div class="user_avatar">
                                <img src="${o.avatar}" class="Avatar" alt="Avatar">
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
