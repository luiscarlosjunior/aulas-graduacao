set serveroutput on;

DECLARE

  --Declare variables
  rowEmp1  employee%ROWTYPE;
  rowEmp2  employee%ROWTYPE;
  
BEGIN

  --Fill the fields
  rowEmp1.lname := 'Doe';
  rowEmp1.fname := 'John';
  
  rowEmp2.lname := 'Doe';
  rowEmp2.fname := 'Jane';
 
  --Output one of the records
  dbms_output.put_line ('Row 1 is ' || rowEmp1.lname || ', ' || rowEmp1.fname || '.');
  
END;