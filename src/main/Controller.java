package main;

import logic.Person;
import presentation.GUIForm;
import presentation.NotificationLog;
import presentation.SendNotificationForm;
import presentation.TabbedPaneForm;

import javax.swing.*;

public class Controller {
    private static String staffUsername = "cosmo.spacely";
    private static String staffPassword = "managerpassword";
    // get currently logged in user (username/password hardcoded for now)
    private static final Person currentUser = Person.authenticateStaffUser(
            staffUsername, staffPassword);

    public static void start() {
        // login form ----
        // authenticate user, save as Person variable

        createGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createGUI() {
        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        JFrame frame = new JFrame("Panther Pantry Notification Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabbedPaneForm tabbedPanel = new TabbedPaneForm();
        JTabbedPane tabbedPane = tabbedPanel.getTabbedPane();
        //tabbedPane.setBackground(new Color(193, 193, 193));
        //tabbedPane.setForeground(new Color(0, 128, 153));
        //tabbedPane.setFont(Font.getFont(String.valueOf(50)));
        //tabbedPane.setPreferredSize(new Dimension(100, 100));

        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "Send Notification" +
                        "</td></tr></table></body></html>",
                null,
                new SendNotificationForm(currentUser).getRootPanel(),
                "Send a new email or text message");
        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "Templates" +
                        "</td></tr></table></body></html>",
                null,
                null,
                "Create or edit notification templates");
        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "View Notification Log" +
                        "</td></tr></table></body></html>",
                null,
                new NotificationLog().getRootPanel(),
                "See old messages sent");

        showForm(tabbedPanel, frame);


        if (currentUser == null) {
            JOptionPane.showMessageDialog(tabbedPanel.getRootPanel(), staffUsername + " failed authentication!\n" +
                    "Check Database for record, and ensure username/passwords match!"
                    ,"LOGIN ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Display the GUI interface
     * @param form to get the root panel
     * @param frame window to display
     */
    public static void showForm(GUIForm form, JFrame frame) {
        JPanel root = form.getRootPanel();

        //frame.getContentPane().removeAll();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
