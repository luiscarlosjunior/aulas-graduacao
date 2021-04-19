CREATE OR REPLACE PACKAGE employee_data
AS

  --Define procedure to open employee data
  PROCEDURE open_employee_data (
          employee_cv   IN OUT SYS_REFCURSOR,
          x_dno         IN     employee.dno%TYPE); 

  --Define procedure to fetch employee data
  PROCEDURE fetch_employee_data (
          employee_cv      IN    SYS_REFCURSOR,
          employee_output  OUT   VARCHAR2);
          
END employee_data;
/

CREATE OR REPLACE PACKAGE BODY employee_data 
AS

  --Procedure to open the cursor
  PROCEDURE open_employee_data (
    employee_cv   IN OUT SYS_REFCURSOR,
    x_dno         IN     employee.dno%TYPE) 
  IS
  
    --Define private variable
    dependent_count INTEGER;
  
  BEGIN
  
    --Execute query retrieving the dependent count
    SELECT COUNT(*)
    INTO dependent_count
    FROM employee
      INNER JOIN dependent ON employee.ssn = dependent.essn
    WHERE employee.dno = x_dno;
    
    --Test the dependent count
    IF dependent_count < 2 THEN
    
      --Open the cursor retrieving employee detail
      OPEN employee_cv FOR
        SELECT *
        FROM employee
        WHERE employee.dno = x_dno;
    
    ELSE
      --Open the cursor retrieving dependent detail
      OPEN employee_cv FOR
        SELECT dependent.*
        FROM dependent
          INNER JOIN employee ON employee.ssn = dependent.essn
        WHERE employee.dno = x_dno;
    
    END IF;
  
  END open_employee_data;
  
  --Procedure to fetch the employee data
  PROCEDURE fetch_employee_data (
    employee_cv      IN    SYS_REFCURSOR,
    employee_output  OUT   VARCHAR2) 
  IS
  
    --Define private variables        
    employee_row  employee%ROWTYPE;
    dependent_row dependent%ROWTYPE;
    
  BEGIN
  
    --Fetch the next record
    FETCH employee_cv INTO employee_row;
    
    --Compile the output
    employee_output := employee_row.lname || ' ' || employee_row.salary;
    
  EXCEPTION
    --Test the error
    WHEN ROWTYPE_MISMATCH THEN
        FETCH employee_cv INTO dependent_row;
        employee_output := dependent_row.dependent_name || ' ' || dependent_row.bdate;
        
  END fetch_employee_data;
  
END employee_data;
