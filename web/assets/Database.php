<?php
/*
File Name: Database.php
Last Edited: 05/19/2021
Author: Katie Pundt
*/

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
        // prepare query and bind parameters
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
}