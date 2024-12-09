package onlineplanner.controller;

import onlineplanner.entity.Task;
import onlineplanner.persistence.SessionFactoryProvider;
import onlineplanner.persistence.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

@WebServlet("/taskList")
public class ShowTasks extends HttpServlet {
    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        taskDAO = new TaskDAO(SessionFactoryProvider.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get today's date
            LocalDate today = LocalDate.now();

            // Retrieve tasks
            List<Task> todayTasks = taskDAO.getTasksForTodoDate(today);
            List<Task> dueTodayTasks = taskDAO.getTasksForDueDate(today);
            List<Task> allTasks = taskDAO.getAll();

            // Set tasks as request attributes
            request.setAttribute("todayTasks", todayTasks);
            request.setAttribute("dueTodayTasks", dueTodayTasks);
            request.setAttribute("allTasks", allTasks);

            // Forward the request to the JSP
            request.getRequestDispatcher("/taskList.jsp").forward(request, response);
        } catch (Exception e) {
            // Log error and forward to an error page
            getServletContext().log("Error retrieving tasks", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to display tasks.");
        }
    }
}