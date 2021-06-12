SET SERVEROUTPUT ON;

DECLARE

  --Declare weak cursor definition to hold work detail
  TYPE WorkCursorType IS REF CURSOR;
  WorkCursor WorkCursorType;
  
  --Define variables to be used later
  EmpLName    employee.LName%TYPE;
  EmpSalary   employee.Salary%TYPE;
  EmpPName    project.PName%TYPE;
  EmpHours    works_on.Hours%TYPE;

  --Create an explicit cursor named EmpWork
  CURSOR EmpWork IS
    SELECT LName, Salary, CURSOR(SELECT PName, Hours
                                 FROM works_on w
                                   INNER JOIN project p ON p.pnumber = w.pno
                                 WHERE w.essn = e.ssn) AS Work
    FROM employee e
    ORDER BY LName;
    
BEGIN

  --Open the primary cursor EmpWork - Note: the secondary cursor is also 
  --opened at this time.
  OPEN EmpWork;
  
    --Begin the loop for the primary cursor EmpWork
    LOOP
    
      --Retrieve the next EmpWork record and all associated work detail
      --found in the WorkCursor object
      FETCH EmpWork INTO EmpLname, EmpSalary, WorkCursor;
      
      --Exit the loop if no data remains
      EXIT WHEN EmpWork%NOTFOUND;
      
      --Output the employee last name
      dbms_output.put_line ('Processing here for ' || EmpLname);
      
      --Begin the loop for the secondary cursor WorkCursor
      LOOP
      
        --Retrieve the next work detail item
        FETCH WorkCursor INTO EmpPName, EmpHours;
        
        --Exit the loop if no data remains
        EXIT WHEN WorkCursor%NOTFOUND;
        
        --Output the employee name and project
        dbms_output.put_line ('Processing here for ' || EmpLname || ' and for project ' || EmpPname);
        
      END LOOP;
      
    END LOOP;
    
  --Close the cursor - Note: the WorkCursor will automatically close too
  CLOSE EmpWork;
  
END;