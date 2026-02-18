SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION SOMA (
 NUM_1 IN NUMBER,
 NUM_2 IN NUMBER)
 RETURN NUMBER IS
 SOMA NUMBER;
BEGIN
 SOMA := NUM_1 + NUM_2;
 RETURN SOMA;
END SOMA;
/

SELECT SOMA (15,25) FROM DUAL;

DECLARE
  V_1 NUMBER;
  V_2 NUMBER;
BEGIN
  V_1 := &Entre_com_um_valor;
  V_2 := &Entre_com_outro_valor;

  dbms_output.put_line('A soma é ' || soma(V_1,V_2));
END;



select sysdate from dual;