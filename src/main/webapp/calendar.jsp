<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weekly Calendar</title>
</head>
<body>

<h1>Weekly Calendar</h1>

<table border="1">
    <thead>
    <tr>
        <th>Mon</th>
        <th>Tue</th>
        <th>Wed</th>
        <th>Thu</th>
        <th>Fri</th>
        <th>Sat</th>
        <th>Sun</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <c:forEach var="task" items="${mon}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${tue}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${wed}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${thu}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${fri}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${sat}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="task" items="${sun}">
                <p>${task.title}</p>
            </c:forEach>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
