SET SERVEROUTPUT ON;

DECLARE
  -- Declarando constantes
  idadeMaxima CONSTANT NUMBER(3) := 125;
  idadeMinima CONSTANT NUMBER(3) := 0; 
  
  idade1 NUMBER(3);
  idade2 NUMBER(3);
BEGIN
  -- Entre 60 e 125 anos, grupo risco;
  -- Entre 18 e 59 anos, tomar cuidado;
  -- Entre 0 e 17 anos, fique estudando;
  
  idade1 := 50;
  
  IF NOT(idade1 < 0 OR idade1 > 125) THEN
    IF idade1 >= 60 AND idade1 <= 125 THEN
      dbms_output.put_line('Voc� est� no grupo de risco, tome cuidado!');
    ELSIF idade1 >= 18 AND idade1 <= 59 THEN
      dbms_output.put_line('Tome cuidade, fique em casa!');
    ELSE
      dbms_output.put_line('Fique estudando!');
    END IF;
  ELSE
    dbms_output.put_line('Idade inv�lida!');
  END IF;
  
  -- Operadores de compara��o
  /*
  -- Uma segunda solu��o
  IF idade1 <= 0 OR idade1 > 125 THEN
    dbms_output.put_line('Idade inv�lida!');
  ELSE
    IF idade1 >= 60 AND idade1 <= 125 THEN
      dbms_output.put_line('Voc� est� no grupo de risco, tome cuidado!');
    ELSIF idade1 >= 18 AND idade1 <= 59 THEN
      dbms_output.put_line('Tome cuidade, fique em casa!');
    ELSE
      dbms_output.put_line('Fique estudando!');
    END IF;
  END IF;
  */

  -- Operadores aritm�ticos
  /*
  dbms_output.put_line(idade1 + idade2);
  dbms_output.put_line(idade1 * idade2);
  dbms_output.put_line(idade1 - idade2);
  dbms_output.put_line(idade1 / idade2);
  dbms_output.put_line(idade1 mod idade2);
  */
END;