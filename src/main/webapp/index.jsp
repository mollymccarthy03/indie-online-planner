<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Planner</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp">Online Planner</a>
        </div>
        <ul class="nav-links">
            <li><a href="signin.jsp">Sign In</a></li>
        </ul>
    </nav>
</header>

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

<footer>
    <p>&copy; 2024 Online Planner App Made by Molly McCarthy</p>
</footer>
</body>
</html>
