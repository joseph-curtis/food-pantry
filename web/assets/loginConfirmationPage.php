<?php
/*
File Name: loginConfirmationPage.php
Last Edited: 05/03/2021
Author: Katie Pundt
*/

function check_login($username, $password)
{
// database credentials
    $server = "cisdbss.pcc.edu";
    $dbname = "cis234a_team_JK_LOL";
    $dbusername = "cis234a_team_JK_LOL";
    $dbpassword = "Cis234A_Team_JK_lOl_Spring_21_&(%";

// connect to database
    $conn = new PDO("sqlsrv:Server=$server;Database=$dbname", $dbusername, $dbpassword);
    if ($conn) {
        // echo "Successfully connected to MS SQL Server!<br>";
    } else {
        echo "Connection failed!";
        die (print_r(sqlsrv_errors(), true));
    }

// prepare and execute query and fetch the results
// $stmt = $conn->prepare("SELECT * FROM PERSON WHERE username = :username AND password_hash = HASHBYTES('SHA2_256', :password);");


//$rs = $stmt->execute([
//    ":username" => $username,
//    ":password" => $password
//]);

    $stmt = $conn->prepare("SELECT username, password_hash FROM PERSON WHERE password_hash = HASHBYTES('SHA2_256', :password);");
    $rs = $stmt->execute([":password" => "Test123!"]);
    $err = $stmt->errorInfo();

// print results
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    // print_r($result);

    $conn = null;

    foreach ($result as $res) {
        if ($res["username"] == $username) {
            // echo "Logged in successfully as " . $username . "\n";
            return TRUE;
        }
    }
    return FALSE;
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Panther Pantry Login Confirmation</title>
</head>
<body>
<h1>Panther Pantry Login Confirmation</h1>
<p><?php
    $username = $_POST["username"];
    $password = $_POST["password"];
    $logged_in = check_login($username, $password);
    if($logged_in) {
        echo "Welcome back " . $username . "!";
    } else {
        echo "Login failed.";
    }
    ?></p>
</body>
</html>
