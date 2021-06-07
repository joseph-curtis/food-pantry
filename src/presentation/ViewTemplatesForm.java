package presentation;
/**
 * @author Joseph Curtis
 * @version 2021.06.02
 */

import data.Database;
import logic.Template;
import main.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewTemplatesForm extends DefaultTableModel implements GUIForm {
    private JPanel rootPanel;
    private JTable templatesTable;
    private JButton editTemplateButton;
    private JLabel windowHeaderLabel;
    private JButton createTemplateButton;
    private JFrame editorWindow;

    private DefaultTableModel model;
    // create table model
    private static final DefaultTableModel noEditTableModel = new DefaultTableModel(
            null,
            new String[] {"ID", "Name", "Subject", "Body"})
    {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ViewTemplatesForm() {
        showTemplatesTable();

        // button event handlers:

        createTemplateButton.addActionListener(event -> {
            editorWindow = new JFrame("Create New Template");
            Controller.showForm(new EditTemplateForm(editorWindow), editorWindow);
        });

        editTemplateButton.addActionListener(event -> {
            editorWindow = new JFrame("Template Editor");

            // get selected row index
            int row = templatesTable.getSelectedRow();
            if (row >= 0) {
                // get Template ID and get template object
                int templateID = (int) model.getValueAt(row, 0);
                Template template = Database.getTemplate(templateID);

                // show Edit template form
                editorWindow = new JFrame();
                Controller.showForm(new EditTemplateForm(editorWindow, template), editorWindow);
            }
        });
    }

    public void showTemplatesTable() {
        // Create table using the new default model
        templatesTable.setModel(noEditTableModel);

        // get the list of saved templates
        ArrayList<Template> templatesList = Template.getTemplatesList();
        // add the rows
        model = (DefaultTableModel) templatesTable.getModel();

        model.setRowCount(0);
        for(Template template : templatesList) {
            model.addRow(new Object[]{
                    template.getTemplate_id(),
                    template.getName(),
                    template.getSubject(),
                    template.getTextBody(),
            });
        }
    }

    @Override
    public JPanel getRootPanel() { return rootPanel; }
}
