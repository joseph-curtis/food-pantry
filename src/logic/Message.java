package logic;

import data.Database;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
//import java.util.Date;

public class Message {
    private String subject;
    private String textBody;
    private String fromPersonId;
    private String dateTime;

    public Message(String subject, String textBody, String fromPersonId, String dateTime) {
        this.subject = subject;
        this.textBody = textBody;
        this.fromPersonId = fromPersonId;
        this.dateTime = dateTime;
    }

    public static ArrayList<Message> getAllMessages(){
        return Database.getAllMessages();
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

    public String getFromPersonId() {
        return fromPersonId;
    }

    public String getDateTime() {
        return dateTime;
    }

//    public void setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//    }

//    public Date stringToDate(String d){
//        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yy");
//        Date date = null;
//        try {
//            date = formatter.parse(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return date;


//        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//
//        try {
//            date = formatter.parse(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return date;
//    }


    @Override
    public String toString() {
        return  subject + "\n\n" +
                textBody + "\n" +
                fromPersonId + "\n" +
                dateTime;
    }
}
