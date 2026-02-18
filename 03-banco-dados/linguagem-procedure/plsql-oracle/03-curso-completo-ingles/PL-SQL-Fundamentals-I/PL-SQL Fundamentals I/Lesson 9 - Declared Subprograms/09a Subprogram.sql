set serveroutput on;

DECLARE

	--Declare variables
	EmpLName  	employee.LName%TYPE;
	EmpSSN		  employee.ssn%TYPE := '&enter_employee_ssn';

  --Declare the subprogram.  Notice the name is 'Display'
	PROCEDURE Display (MessageText VARCHAR2) IS
	
    --Declare variables used by subprogram
		TempMessageText  VARCHAR2(80);
	
	BEGIN
	
		--Test the length
		IF LENGTH(MessageText) > 40 THEN
		
      --Shorten the message
			TempMessageText := SUBSTR(MessageText, 1, 40);
			
		ELSE
    
      --Compile the message
			TempMessageText := 'Message generated on ' ||
					    TO_CHAR(SYSDATE, 'fmDAY') || ': ' ||
					    UPPER(MessageText);
					    
		END IF;

		--Output the running total
		dbms_output.put_line(TempMessageText);
		
	END Display;
	
BEGIN

	--Retrieve the Last Name for the entered SSN
	SELECT LName
	INTO EmpLName
	FROM employee
	WHERE ssn = EmpSSN;
	
	--Display the detail 
	Display ('Employee ' || EmpSSN || ' is ' || EmpLName);

EXCEPTION
	--Test for wrong number of rows
	WHEN too_many_rows OR no_data_found THEN
		Display('Database error');
	
	--Test for generic error
	WHEN OTHERS THEN
		Display('Unknown error');

END MainBlock;
