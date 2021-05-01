package presentation;

import logic.ActiveStaffMember;
import logic.EmailNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField recipientTextField;

    /**
     * Constructor sets properties for declared components
     */
    public SendNotificationForm(ActiveStaffMember currentUser) {
        rootPanel.setPreferredSize(new Dimension(400, 500));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = false;
                try {
                    success = EmailNotification.send(currentUser.getEmail(), recipientTextField.getText()
                            , subjectTextField.getText(), bodyTextArea.getText() + "\n From:\n" + currentUser.getName());
                } catch (RuntimeException exception) {
                    JOptionPane.showMessageDialog(rootPanel,
                            "An address was incorrect!  Check the following addresses:\n"
                            + recipientTextField.getText());
                }
                if (success) {
                    System.out.println("Email sent successfully!");
                } else {
                    System.out.println("Network unavailable!");
                }

                JOptionPane.showMessageDialog(rootPanel,
                        "Subject: " + subjectTextField.getText()
                                + "\nBody: " + bodyTextArea.getText()
                                + "\n From:\n" + currentUser.getName());
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
}
