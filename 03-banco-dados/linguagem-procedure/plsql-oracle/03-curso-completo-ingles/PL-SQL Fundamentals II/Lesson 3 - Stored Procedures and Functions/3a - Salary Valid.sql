CREATE OR REPLACE FUNCTION salary_valid 
( 
	input_ssn 	  IN CHAR, 
	input_salary 	IN NUMBER 
) 
RETURN boolean 
IS 
	count_management  NUMBER; 
	count_projects	  NUMBER; 
	count_dependents	NUMBER; 
	salary_limit	    NUMBER; 
BEGIN 
/* 
The computed salary limit begins at $50,000 but other exceptions need to be 
identified. 
*/ 
	salary_limit := 50000; 

/* 
If the database indicates that the employee is a department manager then their 
salary limit is increased by $1000. 
*/ 

	SELECT count(*) 
	INTO count_management 
	FROM department 
	WHERE department.mgrssn = input_ssn;
  
  --Test the count_management value 
	IF count_management > 0 THEN 
		salary_limit := salary_limit + 1000; 
	END IF; 

/* 
If the database indicates the employee has worked on projects then their 
salary limit increases by $2000 per project. 
*/ 
	SELECT count(*) 
	INTO count_projects 
	FROM works_on 
	WHERE works_on.essn = input_ssn; 

  --Recalculate the salary limit
	salary_limit := salary_limit + (count_projects * 2000); 

/* 
Finally if the employee has dependents then their salary limit increases by 
$3000 per dependent. 
*/ 
	SELECT count(*) 
	INTO count_dependents 
	FROM dependent 
	WHERE dependent.essn = input_ssn; 

  --Recalculate the salary limit
	salary_limit := salary_limit + (count_dependents * 3000); 
	
/*
The conclusion of the program is straightforward.  If the proposed salary is
judged as approprieate for this particular employee, then the function result
indicates such and the program terminates when the result is returned to the 
calling program by means of the RETURN() statement.  If the opposite is true,
an appropriate termination also occurs.
*/
	IF input_salary > salary_limit THEN
		RETURN (FALSE);
	ELSE
		RETURN (TRUE);
	END IF;

END salary_valid;
	