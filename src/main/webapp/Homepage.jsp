<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Planner</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<header>
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp">Online Planner</a>
        </div>
        <ul class="nav-links">
            <c:choose>
                <c:when test="{sessionScope.userId == null }">
                    <li><a href="signin.jsp">Sign In</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="Homepage.jsp">Homepage</a></li>
                    <li><a href="addTask.jsp">Add Task</a></li>
                    <li><a href="signout.jsp">Sign Out</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>
<body>
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
