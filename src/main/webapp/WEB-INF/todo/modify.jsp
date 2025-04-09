<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/modify" method="post">
    <input type="text" name="tno" value="${dto.tno}"><br>
    <input type="text" name="title" value="${dto.title}"><br>
    <input type="date" name="duedate" value="${dto.duedate}"><br>
    <input type="checkbox" name="finished" value="true"
           <c:if test="${dto.finished}">checked</c:if>><br>
    <button type="submit">Modify</button>
    <br>
</form>
<form action="/todo/remove" method="post">
    <input type="hidden" name="tno" value="${dto.tno}">
    <button type="submit">Remove</button>
</form>
</body>
</html>
