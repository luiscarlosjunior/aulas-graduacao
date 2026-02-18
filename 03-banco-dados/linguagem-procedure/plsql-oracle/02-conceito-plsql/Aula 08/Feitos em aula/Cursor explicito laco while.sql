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
  
  FETCH C_ALUNO INTO V_ALUNO;
  
  WHILE C_ALUNO%FOUND LOOP
     dbms_output.put_line('Aluno: ' || v_aluno.nome || 
    ' RA:' || v_aluno.ra);
    FETCH C_ALUNO INTO V_ALUNO;
  END LOOP;
  
  -- Fechar
  CLOSE C_ALUNO;

END;