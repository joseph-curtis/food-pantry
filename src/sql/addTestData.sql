/*
********************************************************************************
//  INSERT test data including people and messages
//
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  version:	05.03.2021
//  Notes:      copy/paste this in SQL Server Management Studio and execute
********************************************************************************
*/
USE cis234a_team_JK_LOL;

/* =========== */
/* add people: */
/* =========== */

INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role)
VALUES (
    'manager'
    , 'manager'
    , 'testmanager'
    , HASHBYTES('SHA2_256', 'managerpassword')
    , 'teamcjklol@gmail.com'
    , 'Manager'
);
INSERT INTO PERSON (firstname, lastname, username, password_hash, email, role)
VALUES (
    'worker'
    , 'worker'
    , 'testworker'
    , HASHBYTES('SHA2_256', 'workerpassword')
    , 'teamcjklol@gmail.com'
    , 'Worker'
);
INSERT INTO PERSON (firstname, lastname, username, password_hash,
					email, phone, role)
VALUES (
    'student'
    , 'student'
    , 'teststudent'
    , HASHBYTES('SHA2_256', 'studentpassword')
    , 'teamcjklol@gmail.com'
	, 5035551111
    , 'Student'
);

/* ============================= */
/* add messages with recipients: */
/* ============================= */

-- this is important to retreive the same PK_MessageID for RECIPIENT table:
DECLARE @messageDatetime smalldatetime = CAST(GETDATE() AS smalldatetime);

INSERT INTO MESSAGE (FK_FromPerson_ID, subject, textbody, datetime)
VALUES (
	(SELECT PK_Person_ID FROM PERSON WHERE username = 'teststudent')
	, 'test message'
	, 'this is the <b>text body</b> of the message. This is just a test.'
	, @messageDatetime
);
INSERT INTO RECIPIENT (FK_ToPerson_ID, FK_Message_ID, to_email)
VALUES (
	(SELECT PK_Person_ID FROM PERSON WHERE username = 'teststudent')
	, (SELECT PK_Message_ID FROM MESSAGE WHERE datetime = @messageDatetime)
	, 'canBeAny@email.here'
);
