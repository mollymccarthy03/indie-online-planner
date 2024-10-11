package onlineplanner.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    // Foreign key references to the day of the week
    @ManyToOne
    @JoinColumn(name = "todo_date", referencedColumnName = "id")
    private Day todoDay; // Day for the planned todo day

    @ManyToOne
    @JoinColumn(name = "due_date", referencedColumnName = "id")
    private Day dueDay;  // Day for the due day

    // Constructors
    public Task() {}

    // Constructor expecting Day objects
    public Task(String title, String description, Day todoDay, Day dueDay) {
        this.title = title;
        this.description = description;
        this.todoDay = todoDay;
        this.dueDay = dueDay;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Day getTodoDay() {
        return todoDay;
    }

    public void setTodoDay(Day todoDay) {
        this.todoDay = todoDay;
    }

    public Day getDueDay() {
        return dueDay;
    }

    public void setDueDay(Day dueDay) {
        this.dueDay = dueDay;
    }

    @Override
    public String toString() {
        return title + " - Planned on: " + todoDay.getDayOfWeek() + ", Due: " + dueDay.getDayOfWeek();
    }
}
