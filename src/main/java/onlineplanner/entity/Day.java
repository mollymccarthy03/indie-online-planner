package onlineplanner.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;  // Reference to the Week this day belongs to

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();  // List of tasks associated with this day

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    // Helper method to add a task
    public void addTask(Task task) {
        tasks.add(task);
        task.setDay(this);
    }

    // Helper method to remove a task
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setDay(null);
    }

    @Override
    public String toString() {
        return "Day: " + dayOfWeek;
    }
}
