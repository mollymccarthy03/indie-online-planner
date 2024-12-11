<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Task</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Your CSS stylesheet -->
</head>
<body>
<header>
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp">Online Planner</a>
        </div>
    </nav>
</header>

<main>
    <section class="task-form-section">
        <h2>Create New Task</h2>
        <!-- Form for creating a new task -->
        <form action="addTaskSubmit" method="POST">
            <label for="title">Task Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

            <label for="dueDate">Due Date:</label>
            <input type="date" id="dueDate" name="dueDate" required><br><br>

            <label for="dueDate">When will you complete this assignment?</label>
            <input type="date" id="todoDate" name="todoDate" required><br><br>

            <input type="submit" value="Add Task">
        </form>
    </section>
</main>

<footer>
    <p>&copy; 2024 Online Planner App</p>
</footer>
</body>
</html>
