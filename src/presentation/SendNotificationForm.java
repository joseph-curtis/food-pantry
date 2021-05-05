package presentation;

import logic.Person;
import logic.EmailNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * UI to send notifications to subscribers
 * @author Joseph Curtis
 * @version 2021.04.19
 */
public class SendNotificationForm {
    private JPanel rootPanel;
    private JLabel subjectLabel;
    private JTextField subjectTextField;
    private JButton sendButton;
    private JTextArea bodyTextArea;
    private JPanel subjectPanel;
    private JScrollPane bodyScrollPane;
    private JPanel bodyPanel;
    private JPanel recipientPanel;
    private JLabel recipientLabel;

    /**
     * Constructor sets properties for declared components
     */
    public SendNotificationForm(Person currentUser) {
        String allStudentEmails = getAllStudentsEmail();
        rootPanel.setPreferredSize(new Dimension(400, 500));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = false;
                try {
                    success = EmailNotification.send(
                            currentUser.toEmailString()
                            , allStudentEmails
                            , subjectTextField.getText()
                            , bodyTextArea.getText()
                                    + "<p>From:<br/>"
                                    + currentUser.getFirstName() + ' ' + currentUser.getLastName() + "</p>"
                    );
                } catch (RuntimeException exception) {
                    JOptionPane.showMessageDialog(rootPanel,
                            "An address was incorrect!  Check the following addresses:\n"
                            + "FROM= " + currentUser.toEmailString() + "; TO= " + allStudentEmails);
                }
                if (success) {
                    System.out.println("Email sent successfully!");
                    JOptionPane.showMessageDialog(rootPanel, "Email sent successfully!");
                } else {
                    System.out.println("Network unavailable!");
                }
            }
        });
    }

    /**
     * getter for the root panel
     * @return the root JPanel for this form
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    private String getAllStudentsEmail() {
        final ArrayList<Person> students = Person.getStudentList();
        StringBuilder allStudentEmails = new StringBuilder();
        for (Person student: students) {
            allStudentEmails.append(student.toEmailString()).append(", ");
        }
        return allStudentEmails.toString();
    }

}
