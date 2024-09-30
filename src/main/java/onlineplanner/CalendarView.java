package onlineplanner;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class CalendarView {

    public void displayWeekCalendar(TaskManager taskManager) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfWeek.plusDays(i);
            System.out.println(date.getDayOfWeek() + " " + date);
            List<Task> tasks = taskManager.getTasksForDate(date);
            if (tasks.isEmpty()) {
                System.out.println("No tasks for this day.");
            } else {
                tasks.forEach(task -> System.out.println(" - " + task));
            }
        }
    }
}
