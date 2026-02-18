/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

CREATE OR REPLACE PACKAGE employee_data 
AS

  --Define global variable
  TYPE employee_cv_type IS REF CURSOR RETURN employee%ROWTYPE;

  --Declare procedure to open employee data
  PROCEDURE open_employee_data (
          employee_cv   IN OUT employee_cv_type,
          x_dno         IN     employee.dno%TYPE); 

  --Declare procedure to fetch data from cursor
  PROCEDURE fetch_employee_data (
          employee_cv      IN    employee_cv_type,
          employee_output  OUT   VARCHAR2);
          
END employee_data;
/

CREATE OR REPLACE PACKAGE BODY employee_data 
AS

  PROCEDURE open_employee_data (
    employee_cv   IN OUT employee_cv_type,
    x_dno         IN     employee.dno%TYPE) 
  IS
  BEGIN
  
    --Open the cursor
    OPEN employee_cv FOR
         SELECT *
         FROM employee
         WHERE employee.dno = x_dno;
         
  END open_employee_data;
  
  PROCEDURE fetch_employee_data (
    employee_cv      IN    employee_cv_type,
    employee_output  OUT   VARCHAR2) 
  IS
    
    --Define variable used by the procedure        
    employee_row  employee%ROWTYPE;
          
  BEGIN
  
    --Fetch the next employee record
    FETCH employee_cv INTO employee_row;
    
    --Compile the output
    employee_output := employee_row.lname || ' ' || employee_row.salary;

  END fetch_employee_data;
  
END employee_data;