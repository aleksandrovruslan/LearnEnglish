<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="/">Home page</a><br>
<c:if test="${user == null}">
    <a href="/registration">Sign up</a><br>
    <a href="/login">Sign in</a>
</c:if>
<c:if test="${user != null}">
    <c:if test="${user.role.roleId == 1}">
        <a href="/admin">Admin panel</a><br>
    </c:if>
    <a href="/addword">Add word</a><br>
    <a href="/wordInfo">Get word info</a><br>
    <a href="/mywords">All words</a><br>
    <a href="/learn">Learn words</a><br>
    <a href="/logout">Logout</a><br>
    <c:out value="${user.name}"/>
</c:if>
<c:if test="${message != null}">
    <script type="text/javascript">
        window.alert("<c:out value="${message}"/>");
    </script>
</c:if>