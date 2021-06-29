/*
9) Declare variáveis, para receber e exibir o resultado de um comando que retorne à
quantidade de alunos dos sexos diferentes do masculino e feminino, sendo que esses textos
de busca deverão ser feitos através das variáveis criadas;
*/

/*
Declare variáveis, para receber e exibir o resultado de um comando para retornar à
quantidade de alunos dos sexos masculino e feminino que possuem o número de faltas
maiores do que 15
*/
SET SERVEROUTPUT ON
DECLARE
  V_GENERO_MASC VARCHAR2(30) := 'Masculino';
  V_GENERO_FEM VARCHAR2(30) := 'Feminino';
  V_Total aluno.nota%type;
BEGIN
  
  -- Exercicio 9
  SELECT COUNT(ID) INTO V_Total FROM ALUNO WHERE GENERO <> v_genero_masc AND 
    GENERO <> v_genero_fem;    
  dbms_output.put_line('Exercicio 9');
  dbms_output.put_line('A qtde de alunos do generos diferentes de masculino e feminino é de ' || V_Total);
  dbms_output.put_line('--------------------------------');
  
  -- Exercicio 10
  dbms_output.put_line('Exercicio 10');
  SELECT COUNT(ID) INTO V_Total FROM ALUNO 
  WHERE GENERO = v_genero_masc OR GENERO = v_genero_fem AND FALTAS > 15;    
  dbms_output.put_line('A qtde de alunos que possuem as faltas acima de 15 é de ' || V_Total);  
  
END;