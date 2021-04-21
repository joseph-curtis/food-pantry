<?php
/*
File Name: regConfirmationPage.php
Last Edited: 04/20/2021
Author: Katie Pundt
*/

$first_name = $_GET['first'];
$last_name = $_GET['last'];
$email = $_GET['email'];
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>New Account Confirmation</title>
    </head>
    <body>
        <h1>New Account Confirmation</h1>
        <p>Thank you <?php echo $first_name . " " . $last_name;?> for registering for the PCC Panther Pantry!</p>
        <p>A confirmation email has been sent to <?php  echo $email;?>.<br>
        You will begin receiving food pantry notifications within 24 hours.</p>
    </body>
</html>
