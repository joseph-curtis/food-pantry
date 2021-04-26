/*
********************************************************************************
//  Author:	    Joseph Curtis
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  Date:		04.20.2021
//  Server:		cisdbss.pcc.edu
//  Login:
//  Password:   see secrets in repo
//  Notes:		This script creates a Procedure that creates the SalesDB tables
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


/*** CREATE tables ***/

    CREATE TABLE PERSON
        (
        PK_Person_ID        INT                 NOT NULL
        , firstname         NVARCHAR(35)        NULL
        , lastname          NVARCHAR(35)        NULL
        , username          VARCHAR(30)         NOT NULL
        , password_hash     VARBINARY(128)      NOT NULL
                            /* allows 64 char password and 64 char salt */
        , email             VARCHAR(60)         NOT NULL
        , role              NVARCHAR(30)        NOT NULL
        );
    CREATE TABLE TEMPLATE
        (
        PK_Template_ID      INT                 NOT NULL
        , name              NVARCHAR(45)        NOT NULL
        , temp_subject      NVARCHAR(78)        NULL
        , temp_body         NVARCHAR(4000)      NULL
        );
    CREATE TABLE MESSAGE
        (
        PK_Message_ID       INT                 NOT NULL
        , FK_FromPerson_ID  INT                 NOT NULL
        , FK_Template_ID    INT                 NULL
        , subject           NVARCHAR(78)        NULL
        , textbody          NVARCHAR(4000)      NULL
        , datetime          SMALLDATETIME       NOT NULL
        );
    CREATE TABLE RECIPIENT
        (
        PK_Recipient_ID     INT                 NOT NULL
        , FK_ToPerson_ID    INT                 NOT NULL
        , FK_Message_ID     INT                 NOT NULL
        , to_email          VARCHAR(60)         NOT NULL
        );
    -- add PK constraints:
    ALTER TABLE PERSON
        ADD CONSTRAINT PersonID_PK PRIMARY KEY (PK_Person_ID);

    ALTER TABLE TEMPLATE
        ADD CONSTRAINT TemplateID_PK PRIMARY KEY (PK_Template_ID);

    ALTER TABLE MESSAGE
        ADD CONSTRAINT MessageID_PK PRIMARY KEY (PK_Message_ID);

    ALTER TABLE RECIPIENT
        ADD CONSTRAINT RecipientID_PK PRIMARY KEY (PK_Recipient_ID);

    -- add FK constraints:
    ALTER TABLE MESSAGE
        ADD CONSTRAINT FK_PERSON_FromPersonID FOREIGN KEY (FK_FromPerson_ID)
        REFERENCES PERSON(PK_Person_ID);

    ALTER TABLE MESSAGE
        ADD CONSTRAINT FK_TEMPLATE_TemplateID FOREIGN KEY (FK_Template_ID)
        REFERENCES TEMPLATE(PK_Template_ID);

    ALTER TABLE RECIPIENT
        ADD CONSTRAINT FK_PERSON_ToPersonID FOREIGN KEY (FK_ToPerson_ID)
        REFERENCES PERSON(PK_Person_ID);

    ALTER TABLE RECIPIENT
        ADD CONSTRAINT FK_MESSAGE_MessageID FOREIGN KEY (FK_Message_ID)
        REFERENCES MESSAGE(PK_Message_ID);

    -- create indexes:
    CREATE UNIQUE INDEX IDX_PERSON_username
        ON PERSON (username ASC);

    CREATE INDEX IDX_PERSON_email
        ON PERSON (email ASC);

    CREATE UNIQUE INDEX IDX_TEMPLATE_name
        ON TEMPLATE (name ASC);


    PRINT 'resetDB stored procedure was sucessfully created';
END;
