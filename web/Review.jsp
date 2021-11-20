<%-- 
    Document   : Review
    Created on : Oct 31, 2021, 8:37:17 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review page</title>
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/review.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        <div class="product">
            <div class="card">
                <div class="product-img">
                    <div class="img-display">
                        <div class="img-showcase">
                            <div><img src="${Product.thumbnail}"></div> 
                        </div>
                    </div>
                </div>

                <div class="product-content">
                    <div class="product-name">
                        <p>${Product.name}</p>
                    </div>

                    <div class="product-price">
                        <p class="">$${Product.price}</p>
                    </div>

                    <div class="product-description">
                        <p>${Product.description}</p>
                    </div>                
                </div>
            </div>

            <div class="rating-star">
                <p>Rating</p>
                <div>
                    <span>${avg}/5</span>
                    <img src="assets/images/Star.svg">
                </div>
            </div>

            <div class="review-box">
                <p>User Reviews</p>
                <form action="review" method="post">
                    <div>
                        <img src="${user.avatar}">
                        <div class="input-review">
                            <input name="des" placeholder="What do you think? Please give a review" required>
                            <input type="hidden" name="oid" value="${oid}">
                            <input type="hidden" name="id" value="${id}">
                            <span>Rating:</span>
                            <select name="rate">
                                <option selected="selected" >5</option>
                                <option>4</option>
                                <option>3</option>
                                <option>2</option>
                                <option>1</option>
                            </select>
                            <button class="submit-button" type="submit">Rate</button>
                        </div>
                    </div>
                </form>
            </div>

            <c:forEach items="${feedbackList}" var="o">
                <div class="reviews">
                    <img src="${o.avatar}">
                    <div class="review-content">
                        <p>${o.description}</p>
                        <div>
                            <span>${o.rating}</span>
                            <img src="assets/images/Star.svg">
                            <span class="review-date">${o.createdAt}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>    

        <%@include file="components/Footer.jsp"%>
    </body>
</html>
