package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class NotificationLog {
    private JPanel rootPanel;
    private JTable notificationsTable;
    //private JComboBox startPicker; //see if datepicker bean works for these
    //private JComboBox endPicker; //Look up how to integrate java beans
    private JButton go;
    //private JCalendar JCalendar2;
    //private JCalendar JCalendar1;
    private JDateChooser JDateChooser1;
    private JDateChooser JDateChooser2;

    public NotificationLog() {
        createTable();

    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable() {
        Object[][] data = {};
        notificationsTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Table title"}
        ));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
         JDateChooser1 = new JDateChooser();
         JDateChooser2 = new JDateChooser();
    }
}
