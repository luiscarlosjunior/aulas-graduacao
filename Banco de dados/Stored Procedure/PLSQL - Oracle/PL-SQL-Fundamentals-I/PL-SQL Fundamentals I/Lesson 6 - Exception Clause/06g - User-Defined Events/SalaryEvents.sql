SET SERVEROUTPUT ON;

DECLARE

	--Declare exceptions
	TooMuchMoney	  EXCEPTION;
	TooLittleMoney  EXCEPTION;

	--Declare constants
	SalaryMin       CONSTANT NUMBER(5) := 27000;
	SalaryMax       CONSTANT NUMBER(5) := 55000;

	--Declare variables
	EmployeeSSN     employee.ssn%TYPE;
	OldSalary       employee.salary%TYPE;
	NewSalary       employee.salary%TYPE;

BEGIN

	--Retrieve the salary information for the desired employee
	SELECT ssn, salary
	INTO EmployeeSSN, OldSalary
	FROM  employee 
	WHERE ssn = &prompt_for_ssn;

	--Test the Salary
	IF OldSalary <= SalaryMin THEN
		RAISE TooLittleMoney;
	END IF;
	
	--Calculate the new salary
	NewSalary := OldSalary * 1.25;
	
	--Test the new Salary
	IF NewSalary >= SalaryMax THEN
		RAISE TooMuchMoney;
	END IF;
	
	--Update the salary for the employee
	UPDATE employee
	SET salary = NewSalary
	WHERE ssn = EmployeeSSN;
	
EXCEPTION

	--Test for the TooLittleMoney Event
	WHEN TooLittleMoney THEN
		--Output the detail to the user
		dbms_output.put_line('No raise attempted.  Base salary too low.');

	--Test for the TooMuchMoney Event	
	WHEN TooMuchMoney THEN
		--Output the detail to the user
		dbms_output.put_line('Raise attempted but new salary is too high');

END;