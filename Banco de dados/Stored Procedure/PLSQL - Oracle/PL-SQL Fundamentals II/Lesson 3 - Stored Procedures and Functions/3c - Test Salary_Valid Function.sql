SET SERVEROUTPUT ON;

BEGIN

  --Test the Salary_Valid function
  IF salary_valid('123456789', 80000) THEN
    dbms_output.put_line('Salary is valid');
  ELSE
    dbms_output.put_line('Salary is not valid');
  END IF;
  
END;