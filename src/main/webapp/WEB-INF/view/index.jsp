<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<body>

<p>Number of records: <c:out value="${requestScope.count}"/></p>

<c:forEach var="record" items="${requestScope.records}">
    <ul>
        <li>Login: <c:out value="${record.login}"/> </li>
        <li>First Name: <c:out value="${record.firstName}"/> </li>
        <li>Email: <c:out value="${record.email}"/> </li>
    </ul>
</c:forEach>

<a href="/form">Add record</a>
</body>
</html>
