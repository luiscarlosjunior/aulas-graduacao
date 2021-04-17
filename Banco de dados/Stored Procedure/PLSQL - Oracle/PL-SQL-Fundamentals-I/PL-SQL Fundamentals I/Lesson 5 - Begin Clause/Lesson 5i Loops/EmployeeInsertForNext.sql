SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		  3/23/2013
Purpose:	This script will add 1000 new employee records
*/
DECLARE
  
  --Declare variables
  vCount  NUMBER(5);
  
BEGIN

  --Execute a FOR...NEXT loop
  FOR I IN 1..1000 LOOP
  
    --Insert a new employee record
    INSERT INTO employee (ssn, lname, fname, dno, salary)
      VALUES (900000000 + I, 'Doe', 'John', 1, 30000 + I);
      
  END LOOP;
  
  --Transaction control statement to complete the transaction
  COMMIT;

  --Retrieve the number of records in the employee table
  SELECT COUNT(ssn)
  INTO vCount
  FROM employee;
  
  --Output the detail to the user
  dbms_output.put_line(vCount || ' employee records found');

EXCEPTION
  --Test the exception
  WHEN OTHERS THEN
    ROLLBACK;
                       
END;