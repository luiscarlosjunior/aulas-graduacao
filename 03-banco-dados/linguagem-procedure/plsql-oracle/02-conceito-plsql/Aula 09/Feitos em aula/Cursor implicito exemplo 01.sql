DECLARE
  V_ALUNO ALUNO%ROWTYPE;
BEGIN
  
  UPDATE ALUNO SET NOTA = 6
  WHERE FALTAS > 15;
  
  --dbms_output.put_line(SQL%ROWCOUNT || ' linhas afetadas');
  
END;