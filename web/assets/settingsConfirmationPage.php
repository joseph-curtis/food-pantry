<?php
/*
File Name: settingsConfirmationPage.php
Last Edited: 05/27/2021
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
        echo 'Your settings have been updated!';

        // if a notifications radio button is selected, update the database
        if (isset($_POST['notifications'])) {
            Database::update_preferences();
        } elseif (!empty($_POST["email"]) || (!empty($_POST["cellPhone"])) || (!empty($_POST["password"]))) {
            Database::update_contact_info();
        } else {
            exit();
        }


        ?></>
</div>
</body>
</html>

