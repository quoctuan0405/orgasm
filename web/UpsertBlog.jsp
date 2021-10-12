<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add a Blog</title>
    <!-- Common CSS file -->
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

    <!-- Specific CSS file -->
    <link href="${pageContext.request.contextPath}/css/upsertBlog.css" rel="stylesheet">
</head>

<body>
    <%@include file="components/Header.jsp"%>
    <div class="banner">
        <span>${user.username}'s Blog</span>
    </div>
    
    <form action="addblog" method="POST">
    <table>
        <tr>
            <td><input type="text" placeholder="My descriptive title" name="title"></td>
        </tr>
        <tr>
            <td>
                <input type="text" placeholder="https://i.postimg.cc/Y2RXhFYm/pexels-rajesh-tp-1633559.jpg" name="image">
                <select name="category" >
                    <c:forEach items="${listBlogCategory}" var="o">
                        <option value="${o.id}">${o.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><textarea type="text" placeholder="My very very long and boring content" name="content"></textarea></td>
        </tr>
    </table>

    <button>Add new blog</button>
    </form>

    <%@include file="components/Footer.jsp"%>
</body>

</html>