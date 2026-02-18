SET SERVEROUTPUT ON;

/*
Author:		Tim Miles
Date:		  3/19/2013
Purpose:	This script shows how to calculate date values.
*/
DECLARE

	--Declare variables
	EmpReviewDate	employee.Bdate%TYPE;
	EmpNextReview EmpReviewDate%TYPE;
  
BEGIN

	--Set the variables
	EmpReviewDate := SYSDATE;
	EmpNextReview := EmpReviewDate + 100;
	
	--Display the current review date
	dbms_output.put_line('Current Review Date: ' || EmpReviewDate);

	--Display the next, calculated review date
	dbms_output.put_line('Next Review Date: ' || EmpNextReview);

END;
/