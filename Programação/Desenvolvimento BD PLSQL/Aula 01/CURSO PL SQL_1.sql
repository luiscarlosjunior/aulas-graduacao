
SET SERVEROUTPUT ON;
DECLARE
   v_ID NUMBER(5) := 1;
BEGIN
    v_ID := 2;
   dbms_output.put_line(v_ID);
END;