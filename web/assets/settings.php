<?php
/*
File Name: settings.php
Last Edited: 05/30/2021
Author: Katie Pundt
*/
require_once 'Database.php';
require_once 'utilities.php';
require_once 'User.php';
require_secure();
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>PCC Food Pantry | Subscriber Settings</title>
    <!-- Reset CSS -->
    <link href="css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link rel="stylesheet" href="css/pantherPantry.css">
    <!-- Scripts -->
    <script src="regValidate.js"></script>
</head>
<body>
<h1>Food Pantry Notification Registration</h1>
<nav>
    <a href="../../web/pantryHome.html">Home</a>
    <a href="../../web/assets/logout.php">Logout</a>
</nav>
<div id="wrapper">
    <h2>Manage Account Settings</h2>
    <p class="output"><?php
    echo 'Welcome back ' . $_SESSION["session_username"] . '!' . '<br>';
    echo 'Manage your notification preferences or update your contact information below.' . '<br>';

    ?></p>
    <div id="formWrapper">
        <form method="post" action="../assets/settingsConfirmationPage.php">
            <div id="errorMessages">
                <span id="emailMessage"></span>
                <span id="cellPhoneMessage"></span>
                <span id="passwordMessage"></span>
                <span id="confirmPasswordMessage"></span>
            </div>

            <h3>Notification Preferences</h3>

            <div id="preferences">
                <input type="checkbox" id="receive_email" name="receive_email" value="receive_email" <?php Database::get_email_checkbox() ?>>
                <label for="emailOnly">Email notifications</label><br>

                <input type="checkbox" id="receive_sms" name="receive_sms" value="receive_sms" <?php Database::get_sms_checkbox() ?>>
                <label for="smsOnly">SMS notifications</label><br>

            </div><br>

            <h3>Update Contact Information</h3>
            <div id="contactInfo">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" oninput="validateEmail(email)" value="<?php Database::get_email()?>"><br>

                <label for="cellPhone">Cell Phone</label>
                <input id="cellPhone" name="cellPhone" type="tel" maxlength="10" oninput="validateCellPhone()" value="<?php echo Database::get_phone()?>"><br>

                <label for="password">Update Password</label>
                <input id="password" name="password" type="password" oninput="validatePassword()"><br>

                <label for="confirmPassword">Confirm Password</label>
                <input id="confirmPassword" name="confirmPassword" type="password" oninput="validatePasswordConfirm()"><br>
            </div>
            <div id="result"></div><br>
            <input type="submit" id="settingsButton" value="Save Changes">
        </form>
    </div>
</div>
</body>
</html>
