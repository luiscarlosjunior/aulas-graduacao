-- Cria duas tabelas repetidas para poder usar a instrução de retirar 
-- do entidades do banco de dados
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
 
 -- ************************************************** ---
 -- Usando a instrução 'Alter' para manipular dados da Table e Coluna
 
 -- Adicionando uma nova coluna
 ALTER TABLE Depto2 
   ADD Descricao VARCHAR(50);
 
 SELECT * FROM Depto2;
 
 -- Removendo uma coluna------
 ALTER TABLE Depto2
 DROP COLUMN Descricao;
 
INSERT INTO Depto2 values(1, 'Presid�ncia', 'S�o Paulo',20000); 
INSERT INTO Depto2 values(10, 'F�brica', 'Santo Andr�',30000); 
 
 SELECT * FROM Depto2;
--------------------------

---- Adicionando Coluna com Valores
 ALTER TABLE Depto2 
 ADD Descricao VARCHAR(50);
 
 SELECT * FROM Depto2;

insert into Depto2 (nrdepto, nmdepto, dslocal, vlorcamento)
    values(4, 'Vice-Presidente', 'Sao Paulo', 20000); 

 SELECT * FROM Depto2;
 --------------------------------
 
 -- MODIFICAR COLUNA
 ALTER TABLE Depto2
 MODIFY Descricao DEFAULT 'Sem comentarios';
 
INSERT INTO Depto2 (nrdepto, nmdepto, dslocal, vlorcamento)
    VALUES(5, 'Secretario', 'Sao Paulo', 20000); 
 
 SELECT * FROM Depto2; 
 
 -- Alterar Nome da Tabela
 ALTER TABLE Depto2
 RENAME TO Departamento;
 -----------------------