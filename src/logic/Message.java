package logic;

import java.util.Date; //maybe simpledate

public class Message {
    private String subject;
    private String textBody;
    private String fromPersonId;
    private Date   dateTime;

    public Message(String subject, String textBody, String fromPersonId, Date dateTime) {
        this.subject = subject;
        this.textBody = textBody;
        this.fromPersonId = fromPersonId;
        this.dateTime = dateTime;
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

    public Date getDateTime() {
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
