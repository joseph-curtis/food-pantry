<?php
/*
File Name: regConfirmationPage.php
Last Edited: 05/05/2021
Author: Katie Pundt
*/
require 'constants.php';

// check that fields are set, if they are connect to database
if (isset($_POST["first"]) &&
    isset($_POST["last"]) &&
    isset($_POST["username"]) &&
    isset($_POST["email"]) &&
    isset($_POST["password"]) &&
    isset($_POST["cellPhone"])) {

    // connect to database
    $conn = new PDO("sqlsrv:Server=" . DB_SERVER .";Database=" . DB_NAME, DB_USERNAME, DB_PASSWORD);

    // prepare sql and bind parameters using PDO
    $stmt = $conn->prepare("INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role, phone) VALUES (:firstname, :lastname, :username, (SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password))) , :email, 'Student', :phone)");

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

    $conn = null;
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>New Account Confirmation</title>
    <!-- Reset CSS -->
    <link href="css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link href="css/pantherPantry.css" rel="stylesheet">
</head>
<body>
    <h1>New Account Confirmation</h1>
    <nav>
        <a href="../../web/pantryHome.html">Home</a>
    </nav>
    <div id="wrapper">
        <p class="output">Thank you <?php echo $firstName . " " . $lastName;?> for registering for the PCC Panther Pantry!</p>
        <p class="output">A confirmation email has been sent to <?php echo $email;?>.<br>
        You will begin receiving food pantry notifications within 24 hours.</p>
    </div>
</body>
</html>
