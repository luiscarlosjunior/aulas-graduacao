SET SERVEROUTPUT ON;
--SELECT * FROM EMPLOYEE;
DECLARE

  --Declare variables
  x_ssn		    CHAR(9);
  x_salary    NUMBER(6);
  x_bdate	    DATE;
  age	        NUMBER(2);
  message	    VARCHAR2(40);
  
BEGIN

  --Prompt for the SSN
  x_ssn := '&prompt_for_ssn';
  
  --Find the birth date
  SELECT bdate, salary
  INTO	 x_bdate, x_salary
  FROM   employee
  WHERE ssn = x_ssn;

  --convert the age
  age := TRUNC(MONTHS_BETWEEN(SYSDATE, x_bdate) / 12, 0);
  
  --Test the month
  CASE 
    WHEN age < 20 THEN message := 'Very young employee';
    
    WHEN x_salary < 30000 THEN message := 'Very inexpensive employee';
    
    WHEN age BETWEEN 40 AND 50 AND
         x_salary BETWEEN 30000 AND 40000 THEN 
            message := 'Middle age, middle salary employee';
            
    ELSE message := 'No analysis made';
    
  END CASE;

  --Output the results
  dbms_output.put_line(message);
  
END;
