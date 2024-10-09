package onlineplanner.util;

import onlineplanner.TaskDAO;
import onlineplanner.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
class TaskDAOTest {

    @BeforeEach
            void setUp() {
        Database database = Database.getInstance();
        database.runSQL("test/resources/cleanDB.sql");
        taskDAO = new TaskDAO();
    }

    TaskDAO taskDAO;
    @Test
    void getById() {
        taskDAO.getById(4);
        Task getTask = taskDAO.getById(4);
        assertNotNull(getTask);
        assertEquals("Finish Planner",getTask.getTitle());
    }

    @Test
    void update() {
        // Retrieve the task you want to update (assume it exists)
        Task existingTask = taskDAO.getById(4);

        // Store the original value to restore it later
        String originalTitle = existingTask.getTitle();

        // Modify the task (e.g., update the title)
        existingTask.setTitle("Updated Task Title");
        taskDAO.update(existingTask);

        // Retrieve the task again and assert that the update occurred
        Task updatedTask = taskDAO.getById(4);
        assertEquals("Updated Task Title", updatedTask.getTitle());

        // Restore the original value
        existingTask.setTitle(originalTitle);
        taskDAO.update(existingTask);

        // Verify the restoration
        Task restoredTask = taskDAO.getById(4);
        assertEquals(originalTitle, restoredTask.getTitle());
    }


    @Test
    void insertSuccess() {
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
        taskDAO.delete(taskDAO.getById(5));
        assertNull(taskDAO.getById(5));
    }

    @Test
    void getAll() {
        List<Task> tasks = taskDAO.getAll();
        assertEquals(2, tasks.size());
    }

    @Test
    void getByPropertyEqual() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyEqual("title", "Testing Java Application");
        assertEquals(1, tasks.size());
    }

    @Test
    void getByPropertyLike() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyLike("title", "Week");
        assertEquals(0, tasks.size());
    }
}