  -- Exercício
  -- Criar o procedimento valorMaximo, com três args
  -- sendo, dois IN e um OUT.
-- Cria/Altera um procedimento
CREATE OR REPLACE PROCEDURE PEGA_SOMA (
  V_1 IN NUMBER,
  V_2 IN NUMBER,
  Soma OUT NUMBER
) 
IS
BEGIN
  soma := V_1 + V_2;
END PEGA_SOMA;
/

DECLARE
  V_1 NUMBER := &Entre_com_valor;
  V_2 NUMBER := &Entre_com_valor;
  TOTAL NUMBER(3);
BEGIN
  -- Chamar a função PEGA_SOMA, passando argumentos
  pega_soma(V_1, V_2, TOTAL);
  
  dbms_output.put_line('A soma total é ' || total);
  
END;