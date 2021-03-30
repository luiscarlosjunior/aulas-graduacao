SET SERVEROUTPUT ON

-- Laço de repetição simples
DECLARE
  --Declarando um cursor
  CURSOR C_ALUNO IS
    -- Passa a consulta
    SELECT RA, NOME 
      FROM ALUNO;
      
  V_ALUNO C_ALUNO%ROWTYPE;    
BEGIN
  -- Segundo passo - Abrir o cursor
  OPEN C_ALUNO; 
  
  LOOP
    -- Pegar cada linha
    FETCH C_ALUNO INTO V_ALUNO;
  EXIT WHEN C_ALUNO%NOTFOUND;
    dbms_output.put_line('Aluno: ' || v_aluno.nome || 
    ' RA:' || v_aluno.ra);
  END LOOP;
  
  -- Fechar
  CLOSE C_ALUNO;

END;
