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
            <td>
                <table border="1">
                    <tr>
                        <td>
                            English word
                        </td>
                        <td>
                            Word translation
                        </td>
                        <td>
                            Number answers
                        </td>
                        <td>
                            Correct answers
                        </td>
                        <td>
                            Action
                        </td>
                    </tr>
                    <c:forEach var="word" items="${words}">
                        <tr>
                            <td>
                                <c:out value="${word.english.englishWord}"/>
                            </td>
                            <td>
                                <c:forEach var="rus" items="${word.russian}">
                                    <c:out value="${rus.russianWord}"/>,
                                </c:forEach>
                            </td>
                            <td>
                                <c:out value="${word.numberAnswers}"/>
                            </td>
                            <td>
                                <c:out value="${word.correctAnswers}"/>
                            </td>
                            <td>
                                <a href="/mywords?action=deleteWord&variableWordEnglishId=<c:out value="${word.english.englishId}"/>">delete word</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html>
