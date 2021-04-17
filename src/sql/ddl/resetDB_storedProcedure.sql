********************************************************************************
//  Desc:	    Joseph Curtis
//  Software:	Microsoft SQL Server Management Studio - (Transact SQL)
//				SQL Server 2012 architechture
//  Date:		04.16.2021
//  Server:		cisdbss.pcc.edu
//  Login:
//  Password:   see secrets in repo
//  Notes:		This script creates a Procedure that creates the SalesDB tables
				and then inserts additional new data.

// TO TEST:  run the following--
EXECUTE resetDB;

5/31/2020:  Fully tested; compiled and executed correctly.  Ran full-join query
			to make sure inserts are correct.  Tested if statements for TRUE
			and FALSE conditions.  100% valid.
********************************************************************************
*/
USE s276_JoeC;

// add stored procedure here