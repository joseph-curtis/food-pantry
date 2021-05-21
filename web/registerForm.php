<?php
/*
File Name: registerForm.php
Last Edited: 04/27/2021
Author: Katie Pundt
*/

?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>PCC Food Pantry Registration</title>
    <!-- Reset CSS -->
    <link href="assets/css/reset.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <!-- Project CSS -->
    <link rel="stylesheet" href="assets/css/pantherPantry.css">
    <!-- Scripts -->
    <script src="assets/regValidate.js"></script>
</head>
<body>
    <h1>Food Pantry Notification Registration</h1>
    <nav>
        <a href="pantryHome.html">Home</a>
        <a href="loginForm.php">Login</a>
        <a href="registerForm.php">Create New Account</a>
    </nav>
    <div id="wrapper">
        <h2>Create A New Account</h2>
        <p id="register">If you would like to sign up to receive notifications from the PCC Panther Pantry please create an
            account by filling out the form below.</p>
        <div id="formWrapper">
            <form method="post" action="../web/assets/regConfirmationPage.php">
                <div id="errorMessages">
                    <span id="firstNameMessage"></span>
                    <span id="lastNameMessage"></span>
                    <span id="usernameMessage"></span>
                    <span id="emailMessage"></span>
                    <span id="cellPhoneMessage"></span>
                    <span id="passwordMessage"></span>
                    <span id="confirmPasswordMessage"></span>
                </div>

                <div id="formContents">
                    <label for="firstName">First Name</label>
                    <input id="firstName" name="first" type="text" oninput="validateFirstName();"><br>

                    <label for="lastName">Last Name</label>
                    <input id="lastName" name="last" type="text" oninput="validateLastName();"><br>

                    <label for="username">Username</label>
                    <input id="username" name="username" type="text" oninput="validateUsername();"><br>

                    <label for="email">Email</label>
                    <input id="email" name="email" type="email" oninput="validateEmail(email);"><br>

                    <label for="cellPhone">Cell Phone</label>
                    <input id="cellPhone" name="cellPhone" type="tel" maxlength="10" oninput="validateCellPhone();"><br>

                    <label for="password">Password</label>
                    <input id="password" name="password" type="password" oninput="validatePassword()" required><br>

                    <label for="confirmPassword">Confirm Password</label>
                    <input id="confirmPassword" name="confirmPassword" type="password" oninput="validatePasswordConfirm()" required><br>

                    <div id="result"></div>
                    <input type="submit" id="subscribeButton" value="Submit" formtarget="_blank">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
