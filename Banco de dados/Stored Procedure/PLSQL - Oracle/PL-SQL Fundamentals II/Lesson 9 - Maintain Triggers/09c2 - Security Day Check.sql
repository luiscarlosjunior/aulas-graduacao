CREATE OR REPLACE TRIGGER security_day_check
  BEFORE DELETE OR UPDATE ON employee
  FOLLOWS security_hour_check
DECLARE
  
  --Declare variables
  dy_of_week   CHAR(3);
  
BEGIN
  --Set variables
  dy_of_week := TO_CHAR(SYSDATE,'DY');

  --Test the day of week
  IF dy_of_week IN ('SAT', 'SUN') THEN
    
    --Raise an application error
    RAISE_APPLICATION_ERROR(-20601, 'Transaction rejected for security limitations in effect this day');

  END IF;

END;