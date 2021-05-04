<?php
/*
File Name: loginConfirmationPage.php
Last Edited: 05/03/2021
Author: Katie Pundt
*/

// database credentials
$server = "cisdbss.pcc.edu";
$dbname = "cis234a_team_JK_LOL";
$userName = "cis234a_team_JK_LOL";
$password = "Cis234A_Team_JK_lOl_Spring_21_&(%";

// connect to database
$conn = new PDO("sqlsrv:Server=$server;Database=$dbname", $userName, $password);
if ($conn) {
    echo "Successfully connected to MS SQL Server!<br>";
} else {
    echo "Connection failed!";
    die (print_r(sqlsrv_errors(), true));
}

// prepare and execute query and fetch the results
$stmt = $conn->prepare("SELECT * FROM PERSON WHERE username = :username AND password_hash = HASHBYTES('SHA2_256', :password);");

// get username from login form
$username = $_POST["username"];
$password = $_POST["password"];

$rs = $stmt->execute([
    ":username" => $username,
    ":password" => $password
]);

// print results
$result = $stmt->fetchAll(PDO::FETCH_NUM);
print_r($result);

print_r($stmt->errorInfo());



$conn = null;



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
