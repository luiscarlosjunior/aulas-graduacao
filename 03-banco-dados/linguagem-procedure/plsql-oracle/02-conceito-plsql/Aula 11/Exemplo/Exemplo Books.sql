-- Tabela de livros
CREATE TABLE livros (
  id      NUMBER(10)    NOT NULL,
  titulo   VARCHAR2(100) NOT NULL
);

-- Cria uma restrição de PK para primary key
ALTER TABLE livros
  ADD (
    CONSTRAINT livros_pk PRIMARY KEY (id)
  );

-- Cria um comando sequence para sequencia
CREATE SEQUENCE livros_sequencia;

-- No nosso caso, vamos utilizar um trigger para ir atualizando 
CREATE OR REPLACE TRIGGER livros_on_insert
  BEFORE INSERT ON livros
  FOR EACH ROW
BEGIN
  SELECT livros_sequence.nextval
  INTO :new.id
  FROM dual;
END;

INSERT INTO livros (titulo) VALUES ('Sherlock Holmes');

select * from livros;
