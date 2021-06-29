--Update the employee Borg
UPDATE employee
SET salary = 90000
WHERE lname = 'Borg';

--Add a new employee
INSERT INTO employee
  (fname, minit, lname, ssn, bdate, address, sex, salary, superssn, dno)
VALUES
  ('John', 'R', 'McMillan', '011325555', '19-JUN-66', '55 Main, Springfield, OH',
   'M', 69900, '888665555', 3);

--See if the record was added to the budget request table
SELECT * FROM budget_request;
