/* Lógico_1: */

CREATE TABLE Departamento (
    Numero INT PRIMARY KEY,
    Nome varchar(50),
    fk_Localizacao_Localizacao_PK INT,
    fk_Projeto_Numero INT,
    fk_Funcionario_ID INT
);
GO
CREATE TABLE Projeto (
    Numero INT PRIMARY KEY,
    Nome varchar(50),
    Localizacao varchar(50)
);
GO
CREATE TABLE Funcionario (
    Nome varchar(50),
    Logradouro varchar(50),
    Cidade varchar(50),
    Bairro varchar(50),
    Salario varchar(50),
    ID INT PRIMARY KEY,
    CPF char(11),
    fk_Departamento_Numero INT,
    Data_Inicio DATE
);
GO
CREATE TABLE Dependente (
    Sexo CHAR(1),
    Relacao Varchar(50),
    Nome varchar(50)
);
GO
CREATE TABLE Localizacao (
    Localizacao_PK INT NOT NULL PRIMARY KEY,
    Localizacao varchar(50)
);
GO
CREATE TABLE Supervisiona (
    fk_Funcionario_ID INT,
    fk_Funcionario_ID_ INT
);
GO
CREATE TABLE Trabalha_em (
    fk_Funcionario_ID INT,
    fk_Projeto_Numero INT,
    Horas DATE
);
 GO
ALTER TABLE Departamento ADD CONSTRAINT FK_Departamento_2
    FOREIGN KEY (fk_Localizacao_Localizacao_PK)
    REFERENCES Localizacao (Localizacao_PK)
    ON DELETE NO ACTION;
 GO
ALTER TABLE Departamento ADD CONSTRAINT FK_Departamento_3
    FOREIGN KEY (fk_Projeto_Numero)
    REFERENCES Projeto (Numero)
    ON DELETE CASCADE;
 GO
ALTER TABLE Departamento ADD CONSTRAINT FK_Departamento_4
    FOREIGN KEY (fk_Funcionario_ID)
    REFERENCES Funcionario (ID)
    ON DELETE CASCADE;
 GO
ALTER TABLE Funcionario ADD CONSTRAINT FK_Funcionario_2
    FOREIGN KEY (fk_Departamento_Numero)
    REFERENCES Departamento (Numero)
    ON DELETE NO ACTION;
 GO
ALTER TABLE Supervisiona ADD CONSTRAINT FK_Supervisiona_1
    FOREIGN KEY (fk_Funcionario_ID)
    REFERENCES Funcionario (ID)
    ON DELETE NO ACTION;
 GO
ALTER TABLE Supervisiona ADD CONSTRAINT FK_Supervisiona_2
    FOREIGN KEY (fk_Funcionario_ID_)
    REFERENCES Funcionario (ID)
    ON DELETE NO ACTION;
 GO
ALTER TABLE Trabalha_em ADD CONSTRAINT FK_Trabalha_em_1
    FOREIGN KEY (fk_Funcionario_ID)
    REFERENCES Funcionario (ID)
    ON DELETE NO ACTION;
 GO
ALTER TABLE Trabalha_em ADD CONSTRAINT FK_Trabalha_em_2
    FOREIGN KEY (fk_Projeto_Numero)
    REFERENCES Projeto (Numero)
    ON DELETE NO ACTION;