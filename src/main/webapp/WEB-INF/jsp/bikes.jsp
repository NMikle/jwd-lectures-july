<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Cool bikes</h3>
<table>
    <tr>
        <th>Model</th>
        <th>Price</th>
<%--        <th>Owner</th>--%>
    </tr>
    <c:forEach var="bike" items="${requestScope.bikes}">
        <tr>
            <td>${bike.model}</td>
            <td>${bike.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
