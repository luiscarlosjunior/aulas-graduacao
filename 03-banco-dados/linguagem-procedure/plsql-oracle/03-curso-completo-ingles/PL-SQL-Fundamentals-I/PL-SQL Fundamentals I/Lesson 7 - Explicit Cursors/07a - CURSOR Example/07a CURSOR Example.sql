set serveroutput on;

DECLARE

	--Declare the cursor
	CURSOR Employees IS
	  SELECT *
	  FROM employee;

	--Declare the row type associated to the CURSOR
	EmpRecords  employee%ROWTYPE;

BEGIN
	--Open the CURSOR
	OPEN Employees;

	--Begin the Indefinite LOOP
	LOOP
		--Retrieve the CURSOR data into the holding object
		FETCH Employees INTO EmpRecords;
		
		--Test whether a record was found
		EXIT WHEN Employees%NOTFOUND;

		--Display the record detail
		dbms_output.put_line('Employees ' || EmpRecords.LName || 
				     ' earns $' || EmpRecords.Salary);

  	END LOOP;

	--Close the CURSOR
	CLOSE Employees;

END;
