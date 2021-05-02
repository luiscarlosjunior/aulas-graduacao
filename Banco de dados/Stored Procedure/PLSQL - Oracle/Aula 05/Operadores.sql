DECLARE
  -- Declarando constantes
  idadeMaxima CONSTANT NUMBER(3) := 125;
  idadeMinima CONSTANT NUMBER(3) := 0; 
  b_resultado boolean;
  
  idade1 NUMBER(3) := 2;
  idade2 NUMBER(3) := 2;
BEGIN
      
  -- Operadores aritméticos
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
  
  -- Operadores lógicos
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

-- Operadores de comparação
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
  END IF;*/ 
  
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
  
  -- Operadores lógicos
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
   introduction clob; 
   choice char(1); 
BEGIN 
   name := 'John Smith'; 
   company := 'Infotech'; 
   introduction := ' Hello! I''m John Smith from Infotech.'; 
   choice := 'y'; 
   IF choice = 'y' THEN 
      dbms_output.put_line(name); 
      dbms_output.put_line(company); 
      dbms_output.put_line(introduction); 
   END IF; 
END; 
/