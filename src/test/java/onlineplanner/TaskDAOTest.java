package onlineplanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TaskDAOTest {

    TaskDAO taskDAO;
    @Test
    void getById() {
        taskDAO = new TaskDAO();
        taskDAO.getById(1);
        Task getTask = taskDAO.getById(1);
        assertNotNull(getTask);
        assertEquals("Week One",getTask.getTitle());
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByPropertyEqual() {
    }

    @Test
    void getByPropertyLike() {
    }
}