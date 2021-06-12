SET SERVEROUTPUT ON;

DECLARE

  --Declare RECORD object
  TYPE EmpRecord
    IS RECORD (ssn          employee.ssn%TYPE,  
               LName        employee.LName%TYPE,
               DName        department.DName%TYPE,
               BonusPayment NUMBER(6));
               
  --Define the variable
  InactiveEmp EmpRecord;

BEGIN
	--Retrieve the first employee
	SELECT essn, LName, DName, 0
	INTO InactiveEmp
	FROM  employee emp
	INNER JOIN department dep on emp.dno = dep.dnumber
	INNER JOIN works_on wo on emp.ssn = wo.essn
	WHERE hours = (SELECT MIN(hours) FROM works_on)
	AND ROWNUM <= 1;

	--Output the employee name
	dbms_output.put_line('Inactive employee about to be transferred: ' ||
			      InactiveEmp.LName);

	--Remove this employee as a manager of any department.
	UPDATE department
	SET MgrSSN = NULL
	WHERE MgrSSN = InactiveEmp.ssn;

	--Test the cursor
	IF SQL%FOUND THEN
		dbms_output.put_line('Departments removed as manager: ' ||
				      SQL%ROWCOUNT);
	END IF;

	--Remove this employee as a supervisor of other employees.
	UPDATE employee
	SET SuperSSN = NULL
	WHERE SuperSSN = InactiveEmp.ssn;

	--Test the cursor
	IF SQL%FOUND THEN
		dbms_output.put_line('Employees removed as supervisor: ' ||
				      SQL%ROWCOUNT);
	END IF;

	--Delete any dependetns and all WORKS_ON rows.
	DELETE FROM dependent
	WHERE essn = InactiveEmp.ssn;

	--Test the cursor
	IF SQL%FOUND THEN
		dbms_output.put_line('Dependent records deleted: ' ||
				      SQL%ROWCOUNT);
	END IF;

	DELETE FROM works_on
	WHERE essn = InactiveEmp.ssn;

	--Test the cursor
	IF SQL%FOUND THEN
		dbms_output.put_line('WORKS_ON records deleted: ' ||
				      SQL%ROWCOUNT);
	END IF;

	--Delete this employee from teh EMPLOYEE table itself
	DELETE FROM employee
	WHERE ssn = InactiveEmp.ssn;

	--Test the cursor
	IF SQL%FOUND THEN
		dbms_output.put_line('Employee records deleted: ' ||
				      SQL%ROWCOUNT);
	END IF;

	--Transaction control statement to complete the transaction
	COMMIT;

	--Output the detail to the user
	dbms_output.put_line('Least active employee has been transferred: ' ||
			 InactiveEmp.LName);

END;