SET SERVEROUTPUT ON

/* Exemplo 01
DECLARE
  -- Declara as variáveis
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
  -- Declarando variável
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
  
  -- Exibe a saída 
  dbms_output.put_line(vSaida);

END;
*/
/* Exemplo 03
SET SERVEROUTPUT ON

DECLARE 
  -- Declarando variável
  alunos  aluno%ROWTYPE;
  -- Variáveis de Auxilio de saída
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

