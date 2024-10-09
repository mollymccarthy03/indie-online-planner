package onlineplanner.util;

import onlineplanner.WeekDAO;
import onlineplanner.entity.Task;
import onlineplanner.entity.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
