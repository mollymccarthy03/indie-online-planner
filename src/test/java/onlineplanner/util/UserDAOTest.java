package onlineplanner.util;

import onlineplanner.entity.Task;
import onlineplanner.persistence.TaskDAO;
import onlineplanner.persistence.UserDAO;
import onlineplanner.entity.User;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {


    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDAO = new UserDAO();
        taskDAO = new TaskDAO();

    }
    TaskDAO taskDAO;
    UserDAO userDAO;

    @Test
    void getById() {
        // Test fetching a user by ID
        User user = userDAO.getById(1);
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    void update() {
        // Retrieve the user you want to update (assume it exists)
        User existingUser = userDAO.getById(1);

        // Store the original value to restore it later
        String originalUsername = existingUser.getUsername();

        // Modify the user (e.g., update the username)
        existingUser.setUsername("updated_username");
        userDAO.update(existingUser);

        // Retrieve the user again and assert that the update occurred
        User updatedUser = userDAO.getById(1);
        assertEquals("updated_username", updatedUser.getUsername());

        // Optionally, restore the original value after the test
        existingUser.setUsername(originalUsername);
        userDAO.update(existingUser);
    }

    @Test
    void delete() {
        // Delete a user and assert that they no longer exist
        userDAO.delete(userDAO.getById(5));
        assertNull(userDAO.getById(5));
    }

    @Test
    void getAll() {
        // Test fetching all users
        List<User> users = userDAO.getAll();
        assertEquals(4, users.size());
    }

    @Test
    void getByPropertyEqual() {
        // Test fetching users by exact property match (e.g., username)
        List<User> users = userDAO.getByPropertyEqual("username", "john_doe");
        assertEquals(1, users.size());
        assertEquals("john_doe", users.get(0).getUsername());
    }

    @Test
    void getByPropertyLike() {
        // Test fetching users by partial property match (e.g., username contains "john")
        List<User> users = userDAO.getByPropertyLike("username", "john");
        assertEquals(1, users.size());
    }
    @Test
    void addTaskToUser() {
        // Retrieve an existing user
        User user = userDAO.getById(1);
        assertNotNull(user);

        // Create a new task and associate it with the user
        Task newTask = new Task();
        newTask.setTitle("New Task");
        newTask.setDescription("New task description");
        newTask.setTodoDate(20241201);
        newTask.setDueDate(20241205);
        newTask.setUser(user);

        // Save the task
        taskDAO.insert(newTask);

        // Verify the task has been saved by checking its ID is not null (or 0)
        assertNotNull(newTask.getId(), "Task ID should not be null after saving");

        // Retrieve the task by its ID from the database
        Task savedTask = taskDAO.getById(newTask.getId());
        assertNotNull(savedTask, "Saved task should not be null");
        assertEquals(user.getId(), savedTask.getUser().getId(), "Task should be associated with the correct user");
    }

    @Test
    void deleteTaskAssociatedWithUser() {
        // Retrieve user with ID 5 and ensure it exists
        User user = userDAO.getById(5);
        assertNotNull(user);

        // Retrieve the first task associated with the user
        Task taskToDelete = taskDAO.getByPropertyEqual("user", user).stream().findFirst().orElse(null);
        assertNotNull(taskToDelete, "User should have at least one task");

        // Get the task ID before deletion
        int taskId = taskToDelete.getId();

        // Delete the task
        taskDAO.delete(taskToDelete);

        // Verify the task is deleted
        assertNull(taskDAO.getById(taskId), "The task should be deleted");
    }

    @Test
    void deleteUserAndTasks() {
        // Retrieve an existing user with tasks
        User user = userDAO.getById(3);
        assertNotNull(user);

        // Retrieve and delete all tasks associated with the user
        List<Task> userTasks = new ArrayList<>(user.getTasks());
        for (Task task : userTasks) {
            taskDAO.delete(task);  // Manually delete each task
        }

        // Delete the user
        userDAO.delete(user);

        // Verify the user is deleted
        assertNull(userDAO.getById(3), "User should be deleted");

        // Verify the tasks are deleted
        for (Task task : userTasks) {
            assertNull(taskDAO.getById(task.getId()), "Task should be deleted");
        }
    }
}
