SET SERVEROUTPUT ON;

DECLARE

  --Declare variables
  output_text CHAR(100);

BEGIN

  --Execute the raise_salary with a different parameter order
  raise_salary (result_message=>output_text, employee_pct=>30, employee_ssn=>123456789);
  
  --Output the results
  dbms_output.put_line(output_text);
  
END;

/*
--Highlight the following 3 lines and press F5 
--to execute query retrieving the salary
SELECT fname, lname, salary
FROM employee
WHERE ssn=123456789;
*/