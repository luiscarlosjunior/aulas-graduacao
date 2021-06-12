SET SERVEROUTPUT ON;

/*
Author:   Tim Miles
Date:     3/23/2013
Purpose:  This routine has the possibility of returning zero records 
          which will cause an exception.
*/
DECLARE

  --Declare variables
  vSSN  employee.ssn%TYPE;
  
BEGIN

  --Select 
  SELECT ssn
  INTO  vSSN
  FROM  employee
  WHERE ssn = &enter_ssn;
  
  --Display the result
  dbms_output.put_line('I found ' || vSSN);
  
END;