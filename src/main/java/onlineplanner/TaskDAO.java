package onlineplanner;

import java.time.LocalDate;
import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTasks();
    List<Task> getTasksForDate(LocalDate date);
    void updateTask(Task task);
    void deleteTask(int id);
}
