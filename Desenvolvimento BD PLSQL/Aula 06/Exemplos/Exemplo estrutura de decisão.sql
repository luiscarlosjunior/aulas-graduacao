SET SERVEROUTPUT ON

/*
DECLARE 
  idade1  NUMBER(2)  := &Entre_com_a_idade;
  idade2  NUMBER(2)  := &Entre_com_a_idade;
BEGIN
  
  if idade1 > idade2 then
    dbms_output.put_line('Idade 1 é maior que a idade 2');
  else
    dbms_output.put_line('Idade 2 é maior que a idade 1');
  end if;
END;
/
*/

DECLARE 
  idade1  NUMBER(2)  := &Entre_com_a_idade;
  idade2  NUMBER(2)  := &Entre_com_a_idade;
BEGIN
  if idade1 > idade2 then
    dbms_output.put_line('Idade 1 é maior que a idade 2');
  elsif idade1 = idade2 then
    dbms_output.put_line('As idades são iguais');
  else
    dbms_output.put_line('Idade 2 é maior que a idade 1');
  end if;
END;
/