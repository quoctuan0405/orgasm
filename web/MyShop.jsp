<%-- 
    Document   : MyShop
    Created on : Oct 2, 2021, 5:18:07 PM
    Author     : dangd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyShop</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        
        <!-- Specified CSS file -->
        <link href="${pageContext.request.contextPath}/css/myShop.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>My Shop</span>
            </div>
        </div> 

        <div class="table-product">

            <table>
                <thead>
                    <tr>
                        <th>Products</th>
                        <th>Prices</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${myProductList}" var="o">
                        <tr>
                            <td>
                                <img class="image" src="${o.thumbnail}">
                                <p class="product-name">${o.name}</p>
                            </td>
                            <td>
                                <p>$${o.price}</p>
                            </td>
                            <td>
                                <p>${o.quantity}</p>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/product/edit?id=${o.id}"><img src="${pageContext.request.contextPath}/assets/images/Edit.svg" ></a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/product/delete" method="POST" class="delete-product-form">
                                    <input name="id" value="${o.id}" type="hidden" />
                                    <button type="submit">
                                        <img src="${pageContext.request.contextPath}/assets/images/Remove.svg" >
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="buttons">
                <a href="${pageContext.request.contextPath}/product/add">
                    <button>Add new product</button>
                </a>                            
            </div>
        </div>      

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
