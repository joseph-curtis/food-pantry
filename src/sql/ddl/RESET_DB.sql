/*
********************************************************************************
//  Executes to RESET THE DATABASE
//
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  version:	04.20.2021
//  Notes:      copy/paste this in SQL Server Management Studio and execute
********************************************************************************
*/
USE cis234a_team_JK_LOL;
--EXECUTE resetDB;

GO
/*** DROP statements ***/

    IF OBJECT_ID('RECIPIENT', 'U') IS NOT NULL
        DROP TABLE RECIPIENT;

    IF OBJECT_ID('MESSAGE', 'U') IS NOT NULL
        DROP TABLE MESSAGE;

    IF OBJECT_ID('TEMPLATE', 'U') IS NOT NULL
        DROP TABLE TEMPLATE;

    IF OBJECT_ID('PERSON', 'U') IS NOT NULL
        DROP TABLE PERSON;


/*** CREATE tables ***/

    CREATE TABLE PERSON
        (
        PK_Person_ID        INT                 NOT NULL
        , firstname         NVARCHAR(35)        NULL
        , lastname          NVARCHAR(35)        NULL
        , username          VARCHAR(30)         NOT NULL
        , password_hash     VARCHAR(64)         NOT NULL
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

    CREATE UNIQUE INDEX IDX_PERSON_email
        ON PERSON (email ASC);

    CREATE UNIQUE INDEX IDX_TEMPLATE_name
        ON TEMPLATE (name ASC);

/************************************************/
/*** INSERT test data & managers/workers here ***/

    DECLARE @test_manager_id INT = 1;
    DECLARE @test_worker_id INT = 2;

    INSERT INTO PERSON (PK_Person_ID, firstname, lastname, username,
                        password_hash, email, role)
    VALUES (
        @test_manager_id
        , 'test'
        , 'manager'
        , 'testmanager'
        , '11111111'
        , 'manager+test@pcc.edu'
        , 'Manager'
    );
    INSERT INTO PERSON (PK_Person_ID, firstname, lastname, username,
                        password_hash, email, role)
    VALUES (
        @test_worker_id
        , 'test'
        , 'worker'
        , 'testworker'
        , '99999999'
        , 'test+worker@pcc.edu'
        , 'Worker'
    );
    PRINT 'resetDB stored procedure was sucessfully created';