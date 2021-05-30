--Atributos

SET SERVEROUTPUT ON;
DECLARE
  TYPE t_Nome IS TABLE OF aluno.nome%type 
    INDEX BY BINARY_INTEGER;
  v_Nome t_Nome;
BEGIN

  v_Nome(0) := 'Antonio';
  v_Nome(1) := 'LUIS';
  v_Nome(2) := 'LAIS';
    
  dbms_output.put_line('Indice: ' || v_Nome.NEXT(1));
  dbms_output.put_line('Indice: ' || v_Nome.PRIOR(1));
  dbms_output.put_line('Indice: ' || v_Nome(v_Nome.NEXT(1)));
END;

SET SERVEROUTPUT ON;
DECLARE
  TYPE meuNome IS TABLE OF aluno.nome%type 
    INDEX BY BINARY_INTEGER;
  vNome meuNome;
BEGIN

  vNome(0) := 'Antonio';
  vNome(1) := 'LUIS';
  vNome(2) := 'LAIS';
    
  --dbms_output.put_line('Indice: ' || vNome.NEXT(1));
  --dbms_output.put_line('Indice: ' || vNome.PRIOR(1));
  
  /*FOR i IN 0..vNome.COUNT  LOOP
    dbms_output.put_line('Nome: ' || vNome(i));
  END LOOP;*/
    
  --dbms_output.put_line('Não tem dado' || vNome.FIRST);
  --dbms_output.put_line('Não tem dado' || vNome.LAST);
   
  IF vNome.EXISTS(32) THEN
    dbms_output.put_line('Existe dado');
  ELSE
    dbms_output.put_line('Não tem dado');
  END IF;

  vNome.DELETE(2);

END;