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
    private JPasswordField passwordField;
    private JButton loginbutton;
    private String username;
    private char[] password;

    public LoginForm()  {
        rootPanel.setPreferredSize(new Dimension(400, 500));

        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username = usernameField.getText();
                password = passwordField.getPassword();
                if(usernameField.getText().isEmpty() || passwordField.getPassword().length <1){
                    JOptionPane.showMessageDialog(null, "Please Enter a Name and Password");
                } else {
                    Person currentUser = Person.authenticateStaffUser(username, password);

                    if(currentUser == null) {
                        System.out.println("User not found, need an error dialog.");
                    }
                    if(currentUser != null) {
                        System.out.println("User " + currentUser.getFirstName() + " logged in successfully.");
                        Controller.setPerson(currentUser);
                        Controller.close();
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
