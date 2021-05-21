package logic.test;

import logic.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person employee = new Person(2062, "George", "Jetson", "teamcjklol@gmail.com");

    @BeforeEach
    void setUp() {
    }

    @Test
    void authenticateStaffUser() {
        Person dbGeorge = new Person(1112, "George", "Jetson", "teamcjklol@gmail.com");
        assertEquals(dbGeorge, Person.authenticateStaffUser("george.jetson", "workerpassword"));
    }

    @Test
    void getFirstName() {
        assertEquals("George", employee.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Jetson", employee.getLastName());
    }

    @Test
    void getID() {
        assertEquals(2062, employee.getID());
    }

    @Test
    void getEmail() {
        assertEquals("teamcjklol@gmail.com", employee.getEmail());
    }

    @Test
    void toEmailString() {
        assertEquals("George Jetson <teamcjklol@gmail.com>", employee.toEmailString());
    }
}