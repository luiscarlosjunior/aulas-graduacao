SET SERVEROUTPUT ON;

DECLARE

  --Declare the record
  TYPE EmpRecord
    IS RECORD (ssn            employee.ssn%TYPE,
               LName          employee.lname%TYPE,
               DName          department.DName%TYPE,
               BonusPayment   NUMBER(6)
               );
  
  --Declare variables 
  ActiveEmp     EmpRecord;
  InactiveEmp   EmpRecord;
  
BEGIN

  --Retrieve the Active employee detail
  SELECT essn, LName, DName, 5000
  INTO ActiveEmp
  FROM employee, department, works_on
  WHERE employee.dno = department.dnumber
  AND employee.ssn = works_on.essn
  AND hours = (SELECT MAX(hours) FROM works_on)
  AND ROWNUM <= 1;

  --Output the results
  dbms_output.put_line('Active employee name: ' || ActiveEmp.LName);
  dbms_output.put_line('Active employee department: ' || ActiveEmp.DName);
  dbms_output.put_line('Active employee bonus: $' || ActiveEmp.BonusPayment);
  
  --Retrieve the Inactive employee detail
  SELECT essn, LName, DName, 0
  INTO InactiveEmp
  FROM employee, department, works_on
  WHERE employee.dno = department.dnumber
  AND employee.ssn = works_on.essn
  AND hours = (SELECT MIN(hours) FROM works_on)
  AND ROWNUM <= 1;

  --Output the results
  dbms_output.put_line('Inactive employee name: ' || InactiveEmp.LName);
  dbms_output.put_line('Inactive employee department: ' || InactiveEmp.DName);
  dbms_output.put_line('Inactive employee bonus: $' || InactiveEmp.BonusPayment);

END;