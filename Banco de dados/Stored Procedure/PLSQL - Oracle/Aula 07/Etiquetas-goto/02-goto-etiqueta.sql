BEGIN
  FOR v_contador IN 1..15 LOOP
    dbms_output.put_line('Contador: ' || v_contador);
    IF v_contador = 5 THEN
      GOTO FIM;
    END IF;
  END LOOP;
  dbms_output.put_line('Passou aqui?');
  <<FIM>>
  dbms_output.put_line('Final do programa');
END;
