SET SERVEROUTPUT ON

DECLARE
  vTEXTO VARCHAR2(30);
  VIDADE NUMBER(3);
BEGIN
  -- Usa-se aspas simples quando for texto
  vTEXTO := '&Entre_com_o_seu_nome';
  -- Não usa aspas quando for numérico
  vIDADE := &Entre_com_a_Idade;
  dbms_output.put_line(vTEXTO);
  dbms_output.put_line(vIDADE);
END;
/