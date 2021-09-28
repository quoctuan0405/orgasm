<%-- 
    Document   : product
    Created on : Sep 11, 2021, 9:47:01 PM
    Author     : dangd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Our Products</title>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        
        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        <div class="container">
            <div class="category">
                <div class="category-header">Categories</div>
                <ul>
                    <c:forEach items="${listC}" var="o">
                        <li class="Head"><a class="cate" href="productCategory?id=${o.id}">${o.name}</a></li>
                    </c:forEach>    
                </ul>
            </div>
            <div class="search">
                <form action="productSearch" method="post" class="searchMethod">
                    <div>
                        <input class="input" type="text" name="search" placeholder="What do you need?"/>                        
                    </div>
                </form>
                <div class="picture">
                    <c:forEach items="${listP}" var="o">
                        <a class="product" href="${pageContext.request.contextPath}/product?id=${o.id}">
                            <div>
                                <img class="image" src="${o.thumbnail}">
                                <div class="card-body">
                                    <h3 class="nameProduct">${o.name}</h3>                                  
                                    <div class="price">
                                        <p class="">$${o.price}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
