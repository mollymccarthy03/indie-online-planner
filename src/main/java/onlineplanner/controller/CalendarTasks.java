//package onlineplanner.controller;
//
//import onlineplanner.entity.Task;
//import onlineplanner.persistence.TaskDAO;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/calendar")
//public class CalendarTasks extends HttpServlet {
//
//    // Sample task service that would retrieve tasks from a database or repository
//    private TaskDAO taskDAO = new TaskDAO();
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get current date or specific date from the request parameter
//        LocalDate currentDate = LocalDate.now();
//
//        // Get the start of the week (Monday)
//        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
//
//        // Retrieve tasks for the week (Monday to Sunday)
//        //Map<DayOfWeek, List<Task>> tasksByDay = taskDAO.getTasksForWeek(startOfWeek);
//
//        // Set tasks as attributes for JSP
//        request.setAttribute("mon", tasksByDay.get(DayOfWeek.MONDAY));
//        request.setAttribute("tue", tasksByDay.get(DayOfWeek.TUESDAY));
//        request.setAttribute("wed", tasksByDay.get(DayOfWeek.WEDNESDAY));
//        request.setAttribute("thu", tasksByDay.get(DayOfWeek.THURSDAY));
//        request.setAttribute("fri", tasksByDay.get(DayOfWeek.FRIDAY));
//        request.setAttribute("sat", tasksByDay.get(DayOfWeek.SATURDAY));
//        request.setAttribute("sun", tasksByDay.get(DayOfWeek.SUNDAY));
//
//        // Forward the request to the JSP page
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/calendar.jsp");
//        dispatcher.forward(request, response);
//    }
//}
