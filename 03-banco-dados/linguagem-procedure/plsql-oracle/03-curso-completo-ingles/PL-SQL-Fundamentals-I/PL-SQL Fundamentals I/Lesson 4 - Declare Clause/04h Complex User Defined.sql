SET SERVEROUTPUT ON;

DECLARE

  --Simple type declaration
  TYPE BonusCompensation
    IS RECORD (CashPayment    NUMBER(6),
               CompanyCar     BOOLEAN,
               VacationWeeks  NUMBER(2)
               );
  
  --Extended type declaration
  TYPE EmpRecord
    IS RECORD (ssn            employee.ssn%TYPE,
               LName          employee.lname%TYPE,
               DName          department.DName%TYPE,
               BonusPayment   BonusCompensation
               );
               
  --Another extended type declaration along with the instance declaration
  TYPE ManagerRecord
    IS RECORD (ssn            employee.ssn%TYPE,
               BonusPayment   BonusCompensation
               );
               
  --Instance Declaration
  BestEmp       EmpRecord;
  BestManager   ManagerRecord;

BEGIN

  /*
  Less than meaningful logic to determine the employee who should receive
  a bonus.  The main focus in this example is the ability to store datbase
  values within the record instance.
  */
  SELECT essn, LName, DName
  INTO BestEmp.ssn,
       BestEmp.LName,
       BestEmp.DName
  FROM employee, department, works_on
  WHERE employee.dno = department.dnumber
  AND employee.ssn = works_on.essn
  AND hours = (SELECT MAX(hours) FROM works_on)
  AND ROWNUM <= 1;

  --The next segment of code accesses the values within the record instance
  BestEmp.BonusPayment.CashPayment := 5000;
  BestEmp.BonusPayment.CompanyCar := TRUE;
  BestEmp.BonusPayment.VacationWeeks := 1;
  
  --Output the results
  dbms_output.put_line('Best employee name: ' || BestEmp.LName);
  dbms_output.put_line('Best employee department: ' || BestEmp.DName);
  dbms_output.put_line('Best employee bonus payment: $' || BestEmp.BonusPayment.CashPayment);

  --Test for company car
  IF BestEmp.BonusPayment.CompanyCar = TRUE THEN
    dbms_output.put_line ('Company car also provided');
  END IF;
  
  --Test for vacation weeks
  IF BestEmp.BonusPayment.VacationWeeks > 0 THEN
    dbms_output.put_line ('Extra vacation weeks granted: ' || BestEmp.BonusPayment.VacationWeeks);
  END IF;
  
  /*
  A similar set of instructions uses the other record instance.  This is used to 
  perform similar logic for a manager who is selected for bonus compensation.
  */
  SELECT ssn
  INTO BestManager.ssn
  FROM employee, department
  WHERE employee.ssn = department.MgrSSN
  AND ROWNUM <= 1;

  --The next segment of code accesses the values within the record instance
  BestManager.BonusPayment.CashPayment := 10000;
  BestManager.BonusPayment.CompanyCar := TRUE;
  BestManager.BonusPayment.VacationWeeks := 2;
  
  --Output the results
  dbms_output.put_line('');
  dbms_output.put_line('Best manager SSN: ' || BestManager.ssn);
  dbms_output.put_line('Best manager bonus payment: $' || BestManager.BonusPayment.CashPayment);

  --Test for company car
  IF BestManager.BonusPayment.CompanyCar = TRUE THEN
    dbms_output.put_line ('Company car also provided');
  END IF;
  
  --Test for vacation weeks
  IF BestManager.BonusPayment.VacationWeeks > 0 THEN
    dbms_output.put_line ('Extra vacation weeks granted: ' || BestManager.BonusPayment.VacationWeeks);
  END IF;
  
/*  
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
*/
END;