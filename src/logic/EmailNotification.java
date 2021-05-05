package logic;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// sourced some code from https://github.com/PCC-CIS-234A/JavaMail/blob/master/src/Main.java
public class EmailNotification {
    private static final String username = "teamcjklol@gmail.com";
    private static final String password = "zkymvgnqnmjezozv";

    /*
     * Send (via email) the notification.
     * @param fromAddress the return-to address
     * @param toAddress the address list to send to
     * @param subject the message subject
     * @param body the message body content
     * @return false if network is unavailable
     * @throws RuntimeException when an address given is incorrect
     */
    public static boolean send(String fromAddress, String toAddress, String subject, String body) {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject(subject);
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


}
