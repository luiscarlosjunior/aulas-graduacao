set serveroutput on;

DECLARE

	--Declare the cursor
	CURSOR Employees IS
	  SELECT *
	  FROM employee
    where salary > 30000
	  ORDER BY salary DESC;

	--Declare the row type associated to the CURSOR
	EmpRecords  employee%ROWTYPE;

BEGIN
	--Open the CURSOR
	IF NOT (Employees%ISOPEN) THEN
		OPEN Employees;
	END IF;
	
	--Begin the Indefinite LOOP
	LOOP
		--Retrieve the CURSOR data into the holding object
		FETCH Employees INTO EmpRecords;
		
		--Test whether a record was found
		EXIT WHEN Employees%NOTFOUND;

		--Display the record detail
		dbms_output.put_line('Employees number ' || 
				     Employees%ROWCOUNT || ' ' ||
				     EmpRecords.LName || 
				     ' earns $' || EmpRecords.Salary);

  END LOOP;

	--Close the CURSOR
	IF Employees%ISOPEN THEN
		CLOSE Employees;
	END IF;
	
END;
