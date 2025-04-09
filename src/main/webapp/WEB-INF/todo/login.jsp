<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo Login</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>


<form action="/login" method="post">
    <input type="text" name="mid"/>
    <input type="text" name="mpwd"/>
    <input type="checkbox" name="auto">
    <button type="submit">로그인</button>
</form>
</body>
</html>
