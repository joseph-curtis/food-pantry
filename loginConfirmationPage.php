<?php
/*
File Name: loginConfirmationPage.php
Last Edited: 04/20/2021
Author: Katie Pundt
*/

$username = $_GET['username'];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Panther Pantry Login Confirmation</title>
</head>
<body>
<h1>Panther Pantry Login Confirmation</h1>
<p>Welcome back <?php echo $username?>!</p>
</body>
</html>
