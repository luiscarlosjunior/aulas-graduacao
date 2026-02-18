SET SERVEROUTPUT ON
/*
DECLARE
  vContador NUMBER := 1;
BEGIN
  LOOP
    DBMS_OUTPUT.PUT_LINE (vContador);
    vContador := vContador + 1;
    IF vContador > 5 THEN
      EXIT;
    END IF;
  END LOOP;
END;
/

DECLARE 
  vContador NUMBER := 10;
BEGIN
  FOR I IN 1..vContador LOOP
    DBMS_OUTPUT.PUT_LINE (I);
  END LOOP;
END;
/
*/
DECLARE 
  vContador NUMBER := &Entre_com_o_numero;
BEGIN
  FOR I IN 1..vContador LOOP
    DBMS_OUTPUT.PUT_LINE (I);
  END LOOP;
END;
/
