CREATE OR REPLACE PACKAGE personnel AS

--Define hire_employee procedure
PROCEDURE hire_employee 
(  input_ssn       IN employee.ssn%TYPE,
   first_name      IN employee.fname%TYPE,
   last_name       IN employee.lname%TYPE,
   department_name IN department.dname%TYPE,
   input_salary    IN employee.salary%TYPE);

--Define procedure to fire employees
PROCEDURE fire_employee (input_ssn IN employee.ssn%TYPE);

--Define procedure to transfer employees to different department
PROCEDURE transfer_employee 
( input_ssn             IN employee.ssn%TYPE,
  new_department_number IN department.dnumber%TYPE);

--Define procedure to test salary increases
PROCEDURE raise_salary_valid
	(employee_ssn	  IN	CHAR,
 	 employee_pct	  IN	NUMBER	DEFAULT 5,
	 result_message	OUT CHAR);

END personnel;