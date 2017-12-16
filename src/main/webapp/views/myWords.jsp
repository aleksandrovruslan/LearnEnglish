<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>my words</title>
</head>
<body>
<table>
    <tr>
        <td>
            <%@include file="/views/menu.jsp"%>
        </td>
    </tr>
    <tr>
        <td>
            <table border="1">
                <tr>
                    <td>
                        English word
                    </td>
                    <td>
                        Word translation
                    </td>
                </tr>
                <c:forEach var="word" items="${words}">
                    <tr>
                        <td>
                            <c:out value="${word.english.englishWord}"/>
                        </td>
                        <td>
                            <c:forEach var="rus" items="${word.russian}">
                                <c:out value="${rus.russianWord}"/>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
