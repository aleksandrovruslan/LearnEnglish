<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="/">Главная страница</a><br>
<a href="/addword">добавить слово</a><br>
<a href="/addrole">добавить роль</a><br>
<a href="/admin">админка</a><br>
<c:if test="${user == null}">
    <a href="/registration">sign up</a><br>
    <a href="/login">sign in</a>
</c:if>
<c:if test="${user != null}">
    <c:out value="${user.name}"/>
    <a href="/logout">logout</a>
</c:if>