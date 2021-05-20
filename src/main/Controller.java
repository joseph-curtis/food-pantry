package main;

import logic.User;
import presentation.LoginForm;
import presentation.NotificationLog;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private static User mUser = null;
    private static JFrame mFrame = null;

    public static void start() {
        createGUI();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createGUI() {
        mFrame = new JFrame("Send Notification Frame");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showLogin();
    }

    public static void showLogin() {
        mFrame.getContentPane().removeAll();
        mFrame.getContentPane().add(new LoginForm().getRootPanel());
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
    }

    public static void showUI(){
        mFrame.getContentPane().removeAll();
        mFrame.getContentPane().add(new NotificationLog().getRootPanel());
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
    }

    public static void login() {
        showUI();
    }

    public static void setUser(User user) {
        mUser = user;
    }
}
