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

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> mon;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tues;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> weds;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> thurs;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> fri;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> sat;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> sun;

    // Mapping each day of the week to a list of tasks
    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    // Constructors, getters, setters...

    public Week() {
    }

    public Week(List<Task> mon, List<Task> tues, List<Task> weds, List<Task> thurs, List<Task> fri, List<Task> sat, List<Task> sun) {
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

    public List<Task> getMon() {
        return mon;
    }

    public void setMon(List<Task> mon) {
        this.mon = mon;
    }

    public List<Task> getTues() {
        return tues;
    }

    public void setTues(List<Task> tues) {
        this.tues = tues;
    }

    public List<Task> getWeds() {
        return weds;
    }

    public void setWeds(List<Task> weds) {
        this.weds = weds;
    }

    public List<Task> getThurs() {
        return thurs;
    }

    public void setThurs(List<Task> thurs) {
        this.thurs = thurs;
    }

    public List<Task> getFri() {
        return fri;
    }

    public void setFri(List<Task> fri) {
        this.fri = fri;
    }

    public List<Task> getSat() {
        return sat;
    }

    public void setSat(List<Task> sat) {
        this.sat = sat;
    }

    public List<Task> getSun() {
        return sun;
    }

    public void setSun(List<Task> sun) {
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

    }
    public void showAllTasks(Task task) {

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
