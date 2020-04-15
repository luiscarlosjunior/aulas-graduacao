

DECLARE 
  numero  NUMBER(2) := 10;
  numero2 NUMBER(2) := 10;
  idade   NUMBER(3) := 125;
  verdade BOOLEAN := false;
  grade   CHAR(1);
BEGIN
  
  dbms_output.put_line('Abaixo segue os exemplo');
  
  -- Operadores aritméticos
  -- dbms_output.put_line(numero + numero2);
  -- dbms_output.put_line(numero * numero2);
  -- dbms_output.put_line(numero / numero2);
  -- dbms_output.put_line(numero - numero2);
  -- dbms_output.put_line(numero mod numero2);

  -- Operadores comparação
  /*
  if numero <> numero2 then
    dbms_output.put_line('Idade 1 é maior que a idade 2');
  else
    dbms_output.put_line('Idade 2 é maior que a idade 1');
  end if;
  */
  
  /*
    IF idade >= 0 AND idade <= 120 THEN
    IF idade >= 18 THEN
      SYS.dbms_output.put_line('Seção adulto');
    ELSIF idade >= 15 THEN
      dbms_output.put_line('Seção Juvenil');
    ELSE
      dbms_output.put_line('Seção Infatil');
    END IF;
  ELSE
    dbms_output.put_line('Idade Inválida');
  END IF;  
  */
  /*
  for i in reverse 1..10 LOOP
    dbms_output.put_line(i);
  END LOOP;
  */
  
  -- grade := 'B';

  /*
  CASE grade
    WHEN 'A' THEN DBMS_OUTPUT.PUT_LINE('Excellent');
    WHEN 'B' THEN DBMS_OUTPUT.PUT_LINE('Very Good');
    WHEN 'C' THEN DBMS_OUTPUT.PUT_LINE('Good');
    WHEN 'D' THEN DBMS_OUTPUT.PUT_LINE('Fair');
    WHEN 'F' THEN DBMS_OUTPUT.PUT_LINE('Poor');
    --ELSE DBMS_OUTPUT.PUT_LINE('No such grade');
  END CASE;
 */
 
 
 
END;