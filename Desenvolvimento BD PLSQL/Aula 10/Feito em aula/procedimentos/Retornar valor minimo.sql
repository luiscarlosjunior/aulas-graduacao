CREATE OR REPLACE PROCEDURE VALOR_MAXIMO (
  V_A IN NUMBER,
  V_B IN NUMBER,
  V_C OUT NUMBER
)
IS
BEGIN
  -- Operação ternária 
  --V_C := (V_A > V_B) ? V_A : V_B;
    
  IF V_A > V_B THEN
    V_C := V_A;
  ELSE
    V_C := V_B;
  END IF;
  
  
END VALOR_MAXIMO;
/

DECLARE 
  valor_A NUMBER;
  valor_B NUMBER;
  valor_C NUMBER;
BEGIN
  valor_A := 50;
  valor_B := 50;
  
  -- Criar o procedimento valorMaximo, com três args
  -- sendo, dois IN e um OUT.
  valor_Maximo(valor_A, valor_B, valor_C);
  
  dbms_output.put_line('O valor máximo é ' || valor_C);
  
END;