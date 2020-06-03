SET SERVEROUTPUT ON;

DECLARE 
  idade NUMBER;
  ano_nasc NUMBER(4);
  ano_atual NUMBER(4);
BEGIN
  ano_nasc := 1993;
  ano_atual := 2020;
  retornar_idade(ano_nasc, ano_atual, idade);
   DBMS_OUTPUT.PUT_LINE(idade);    
END;

DECLARE 
  idade NUMBER;
BEGIN
  retornar_idade(1900, 2052, idade);
  DBMS_OUTPUT.PUT_LINE(idade);    
END;

