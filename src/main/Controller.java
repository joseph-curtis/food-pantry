package main;

import com.formdev.flatlaf.FlatLightLaf;
import logic.Person;
import presentation.*;
import presentation.LoginForm;
import presentation.NotificationLog;
import presentation.SendNotificationForm;
import presentation.TabbedPaneForm;


import javax.swing.*;
import java.awt.*;

public class Controller {
    private static Person currentUser = null;
    private static JFrame windowFrame = null;
    private static TabbedPaneForm tabbedPanel;
    private static JTabbedPane tabbedPane;
    private static ViewTemplatesForm templatesTab;


    public static void start() {
        // login form
        FlatLightLaf.install();

        tabbedPanel = new TabbedPaneForm();
        tabbedPane = tabbedPanel.getTabbedPane();
        templatesTab = new ViewTemplatesForm();

        windowFrame = new JFrame("Staff User Login");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showForm(new LoginForm(), windowFrame);
    }

    /**
     * clears the current window shown
     */
    public static void close() {
        windowFrame.dispose();
    }

    @Deprecated
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
        windowFrame.getContentPane().removeAll();

        // Create a JFrame to show the tabbed GUI
        windowFrame = new JFrame("Panther Pantry Notification Manager");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "Send Notification" +
                        "</td></tr></table></body></html>",
                null,
                new SendNotificationForm(currentUser).getRootPanel(),
                "Send a new email or text message");

        // add template tab as instanced variable
        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "Templates" +
                        "</td></tr></table></body></html>",
                null,
                templatesTab.getRootPanel(),
                "Create or edit notification templates");

        tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                        "View Notification Log" +
                        "</td></tr></table></body></html>",
                null,
                new NotificationLog().getRootPanel(),
                "See old messages sent");

        if (!currentUser.getRole().equals("Manager")) {
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setBackgroundAt(1, Color.gray);
        }

        showForm(tabbedPanel, windowFrame);
    }

    /**
     * Display the GUI interface
     * @param form to get the root panel
     * @param frame window to display
     */
    public static void showForm(GUIForm form, JFrame frame) {
        JPanel root = form.getRootPanel();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setLocationRelativeTo(root);
        frame.setVisible(true);
    }

    /**
     * authenticate user, save as Person variable
     * @param user the current user that is logging in
     */
    public static void setUser(Person user) {
        currentUser = user;
    }

    public static void updateTemplatesTab() {
        templatesTab.showTemplatesTable();
    }
}
