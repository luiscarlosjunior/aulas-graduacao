FOR index IN lower_bound .. upper_bound
LOOP 
    statements; 
END LOOP;


BEGIN
  FOR l_counter IN 1..5
  LOOP
    DBMS_OUTPUT.PUT_LINE( l_counter );
  END LOOP;
END;


--Simulando STEPcláusula em FOR LOOPdeclaração
DECLARE
  l_step  PLS_INTEGER := 2;
BEGIN
  FOR l_counter IN 1..5 LOOP
    dbms_output.put_line (l_counter*l_step);
  END LOOP;
END;

--Variável de referência com o mesmo nome do índice de loop
DECLARE
  l_counter PLS_INTEGER := 10;
BEGIN
  FOR l_counter IN 1.. 5 loop
    DBMS_OUTPUT.PUT_LINE (l_counter);
  end loop;
  -- after the loop
  DBMS_OUTPUT.PUT_LINE (l_counter);
END;

<<outer>>
DECLARE
  l_counter PLS_INTEGER := 10;
BEGIN
  FOR l_counter IN 1.. 5 loop
    DBMS_OUTPUT.PUT_LINE ('Local counter:' ||  l_counter);
    outer.l_counter := l_counter;
  end loop;
  -- after the loop
  DBMS_OUTPUT.PUT_LINE ('Global counter' || l_counter);
END outer;

BEGIN
  FOR l_index IN 1..3 loop
    DBMS_OUTPUT.PUT_LINE (l_index);
  END LOOP;
  -- referencing index after the loop
  --DBMS_OUTPUT.PUT_LINE (l_index);
END;

-- Reverse

FOR index IN REVERSE lower_bound .. upper_bound
    LOOP 
    statements; 
END LOOP;

BEGIN
  FOR l_counter IN REVERSE 1..3
  LOOP
    DBMS_OUTPUT.PUT_LINE( l_counter );
  END LOOP;
END;