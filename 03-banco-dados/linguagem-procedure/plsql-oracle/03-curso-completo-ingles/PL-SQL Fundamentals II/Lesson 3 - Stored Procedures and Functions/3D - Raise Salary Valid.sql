/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

CREATE OR REPLACE PROCEDURE raise_salary_valid
	(employee_ssn	  IN	CHAR,
 	 employee_pct	  IN	NUMBER DEFAULT 5,
	 result_message	OUT CHAR)
AS
  old_salary      employee.salary%TYPE;
	increase_amount	NUMBER;
	update_error		EXCEPTION;

BEGIN

  --Determine the old salary
	SELECT salary
	INTO old_salary
	FROM employee
	WHERE ssn = employee_ssn;

  --Ensure the salary is valid
	IF (old_salary IS NOT NULL) AND (old_salary > 0) THEN

    --Calculate the increase amount
    increase_amount := employee_pct / 100;

    --Test whether the new salary is valid using the Salary_Valid function
    IF salary_valid (employee_ssn, old_salary + (old_salary * increase_amount)) THEN

      --Update the salary
      UPDATE employee
      SET salary = salary + (salary * increase_amount)
      WHERE ssn = employee_ssn;

      --Test whether a record was updated
      IF SQL%ROWCOUNT <> 1 THEN
        
        --Record was not updated, flag error
        RAISE update_error;
        
      END IF;
    
    ELSE
      
      --Salary was not valid, set the resulting message
      result_message := 'Proposed salary invalid, no update issued';
            
    END IF;

	ELSE
		
    --The old salary was not found, set the resulting message
    result_message := 'Current salary is either NULL or 0';
    
	END IF;

EXCEPTION
  --Set the message for no data found
	WHEN NO_DATA_FOUND THEN
		result_message := 'Employee ' || employee_ssn || ' not found';

  --Set the message for any update errors and perform ROLLBACK
	WHEN update_error THEN
		result_message := 'Database error';
		ROLLBACK;

	WHEN OTHERS THEN
		result_message := 'Unknown error';

END raise_salary_valid;
/

