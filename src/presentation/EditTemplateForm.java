package presentation;
/**
 * @author Joseph Curtis
 * @version 2021.06.02
 */

import logic.Template;
import main.Controller;

import javax.swing.*;
import java.awt.*;

public class EditTemplateForm extends Component implements GUIForm{
    private JPanel rootPanel;
    private JLabel subjectLabel;
    private JTextField subjectTextField;
    private JButton saveButton;
    private JTextArea bodyTextArea;
    private JPanel subjectPanel;
    private JScrollPane bodyScrollPane;
    private JPanel bodyPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton cancelButton;
    private JPanel buttonsPanel;
    private JTextField nameTextField;
    private JLabel nameLabel;

    /**
     * Edit an existing template
     * @param windowFrame the JFrame this is attached to
     * @param template existing template to edit
     */
    public EditTemplateForm(JFrame windowFrame, Template template) {
        titleLabel.setText("Edit Template");
        rootPanel.setPreferredSize(new Dimension(400, 500));

        // set fields to existing data
        nameTextField.setText(template.getName());
        subjectTextField.setText(template.getSubject());
        bodyTextArea.setText(template.getTextBody());

        // button event handlers
        cancelButton.addActionListener(event -> {
            windowFrame.dispose();
        });
        saveButton.addActionListener(event -> {
            if (saveEventCheck()) {
                // fields are not blank, set new values and save
                template.setName(nameTextField.getText());
                template.setSubject(subjectTextField.getText());
                template.setTextBody(bodyTextArea.getText());

                saveTemplate(template);
                Controller.updateTemplatesTab();
                windowFrame.dispose();
            }
        });
    }

    /**
     * Create a new template
     * @param windowFrame the JFrame this is attached to
     */
    public EditTemplateForm(JFrame windowFrame) {
        titleLabel.setText("Create New Template");
        rootPanel.setPreferredSize(new Dimension(400, 500));

        // button event handlers
        cancelButton.addActionListener(event -> {
            windowFrame.dispose();
        });
        saveButton.addActionListener(event -> {
            if (saveEventCheck()) {
                saveTemplate();
                Controller.updateTemplatesTab();
                windowFrame.dispose();
            }
        });
    }

    /**
     * Check fields for blanks, alert user
     * @return true only if Template can be saved
     */
    private boolean saveEventCheck() {
        if (nameTextField.getText().isBlank() || nameTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel
                    , "Please enter a name for the template"
                    , "Name is Required", JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (subjectTextField.getText().isBlank() || subjectTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel
                    , "Please enter a Subject"
                    , "Subject is Blank", JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (bodyTextArea.getText().isBlank() || bodyTextArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel
                    , "Please enter text for the Body"
                    , "Body is Blank", JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else {
            // returns true to save the message
            return true;
        }
    }

    /**
     * Save a new template
     */
    private void saveTemplate() {
        try {
            Template.saveTemplate(nameTextField.getText(), subjectTextField.getText(), bodyTextArea.getText());
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(rootPanel
                    , "Could not save Template.  Check database state"
                    , "DATABASE ERROR", JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void saveTemplate(Template oldTemplate) {
        try {
            oldTemplate.updateTemplate();
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(rootPanel
                    , "Could not save Template.  Check database state"
                    , "DATABASE ERROR", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
