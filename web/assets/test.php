<?php



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

    /*
    // prepare sql and bind parameters using PDO
    $stmt = $conn->query("SELECT username, password_hash FROM PERSON;")->fetch();

    // set parameters and execute
    $username = $_GET["username"];
    $password = $_GET["password"];

    $rs = $stmt->execute([
        ":username" => $username,
        ":password" => $password
    ]);

    print_r($stmt->errorInfo());

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo $row;
    }

    $conn = null;
     */

$stmt = $conn->prepare("SELECT username, password_hash FROM PERSON WHERE username = 'test.student'");
$stmt->execute();
$result = $stmt->fetch(PDO::FETCH_ASSOC);
print_r($result);
