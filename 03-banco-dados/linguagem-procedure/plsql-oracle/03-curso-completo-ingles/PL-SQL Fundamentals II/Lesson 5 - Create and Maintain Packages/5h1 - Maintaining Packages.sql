--Use the DepTree_Fill procedure to display dependencies
EXECUTE deptree_fill ('table', 'system', 'employee');

--View the results
SELECT * FROM ideptree;

-----------------------------------------------------

--Use the USER_OBJECTS view to gather information about our packages
SELECT object_name, object_type, status
FROM user_objects
WHERE object_type IN ('PACKAGE', 'PACKAGE BODY');

-----------------------------------------------------

--Recompile the BODY
ALTER PACKAGE personnel COMPILE BODY;

--Recompile the entire package
ALTER PACKAGE personnel COMPILE;
