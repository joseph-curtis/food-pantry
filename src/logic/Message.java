package logic;

import data.Database;

import java.util.ArrayList;
import java.util.Date; //maybe simpledate

public class Message {
    private String subject;
    private String textBody;
    private String fromPersonId;
    private String   dateTime;

    public Message(String subject, String textBody, String fromPersonId, String dateTime) {
        this.subject = subject;
        this.textBody = textBody;
        this.fromPersonId = fromPersonId;
        this.dateTime = dateTime;
    }

    public static ArrayList<Message> getAllMessages(){
        return Database.getAllMessages();
    }

    public String getSubject() {
        return subject;
    }

    public String getTextBody() {
        return textBody;
    }

    public String getFromPersonId() {
        return fromPersonId;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return  subject + "\n\n" +
                textBody + "\n" +
                fromPersonId + "\n" +
                dateTime;
    }
}
