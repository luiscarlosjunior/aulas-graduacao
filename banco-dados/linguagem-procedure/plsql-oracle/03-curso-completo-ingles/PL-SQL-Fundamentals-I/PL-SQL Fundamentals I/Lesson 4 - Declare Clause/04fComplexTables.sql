SET SERVEROUTPUT ON;

DECLARE

  --Declare the table
  TYPE EmpSSNarray
    IS TABLE OF employee.ssn%TYPE
    INDEX BY SIMPLE_INTEGER;
    
  --Declare variables using the table
  ManagementList  EmpSSNarray;
  WorkerList      EmpSSNarray;
  
BEGIN

  --Retrieve the first Supervisor
  SELECT superssn
  INTO ManagementList(1)
  FROM employee
  WHERE superssn IS NOT NULL
  AND ROWNUM <= 1;
  
  --Retrieve the second Supervisor
  SELECT superssn
  INTO ManagementList(2)
  FROM employee
  WHERE superssn IS NOT NULL
  AND ROWNUM <= 1
  AND superssn <> ManagementList(1);
  
  --Retrieve the first worker
  SELECT essn
  INTO WorkerList(1)
  FROM works_on
  WHERE hours IS NOT NULL
  AND ROWNUM <= 1
  AND essn NOT IN (ManagementList(1), ManagementList(2));
  
  --Retrieve the second worker
  SELECT essn
  INTO WorkerList(2)
  FROM works_on
  WHERE hours IS NOT NULL
  AND ROWNUM <= 1
  AND essn NOT IN (ManagementList(1), ManagementList(2), WorkerList(1));
  
  --Output the results
  dbms_output.put_line ('Managers are: ' || ManagementList(1) || ', ' || ManagementList(2));
  dbms_output.put_line ('Workers are: ' || WorkerList(1) || ', ' || WorkerList(2));
  
END;