-- Cria um objeto do tipo sequence para poder contralar a 
CREATE SEQUENCE pk_id_aluno START WITH 310;

CREATE TRIGGER trg_sequencia_id_aluno 
  BEFORE INSERT ON ALUNO
  FOR EACH ROW
BEGIN
  SELECT pk_id_aluno.nextval INTO :new.id FROM DUAL;
END;

SELECT COUNT(*) FROM ALUNO;
