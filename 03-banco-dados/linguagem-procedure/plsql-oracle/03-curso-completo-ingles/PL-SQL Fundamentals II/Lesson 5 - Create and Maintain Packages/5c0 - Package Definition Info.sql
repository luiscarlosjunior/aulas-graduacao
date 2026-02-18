--Get the system description for the personnel package
DESCRIBE personnel;

--Get package definition
SELECT object_name, object_type, status
FROM user_objects
WHERE object_type IN ('PACKAGE', 'PACKAGE BODY');
