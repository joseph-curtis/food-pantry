/*
********************************************************************************
//  Use these templates for joining queries
//
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architecture
//  version:	05.18.2021
//  Notes:      copy/paste this in SQL Server Management Studio and execute
********************************************************************************
*/
USE cis234a_team_JK_LOL;

-- get full MESSAGE info
SELECT PK_Message_ID, subject, textbody, datetime, firstname, lastname, COUNT(PK_Recipient_ID) AS RecCount
FROM MESSAGE JOIN PERSON ON FK_FromPerson_ID = PK_Person_ID
    JOIN RECIPIENT ON FK_Message_ID = PK_Message_ID
WHERE datetime <= ? AND datetime >=   ?
GROUP BY PK_Message_ID, subject, textbody, datetime, firstname, lastname
ORDER BY datetime DESC;

