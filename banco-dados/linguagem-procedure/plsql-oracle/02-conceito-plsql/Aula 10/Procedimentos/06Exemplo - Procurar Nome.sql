SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE atualizar_nome(
  V_RA IN ALUNO.RA%TYPE,
  V_ALUNO OUT ALUNO.NOME%TYPE
)
IS
BEGIN
  SELECT NOME INTO V_ALUNO FROM ALUNO WHERE RA = V_RA;
  
  IF SQL%SQLNOTFOUND THEN
    dbms_output.put_line('N�o foi encontrado esse n�mero de RA');
  ELSE
    dbms_output.put_line('Altera��o incluida com sucesso');
  END IF;
END atualizar_nome

DECLARE 
  idade NUMBER(3);
BEGIN
  retorna_idade(1900,2000, idade);
  dbms_output.put_line(idade);
END;