<c:set var="title" value="Search Results" />

<script type="text/javascript" class="init">
  $(document).ready( function () {
    $('#userTable').DataTable();
  } );
</script>
<html>
<body>

<div class="container-fluid">
  <h2>Search Results: </h2>
  <table id="userTable" class="display" cellspacing="0" width="100%">
    <thead>
    <th>Title</th>
    <th>Description</th>
    <th>To Do Date</th>
    <th>Due Date</th>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
      <tr>
        <td>${task.title} </td>
        <td>${task.description}</td>
        <td>${task.todoDate}</td>
        <td>${task.dueDate}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>