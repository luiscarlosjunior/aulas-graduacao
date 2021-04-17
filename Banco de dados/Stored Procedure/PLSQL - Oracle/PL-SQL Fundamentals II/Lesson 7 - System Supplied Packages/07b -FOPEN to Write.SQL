SET SERVEROUTPUT ON;

/* 
Copyright (c) 2010 Sideris Courseware Corporation. All Rights Reserved.
Each instructor or student with access to this file must have purchased
a license to the corresponding Sideris Courseware textbook to which 
these files apply. All other use, broadcast, webcast, duplication or distribution
is prohibited and illegal.
*/

DECLARE 

  --Declare variables
  TextRecord   VARCHAR2(2000);
  TextFile     utl_file.FILE_TYPE;

  --Create explicit cursor with employee detail
  CURSOR EmployeeList IS
     SELECT LName, FName, Salary, DName
     FROM employee e
       INNER JOIN department d ON d.dnumber = e.dno
     ORDER BY DName;

  --Declare a new type
  TYPE EmployeeRecordType IS RECORD
     (EmpLast   employee.LName%TYPE,
      EmpFirst  employee.FName%TYPE,
      EmpSalary employee.Salary%TYPE,
      DeptName  department.DName%TYPE);

  --Instantiate the EmployeeRecordType
  EmployeeRecord   EmployeeRecordType;

BEGIN 

  --Open the cursor
  OPEN EmployeeList;
  
  --Open the file named Employees.csv
  TextFile := utl_file.fopen (UPPER('TextDirectory'), 'Employees.csv', 'w', 
                              32767);

  LOOP
  
    --Retrieve the next record from the cursor
    FETCH EmployeeList INTO EmployeeRecord;
  
    --Determine when to exit the loop
    EXIT WHEN EmployeeList%NOTFOUND;

    --Pass the data to the TextRecord
    TextRecord := EmployeeRecord.DeptName || ',' ||
                  EmployeeRecord.EmpLast  || ',' ||
                  EmployeeRecord.EmpFirst || ',' ||
                  EmployeeRecord.EmpSalary;

    --Post the TextRecord to the currently open file.  
    --Follow with the line terminator
    utl_file.put (TextFile, TextRecord);
    utl_file.new_line (TextFile);

  END LOOP;

  --Close the cursor and file
  CLOSE EmployeeList;
  utl_file.fclose (TextFile);

EXCEPTION
  WHEN OTHERS THEN
    dbms_output.put_line (sqlcode);
    dbms_output.put_line (sqlerrm);
    
END; 
