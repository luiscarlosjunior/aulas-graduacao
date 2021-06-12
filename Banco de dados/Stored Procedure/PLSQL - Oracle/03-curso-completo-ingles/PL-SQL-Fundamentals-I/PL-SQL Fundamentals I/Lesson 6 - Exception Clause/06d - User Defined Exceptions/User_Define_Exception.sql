SET SERVEROUTPUT ON;

DECLARE

  --User-assigned exception name declaration
  not_null_constraint	EXCEPTION;

  --Equate the new exception name to the corresponding
  --Oracle error number.
  PRAGMA EXCEPTION_INIT (not_null_constraint, -1407);

BEGIN

  --Perform a SQL statement to deliberately violate the constraint
  --and raise the exception
  UPDATE  employee
  SET  lname = NULL;
  
  --If the next command fires, we failed to trap the error and
  --something must be wrong.  Perhaps, we don't have a NOT NULL
  --constraint on the column or maybe there are no rows in the 
  --table to update.
  dbms_output.put_line('Failure:  Missed the EXCEPTION_INIT');
  
EXCEPTION
  --If we execute this handler, then we have trapped the error
  WHEN not_null_constraint THEN
    dbms_output.put_line('Success: not_null_constraint exception trapped');
    
  --If this handler fires, then we have trapped an exception but 
  --we have missed the exception name NOT_NULL_CONSTRAINT, which 
  --we thought was defined
  WHEN OTHERS THEN
    dbms_output.put_line('Failure: exception trapped but no name defined');
  	
END;