package onlineplanner;

import onlineplanner.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
class TaskDAOTest {

    @BeforeEach
            void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    TaskDAO taskDAO;
    @Test
    void getById() {
        taskDAO = new TaskDAO();
        taskDAO.getById(1);
        Task getTask = taskDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Updated Week One",getTask.getTitle());
    }

    @Test
    void update() {
        taskDAO = new TaskDAO();
        // Retrieve the task you want to update (assume it exists)
        Task existingTask = taskDAO.getById(1);
        // Modify the task
        existingTask.setTitle("Updated Week One");
        taskDAO.update(existingTask);
        // Retrieve the task again and assert that the update occurred
        Task updatedTask = taskDAO.getById(1);
        assertEquals("Updated Week One", updatedTask.getTitle());
    }


    @Test
    void insertSuccess() {
        taskDAO = new TaskDAO();
        // Create a new task
        Task taskToInsert = new Task("Testing Java Application", "Add testing", LocalDate.now(), LocalDate.now().plusDays(5));
        // Insert the task
        taskDAO.insert(taskToInsert);
        // Retrieve the task and assert that it was inserted
        Task insertedTask = taskDAO.getById(taskToInsert.getId());
        assertNotEquals(0, insertedTask);
        assertEquals("Testing Java Application", insertedTask.getTitle());
    }


    @Test
    void delete() {
        taskDAO = new TaskDAO();
        taskDAO.delete(taskDAO.getById(3));
        assertNull(taskDAO.getById(3));
    }

    @Test
    void getAll() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getAll();
        assertEquals(4, tasks.size());
    }

    @Test
    void getByPropertyEqual() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyEqual("title", "Testing Java Application");
        assertEquals(3, tasks.size());
    }

    @Test
    void getByPropertyLike() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyLike("title", "Week");
        assertEquals(1, tasks.size());
    }
}