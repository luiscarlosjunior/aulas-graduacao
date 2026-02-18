-- Um exemplo bem simples de base de dados escolar
CREATE TABLE Professor
  (
    ID    INTEGER,
    nome  VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL
  );
  
  ALTER TABLE Professor ADD (
  CONSTRAINT Professor_ID PRIMARY KEY (ID));

/*
CREATE SEQUENCE prof_seq START WITH 1;

CREATE OR REPLACE TRIGGER prof_bir 
BEFORE INSERT ON Professor 
FOR EACH ROW

BEGIN
  SELECT prof_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/
*/

CREATE TABLE Curso (
  ID INTEGER  NOT NULL ,
  nome VARCHAR2(50) NOT NULL,
  requisito VARCHAR2(255),
  carga_horaria INTEGER,
  PRIMARY KEY(ID)
);
/

CREATE TABLE alunos (
  ID INTEGER,
  cpf CHAR(11) NOT NULL,
  nome VARCHAR2(50) NOT NULL,
  email VARCHAR2(50) NOT NULL,
  fone CHAR(14) NOT NULL,
  data_nascimento DATE,
  CONSTRAINT Alunos_ID PRIMARY KEY(ID)
);

CREATE TABLE turma (
  ID INTEGER,
  instrutores_id INTEGER,
  cursos_id INTEGER,
  data_inicio DATE,
  data_final DATE,
  carga_horaria INTEGER,
  CONSTRAINT turmas_id PRIMARY KEY(ID)
);
  
CREATE TABLE matriculas (
  ID INTEGER,
  turmas_id INTEGER,
  alunos_id INTEGER NOT NULL,
  data_matricula DATE,
  CONSTRAINT matriculas_PK PRIMARY KEY(id)
);
