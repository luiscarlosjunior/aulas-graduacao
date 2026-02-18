SELECT  UPPER(LName) AS "Last Name",
        ROUND(Salary * 1.25) AS "Proposed Salary",
        SALARY_VALID_RESULT( ssn, Salary * 1.25 ) AS "Valid"
FROM employee;