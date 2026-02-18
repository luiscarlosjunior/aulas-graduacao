DECLARE

  --Declare variables
  department_number  employee.dno%TYPE := 5;
  employee_cv        employee_data.employee_cv_type;
  employee_output    VARCHAR2(1000);
  
BEGIN

  --Open the cursor
  employee_data.open_employee_data (employee_cv, department_number);

  LOOP
  
    --Fetch the next employee
    employee_data.fetch_employee_data (employee_cv, employee_output);

    --Set condition for the exit  
    EXIT WHEN employee_cv%NOTFOUND;
    
    --Output the results
    dbms_output.put_line (employee_output);
  
  END LOOP;
  
END;