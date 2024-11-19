package onlineplanner.util;

import onlineplanner.persistence.WeekDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekDAOTest {

    WeekDAO weekDAO;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("src/test/resources/cleanDB.sql");
        weekDAO = new WeekDAO();
    }

    @Test
    void getById() {

    }

    @Test
    void update() {

    }


    @Test
    void insertSuccess() {

    }

    @Test
    void delete() {

    }

    @Test
    void getAllTasks() {

    }
}
