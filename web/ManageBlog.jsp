<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Blog</title>
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
    <form <c:if test='${type == "edit" }'> action="editblog" </c:if> 
          <c:if test='${type == "add" }'> action="addblog" </c:if>  
          <c:if test='${type == "delete" }'> action="deleteblog" </c:if>  method="POST">
    <table>
        <input type="hidden" name="id" value="${blog.id}"/>
        <tr>
            <td>
                <c:if test='${type == "edit" }'> <input type="text" value="${blog.title}" name="title" ></c:if>
                <c:if test='${type == "add" }'> <input type="text" placeholder="Blog Title" name="title" ></c:if>
                <c:if test='${type == "delete" }'> <input type="text" readonly="" value="${blog.title}" name="title" ></c:if>
            </td>
        </tr>
        <tr>
            <td>
                <c:if test='${type == "edit" }'> <input type="text" value="${blog.image}" name="image" ></c:if>
                <c:if test='${type == "add" }'> <input type="text" placeholder="Blog Image (e.g https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg)" name="image" /></c:if>
                <c:if test='${type == "delete" }'> <input type="text" readonly="" value="${blog.image}" name="image" ></c:if>
                
                <c:if test='${type == "edit" }'>
                    <select name="category" >
                        <c:forEach items="${listBlogCategory}" var="o">
                            <option ${blog.category==o.id ? 'selected = "selected"' : ''} value="${o.id}" >${o.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${type == "add" }'>
                    <select name="category" >
                        <c:forEach items="${listBlogCategory}" var="o">
                            <option value="${o.id}" >${o.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${type == "delete" }' >
                    <div class="category" name="category" readonly="">
                        <c:forEach items="${listBlogCategory}" var="o">
                            <c:if test="${blog.category == o.id}" > ${o.name} </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <c:if test='${type == "edit" }'><textarea type="text"  name="content">${blog.content}</textarea></c:if>    
                <c:if test='${type == "add" }'><textarea type="text"  name="content" placeholder="Blog Content"></textarea></c:if>    
                <c:if test='${type == "delete" }'><textarea type="text"  name="content" readonly="">${blog.content}</textarea></c:if>    
            </td>
        </tr>
    </table>
        <p class="message" style="font-size: 25px; color: red;">${message}</p>
        <div class="button-manage">
        <c:if test='${type == "edit" }'>
            <button>Save</button>
            <a href='blogdetail?id=${blog.id}'><button type="button" >Cancel</button></a>

        </c:if>
        <c:if test='${type == "add" }'>
            <button>Add Blog</button>
            <a href='myblog?id=${user.id}'><button type="button" >Cancel</button></a>
        </c:if>
        <c:if test='${type == "delete" }'>
            <button class="delete-button">Delete Blog</button>
            <a href='blogdetail?id=${blog.id}'><button type="button" >Cancel</button></a>
        </c:if>
        </div>
    </form>

    <%@include file="components/Footer.jsp"%>
</body>

</html>
