<?php
/*
File Name: Database.php
Last Edited: 06/01/2021
Author: Katie Pundt
*/
session_start();

class Database
{
    // database credentials
    const DB_SERVER = "cisdbss.pcc.edu";
    const DB_NAME = "cis234a_team_JK_LOL";
    const DB_USERNAME = "cis234a_team_JK_LOL";
    const DB_PASSWORD = "Cis234A_Team_JK_lOl_Spring_21_&(%";

    // SQL statements
    const CREATE_NEW_ACCT_SQL = "INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role, phone, activated, receive_email, receive_sms) VALUES (:firstname, :lastname, :username, (SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password))) , :email, 'Student', :phone, 0, 1, :receive_sms);";
    const GET_USERNAME_SQL = "SELECT * FROM PERSON WHERE username = :username;";
    const GET_EMAIL_SQL = "SELECT email FROM PERSON WHERE username = :session_username;";
    const GET_PHONE_SQL = "SELECT phone FROM PERSON WHERE username = :session_username;";
    const GET_EMAIL_CHECKBOX_SQL = "SELECT receive_email FROM PERSON WHERE username = :session_username;";
    const GET_SMS_CHECKBOX_SQL = "SELECT receive_sms FROM PERSON WHERE username = :session_username;";
    const LOGIN_SQL = "SELECT * FROM PERSON WHERE password_hash = HASHBYTES('SHA2_256', :password);";
    const UPDATE_EMAIL_SQL = "UPDATE PERSON SET email = :email WHERE username = :session_username;";
    const UPDATE_CELL_PHONE_SQL = "UPDATE PERSON SET phone = :phone WHERE username = :session_username;";
    const UPDATE_PASSWORD_SQL = "UPDATE PERSON SET password_hash = (SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password))) WHERE username = :session_username;";
    const UPDATE_NOTIFICATION_EMAIL_SQL = "UPDATE PERSON SET receive_email = 1, receive_sms = 0 WHERE username = :session_username;";
    const UPDATE_NOTIFICATION_SMS_SQL = "UPDATE PERSON SET receive_email = 0, receive_sms = 1 WHERE username = :session_username;";
    const UPDATE_NOTIFICATION_BOTH_SQL = "UPDATE PERSON SET receive_email = 1, receive_sms = 1 WHERE username = :session_username;";
    const UPDATE_NOTIFICATION_OPT_OUT_SQL = "UPDATE PERSON SET receive_email = 0, receive_sms = 0 WHERE username = :session_username;";
    const UPDATE_ACTIVATED_SQL = "UPDATE PERSON SET activated = 1 WHERE username = :session_username;";
    const GET_ACTIVATED_STATUS_SQL = "SELECT activated FROM PERSON WHERE username = :session_username";


    private static $db = NULL;

    // connect to database
    private static function connect() {
        if (empty(Database::$db)) {
            Database::$db = new PDO(
                "sqlsrv:Server=" . Database::DB_SERVER .";Database=" . Database::DB_NAME, Database::DB_USERNAME, Database::DB_PASSWORD
            );
        }
    }

    // create new account
    public static function create_account($firstName, $lastName, $username, $password, $email, $cellPhone, $receive_sms)
    {
        Database::connect();
        // prepare query
        $stmt = Database::$db->prepare(Database::CREATE_NEW_ACCT_SQL);

        // set parameters and execute
        $stmt->execute([
            ":firstname" => $firstName,
            ":lastname" => $lastName,
            ":username" => $username,
            ":password" => $password,
            ":email" => $email,
            ":phone" => $cellPhone,
            ":receive_sms" => $receive_sms
        ]);

        // fetch results
        $users = $stmt->fetchAll(PDO::FETCH_ASSOC);
        $usersList =[];
        foreach ($users as $user) {
            $usersList[] = new User($user);
        }

        return $usersList;

    }

    // check that username is not already in use
    public static function duplicate_account($username)
    {
        Database::connect();

        // prepare and execute query
        $stmt = Database::$db->prepare(Database::GET_USERNAME_SQL);
        $stmt->execute([":username" => $username]);

        // fetch results
        $results = $stmt->fetchAll(PDO::FETCH_ASSOC);

        foreach ($results as $res) {
            if ($res["username"] == $username) {
                return TRUE; // the account already exists
            } else {
                return FALSE;
            }
        }
    }

    // login
    public static function check_login($username, $password)
    {
        Database::connect();

        // prepare and execute query
        $stmt = Database::$db->prepare(Database::LOGIN_SQL);
        $stmt->execute([":password" => $password]);

        // fetch results
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

        foreach ($result as $res) {
            if ($res["username"] == $username) {
                return TRUE;
            }
        }
        return FALSE;
    }

    // receive only email notifications
    public static function email_notification()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_NOTIFICATION_EMAIL_SQL);

        // set parameters and execute
        $stmt->execute([
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // receive only sms notifications
    public static function sms_notification()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_NOTIFICATION_SMS_SQL);

        // set parameters and execute
        $stmt->execute([
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // receive both email and sms notifications
    public static function both_notification()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_NOTIFICATION_BOTH_SQL);

        // set parameters and execute
        $stmt->execute([
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // opt out of notifications
    public static function opt_out()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_NOTIFICATION_OPT_OUT_SQL);

        // set parameters and execute
        $stmt->execute([
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // update email address
    public static function update_email($email)
    {
        Database::connect();

        if (!empty($_POST["email"])) {
            // prepare query
            $stmt = Database::$db->prepare(Database::UPDATE_EMAIL_SQL);

            // set parameters and execute
            $stmt->execute([
                ":email" => $email,
                ":session_username" => $_SESSION["session_username"]
            ]);
        }
        return $email;

    }

    // update cell phone number
    public static function update_phone($cellPhone)
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_CELL_PHONE_SQL);

        if ($cellPhone == FALSE) {
            $cellPhone = NULL;
        }

        $stmt->execute([
        ":phone" => $cellPhone,
        ":session_username" => $_SESSION["session_username"]
        ]);

        return $cellPhone;
    }

    // update password
    public static function update_password($password)
    {
        Database::connect();

        if ((!empty($_POST["password"])) && $_POST["password"] == $_POST["confirmPassword"]) {
            // prepare query
            $stmt = Database::$db->prepare(Database::UPDATE_PASSWORD_SQL);

            // set parameters and execute
            $stmt->execute([
                ":password" => $password,
                ":session_username" => $_SESSION["session_username"]
            ]);
        }
    }

    // update preferences
    public static function update_preferences()
    {
        if (!empty($_POST['receive_email'])) {
            Database::email_notification();
        }
        if (!empty($_POST['receive_sms']))  {
            Database::sms_notification();
        }
        if (!empty($_POST['receive_email']) && (!empty($_POST['receive_sms']))) {
            Database::both_notification();
        }
        if (empty($_POST['receive_email']) && (empty($_POST['receive_sms']))) {
            Database::opt_out();
        }
    }

    public static function get_email()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::GET_EMAIL_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

        // fetch results
        $results = $stmt->fetch(PDO::FETCH_OBJ);
        print $results->email;

    }

    public static function get_phone()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::GET_PHONE_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

        // fetch results
        $results = $stmt->fetch(PDO::FETCH_OBJ);
        print $results->phone;

    }

    public static function get_email_checkbox()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::GET_EMAIL_CHECKBOX_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

        // fetch results
        $results = $stmt->fetch(PDO::FETCH_OBJ);

        if ($results->receive_email == 1) {
            print 'checked';
        } else {
            print '';
        }

    }

    public static function get_sms_checkbox()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::GET_SMS_CHECKBOX_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

        // fetch results
        $results = $stmt->fetch(PDO::FETCH_OBJ);

        if ($results->receive_sms == 1) {
            print 'checked';
        } else {
            print '';
        }

    }

    // update activation status
    public static function update_activated()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_ACTIVATED_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

    }

    // get activation status
    public static function get_activated_status()
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::GET_ACTIVATED_STATUS_SQL);

        // set parameters and execute
        $stmt->execute([":session_username" => $_SESSION["session_username"]]);

        // fetch results
        $results = $stmt->fetch(PDO::FETCH_OBJ);

        if ($results->activated == 1) {
            return TRUE;
        } else {
            return FALSE;
        }

    }

}
