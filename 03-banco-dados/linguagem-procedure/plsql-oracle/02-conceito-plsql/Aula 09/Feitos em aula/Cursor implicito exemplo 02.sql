DECLARE
  V_ALUNO ALUNO.NOME%TYPE;
  V_RA ALUNO.RA%TYPE;
BEGIN
  V_ALUNO :=  '&Entre_com_um_nome';
  V_RA    :=  '71033266097    ';
  
  UPDATE ALUNO SET NOME = V_ALUNO
  WHERE RA = V_RA;
  
  IF SQL%NOTFOUND THEN
    dbms_output.put_line('Não foi encontrado esse número de RA');
  ELSE
    dbms_output.put_line('Alteração incluida com sucesso!');
  END IF;
  
END;