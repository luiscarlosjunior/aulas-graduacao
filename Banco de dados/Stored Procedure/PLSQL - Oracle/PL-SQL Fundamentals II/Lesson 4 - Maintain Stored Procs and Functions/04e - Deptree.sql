--Fire the Dependency Tracking Utility
EXECUTE deptree_fill('table', 'system', 'employee');

--Query the results of the utility
SELECT * FROM ideptree;