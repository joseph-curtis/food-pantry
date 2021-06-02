<?php
/*
File Name: loginConfirmationPage.php
Last Edited: 06/01/2021
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
    <title>PCC Food Pantry | Login Confirmation</title>
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
        <a href="../../web/loginForm.php">Login</a>
    </nav>
    <div id="wrapper">
        <p class="output"><?php
            $username = $_POST["username"];
            $password = $_POST["password"];
            $_SESSION["session_username"] = $username;
            $logged_in = Database::check_login($username, $password,);

            if ($logged_in == FALSE) {
                echo "Login failed. Please try again.";
                exit();
            }

            $activated = Database::get_activated_status();
            if($activated == 1 && $logged_in) {
                header('Location: ' . 'settings.php');
            } elseif ($activated == 0 && $logged_in) {
                echo "Please activate your account.";
            } else {
                return FALSE;
            }
            ?></p>
    </div>
</body>
</html>
