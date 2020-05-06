-- example with GOTO statement
DECLARE
   vNome  aluno.nome%type;
   vID     NUMBER(3) := 1;
BEGIN
  <<pegar_nome>> 
  SELECT nome 
    INTO vNome 
  FROM aluno 
    WHERE ID = vID;
    
    DBMS_OUTPUT.PUT_LINE (vNome);
    IF vNome != 'Emma' THEN
      vID := vID + 1;
      GOTO pegar_nome;  -- branch to enclosing block
    END IF;
END;
/