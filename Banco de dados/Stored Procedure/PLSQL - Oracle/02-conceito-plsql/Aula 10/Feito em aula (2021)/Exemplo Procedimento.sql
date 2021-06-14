SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE HelloWorld
AS
BEGIN
    dbms_output.put_line('Hello, world!');    
END;

BEGIN
    HelloWorld;
END;
exec helloworld;
execute helloworld;

helloworld;
/
-- Criando PROCEDURE com Parâmetro de entrada
CREATE OR REPLACE PROCEDURE SomaDoisNumero (
    n_numero1 NUMBER,
    n_numero2 NUMBER
)
AS
BEGIN
    dbms_output.put_line(n_numero1 + n_numero2);
END;


BEGIN
    SomaDoisNumero(10, 50);
END;
exec SomaDoisNumero(15, 9);
execute SomaDoisNumero(35, 71);



