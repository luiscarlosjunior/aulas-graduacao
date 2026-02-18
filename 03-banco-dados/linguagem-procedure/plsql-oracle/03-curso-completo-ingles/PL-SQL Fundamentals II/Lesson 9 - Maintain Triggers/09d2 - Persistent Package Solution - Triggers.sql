/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

-------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER EmployeeIntegrityRow
	AFTER DELETE OR UPDATE OF ssn ON employee
 	FOR EACH ROW
BEGIN

  --Increment the DeleteIndex counter
  supervisor.DeleteIndex := supervisor.DeleteIndex + 1;
  
  --Add the old SSN to the array using the newly calculated index
  supervisor.DeleteList (supervisor.DeleteIndex) := :OLD.ssn;
  
END EmployeeIntegrity;
/

-------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER EmployeeIntegrityStatement
	AFTER DELETE OR UPDATE OF ssn ON employee
BEGIN

  --Call the package procedure to replace the supervisor 
  supervisor.ReplaceSupervisor;

  --Clear the array and index counter
  supervisor.DeleteList := supervisor.EmptyArray;
  supervisor.DeleteIndex := 0;

END EmployeeIntegrityStatement;
/