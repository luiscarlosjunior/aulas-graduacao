--View current status of each of the objects
SELECT object_name, status
FROM user_objects
WHERE object_type IN('PROCEDURE', 'FUNCTION')
ORDER BY object_name;

--Delete the EMPLOYEE table
DROP TABLE employee;

--View current status of each of the objects
SELECT object_name, status
FROM user_objects
WHERE object_type IN('PROCEDURE', 'FUNCTION')
ORDER BY object_name;

