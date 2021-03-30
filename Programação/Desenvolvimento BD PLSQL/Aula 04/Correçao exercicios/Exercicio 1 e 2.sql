/*
 1)Faça um comando que apareça somente as colunas RA, NOME, e NOTA, onde a nota do
aluno seja maior que a média de todos os alunos
*/
/*
-- As colunas que irão aparecer
SELECT RA, NOME, NOTA 
  -- Onde busca
  FROM ALUNO 
  -- Realiza uma subconsulta para trazer uma valor especifíco
  WHERE NOTA > (SELECT AVG(NOTA) FROM ALUNO);
-- Obs: Com o que foi aprendido até agora é melhor fazer sem usar o bloco anônimo, dado
-- a limitação de exibir mais de uma dado (com o que foi aprendido até agora)
------------------------------------------------------------------------------
*/
/*
 2) Faça um comando que apareça a quantidade de notas dos alunos que tenha a média abaixo
da média de todos os alunos
*/
DECLARE
  V_MediaTotal aluno.nota%type;
  V_Total aluno.nota%type;
BEGIN
  
  -- Pega a média de todos os alunos
  SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO;
  
  -- 1 opção
  -- SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA = SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO
  -- 2 opcao
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA < V_MediaTotal;
  
  -- Exibe o resultado
  dbms_output.put_line('O número total de aluno abaixo da média ' || to_char(V_MediaTotal, '9999D99') || 
  ' é de ' || V_Total);
  -- o método to_char Formata a saída de algum valor

END;