<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo 1개 읽기 페이지</title>
</head>
<body>
<input type="text" name="tno" value="${dto.tno}"><br>
<input type="text" name="title" value="${dto.title}"><br>
<input type="date" name="duedate" value="${dto.duedate}"><br>
<input type="checkbox" name="finished"
       <c:if test="${dto.finished}">checked</c:if>><br>
<a href="/todo/modify">Modify/Remove</a>
<a href="/todo/list">list</a>

</body>
</html>
