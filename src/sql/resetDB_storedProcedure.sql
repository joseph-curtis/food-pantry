/*
********************************************************************************
//  Author:	    Joseph Curtis
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  Date:		05.23.2021
//  Server:		cisdbss.pcc.edu
//  Notes:		This script creates a Procedure that creates the DB tables
				and then inserts additional new data.
// TO TEST:  run the following--
EXECUTE resetDB;
4/20/2021:    first draft, no execute permissions! Use RESET_DB.sql
********************************************************************************
*/
/*** Activate database ***/
USE cis234a_team_JK_LOL;

GO
/*** DROP procedure if exists ***/
IF EXISTS (SELECT name FROM sys.objects WHERE name = 'resetDB')
    BEGIN
        DROP PROCEDURE resetDB;
        PRINT 'resetDB procedure has been dropped';
    END;
ELSE
    BEGIN
        PRINT 'resetDB procedure did not exist so no DROP was needed';
    END;

GO
/*** Start stored procedure: ***/

CREATE PROCEDURE resetDB
AS
BEGIN

/*** DROP statements ***/

    IF OBJECT_ID('RECIPIENT', 'U') IS NOT NULL
        BEGIN
            DROP TABLE RECIPIENT;
            PRINT 'RECIPIENT table has been dropped';
        END;

    IF OBJECT_ID('MESSAGE', 'U') IS NOT NULL
        BEGIN
            DROP TABLE MESSAGE;
            PRINT 'MESSAGE table has been dropped';
        END;

    IF OBJECT_ID('TEMPLATE', 'U') IS NOT NULL
        BEGIN
            DROP TABLE TEMPLATE;
            PRINT 'TEMPLATE table has been dropped';
        END;

    IF OBJECT_ID('PERSON', 'U') IS NOT NULL
        BEGIN
            DROP TABLE PERSON;
            PRINT 'PERSON table has been dropped';
        END;
	
	IF OBJECT_ID('SETTINGS', 'U') IS NOT NULL
		BEGIN
			DROP TABLE SETTINGS;
			PRINT 'SETTINGS table has been dropped';
		END;


/*** CREATE tables ***/

    CREATE TABLE PERSON
        (						/*autonumber*/
        PK_Person_ID        INT IDENTITY (1, 1) NOT NULL	PRIMARY KEY
        , firstname         NVARCHAR(35)        NULL
        , lastname          NVARCHAR(35)        NULL
        , username          VARCHAR(30)         NOT NULL
        , password_hash     VARBINARY(128)      NOT NULL
                            /* allows 64 char password and 64 char salt */
        , email             VARCHAR(60)         NOT NULL
        , role              NVARCHAR(30)        NOT NULL
        , phone             NVARCHAR(10)        NULL
        );
    CREATE TABLE TEMPLATE
        (
        PK_Template_ID      INT IDENTITY (1, 1) NOT NULL	PRIMARY KEY
        , name              NVARCHAR(45)        NOT NULL
        , temp_subject      NVARCHAR(78)        NULL
        , temp_body         NVARCHAR(4000)      NULL
        );
    CREATE TABLE MESSAGE
        (
        PK_Message_ID       INT IDENTITY (1, 1) NOT NULL	PRIMARY KEY
        , FK_FromPerson_ID  INT                 NOT NULL
        , FK_Template_ID    INT                 NULL
        , subject           NVARCHAR(78)        NULL
        , textbody          NVARCHAR(4000)      NULL
        , datetime          SMALLDATETIME       NOT NULL
        );
    CREATE TABLE RECIPIENT
        (
        PK_Recipient_ID     INT IDENTITY (1, 1) NOT NULL	PRIMARY KEY
        , FK_ToPerson_ID    INT                 NOT NULL
        , FK_Message_ID     INT                 NOT NULL
        , to_email          VARCHAR(60)         NOT NULL
        );
	CREATE TABLE SETTINGS
		(
		PK_Settings_ID		INT IDENTITY(1, 1)	NOT NULL	PRIMARY KEY
		, FK_Person_ID		INT				  	NULL
		, email				BIT					NOT NULL	DEFAULT 0		
		, sms				BIT					NOT NULL	DEFAULT 0
		, both				BIT					NOT NULL	DEFAULT 0
		, opt_out			BIT					NOT NULL	DEFAULT 0
		);

    -- add FK constraints:
    ALTER TABLE MESSAGE
        ADD CONSTRAINT FK_MESSAGE_FromPerson_PERSON_id
		FOREIGN KEY (FK_FromPerson_ID) REFERENCES PERSON(PK_Person_ID);

    ALTER TABLE MESSAGE
        ADD CONSTRAINT FK_MESSAGE_template_TEMPLATE_id
		FOREIGN KEY (FK_Template_ID) REFERENCES TEMPLATE(PK_Template_ID);

    ALTER TABLE RECIPIENT
        ADD CONSTRAINT FK_RECIPIENT_ToPerson_PERSON_id
		FOREIGN KEY (FK_ToPerson_ID) REFERENCES PERSON(PK_Person_ID);

    ALTER TABLE RECIPIENT
        ADD CONSTRAINT FK_RECIPIENT_message_MESSAGE_id
		FOREIGN KEY (FK_Message_ID) REFERENCES MESSAGE(PK_Message_ID);

	ALTER TABLE SETTINGS
		ADD CONSTRAINT FK_SETTINGS_Person_PERSON_id
		FOREIGN KEY (FK_Person_ID) REFERENCES PERSON(PK_Person_ID);

    -- create indexes:
    CREATE UNIQUE INDEX IDX_PERSON_username
        ON PERSON (username ASC);

    CREATE INDEX IDX_PERSON_email
        ON PERSON (email ASC);

    CREATE UNIQUE INDEX IDX_TEMPLATE_name
        ON TEMPLATE (name ASC);


    PRINT 'DB tables were sucessfully re-created';
END;