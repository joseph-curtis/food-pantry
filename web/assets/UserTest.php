<?php
/*
File Name: UserTest.php
Last Edited: 05/19/2021
Author: Katie Pundt
*/
require_once 'User.php';
require_once 'Database.php';

use PHPUnit\Framework\TestCase;

class UserTest extends TestCase
{

    public function test__construct()
    {
        $user = new User([
            "firstname" => "Test",
            "lastname" => "Student",
            "username" => "test.student",
            "password" => "Test123!",
            "email" => "test.student@pcc.edu",
            "phone" => "5035551234"
        ]);
        $this->assertEquals("Test", $user->getFirstname());
        $this->assertEquals("Student", $user->getLastname());
        $this->assertEquals("test.student", $user->getUsername());
        $this->assertEquals("Test123!", $user->getPassword());
        $this->assertEquals("test.student@pcc.edu", $user->getEmail());
        $this->assertEquals("5035551234", $user->getPhone());
    }

    public function testGetFirstname()
    {
        $user = new User(["firstname" => "Test", "lastname" => "", "username" => "", "password" => "",
            "email" => "", "phone" => ""]);
        $this->assertEquals("Test", $user->getFirstname());
    }

    public function testGetLastname()
    {
        $user = new User(["firstname" => "", "lastname" => "Student", "username" => "", "password" => "",
            "email" => "", "phone" => ""]);
        $this->assertEquals("Student", $user->getLastname());
    }

    public function testGetUsername()
    {
        $user = new User(["firstname" => "", "lastname" => "", "username" => "test.student", "password" => "",
            "email" => "", "phone" => ""]);
        $this->assertEquals("test.student", $user->getUsername());
    }

    public function testGetPassword()
    {
        $user = new User(["firstname" => "", "lastname" => "", "username" => "", "password" => "Test123!",
            "email" => "", "phone" => ""]);
        $this->assertEquals("Test123!", $user->getPassword());
    }

    public function testGetEmail()
    {
        $user = new User(["firstname" => "", "lastname" => "", "username" => "", "password" => "",
            "email" => "test.student@pcc.edu", "phone" => ""]);
        $this->assertEquals("test.student@pcc.edu", $user->getEmail());
    }

    public function testGetPhone()
    {
        $user = new User(["firstname" => "", "lastname" => "", "username" => "", "password" => "",
            "email" => "", "phone" => "5035551234"]);
        $this->assertEquals("5035551234", $user->getPhone());
    }

    public function testJsonSerialize()
    {
        $user = new User([
            "firstname" => "Test",
            "lastname" => "Student",
            "username" => "test.student",
            "password" => "Test123!",
            "email" => "test.student@pcc.edu",
            "phone" => "5035551234"
        ]);
        $this->assertEquals([
            "firstname" => "Test",
            "lastname" => "Student",
            "username" => "test.student",
            "password" => "Test123!",
            "email" => "test.student@pcc.edu",
            "phone" => "5035551234"
        ], $user->jsonSerialize());
    }
}
