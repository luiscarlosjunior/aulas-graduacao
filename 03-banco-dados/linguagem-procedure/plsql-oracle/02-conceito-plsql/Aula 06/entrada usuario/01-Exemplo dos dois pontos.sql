SET SERVEROUTPUT ON

DECLARE
  vTEXTO VARCHAR2(30);
  VIDADE NUMBER(3);
BEGIN
  vTEXTO := :Entre_com_o_seu_nome;
  vIDADE := :Entre_com_a_Idade;
  dbms_output.put_line(vTEXTO);
  dbms_output.put_line(vIDADE);
END;
