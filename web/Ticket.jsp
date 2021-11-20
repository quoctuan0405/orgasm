<%-- 
    Document   : Ticket
    Created on : Nov 4, 2021, 10:03:38 PM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Support Tickets</title>

        <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

        <!-- Specified CSS file -->
        <link href="${pageContext.request.contextPath}/css/ticket.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/comment.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/paging.css" rel="stylesheet">
    </head>
    <body>

        <%@include file="components/Header.jsp"%>

        <div class="banner">
            <div class="te">
                <span>Support Tickets</span>
            </div>
        </div>

        <div class="post-body">

            <div class="main">
                <div class="box">

                    <div class="search">
                        <form action="UserTicketSearch" method="post" class="searchMethod">
                            <div>
                                <input class="input" type="text" name="search" placeholder="Search"/>                        
                            </div>
                        </form>
                    </div>



                    <c:if test="${sessionScope.acc != null}">
                        <div class="management">
                            <div class="label"> Management</div>
                            <ul>
                                <li class="my-ports">
                                    <a href="${pageContext.request.contextPath}/myticket">Add a Ticket</a>
                                </li>
                            </ul>
                            <ul>
                                <li class="my-ports">
                                    <a href="${pageContext.request.contextPath}/ticket">My Ticket</a>
                                </li>
                            </ul>
                        </div>
                    </c:if>

                </div>

            </div>



            <div class="post-tag">  
                <c:set var="tag" value="0"></c:set>
                <c:forEach var="o" items="${ticketList}">                   

                    <c:set var="tag" value="1"></c:set>
                        <div class="post">
                            <div class="thumbnail"><img src="${o.avatar}"></div>
                        <div class="post-content-body">
                            <div class="post-title">
                                <ul>
                                    <li>${o.username}</li>
                                    <li><a class="cate" href="ticketdetail?id=${o.id}">${o.title}</a></li>
                                    <a href="removeticket?tid=${o.id}" class="text-dark" onclick="return confirm(
                                                    'Are you sure want to delete this ticket?')"> 
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </a>

                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="pagination row">
                    <c:forEach begin="1" end="${endPage}" var="o">
                        <a href="ticket?page=${o}" <c:if test="${currentPage == o}">class="current"</c:if>>${o}</a>
                    </c:forEach>
                </div>

            </div>



        </div>

        <%@include file="components/Footer.jsp"%>
    </body>
</html>

