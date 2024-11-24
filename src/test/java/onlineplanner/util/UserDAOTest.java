package onlineplanner.util;

import onlineplanner.persistence.UserDAO;
import onlineplanner.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {


    @BeforeEach
    @Test
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("src/test/resources/cleanDB.sql"); // Ensure clean DB before tests
        userDAO = new UserDAO();

    }

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
        assertEquals(3, users.size());
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
}
