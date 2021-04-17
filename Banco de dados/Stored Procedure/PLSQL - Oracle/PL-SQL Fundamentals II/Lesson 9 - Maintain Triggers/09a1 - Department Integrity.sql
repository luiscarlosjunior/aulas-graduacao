CREATE OR REPLACE TRIGGER DepartmentIntegrity
  BEFORE UPDATE OF mgrssn ON DEPARTMENT
  FOR EACH ROW
DECLARE

  --Declare variables
 	x_dno	employee.dno%TYPE;

BEGIN

  --Run query to determine whether the employee is a manager
	SELECT dno
	INTO x_dno
	FROM employee
	WHERE employee.ssn = :new.mgrssn;

  --Test whether the department numbers match
	IF x_dno <> :NEW.dnumber THEN
  
    --Raise the application error to end processing
    RAISE_APPLICATION_ERROR(-20000, 'Manager must be promoted from within the department');
 	
  END IF;

EXCEPTION
	WHEN NO_DATA_FOUND THEN

    --Raise the error that the person is not a manager
		RAISE_APPLICATION_ERROR(-20001, 'Manager is not a current employee');
    
END;