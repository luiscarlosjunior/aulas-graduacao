SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		3/19/2013
Purpose:	This script shows how to concatenate VARCHAR() values.
*/
DECLARE

	--Declare variables
	EmpFname	employee.fname%TYPE;
	EmpLname	employee.lname%TYPE;
	EmpFullName	VARCHAR(50);
	
BEGIN

	--Set the variables
	EmpFname := 'Jane';
	EmpLname := 'Smith';
	
	--Compile the name
	EmpFullName := EmpLname || ', ' || EmpFname;
	
	--Display the name
	dbms_output.put_line('Employee Name: ' || EmpFullName);

END;
/