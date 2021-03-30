DECLARE 
  V_RA ALUNO.RA%TYPE := '9999'; --'71033266097    ';
  V_NOME ALUNO.NOME%TYPE := 'Gisele';
BEGIN
  
  SELECT NOME INTO V_NOME
    FROM ALUNO
    WHERE FALTAS > 10;
  
  dbms_output.put_line(V_RA || ' ' || V_NOME);
  
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    dbms_output.put_line('Não foi encontrado nenhum aluno com o RA ' || V_RA);
  WHEN TOO_MANY_ROWS THEN
      dbms_output.put_line('Por incrível que parece, existe mais desse RA ' || V_RA);
END;