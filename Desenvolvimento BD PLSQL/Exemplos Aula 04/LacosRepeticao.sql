DECLARE
  idade1 NUMBER(3);
  idade2 NUMBER(3);
BEGIN

  FOR i IN 1..10 LOOP
    dbms_output.put_line(i);
  END LOOP;
END;