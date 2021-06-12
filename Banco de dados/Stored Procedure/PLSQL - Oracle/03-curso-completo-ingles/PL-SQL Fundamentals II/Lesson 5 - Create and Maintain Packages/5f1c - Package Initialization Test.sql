SET SERVEROUTPUT ON;

BEGIN

  --Output the salary cap
  dbms_output.put_line('Salary Cap is: $' || personnel.what_is_cap());
  
END;