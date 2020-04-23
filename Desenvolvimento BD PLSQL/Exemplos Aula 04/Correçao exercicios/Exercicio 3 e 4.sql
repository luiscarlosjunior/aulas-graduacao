-- 3) Declare uma variável que receba e exiba o resultado de um comando que retorna à
--quantidade de notas dos alunos que tenha a média acima da média de todos os alunos
/*
DECLARE
  V_MediaTotal aluno.nota%type;
  V_Total aluno.nota%type;
BEGIN
  
  -- Pega a média de todos os alunos
  SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO;
  
  -- 1 opção
  -- SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA = SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO
  -- 2 opcao
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA > V_MediaTotal;
  
  -- Exibe o resultado
  dbms_output.put_line('O número total de aluno acima da média ' || 
    to_char(V_MediaTotal, '9999D99') || 
  ' é de ' || V_Total);
  -- o método to_char Formata a saída de algum valor

END;
*/
--------------------------------------------------------------------------------
-- 4) Declare uma variável que receba e exiba o resultado de um comando para retornar à
-- quantidade de alunos que contém no RA o caractere no início 9, mais um número qualquer
-- e 4; Ex: 984566322;
DECLARE
  V_Total NUMBER;
BEGIN
  
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE RA LIKE '9_4%';
  
  dbms_output.put_line('A qtde de alunos com o RA que começa com 9 alguma e 4 é de' ||
  V_Total);
  
END;