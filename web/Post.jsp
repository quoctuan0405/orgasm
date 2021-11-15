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
        <title>Discussion</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

        <!-- Specified CSS file -->
        <link href="${pageContext.request.contextPath}/css/post.css" rel="stylesheet">
    </head>
    <body>

        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>Posts</span>
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
                            <c:forEach var="o" items="${categoryList}">
                                <li class="Head"><a class="cate" href="postCate?id=${o.id}">${o.name}</a></li>
                            </c:forEach>    
                        </ul>
                    </div>

                    <c:if test="${sessionScope.acc != null}">
                        <div class="category">
                            <div class="category-header"> Management</div>
                            <ul>
                                <li class="Head">
                                    <a class="cate" href="${pageContext.request.contextPath}/mypost">My Posts</a>
                                </li>
                            </ul>
                        </div>
                    </c:if>

                </div>

            </div>

            <div class="post-tag">  
                <c:set var="tag" value="0"></c:set>
                <c:forEach var="o" items="${postList}">                   
                    <c:if test="${tag==0}">
                        <div class="tag-button">
                            <ul>
                                <c:if test="${o.cateID != null}">
                                    <li>#${o.cateName}</li>
                                </c:if>
                                    
                                <c:if test="${o.cateID == null}"><li>#all</li></c:if>
                            </ul>
                            <a href="newpost">
                                <button>Add to post</button>
                            </a>
                        </div>
                    </c:if>
                    <c:set var="tag" value="1"></c:set>
                    <a href="${pageContext.request.contextPath}/postdetail?id=${o.id}">
                        <div class="post">
                            <div class="thumbnail"><img src="${o.avatar}"></div>
                            <div class="post-content-body">
                                <div class="post-title">
                                    <ul>
                                        <li>${o.username}</li>
                                        <li>${o.title}</li>
                                    </ul>
                                </div>
                                <div class="hastag">
                                    #${o.cateName}
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>

                <div class="pagination row">
                    <c:forEach begin="1" end="${endPage}" var="o">
                        <a href="port?page=${o}" <c:if test="${currentPage == o}">class="current"</c:if>>${o}</a>
                    </c:forEach>
                </div>

            </div>

        </div>

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
