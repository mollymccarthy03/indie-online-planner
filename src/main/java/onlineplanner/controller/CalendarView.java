package onlineplanner.controller;

import onlineplanner.entity.Task;
import onlineplanner.persistence.TaskDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class CalendarView {

    private static final Logger logger = LogManager.getLogger(CalendarView.class);  // Log4j2 logger
    private EnumMap<DayOfWeek, List<Task>> weekTasks;
    private TaskDAO taskDAO; // Injecting TaskDAO

    public CalendarView(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
        weekTasks = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek day : DayOfWeek.values()) {
            weekTasks.put(day, new ArrayList<>());
        }
    }

    // Fetch tasks for the current week from the database and assign them to the appropriate day
    public void loadTasksForWeek(LocalDate startOfWeek) {
        // Loop through each day of the week and fetch tasks for each day
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            LocalDate date = startOfWeek.with(dayOfWeek);
            List<Task> tasksForDay = taskDAO.getTasksForDate(date); // Fetch tasks for this specific day
            weekTasks.put(dayOfWeek, tasksForDay);
            logger.info("Loaded tasks for {}: {}", dayOfWeek, tasksForDay.size());
        }
    }

    public List<Task> getTasksForDay(DayOfWeek day) {
        return weekTasks.get(day);
    }

    // Helper method to get the start of the current week (Monday)
    public static LocalDate getStartOfWeek() {
        return LocalDate.now().with(DayOfWeek.MONDAY);
    }

    public void printCalendar() {
        logger.info("Printing calendar tasks for the week:");
        for (DayOfWeek day : DayOfWeek.values()) {
            logger.info("Day: {}", day);
            List<Task> tasks = weekTasks.get(day);
            if (!tasks.isEmpty()) {
                tasks.forEach(task -> logger.info(" - {}", task.getTitle()));
            } else {
                logger.info(" No tasks for this day.");
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        TaskDAO taskDAO = new TaskDAO(); // Assuming you have a TaskDAO class
        CalendarView calendarView = new CalendarView(taskDAO);

        // Get the start of the current week (Monday)
        LocalDate startOfWeek = getStartOfWeek();
        calendarView.loadTasksForWeek(startOfWeek);

        // Print the calendar
        calendarView.printCalendar();
    }
}

