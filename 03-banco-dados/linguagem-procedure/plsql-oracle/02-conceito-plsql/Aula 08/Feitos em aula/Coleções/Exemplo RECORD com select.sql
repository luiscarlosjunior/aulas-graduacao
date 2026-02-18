DECLARE
  -- Defini o meu tipo
  type registro_aluno IS RECORD (
    registro aluno.ra%type,
    nome aluno.nome%type
  );
  
  -- Declarar o meu tipo
  v_registro registro_aluno;
BEGIN

  SELECT RA, NOME
    INTO v_registro.registro, v_registro.nome
  FROM aluno
  WHERE ROWNUM <= 1;
  
  dbms_output.put_line('Registro: ' || v_registro.registro);
  dbms_output.put_line('Nome: ' || v_registro.nome);
  
END;