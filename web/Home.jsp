<%-- 
    Document   : Home
    Created on : Sep 12, 2021, 8:48:04 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>

        <!-- Common CSS file -->
        <link href="css/common.css" rel="stylesheet">

        <!-- Specific CSS file -->
        <link href="css/homepage.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        <div class="container">
            <div class="category">
                <div class="category-header">Categories</div>
                <ul>
                    <c:forEach items="${listC}" var="o">
                        <li><a href="${pageContext.request.contextPath}/productCategory?id=${o.id}">${o.name}</a></li>
                    </c:forEach>    
                </ul>
            </div>
            <div class="search">
                <form action="productSearch" method="POST">
                    <div>
                        <input class="input" type="text" name="search" placeholder="What do you need?"/>
                    </div>
                </form>
                <div class="ban">
                    <img src="https://i.postimg.cc/k5Ws9R7G/pexels-rauf-allahverdiyev-1367243.jpg"/>
                    <a href="${pageContext.request.contextPath}/shop">
                        <button class="btn">Shop now</button> 
                    </a>
                </div>
            </div>
        </div>
        <div>
            <div class="he1">Premium Product</div>
            <div class="picture">                
                <c:forEach items="${listPP}" var="o">
                    <a class="product" href="${pageContext.request.contextPath}/product?id=${o.id}">
                        <div>
                            <div class="pp">
                                <img class="image" src="${o.thumbnail}">
                                <div class="de">
                                    <h3>${o.name}</h3>                               
                                    <div>
                                        <p class="pr">$${o.price}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
