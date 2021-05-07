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
                Controller.start();
            }
        });
    }
}
