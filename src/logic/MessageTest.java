package logic;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @org.junit.jupiter.api.Test
    void getMessagesByDate() {
        long l = 1619852400000L;
        long lo = 1621574812313L;
        Date d = new Date(l);
        Date da = new Date(lo);
        ArrayList<Message> messages = Message.getMessagesByDate(d, da);
        assertEquals(15, messages.size());

    }
    //String firstname, String lastname, String subject, String textBody, String dateTime, int subscriberCount
    @org.junit.jupiter.api.Test
    void getSubject() {
        Message m = new Message(null, null, "subject", null, null, 0);
        assertEquals("subject", m.getSubject());
    }

    @org.junit.jupiter.api.Test
    void getTextBody() {
        Message m = new Message(null, null, null, "Text Body", null, 0);
        assertEquals("Text Body", m.getTextBody());
    }

    @org.junit.jupiter.api.Test
    void getDateTime() {
        Message m = new Message(null, null, null, null, "2021:05:19 00:00:00.00", 0);
        assertEquals("2021:05:19 00:00:00.00", m.getDateTime());
    }

    @org.junit.jupiter.api.Test
    void getFullName() {
        Message m = new Message("firstname", "lastname", null, null, null, 0);
        assertEquals("firstname lastname", m.getFullName());
    }

    @org.junit.jupiter.api.Test
    void getSubscriberCount() {
        Message m = new Message(null, null, null, null, null, 22);
        assertEquals(22, m.getSubscriberCount());
    }
}