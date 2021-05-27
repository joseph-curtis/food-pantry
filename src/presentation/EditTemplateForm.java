package presentation;

import javax.swing.*;
import java.awt.*;

public class EditTemplateForm implements GUIForm{
    private JPanel rootPanel;
    private JLabel subjectLabel;
    private JTextField subjectTextField;
    private JButton saveButton;
    private JTextArea bodyTextArea;
    private JPanel subjectPanel;
    private JScrollPane bodyScrollPane;
    private JPanel bodyPanel;
    private JPanel titlePanel;
    private JLabel recipientLabel;
    private JButton cancelButton;
    private JPanel buttonsPanel;
    private String userActionLabel;

    public EditTemplateForm(boolean isNewTemplate) {
        userActionLabel = isNewTemplate ? "Create New " : "Edit ";
        rootPanel.setPreferredSize(new Dimension(400, 500));
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
