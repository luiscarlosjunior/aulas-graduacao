DECLARE

  --Declare variables
  x_ssn		CHAR(9);
  x_bdate	DATE;
  birth_month	CHAR(3);
  message	VARCHAR2(40);
  
BEGIN

  --Prompt for the SSN
  x_ssn := '&prompt_for_ssn';
  
  --Find the birth date
  SELECT bdate
  INTO	 x_bdate
  FROM   employee
  WHERE ssn = x_ssn;

  --convert the birth month
  birth_month := TO_CHAR(x_bdate, 'MON');
  
  --Test the month
  CASE birth_month
    WHEN 'JAN' THEN message := 'Start of the year';
    WHEN 'FEB' THEN message := 'Short month';
    WHEN 'MAR' THEN message := 'Spring has sprung';
    WHEN 'APR' THEN message := 'Watch for showers';
    WHEN 'MAY' THEN message := 'Smell the flowers';
    WHEN 'JUN' THEN message := 'Time for brides';
    WHEN 'JUL' THEN message := 'Vacation time';
    ELSE message := 'No comment';
  END CASE;

  --Output the results
  dbms_output.put_line(message);
  
END;
