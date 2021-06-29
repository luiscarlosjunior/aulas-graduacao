-- 7)Declare uma variável que receba e exiba o resultado de um comando que retorne à
-- quantidade de alunos que contém no RA o texto o numero 86 em qualquer luga
/*
DECLARE
  V_Total aluno.nota%type;
BEGIN
  
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE RA LIKE '%86%'  ;
  
  -- Exibe o resultado
  dbms_output.put_line('A qtde de alunos que contém 86 no RA é de ' ||  
  ' é de ' || V_Total);
  -- o método to_char Formata a saída de algum valor

END;
*/
-- 8 Declare variáveis, para receber e exibir o resultado de um comando que retorne à
-- quantidade de alunos do sexo masculino e feminino - separadamente, sendo que esses
-- textos de busca deverão ser feitos através das variáveis criadas;
DECLARE
  V_GENERO_MASC VARCHAR2(30) := 'Masculino';
  V_GENERO_FEM VARCHAR2(30) := 'Feminino';
  V_Total aluno.nota%type;
BEGIN
  
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE GENERO = v_genero_masc;    
  -- Exibe o resultado masculino
  dbms_output.put_line('A qtde de alunos do genero masculino é de ' || V_Total);

  dbms_output.put_line('---------------------------------------');
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE GENERO = v_genero_fem;
    
  -- Exibe o resultado
  dbms_output.put_line('A qtde de alunos do genero feminino é de ' || V_Total);

END;