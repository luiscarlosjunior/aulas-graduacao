DECLARE
  -- Aqui eu defini um tipo
  type meu_aluno IS TABLE OF aluno%rowtype
    INDEX BY BINARY_INTEGER;
  -- Declarei o meu tipo criado
  v_aluno meu_aluno;
BEGIN
  
  v_aluno(1).nome   := 'Roberta';
  v_aluno(89).nome  := 'Ricardo';
  v_aluno(50).nome  := 'Hugo';
  
  v_aluno.DELETE(89);
  
  dbms_output.put_line(v_aluno(89).nome);
  
--  FOR i IN 1..v_aluno.count LOOP
--    IF v_aluno.EXISTS(i) THEN
--      dbms_output.put_line('Existe valor neste índice');
--    ELSE
--      dbms_output.put_line('Não existe valor neste índice');
--    END IF;
--  END LOOP;
  
  
  
  --dbms_output.put_line('Indice anterior: ' || v_aluno.PRIOR(1));
  --dbms_output.put_line('Indice posterior: ' || v_aluno.NEXT(1));
  
  --dbms_output.put_line('Primeiro indice: ' || v_aluno.FIRST);
  --dbms_output.put_line('Ultimo indice: ' || v_aluno.LAST);
  
END;