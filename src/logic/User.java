package logic;

import data.Database;
import main.Controller;

public class User {
    private int mUserID;
    private String mUsername;
    private String mPassword;
    private String mRole;

    public User(int userID, String username, String password, String role) {
        mUserID = userID;
        mUsername = username;
        mPassword = password;
        mRole = role;
    }

    public static void login(String em, String pwd) {
        // some logic to verify that the user is the legitimate user.
        Database db = new Database();
        User user = db.login(em, pwd);


        if(user == null) {
            System.out.println("User not found, need an error dialog.");
            return;
        }
        if(user != null) {
            System.out.println("User " + user.getUserID() + " logged in successfully.");
            Controller.setUser(user);
            Controller.login();
        } else {
            System.out.println("Password incorrect, need an error dialog.");
        }
    }

    public int getUserID() {
        return mUserID;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getRole() {
        return mRole;
    }
}