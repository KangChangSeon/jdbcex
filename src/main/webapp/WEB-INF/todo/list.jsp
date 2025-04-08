<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Todo List Page</h1>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <a href="/todo/read?tno=${dto.tno}">${dto.tno}</a>
                ${dto.title}
                ${dto.duedate}
            <c:choose>
                <c:when test="${dto.finished}">DONE</c:when>
                <c:otherwise>NOT YET</c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>

</body>
</html>
