<?php
/*
File Name: utilities.php
Last Edited: 05/26/2021
Author: Katie Pundt
*/
function require_secure()
{
    if ($_SERVER['HTTPS'] !== 'on') {
        header("Location: https://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']);
        exit();
    }

}

function destroy_session()
{
    $session_info = session_get_cookie_params();
    $_SESSION = [];
    setcookie(session_name(), '', 0, $session_info['path'], $session_info['domain'], $session_info['secure'], $session_info['httponly']);
    session_destroy();
}

