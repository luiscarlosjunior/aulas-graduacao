DECLARE
  -- Aqui eu defini um tipo
  type meu_aluno IS TABLE OF aluno%rowtype
    INDEX BY BINARY_INTEGER;
  -- Declarei o meu tipo criado
  v_aluno meu_aluno;
BEGIN
  
  -- SELECT para retornar um valor
  SELECT *
    INTO v_aluno(1) 
  FROM ALUNO
  WHERE RA = '90612351728    ';
  
  SELECT *
    INTO v_aluno(89) 
  FROM ALUNO
  WHERE RA = '87534302801    ';
  
  SELECT *
    INTO v_aluno(77) 
  FROM ALUNO
  WHERE RA = '57150917852    ';
  
  dbms_output.put_line('Nome: ' || v_aluno(1).nome || ' RA: ' || v_aluno(1).ra);
  dbms_output.put_line('Nome: ' || v_aluno(89).nome || ' RA: ' || v_aluno(89).ra);
  dbms_output.put_line('Nome: ' || v_aluno(77).nome || ' RA: ' || v_aluno(77).ra);
END;