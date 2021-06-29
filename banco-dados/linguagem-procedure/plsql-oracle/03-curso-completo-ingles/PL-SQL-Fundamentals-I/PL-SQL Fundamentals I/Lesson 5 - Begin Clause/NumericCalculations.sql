SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script shows how to calculate numeric values.
*/
DECLARE

	--Declare variables
	EmpSalary	employee.Salary%TYPE;
	EmpRaisePct	NUMBER(2);
	
BEGIN

	--Set the variables
	EmpSalary := 50000;
	EmpRaisePct := 10;
	
	--Display the starting salary
	dbms_output.put_line('Current Salary: ' || EmpSalary);

	--Calculate the new salary
	EmpSalary := EmpSalary + (EmpSalary * (EmpRaisePct / 100));
	
	--Display the results	
	dbms_output.put_line('New Salary: ' || EmpSalary);

END;
/