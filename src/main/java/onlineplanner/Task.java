package onlineplanner;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate todoDate;
    private LocalDate dueDate;

    public Task(String title, LocalDate todoDate, LocalDate dueDate, String description) {
        this.title = title;
        this.todoDate = todoDate;
        this.dueDate = dueDate;
        this.description = description;
    }

    // Getters and Setters
    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTodoDate() {
        return todoDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return title + " - Planned on: " + todoDate + ", Due: " + dueDate;
    }
}

