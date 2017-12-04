<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>add role</title>
</head>
<body>
    <%@include file="/views/menu.jsp"%>
    <form name="addrole" action="/addrole" method="post">
        Enter role: <input name="role" type="text"><br>
        <input value="add role" type="submit">
    </form>
    <c:if test="${message != null}">
        <c:out value="${message}"/>
    </c:if>
</body>
</html>
