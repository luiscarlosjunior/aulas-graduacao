SET SERVEROUTPUT ON;

CREATE OR REPLACE PACKAGE pack_1 IS
PROCEDURE proc_1;
FUNCTION func_1 RETURN VARCHAR2;
END pack_1;
/

CREATE OR REPLACE PACKAGE BODY pack_1 IS
  PROCEDURE proc_1
  IS
  BEGIN
    DBMS_OUTPUT.PUT_LINE('Mensagem da Procedure');
  END proc_1;
  
  FUNCTION func_1 
  RETURN VARCHAR2 IS
  BEGIN
    RETURN('Mensagem da Function');
  END func_1;
END pack_1;
/

exec pack_1.proc_1;

SELECT pack_1.func_1 FROM DUAL;