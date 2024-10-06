package onlineplanner;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="todo_date")
    private LocalDate todoDate;
    @Column(name="due_date")
    private LocalDate dueDate;

    public Task(String title, String description, LocalDate todoDate, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.todoDate = todoDate;
        this.dueDate = dueDate;
    }

    public Task() {

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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTodoDate(LocalDate todoDate) {
        this.todoDate = todoDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return title + " - Planned on: " + todoDate + ", Due: " + dueDate;
    }
}

