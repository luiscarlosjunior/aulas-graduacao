set serveroutput on;

BEGIN

  --Create FOR…LOOP cursor
  FOR Employees IN 
      (SELECT *
       FROM employee) LOOP

       --Output the salary detail
       dbms_output.put_line('Employee: ' || Employees.Lname ||
       			    ' earns $' || Employees.salary);

  END LOOP;

END;
