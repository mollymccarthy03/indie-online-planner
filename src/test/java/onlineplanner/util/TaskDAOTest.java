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
        database.runSQL("src/test/resources/cleanDB.sql");
        taskDAO = new TaskDAO();
    }

    TaskDAO taskDAO;
    @Test
    void getById() {
        taskDAO.getById(1);
        Task getTask = taskDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Finish Planner",getTask.getTitle());
    }

    @Test
    void update() {
        // Retrieve the task you want to update (assume it exists)
        Task existingTask = taskDAO.getById(2);

        // Store the original value to restore it later
        String originalTitle = existingTask.getTitle();

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