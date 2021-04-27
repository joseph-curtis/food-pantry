<?php
/*
File Name: regConfirmationPage.php
Last Edited: 04/27/2021
Author: Katie Pundt
*/

// check that fields are set, if they are connect to database
if (isset($_POST["first"]) &&
    isset($_POST["last"]) &&
    isset($_POST["username"]) &&
    isset($_POST["email"]) &&
    isset($_POST["password"]) &&
    isset($_POST["cellPhone"])) {

    // database credentials 
    $server = "cisdbss.pcc.edu";
    $dbname = "cis234a_team_JK_LOL";
    $userName = "cis234a_team_JK_LOL";
    $password = "Cis234A_Team_JK_lOl_Spring_21_&(%";

    // connect to database
    $conn = new PDO("sqlsrv:Server=$server;Database=$dbname", $userName, $password);
    if ($conn) {
        echo "Successfully connected to MS SQL Server!";
    } else {
        echo "Connection failed!";
        die (print_r(sqlsrv_errors(), true));
    }

    // prepare sql and bind parameters using PDO
    $stmt = $conn->prepare("INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role, phone) VALUES(:firstname, :lastname, :username, HASHBYTES('SHA2_256', :password), :email, 'Student', :phone)");

    // set parameters and execute
    $firstName = $_POST["first"];
    $lastName = $_POST["last"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];
    $cellPhone = $_POST["cellPhone"];
    $rs = $stmt->execute([
        ":firstname" => $firstName,
        ":lastname" => $lastName,
        ":username" => $username,
        ":password" => $password,
        ":email" => $email,
        ":phone" => $cellPhone
    ]);
    print_r($stmt->errorInfo()) ;

    $conn = null;
}

?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>New Account Confirmation</title>
    </head>
    <body>
        <h1>New Account Confirmation</h1>
        <p>Thank you <?php echo $firstName . " " . $lastName;?> for registering for the PCC Panther Pantry!</p>
        <p>A confirmation email has been sent to <?php echo $email;?>.<br>
        You will begin receiving food pantry notifications within 24 hours.</p>
    </body>
</html>
