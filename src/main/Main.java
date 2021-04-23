package main;

import presentation.SendNotificationForm;

import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createGUI() {
        SendNotificationForm ui = new SendNotificationForm();
        JPanel root = ui.getRootPanel();

        JFrame frame = new JFrame("Send Notification Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}