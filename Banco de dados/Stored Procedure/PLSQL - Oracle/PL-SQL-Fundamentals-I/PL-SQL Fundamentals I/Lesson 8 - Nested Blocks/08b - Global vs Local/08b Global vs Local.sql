SET SERVEROUTPUT ON;

<<MainBlock>>
DECLARE

	--Declare the row type associated to the CURSOR
	EmpRecord  		    employee%ROWTYPE;
	ExceptionOccurred	BOOLEAN := FALSE;
	NoIdea			      EXCEPTION;
	
BEGIN

<<SelectEmp>>
	BEGIN
		--Retrieve the Employee
		SELECT *
		INTO EmpRecord
		FROM employee
		WHERE LName = '&Enter_Last_Name';
	
  EXCEPTION
		WHEN no_data_found THEN
			dbms_output.put_line('No employee with that last name, try again.');

		WHEN too_many_rows THEN
			dbms_output.put_line('Multiple employees with last name, try again.');

		WHEN OTHERS THEN
			RAISE NoIdea;
			
	END SelectEmp;
	
	--Test whether an exception occurred
	IF NOT ExceptionOccurred THEN
	
		--Output the running total
		dbms_output.put_line('Salary for: ' || EmpRecord.LName || 
                         ' is $' || EmpRecord.salary);
	
	END IF;
	
EXCEPTION
	--Test for generic error
	WHEN NoIdea THEN
		--Inform the user of the error
		dbms_output.put_line('Error occurred within the Inner Block.');
		dbms_output.put_line('Error code: ' || SQLCODE);
		dbms_output.put_line('Error message: ' || SQLERRM);

	--Universal Error Handler
	WHEN OTHERS THEN
		--Inform the user of the error
		dbms_output.put_line('Exception trapped by universal handler');
		dbms_output.put_line('Error code: ' || SQLCODE);
		dbms_output.put_line('Error message: ' || SQLERRM);

END MainBlock;
