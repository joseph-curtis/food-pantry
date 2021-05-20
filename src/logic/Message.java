package logic;

import data.Database;

import java.util.ArrayList;
import java.sql.Date;
//import java.util.Date;

public class Message {
    private String subject;
    private String textBody;
    private String fromPersonId;
    private String dateTime;
    private String firstname;
    private String lastname;
    private int subscriberCount;
    private String fullname;

    public Message(String firstname, String lastname, String subject, String textBody, String dateTime, int subscriberCount) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.subject = subject;
        this.textBody = textBody;
        this.dateTime = dateTime;
        this.subscriberCount = subscriberCount;
        this.fullname = firstname + " " + lastname;
    }

    public static ArrayList<Message> getMessagesByDate(Date startDate, Date endDate) {
        return Database.getMessagesByDate(startDate, endDate);
    }

    public String getSubject() {
        return subject;
    }

    public String getTextBody() {
        return textBody;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getFullName() {
        return fullname;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }
}
