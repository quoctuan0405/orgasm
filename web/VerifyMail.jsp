<%-- 
    Document   : CheckMail
    Created on : Sep 30, 2021, 12:48:57 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify your email</title>
        
        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/verifymail.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="components/Header.jsp"%>
        
        <div class="container">
            <div class="card">
                <img src="${pageContext.request.contextPath}/assets/images/EmailSend.svg" alt="Send email"/>
                <c:if test="${invalidToken != null}">
                    <p class="title error">
                        Email verification failed
                    </p>
                </c:if>
                <c:if test="${invalidToken == null}">
                    <p class="title">
                        Verify your email to finish signing up
                    </p>
                </c:if>
                <p class="thanks-text">
                    <c:if test="${invalidToken != null}">
                        Your token has expired!
                    </c:if>
                    <c:if test="${invalidToken == null}">
                        Thank you for choosing Orgasm
                    </c:if>
                </p>
                <hr/>
                <p class="help-text">
                    <c:if test="${invalidToken != null}">
                        <a href="#">Click here</a> to get another verification email.
                    </c:if>
                    <c:if test="${invalidToken == null}">
                        Not receive verification email? <a href="#">Click here.</a>
                    </c:if>
                </p>
            </div>
        </div>
        
        <%@include file="components/Footer.jsp" %>
    </body>
</html>
