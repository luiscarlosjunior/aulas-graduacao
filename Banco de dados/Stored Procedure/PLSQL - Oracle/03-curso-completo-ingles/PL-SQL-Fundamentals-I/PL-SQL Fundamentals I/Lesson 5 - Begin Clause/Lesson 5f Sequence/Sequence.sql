SET SERVEROUTPUT ON;

DECLARE

  --Declare variables
  NewUserID   users.usrid%TYPE;
  
BEGIN

  --Retrieve the next ID
  NewUserID := UsrIDSeq.NEXTVAL;
  
  --Insert the new record
  INSERT INTO users (fname, minit, lname, ssn, bdate, address, sex, salary,
                     dno, usrid)
    VALUES('Tim', 'X', 'Miles', '012349999', TO_DATE('19-AUG-1966', 'DD-MON-YYYY'),
           '5 Main St, Marion, CA', 'M', 65000, 5, NewUserID);
           
  --Let the user know if it was successful
  dbms_output.put_line('Record added successfully.');

END;

