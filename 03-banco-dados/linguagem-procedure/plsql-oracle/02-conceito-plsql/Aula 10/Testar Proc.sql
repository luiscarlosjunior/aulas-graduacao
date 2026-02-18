--SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE retornar_idade(
  ano_nasc IN NUMBER,
  ano_atual IN NUMBER,
  idade OUT NUMBER
)
IS
BEGIN
  idade := ano_atual - ano_nasc;
END retornar_idade;
/

DECLARE 
  idade NUMBER;
  ano_nasc NUMBER(4);
  ano_atual NUMBER(4);
BEGIN
  ano_nasc := 1993;
  ano_atual := 2020;
  -- Fazer um procedimento para retornar a idade
  retornar_idade(ano_nasc, ano_atual, idade);
   DBMS_OUTPUT.PUT_LINE(idade);    
END;




DECLARE 
  idade NUMBER;
BEGIN
  retornar_idade(1900, 2052, idade);
  DBMS_OUTPUT.PUT_LINE(idade);    
END;

