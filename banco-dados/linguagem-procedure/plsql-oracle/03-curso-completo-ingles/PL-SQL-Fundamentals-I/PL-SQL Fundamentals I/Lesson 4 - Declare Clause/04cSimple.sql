SET SERVEROUTPUT ON;

DECLARE

	--Declare contants
	cMinSalary  CONSTANT NUMBER(6) := 15000;
	cMaxSalary  CONSTANT NUMBER(6) := 99000;
  
	--Declare variables
	nSalary	  NUMBER(6) DEFAULT 25000;
	cSSN	    CHAR(11) := '111-22-3333';
	vLName	  VARCHAR2(15) NOT NULL := 'Smith';
	vFName	  VARCHAR2(15) := 'John';
	
BEGIN
	
	--Show the result
	dbms_output.put_line('Name:    ' || vFName || ' ' || vLName );
	dbms_output.put_line('SSN:     ' || cSSN );
	dbms_output.put_line('Salary:  $' || nSalary );
	dbms_output.put_line('');
	dbms_output.put_line('Minimum: $' || cMinSalary);
	dbms_output.put_line('Maximum: $' || cMaxSalary);
  
END;

