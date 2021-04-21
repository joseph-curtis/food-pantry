/*
File Name: regValidate.js
Last Edited: 04/20/2021
Author: Katie Pundt

Validates user input from the create new account form
*/

// allowed special characters for passwords
function required () {
    return "~!@#$%^&*+";
}

// allowed special characters for usernames
function specialUsernameChar () {
    return ".-_";
}

// requirement for lowercase
function isLower (character) {
    return character >= "a" && character <= "z";
}

// requirement for uppercase
function isUpper (character) {
    return character >= "A" && character <= "Z";
}

// requirement for digit
function isDigit (character) {
    return character >= "0" && character <= "9";
}

// requirement for special characters for passwords
function isSpecial(character) {
    return required().indexOf(character) >= 0;
}

// allowed special characters for usernames
function isSpecialUsername(character) {
    return specialUsernameChar().indexOf(character) >= 0;
}

// set error message box
function setMessage(id, message) {
    var messageBox = document.getElementById(id);

    messageBox.innerText = message;
}

// clear message box
function clearMessage(id) {
    setMessage(id, "");
}

// get the input value
function getInputValue(id) {
    return document.getElementById(id).value;
}

// check that valid characters were used for first name
function isValidFirstNameCharacter(character) {
    return isLower(character) || isUpper(character);
}

// check that valid characters were used for last name
function isValidLastNameCharacter(character) {
    return isLower(character) || isUpper(character);
}

// check that valid characters were used for username
function isValidUsernameCharacter(character) {
  return isLower(character) || isUpper(character) || isDigit(character) || isSpecialUsername(character) ;
}

// check first name requirements
function checkFirstNameRequirements(value) {
    if (value === "") {
        setMessage("firstNameMessage", "First name must be more than one alphabetic character.")
    }

    for(var counter = 0; counter < value.length; counter ++) {
        var character = value.charAt(counter);

        if(!isValidFirstNameCharacter(character)) {
            setMessage("firstNameMessage", "Character " + character + " is invalid in the first name field.");
        }
    }
}

// check last name requirements
function checkLastNameRequirements(value) {
    if (value === "") {
        setMessage("lastNameMessage", "Last name must be more than one alphabetic character.")
    }

    for(var counter = 0; counter < value.length; counter ++) {
        var character = value.charAt(counter);

        if(!isValidLastNameCharacter(character)) {
            setMessage("lastNameMessage", "Character " + character + " is invalid in the last name field.");
        }
    }
}

// check username requirements
function checkUsernameRequirements(value) {
    if (value === "") {
        setMessage("usernameMessage", 'Username must be one or more alphanumeric characters. Special characters "., _, and -" are allowed.')
    }

    for (var counter = 0; counter < value.length; counter ++) {
        var character = value.charAt(counter);

        if(!isValidUsernameCharacter(character)) {
            setMessage("usernameMessage", "Character " + character + " is invalid in the username field.")
        }
    }
}

// check cell phone requirements
function checkCellPhoneRequirements(value) {
    for (var counter = 0; counter < value.length; counter ++) {
        var character = value.charAt(counter);

        if(!isDigit(character)) {
            setMessage("cellPhoneMessage", "Character " + character + " is invalid in the cell phone field.")
        }
    }
}

// check that password meets the requirements
function checkPasswordRequirements(value) {
    var hasUpper = false;
    var hasDigit = false;
    var hasRequired = false;

    for(var counter = 0; counter < value.length; counter ++) {
        var character = value.charAt(counter);

        if (isUpper(character)) {
            hasUpper = true;
        } else if (isDigit(character)) {
            hasDigit = true;
        } else if (isSpecial(character)) {
            hasRequired = true;
        }
    }

    if (!hasUpper) {
        setMessage("passwordMessage", "Password must have at least one upper-case letter.");
    } else if(!hasDigit) {
        setMessage("passwordMessage", "Password must have at least one number.");
    } else if(!hasRequired) {
        setMessage("passwordMessage", "Password must have at least one character from " + required() + ".");
    }
}

// check that the password is 8-64 characters
function checkLength(value) {
    if (value === "") {
        setMessage("passwordMessage", "Password must be at least 8 characters, with at least one upper-case, one number, and one character from " + required() + ".");
    }

    if (value.length < 8) {
        setMessage("passwordMessage", "Password must be at least 8 characters.");
        return;
    }

    if(value.length > 64) {
        setMessage("passwordMessage", "Password must not exceed 64 characters.")
    }
}

// validate first name
function validateFirstName() {
    var value = getInputValue("firstName");

    clearMessage("firstNameMessage");
    checkFirstNameRequirements(value);
}

// validate last name
function validateLastName() {
    var value = getInputValue("lastName");

    clearMessage("lastNameMessage");
    checkLastNameRequirements(value);
}

// validate username
function validateUsername() {
    var value = getInputValue("username");

    clearMessage("usernameMessage");
    checkUsernameRequirements(value);
}

// validate email
function validateEmail(email) {
    var validRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (email.value.match(validRegex)) {
        clearMessage("emailMessage");
    } else {
        setMessage("emailMessage", "Invalid email.")
        return false;
    }
}

// validate cell phone
function validateCellPhone() {
    var value = getInputValue("cellPhone");

    clearMessage("cellPhoneMessage");
    checkCellPhoneRequirements(value);
}

// validate password
function validatePassword () {
    var value = getInputValue("password");

    clearMessage("passwordMessage");
    checkLength(value);
    checkPasswordRequirements(value);
}

// validate password confirmation
function validatePasswordConfirm () {
    if (document.getElementById("password").value === document.getElementById("confirmPassword").value) {
        clearMessage("confirmPasswordMessage");
        return true;
    } else {
        setMessage("confirmPasswordMessage", "Passwords do not match. Try again.");
    }
}

// event listeners
window.addEventListener("load", function () {
    document.getElementById("firstName").addEventListener("input", validateFirstName);
    document.getElementById("lastName").addEventListener("input", validateLastName);
    document.getElementById("username").addEventListener("input", validateUsername);
    document.getElementById("email").addEventListener("input", validateEmail);
    document.getElementById("password").addEventListener("input", validatePassword);
    document.getElementById("confirmPassword").addEventListener("input", validatePasswordConfirm);
});
