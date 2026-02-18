SET SERVEROUTPUT ON

DECLARE
    TYPE t_name_type IS VARRAY(2) 
        OF VARCHAR2(20) NOT NULL;
    t_names t_name_type  := t_name_type('John','Jane');
    t_enames t_name_type := t_name_type();
BEGIN
    -- initialize to an empty array
    dbms_output.put_line('Numero de elementos t_enames ' || t_enames.COUNT);
    
    -- initialize to an array of a elements 
    dbms_output.put_line('Numero de elementos t_names ' || t_names.COUNT);
END;
/

DECLARE
  type idade_alunos IS VARRAY(5) OF NUMBER(2);
  idade_varray idade_alunos; 
BEGIN
  
  idade_varray := idade_alunos(23,22,25,10,36);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
  
  dbms_output.put_line('Quantidade ' || idade_varray.COUNT);
  
END;

DECLARE
  type exemplo_varray IS VARRAY(5) OF NUMBER(3);
  idade_varray exemplo_varray; 
BEGIN
  
  idade_varray := exemplo_varray(1, 2, 3, 4, 5);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
END;
/
DECLARE
  type exemplo_varray IS VARRAY(5) OF ALUNO_GRADUACAO.ID%type;
  idade_varray exemplo_varray; 
BEGIN
  
  idade_varray := exemplo_varray(1, 2, 3, 4, 5);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
END;

DECLARE
  type exemplo_varray IS VARRAY(5) OF aluno_graduacao.primeironome%type;
  idade_varray exemplo_varray; 
BEGIN
  
  idade_varray := exemplo_varray(1, 2, 3, 4, 5);
  
  SELECT PRIMEIRONOME INTO 
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
END;


DECLARE
  type idade_alunos IS VARRAY(5) OF NUMBER(2);
  idade_varray idade_alunos; 
BEGIN
  
  idade_varray(0) idade_alunos := idade_alunos(23);--idade_alunos(23,22,25,10,36);
  idade_varray(1) idade_alunos := idade_alunos(23);--idade_alunos(23,22,25,10,36);
  idade_varray(2) idade_alunos := idade_alunos(23);--idade_alunos(23,22,25,10,36);
  idade_varray(3) idade_alunos := idade_alunos(23);--idade_alunos(23,22,25,10,36);
  idade_varray(4) idade_alunos := idade_alunos(23);--idade_alunos(23,22,25,10,36);
  
  FOR i IN 1..idade_varray.count LOOP
    dbms_output.put_line('Números: ' || idade_varray(i));
  END LOOP;
  
  dbms_output.put_line('Quantidade ' || idade_varray.COUNT);
  
END;
