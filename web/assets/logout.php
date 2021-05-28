<?php
/*
File Name: logout.php
Last Edited: 05/27/2021
Author: Katie Pundt
*/
require_once ('utilities.php');
session_start();

destroy_session();

header('Location: ' . '../../web/loginForm.php');
