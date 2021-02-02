<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of tasks</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Sername</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="entry" items="${listOfPerson}">
    <tr>
            <td>${entry.getName()}</td>
            <td>${entry.getSername()}</td>
            <td>${entry.getEmail()}</td>
        </tr>
</c:forEach>
    </tbody>
</table>

</body>
</html>