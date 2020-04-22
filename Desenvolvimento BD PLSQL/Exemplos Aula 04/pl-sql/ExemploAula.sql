--SET SERVEROUTPUT ON
/*
DECLARE 
  V_RA CHAR(15) := '71033266097';
  V_Nome VARCHAR2(50);
BEGIN
  SELECT NOME INTO V_NOME FROM alunos WHERE RA = V_RA;
  dbms_output.put_line('O nome do aluno é: ' || V_NOME);
END;
/

-- Usando o tipo da coluna em variavel
DECLARE 
  V_RA alunos.ra%type := '71033266097';
  V_Nome alunos.nome%type;
BEGIN

  SELECT NOME INTO V_Nome FROM Alunos where RA = V_RA;
  dbms_output.put_line('O nome do aluno é: ' || V_Nome);

END;
/

-- Podemos também declarar uma variável com um tipo que corresponda a uma linha 
-- inteira da tabela.
DECLARE 
  V_Aluno alunos%rowtype;
BEGIN

  V_Aluno.RA := '71033266097';
  SELECT NOME INTO V_Aluno.Nome FROM Alunos where RA = V_Aluno.RA;
  dbms_output.put_line('O nome do aluno é: ' || V_Aluno.Nome);

END;
*/


