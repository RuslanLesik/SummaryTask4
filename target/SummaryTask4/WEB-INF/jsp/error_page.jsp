<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>error</title>
    <c:set var="title" value="Error" scope="page" />
</head>
<body>
<div class="alert alert-danger" role="alert">
    <h2>The following error occurred :</h2>
    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
    <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

    <c:if test="${not empty code}">
        <h3>Error code: ${code}</h3>
    </c:if>

    <c:if test="${not empty message}">
        <h3>${message}</h3>
    </c:if>

    <c:if test="${not empty requestScope.errorList}">
        <c:forEach items="${requestScope.errorList}" var="error">
            <h3>${error}</h3>
        </c:forEach>
    </c:if>

    <c:if test="${not empty requestScope.errorMessage}">
        <h3>${requestScope.errorMessage}</h3>
    </c:if>
</div>
</body>
</html>
