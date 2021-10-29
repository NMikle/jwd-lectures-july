<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.jwd.web.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>World Hello</h1>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<a href="/controller?command=show_bikes">bikes page</a>
<br>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <a href="/controller?command=show_users">users page</a>
    <br>
</c:if>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <a href="/controller?command=logout">logout</a>
    </c:when>
    <c:otherwise>
        <a href="/controller?command=show_login">login</a>
    </c:otherwise>
</c:choose>
</body>
</html>
