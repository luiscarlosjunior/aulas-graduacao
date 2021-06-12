--Create the view for this exercise
CREATE OR REPLACE VIEW employee_department_info
      (employee_name, department_name, employee_ssn) AS
  SELECT lname, dname, ssn
  FROM employee
    INNER JOIN department ON (employee.dno = department.dnumber)
  ORDER BY lname;

--View the results of the View
SELECT * 
FROM employee_department_info;

--Attempt to delete a record from the view
--Note: This will fail.
DELETE FROM employee_department_info
WHERE employee_ssn = 123456789;
