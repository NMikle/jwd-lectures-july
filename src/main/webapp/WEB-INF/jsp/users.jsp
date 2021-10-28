<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Users:</h1>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName} ${user.lastName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
