<?php
/*
File Name: settingsConfirmationPage.php
Last Edited: 05/30/2021
Author: Katie Pundt
*/
require_once 'Database.php';
require_once 'utilities.php';
require_secure();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>PCC Food Pantry | Settings Confirmation</title>
    <!-- Reset CSS -->
    <link href="css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link href="css/pantherPantry.css" rel="stylesheet">
</head>
<body>
<h1>Account Settings</h1>
<nav>
    <a href="../../web/pantryHome.html">Home</a>
    <a href="../../web/assets/logout.php">Logout</a>
    <a href="../../web/registerForm.php">Create New Account</a>
</nav>
<div id="wrapper">
    <p class="output"><?php

        $email = $_POST["email"];
        $cellPhone = $_POST["cellPhone"];
        $password = $_POST["password"];
        $confirmPassword = $_POST["confirmPassword"];
        // update settings
        Database::update_preferences();
        Database::update_email($email);
        Database::update_phone($cellPhone);
        if ($_POST["password"] != $_POST["confirmPassword"]) {
            echo 'Passwords must match!';
        } else {
            Database::update_password($password);
            echo 'Your settings have been updated!';
        }
        ?></>
</div>
</body>
</html>

