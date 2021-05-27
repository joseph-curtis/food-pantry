package presentation;
/**
 * @author Joseph Curtis
 * @version 2021.05.26
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class ViewTemplatesForm extends DefaultTableModel implements GUIForm {
    private JPanel rootPanel;
    private JTable templatesTable;
    private JButton editTemplateButton;
    private JLabel windowHeaderLabel;
    private JButton createTemplateButton;
    private static String format;
    private Date startDate;
    private Date endDate;
    private DefaultTableModel model;

    public ViewTemplatesForm() {
        showTemplatesTable();
    }

    private void showTemplatesTable() {
        templatesTable.setModel(new DefaultTableModel(
                null,
                new String[] {"Name", "Subject", "Body"}
        ));

        // button event handlers
        createTemplateButton.addActionListener(event -> {

        });

        editTemplateButton.addActionListener(event -> {

        });
    }

    @Override
    public JPanel getRootPanel() { return rootPanel; }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
