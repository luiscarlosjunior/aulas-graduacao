CREATE OR REPLACE FUNCTION FUNC_NOME_ALUNO (
 P_RA IN CHAR)
 RETURN VARCHAR2 IS
 V_NOME VARCHAR2(50);
BEGIN
 SELECT NOME INTO V_NOME
  FROM ALUNO
  WHERE RA = P_RA;
  RETURN V_NOME;
END FUNC_NOME_ALUNO;
/