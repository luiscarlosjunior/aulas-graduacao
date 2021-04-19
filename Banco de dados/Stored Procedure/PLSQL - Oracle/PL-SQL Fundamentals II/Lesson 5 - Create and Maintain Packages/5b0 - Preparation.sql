SELECT object_name, object_type
FROM user_objects
WHERE object_type IN ('PROCEDURE', 'FUNCTION')
AND object_name <> 'DEPTREE_FILL'
ORDER BY object_type;

DROP FUNCTION salary_valid;

DROP FUNCTION is_manager;

DROP FUNCTION is_supervisor;

DROP PROCEDURE raise_salary_valid;

DROP PROCEDURE clear_dependents;

DROP PROCEDURE clear_employment;

DROP PROCEDURE hire_employee;

DROP PROCEDURE fire_employee;

DROP PROCEDURE transfer_employee;

--Create a new sequence
CREATE SEQUENCE department_sequence
    START WITH 10
    MAXVALUE 99;