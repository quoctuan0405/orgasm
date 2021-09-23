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
                        <form action="${pageContext.request.contextPath}/cart" method="POST">
                            <input type="number" min="0" value="1" class="purchase-box">
                            <button type="submit" class="btn">
                                Add to Cart <i class = "fas fa-shopping-cart"></i>
                            </button>
                        </form>
                    </div>                    
                </div>
            </div>                        
        </div>
        <%@include file="components/Footer.jsp"%>
    </body>
</html>
