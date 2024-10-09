package onlineplanner.util;

import onlineplanner.WeekDAO;
import onlineplanner.entity.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeekDAOTest {

    WeekDAO weekDAO;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("resources/cleanDB.sql");
        weekDAO = new WeekDAO();
    }

    @Test
    void getById() {
        Week week = weekDAO.getById(1);
        assertNotNull(week);
        assertEquals(LocalDate.of(2024, 10, 7), week.getMon());
    }

    @Test
    void update() {
        // Retrieve the week you want to update (assume it exists)
        Week existingWeek = weekDAO.getById(1);

        // Store the original value to restore it later
        LocalDate originalMonday = existingWeek.getMon();

        // Modify the week (e.g., update Monday's date)
        existingWeek.setMon(LocalDate.of(2024, 10, 14));
        weekDAO.update(existingWeek);

        // Retrieve the week again and assert that the update occurred
        Week updatedWeek = weekDAO.getById(1);
        assertEquals(LocalDate.of(2024, 10, 14), updatedWeek.getMon());

        // Restore the original value
        existingWeek.setMon(originalMonday);
        weekDAO.update(existingWeek);

        // Verify the restoration
        Week restoredWeek = weekDAO.getById(1);
        assertEquals(originalMonday, restoredWeek.getMon());
    }


    @Test
    void insertSuccess() {
        // Create a new week
        Week weekToInsert = new Week(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(6));
        // Insert the week
        int weekId = weekDAO.insert(weekToInsert);
        // Retrieve the week and assert that it was inserted
        Week insertedWeek = weekDAO.getById(weekId);
        assertNotEquals(0, insertedWeek);
        assertEquals(LocalDate.now(), insertedWeek.getMon());
    }

    @Test
    void delete() {
        weekDAO.delete(weekDAO.getById(3));
        assertNull(weekDAO.getById(3));
    }

    @Test
    void getAll() {
        List<Week> weeks = weekDAO.getAll();
        assertEquals(2, weeks.size());
    }
}
