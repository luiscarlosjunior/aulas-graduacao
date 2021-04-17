SET SERVEROUTPUT ON;

DECLARE

  --Define the RECORD object
  TYPE EmpRecord 
    IS RECORD (ssn          employee.ssn%TYPE,
               LName        employee.LName%TYPE,
               DName        department.DName%TYPE,
               BonusPayment NUMBER(6)
               );

  --Declare variables
  ActiveEmp   EmpRecord;
  InactiveEmp EmpRecord;
  
BEGIN
<<LocateActive>>
  SELECT essn, LName, DName, 5000
  INTO ActiveEmp
  FROM employee emp
    INNER JOIN department dep ON emp.dno = dep.dnumber
    INNER JOIN works_on wo ON emp.ssn = wo.essn
  WHERE
    hours = (SELECT MAX(hours) FROM works_on)
    AND ROWNUM <= 1;
    
<<OutputActive>>
  dbms_output.put_line('Active employee name: ' || ActiveEmp.LName);
  dbms_output.put_line('Active employee department: ' || ActiveEmp.DName);
  dbms_output.put_line('Active employee bonus: $' || ActiveEmp.BonusPayment);

<<LocateInactive>>
  SELECT essn, LName, DName, 0
  INTO ActiveEmp
  FROM employee emp
    INNER JOIN department dep ON emp.dno = dep.dnumber
    INNER JOIN works_on wo ON emp.ssn = wo.essn
  WHERE
    hours = (SELECT MIN(hours) FROM works_on)
    AND ROWNUM <= 1;
    
<<OutputActive>>
  dbms_output.put_line('');
  dbms_output.put_line('Inactive employee name: ' || InactiveEmp.LName);
  dbms_output.put_line('Inactive employee department: ' || InactiveEmp.DName);
  dbms_output.put_line('Inactive employee bonus: $' || InactiveEmp.BonusPayment);

END;