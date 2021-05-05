package data;
/**
 * @author Jack Dillon
 * @version 05.05.21
 */
import logic.Message;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

import static java.lang.System.exit;


public class Database {
    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/cis234a_team_JK_LOL";
    private static final String USERNAME = "cis234a_team_JK_LOL";
    private static final String PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";
    private static final String GET_ALL_MESSAGES_SQL = "SELECT subject, textbody, FK_FromPerson_ID, datetime FROM MESSAGE;";
    private static final String GET_MESSAGES_BY_DATE_SQL = "SELECT subject, textbody, FK_FromPerson_ID, datetime FROM MESSAGE WHERE datetime > ? AND datetime < ?;";

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

}
