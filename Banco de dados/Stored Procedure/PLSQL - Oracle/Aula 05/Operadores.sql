DECLARE
  -- Declarando constantes
  idadeMaxima CONSTANT NUMBER(3) := 125;
  idadeMinima CONSTANT NUMBER(3) := 0; 
  b_resultado boolean;
  
  idade1 NUMBER(3) := 2;
  idade2 NUMBER(3) := 2;
BEGIN
      
  -- Operadores aritm�ticos
  dbms_output.put_line(CONCAT(idade1, idade2));
  dbms_output.put_line(idade1 * idade2);
  dbms_output.put_line(idade1 - idade2);
  dbms_output.put_line(idade1 / idade2);
  dbms_output.put_line(idade1 mod idade2);
  dbms_output.put_line(idade1 ** idade2);
 
  /*
  b_resultado := idade1 = 0;
  b_resultado := idade1 <= 0;
  b_resultado := idade1 >= 0;
  b_resultado := NOT(idade1 = 0);
  b_resultado := idade1 != 0;
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF;
  
  -- Operadores l�gicos
  b_resultado := idade1 = 0 AND idade >= 20;
  b_resultado := idade1 <= 0 AND idade >= 20;
  b_resultado := idade1 = 0 AND idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 = 0 OR idade >= 20;
  b_resultado := idade1 <= 0 OR idade >= 20;
  b_resultado := idade1 = 0  OR idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 != 0;
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF;*/ 
  
END;
/

-- Operadores de compara��o
DECLARE
  -- Declarando constantes
  idadeMaxima CONSTANT NUMBER(3) := 125;
  idadeMinima CONSTANT NUMBER(3) := 0; 
  b_resultado boolean;
  
  idade1 NUMBER(3) := 2;
  idade2 NUMBER(3) := 2;
  v_nome VARCHAR(50) := '';
BEGIN
     
  
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF; 
  
END;


DECLARE
  b_resultado boolean := true;
BEGIN
  IF b_resultado THEN
    dbms_output.put_line('FALSE');
  ELSE
    dbms_output.put_line('TRUE');
  END IF; 
END;



DECLARE
  b_resultado boolean := true;
BEGIN
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF; 
END;


DECLARE
  -- Declarando constantes
  idadeMaxima CONSTANT NUMBER(3) := 125;
  idadeMinima CONSTANT NUMBER(3) := 0; 
  b_resultado boolean;
  
  idade1 NUMBER(3) := 2;
  idade2 NUMBER(3) := 2;
BEGIN
      
  /*
  b_resultado := idade1 = 0;
  b_resultado := idade1 <= 0;
  b_resultado := idade1 >= 0;
  b_resultado := NOT(idade1 = 0);
  b_resultado := idade1 != 0;
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF;
  
  -- Operadores l�gicos
  b_resultado := idade1 = 0 AND idade >= 20;
  b_resultado := idade1 <= 0 AND idade >= 20;
  b_resultado := idade1 = 0 AND idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 = 0 OR idade >= 20;
  b_resultado := idade1 <= 0 OR idade >= 20;
  b_resultado := idade1 = 0  OR idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 != 0;
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF;*/ 
  
END;


DECLARE 
   name varchar2(20); 
   company varchar2(30); 
   introduction varchar2(100); 
   choice char(1); 
BEGIN 
   name := 'John Smith'; 
   company := 'Infotech'; 
   introduction := 'Olá! Eu sou John Smith da Infotech.'; 
   choice := 's'; 
   IF choice = 's' THEN 
      dbms_output.put_line(name); 
      dbms_output.put_line(company); 
      dbms_output.put_line(introduction); 
   END IF; 
END; 
/

DECLARE
  type resultados IS VARRAY(5) OF BOOLEAN; 
  b_resultado resultados;
  idade1 NUMBER(3) := 25;
  idade2 NUMBER(3) := 65;
BEGIN
 
  b_resultado := idade1 = idade2;
  b_resultado := idade1 <= idade2;
  b_resultado := idade1 >= idade2;
  b_resultado := NOT(idade1 = idade2);
  b_resultado := idade1 != idade2;
  

  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF;
  
  -- Operadores l�gicos
  b_resultado := idade1 = 0 AND idade >= 20;
  b_resultado := idade1 <= 0 AND idade >= 20;
  b_resultado := idade1 = 0 AND idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 = 0 OR idade >= 20;
  b_resultado := idade1 <= 0 OR idade >= 20;
  b_resultado := idade1 = 0  OR idade != 20;
  b_resultado := NOT(idade1 = 0 AND idade >= 20);
  b_resultado := idade1 != 0;
  
  IF NOT(b_resultado) THEN
    dbms_output.put_line('TRUE');
  ELSE
    dbms_output.put_line('FALSE');
  END IF; 
  
END;
/