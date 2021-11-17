<%-- 
    Document   : Ports
    Created on : Oct 18, 2021, 7:53:09 AM
    Author     : dangd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

        <!-- Specified CSS file -->
        <link href="${pageContext.request.contextPath}/css/mypost.css" rel="stylesheet">
    </head>
    <body>

        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>Post</span>
            </div>
        </div>

        <div class="post-body">

            <div class="main">
                <div class="box">

                    <div class="search">
                        <form action="searchPost" method="post" class="searchMethod">
                            <div>
                                <input class="input" type="text" name="search" placeholder="Search"/>                        
                            </div>
                        </form>
                    </div>

                    <div class="category">
                        <div class="category-header">Categories</div>
                        <ul>
                            <c:forEach var="o" items="${listPostCategory}">
                                <li class="Head"><a class="cate" href="postCate?id=${o.id}">${o.name}</a></li>
                                </c:forEach>    
                        </ul>
                    </div>

                    <div class="category">
                        <div class="category-header"> Management</div>
                        <ul>
                            <li class="Head">
                                <a class="cate" href="mypost">My Post</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="post-tag">  
                <c:set var="tag" value="0"></c:set>
                <c:forEach var="o" items="${listPost}">                   
                    <c:if test="${tag==0}">
                        <div class="tag-button">
                            <p class="title">My Post</p>
                            <a href="newpost">
                                <button>Add to post</button>
                            </a>
                        </div>
                    </c:if>
                    <c:set var="tag" value="1"></c:set>
                        <a href="${pageContext.request.contextPath}/postdetail?id=${o.id}">
                            <div class="post">
                                <div class="thumbnail"><img src="${user.avatar}"></div>
                                <div class="post-content-body">
                                    <div class="post-title">
                                        <ul>
                                            <li>${user.username}</li>
                                            <li>${o.title}</li>
                                        </ul>
                                    </div>
                                    <div class="hastag">
                                        <div class="edit-button-block">
                                            <a class="edit-button" href="${pageContext.request.contextPath}/editpost?id=${o.id}">
                                                <img src="${pageContext.request.contextPath}/assets/images/Edit.svg"/>
                                            </a>
                                        </div>
                                        <form action="${pageContext.request.contextPath}/deletepost" method="GET" class="delete-product-form">
                                            <input name="id" value="${o.id}" type="hidden"/>
                                            <button type="submit">
                                                <img src="${pageContext.request.contextPath}/assets/images/Remove.svg" >
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                <div class="pagination row">
                    <c:forEach begin="1" end="${endPage}" var="o">
                        <a href="post?page=${o}" <c:if test="${currentPage == o}">class="current"</c:if>>${o}</a>
                    </c:forEach>
                </div>

            </div>

        </div>

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
