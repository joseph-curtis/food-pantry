package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import logic.Message;

import java.util.ArrayList;

public class NotificationLog {
    private JPanel rootPanel;
    private JTable notificationsTable;
    //private JComboBox startPicker; //see if datepicker bean works for these
    //private JComboBox endPicker; //Look up how to integrate java beans
    private JButton go;
    private JDateChooser JDateChooser1;
    private JDateChooser JDateChooser2;

    public NotificationLog() {
        createTable();
        createUIComponents();
        ArrayList<Message> messages = Message.getAllMessages();
        for(Message message : messages){
            System.out.println(message);
        }

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
         this.JDateChooser1 = new JDateChooser();
         this.JDateChooser2 = new JDateChooser();
    }
}
