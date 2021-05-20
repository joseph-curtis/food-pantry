<?php
/*
File Name: loginConfirmationPage.php
Last Edited: 05/19/2021
Author: Katie Pundt
*/
require_once 'Database.php';

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Panther Pantry Login Confirmation</title>
    <!-- Reset CSS -->
    <link href="css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link href="css/pantherPantry.css" rel="stylesheet">
</head>
<body>
    <h1>Panther Pantry Login Confirmation</h1>
    <nav>
        <a href="../../web/pantryHome.html">Home</a>
    </nav>
    <div id="wrapper">
        <p class="output"><?php
            $username = $_POST["username"];
            $password = $_POST["password"];
            $logged_in = Database::check_login($username, $password);
            if($logged_in) {
                echo "Welcome back " . $username . "!";
            } else {
                echo "Login failed.";
            }
            ?></p>
    </div>
</body>
</html>
