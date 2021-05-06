package logic;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// sourced some code from https://github.com/PCC-CIS-234A/JavaMail/blob/master/src/Main.java
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
        sendToEmailString = getAllStudentsEmailString();
        this.fromPerson = fromPerson;
        this.subject = subject;
        this.textBody = textBody;
    }

    /*
    sends the (instantiated) message
    @param fromPerson current user (Person object) that is sending notification
    @return true if email sent successfully
     */
    public boolean sendEmail() {
        String signedTextBody = textBody + "<p>From:<br/>"
                + fromPerson.getFirstName() + ' ' + fromPerson.getLastName() + "</p>";

        return sendEmail(fromPerson.getEmail(), sendToEmailString, subject, signedTextBody);
    }

    /*
     * Send (via email) the notification.
     * @param fromAddress the return-to address
     * @param toAddress the address list to send to
     * @param subject the message subject
     * @param body the message body content
     * @return false if network is unavailable
     * @throws RuntimeException when an address given is incorrect
     */
    public static boolean sendEmail(String fromAddress, String toAddress, String subj, String body) {
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
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject(subj);
            message.setContent(body, "text/html");

            Transport.send(message);
            return true;
        }
        catch (AddressException e) {
            throw new RuntimeException(e);
        }
        catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveMessage() {
        // exception handling?  or set return type to boolean?
    }

    private String getAllStudentsEmailString() {
        StringBuilder allStudentEmails = new StringBuilder();
        for (Person student: studentList) {
            allStudentEmails.append(student.toEmailString()).append(", ");
        }
        return allStudentEmails.toString();
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

}
