CREATE OR REPLACE TRIGGER budget_event
  AFTER INSERT OR UPDATE OF salary ON employee
  FOR EACH ROW
  
BEGIN

  --Test whether this is an Update event
  IF UPDATING
  AND :NEW.salary > :OLD.salary THEN
  
    --Insert the raise detail
    INSERT INTO budget_request (account_no, amount, description)
      VALUES(101, :NEW.salary - :OLD.salary, 'Employee raise');

  ELSE

    --Insert the new employee detail
    INSERT INTO budget_request (account_no, amount, description)
      VALUES(101, :NEW.salary, 'New employee');

  END IF;
  
END;