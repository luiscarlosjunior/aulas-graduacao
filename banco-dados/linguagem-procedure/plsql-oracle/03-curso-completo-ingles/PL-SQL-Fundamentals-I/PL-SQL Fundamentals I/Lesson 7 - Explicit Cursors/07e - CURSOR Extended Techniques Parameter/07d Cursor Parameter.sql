set serveroutput on;

DECLARE

	--Declare the cursor
	CURSOR Employees (SelectDept department.dnumber%TYPE) IS
	  SELECT *
	  FROM employee
	  WHERE (dno = SelectDept)
	FOR UPDATE OF salary;

	--Declare holding variable
	SelectDept	department.dnumber%TYPE;

	--Declare the row type associated to the CURSOR
	EmpRecord  	employee%ROWTYPE;

	--Declare variables for the pay
	PayCut		employee.salary%TYPE := 0;
	ReductionTotal	PayCut%TYPE := 0;
	
	--Declare dependent variables
	DependentCount	INTEGER;
	HasDependents	BOOLEAN := FALSE;
	
	--Declare work variables
	HoursSum	works_on.hours%TYPE;
	WorksHard	BOOLEAN := FALSE;
	
	--Declare misc variables
	DayOfMonth	INTEGER;
	TooLateInMonth	EXCEPTION;
	
BEGIN
	--Retrieve the Day of Month
	SELECT TO_CHAR(SYSDATE, 'DD')
	INTO DayOfMonth
	FROM DUAL;
	
	--Test the day
	IF DayOfMonth > 25 THEN
		Raise TooLateInMonth;
	END IF;

	--Open the CURSOR
	IF NOT (Employees%ISOPEN) THEN
	
		--Retrieve department variable for the parameter
		SELECT d.dnumber
		INTO SelectDept
		FROM department d
		      INNER JOIN dept_locations dl on d.dnumber = dl.dnumber
		WHERE ((dl.dlocation = 'Stafford')
		      AND (ROWNUM <= 1));

		--Open the cursor passing the parameter
		OPEN Employees (SelectDept);
    
	END IF;
	
	--Begin the Indefinite LOOP
	LOOP
		--Retrieve the CURSOR data into the holding object
		FETCH Employees INTO EmpRecord;
		
		--Test whether a record was found
		EXIT WHEN Employees%NOTFOUND;

		--Calculate the Pay Cut
		PayCut := EmpRecord.salary * .10;
		
		--Retrieve the number of dependents
		SELECT COUNT(*)
		INTO DependentCount
		FROM dependent
		WHERE essn = EmpRecord.ssn;
		
		--Set the boolean variable
		HasDependents := (DependentCount > 0);
		
		--Retrieve the total number of hours for the current employee
		SELECT SUM(hours)
    INTO HoursSum
		FROM works_on
		WHERE essn = EmpRecord.ssn;
		
		--Set the Works Hard variable
		WorksHard := (HoursSum > 40);
		
		--Test the Dependents and WorksHard variables
		CASE
			WHEN HasDependents THEN PayCut := PayCut - 100;
			WHEN WorksHard THEN PayCut := PayCut - 50;
			ELSE NULL;
		END CASE;
		
		--Update the Employee record
		UPDATE employee
		SET salary = salary - PayCut
		WHERE CURRENT OF Employees;
		
		--Ouput the detail to the user
		dbms_output.put_line('Salary for ' || EmpRecord.LName ||
                         ' reduced by $' || PayCut);
				     
		--Calculate running totals
		ReductionTotal := ReductionTotal + PayCut;
		HasDependents := FALSE;
		WorksHard := FALSE;

  END LOOP;

	--Commit the changes
	COMMIT;

	--Close the CURSOR
	IF Employees%ISOPEN THEN
		CLOSE Employees;
	END IF;
	
	--Output the running total
	dbms_output.put_line('Total salary reduction: $' || ReductionTotal);
	
EXCEPTION
	--Test for too late in month
	WHEN TooLateInMonth THEN
		dbms_output.put_line('No salary changes permitted after the 25th, program ending');
	
	--Test for generic error
	WHEN OTHERS THEN
		--Inform the user of the error
		dbms_output.put_line('Unknown error, details below:');
		dbms_output.put_line('Error code: ' || SQLCODE);
		dbms_output.put_line('Error message: ' || SQLERRM);

		--Test whether the cursor is open
		CASE EMPLOYEES%ISOPEN
			WHEN TRUE THEN dbms_output.put_line('Cursor is currently open');
			WHEN FALSE THEN dbms_output.put_line('Cursor is currently closed');
		END CASE;

END;
