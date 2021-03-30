SET SERVEOUTPUT ON

DECLARE 
  vNOME   aluno.nome%type := :vNOME;
  vQtde   NUMBER(2);
BEGIN

  SELECT COUNT(RA) 
    INTO vQtde
  FROM ALUNO
  WHERE NOME LIKE vNOME;
  
  IF vQtde > 5 THEN
    dbms_output.put_line('Existem mais de cinco nomes com o texto ' 
    || vNome);
  ELSIF vQtde > 0 THEN
    dbms_output.put_line('Existe ao menos um nome com o texto ' 
    || vNome);
  ELSE
    dbms_output.put_line('Não existe nenhum nome com o texto ' 
    || vNome);
  END IF;
  dbms_output.put_line('Quantidade total encontrado ' || vQtde);  
  
END;