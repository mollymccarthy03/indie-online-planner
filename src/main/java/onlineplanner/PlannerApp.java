package onlineplanner;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class PlannerApp {

    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        TaskDAO taskDAO = new TaskDAO(connection);

        // Create a new task
        Task newTask = new Task("Team Meeting", LocalDate.now().plusDays(2), LocalDate.now().plusDays(5), "Discuss project progress");
        taskDAO.addTask(newTask);

        // Get all tasks
        List<Task> tasks = taskDAO.getAllTasks();
        tasks.forEach(task -> System.out.println(task));

        // Close connection
        try {
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
