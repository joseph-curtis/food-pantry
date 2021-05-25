package logic.test;

import logic.Notification;
import logic.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {
    Person employee = new Person(99, "George", "Jetson", "teamcjklol@gmail.com");
    Person student1 = new Person(1, "Susan", "Studensky", "test.student1@pcc.edu");
    Person student2 = new Person(2, "Classy", "Classmate", "test.student2@pcc.edu");
    ArrayList<Person> studentList = new ArrayList<Person>();

    Notification testMessage = new Notification(studentList, employee, "subject", "body");

    @BeforeEach
    void setUp() {
        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    void getFromPerson() {
        assertEquals(employee, testMessage.getFromPerson());
    }

    @Test
    void getSubject() {
        assertEquals("subject", testMessage.getSubject());
    }

    @Test
    void getTextBody() {
        assertEquals("body", testMessage.getTextBody());
    }

    @Test
    void getSendToEmailString() {
        Notification testMessage = new Notification(studentList, employee, "subject", "body");
        assertEquals("Susan Studensky <test.student1@pcc.edu>, Classy Classmate <test.student2@pcc.edu>, "
                , testMessage.getSendToEmailString());
    }

    @Test
    void setSubject() {
        testMessage.setSubject("new subject");
        assertEquals("new subject", testMessage.getSubject());
    }

    @Test
    void setTextBody() {
        testMessage.setTextBody("a different text body");
        assertEquals("a different text body", testMessage.getTextBody());
    }

    @Test
    void testToString() {
        assertEquals("###MESSAGE###\n" +
                "FROM: George Jetson <teamcjklol@gmail.com>\n" +
                "TO: \n" +
                "SUBJECT: subject\n" +
                "BODY: body"
                , testMessage.toString());
    }
}