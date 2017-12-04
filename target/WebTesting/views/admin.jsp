<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin panel</title>
</head>
<body>
<%@include file="/views/menu.jsp"%>
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Password</td>
        <td>Email</td>
        <td>Role</td>
    </tr>
<c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.userId}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.password}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.role.name}"/></td>
        <td><a href='admin?action=delete&userId=<c:out value="${user.userId}"/>'>delete user</a></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
