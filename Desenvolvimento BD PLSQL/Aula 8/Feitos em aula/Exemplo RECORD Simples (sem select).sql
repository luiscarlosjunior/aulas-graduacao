DECLARE
  -- Defini o meu tipo
  type registro_aluno IS RECORD (
    registro CHAR(15),
    nome VARCHAR2(50)
  );
  
  -- Declarar o meu tipo
  v_registro registro_aluno;
BEGIN

  -- Atribuindo valor
  v_registro.registro := '&Digite_um_RA';
  v_registro.nome := '&Digite_um_nome';
  
  --Exibindo
  dbms_output.put_line('Nome: ' || v_registro.nome); 
  dbms_output.put_line('Registro: ' || v_registro.registro);

END;