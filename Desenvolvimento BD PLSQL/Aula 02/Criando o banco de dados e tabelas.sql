-- Cria o banco de dados
CREATE DATABASE AulaTeste
GO
-- Força o uso do banco de dados AulaTeste
USE AulaTeste
GO
CREATE TABLE Depto 
 (NrDepto SMALLINT NOT NULL, 
  NmDepto CHAR(20) NOT NULL, 
  DsLocal VARCHAR(20) NOT NULL, 
  VlOrcamento DECIMAL(12) NULL, 
  CONSTRAINT PK_Depto PRIMARY KEY (NrDepto) 
 ); 
 GO
 CREATE TABLE Empregado 
 (NrEmpregado INTEGER NOT NULL, 
  NmEmpregado CHAR(20) NOT NULL, 
  NrGerente INTEGER NULL, 
  DsCargo VARCHAR(20) NULL, 
  NrDepto SMALLINT NOT NULL, 
  DtAdmissao DATETIME NOT NULL, 
  VlSalario DECIMAL(10,2) NULL, 
  VlComissao DECIMAL(10,2) NULL, 
  CONSTRAINT PK_Empregado PRIMARY KEY(NrEmpregado), 
  CONSTRAINT FK_Empregado_Depto FOREIGN KEY(NrDepto) 
  REFERENCES Depto(NrDepto) 
 ); 
 GO
-- Inserir dados no departamento
INSERT INTO Depto values(1, 'Presidência', 'São Paulo',20000); 
insert into Depto values(10, 'Fábrica', 'Santo André',30000); 
insert into Depto values(20, 'RH', 'São Paulo',30000); 
insert into Depto values(30, 'Comercial', 'São Paulo',60000); 
insert into Depto values(40, 'Informática', 'Santo André',40000); 
insert into Depto values(50, 'Telemarketing', 'São Paulo',20000); 

-- Inserir dados 
insert into Empregado values(100,'Francisco Alves',null,'Presidente',01,'13-01-1998',20000,null) 
insert into Empregado values(101,'Ivete Sangalo',null,'Secretária',20,'20-01-1998',1500,null) 
insert into Empregado values(110,'Chico Buarque',null,'Gerente',10, '13-01-1998',2500,0) 
insert into Empregado values(111,'Romário',110,'Operário',10,'13-01-1998',900,800) 
insert into Empregado values(112,'Rivaldo',110,'Operário',10,'13-01-1998',900,1700) 
insert into Empregado values(113,'Dida',110,'Operário',10,'23-01-2001',900,600) 
insert into Empregado values(114,'Ronaldinho',110,'Operário',10,'23-01-2001',900,1800) 
insert into Empregado values(120,'Caetano Velozo',null,'Gerente',20,'23-01-2001',3000,0) 
insert into Empregado values(121,'Marisa Monte',120,'Secretária',20,'23-01-2000',700,null) 
insert into Empregado values(130,'Barlos Tristao',null,'Gerente',30,'23-01-2000',2000,500) 
insert into Empregado values(131,'Ana Paula Arósio',130,'Vendedora',30,'23-01-2002',500,2200) 
insert into Empregado values(132,'Adriana Esteves',130,'Vendedora',30,'23-01-2002',500,2500) 
insert into Empregado values(133,'Regina Duarte',130,'Vendedora',30,'23-01-2002',500,1500) 
insert into Empregado values(134,'Glória Pires',130,'Vendedora',30,'23-01-2002',500,1000) 
insert into Empregado values(140,'Gilberto Gil',null,'Gerente',40,'23-01-2001',3000,0) 
insert into Empregado values(141,'Marina Lima',140,'Secretária',40,'23-01-2001',700,null) 
insert into Empregado values(150,'Zé Ramalho',null,'Gerente',50,'23-01-2001',2000,0) 
insert into Empregado values(151,'Camila Pitanga',150,'Vendedora',50,'23-01-2002',600,1500) 
insert into Empregado values(152,'Deborah Secco',150,'Vendedora',50,'23-01-2002',600,1000) 