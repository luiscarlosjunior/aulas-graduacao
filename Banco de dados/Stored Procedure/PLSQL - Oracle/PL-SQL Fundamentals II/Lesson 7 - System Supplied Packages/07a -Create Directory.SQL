/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

--Create the Directory reference 
CREATE OR REPLACE DIRECTORY TextDirectory AS 'C:\Test';

--Query the Data Dictionary
SELECT * FROM DBA_DIRECTORIES;

