SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script shows how to calculate Boolean values.
*/
DECLARE

	--Declare variables
	EmpSalary	employee.Salary%TYPE;
	HighPaid	BOOLEAN := FALSE;
	
BEGIN

	--Set the variables
	EmpSalary := &enter_salary_amount;
	HighPaid := (EmpSalary > 65000);
	
	--Test the HighPaid result
	IF HighPaid THEN
	
		--Display the message to the user
		dbms_output.put_line('Yes, this salary is high');

	ELSE
	
		--Display the message to the user
		dbms_output.put_line('No, this salary is fine.');

	END IF;
	
END;
/