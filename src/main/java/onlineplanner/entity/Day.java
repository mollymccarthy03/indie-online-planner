package onlineplanner.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;  // Reference to the Week entity

    // One-to-many relationships for tasks with todoDate and dueDate
    @OneToMany(mappedBy = "todoDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasksToDo = new ArrayList<>();

    @OneToMany(mappedBy = "dueDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasksDue = new ArrayList<>();

    // Constructors
    public Day() {}

    public Day(String dayOfWeek, Week week) {
        this.dayOfWeek = dayOfWeek;
        this.week = week;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public List<Task> getTasksToDo() {
        return tasksToDo;
    }

    public void setTasksToDo(List<Task> tasksToDo) {
        this.tasksToDo = tasksToDo;
    }

    public List<Task> getTasksDue() {
        return tasksDue;
    }

    public void setTasksDue(List<Task> tasksDue) {
        this.tasksDue = tasksDue;
    }

    // Helper methods to add/remove tasks
    public void addTaskToDo(Task task) {
        tasksToDo.add(task);
        task.setTodoDay(this);
    }

    public void removeTaskToDo(Task task) {
        tasksToDo.remove(task);
        task.setTodoDay(null);
    }

    public void addTaskDue(Task task) {
        tasksDue.add(task);
        task.setDueDay(this);
    }

    public void removeTaskDue(Task task) {
        tasksDue.remove(task);
        task.setDueDay(null);
    }

    @Override
    public String toString() {
        return "Day: " + dayOfWeek;
    }
}
