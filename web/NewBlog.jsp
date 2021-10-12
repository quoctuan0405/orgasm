<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Common CSS file -->
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        
        <!-- Specific CSS file -->
        <link href="${pageContext.request.contextPath}/css/newBlog.css" rel="stylesheet">
</head>

<body>
    <%@include file="components/Header.jsp"%>
    <div class="banner">
                <span>Blog</span>
        </div>
    <form action="newblog" method="POST">
    <table>
        <tr>
            <td><input type="text" value="My descriptive title"" name="title"></td>
        </tr>
        <tr>
            <td>
                <input type="text" value="image" name="image">
                <select name="category" >
                    <c:forEach items="${listBlogCategory}" var="o">
                        <option value="${o.id}">${o.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="text" value="My very very long and boring content" name="content"></td>
        </tr>
    </table>

    <button>Add new blog</button>
    </form>

    <%@include file="components/Footer.jsp"%>
</body>

</html>