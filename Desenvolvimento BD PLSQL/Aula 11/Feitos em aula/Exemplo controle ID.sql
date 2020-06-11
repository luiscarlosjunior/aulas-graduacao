CREATE TABLE LIVROS (
  ID  NUMBER NOT NULL,
  TITULO VARCHAR2(100) NOT NULL
);

-- Altero a tabela livro
ALTER TABLE LIVROS 
  ADD ( CONSTRAINT livros_pk PRIMARY KEY (ID) );

CREATE SEQUENCE livros_sequencia;

-- Criar a trigger

CREATE OR REPLACE TRIGGER trg_sequencia_id_livro
  BEFORE INSERT ON LIVROS
  FOR EACH ROW
BEGIN
  SELECT livros_sequencia.nextval
    INTO :new.id
  FROM DUAL;  
END;

select * from livros

INSERT INTO LIVROS (titulo) VALUES ('Sherlock Holmes');
INSERT INTO LIVROS (titulo) VALUES ('Senhor dos Aneis');
INSERT INTO LIVROS (titulo) VALUES ('Biblia');