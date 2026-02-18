DECLARE
  --Declarando um cursor
  CURSOR C_ALUNO IS
    -- Passa a consulta
    SELECT RA, NOME 
      FROM ALUNO;
          
BEGIN

  FOR I_ALUNO IN C_ALUNO LOOP
    dbms_output.put_line('Aluno: ' || i_aluno.nome || 
    ' RA:' || i_aluno.ra);
  END LOOP;

END;