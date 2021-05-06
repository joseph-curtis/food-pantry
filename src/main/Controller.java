package main;

import logic.Person;
import presentation.SendNotificationForm;

import javax.swing.*;

public class Controller {
    private static String staffUsername = "cosmo.spacely";
    private static String staffPassword = "managerpassword";
    // get currently logged in user (username/password hardcoded for now)
    private static final Person currentUser = Person.authenticateStaffUser(
            staffUsername, staffPassword);

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

        if (currentUser == null) {
            JOptionPane.showMessageDialog(root, staffUsername + " failed authentication!\n" +
                    "Check Database for record, and ensure username/passwords match!");
        }
    }
}
