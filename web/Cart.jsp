<%-- 
    Document   : Cart
    Created on : Sep 21, 2021, 8:43:21 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/shoppingcart.css" rel="stylesheet">

    </head>
    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>Shopping Cart</span>
            </div>
        </div> 

        <div class="cart">

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
                <c:set var="o" value="${sessionScope.cart}"/>
                <tbody>
                    <c:set var="t" value="0"/>
                    <c:forEach items="${o.products}" var="productInCart">
                        <tr>
                            <td>
                                <img class="image" src="${productInCart.product.thumbnail}">
                                <p class="product-name">${productInCart.product.name}</p>
                            </td>
                            <td>
                                <p>$<fmt:formatNumber pattern="##.#" value="${productInCart.product.price}"/></p>
                            </td>
                            <td>
                                <form>
                                    <button><a href="cart?num=-1&id=${productInCart.product.id}">-</a></button>
                                    <input class="quantity-box" type="text" name="quantity" value="${productInCart.quantity}" />
                                    <button><a href="cart?num=1&id=${productInCart.product.id}">+</a></button>
                                </form>
                            </td>
                            <td>
                                    <p>$<fmt:formatNumber pattern="##.#" value="${productInCart.quantity * productInCart.product.price}"/></p>
                            </td>
                            <td>
                                <form action="cart" method="post">
                                    <input type="hidden" name="id" value="${productInCart.product.id}"/>
                                    <button type="submit">
                                        <img src="assets/images/Remove.svg">
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <c:set var="t" value="${ t + productInCart.quantity * productInCart.product.price}"/>
                    </c:forEach>
                </tbody>
            </table>

            <div class="buttons">
                <div>
                    <a href="${pageContext.request.contextPath}/home">
                        <button>Continues Shopping</button>
                    </a>
                </div>        
                <div>                    
                    <a href="${pageContext.request.contextPath}/myorder">
                        <button class="update-btn">My Order</button>
                    </a>
                </div>                        
            </div>
            <div class="cart-total">
                <jsp:useBean id="db" class="model.dao.Cart"/>
                <div>
                    <p>Cart Total</p>
                    <p><fmt:formatNumber pattern="##.#" value="${t}"/>$</p>
                </div>
                <div>
                    <form action="checkout" method="post">
                        <button type="submit">Check out</button>
                    </form>
                </div>
            </div>
        </div>      

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
