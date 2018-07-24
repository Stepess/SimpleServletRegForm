<%--
  Created by IntelliJ IDEA.
  User: Senpai
  Date: 24.07.2018
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration Form</title>
</head>
<body>

<c:forEach var="error" items="${requestScope.errors}">
    <p><span style="color: red; "><c:out value="${error}"/></span> </p>
</c:forEach>

    <form method="post" action="/form">
        <label><input type=text" name="login" value="${requestScope.record.login}"></label>Login<br>
        <label><input type="text" name="firstName" value="${requestScope.record.firstName}"></label>Name<br>
        <label><input type="text" name="email" value="${requestScope.record.email}"></label>Email<br>
        <input type="submit" value="Ok" name="Ok"><br>
    </form>

    <a href="/">Back to the main page</a>
</body>
</html>
