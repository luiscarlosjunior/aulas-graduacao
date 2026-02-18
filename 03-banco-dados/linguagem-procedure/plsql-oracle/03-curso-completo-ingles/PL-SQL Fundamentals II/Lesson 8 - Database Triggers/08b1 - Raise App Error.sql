--Test to see what TO_CHAR() function returns
SELECT TO_CHAR(SYSDATE, 'DY') FROM dual;

--Create trigger to limit updates based on day and time
CREATE OR REPLACE TRIGGER security_time_check
     BEFORE DELETE OR UPDATE ON employee
DECLARE

  --Declare variables
  dy_of_week   CHAR(3);
  hh_of_day    NUMBER(2);

BEGIN

  --Set variables
  dy_of_week := TO_CHAR(SYSDATE,'DY');
  hh_of_day := TO_CHAR(SYSDATE,'HH24');
   
/*
If you wish to test this logic, change the next 
statement to refer to the present day of the week. 
Then replace the trigger and attempt an UPDATE or 
DELETE operation.
*/
  --Test the day and time
  IF dy_of_week IN ('FRI', 'SUN')
     OR hh_of_day NOT BETWEEN 8 AND 17 THEN

    --Raise an application error to stop the process
    RAISE_APPLICATION_ERROR(-20600, 'Transaction rejected for security reasons');
  
  END IF;
  
END;