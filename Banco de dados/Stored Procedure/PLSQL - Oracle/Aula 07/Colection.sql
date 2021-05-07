SET SERVEROUTPUT ON

DECLARE
  -- Declara as tabelas
  TYPE AlunoArray
    IS TABLE OF aluno.nome%TYPE;
    
  -- Declaro variav�is 
  Nomes AlunoArray;
    
BEGIN

  SELECT NOME
    INTO Nomes(1)
  FROM aluno
  WHERE ROWNUM <= 1;

  -- Looping
  --For I in 0..50 
  --LOOP
    DBMS_OUTPUT.PUT_LINE('Nome: ' || Nomes(1));
  --END LOOP;

END;