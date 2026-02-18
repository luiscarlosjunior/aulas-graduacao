--Update the employee Borg
UPDATE employee
SET salary = 75000
WHERE lname = 'Borg';

--See if the record was added to the audit table
SELECT * FROM audit_entry;