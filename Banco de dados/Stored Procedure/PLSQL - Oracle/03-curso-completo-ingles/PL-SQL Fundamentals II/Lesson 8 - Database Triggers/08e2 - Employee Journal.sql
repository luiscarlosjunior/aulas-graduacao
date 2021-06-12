CREATE OR REPLACE TRIGGER employee_journal
  AFTER INSERT OR UPDATE OF salary ON employee
  FOR EACH ROW
  WHEN (new.salary > 70000)
  
BEGIN

  --Insert the new row into the audit_entry table
  INSERT INTO audit_entry 
    (entry_date, entry_user, entry_text, old_value, new_value)
  VALUES
    (SYSDATE, USER, 'Salary > 70000 for ' || :NEW.ssn, 
     :OLD.salary,
     :NEW.salary);
    
END;