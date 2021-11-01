<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.web.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="l10n.page.main" var="loc" />
<fmt:message bundle="${loc}" key="label.title" var="pageTitle" />
<fmt:message bundle="${loc}" key="label.hello" var="helloMessage" />
<fmt:message bundle="${loc}" key="label.welcome" var="welcomeMessage" />
<fmt:message bundle="${loc}" key="label.link.bikes" var="bikesLink" />
<fmt:message bundle="${loc}" key="label.link.users" var="usersLink" />
<fmt:message bundle="${loc}" key="label.link.login" var="loginLink" />
<fmt:message bundle="${loc}" key="label.link.logout" var="logoutLink" />
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<h1>${helloMessage}</h1>
<c:if test="${not empty sessionScope.user}">
    <p>${welcomeMessage} ${sessionScope.user.firstName}</p>
</c:if>
<a href="${pageContext.request.contextPath}/controller?command=show_bikes">${bikesLink}</a>
<br>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <a href="${pageContext.request.contextPath}/controller?command=show_users">${usersLink}</a>
    <br>
</c:if>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=logout">${logoutLink}</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/controller?command=show_login">${loginLink}</a>
    </c:otherwise>
</c:choose>
</body>
</html>
