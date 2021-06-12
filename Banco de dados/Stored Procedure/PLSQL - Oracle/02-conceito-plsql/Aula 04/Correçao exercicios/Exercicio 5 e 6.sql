-- 5) Declare uma variável que receba e exiba o resultado de um comando que retorne à
--quantidade que possuem o número de faltas menores do que 7

DECLARE
  V_Total aluno.nota%type;
BEGIN
  
  -- Exercicio 5
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE FALTAS < 7;
  
  -- Exibe o resultado
  dbms_output.put_line('Exercício 5');
  dbms_output.put_line('A qtde de alunos com número de faltas menores que 7 ' ||  
  ' é de ' || V_Total || ' aluno');
  -----------------------------------------------------
  dbms_output.put_line('------------------------------');
  -- Exercicio 6
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE FALTAS > 15;
  
  -- Exibe o resultado
  dbms_output.put_line('Exercício 6');
  dbms_output.put_line('A qtde de alunos com número de faltas maior que 15 ' ||  
  ' é de ' || V_Total || ' aluno');
END;
