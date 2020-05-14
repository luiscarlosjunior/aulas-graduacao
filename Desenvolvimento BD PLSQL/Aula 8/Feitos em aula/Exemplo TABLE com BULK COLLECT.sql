DECLARE
  -- Defini o meu tipo de dados (Coleção)
  TYPE meu_aluno IS TABLE OF aluno%rowtype
    INDEX BY BINARY_INTEGER;
  
  -- Declaro o meu tipo de dados para poder usar
  v_aluno meu_aluno;
BEGIN

  -- Selecionar alguns dados (todos)
  SELECT * 
    BULK COLLECT INTO v_aluno
  FROM aluno;
    
  FOR i IN 1..v_aluno.count LOOP
    dbms_output.put_line('Indice: ' || i || 'Nome: ' || v_aluno(i).nome);
  END LOOP;
  
END;