package logic;

import data.Database;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// sourced some code from https://github.com/PCC-CIS-234A/JavaMail/blob/master/src/Main.java

/**
 * Holds message data and can email or text (sprint 2).
 * Also saves message to database.
 * @author Joseph Curtis
 * @version 2021.05.18
 */
public class Notification {
    private static final String emailUsername = "teamcjklol@gmail.com";
    private static final String emailPassword = "zkymvgnqnmjezozv";

    private final ArrayList<Person> studentList;
    private final String sendToEmailString;
    private final Person fromPerson;
    private String subject;
    private String textBody;

    public Notification(ArrayList<Person> studentList, Person fromPerson, String subject, String textBody) {
        this.studentList = studentList;
        sendToEmailString = getAllEmailString();
        this.fromPerson = fromPerson;
        this.subject = subject;
        this.textBody = textBody;
    }

    /**
     * sends the (instantiated) message
     * @return true if email sent successfully
     */
    public boolean sendEmail() throws RuntimeException {
        //String signedTextBody = textBody + "<p>From:<br/>"
        //        + fromPerson.getFirstName() + ' ' + fromPerson.getLastName() + "</p>";

        return sendEmail(fromPerson.getEmail(), sendToEmailString, subject, textBody);
    }

    /**
     * Send (via email) the notification.
     * @param fromAddress the return-to address
     * @param toAddress the address list to send to
     * @param subj the message subject
     * @param body the message body content
     * @return false if network is unavailable
     * @throws RuntimeException when an address given is incorrect
     */
    public static boolean sendEmail(String fromAddress
            , String toAddress
            , String subj
            , String body) throws RuntimeException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailUsername, emailPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            // TO is the team email, all students are BCC
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(fromAddress));
            message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(toAddress));
            message.setSubject(subj);
            message.setContent(body, "text/plain");

            Transport.send(message);
            return true;
        }
        catch (javax.mail.internet.AddressException e) {
            throw new RuntimeException(e);
        }
        catch (javax.mail.MessagingException e) {
            // network is unavailable
            e.printStackTrace();
            return false;
        }
    }

    public void saveMessage() throws RuntimeException {
        Database.saveMessage(fromPerson.getID(), studentList, subject, textBody);
    }

    private String getAllEmailString() {
        StringBuilder allStudentEmails = new StringBuilder();
        for (Person student: studentList) {
            allStudentEmails.append(student.toEmailString()).append(", ");
        }
        return allStudentEmails.toString();
    }

    public Person getFromPerson() {
        return fromPerson;
    }
    public String getSubject() {
        return subject;
    }
    public String getTextBody() {
        return textBody;
    }
    public String getSendToEmailString() {
        return sendToEmailString;
    }
    public void setSubject(String subj) {
        subject = subj;
    }
    public void setTextBody(String body) {
        textBody = body;
    }
    public String toString() {
        return "###MESSAGE###\nFROM: " + fromPerson.toEmailString()
                + "\nTO: " + sendToEmailString
                + "\nSUBJECT: " + subject
                + "\nBODY: " + textBody;
    }

}
