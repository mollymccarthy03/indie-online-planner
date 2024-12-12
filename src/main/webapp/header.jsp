<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp">Online Planner</a>
        </div>
        <ul class="nav-links">
            <c:choose>
                <c:when test="{sessionScope.userId == null}">
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
</div>