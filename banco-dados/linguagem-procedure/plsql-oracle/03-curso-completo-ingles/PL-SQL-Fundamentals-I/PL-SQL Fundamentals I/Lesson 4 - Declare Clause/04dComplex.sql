set serveroutput on;

DECLARE

  --Declare variables
  x_salary  employee.salary%TYPE;
  x_last    employee.lname%TYPE;
  
BEGIN

  --Retrieve the salary data
  select salary, lname into x_salary, x_last from employee WHERE SSN = '999887777';

  --Display the results
  dbms_output.put_line (x_last || ' salary is $' || x_salary);
  
END;