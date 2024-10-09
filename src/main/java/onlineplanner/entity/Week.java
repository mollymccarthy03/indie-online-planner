package onlineplanner.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "week")
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "mon")
    private LocalDate mon;

    @Column(name = "tues")
    private LocalDate tues;

    @Column(name = "weds")
    private LocalDate weds;

    @Column(name = "thurs")
    private LocalDate thurs;

    @Column(name = "fri")
    private LocalDate fri;

    @Column(name = "sat")
    private LocalDate sat;

    @Column(name = "sun")
    private LocalDate sun;

    // Mapping each day of the week to a list of tasks
    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    // Constructors, getters, setters...

    public Week() {
    }

    public Week(LocalDate mon, LocalDate tues, LocalDate weds, LocalDate thurs, LocalDate fri, LocalDate sat, LocalDate sun) {
        this.mon = mon;
        this.tues = tues;
        this.weds = weds;
        this.thurs = thurs;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    // Getters and Setters for fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getMon() {
        return mon;
    }

    public void setMon(LocalDate mon) {
        this.mon = mon;
    }

    public LocalDate getTues() {
        return tues;
    }

    public void setTues(LocalDate tues) {
        this.tues = tues;
    }

    public LocalDate getWeds() {
        return weds;
    }

    public void setWeds(LocalDate weds) {
        this.weds = weds;
    }

    public LocalDate getThurs() {
        return thurs;
    }

    public void setThurs(LocalDate thurs) {
        this.thurs = thurs;
    }

    public LocalDate getFri() {
        return fri;
    }

    public void setFri(LocalDate fri) {
        this.fri = fri;
    }

    public LocalDate getSat() {
        return sat;
    }

    public void setSat(LocalDate sat) {
        this.sat = sat;
    }

    public LocalDate getSun() {
        return sun;
    }

    public void setSun(LocalDate sun) {
        this.sun = sun;
    }

    // Manage tasks (One-to-Many relationship)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setWeek(this); // Ensure the bidirectional relationship is maintained
    }

    // toString method
    @Override
    public String toString() {
        return "Week{" +
                "Monday=" + mon +
                ", Tuesday=" + tues +
                ", Wednesday=" + weds +
                ", Thursday=" + thurs +
                ", Friday=" + fri +
                ", Saturday=" + sat +
                ", Sunday=" + sun +
                '}';
    }
}
