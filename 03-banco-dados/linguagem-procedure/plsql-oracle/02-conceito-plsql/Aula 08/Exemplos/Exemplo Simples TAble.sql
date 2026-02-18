SET SERVEROUTPUT ON;
DECLARE
  TYPE meuNome IS TABLE OF aluno.nome%type 
    INDEX BY BINARY_INTEGER;
  vNome meuNome;
BEGIN

  vNome(0) := 'Antonio';
  vNome(1) := 'LUIS';
  vNome(2) := 'LAIS';
    
  dbms_output.put_line('Nome: ' || vNome.NEXT(i));
    
  FOR i IN 0..vNome.COUNT
  LOOP
    dbms_output.put_line('Nome: ' || vNome(i));
  END LOOP;
    
  dbms_output.put_line('Não tem dado' || vNome.FIRST);
  dbms_output.put_line('Não tem dado' || vNome.LAST);
  
  IF vNome.EXISTS(1) THEN
    dbms_output.put_line('Existe dado');
  ELSE
    dbms_output.put_line('Não tem dado');
  END IF;

END;
--/
--DECLARE
--  -- Definindo um tipo TABLE
--  TYPE meuAluno IS TABLE OF aluno%rowtype 
--    INDEX BY BINARY_INTEGER;
--  -- Declarando o tipo TABLE criado
--  vNome meuNome;
--BEGIN
--
--  vNome(1).Nome := 'Luis Carlos';
--  vNome(1).RA := '963252';
--  vNome(2).Nome := 'Gustavo Lima';
--  vNome(2).RA := '789654';
--  vNome(3).Nome := 'Lais Helena';
--  vNome(3).RA := '123456';
--  
----  IF vNome.EXISTS(1) THEN
----    dbms_output.put_line('Tem dados ');
----  ELSE
----    dbms_output.put_line('Não tem dado');
----  END IF;
--  
--  --dbms_output.put_line('Nome: ' || meuAluno(1).Nome);
--  
----  FOR i IN 1..vNome.COUNT
----  LOOP
----    dbms_output.put_line('Nome: ' || vNome(i).Nome);
----  END LOOP;
--END;
--/