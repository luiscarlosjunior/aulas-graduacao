-- 3) Declare uma vari�vel que receba e exiba o resultado de um comando que retorna �
--quantidade de notas dos alunos que tenha a m�dia acima da m�dia de todos os alunos
/*
DECLARE
  V_MediaTotal aluno.nota%type;
  V_Total aluno.nota%type;
BEGIN
  
  -- Pega a m�dia de todos os alunos
  SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO;
  
  -- 1 op��o
  -- SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA = SELECT AVG(NOTA) INTO V_MediaTotal FROM ALUNO
  -- 2 opcao
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE NOTA > V_MediaTotal;
  
  -- Exibe o resultado
  dbms_output.put_line('O n�mero total de aluno acima da m�dia ' || 
    to_char(V_MediaTotal, '9999D99') || 
  ' � de ' || V_Total);
  -- o m�todo to_char Formata a sa�da de algum valor

END;
*/
--------------------------------------------------------------------------------
-- 4) Declare uma vari�vel que receba e exiba o resultado de um comando para retornar �
-- quantidade de alunos que cont�m no RA o caractere no in�cio 9, mais um n�mero qualquer
-- e 4; Ex: 984566322;
DECLARE
  V_Total NUMBER;
BEGIN
  
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE RA LIKE '9_4%';
  
  dbms_output.put_line('A qtde de alunos com o RA que come�a com 9 alguma e 4 � de' ||
  V_Total);
  
END;