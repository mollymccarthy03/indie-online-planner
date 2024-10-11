package onlineplanner.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "week")
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> days = new ArrayList<>();  // List of days in the week

    // Constructors
    public Week() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    // Helper method to add a day to the week
    public void addDay(Day day) {
        days.add(day);
        day.setWeek(this);
    }

    // Helper method to remove a day from the week
    public void removeDay(Day day) {
        days.remove(day);
        day.setWeek(null);
    }

    @Override
    public String toString() {
        return "Week " + id + " with " + days.size() + " days";
    }
}
