package main;

import data.Database;
import logic.Person;
import presentation.SendNotificationForm;

import javax.swing.*;

public class Controller {
    // get currently logged in user (username hardcoded for now)
    private static final Person currentUser = Person.getCurrentUser("testmanager");

    public static void start() {
        createGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createGUI() {
        SendNotificationForm ui = new SendNotificationForm(currentUser);
        JPanel root = ui.getRootPanel();

        JFrame frame = new JFrame("Send Notification Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
