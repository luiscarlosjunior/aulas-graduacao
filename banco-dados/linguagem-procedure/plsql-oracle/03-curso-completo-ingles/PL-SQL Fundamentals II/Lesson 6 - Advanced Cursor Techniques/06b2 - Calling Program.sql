DECLARE

  --Declare variables
  department_number  employee.dno%TYPE := 5;
  employee_cv        employee_data.employee_cv_type;
  employee_output    VARCHAR2(1000);
  
BEGIN

  --Call procedure to open the cursor
  employee_data.open_employee_data (employee_cv, department_number);
  
  LOOP
  
    --Fetch the next row of data
    employee_data.fetch_employee_data (employee_cv, employee_output);
    
    --Test whether to exit the loop
    EXIT WHEN employee_cv%NOTFOUND;
    
    --Output the detail
    dbms_output.put_line (employee_output);

  END LOOP;
  
END;