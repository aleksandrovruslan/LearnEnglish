<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>home page</title>
    </head>
    <body>
    <table>
        <tr>
            <td>
                <%@include file="/views/menu.jsp"%>
            </td>
            <td>
                <form action="/login" method="post">
                    <table>
                        <tr>
                            <td>User login</td>
                            <td><input name="login" type="text" /></td>
                        </tr>
                        <tr>
                            <td>password</td>
                            <td><input name="password" type="password" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input name="ok" value="sign in" type="submit" /></td>
                        </tr>
                    </table>
                </form>
            </td>
        </tr>
    </table>
    <c:if test="${message != null}">
    <c:out value="${message}"/>
    </c:if>
    </body>
</html>