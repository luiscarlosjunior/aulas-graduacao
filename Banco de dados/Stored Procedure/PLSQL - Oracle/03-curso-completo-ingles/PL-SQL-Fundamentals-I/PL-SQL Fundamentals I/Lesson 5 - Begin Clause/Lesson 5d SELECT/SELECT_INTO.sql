SET SERVEROUTPUT ON;

/*
Author:   Tim Miles
Date:     3/23/2013
Purpose:  This routine shows how to use the SELECT...INTO
	  statement.
*/
DECLARE

  --Declare the RECORD
	TYPE EmpRecord
	  IS RECORD  (ssn       employee.ssn%TYPE,
                LName     employee.Lname%TYPE,
                Dname     department.Dname%TYPE,
                BonusPay  NUMBER(6));

  --Declare the object
	InactiveEmp  EmpRecord;	

BEGIN
  
  --Select the detail into the object
	SELECT essn, Lname, Dname, 0
	INTO InactiveEmp
	FROM employee, department, works_on
	WHERE employee.dno = department.dnumber
	AND employee.ssn = works_on.essn
	AND hours = (SELECT MIN(hours) FROM works_on)
	AND ROWNUM <= 1;

	--Show the results
	dbms_output.put_line('The employee is: ' || InactiveEmp.LName);
	
END;