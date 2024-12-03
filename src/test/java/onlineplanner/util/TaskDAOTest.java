package onlineplanner.util;

import onlineplanner.persistence.TaskDAO;
import onlineplanner.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskDAOTest {

    @BeforeEach
            void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        taskDAO = new TaskDAO();
    }

    TaskDAO taskDAO;
    @Test
    void getById() {
        taskDAO.getById(1);
        Task getTask = taskDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Complete homework",getTask.getTitle());
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
        taskDAO.delete(taskDAO.getById(4));
        assertNull(taskDAO.getById(4));
    }

    @Test
    void getAll() {
        List<Task> tasks = taskDAO.getAll();
        assertEquals(9, tasks.size());
    }

    @Test
    void getByPropertyEqual() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyEqual("title", "Doctor appointment");
        assertEquals(1, tasks.size());
    }

    @Test
    void getByPropertyLike() {
        taskDAO = new TaskDAO();
        List<Task> tasks = taskDAO.getByPropertyLike("title", "Read");
        assertEquals(1, tasks.size());
    }

}