--SET SERVEROUTPUT ON
/*
DECLARE 
  V_RA CHAR(15) := '71033266097';
  V_Nome VARCHAR2(50);
BEGIN
  SELECT NOME INTO V_NOME FROM aluno WHERE RA = V_RA;
  dbms_output.put_line('O nome do aluno �: ' || V_NOME);
END;                    
/


-- Usando o tipo da coluna em variavel
DECLARE 
  V_RA alunos.ra%type := '71033266097';
  V_Nome alunos.nome%type;
BEGIN

  SELECT NOME INTO V_Nome FROM Alunos where RA = V_RA;
  dbms_output.put_line('O nome do aluno �: ' || V_Nome);

END;
/

-- Podemos tamb�m declarar uma vari�vel com um tipo que corresponda a uma linha 
-- inteira da tabela.
DECLARE 
  V_Aluno alunos%rowtype;
BEGIN

  V_Aluno.RA := '71033266097';
  SELECT NOME INTO V_Aluno.Nome FROM Alunos where RA = V_Aluno.RA;
  dbms_output.put_line('O nome do aluno �: ' || V_Aluno.Nome);

END;
*/

-- Insert dados
DECLARE
  V_RA CHAR(15) := '71033266097';
  V_NOME VARCHAR2(50) := 'Daniela Dorneles';
  V_Nota NUMBER := 10;
  V_Total NUMBER;
  V_TUDO CONSTANT NUMBER := 1000;
BEGIN
  INSERT INTO ALUNO (ID, RA,NOME, SOBRENOME, NOTA, FALTAS, CURSO, GENERO, PERIODO) VALUES (305,V_RA,V_NOME,'Rotar', 9, 10, 'CC', 'Feminino', 'Matutino');
  
  if (V_Nota >= 7) THEN
    dbms_output.put_line('Parab�ns pela nota ' || V_NOME);
  ELSE
    dbms_output.put_line('Cadastro realizado com sucesso ' || V_NOME);
  END IF;
  
  SELECT COUNT(*) INTO V_Total FROM ALUNO;
  
  dbms_output.put_line('Voc� � o aluno: ' || V_Total);
  dbms_output.put_line('Ainda faltam ' || (V_Total-V_Tudo) || ' para completar 1000 alunos');
  END;
/
/*
-- Select
SELECT * FROM aluno
SELECT SUM(NOTA)/COUNT(*) as M�dia FROM ALUNO;
SELECT COUNT(GENERO) FROM ALUNO WHERE GENERO = 'Masculino';
SELECT COUNT(GENERO) FROM ALUNO WHERE GENERO = 'Feminino';
SELECT COUNT(RA) FROM ALUNO WHERE RA like '9_4%';
SELECT COUNT(RA) FROM ALUNO WHERE RA like '94%';
SELECT COUNT(GENERO) FROM ALUNO WHERE GENERO <> 'Masculino' AND GENERO <> 'Feminino';
select count(*), curso, SUM(nota)/count(nota) as media from aluno group by curso having sum(nota)/count(nota) >= 7;

-- Insert

*/