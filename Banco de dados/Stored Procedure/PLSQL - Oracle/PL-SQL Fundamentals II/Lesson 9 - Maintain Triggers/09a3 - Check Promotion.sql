CREATE OR REPLACE PROCEDURE check_promotion (
  ManagerSSN IN department.mgrssn%TYPE,
  DeptNumber IN department.dnumber%TYPE) AS

  --Declare variables
 	x_dno	employee.dno%TYPE;
  
BEGIN

  --Run query to determine whether the employee is a manager
	SELECT dno
	INTO x_dno
	FROM employee
	WHERE employee.ssn = ManagerSSN;

  --Test whether the department numbers match
	IF x_dno <> DeptNumber THEN
  
    --Raise the application error to end processing
    RAISE_APPLICATION_ERROR(-20000, 'Manager must be promoted from within the department');
 	
  END IF;

EXCEPTION
	WHEN NO_DATA_FOUND THEN

    --Raise the error that the person is not a manager
		RAISE_APPLICATION_ERROR(-20001, 'Manager is not a current employee');
    
END;