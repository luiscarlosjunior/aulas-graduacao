/*
SET SERVEROUTPUT ON
DECLARE 
  V_ALUNO ALUNO%ROWTYPE;
BEGIN
  UPDATE ALUNO SET NOTA = 0
  WHERE FALTAS > 15;
  dbms_output.put_line(SQL%ROWCOUNT || ' Linhas encontradas');
END;
/
*/
DECLARE 
  V_RA ALUNO.RA%TYPE := '12345678';
  V_NOME aluno.nome%type := 'Ronaldo';
BEGIN
  UPDATE ALUNO SET NOME = V_NOME 
  WHERE RA = V_RA;
  
  IF SQL%NOTFOUND THEN
    dbms_output.put_line('Não houve alteração');
  END IF;
  COMMIT;
END;
/

