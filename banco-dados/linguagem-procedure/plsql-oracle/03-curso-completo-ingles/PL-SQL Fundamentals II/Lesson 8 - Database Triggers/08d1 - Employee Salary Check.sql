CREATE OR REPLACE TRIGGER EMPLOYEE_SALARY_CHECK
  BEFORE INSERT OR UPDATE OF salary ON employee
  FOR EACH ROW
  WHEN (new.salary > 70000)
DECLARE

  --Declare variables
  x_mgrssn      department.mgrssn%TYPE;
  message_text  VARCHAR2(100);
  
BEGIN

  --Execute query to determine whether the ssn refers to a manager
	SELECT MGRSSN
	INTO X_MGRSSN
	FROM department
	WHERE mgrssn = :new.ssn;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
    --Set the message text
    message_text := 'Must be manager for salary of ' || TO_CHAR(:NEW.SALARY);
    
    --Raise application error to cancel operation
		RAISE_APPLICATION_ERROR(-20001, message_text);
    
END;