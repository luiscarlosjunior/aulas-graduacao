SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script shows how to use SQL Functions.
*/
DECLARE

	--Declare the type
	TYPE EmpRecord
		IS RECORD (ssn		employee.ssn%TYPE,
			   LName	employee.lname%TYPE,
			   DName	department.DName%TYPE,
			   BonusPayment NUMBER(6)
			   );
			   
	--Declare variables
	BestEmp		EmpRecord;
	
BEGIN

	--Fill the Type
	SELECT essn, LName, DName, 5000
	INTO BestEmp
	FROM employee, department, works_on
	WHERE employee.dno = department.dnumber
	AND employee.ssn = works_on.essn
	AND hours = (SELECT MAX(hours) FROM works_on)
	AND ROWNUM <= 1;
	
	--Display the message to the user
	dbms_output.put_line('Best employee name: ' || UPPER(BestEmp.LName));
	
	--Display the message to the user
	dbms_output.put_line('Best employee bonus: ' || ROUND(BestEmp.BonusPayment * 1.15, -3));

END;
/