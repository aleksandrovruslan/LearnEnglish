<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>word info</title>
</head>
<body>
<table>
    <tr>
        <td>
            <%@include file="/views/menu.jsp"%>
        </td>
        <td>
            <c:if test="${user != null}">
                <c:if test="${words == null}">
                    <form action="/wordInfo" method="post">
                        <table>
                            <tr>
                                <td>
                                    Enter english word:
                                </td>
                                <td>
                                    <input name="english" type="text">
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input value="get info" type="submit">
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:if>
                <c:if test="${words != null}">
                    <table border="2">
                        <tr>
                            <td>
                                <c:out value="${words[0].english.englishWord}"/>
                            </td>
                            <c:forEach var="word" items="${words}">
                                <td>
                                    <c:out value="${word.russian.russianWord}"/>
                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                </c:if>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
