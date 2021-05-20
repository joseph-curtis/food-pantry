package presentation;

import logic.User;

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
                    User.login(username, password);
                }

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
