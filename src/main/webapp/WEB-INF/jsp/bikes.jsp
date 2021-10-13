<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Cool bikes</h3>
<ul>
    <c:forEach var="bike" items="${requestScope.bikes}">
        <li>${bike.model}</li>
    </c:forEach>
</ul>
</body>
</html>
