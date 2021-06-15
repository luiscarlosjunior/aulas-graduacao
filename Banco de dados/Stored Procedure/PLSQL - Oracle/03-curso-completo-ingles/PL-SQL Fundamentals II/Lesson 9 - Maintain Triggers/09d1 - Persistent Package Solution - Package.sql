/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

-------------------------------------------------------------------------------

CREATE OR REPLACE PACKAGE Supervisor AS

  --Define new Type
  TYPE SSNarray IS TABLE OF employee.ssn%TYPE
  INDEX BY SIMPLE_INTEGER;

  --Define variables using the new Type
  DeleteList   SSNarray;
  EmptyArray   SSNarray;
  DeleteIndex  SIMPLE_INTEGER DEFAULT 0;

  --Define public procedure
  PROCEDURE ReplaceSupervisor;

END Supervisor;
/

-------------------------------------------------------------------------------

CREATE OR REPLACE PACKAGE BODY Supervisor AS

  --Define procedure to handle the supervisor replacement
  PROCEDURE ReplaceSupervisor IS
  BEGIN
  
    --Perform loop using the DeleteList object defined in the
    --package specification
    FOR i IN supervisor.DeleteList.FIRST .. supervisor.DeleteList.LAST LOOP
    
      --Update the employee table
      UPDATE employee
      SET superssn = (SELECT DISTINCT ssn
                      FROM employee
                      WHERE superssn IS NULL)
      WHERE superssn = supervisor.DeleteList (i);
      
    END LOOP;
    
  END ReplaceSupervisor;

END Supervisor;
