SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE HelloWorld
AS
BEGIN
  dbms_output.put_line('Hello World');
END HelloWorld;
/

execute soma(10, 50);

execute helloworld;
exec helloworld;

BEGIN
  helloworld;
END;
