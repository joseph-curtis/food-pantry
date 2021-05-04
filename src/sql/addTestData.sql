/*
********************************************************************************
//  INSERTs test data including managers & workers
//
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  version:	04.24.2021
//  Notes:      copy/paste this in SQL Server Management Studio and execute
********************************************************************************
*/
USE cis234a_team_JK_LOL;

DECLARE @manager_password NVARCHAR(64) = 'password';
DECLARE @manager_hash VARBINARY(128);
SET @manager_hash = HASHBYTES('SHA2_256', @manager_password);

DECLARE @worker_password NVARCHAR(64) = 'workpassword';
DECLARE @worker_hash VARBINARY(128);
SET @worker_hash = HASHBYTES('SHA2_256', @worker_password);

DECLARE @subscriber_password NVARCHAR(64) = 'studentpassword';
DECLARE @subscriber_hash VARBINARY(128);
SET @subscriber_hash = HASHBYTES('SHA2_256', @subscriber_password);

INSERT INTO PERSON (PK_Person_ID, firstname, lastname, username,
                    password_hash, email, role)
VALUES (
    (SELECT ISNULL(MAX(PK_Person_ID), 0) + 1 FROM PERSON)
    , 'test'
    , 'manager'
    , 'testmanager'
    , @manager_hash
    , 'teamcjklol@gmail.com'
    , 'Manager'
);
INSERT INTO PERSON (PK_Person_ID, firstname, lastname, username,
                    password_hash, email, role)
VALUES (
    (SELECT ISNULL(MAX(PK_Person_ID), 0) + 1 FROM PERSON)
    , 'test'
    , 'worker'
    , 'testworker'
    , @worker_hash
    , 'teamcjklol@gmail.com'
    , 'Worker'
);
INSERT INTO PERSON (PK_Person_ID, firstname, lastname, username,
                    password_hash, email, role)
VALUES (
    (SELECT ISNULL(MAX(PK_Person_ID), 0) + 1 FROM PERSON)
    , 'test'
    , 'student'
    , 'testworker'
    , @subscriber_hash
    , 'teamcjklol@gmail.com'
    , 'Subscriber'
);
