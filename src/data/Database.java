package data;

import logic.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database class for the data layer. Note that there is only one database, so all the properties
 * and methods are static, and the single connection object is shared across all calls to the
 * database methods.
 * NOTES:  4.28.2021 cannot select record with password_hash = HASHBYTES('SHA2_256', 'password')
 *   should look into issue later.
 */
//https://github.com/PCC-CIS-234A/DBRoundTrip/blob/master/src/data/Database.java as template
public class Database {
    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/cis234a_team_JK_LOL";
    private static final String USERNAME = "cis234a_team_JK_LOL";
    private static final String PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";

    // SQL queries
    private static final String STAFF_USER_AUTH
            = "SELECT PK_Person_ID, firstname, lastname, email" +
            " FROM PERSON" +
            " WHERE (role = 'Manager' OR role = 'Worker')" +
            " AND username = ?" +
            " AND password_hash = HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), ?));";
    private static final String GET_ALL_STUDENTS
            = "SELECT PK_Person_ID, firstname, lastname, email" +
            " FROM PERSON" +
            " WHERE role = 'Student'";
    private static final String SAVE_MESSAGE
            = "INSERT INTO MESSAGE (FK_FromPerson_ID, subject, textbody, datetime)" +
            " VALUES (?, ?, ?, , CAST(GETDATE() AS smalldatetime));" +
            "GO" +
            "SELECT @@IDENTITY AS 'message_id';";
    private static final String SAVE_RECIPIENT
            = "INSERT INTO RECIPIENT (FK_ToPerson_ID, FK_Message_ID, to_email)" +
            " VALUES (?, ?, ?);";


    // The one and only connection object
    public static Connection connection = null;

    public static void connect(){
        if (connection != null){
            return;
        } else {
            try {
                // Create a database connection with the given username and password.
                connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error! Couldn't connect to the database!");
            }
        }
    }

    public static Person authenticateStaffUser(String username, String password) {
        Person currentUser = null;
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(STAFF_USER_AUTH);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                currentUser = new Person(
                        rs.getInt("PK_Person_ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
    }

    public static ArrayList<Person> getStudentList() {
        ArrayList<Person> studentList = new ArrayList<>();
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(GET_ALL_STUDENTS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                studentList.add(new Person(
                        rs.getInt("PK_Person_ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static Integer saveMessage(logic.Message message) {
        Integer messageID = -1;
        //blah
        return messageID;
    }

    public static Boolean saveRecipients(ArrayList<Person> recipients) {
        //blah
        return true;
    }
}
