
CREATE OR REPLACE PROCEDURE valorMinimo(x IN number, y IN number, z OUT number) IS
BEGIN
   IF x < y THEN
      z:= x;
   ELSE
      z:= y;
   END IF;
END; 

DECLARE
   a number;
   b number;
   c number;  
BEGIN
   a:= 10;
   b:= 15;
   valorMinimo(a, b, c);
 dbms_output.put_line('O valor mínimo obtido entre os valores ' || a
 || ' e ' || b || ' foi ' || c);  
END;
/