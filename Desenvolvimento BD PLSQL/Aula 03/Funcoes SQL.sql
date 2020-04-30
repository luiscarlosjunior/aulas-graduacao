-- Funções SQL

-- Funções de texto
SELECT LOWER(nome) FROM ALUNO;
SELECT UPPER(nome) FROM ALUNO;
SELECT substr(nome,0,4) as Nome FROM ALUNO WHERE ROWNUM < 5; -- ROWNUM limite a quantidade de linha que é exibida
SELECT INSTR(nome, 'e', 0, LENGTH(nome)) FROM ALUNO WHERE ROWNUM < 5;
SELECT LENGTH(nome) FROM ALUNO;