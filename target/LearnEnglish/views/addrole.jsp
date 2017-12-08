<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>add role</title>
    </head>
    <body>
    <table>
        <tr>
            <td>
                <%@include file="/views/menu.jsp"%>
            </td>
            <td>
                <form name="addrole" action="/addrole" method="post">
                    Enter role: <input name="role" type="text"><br>
                    <input value="add role" type="submit">
                </form>
            </td>
        </tr>
    </table>
    </body>
</html>
