<%--
  Created by IntelliJ IDEA.
  User: rlckd
  Date: 2025-04-07
  Time: 오후 2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>todo register</title>
</head>
<body>
<form action="/todo/register" method="post">
    <input type="text" name="title" placeholder="INSERT TITLE"><br>
    <input type="date" name="date"><br>
    <button type="reset">RESET</button>
    <button type="submit">REGISTER</button>
</form>
</body>
</html>
