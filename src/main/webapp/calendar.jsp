<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="calendar-container">
        <!-- Calendar HTML structure -->

    <table class="calendar">
        <thead>
        <tr>
            <th>Monday</th>
            <th>Tuesday</th>
            <th>Wednesday</th>
            <th>Thursday</th>
            <th>Friday</th>
            <th>Saturday</th>
            <th>Sunday</th>
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
</div>
