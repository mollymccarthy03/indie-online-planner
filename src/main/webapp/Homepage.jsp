<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<%-- header jsp --%>
<c:import url="head.jsp" />

<body>

<c:import url="header.jsp" />


<main>
    <section class="calendar-section">
        <h2>Today is {Today's Date}</h2>
        <!-- Include the Calendar JSP -->
        <jsp:include page="calendar.jsp" />
    </section>

    <section class="task-list-section">
        <div id="task-list">
            <!-- Current Day Task list JSP -->
            <jsp:include page="taskList.jsp" />
        </div>
    </section>
    <section class="add-task-section">
        <!-- Link to the addTask.jsp page where the user can create a new task -->
        <a href="addTask.jsp">
            <button type="button">Add Task</button>
        </a>
    </section>
</main>
</body>
</html>
