<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Post</title>
    <!-- Common CSS file -->
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

    <!-- Specific CSS file -->
    <link href="css/PostManagement.css" rel="stylesheet" type="text/css"/>
</head>

<body>
    <%@include file="components/Header.jsp"%>
    <div class="banner">
        <span>${user.username}'s Post</span>
    </div>
    <form <c:if test='${type == "edit" }'> action="editpost" </c:if> 
          <c:if test='${type == "add" }'> action="newpost" </c:if>  
          <c:if test='${type == "delete" }'> action="deletepost" </c:if>  method="POST">
    <table>
        <input type="hidden" name="id" value="${post.id}"/>
        <tr>
            <td>
                <c:if test='${type == "edit" }'> <input type="text" value="${post.title}" name="title" ></c:if>
                <c:if test='${type == "add" }'> <input type="text" placeholder="Post Title" name="title" ></c:if>
                <c:if test='${type == "delete" }'> <input type="text" readonly="" value="${post.title}" name="title" ></c:if>
            </td>
        </tr>
        <tr>
            <td>
                <c:if test='${type == "edit" }'>
                    <select name="category" >
                        <c:forEach items="${listPostCategory}" var="o">
                            <option ${post.category==o.id ? 'selected = "selected"' : ''} value="${o.id}" >${o.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${type == "add" }'>
                    <select name="category" >
                        <c:forEach items="${listPostCategory}" var="o">
                            <option value="${o.id}" >${o.name}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${type == "delete" }' >
                    <div class="category" name="category" readonly="">
                        <c:forEach items="${listPostCategory}" var="o">
                            <c:if test="${post.category == o.id}" > ${o.name} </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <c:if test='${type == "edit" }'><textarea type="text"  name="content">${post.content}</textarea></c:if>    
                <c:if test='${type == "add" }'><textarea type="text"  name="content" placeholder="Post Content"></textarea></c:if>    
                <c:if test='${type == "delete" }'><textarea type="text"  name="content" readonly="">${post.content}</textarea></c:if>    
            </td>
        </tr>
    </table>
        <p class="message" style="font-size: 25px; color:red; ">${message}</p>
        <div class="button-manage">
        <c:if test='${type == "edit" }'>
            <button>Save</button>
            <a href='mypost?id=${user.id}'><button type="button" >Cancel</button></a>
        </c:if>
        <c:if test='${type == "add" }'>
            <button>Add Post</button>
            <a href='mypost?id=${user.id}'><button type="button" >Cancel</button></a>
        </c:if>
        <c:if test='${type == "delete" }'>
            <button class="delete-button">Delete Post</button>
            <a href='mypost?id=${user.id}'><button type="button" >Cancel</button></a>
        </c:if>
        </div>
    </form>

    <%@include file="components/Footer.jsp"%>
</body>

</html>