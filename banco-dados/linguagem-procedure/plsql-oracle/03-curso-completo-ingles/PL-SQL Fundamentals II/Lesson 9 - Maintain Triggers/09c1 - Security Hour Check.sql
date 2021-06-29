CREATE OR REPLACE TRIGGER SECURITY_HOUR_CHECK
  BEFORE DELETE OR UPDATE ON employee
DECLARE
  
  --Declare variables
  HH_OF_DAY    number(2);
  
BEGIN
  --Set variables
  hh_of_day := TO_CHAR(SYSDATE,'HH24');
   
  --Test the hour of the day
  IF HH_OF_DAY NOT BETWEEN 8 AND 17 THEN

    --Raise an application error
    RAISE_APPLICATION_ERROR(-20600, 'Transaction rejected for security limitations in effect at this time');
  
  END IF;

END;
