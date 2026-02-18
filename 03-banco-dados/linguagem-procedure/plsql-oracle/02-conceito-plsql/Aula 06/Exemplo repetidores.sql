DECLARE 
    n_contador number := 1;
BEGIN
    LOOP
        dbms_output.put_line(n_contador);
        n_contador := n_contador + 1;
        IF n_contador > 5 THEN
            EXIT;
        END IF;
    END LOOP;
END;
-----------------