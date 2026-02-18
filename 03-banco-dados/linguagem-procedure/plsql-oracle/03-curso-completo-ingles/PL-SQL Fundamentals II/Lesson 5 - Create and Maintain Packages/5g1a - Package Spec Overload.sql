/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

CREATE OR REPLACE PACKAGE personnel_overload AS

--Define procedure for hiring salary employee
PROCEDURE hire_employee 
(  input_ssn       IN employee.ssn%TYPE,
   first_name      IN employee.fname%TYPE,
   last_name       IN employee.lname%TYPE,
   department_name IN department.dname%TYPE,
   input_salary    IN employee.salary%TYPE);

--Define procedure for hiring hourly employee
PROCEDURE hire_employee 
(  input_ssn       IN employee.ssn%TYPE,
   first_name      IN employee.fname%TYPE,
   last_name       IN employee.lname%TYPE,
   department_name IN department.dname%TYPE,
   hourly_pay      IN NUMBER,
   union_member    IN BOOLEAN);

--Define procedure to fire an employee
PROCEDURE fire_employee (input_ssn IN employee.ssn%TYPE);

--Define procedure to transfer an employee
PROCEDURE transfer_employee 
(input_ssn             IN employee.ssn%TYPE,
 new_department_number IN department.dnumber%TYPE);

--Define procedure to raise the salary
PROCEDURE raise_salary_valid
	(employee_ssn	IN	CHAR,
 	 employee_pct	IN	NUMBER	DEFAULT 5,
	 result_message	OUT CHAR);

END personnel_overload;
/
