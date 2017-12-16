<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>learn words</title>
</head>
<body>
<script type="text/javascript">
    var items=1;
    function addField() {
        var div=document.getElementById("items");
        var button=document.getElementById("add");
        items++;
        var newitem="<tr><td>Enter russian word: </td>";
        newitem+="<td><input name=\"russian\" type=\"text\"></td></tr>";
        var newnode=document.createElement("div");
        newnode.innerHTML=newitem;
        div.insertBefore(newnode,button);
    }
</script>
<table>
    <tr>
        <td>
            <%@include file="/views/menu.jsp"%>
        </td>
        <td>
            <form action="/learn" method="post">
                <table>
                    <c:if test="${rand == 0}">
                        <tr>
                            <td>enter the translation of the word:</td>
                            <td>
                                <c:out value="${randWord.english.englishWord}"/>
                            </td>
                        </tr>
                        <div id="items">
                            <tr>
                                <td>
                                    <input value="+" type="button" onclick="addField();" id="add"/>
                                    Enter russian word:
                                </td>
                                <td>
                                    <input name="russian" type="text"><br>
                                </td>
                            </tr>
                        </div>
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
                    <tr>
                        <td></td>
                        <td>
                            <input value="check" type="submit">
                        </td>
                    </tr>
                </table>


                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--English word--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--Word translation--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<c:forEach var="word" items="${words}">--%>
                        <%--<tr>--%>
                            <%--<td>--%>
                                <%--<c:out value="${word.english.englishWord}"/>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:forEach var="rus" items="${word.russian}">--%>
                                    <%--<c:out value="${rus.russianWord}"/>--%>
                                <%--</c:forEach>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                <%--</table>--%>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
