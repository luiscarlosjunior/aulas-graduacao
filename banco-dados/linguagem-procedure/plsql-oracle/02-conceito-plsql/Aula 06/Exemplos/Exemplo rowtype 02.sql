SET SERVEROUTPUT ON

/* Exemplo 01
DECLARE
  -- Declara as vari�veis
  alunos1  aluno%ROWTYPE;
  alunos2  aluno%ROWTYPE;
BEGIN
  -- Preencho os campos
  alunos1.NOME := 'Juca';
  alunos1.SOBRENOME := 'Mota';
  alunos2.NOME  := 'Raisa';
  alunos2.SOBRENOME := 'Melo';

  -- Exibo um dos resultados
  dbms_output.put_line(alunos1.NOME || ' e ' || alunos2.NOME);
END;
*/
/*
Exemplo 02
DECLARE 
  -- Declarando vari�vel
  alunos  aluno%ROWTYPE;
  vSaida VARCHAR(50);
BEGIN

  -- Recebe os dados do aluno
  SELECT RA, NOME, NOTA
  INTO alunos.RA, alunos.Nome, alunos.Nota
  FROM aluno
  WHERE nome = 'Alan';

  vSaida :=  alunos.RA || ' ' || alunos.Nome 
      || ' ' || chr(9) || alunos.Nota;
  
  -- Exibe a sa�da 
  dbms_output.put_line(vSaida);

END;
*/
/* Exemplo 03
SET SERVEROUTPUT ON

DECLARE 
  -- Declarando vari�vel
  alunos  aluno%ROWTYPE;
  -- Vari�veis de Auxilio de sa�da
  vSaida VARCHAR2(50);
  vColunas VARCHAR2(50);
BEGIN
  -- Recebe os dados do aluno
  SELECT RA, NOME, NOTA
  INTO alunos.RA, alunos.Nome, alunos.Nota
  FROM aluno
  WHERE nome = 'Alan';
  
  vColunas := 'RA ' || rpad(' ', 12) || ' Nome ' || ' Nota Final ';
  vSaida :=  alunos.RA || ' ' || alunos.Nome 
      || ' ' || chr(9) || alunos.Nota;
  
  -- Exibe o resultado
  dbms_output.put_line(vColunas);
  dbms_output.put_line(vSaida);

END;
*/

