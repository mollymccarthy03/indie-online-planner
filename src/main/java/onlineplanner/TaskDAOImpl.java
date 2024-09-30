package onlineplanner;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private Connection connection;

    public TaskDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, description, todo_date, due_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getTodoDate()));
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getString("title"),
                        rs.getDate("todo_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getString("title"),
                        rs.getDate("todo_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> getTasksForDate(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE todo_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getString("title"),
                        rs.getDate("todo_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, todo_date = ?, due_date = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setDate(3, Date.valueOf(task.getTodoDate()));
            stmt.setDate(4, Date.valueOf(task.getDueDate()));
            stmt.setInt(5, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
