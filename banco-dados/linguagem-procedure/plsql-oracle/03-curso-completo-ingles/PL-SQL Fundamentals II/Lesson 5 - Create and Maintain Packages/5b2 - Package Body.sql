CREATE OR REPLACE PACKAGE BODY Personnel AS

--Create function to test whether an SSN is associated to a manager
FUNCTION is_manager (input_ssn IN employee.ssn%TYPE)
RETURN BOOLEAN IS
  manager_count  NUMBER;
BEGIN
  --Retrieve count of SSN's
  SELECT  COUNT(mgrssn)
  INTO    manager_count
  FROM    department
  WHERE   mgrssn = input_ssn;

  --Test the count
  IF manager_count > 0 THEN
    RETURN (TRUE);
  ELSE
    RETURN (FALSE);
  END IF;
    
END is_manager;

--Create function to test whether an SSN is associated to a supervisor
FUNCTION is_supervisor (input_ssn IN employee.ssn%TYPE)
RETURN BOOLEAN IS
  supervisor_count  NUMBER;
BEGIN
  --Execute query to count records where the SSN is a supervisor
  SELECT  COUNT(superssn)
  INTO    supervisor_count
  FROM    employee
  WHERE   superssn = input_ssn;

  --Test the count
  IF supervisor_count > 0 THEN
    RETURN (TRUE);
  ELSE
    RETURN (FALSE);
  END IF;
    
END is_supervisor;

--Create function to test whether a salary is valid 
FUNCTION salary_valid
 (input_ssn     IN CHAR,
  input_salary  IN NUMBER) 
RETURN boolean IS
  count_management  NUMBER;
  count_projects    NUMBER;
  count_dependents  NUMBER;
  salary_limit      NUMBER;
BEGIN
  --Set the salary limit
  salary_limit := 50000;

  --Execute query to get a count of employees being managed
  SELECT count(*)
  INTO count_management
  FROM department
  WHERE department.mgrssn = input_ssn;

  --Test the count.  If valid, increase salary by $1,000
  IF count_management > 0 THEN
    salary_limit := salary_limit + 1000;
  END IF;

  --Execute query to count the number of projects worked
  SELECT count(*)
  INTO count_projects
  FROM works_on
  WHERE works_on.essn = input_ssn;

  --Calculate the salary increase by multiplying the project count by $2,000
  salary_limit := salary_limit + (count_projects * 2000);

  --Execute query to count the number of dependents
  SELECT count(*)
  INTO count_dependents
  FROM dependent
  WHERE dependent.essn = input_ssn;

  --Calculate the salary increase by multiplying the dependents by $3,000
  salary_limit := salary_limit + (count_dependents * 3000);

  --Test whether the calculated salary exceeds the limit
  IF input_salary > salary_limit THEN
    RETURN (FALSE);
  ELSE
    RETURN (TRUE);
  END IF;
  
END salary_valid;

--Create procedure testing whether the salary increase is valid
PROCEDURE raise_salary_valid
	(employee_ssn	  IN	CHAR,
 	 employee_pct	  IN	NUMBER	DEFAULT 5,
	 result_message	OUT CHAR) AS

  --Declare variables for the procedure
	old_salary		  employee.salary%TYPE;
	increase_amount	NUMBER;
	update_error		EXCEPTION;
BEGIN

  --Retrieve the current salary
	SELECT salary
	INTO old_salary
	FROM employee
	WHERE ssn = employee_ssn;

  --Test whether a salary was found
	IF old_salary IS NOT NULL
	AND old_salary > 0 THEN

    --Calculate the increase percentage passed to the procedure
    increase_amount := employee_pct / 100;

    --Call the function testing the new salary amount
    IF salary_valid (employee_ssn, old_salary + (old_salary * increase_amount)) THEN

      --Update the employee salary by increasing it using the calculated percentage
  		UPDATE employee
    	SET salary = salary + (salary * increase_amount)
      WHERE ssn = employee_ssn;

      --Test whether a record was updated.  If not, raise an exception
  		IF SQL%ROWCOUNT <> 1 THEN
    
        --Raise the exception
    		RAISE update_error;
        
      END IF;

    ELSE

      --The salary_valid function deemed the increase invalid
      result_message := 'Proposed salary invalid, no update issued';
    END IF;
	
  ELSE
    
    --Create the message for a record not found
  	result_message := 'Current salary is either NULL or 0';
    
	END IF;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		result_message := 'Employee ' || employee_ssn || ' not found';
	WHEN update_error THEN
		result_message := 'Database error';
		ROLLBACK;
	WHEN OTHERS THEN
		result_message := 'Unknown error';
END raise_salary_valid;

--Create procedure to remove dependent records associated to an SSN
PROCEDURE clear_dependents (input_ssn IN employee.ssn%TYPE) IS
BEGIN

  --Execute query deleting dependent records
  DELETE FROM dependent
  WHERE essn = input_ssn;  

END clear_dependents;

--Create procedure removing all employment information 
PROCEDURE clear_employment (input_ssn IN employee.ssn%TYPE) IS
BEGIN

  --Delete all records from the works_on table
  DELETE FROM works_on
  WHERE essn = input_ssn;

  --Delete the employee record
  DELETE FROM employee
  WHERE ssn = input_ssn;

  --Remove any links representing a supervisory role
  IF is_supervisor (input_ssn) THEN
  
    --Update the employee table
    UPDATE employee
    SET superssn = NULL
    WHERE superssn = input_ssn;
    
  END IF;

  --Remove any links representing a managerial role
  IF is_manager (input_ssn) THEN
  
    --Update the department table
    UPDATE department
    SET mgrssn = NULL
    WHERE mgrssn = input_ssn;

  END IF;

END clear_employment;

--Create procedure to hire employees
PROCEDURE hire_employee 
(  input_ssn       IN employee.ssn%TYPE,
   first_name      IN employee.fname%TYPE,
   last_name       IN employee.lname%TYPE,
   department_name IN department.dname%TYPE,
   input_salary    IN employee.salary%TYPE) IS

  --Declare variables used by the procedure
  new_department_number   department.dnumber%TYPE;
  
BEGIN

  BEGIN
  
    --Retrieve the department number based on the name passed
    SELECT dnumber
    INTO   new_department_number
    FROM   department
    WHERE  lower(department.dname) = lower(department_name);
  
  EXCEPTION
    --If no data found
    WHEN no_data_found THEN
  
      --Retrieve the next department number in the sequence
      SELECT department_sequence.NEXTVAL
      INTO new_department_number
      FROM dual;

      --Insert the new department information
      INSERT INTO department (dnumber, dname, mgrssn, mgrstartdate)
      VALUES (new_department_number, 
              INITCAP(department_name), 
              input_ssn,
              SYSDATE);
   END;

  --Insert the new employee data
  INSERT INTO employee (ssn, fname, lname, dno, salary)
  VALUES (input_ssn, first_name, last_name, 
          new_department_number, input_salary);
          
END hire_employee;

--Create procedure to fire an employee
PROCEDURE fire_employee (input_ssn IN employee.ssn%TYPE) IS
BEGIN

  --Test whether the employee is a manager or supervisor.  If so, you cannot fire him/her
  IF is_manager (input_ssn)
  OR is_supervisor (input_ssn) THEN
  
    --Output the message
    dbms_output.put_line ('Cannot fire a manager or supervisor');
  
  ELSE
  
    --Delete the employee
    DELETE FROM employee WHERE ssn = input_ssn;
    
    --Call procedures to remove dependents and employment detail
    CLEAR_DEPENDENTS (input_ssn);
    CLEAR_EMPLOYMENT (input_ssn);
  
  END IF;

END fire_employee;

--Create procedure to transfer an employee to a different department
PROCEDURE transfer_employee 
( input_ssn             IN employee.ssn%TYPE,
  new_department_number IN department.dnumber%TYPE) IS
BEGIN
  
  --Test whether the employee is a supervisor or manager
  IF is_supervisor(input_ssn) THEN
     dbms_output.put_line ('Cannot transfer a supervisor with subordinates');
  ELSIF is_manager(input_ssn) THEN

    --Update the employee table
    UPDATE employee
    SET dno = new_department_number
    WHERE employee.ssn = input_ssn;

    --Update the department table
    UPDATE department
    SET mgrssn = input_ssn
    WHERE department.dnumber = new_department_number;
  
  ELSE

    --Update the employee table
    UPDATE employee
    SET dno = new_department_number
    WHERE employee.ssn = input_ssn;
  
  END IF;

END transfer_employee;
 
END Personnel;
