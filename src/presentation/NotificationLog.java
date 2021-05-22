package presentation;
/**
 * @author Jack Dillon
 * @version 05.05.21
 */
import com.toedter.calendar.JDateChooser;
import logic.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.sql.Date;

public class NotificationLog extends GUIForm{
    private JPanel rootPanel;
    private JTable notificationsTable;
    private JButton go;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private static String format;
    private Date startDate;
    private Date endDate;

    /**
     * Instantiates the table and date choosers. When the go button is pressed it shows the notification log
     */
    public NotificationLog() {
        format = "MM-dd-yy";
        createTable();
        createUIComponents();
        go.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNotificationLog();
            }
        });
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Retrieves old messages in order to display in the table
     */
    private void showNotificationLog() {
        ArrayList<Message> messages = Message.getMessagesByDate(startDate, endDate);
        DefaultTableModel model = (DefaultTableModel) notificationsTable.getModel();

        model.setRowCount(0);
        for(Message message : messages){

            model.addRow(new Object[]{
                message.getSubject(),
                message.getTextBody(),
                message.getFromPersonId(),
                message.getDateTime().formatted(format)
            });
        }
    }

    /**
     * Creates table using the default model.
     */
    private void createTable() {
        notificationsTable.setModel(new DefaultTableModel(
                null,
                new String[] {"Subject", "Notification", "Sender", "Date Sent"}
        ));
    }

    /**
     * Instantiates the java bean date choosers. Adds an event listener to retrieve day of the user's choice.
     * End date chooser adds on 23:59:59 so it will encompass the entire day
     */
    private void createUIComponents() {
         this.startDateChooser = new JDateChooser();
         this.startDateChooser.setDateFormatString(format);
         this.endDateChooser = new JDateChooser();
         this.endDateChooser.setDateFormatString(format);

        startDateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    java.util.Date date = (java.util.Date) evt.getNewValue();
                    if(date != null){
                        startDate = utilDateToSQLDate(date);
                    }
                }
            }
        });

        endDateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    java.util.Date date = (java.util.Date) evt.getNewValue();
                    if(date != null){
                        date.setTime(date.getTime() + 86399000);
                        endDate = utilDateToSQLDate(date);
                    }
                }
            }
        });
    }

    /**
     * Converts utility Date to SQL date
     * @param java.util.Date d
     * @return java.sql.Date new Date
     */
    public Date utilDateToSQLDate(java.util.Date d) {
        return new Date(d.getTime());
    }
}
