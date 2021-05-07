package data;
/**
 * @author Jack Dillon
 * @version 05.05.21
 */

/**
 * Database class for the data layer. Note that there is only one database, so all the properties
 * and methods are static, and the single connection object is shared across all calls to the
 * database methods.
 * NOTES:  5.5.2021 fixed HASHBYTES issue.
 */

//https://github.com/PCC-CIS-234A/DBRoundTrip/blob/master/src/data/Database.java as template

import logic.Message;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import static java.lang.System.exit;
import logic.Person;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/cis234a_team_JK_LOL";
    private static final String USERNAME = "cis234a_team_JK_LOL";
    private static final String PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";
    private static final String GET_ALL_MESSAGES_SQL = "SELECT subject, textbody, FK_FromPerson_ID, datetime FROM MESSAGE;";
    private static final String GET_MESSAGES_BY_DATE_SQL = "SELECT subject, textbody, FK_FromPerson_ID, datetime FROM MESSAGE WHERE datetime > ? AND datetime < ?;";

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
    private static final String INSERT_MESSAGE
            = "INSERT INTO MESSAGE (FK_FromPerson_ID, subject, textbody, datetime)" +
            " VALUES (?, ?, ?, CAST(GETDATE() AS smalldatetime));";
    private static final String INSERT_RECIPIENTS
            = "INSERT INTO RECIPIENT (FK_ToPerson_ID, FK_Message_ID, to_email)" +
            " VALUES (?, ?, ?);";
    private static final String GET_ID = "SELECT @@IDENTITY AS PK_Message_ID;";


    // The one and only connection object
    public static Connection connection = null;

    /**
     * Creates database connection
     */
    public static void connect() {
        if (connection != null){
            return;
        } else {
            try {
                connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME,PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                exit(-1);
            }
        }

    }

    /**
     * Retrieves messages that were written between a set of days
     * @param startDate
     * @param endDate
     * @return messages
     */
    public static ArrayList<Message> getMessagesByDate(Date startDate, Date endDate) {
        connect();
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(GET_MESSAGES_BY_DATE_SQL);
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                messages.add(new Message(rs.getString("subject"),
                        rs.getString("textbody"),
                        rs.getString("FK_FromPerson_ID"),
                        rs.getString("datetime")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    /**
     * @deprecated
     */
    public static ArrayList<Message> getAllMessages() {
        connect();
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(GET_ALL_MESSAGES_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                messages.add(new Message(rs.getString("subject"),
                        rs.getString("textbody"),
                        rs.getString("FK_FromPerson_ID"),
                        rs.getString("datetime")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public static Person authenticateStaffUser(String username, String password) {
        Person currentUser = null;
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(STAFF_USER_AUTH);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            currentUser = new Person(
                    rs.getInt("PK_Person_ID"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email")
            );
            // close up connection leak
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
        } finally {
            connection = null;
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
            // close up connection leak
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
        } finally {
            connection = null;
        }
        return studentList;
    }

    public static void saveMessage(int fromPersonID
            , ArrayList<Person> recipients
            , String subject
            , String textBody) throws RuntimeException {
        int messageID = -1;
        connect();

        try (PreparedStatement insertMessage = connection.prepareStatement(INSERT_MESSAGE);
             PreparedStatement getMessageID = connection.prepareStatement(GET_ID);
             PreparedStatement insertRecipients = connection.prepareStatement(INSERT_RECIPIENTS)) {

            // start transaction
            connection.setAutoCommit(false);

            insertMessage.setInt(1, fromPersonID);
            insertMessage.setString(2, subject);
            insertMessage.setString(3, textBody);
            insertMessage.executeUpdate();

            ResultSet rs = getMessageID.executeQuery();
            rs.next();
            messageID = rs.getInt(1);

            if (messageID>0) {
                for (Person recipient : recipients) {
                    insertRecipients.setInt(1, recipient.getID());
                    insertRecipients.setInt(2, messageID);
                    insertRecipients.setString(3, recipient.getEmail());
                    insertRecipients.executeUpdate();
                }
            } else {
                connection.rollback();;
                throw new RuntimeException("invalid PK_Message_ID returned == " + messageID);
            }
            // end transaction & commit
            connection.commit();
            connection.close();
            System.out.println("Message saved successfully in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.rollback();;
                    connection.close();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
            throw new RuntimeException("Error writing to database: " + e);
        } finally {
            connection = null;
        }
    }
}
