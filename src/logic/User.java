package logic;

import data.Database;
import main.Controller;

@Deprecated
public class User {
    private int mUserID;
    private String mUsername;
    private String mRole;

    public User(int userID, String username, String role) {
        mUserID = userID;
        mUsername = username;
        mRole = role;
    }

    /**
     * some logic to verify that the user is a legitimate staff member
     * @param em
     * @param pwd
     * @return a Person class
     */
//    public static User login(String em, String pwd) {
//        User user = Database.login(em, pwd);
//        return user;
//    }

    public int getUserID() {
        return mUserID;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getRole() {
        return mRole;
    }
}