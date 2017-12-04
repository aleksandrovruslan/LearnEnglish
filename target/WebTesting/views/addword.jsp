<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: family
  Date: 25.11.17
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>add word</title>
</head>
<body>
<script type="text/javascript">
    var items=1;
    function addField() {
        div=document.getElementById("items");
        button=document.getElementById("add");
        items++;
        newitem="<strong>Enter russian word: </strong>";
        newitem+="<input name=\"russian\" type=\"text\"><br>";
        newnode=document.createElement("span");
        newnode.innerHTML=newitem;
        div.insertBefore(newnode,button);
    }
</script>
<%@include file="/views/menu.jsp"%>
    <form name="addword" action="/addword" method="post">
        <strong>Enter english word: </strong><input name="english" type="text"><br>
        <div id="items">
            <strong>Enter russian word: </strong><input name="russian" type="text"><br>
            <input value="+" type="button" onclick="addField();" id="add"/>
        </div>
        <input value="add word" type="submit"/>
    </form>
    <c:if test="${message != null}">
        <c:out value="${message}"/>
    </c:if>
</body>
</html>
