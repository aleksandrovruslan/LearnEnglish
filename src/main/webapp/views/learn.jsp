<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>learn words</title>
</head>
<body>
<script type="text/javascript">
    function addField() {
        var table=document.getElementById("words");
        var tr = document.createElement("tr");
        tr.innerHTML = "<td>Enter russian word: </td>" +
            "<td><input name=\"russian\" type=\"text\"></td>";
        var td = document.createElement("td");
        tr.appendChild(td);
        table.appendChild(tr);
        return false;
    }
</script>
<table>
    <tr>
        <td>
            <%@include file="/views/menu.jsp"%>
        </td>
        <td>
            <form action="/learn" method="post">
                <table id="words">
                    <c:if test="${rand == 0}">
                        <tr>
                            <td>enter the translation of the word:</td>
                            <td>
                                <c:out value="${randWord.english.englishWord}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Enter russian word:
                            </td>
                            <td>
                                <input name="russian" type="text"><br>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${rand == 1}">
                        <tr>
                            <td>enter the translation of the word: </td>
                            <td>
                                <c:forEach var="rus" items="${randWord.russian}">
                                    <c:out value="${rus.russianWord}"/>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td>Enter english word: </td>
                            <td>
                                <input name="english" type="text">
                            </td>
                        </tr>
                    </c:if>
                </table><br>
                <c:if test="${rand == 0}">
                    <input value="+" type="button" onclick="addField();" id="add"/>
                </c:if><br>
                <input value="check" type="submit">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
