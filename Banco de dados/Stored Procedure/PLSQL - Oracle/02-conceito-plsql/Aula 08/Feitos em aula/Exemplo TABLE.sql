DECLARE
  -- Aqui eu defini um tipo
  type meu_aluno IS TABLE OF aluno%rowtype
    INDEX BY BINARY_INTEGER;
  -- Declarei o meu tipo criado
  v_aluno meu_aluno;
BEGIN
  -- Atribuição de valores
  v_aluno(1).nome := 'Helena';
  v_aluno(1).ra := '987654321';
  v_aluno(2).nome := 'Camila';
  v_aluno(2).ra := '987654321';

  -- Exibir valores
  dbms_output.put_line('Nome: ' || v_aluno(1).nome || ' RA: ' || v_aluno(1).ra );
  dbms_output.put_line('Nome: ' || v_aluno(2).nome || ' RA: ' || v_aluno(2).ra );
END;