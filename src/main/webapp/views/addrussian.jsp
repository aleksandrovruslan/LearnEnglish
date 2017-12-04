<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>add russian word</title>
</head>
<body>
<%@include file="/views/menu.jsp"%>
    <form action="/addrussian" method="post">
        Add russian word:<br>
        <input name="word" type="text"/><br>
        <input value="send" type="submit"/>
    </form>
</body>
</html>
