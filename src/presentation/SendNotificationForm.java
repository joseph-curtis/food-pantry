package presentation;

import logic.Person;
import logic.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UI to send notifications to subscribers
 * @author Joseph Curtis
 * @version 2021.04.19
 */
public class SendNotificationForm extends GUIForm {
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
        rootPanel.setPreferredSize(new Dimension(400, 500));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emailSentSuccess = false;
                boolean addressError = false;
                Notification notification = new Notification(
                        Person.getStudentList(), currentUser, subjectTextField.getText(), bodyTextArea.getText()
                );

                // Send message confirmation dialog

                try {
                    emailSentSuccess = notification.sendEmail();
                } catch (RuntimeException exception) {
                    addressError = true;
                    JOptionPane.showMessageDialog(rootPanel
                            , "An address was incorrect!  Check the following addresses:\n"
                                    + "FROM= " + currentUser.toEmailString()
                                    + "; TO= " + notification.getSendToEmailString()
                            , "ERROR AddressException", JOptionPane.WARNING_MESSAGE
                    );
                } finally {
                    if (emailSentSuccess) {
                        JOptionPane.showMessageDialog(rootPanel
                                , "Email sent successfully!"
                                , "Success", JOptionPane.INFORMATION_MESSAGE
                        );
                        try {
                            // save message to the database
                            notification.saveMessage();
                            subjectTextField.setText("");
                            bodyTextArea.setText("");
                        } catch (RuntimeException exception) {
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(rootPanel
                                    , "Check database state"
                                    , "DATABASE ERROR", JOptionPane.ERROR_MESSAGE
                            );
                            // print message out to save later
                            System.out.print(notification.toString());
                        }

                    } else if (!addressError) {
                        JOptionPane.showMessageDialog(rootPanel
                                , "Check network connection"
                                , "Network Unavailable", JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
    }

    /**
     * getter for the root panel
     * @return the root JPanel for this form
     */
    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

}
