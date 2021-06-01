<?php
/*
File Name: Email.php
Last Edited: 05/31/2021
Author: Katie Pundt
*/
require_once 'Mail.php';

class Email {

    const HOST = 'ssl://smtp.gmail.com';
    const PORT = '465';
    const USERNAME = 'teamcjklol@gmail.com';
    const PASSWORD = 'zkym vgnq nmje zozv';
    const FROM = 'teamcjklol@gmail.com';
    const SUBJECT = 'Panther Pantry Account Activation';
    const BODY = '<p>Thanks for registering for the Panther Pantry!</p><p>Please click the link below to activate your account. You need to activate your account in order to receive notifications and access your account settings.</p>';

    private $to;
    private $body;
    private $subject;
    private $result;

    public function __construct($to, $subject, $body)
    {
        $this->to = $to;
        $this->subject = $subject;
        $this->body = $body;
    }

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

    public function get_status()
    {
        return $this->result;
    }

}

