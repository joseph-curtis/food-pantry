<?php
/*
File Name: Email.php
Last Edited: 06/01/2021
Author: Katie Pundt
*/
require_once 'Mail.php';
require_once 'Database.php';

class Email {

    const HOST = 'ssl://smtp.gmail.com';
    const PORT = '465';
    const USERNAME = 'teamcjklol@gmail.com';
    const PASSWORD = 'zkym vgnq nmje zozv';
    const FROM = 'PCC Panther Pantry <teamcjklol@gmail.com>';
    const SUBJECT = 'Panther Pantry Account Activation';

    // account activation constants
    const CODE_CHARS = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const CODE_CHAR_LEN = 62;
    const VERIFICATION_CODE_LEN = 10;

    private $to;
    private $body;
    private $subject;
    private $result;


    // construct new email
    public function __construct($to, $subject, $body)
    {
        $this->to = $to;
        $this->subject = $subject;
        $this->body = $body;
    }

    // send email
    public function send()
    {
        $headers = [
            'To' => $this->to,
            'From' => Email::FROM,
            'Subject' => $this->subject,
            'MIME-Version' => '1.0',
            'Content-Type' => 'text/html; charset=utf-8'
        ];

        $transport = [
            'host' => Email::HOST,
            'port' => Email::PORT,
            'username' => Email::USERNAME,
            'password' => Email::PASSWORD,
            'auth' => TRUE
        ];

        $smtp = Mail::factory('smtp', $transport);

        $this->result = $smtp->send($this->to, $headers, $this->body);

    }

    // generate activation code
    private function generate_code($length)
    {
        $code = '';

        for ($i = 0; $i < $length; $i++) {
            $code .= Email::CODE_CHARS[rand(0, Email::CODE_CHAR_LEN - 1)];
        }
        return $code;
    }

    // send email with activation code link
    public function send_verification()
    {
        $email = $_POST["email"];
        $username = $_POST["username"];
        $_SESSION["session_username"] = $username;

        // generate code and message
        $url = 'http://localhost/cis234a/cis234a_team_JK_LOL/web/assets/activate.php';
        $code = Email::generate_code(EMAIL::VERIFICATION_CODE_LEN);
        $body = <<<BODY
<h1>Welcome to the Panther Pantry!</h1>
<p>Thank you for registering on the <a href="$url">PCC Panther Pantry</a> website.</p><p> In order to verify this account, please click on the link below. Once you have verified your account, login to select your notification preferences.</p>
<p><a href="$url?code=$code&email=$email">Verify my Account</a></p>
BODY;
        // send message
        $message = new Email($email, Email::SUBJECT, $body);
        $message->send();

    }

}

