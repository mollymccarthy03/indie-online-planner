package onlineplanner.controller;

import onlineplanner.entity.Task;
import onlineplanner.persistence.GenericDAO;
import onlineplanner.persistence.SessionFactoryProvider;
import onlineplanner.persistence.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

@WebServlet(
        name = "taskList",
        urlPatterns = { "/taskList" }
)
public class ShowTasks extends HttpServlet {
    private GenericDAO genericDAO;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        genericDAO = new GenericDAO<>(Task.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get today's date
            LocalDate today = LocalDate.now();

            // Retrieve tasks
            List<Task> todayTasks =  genericDAO.getTasksForTodoDate(today);
            List<Task> dueTodayTasks = genericDAO.getTasksForDueDate(today);
            List<Task> allTasks = genericDAO.getAll();

            logger.debug(todayTasks);
            logger.debug(dueTodayTasks);
            logger.debug(allTasks);

            // Set tasks as request attributes
            request.setAttribute("todayTasks", todayTasks);
            request.setAttribute("dueTodayTasks", dueTodayTasks);
            request.setAttribute("allTasks", allTasks);

            // Forward the request to the JSP
            request.getRequestDispatcher("/taskList.jsp").forward(request, response);
        } catch (Exception e) {
        logger.error("Error retriving tasks", e.getMessage());
    }
    }
}