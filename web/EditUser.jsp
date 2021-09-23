<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Information</title>
    <!-- Common CSS file -->
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">

    <!-- Specific CSS file -->
    <link href="${pageContext.request.contextPath}/css/editUser.css" rel="stylesheet">
</head>

<body>
    <%@include file="components/Header.jsp"%>
    
    <form action="${pageContext.request.contextPath}/profile/edit" method="POST">
        <div class="bio">
            <p>Bio</p>
            
            <div class="bio-table">
                <table> 
                    <input type="hidden" value="${user.id}" name="userId">
                    
                    <tr>
                        <td>Avatar</td>
                        <td><input type="text" value="${user.avatar}" name="avatar">
                        </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" value="${user.phone}" name="phone">
                        </td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" value="${user.address}" name="address">
                        </td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td><input type="text" value="${user.gender}" name="gender">
                        </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td><input type="text" value="${user.status}" name="status">
                        </td>
                    </tr>
                    <tr>
                        <td><span>Short infor</span>
                            <span>(max 20 characters)</span>
                        </td>
                        <td><input type="text" value="${user.shortDescription}" name="shortDescription">
                        </td>
                    </tr>
                </table>
            </div>
                    
            <div class="text">
                <c:if test="${admin == null}">
                    <span>Need to change your password?</span>
                    <a href="#">Click there!</a>
                </c:if>
                <c:if test="${admin != null}">
                    <a href="#">Reset user password</a>
                </c:if>
            </div>

            <button class="save-button" type="action">Save</button>

        </div>

        <div class="profile">
            <p>Profile</p>

            <div class="tus">
               <input  type="text" value="${user.profile}" name="profile">
            </div>
            
            <button class="save-button" type="action">Save</button>
        </div>
    </form>
            
     <%@include file="components/Footer.jsp"%>
</body>

</html>