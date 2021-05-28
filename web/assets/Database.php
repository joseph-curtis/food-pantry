<?php
/*
File Name: Database.php
Last Edited: 05/27/2021
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
    const CREATE_NEW_ACCT_SQL = "INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role, phone) VALUES (:firstname, :lastname, :username, (SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password))) , :email, 'Student', :phone)";
    const GET_USERNAME_SQL = "SELECT * FROM PERSON WHERE username = :username";
    const LOGIN_SQL = "SELECT username, password_hash FROM PERSON WHERE password_hash = HASHBYTES('SHA2_256', :password);";
    const UPDATE_EMAIL_SQL = "UPDATE PERSON SET email = :email WHERE username = :session_username";
    const UPDATE_CELL_PHONE_SQL = "UPDATE PERSON SET phone = :phone WHERE username = :session_username";
    const UPDATE_PASSWORD_SQL = "UPDATE PERSON SET password_hash = (SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password))) WHERE username = :session_username";
    const UPDATE_NOTIFICATION_EMAIL_SQL = "UPDATE SETTINGS SET email = 1, sms = 0, both = 0, opt_out = 0 FROM	SETTINGS JOIN PERSON ON SETTINGS.FK_Person_ID = PERSON.PK_Person_ID WHERE username = :session_username";
    const UPDATE_NOTIFICATION_SMS_SQL = "UPDATE SETTINGS SET email = 0, sms = 1, both = 0, opt_out = 0 FROM	SETTINGS JOIN PERSON ON SETTINGS.FK_Person_ID = PERSON.PK_Person_ID WHERE username = :session_username";
    const UPDATE_NOTIFICATION_BOTH_SQL = "UPDATE SETTINGS SET email = 0, sms = 0, both = 1, opt_out = 0 FROM	SETTINGS JOIN PERSON ON SETTINGS.FK_Person_ID = PERSON.PK_Person_ID WHERE username = :session_username";
    const UPDATE_NOTIFICATION_OPT_OUT_SQL = "UPDATE SETTINGS SET email = 0, sms = 0, both = 0, opt_out = 1 FROM	SETTINGS JOIN PERSON ON SETTINGS.FK_Person_ID = PERSON.PK_Person_ID WHERE username = :session_username";

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
    public static function create_account($firstName, $lastName, $username, $password, $email, $cellPhone)
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
            ":phone" => $cellPhone
        ]);

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

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_EMAIL_SQL);

        // set parameters and execute
        $stmt->execute([
            ":email" => $email,
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // update cell phone number
    public static function update_phone($cellPhone)
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_CELL_PHONE_SQL);

        $stmt->execute([
            ":phone" => $cellPhone,
            ":session_username" => $_SESSION["session_username"]
        ]);


    }

    // update password
    public static function update_password($password)
    {
        Database::connect();

        // prepare query
        $stmt = Database::$db->prepare(Database::UPDATE_PASSWORD_SQL);

        // set parameters and execute
        $stmt->execute([
            ":password" => $password,
            ":session_username" => $_SESSION["session_username"]
        ]);
    }

    // update preferences
    public static function update_preferences()
    {
        $radioVal = $_POST['notifications'];

        if($radioVal == 'emailOnly') {
            Database::email_notification();
        } elseif ($radioVal == 'smsOnly') {
            Database::sms_notification();
        } elseif ($radioVal == 'both') {
            Database::both_notification();
        } elseif ($radioVal == 'optOut') {
            Database::opt_out();
        } else {
            exit();
        }

    }

    // update contact info
    public static function update_contact_info()
    {
        $email = $_POST["email"];
        $cellPhone = $_POST["cellPhone"];
        $password = $_POST["password"];

        if (!empty($_POST["email"])) {
            Database::update_email($email);
        } if (!empty($_POST["cellPhone"])) {
        Database::update_phone($cellPhone);
        } if (!empty($_POST["password"]) && !empty($_POST["passwordConfirm"])) {
            Database::update_password($password);
        } else {
            exit();
        }
    }

}
