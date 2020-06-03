/*CREATE OR REPLACE PROCEDURE nome_procedure
 (argumento1 IN | OUT |IN OUT tipo_de_dados,
  argumento2 IN | OUT |IN OUT tipo_de_dados,
  ...
  argumentoN IN | OUT |IN OUT tipo_de_dados) IS | AS
  variáveis locais, constantes, ...
BEGIN
...
END nome_procedure;
*/

SET SERVEROUTPUT ON

CREATE OR REPLACE PROCEDURE HelloWorld
AS
BEGIN
  dbms_output.put_line('Hello World!');
END HelloWorld;
/

execute HelloWorld
  
