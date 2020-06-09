-- Tabela de livros
CREATE TABLE books (
  id      NUMBER(10)    NOT NULL,
  title   VARCHAR2(100) NOT NULL
);

-- Cria uma restrição de PK para primary key
ALTER TABLE books
  ADD (
    CONSTRAINT books_pk PRIMARY KEY (id)
  );

-- Cria um comando sequence para sequencia
CREATE SEQUENCE books_sequence;

-- No nosso caso, vamos utilizar um trigger para ir atualizando 
CREATE OR REPLACE TRIGGER books_on_insert
  BEFORE INSERT ON books
  FOR EACH ROW
BEGIN
  SELECT books_sequence.nextval
  INTO :new.id
  FROM dual;
END;

INSERT INTO books (title) VALUES ('Sherlock Holmes');

select * from books;
