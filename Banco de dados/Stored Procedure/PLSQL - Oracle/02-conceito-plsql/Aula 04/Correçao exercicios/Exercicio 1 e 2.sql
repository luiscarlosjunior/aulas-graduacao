/*
 1)Fa�a um comando que apare�a somente as colunas RA, NOME, e NOTA, onde a nota do
aluno seja maior que a m�dia de todos os alunos
*/
/*
-- As colunas que ir�o aparecer
SELECT RA, NOME, NOTA 
  -- Onde busca
  FROM ALUNO 
  -- Realiza uma subconsulta para trazer uma valor especif�co
  WHERE NOTA > (SELECT AVG(NOTA) FROM ALUNO);
-- Obs: Com o que foi aprendido at� agora � melhor fazer sem usar o bloco an�nimo, dado
-- a limita��o de exibir mais de uma dado (com o que foi aprendido at� agora)
------------------------------------------------------------------------------
*/
/*
 2) Fa�a um comando que apare�a a quantidade de notas dos alunos que tenha a m�dia abaixo
da m�dia de todos os alunos
*/
DECLARE
  V_MediaTotal aluno.nota%type;
  V_Total aluno.nota%type;
BEGIN
  
  -- Pega a m�dia de todos os alunos
  SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO;
  
  -- 1 op��o
  -- SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA = SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO
  -- 2 opcao
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA < V_MediaTotal;
  
  -- Exibe o resultado
  dbms_output.put_line('O n�mero total de aluno abaixo da m�dia ' || to_char(V_MediaTotal, '9999D99') || 
  ' � de ' || V_Total);
  -- o m�todo to_char Formata a sa�da de algum valor

END;