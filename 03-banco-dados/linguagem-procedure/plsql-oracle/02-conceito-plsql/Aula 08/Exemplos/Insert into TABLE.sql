
-- Usando o BULK COLLECT
DECLARE
  TYPE meuAluno IS TABLE OF aluno%rowtype 
    INDEX BY BINARY_INTEGER;
  aluno_table meuAluno;
BEGIN
  
  -- INSERT
  SELECT * 
    BULK COLLECT INTO aluno_table
    FROM ALUNO;
      
  FOR i IN 0..aluno_table.COUNT
  LOOP
    dbms_output.put_line(i);
  END LOOP;
    
END;

SET SERVEROUTPUT ON;
-- Usando a forma normal

DECLARE
  TYPE t_aluno IS TABLE OF aluno%rowtype 
    INDEX BY BINARY_INTEGER;
  aluno_table t_aluno;
BEGIN
  
  -- SELECT
  SELECT nota, nome, genero
    INTO aluno_table(1)
    FROM ALUNO
    WHERE RA = '99073972248';
  
  SELECT * 
    INTO aluno_table(76)
    FROM ALUNO
    WHERE RA = '99073972248';
    
  FOR i IN 1..aluno_table.COUNT
  LOOP
    dbms_output.put_line(i);  
    dbms_output.put_line(aluno_table(aluno_table.Next(i)).genero);

  END LOOP;
    
END;
/
