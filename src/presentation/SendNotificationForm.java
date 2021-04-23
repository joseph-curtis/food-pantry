package presentation;

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

    /**
     * Constructor sets properties for declared components
     */
    public SendNotificationForm() {
        rootPanel.setPreferredSize(new Dimension(400, 500));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Subject: " + subjectTextField.getText()
                                + "\nBody: " + bodyTextArea.getText());
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
