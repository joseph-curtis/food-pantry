/*
********************************************************************************
//  use this to SELECT and compare hash data and username when logging in
//
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  version:	05.05.2021
//  Notes:      copy/paste this in your Java/PHP project
********************************************************************************
*/
USE cis234a_team_JK_LOL;

/* use parameters for username and password */
SELECT  firstname, lastname
FROM    PERSON
WHERE   username = ? AND password_hash = HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), ?));

/* use for php queries */
SELECT HASHBYTES('SHA2_256', CONVERT(NVARCHAR(MAX), :password));