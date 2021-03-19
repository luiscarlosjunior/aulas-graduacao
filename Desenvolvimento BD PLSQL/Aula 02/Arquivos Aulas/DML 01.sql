-- Executar para ver o error dos nomes
/*
CREATE TABLE Depto2 
 (
    NrDepto SMALLINT NOT NULL, 
    NmDepto CHAR(20) NOT NULL, 
    DsLocal VARCHAR(20) NOT NULL, 
    VlOrcamento DECIMAL(12) NULL, 
    CONSTRAINT PK_Depto PRIMARY KEY (NrDepto) 
 );
 */ 

-- Criar tabela repetida
CREATE TABLE Depto2 
 (
    NrDepto SMALLINT NOT NULL, 
    NmDepto CHAR(20) NOT NULL, 
    DsLocal VARCHAR(20) NOT NULL, 
    VlOrcamento DECIMAL(12) NULL, 
    CONSTRAINT PK_Depto2 PRIMARY KEY (NrDepto) 
 );
 
CREATE TABLE Empregado2 (
    NrEmpregado INTEGER NOT NULL, 
      NmEmpregado CHAR(20) NOT NULL, 
      NrGerente INTEGER NULL, 
      DsCargo VARCHAR(20) NULL, 
      NrDepto SMALLINT NOT NULL, 
      DtAdmissao DATE NOT NULL, 
      VlSalario DECIMAL(10,2) NULL, 
      VlComissao DECIMAL(10,2) NULL, 
      CONSTRAINT PK_Empregado2 PRIMARY KEY(NrEmpregado), 
      CONSTRAINT FK_Empregado_Depto2 FOREIGN KEY(NrDepto) 
      REFERENCES Depto(NrDepto) 
 );
 
 
 -- Usar o Alter Table Coluna/Tabela
 
 -- Add uma nova coluna
 ALTER TABLE Depto2 
 ADD Descricao VARCHAR(50);
 
 SELECT * FROM Depto2;
 
 -- Remover coluna------
 ALTER TABLE Depto2
 DROP COLUMN Descricao;
 
insert into Depto2 values(1, 'Presidência', 'São Paulo',20000); 
insert into Depto2 values(10, 'Fábrica', 'Santo André',30000); 
 
 SELECT * FROM Depto2;
--------------------------

---- Add Coluna com Valores
 ALTER TABLE Depto2 
 ADD Descricao VARCHAR(50);
 
 SELECT * FROM Depto2;

--insert into Depto2 values(3, 'Vice-Presidente', 'São Paulo', 20000); 
insert into Depto2 (nrdepto, nmdepto, dslocal, vlorcamento)
    values(4, 'Vice-Presidente', 'São Paulo', 20000); 

 SELECT * FROM Depto2;
 --------------------------------
 
 -- MODIFICAR COLUNA
 ALTER TABLE Depto2
 MODIFY Descricao DEFAULT 'Sem comentários';
 
insert into Depto2 (nrdepto, nmdepto, dslocal, vlorcamento)
    values(5, 'Secretário', 'São Paulo', 20000); 
 
 SELECT * FROM Depto2; 
 ----------------------------
 
 --ALTER TABLE Depto2
 --MODIFY Descricao Not null;
 
 
 -- Alterar Nome Tabela
 ALTER TABLE Depto2
 RENAME TO Departamento;
 -----------------------