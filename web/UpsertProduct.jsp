    <%-- 
    Document   : EditProduct
    Created on : Oct 2, 2021, 10:13:48 PM
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
        <link href="${pageContext.request.contextPath}/css/upsertProduct.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>My Shop</span>
            </div>
        </div> 

        <form action="" method="POST">
            <div class="father">
                <div class="table-product">
                    <table>
                        <thead>
                            <tr>
                                <th>Products</th>
                                <th class="head-price">Prices</th>
                                <th class="head-quantity">Quantity</th>
                            </tr>
                        </thead>
                    </table>
                    <hr />
                    <div class="container">
                        <div class="headboard">
                            <input name="id" value="${product.id}" type="hidden">
                            <ul>
                                <li class="name-box">
                                    <input name="name" value="${product.name}" placeholder="Name" type="text" required>
                                </li>
                                <li class="price-box">
                                    <input name="price" value="${product.price}" placeholder="Price" type="number" min="0" step="0.01" required>
                                </li>
                                <li class="quantity-box">
                                    <input name="quantity" value="${product.quantity}" placeholder="Quantity" type="number" min="0" required>
                                </li>
                                <li class="type-box">
                                    <select name="type" style="width: 216px; height: 50px">
                                        <c:forEach items="${categoryList}" var="o">                                    
                                            <option 
                                                value="${o.id}"
                                                <c:if test="${o.id == product.category}">selected="selected"</c:if>
                                            >
                                                ${o.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <ul>
                                <li class="picture-box">
                                    <input name="picture" value="${product.thumbnail}" placeholder="Image (e.g https://i.postimg.cc/6B3P4MH6/pexels-rajesh-tp-1633525.jpg)" type="text" required>
                                </li>
                            </ul>
                        </div>
                        <div>
                            <ul>
                                <li class="descriptions-box">
                                    <textarea name="description" placeholder="Description" type="text" required>${product.description}</textarea>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="buttons">
                <a>
                    <button>
                        <c:if test="${product == null}">
                            Add new product
                        </c:if>
                        <c:if test="${product != null}">
                            Update product
                        </c:if>
                    </button>
                </a>                            
            </div>
        </form>
                                    
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
