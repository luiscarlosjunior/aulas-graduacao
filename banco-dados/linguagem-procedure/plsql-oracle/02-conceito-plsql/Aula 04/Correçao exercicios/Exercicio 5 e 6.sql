-- 5) Declare uma vari�vel que receba e exiba o resultado de um comando que retorne �
--quantidade que possuem o n�mero de faltas menores do que 7

DECLARE
  V_Total aluno.nota%type;
BEGIN
  
  -- Exercicio 5
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE FALTAS < 7;
  
  -- Exibe o resultado
  dbms_output.put_line('Exerc�cio 5');
  dbms_output.put_line('A qtde de alunos com n�mero de faltas menores que 7 ' ||  
  ' � de ' || V_Total || ' aluno');
  -----------------------------------------------------
  dbms_output.put_line('------------------------------');
  -- Exercicio 6
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE FALTAS > 15;
  
  -- Exibe o resultado
  dbms_output.put_line('Exerc�cio 6');
  dbms_output.put_line('A qtde de alunos com n�mero de faltas maior que 15 ' ||  
  ' � de ' || V_Total || ' aluno');
END;
