package main;
/**
 * @author Jack Dillon
 * @version 05.05.21
 */
import presentation.NotificationLog;
//import presentation.GUIForm;
//import presentation.TabbedPaneForm;

import javax.swing.*;

public class Main {
    /**
     * Main method. Every program has to start somewhere.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    /**
     * Instantiates the UI
     */
    private static void createGUI() {
        NotificationLog ui = new NotificationLog();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame("Panther Pantry Electronic Notification System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
