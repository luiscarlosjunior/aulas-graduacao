SET SERVEROUTPUT ON;

DECLARE
  type exemplo_varray IS VARRAY(5) OF NUMBER(3);
  idade_varray exemplo_varray; 
BEGIN
  
  idade_varray := exemplo_varray(1, 2, 3, 4, 5);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
END;
/
DECLARE
  type exemplo_varray IS VARRAY(5) OF aluno.faltas%type;
  idade_varray exemplo_varray; 
BEGIN
  
  idade_varray := exemplo_varray(1, 2, 3, 4, 5);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
END;
