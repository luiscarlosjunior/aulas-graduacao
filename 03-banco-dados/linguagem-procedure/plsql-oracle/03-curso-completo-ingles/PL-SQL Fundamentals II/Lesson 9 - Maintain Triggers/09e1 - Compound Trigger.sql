CREATE OR REPLACE TRIGGER EmployeeIntegrity
FOR DELETE OR UPDATE OF ssn ON employee
COMPOUND TRIGGER

  --Define the Table Type holding the SSN's
  TYPE SSNarray IS TABLE OF employee.ssn%TYPE
  INDEX BY SIMPLE_INTEGER;

  --Define variables
  DeleteList   SSNarray;
  DeleteIndex  SIMPLE_INTEGER DEFAULT 0;

  AFTER EACH ROW
  IS
  BEGIN
  
    --Increment the counter and add the SSN to the array
    DeleteIndex := DeleteIndex + 1;
    DeleteList (DeleteIndex) := :old.ssn;
  
  END AFTER EACH ROW;
  
  AFTER STATEMENT
  IS
  BEGIN
  
    --Loop through the table
    FOR i IN DeleteList.FIRST .. DeleteList.LAST LOOP
  
      --Update the employee table
      UPDATE employee
      SET superssn = (SELECT ssn
                      FROM employee
                      WHERE superssn IS NULL)
      WHERE superssn = DeleteList (i);
  
    END LOOP;
    
  END AFTER STATEMENT;

END EmployeeIntegrity;