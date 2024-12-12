package onlineplanner.util;

import onlineplanner.entity.User;
import onlineplanner.persistence.GenericDAO;
import onlineplanner.entity.Task;
import onlineplanner.persistence.TaskDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TaskDAOTest {

    GenericDAO<User> userDAO;
    GenericDAO<Task> taskDAO;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        userDAO = new GenericDAO<>(User .class);
        taskDAO = new GenericDAO<>(Task.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    @Test
    void getById() {
        Task getTask = taskDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Complete homework", getTask.getTitle());
    }

    @Test
    void update() {
        // Retrieve the task you want to update (assume it exists)
        Task existingTask = taskDAO.getById(2);

        // Modify the task (e.g., update the title)
        existingTask.setTitle("Updated Task Title");
        taskDAO.update(existingTask);

        // Retrieve the task again and assert that the update occurred
        Task updatedTask = taskDAO.getById(2);
        assertEquals("Updated Task Title", updatedTask.getTitle());
    }

    @Test
    void delete() {
        Task taskToDelete = taskDAO.getById(4);
        taskDAO.delete(taskToDelete);
        assertNull(taskDAO.getById(4));
    }

    @Test
    void getAll() {
        List<Task> tasks = taskDAO.getAll();
        assertEquals(10, tasks.size());
    }

    @Test
    void getByPropertyEqual() {
        List<Task> tasks = taskDAO.getByPropertyEqual("title", "Complete Homework");
        assertEquals(1, tasks.size());
    }

    @Test
    void getByPropertyLike() {
        List<Task> tasks = taskDAO.getByPropertyLike("title", "Read");
        assertEquals(1, tasks.size());
    }
    @Test
    void getTasksForTodoDate() {
        // Define the date for which we want to fetch tasks
        LocalDate todoDate = LocalDate.of(2024, 12, 15);

        // Fetch tasks with the specified tododate
        List<Task> tasks = taskDAO.getByPropertyEqual("todoDate", todoDate);

        // Assertions
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty(), "Tasks list should not be empty");
        assertEquals(1, tasks.size());
        assertEquals(todoDate, tasks.get(0).getTodoDate());
    }

    @Test
    void getTasksForDueDate() {
        // Define the date for which we want to fetch tasks
        LocalDate dueDate = LocalDate.of(2024, 12, 15);

        // Fetch tasks with the specified due date
        List<Task> tasks = taskDAO.getByPropertyEqual("dueDate", dueDate);

        // Assertions
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty(), "Tasks list should not be empty");
        assertEquals(3, tasks.size());
        assertEquals(dueDate, tasks.get(0).getDueDate());
    }
}
