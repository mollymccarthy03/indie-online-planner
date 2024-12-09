package onlineplanner.controller;

import onlineplanner.entity.Task;
import onlineplanner.persistence.GenericDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addTask")
public class AddTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GenericDAO genericDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create Task object
        Task task = new Task();
        // Insert the task into the database using GenericDAO
        genericDAO.insert(task);
        // Redirect to the homepage
        response.sendRedirect("addTask.jsp");
    }
}
