<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>admin panel</title>
    </head>
    <body>
    <table>
        <tr>
            <td>
                <%@include file="/views/menu.jsp"%><br>
                <%--TODO add the function of adding and editing roles, editing users and access filter--%>
                <a href="/admin">Add role</a><br>
            </td>
            <td>
                <table border="2">
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Login</td>
                        <td>Password</td>
                        <td>Email</td>
                        <td>Role</td>
                        <td>Action</td>
                    </tr>
                    <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.userId}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.role.name}"/></td>
                        <td><a href="/admin?action=deleteUser&variableUserId=<c:out value="${user.userId}"/>">delete user</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html>
