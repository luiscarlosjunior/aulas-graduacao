/*
-----------------
USER_OBJECTS
-----------------
*/

--Use the DESCRIBE command to display information about the User_Objects view
DESCRIBE user_objects

--Query the User_Objects view for all procedures and functions
SELECT object_name, status
FROM user_objects
WHERE object_type IN('PROCEDURE', 'FUNCTION');

/*
-----------------
USER_SOURCE
-----------------
*/

--Use the DESCRIBE command to display information about the User_Source view
DESCRIBE user_source

--Determine which objects have source code
SELECT DISTINCT name, type
FROM user_source;

--Retrieve the code for the raise_salary procedure
SELECT text
FROM user_source
WHERE name = 'RAISE_SALARY'
ORDER BY line;


/*
-----------------
USER_ERRORS
-----------------
*/

--Use the DESCRIBE command to display information about the User_Errors view
DESCRIBE user_errors

--Execute a query retrieving all errors for the Error_Salary procedure
SELECT line, text, attribute, message_number
FROM user_errors
WHERE name = 'ERROR_SALARY'
ORDER BY line;


/*
-----------------
USER_OBJECT_SIZE
-----------------
*/

--Use the DESCRIBE command to display information about the User_Object_Size view
DESCRIBE user_object_size

--Query the user_object_size to ascertain storage information for our objects.
SELECT *
FROM user_object_size
WHERE type IN('PROCEDURE', 'FUNCTION');


/*
-----------------
USER_DEPENDENCIES
-----------------
*/

--Query the USER_DEPENDENCIES to find the dependency information on the 
--RAISE_SALARY_VALID procedure
SELECT referenced_name, referenced_type
FROM user_dependencies
WHERE name = 'RAISE_SALARY_VALID';