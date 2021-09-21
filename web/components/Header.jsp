<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <nav>
        <div class="header-logo">
            <a href="home">Orgasm</a>
        </div>
        <div class="header-links">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/home">Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/shop">Shop</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/blog">Blog</a>
                </li>
                <c:if test="${sessionScope.acc != null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/cart">Cart</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/profile">Profile</a>
                    </li>
                </c:if>
                <li>
                    <a href="${pageContext.request.contextPath}/about">About us</a>
                </li>
            </ul>

            <ul class="header-right">
                <c:if test="${sessionScope.acc != null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Join us</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>
<div class="header-placeholder"></div>