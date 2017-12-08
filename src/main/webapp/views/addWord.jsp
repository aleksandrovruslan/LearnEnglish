<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<table>
    <tr>
        <td>
            <%@include file="/views/menu.jsp"%>
        </td>
        <td>
            <form name="addword" action="/addword" method="post">
                <strong>Enter english word: </strong><input name="english" type="text"><br>
                <div id="items">
                    <strong>Enter russian word: </strong><input name="russian" type="text"><br>
                    <input value="+" type="button" onclick="addField();" id="add"/>
                </div>
                <input value="add word" type="submit"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
