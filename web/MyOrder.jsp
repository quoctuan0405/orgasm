<%-- 
    Document   : MyOrder
    Created on : Oct 16, 2021, 9:55:15 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/order.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>My Orders</span>
            </div>
        </div>
        <c:set var="z" value="1"/>
        <c:forEach items="${OrderList}" var="o">
            <div class="order">            
                <div class="">
                    <span>Order#${z}</span>
                    <c:set var="z" value="${z+1}"/>
                    <span>${o.updatedAt}</span>
                </div>
                <table>                
                    <thead>
                        <tr>
                            <th>Products</th>
                            <th>Prices</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="t" value=""/>
                        <c:forEach items="${ProductList}" var="a">
                            <c:if test="${a.creatorId == o.id}">
                                <tr>
                                    <td>
                                        <img class="image" src="${a.thumbnail}">
                                        <p class="product-name">${a.name}</p>
                                    </td>
                                    <td>
                                        <p>$${a.price}</p>
                                    </td>
                                    <td>
                                        <c:forEach items="${Quantity}" var="q">
                                            <c:if test="${q.orderId == o.id && q.productId == a.id}">
                                                <p>${q.quantity}</p>
                                                <c:set var="t" value="${q.quantity}"/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <p>$${a.price*t}</p>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/review?id=${a.id}&oid=${o.id}">
                                            <button>Give a review</button>
                                        </a>
                                    </td>
                                </tr> 
                            </c:if>      
                        </c:forEach>
                    </tbody>
                </table>
            </div>      
        </c:forEach>

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
