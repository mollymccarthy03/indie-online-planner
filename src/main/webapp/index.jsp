<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
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
<main>


</main>

<footer>
    <p>&copy; 2024 Online Planner App Made by Molly McCarthy</p>
</footer>
</body>
</html>