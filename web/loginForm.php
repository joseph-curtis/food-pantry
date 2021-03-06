<?php
/*
File Name: loginForm.php
Last Edited: 05/26/2021
Author: Katie Pundt
*/
require_once 'assets/utilities.php';
require_secure();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>PCC Panther Pantry | Account Login</title>
    <!-- Reset CSS -->
    <link href="assets/css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link href="assets/css/pantherPantry.css" rel="stylesheet">
</head>
<body>
    <h1>Panther Pantry Login</h1>
    <nav>
        <a href="pantryHome.html">Home</a>
        <a href="loginForm.php">Login</a>
        <a href="registerForm.php">Create New Account</a>
    </nav>
    <div id="wrapper">
        <h2>Login</h2>
    <div id="formWrapper">
        <form method="POST" action="../web/assets/loginConfirmationPage.php">
            <div id="formContents">
                <label for="username">Username</label>
                <input id="username" name="username" type="text" required><br>
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required><br>
                <div id="result"></div>
                <div id="result"></div>
            </div>
            <input type="submit" id="loginButton" value="Submit">
        </form>
    </div>
</body>
</html>
