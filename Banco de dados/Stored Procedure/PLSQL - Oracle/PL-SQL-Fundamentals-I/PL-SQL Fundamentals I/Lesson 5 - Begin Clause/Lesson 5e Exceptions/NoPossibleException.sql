SET SERVEROUTPUT ON;

/*
Author:   Tim Miles
Date:     3/23/2013
Purpose:  This routine uses the SUM() function, which eliminates the 
          possibility of returning zero records 
*/
DECLARE

  --Declare variables
  vHours  works_on.hours%TYPE;
  
BEGIN

  --Select 
  SELECT SUM(HOURS)
  INTO  vHours
  FROM  works_on
  WHERE essn = &enter_ssn;
  
  --Display the result
  dbms_output.put_line('Total hours: ' || vHours);
  
END;