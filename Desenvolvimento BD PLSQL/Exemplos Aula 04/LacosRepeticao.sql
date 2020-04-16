SET SERVEROUTPUT ON
/*DECLARE
  x NUMBER := 0;
BEGIN

  LOOP
    DBMS_OUTPUT.PUT_LINE ('Dentro loop:  x = ' || TO_CHAR(x));
    x := x + 1;
    IF x > 3 THEN
      EXIT;
    END IF;
  END LOOP;
  -- Depois da palavra EXIT, o fluxo começa
  DBMS_OUTPUT.PUT_LINE(' Depois loop:  x = ' || TO_CHAR(x));
  
END;
*/

DECLARE
  pronto  BOOLEAN := FALSE;
BEGIN
  
  WHILE pronto LOOP
    DBMS_OUTPUT.PUT_LINE ('This line does not print.');
    pronto := TRUE;  -- This assignment is not made.
  END LOOP;

  WHILE NOT pronto LOOP
    DBMS_OUTPUT.PUT_LINE ('Hello, world!');
    pronto := TRUE;
  END LOOP;
END;


/*
DECLARE
  idade1 NUMBER(3);
  idade2 NUMBER(3);
BEGIN

  FOR i IN 1..10 LOOP
    dbms_output.put_line(i);
  END LOOP;
END;
*/