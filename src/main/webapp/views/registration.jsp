<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/menu.jsp"%>
<p>Для регистрации введите имя и пароль.</p>
<form action='/registration' name='registration' method='post'>
    <table>
        <tr>
            <td>
                name:
            </td>
            <td>
                <input name="name" type="text" /><br>
            </td>
        </tr>
        <tr>
            <td>
                login:
            </td>
            <td>
                <input name="login" type="text" /><br>
            </td>
        </tr>
        <tr>
            <td>
                email:
            </td>
            <td>
                <input name="email" type="email" /><br>
            </td>
        </tr>
        <tr>
            <td>
                password:
            </td>
            <td>
                <input name="password" type="password" /><br>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input name="ok" type="submit" value="Sign up" />
            </td>
        </tr>
    </table>
</form>
<c:if test="${message != null}">
    <c:out value="${message}"/>
</c:if>