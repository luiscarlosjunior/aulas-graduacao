DECLARE 
  vAluno aluno%ROWTYPE;
  vAux NUMBER(4);
  vCont NUMBER(2) := 0;
  vLimite NUMBER(2) := 0;
BEGIN
  -- Pega a quantidade de registro
  SELECT MAX(ID) 
    INTO vAux
  FROM ALUNO;
  
  DBMS_OUTPUT.PUT_LINE(vAux);
  vAluno.Nome := '&Entre_com_Nome';
  vAluno.Sobrenome := '&Entre_com_Sobrenome';
  vLimite := &Quantidade_de_vezes;
  
  LOOP
    vAux := vAux + 1;
    vCont := vCont + 1;
    -- Pegar os dados
  
    IF vCont > vLimite THEN
      EXIT;
    END IF;
    
    INSERT INTO ALUNO 
    (ID, RA, NOME, SOBRENOME, CURSO, PERIODO)
    VALUES 
    (vAux, 900000 + vAux, 'vAluno.Nome', 'vAluno.Sobrenome', 
      'Ciências da computação', 'Noturno');

  END LOOP;
  
END;