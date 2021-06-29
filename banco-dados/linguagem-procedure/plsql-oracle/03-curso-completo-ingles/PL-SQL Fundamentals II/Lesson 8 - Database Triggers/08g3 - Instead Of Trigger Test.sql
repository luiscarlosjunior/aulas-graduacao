--Issue the delete DML statement using the View
DELETE FROM employee_department_info
WHERE employee_ssn = 123456789;

--Verify the department info
SELECT *
FROM employee_department_info;

--Verify the employee info
SELECT ssn, lname
FROM employee;