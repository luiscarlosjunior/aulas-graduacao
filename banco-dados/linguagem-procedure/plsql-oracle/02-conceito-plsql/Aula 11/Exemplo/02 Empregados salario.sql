SET SERVEROUTPUT ON;

CREATE TABLE EMPREGADOS (
  ID NUMBER NOT NULL,
  NOME VARCHAR2(50) NOT NULL,
  IDADE NUMBER(2) NOT NULL,
  SALARIO NUMERIC (7,2)
);

INSERT INTO EMPREGADOS (ID, NOME, IDADE, SALARIO)
VALUES (1, 'SANTOS', 25, 4000.00);
--        (2, 'Carlos', 25, 4000.00),
--        (3, 'Carlos', 25, 4000.00),
--        (4, 'Carlos', 25, 4000.00);

CREATE OR REPLACE TRIGGER display_salary_changes 
BEFORE DELETE OR INSERT OR UPDATE ON EMPREGADOS 
FOR EACH ROW 
WHEN (NEW.ID > 0) 
DECLARE 
   sal_diff number; 
BEGIN 
   sal_diff := :NEW.SALARIO  - :OLD.SALARIO; 
   dbms_output.put_line('Salario antigo: ' || :OLD.SALARIO); 
   dbms_output.put_line('Salario novo: ' || :NEW.SALARIO); 
   dbms_output.put_line('Diferença entre os salarios: ' || sal_diff); 
END; 
/ 

select * from empregados