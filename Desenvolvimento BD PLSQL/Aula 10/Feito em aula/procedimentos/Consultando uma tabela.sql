CREATE OR REPLACE PROCEDURE VERIFICAR_NOME (
  V_RA IN ALUNO.RA%TYPE,
  V_NOME OUT ALUNO.NOME%TYPE
)
IS
BEGIN
  
  SELECT NOME INTO V_NOME FROM ALUNO WHERE RA = V_RA;
    
  IF SQL%NOTFOUND THEN 
    dbms_output.put_line('Aluno não foi encontrado');
  ELSE 
    dbms_output.put_line('Aluno encontrado');
  END IF;
  
END VERIFICAR_NOME;
/

