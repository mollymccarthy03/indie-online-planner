package onlineplanner.util;

import onlineplanner.persistence.GenericDAO;
import onlineplanner.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TaskDAOTest {

    GenericDAO<Task> genericDAO;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDAO = new GenericDAO<>(Task.class);  // Use GenericDAO for Task
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    @Test
    void getById() {
        Task getTask = genericDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Complete homework", getTask.getTitle());
    }

    @Test
    void update() {
        // Retrieve the task you want to update (assume it exists)
        Task existingTask = genericDAO.getById(2);

        // Modify the task (e.g., update the title)
        existingTask.setTitle("Updated Task Title");
        genericDAO.update(existingTask);

        // Retrieve the task again and assert that the update occurred
        Task updatedTask = genericDAO.getById(2);
        assertEquals("Updated Task Title", updatedTask.getTitle());
    }

    @Test
    void delete() {
        Task taskToDelete = genericDAO.getById(4);
        genericDAO.delete(taskToDelete);
        assertNull(genericDAO.getById(4));
    }

    @Test
    void getAll() {
        List<Task> tasks = genericDAO.getAll();
        assertEquals(10, tasks.size());
        System.out.println(tasks.toString());
    }

    @Test
    void getByPropertyEqual() {
        List<Task> tasks = genericDAO.getByPropertyEqual("title", "Complete Homework");
        assertEquals(1, tasks.size());
    }

    @Test
    void getByPropertyLike() {
        List<Task> tasks = genericDAO.getByPropertyLike("title", "Read");
        assertEquals(1, tasks.size());
    }
    @Test
    void getTasksForTodoDate() {
        // Define the date for which we want to fetch tasks
        LocalDate todoDate = LocalDate.of(2024, 12, 15);

        // Fetch tasks with the specified tododate
        List<Task> tasks = genericDAO.getByPropertyEqual("todoDate", todoDate);

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
        List<Task> tasks = genericDAO.getByPropertyEqual("dueDate", dueDate);

        // Assertions
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty(), "Tasks list should not be empty");
        assertEquals(3, tasks.size());
        assertEquals(dueDate, tasks.get(0).getDueDate());
    }
}
