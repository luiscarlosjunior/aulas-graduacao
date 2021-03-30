/*
Vamos criar um INSTEAD OF Trigger que irá capturar os valores informados no 
INSERT da view, mas que também vai adicionar um .nextval da sequence an coluna primária.
*/

SET SERVEROUTPUT ON;

CREATE TABLE teste_pk (
  teste_id NUMBER,
  nome VARCHAR2(10),
  sobrenome VARCHAR2(10)
);
/
 
CREATE OR REPLACE VIEW teste_pk_vw AS
SELECT nome, sobrenome
FROM teste_pk;
/
 
CREATE SEQUENCE teste_pk_seq;
/
 
CREATE OR REPLACE TRIGGER trigger_seq
INSTEAD OF INSERT
ON teste_pk_vw
FOR EACH ROW
BEGIN
  INSERT INTO teste_pk
  VALUES(teste_pk_seq.NEXTVAL, :new.nome, :new.sobrenome);
END trigge_sq;
/
 
INSERT INTO teste_pk_vw
VALUES('Tércio', 'Costa');
/
 
SELECT * FROM teste_pk;
/