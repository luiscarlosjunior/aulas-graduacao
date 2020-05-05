SET SERVEROUTPUT ON

DECLARE
  -- Declara as tabelas
  TYPE AlunoArray
    IS TABLE OF aluno.nome%TYPE
    INDEX BY SIMPLE_INTEGER;
    
  -- Declaro variavéis 
  Nomes AlunoArray;
    
BEGIN

  SELECT NOME
    INTO Nomes(100)
  FROM alunos
  WHERE ROWNUM <= 100;

  -- Looping
  For I in 0..100 
  LOOP
    DBMS_OUTPUT.PUT_LINE('Nome: ' || Nomes(I));
  END LOOP

END;