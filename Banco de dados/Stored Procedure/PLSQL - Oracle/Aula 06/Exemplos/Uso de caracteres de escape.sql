SET SERVEROUTPUT ON

DECLARE
  vTEXTO VARCHAR2(30);
BEGIN
  vTEXTO := '&Entre_com_os_dados';
  dbms_output.put_line(vTEXTO);
END;
/