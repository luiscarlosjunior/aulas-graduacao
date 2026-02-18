CREATE OR REPLACE PROCEDURE error_salary 
( 
	employee_ssn IN CHAR, 
	employee_pct IN NUMBER DEFAULT 5, 
	result_message OUT CHAR 
) 
AS 
	old_salary employee.salary%TYPE; 
	increase_amount NUMBER; 

/* 
Program-defined exceptions are declared here and are used to identify 
exception events which will interrupt main program execution. 
*/ 
	pct_too_high EXCEPTION;
	update_error EXCEPTION;

BEGIN 
	--Disallow raises which exceed 50% on the basis of the business rules. 
	IF employee pct > 50 THEN 
		RAISE pct too_high; 
	END IF; 

	--Retrieve the salary from the employee table
	SELECT salary 
	INTO old salary 
	FROM employee 
	WHERE ssn = employee_ssn; 

/* 
If the existing salary is unknown or NULL, or if it is 0, then no raise is 
possible. Otherwise, compute the raise amount and issue an update to the 
database. 
*/ 
	IF (old_salary IS NOT NULL) AND (old_salary > 0) THEN

		--Convert the employee pct parameter value to a 
		--numeric percentage and update the appropriate 
		--database column. 
		increase_amount := employee_pct / 100; 

		UPDATE employee 
		SET salary = salary + (salary * increase_amount) 
		WHERE ssn = employee_ssn; 
		
		IF SQL%ROWCOUNT <> 1 THEN 
			RAISE update_error
		END IF; 

		--Set the output parameter value if necessary. 
	ELSE
	
		--Set the message
		result_message := 'Current salary is either NULL or 0';

	END IF;
	
EXCEPTION
/*
Set the output parameter value here as well based upon program-defined and
system-defined exceptions which might occur.
*/
	WHEN pct_too_high THEN
		result_message := 'Raise percentage may not exceed 50%'
		
	WHEN NO_DATA_FOUND THEN
		result_message := 'Employee ' || employee_ssn || ' not found'
	
	WHEN update_error THEN
		result_message := 'Database error'
	
	WHEN OTHERS THEN
		result_message := 'Unknown error';

END raise_salary;