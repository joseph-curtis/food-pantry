<?php
/*
File Name: User.php
Last Edited: 05/19/2021
Author: Katie Pundt
*/

class User implements JsonSerializable {
    private $firstname;
    private $lastname;
    private $username;
    private $password;
    private $email;
    private $phone;

    public function __construct($properties)
    {
        $this->firstname = $properties["firstname"];
        $this->lastname = $properties["lastname"];
        $this->username = $properties["username"];
        $this->password = $properties["password"];
        $this->email = $properties["email"];
        $this->phone = $properties["phone"];

    }

    public static function create_account($firstName, $lastName, $username, $password, $email, $cellPhone)
    {
        return Database::create_account($firstName, $lastName, $username, $password, $email, $cellPhone);
    }

    public function getFirstname() {
        return $this->firstname;
    }

    public function getLastname() {
        return $this->lastname;
    }

    public function getUsername() {
        return $this->username;
    }

    public function getPassword() {
        return $this->password;
    }

    public function getEmail() {
        return $this->email;
    }

    public function getPhone() {
        return $this->phone;
    }

    public function jsonSerialize()
    {
        return [
            "firstname" => $this->firstname,
            "lastname" => $this->lastname,
            "username" => $this->username,
            "password" => $this->password,
            "email" => $this->email,
            "phone" => $this->phone
        ];
    }
}
