set serveroutput on;

DECLARE

  --Declare variables
  x_emp  employee%ROWTYPE;
  
BEGIN

  --Retrieve the salary data
  select fname, lname, sex, salary 
  into x_emp.fname, x_emp.lname, x_emp.sex, x_emp.salary 
  from employee 
  WHERE SSN = '333445555';

  --Display the results
  dbms_output.put_line (x_emp.fname || ' ' || x_emp.lname || ' salary is $' || x_emp.salary);
  
END;