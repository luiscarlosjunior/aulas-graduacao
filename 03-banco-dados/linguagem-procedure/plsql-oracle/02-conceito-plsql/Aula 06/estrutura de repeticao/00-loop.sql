---------------------------------


<<label>> LOOP
    statements;
END LOOP loop_label;

--A LOOPdeclaração pode ter um rótulo opcional que aparece no início e no final da declaração.

--É uma boa prática usar a LOOPinstrução quando:

--Você deseja executar o corpo do loop pelo menos uma vez.
--Você não tem certeza de quantas vezes deseja que o loop seja executado.
LOOP
    EXIT;
END LOOP; 

LOOP
    IF condition THEN
        EXIT;
    END IF;
END LOOP;

DECLARE
  l_counter NUMBER := 0;
BEGIN
  LOOP
    l_counter := l_counter + 1;
    IF l_counter > 3 THEN
      EXIT;
    END IF;
    dbms_output.put_line( 'Inside loop: ' || l_counter )  ;
  END LOOP;
  -- control resumes here after EXIT
  dbms_output.put_line( 'After loop: ' || l_counter );
END;


DECLARE
  l_contador NUMBER := 0;
BEGIN
  LOOP
    l_contador := l_contador + 1;
    EXIT WHEN l_contador > 5;
    dbms_output.put_line( 'Laco interno: ' || l_contador) ;
  END LOOP;

  -- Pula para essa linha quando a condi��o do WHEN � satisfeito
  dbms_output.put_line( 'Apos o la�o terminar: ' || l_contador );
END;


--------------------------------------------------------

DECLARE
  l_i NUMBER := 0;
  l_j NUMBER := 0;
BEGIN
  <<outer_loop>>
  LOOP
    l_i := l_i + 1;
    EXIT outer_loop WHEN l_i > 2;    
    dbms_output.put_line('Outer counter ' || l_i);
    -- reset inner counter
    l_j := 0;
      <<inner_loop>> LOOP
      l_j := l_j + 1;
      EXIT inner_loop WHEN l_j > 3;
      dbms_output.put_line(' Inner counter ' || l_j);
    END LOOP inner_loop;
  END LOOP outer_loop;
END;


---------------------------------------------------------------------------------





BEGIN 
    FOR n_contador IN 1..5
    LOOP
        dbms_output.put_line(n_contador);
    END LOOP;
END;

DECLARE
    n_contador number;
BEGIN 
    FOR n_contador IN 1..5
    LOOP
        dbms_output.put_line(n_contador);
    END LOOP;
END;

-- Trabalhando com escopo - Ciclo de vida �til de uma vari�vel

BEGIN
  FOR l_counter IN REVERSE 1..5
  LOOP
    DBMS_OUTPUT.PUT_LINE( l_counter );
  END LOOP;
END;







