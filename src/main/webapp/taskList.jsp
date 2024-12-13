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
<!-- Assuming 'todayTasks', 'dueTodayTasks', and 'allTasks' are passed as request attributes -->
<div class="task-lists-container">
  <section class="task-list">
    <h2>Today's Tasks</h2>
    <ul>
      <c:forEach var="task" items="${todayTasks}">
        <li>
          <strong>${task.title}</strong> - ${task.description}
        </li>
      </c:forEach>
    </ul>
  </section>

  <section class="task-list">
    <h2>Due Today</h2>
    <ul>
      <c:forEach var="task" items="${dueTodayTasks}">
        <li>
          <strong>${task.title}</strong> - ${task.description}
        </li>
      </c:forEach>
    </ul>
  </section>

  <section class="task-list">
    <h2>All Tasks</h2>
    <ul>
      <c:forEach var="task" items="${allTasks}">
        <li>
          <strong>${task.title}</strong> - ${task.description}
        </li>
      </c:forEach>
    </ul>
  </section>
</div>