SET SERVEROUTPUT ON;

DECLARE 
   v_saudacao varchar2(50) := 'olá, mundo oracle'; 
BEGIN 
   dbms_output.put_line(UPPER(v_saudacao)); 
   dbms_output.put_line(LOWER(v_saudacao)); 
   dbms_output.put_line(INITCAP(v_saudacao)); 
   
   /* retrieve the first character in the string */ 
   dbms_output.put_line ( SUBSTR (v_saudacao, 1, 1)); 
   /* retrieve the last character in the string */ 
   dbms_output.put_line ( SUBSTR (v_saudacao, -1, 1)); 
   /* retrieve five characters,  
      starting from the seventh position. */ 
   dbms_output.put_line ( SUBSTR (v_saudacao, 7, 5)); 
   /* retrieve the remainder of the string, 
      starting from the second position. */ 
   dbms_output.put_line ( SUBSTR (v_saudacao, 2)); 
   /* find the location of the first "e" */ 
   dbms_output.put_line ( INSTR (v_saudacao, 'u')); 
   /* find the location of the first "e" */ 
   dbms_output.put_line ( LENGTH(v_saudacao)); 
END; 
DECLARE 
   v_saudacao varchar2(30) := '......Hello World.....'; 
BEGIN 
   dbms_output.put_line(RTRIM(v_saudacao,'.')); 
   dbms_output.put_line(LTRIM(v_saudacao, '.')); 
   dbms_output.put_line(TRIM( '.' from v_saudacao)); 
END; 
/
