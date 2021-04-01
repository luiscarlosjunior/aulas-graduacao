
SET SERVEROUTPUT ON

DECLARE 
  vRA aluno.ra%TYPE;
  vNOME aluno.nome%type;
  vSOBRENOME vNOME%type;
BEGIN
  vRA := '99900099900';
  vNOME := 'Lais';
  vSOBRENOME := 'Matos';
  dbms_output.put_line(vRA || vNOME || ' ' || vSOBRENOME);  
END;