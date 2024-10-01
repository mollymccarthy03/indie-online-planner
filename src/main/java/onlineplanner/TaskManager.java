package onlineplanner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public List<Task> getTasksForDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
