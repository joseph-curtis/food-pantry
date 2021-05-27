package main;

import logic.Person;
import presentation.*;

import javax.swing.*;

public class Controller {
    private static Person currentUser = null;
    private static JFrame windowFrame = null;

    public static void start() {
        // login form
        windowFrame = new JFrame("Staff User Login");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showLogin();
    }

    public static void showLogin() {
        windowFrame.getContentPane().removeAll();
        windowFrame.getContentPane().add(new LoginForm().getRootPanel());
        windowFrame.pack();
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setVisible(true);
    }

    public static void showUI() {
        // double checks to ensure setUser was called before this:
        if (currentUser == null) {
            JOptionPane.showMessageDialog(windowFrame.getRootPane(),
                    "User was not authenticated!\n" +
                            "Call setUser(user) BEFORE calling showUI()"
                    ,"USER NOT LOGGED IN", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: clear the current login window
        windowFrame.getContentPane().removeAll();

        // Create a JFrame to show the tabbed GUI
        windowFrame = new JFrame("Panther Pantry Notification Manager");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TabbedPaneForm tabbedPanel = new TabbedPaneForm();
        JTabbedPane tabbedPane = tabbedPanel.getTabbedPane();

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
                new ViewTemplatesForm().getRootPanel(),
                "Create or edit notification templates");
        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "View Notification Log" +
                        "</td></tr></table></body></html>",
                null,
                new NotificationLog().getRootPanel(),
                "See old messages sent");

        showForm(tabbedPanel, windowFrame);
    }

    /**
     * authenticate user, save as Person variable
     * @param user the current user that is logging in
     */
    public static void setUser(Person user) {
        currentUser = user;
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
