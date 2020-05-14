--TYPE exemplo_varray IS VARRAY(n) of <TIPO_DADO>

DECLARE
  type exemplo_varray IS VARRAY(10) OF aluno.faltas%type;
  idade exemplo_varray;
BEGIN
  idade := exemplo_varray(1, 2, 3, 4, 5);
  
  FOR i in 1..idade.count LOOP
    dbms_output.put_line('Número: ' || idade(i)); 
  END LOOP;
END;