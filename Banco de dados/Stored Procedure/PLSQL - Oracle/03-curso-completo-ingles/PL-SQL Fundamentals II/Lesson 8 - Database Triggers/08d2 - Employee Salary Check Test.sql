--Attempt to update Smith
--This should fail because Smith is not a manager
UPDATE employee
SET salary = 71000
WHERE lname = 'Smith';

--Attempt to update Borg
--This should succeed because Borg is a manager
UPDATE employee
SET salary = 71000
WHERE lname = 'Borg';
