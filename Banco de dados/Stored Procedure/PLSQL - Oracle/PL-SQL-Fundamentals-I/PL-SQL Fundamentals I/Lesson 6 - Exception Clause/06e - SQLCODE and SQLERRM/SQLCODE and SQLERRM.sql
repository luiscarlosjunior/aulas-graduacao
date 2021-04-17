SET SERVEROUTPUT ON;

DECLARE

BEGIN

  /*Perform a SQL statement to deliberately violate the constraint
    and raise the exception*/
  UPDATE  employee
  SET  lname = NULL;
  
  /*If the next command fires, we failed to trap the error and
    something must be wrong.  Perhaps, we don't have a NOT NULL
    constraint on the column or maybe there are no rows in the 
    table to update.*/
  dbms_output.put_line('Failure:  Missed the EXCEPTION_INIT');
  
EXCEPTION
  /*We'll use the WHEN OTHERS exception test using the SQLCODE 
    and SQLERRM objects*/
  WHEN OTHERS THEN
    dbms_output.put_line('Error code: ' || SQLCODE);
    dbms_output.put_line('Error message: ' || SQLERRM);
  	
END;