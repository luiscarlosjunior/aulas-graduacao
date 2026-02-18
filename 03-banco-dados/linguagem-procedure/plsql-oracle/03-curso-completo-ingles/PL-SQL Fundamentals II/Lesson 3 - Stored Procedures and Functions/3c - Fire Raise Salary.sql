set serveroutput on;

DECLARE

  --Declare variables
  output_text CHAR(100);
  
BEGIN

  --Fire raise_salary procedure
  raise_salary('123456789', 51, output_text);
  
  --Output the results
  dbms_output.put_line( output_text );

END;
