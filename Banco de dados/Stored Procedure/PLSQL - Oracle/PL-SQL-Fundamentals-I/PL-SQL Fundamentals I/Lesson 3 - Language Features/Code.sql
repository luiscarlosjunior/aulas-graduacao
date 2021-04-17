DECLARE

	x_salary employee.salary%TYPE;
	
BEGIN
	select salary
	into x_salary
	from employee
	where ssn=&enter_ssn;
	
	--Output the result
	DBMS_OUTPUT.PUT_LINE('Salary is ' || x_salary);

EXCEPTION
	--Output when no records are returned
	WHEN no_data_found THEN
		DBMS_OUTPUT.PUT_LINE ('No employee found');
		
	WHEN others THEN
		DBMS_OUTPUT.PUT_LINE ('Error encountered, but cause unknown');
		
END;