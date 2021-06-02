<?php
/*
File Name: regConfirmationPage.php
Last Edited: 06/02/2021
Author: Katie Pundt
*/
require_once 'Database.php';
require_once 'utilities.php';

require_secure();
Database::update_activated();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>PCC Food Pantry | Activation Confirmation</title>
    <!-- Reset CSS -->
    <link href="css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link href="css/pantherPantry.css" rel="stylesheet">
</head>
<body>
<h1>Panther Pantry Activation Confirmation</h1>
<nav>
    <a href="../../web/pantryHome.html">Home</a>
    <a href="../../web/loginForm.php">Login</a>
</nav>
<div id="wrapper">
    <p class="output">Thanks for activating your account! Please login to select your notification preferences.</p>
</div>
</body>
</html>
