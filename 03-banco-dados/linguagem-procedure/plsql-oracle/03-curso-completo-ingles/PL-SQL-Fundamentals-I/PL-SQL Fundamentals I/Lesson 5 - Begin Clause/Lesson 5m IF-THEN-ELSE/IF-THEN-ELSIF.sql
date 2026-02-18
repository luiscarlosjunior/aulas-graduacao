SET SERVEROUTPUT ON;

DECLARE

  --Declare variables
  birth_month   CHAR(3) := '&enter_birth_month_MMM';
  message       VARCHAR2(30);
  
BEGIN

  --Test the month
  IF birth_month = 'JAN' THEN
  
    --Set the message
    message := 'Start of the year';
    
  ELSIF birth_month = 'FEB' THEN
  
    --Set the message
    message := 'Short month';
    
  ELSE
  
    --Set the message
    message := 'No comment';
  
  END IF;
  
  --Display the output to the user
  dbms_output.put_line(message);

END;