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
                <p>For registration, enter information.</p>
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
            </td>
        </tr>
    </table>
    </body>
</html>