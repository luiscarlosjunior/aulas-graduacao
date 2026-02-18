SET SERVEROUTPUT ON;

DECLARE

  --Declare RECORD object
  TYPE EmpRecord
    IS RECORD (ssn          employee.ssn%TYPE,  
               LName        employee.LName%TYPE,
               DName        department.DName%TYPE,
               BonusPayment NUMBER(6));
               
  --Define the variable
  InactiveEmp EmpRecord;

BEGIN
/*
Identify the first three employees who have been the least active, based upon the number
of hours they have been working on preojects.  
*/

  --Execute the loop 3 times
  FOR i IN 1..3 LOOP

    --Retrieve the first employee
    SELECT essn, LName, DName, 0
    INTO InactiveEmp
    FROM  employee emp
      INNER JOIN department dep on emp.dno = dep.dnumber
      INNER JOIN works_on wo on emp.ssn = wo.essn
    WHERE hours = (SELECT MIN(hours) FROM works_on)
      AND ROWNUM <= 1;
      
    --Remove this employee as a manager of any department.
    UPDATE department
    SET MgrSSN = NULL
    WHERE MgrSSN = InactiveEmp.ssn;
    
    --Remove this employee as a supervisor of other employees.
    UPDATE employee
    SET SuperSSN = NULL
    WHERE SuperSSN = InactiveEmp.ssn;
    
    --Delete any dependetns and all WORKS_ON rows.
    DELETE FROM dependent
    WHERE essn = InactiveEmp.ssn;
    
    DELETE FROM works_on
    WHERE essn = InactiveEmp.ssn;
    
    --Delete this employee from teh EMPLOYEE table itself
    DELETE FROM employee
    WHERE ssn = InactiveEmp.ssn;
    
    --Transaction control statement to complete the transaction
    COMMIT;
    
    --Output the detail to the user
    dbms_output.put_line('Least active employee has been transferred: ' || InactiveEmp.LName);

  END LOOP;
  
END;