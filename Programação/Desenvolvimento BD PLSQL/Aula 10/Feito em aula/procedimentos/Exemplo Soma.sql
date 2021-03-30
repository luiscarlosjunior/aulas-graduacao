CREATE OR REPLACE PROCEDURE SOMA (
  V_1 IN NUMBER,
  V_2 IN NUMBER
) 
IS
BEGIN
  dbms_output.put_line('A soma é ' || (V_1 + V_2));
END SOMA;
/
