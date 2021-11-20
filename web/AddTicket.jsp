<%-- 
    Document   : AddTicket
    Created on : Nov 4, 2021, 1:19:05 PM
    Author     : LAPTOP D&N
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Common CSS file -->
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

    <!-- Specific CSS file -->
    <link href="${pageContext.request.contextPath}/css/addTicket2.css" rel="stylesheet">
    
</head>

<body>
    <%@include file="components/Header.jsp"%>
    <div class="banner">
        <span>Support Ticket</span>
    </div>
    <form action="myticket" method="post" onsubmit="return checkforblank()">
        <table>
            <tr>
                <td><input type="text" value="Please tell us your problem" id="title" name="title"></td>
            </tr>
            <tr>
                <td>

                </td>
            </tr>
            <tr>
                <td><input type="text" value="Content" id="content" name="content"></td>
            </tr>
        </table>
        <input name="authorId" value="${user.id}" type="hidden">
        

        <button type="submit" class="btn">
            Add to Ticket <i class = "fas fa-shopping-cart"></i>
        </button>
        
        <script>
            function checkforblank(){
                if(document.getElementById('title').value == ""){
                    alert('Please input title');
                    return false;
                }
                if(document.getElementById('content').value == ""){
                    alert('Please input content');
                    return false;
                }
            }
        </script>
        
    </form>
        
        



    <%@include file="components/Footer.jsp"%>
</body>

</html>
