<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
