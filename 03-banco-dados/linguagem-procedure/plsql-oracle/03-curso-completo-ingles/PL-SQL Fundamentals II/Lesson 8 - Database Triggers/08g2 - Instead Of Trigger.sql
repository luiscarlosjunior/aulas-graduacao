--Create a new INSTEAD OF trigger to manage record deletions
CREATE OR REPLACE TRIGGER manage_delete
  INSTEAD OF DELETE 
  ON employee_department_info
DECLARE
BEGIN

  --Create the DELETE statement to use
  DELETE FROM employee
  WHERE ssn = :old.employee_ssn;
   
END;