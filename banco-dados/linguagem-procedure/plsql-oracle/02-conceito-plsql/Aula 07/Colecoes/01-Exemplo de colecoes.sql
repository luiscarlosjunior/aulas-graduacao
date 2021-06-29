SET SERVEROUTPUT ON;
DECLARE
  TYPE myColecao IS TABLE OF NUMBER;
  varColecao myColecao := myColecao (9, 12, 12, 13, 45, 25, 36);
BEGIN
  FOR i IN 1..varColecao.COUNT
  LOOP
    dbms_output.put_line(varColecao(i));
  END LOOP;
END;
/
