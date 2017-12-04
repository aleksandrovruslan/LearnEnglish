<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/menu.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>home page</title>
    </head>
    <body>
        <h1>Hello!</h1>
        <c:if test="${message != null}">
            <c:out value="${message}"/>
        </c:if>
    </body>
</html>