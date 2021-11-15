<%-- 
    Document   : ProductDetail
    Created on : Sep 19, 2021, 4:56:41 PM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${detail.name}</title>

        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/productDetail.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        <div class="product">
            <div class="card">
                <div class="product-img">
                    <div class="img-display">
                        <div class="img-showcase">
                            <div><img src="${detail.thumbnail}"></div> 
                        </div>
                    </div>
                </div>

                <div class="product-content">
                    <div class="product-name">
                        <p>${detail.name}</p>
                    </div>

                    <div class="product-price">
                        <p class="">$${detail.price}</p>
                    </div>

                    <div class="product-description">
                        <p>${detail.description}</p>
                    </div>

                    <div class="purchase-info">
                        <form id="addToCartForm" action="${pageContext.request.contextPath}/addtocart" method="POST">
                            <input type="hidden" name="productId" value="${product.id}" />
                            <input type="number" name="productQuantity" id="addToCartProductQuantity" min="0" value="1" class="purchase-box">
                            <button type="button" onclick="buy('${product.id}', '${product.quantity}')" class="btn">
                                Add to Cart
                            </button>
                        </form>
                    </div>                    
                </div>
            </div>                        
        </div>
        <%@include file="components/Footer.jsp"%>
        <script type="text/javascript">
            function buy(id, quantity) {
                const addToCartForm = document.querySelector("#addToCartForm");
                const addToCartProductQuantity = document.querySelector("#addToCartProductQuantity").value;

                if (addToCartProductQuantity > quantity) {
                    alert("Khong du hang");
                    return;

                } else {
                    addToCartForm.submit();
                }
            }
        </script> 
    </body>
</html>
