--Set the server to output results
SET SERVEROUTPUT ON;

DECLARE

	--Declare variables
	nSalary	  NUMBER(4);
	cSSN	    CHAR(10);
	vLName	  VARCHAR2(15);
	vFName	  VARCHAR2(15);
	dDOB      DATE;
  
BEGIN
	--Tell the user that everything is working
	DBMS_OUTPUT.PUT_LINE('Compiled declarations successfully');
END;

