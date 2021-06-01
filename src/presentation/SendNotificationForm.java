package presentation;

import data.Database;
import logic.Parser;
import logic.Person;
import logic.Notification;
import logic.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UI to send notifications to subscribers
 * @author Joseph Curtis
 * @version 2021.04.19
 */
public class SendNotificationForm implements GUIForm {
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
    private JComboBox templateComboBox;
    private ArrayList<Template> templates;
    //private String oldSubject = "";
    //private String oldBodyText = "";

    /**
     * Constructor sets properties for declared components
     */
    public SendNotificationForm(Person currentUser) {
        rootPanel.setPreferredSize(new Dimension(400, 500));

        setupTemplateCombo();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emailSentSuccess = false;
                boolean addressError = false;
                Notification notification = new Notification(
                        Person.getStudentList(), currentUser, subjectTextField.getText(), bodyTextArea.getText()
                );

                notification.sendSMS();
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

    public void setupTemplateCombo(){
        templates = Database.getAllTemplatesList();
        templateComboBox.addItem(null);
        for(Template template : templates){
            templateComboBox.addItem(template.getSubject());
        }

        templateComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index =  templateComboBox.getSelectedIndex();

                    subjectTextField.setText(templates.get(index-1).getSubject());
                    subjectTextField.setEditable(false);

                    bodyTextArea.setText(templates.get(index-1).getTextBody());
                    bodyTextArea.setEditable(false);

                    ArrayList<String>  tags = Parser.parseTags(templates.get(index-1).getTextBody());

                    bodyTextArea.setText(Parser.setUpTagFields(tags, templates.get(index-1).getTextBody()));
                }
            }
        });
    }
//    //for(Component c : tagPanel.getComponents()){
//                if(c instanceof JTextField) {
//                        tags.put(tag, ((JTextField) c).getText());
//                        System.out.println(((JTextField) c).getText());
//                        }
//bodyTextArea.setText(Parser.replaceTag(((JTextField) c).getText()));
//    public void replaceText(){
//        //definitely rename this
//        //save old values for text then if the user changes back to index 0 reload those old values
//        oldSubject = subjectTextField.getText();
//        oldBodyText = bodyTextArea.getText();
//        int index =  templateComboBox.getSelectedIndex();
//        if(index == 0){
//            subjectTextField.setText(oldSubject);
//            subjectTextField.setEditable(true);
//
//            bodyTextArea.setText(oldBodyText);
//            bodyTextArea.setEditable(true);
//        }
//    }
    /**
     * getter for the root panel
     * @return the root JPanel for this form
     */
    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

}
