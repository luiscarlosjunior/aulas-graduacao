DECLARE
  V_CONTAR NUMBER(3);
  TURMA_CHEIA EXCEPTION;
BEGIN

  SELECT COUNT(RA) INTO V_CONTAR
    FROM ALUNO;
  
  IF V_CONTAR > 500 THEN
    RAISE TURMA_CHEIA;
  ELSE
    dbms_output.put_line('Turma está com ' || V_CONTAR || ' ALUNOS ');
  END IF;
  
EXCEPTION
  WHEN TURMA_CHEIA THEN
    dbms_output.put_line('A turma está cheia!');
END;