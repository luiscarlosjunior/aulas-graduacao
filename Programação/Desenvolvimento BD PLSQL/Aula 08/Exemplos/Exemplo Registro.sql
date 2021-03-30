SET SERVEROUTPUT ON;

DECLARE
  TYPE record_aluno IS RECORD (
    registro CHAR (15),
    nome VARCHAR2(50)
  );

  uso_aluno record_aluno;
BEGIN
  
  uso_aluno.registro := '987654321';
  uso_aluno.nome := 'Isabella';
  
  dbms_output.put_line(uso_aluno.nome);

END;
/
SET SERVEROUTPUT ON;

DECLARE
  TYPE record_aluno IS RECORD (
    registro CHAR (15),
    nome VARCHAR2(50)
  );
  
  uso_aluno record_aluno;
BEGIN
  SELECT RA, NOME
    INTO uso_aluno.registro, uso_aluno.nome
  FROM ALUNO
  WHERE RA = '71033266097    ';
  
  dbms_output.put_line(uso_aluno.nome);

END;
/

SET SERVEROUTPUT ON;

DECLARE
  TYPE record_aluno IS RECORD (
    registro aluno.RA%TYPE,
    nome aluno.nome%TYPE
  );
  
  uso_aluno record_aluno;
BEGIN
  SELECT RA, NOME
    INTO uso_aluno.registro, uso_aluno.nome
  FROM ALUNO
  WHERE RA = '71033266097    ';
  
  dbms_output.put_line(uso_aluno.nome);

END;
/

SET SERVEROUTPUT ON;

DECLARE
  TYPE record_aluno IS RECORD (
    registro aluno.RA%TYPE,
    nome aluno.nome%TYPE
  );
  
  uso_aluno record_aluno;
BEGIN
  SELECT RA, NOME
    INTO uso_aluno.registro, uso_aluno.nome
  FROM ALUNO
  WHERE RA = '71033266097    ';
  
  dbms_output.put_line(uso_aluno.nome);

END;
/