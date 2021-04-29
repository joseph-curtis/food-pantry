package data;
import logic.Message;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.exit;

//https://github.com/PCC-CIS-234A/DBRoundTrip/blob/master/src/data/Database.java as template
public class Database {
    private static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/cis234a_team_JK_LOL";
    private static final String USERNAME = "cis234a_team_JK_LOL";
    private static final String PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";
    private static final String GET_ALL_MESSAGES_SQL = "SELECT subject, textbody, FK_FromPerson_ID, datetime FROM MESSAGE;";

    public static Connection connection = null;

    public static void connect(){
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

    public static ArrayList<Message> getAllMessages(){
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
