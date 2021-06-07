package data;

import logic.Message;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import static java.lang.System.exit;
import logic.Person;
import logic.Template;

/**
 * Database class for the data layer. Note that there is only one database, so all the properties
 * and methods are static, and the single connection object is shared across all calls to the
 * database methods.
 * @author Jack Dillon, Joseph Curtis
 * @version 2021.05.24
 */
public class Database {
    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/cis234a_team_JK_LOL";
    private static final String USERNAME = "cis234a_team_JK_LOL";
    private static final String PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";

    // SQL queries
    private static final String LOGIN_SQL
            = "SELECT PK_Person_ID, firstname, lastname, username, role, email" +
            " FROM PERSON" +
            " WHERE (role = 'Manager'" +
                "OR role = 'Worker')" +
            " AND username = ?" +
            " AND password_hash = HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), ?));";
    private static final String GET_ALL_MESSAGES_SQL
            = "SELECT subject, textbody, FK_FromPerson_ID, datetime" +
            "FROM MESSAGE;";
    private static final String GET_MESSAGES_BY_DATE_SQL
            = "SELECT PK_Message_ID, subject, textbody, datetime, firstname, lastname," +
                "COUNT(PK_Recipient_ID) AS RecCount\n" +
            "FROM MESSAGE JOIN PERSON ON FK_FromPerson_ID = PK_Person_ID\n" +
            "\tJOIN RECIPIENT ON FK_Message_ID = PK_Message_ID\n" +
            "WHERE datetime >= ? AND datetime <=   ?\n" +
            "GROUP BY PK_Message_ID, subject, textbody, datetime, firstname, lastname\n" +
            "ORDER BY datetime DESC;";
    private static final String GET_ALL_STUDENTS
            = "SELECT PK_Person_ID, firstname, lastname, email, phone, activated, receive_email, receive_sms" +
            " FROM PERSON " +
            " WHERE role = 'Student';";
    private static final String INSERT_MESSAGE
            = "INSERT INTO MESSAGE (FK_FromPerson_ID, subject, textbody, datetime)" +
            " VALUES (?, ?, ?, CAST(GETDATE() AS smalldatetime));";
    private static final String INSERT_RECIPIENTS
            = "INSERT INTO RECIPIENT (FK_ToPerson_ID, FK_Message_ID, to_email)" +
            " VALUES (?, ?, ?);";
    private static final String GET_ID
            = "SELECT @@IDENTITY AS PK_ID;";
    private static final String GET_TEMPLATE
            = "SELECT name, temp_subject, temp_body " +
            " FROM TEMPLATE " +
            " WHERE PK_Template_ID = ?;";
    private static final String GET_ALL_TEMPLATES
            = "SELECT PK_Template_ID, name, temp_subject, temp_body " +
            " FROM TEMPLATE;";
    private static final String INSERT_TEMPLATE
            = "INSERT INTO TEMPLATE (name, temp_subject, temp_body) " +
            " VALUES (?, ?, ?);";
    private static final String UPDATE_TEMPLATE
            = "UPDATE TEMPLATE " +
            " SET name = ?, temp_subject = ?, temp_body = ? " +
            " WHERE PK_Template_ID = ?;";

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
     * @return messages as an ArrayList
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
                messages.add(new Message(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("subject"),
                        rs.getString("textbody"),
                        rs.getString("datetime"),
                        rs.getInt("RecCount")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // close off connection
            close();
        }
        return messages;
    }

    public static Person login(String username, char[] password) {
        Person currentUser = null;
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(LOGIN_SQL);
            stmt.setString(1, username);
            stmt.setString(2,  String.valueOf(password));
            ResultSet rs = stmt.executeQuery();

            rs.next();
            currentUser = new Person(
                    rs.getInt("PK_Person_ID"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getString("email")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close off connection
            close();
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
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("activated"),
                        rs.getBoolean("receive_email"),
                        rs.getBoolean("receive_sms")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close off connection
            close();
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

            // INSERT records into RECIPIANT table
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
            System.out.println("Message saved successfully in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
            throw new RuntimeException("Error writing to database: " + e);
        } finally {
            // close off connection
            close();
        }
    }

    public static Template getTemplate(int templateID) {
        Template template = null;
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(GET_TEMPLATE);
            stmt.setInt(1, templateID);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            template = new Template(
                    templateID,
                    rs.getString("name"),
                    rs.getString("temp_subject"),
                    rs.getString("temp_body")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close off connection
            close();
        }
        return template;
    }

    public static ArrayList<Template> getAllTemplatesList() {
        ArrayList<Template> templateList = new ArrayList<>();
        connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(GET_ALL_TEMPLATES);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                templateList.add(new Template(
                        rs.getInt("PK_Template_ID"),
                        rs.getString("name"),
                        rs.getString("temp_subject"),
                        rs.getString("temp_body")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close off connection
            close();
        }
        return templateList;
    }

    public static void saveTemplate(String name, String temp_subject, String temp_body)
            throws RuntimeException {
        connect();

        try (PreparedStatement saveTemplate = connection.prepareStatement(INSERT_TEMPLATE)) {

            // start transaction
            connection.setAutoCommit(false);

            saveTemplate.setString(1, name);
            saveTemplate.setString(2, temp_subject);
            saveTemplate.setString(3, temp_body);
            saveTemplate.executeUpdate();

            // end transaction & commit
            connection.commit();
            System.out.println("Template saved successfully in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
            throw new RuntimeException("Error writing to database: " + e);
        } finally {
            // close off connection
            close();
        }
    }

    public static void editTemplate(int templateID, String name, String temp_subject, String temp_body) {
        connect();

        try (PreparedStatement updateTemplate = connection.prepareStatement(UPDATE_TEMPLATE)) {

            // start transaction
            connection.setAutoCommit(false);

            updateTemplate.setString(1, name);
            updateTemplate.setString(2, temp_subject);
            updateTemplate.setString(3, temp_body);
            updateTemplate.setInt(4, templateID);
            updateTemplate.executeUpdate();

            // end transaction & commit
            connection.commit();
            System.out.println("Template updated successfully in the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (!connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException except) {
                except.printStackTrace();
            }
            throw new RuntimeException("Error writing to database: " + e);
        } finally {
            // close off connection
            close();
        }
    }

    /**
     * helper class to close connection leak
     */
    static public void close() {

        try {
            if (!connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
