package presentation;

import logic.Person;
import main.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm {
    private JPanel rootPanel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginbutton;
    private String username;
    private String password;

    public LoginForm()  {
        rootPanel.setPreferredSize(new Dimension(400, 500));

        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username = usernameField.getText();
                password = passwordField.getText();
                if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please Enter a Name and Password");
                } else {
                    Person currentUser = Person.authenticateStaffUser(username, password);

                    if(currentUser == null) {
                        System.out.println("User not found, need an error dialog.");
                    }
                    if(currentUser != null) {
                        System.out.println("User " + currentUser.getFirstName() + " logged in successfully.");
                        Controller.setPerson(currentUser);
                        Controller.showUI();
                    } else {
                        System.out.println("Password incorrect, need an error dialog.");
                    }
                }

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
