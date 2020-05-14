
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
  TYPE meuAluno IS TABLE OF aluno%rowtype 
    INDEX BY BINARY_INTEGER;
  aluno_table meuAluno;
BEGIN
  
  -- INSERT
  SELECT * 
    INTO aluno_table(1)
    FROM ALUNO
    WHERE RA = '99073972248    ';
  
  SELECT * 
    INTO aluno_table(76)
    FROM ALUNO
    WHERE RA = '99073972248    ';
    
  FOR i IN 0..aluno_table.COUNT
  LOOP
    dbms_output.put_line(i);
  END LOOP;
    
END;
/
