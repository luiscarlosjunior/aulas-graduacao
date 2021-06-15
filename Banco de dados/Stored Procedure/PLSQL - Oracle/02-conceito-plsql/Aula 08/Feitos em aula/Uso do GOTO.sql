DECLARE
  v_nome aluno.nome%type;
  vID NUMBER(3) := 1;
BEGIN
  <<pegar_nome>>
  SELECT nome
    INTO v_nome
  FROM aluno
  WHERE ID = vID;
  dbms_output.put_line(v_nome);
  dbms_output.put_line(vID);
  IF v_nome != 'Emma' THEN
    vID := vID + 1;
    GOTO pegar_nome; -- Criando uma ramificação
  END IF;
END;