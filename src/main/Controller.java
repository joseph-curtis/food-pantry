package main;


import com.formdev.flatlaf.FlatDarkLaf;
import logic.Person;
import presentation.LoginForm;
import presentation.NotificationLog;
import presentation.SendNotificationForm;
import presentation.TabbedPaneForm;


import javax.swing.*;

public class Controller {
    private static Person currentUser = null;
    private static JFrame windowFrame = null;

    public static void start() {
        // login form
        FlatDarkLaf.install();
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

    public static void close(){
        windowFrame.dispose();
    }

    public static void showUI() {
        windowFrame.getContentPane().removeAll();

        // Create a JFrame to show our form in, and display the UsersTableGUI form.
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
                        "View Notification Log" +
                        "</td></tr></table></body></html>",
                null,
                new NotificationLog().getRootPanel(),
                "See old messages sent");

       // if(currentUser.getRole().equals("Manager")) {
            tabbedPane.addTab("<html><body><table><tr><td height='60'>" +
                            "Templates" +
                            "</td></tr></table></body></html>",
                    null,
                    null,
                    "Create or edit notification templates");
       // }
        showForm(tabbedPanel, windowFrame);


//        if (currentUser == null) {
//            JOptionPane.showMessageDialog(tabbedPanel.getRootPanel(), currentUser.getFirstName() + " failed authentication!\n" +
//                    "Check Database for record, and ensure username/passwords match!"
//                    ,"LOGIN ERROR", JOptionPane.ERROR_MESSAGE);
//        }
    }

    /**
     * authenticate user, save as Person variable
     * @param user the current user that is logging in
     */
    public static void setPerson(Person user) {
        currentUser = user;
    }

    /**
     * Display the GUI interface
     * @param form to get the root panel
     * @param frame window to display
     */
    public static void showForm(TabbedPaneForm form, JFrame frame) {
        JPanel root = form.getRootPanel();
        frame.getContentPane().add(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
