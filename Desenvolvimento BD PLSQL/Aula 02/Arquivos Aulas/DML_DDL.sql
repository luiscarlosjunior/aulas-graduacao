CREATE TABLE Depto 
 (
    NrDepto SMALLINT NOT NULL, 
    NmDepto CHAR(20) NOT NULL, 
    DsLocal VARCHAR(20) NOT NULL, 
    VlOrcamento DECIMAL(12) NULL, 
    CONSTRAINT PK_Depto PRIMARY KEY (NrDepto) 
 ); 

CREATE TABLE Empregado (
    NrEmpregado INTEGER NOT NULL, 
      NmEmpregado CHAR(20) NOT NULL, 
      NrGerente INTEGER NULL, 
      DsCargo VARCHAR(20) NULL, 
      NrDepto SMALLINT NOT NULL, 
      DtAdmissao DATE NOT NULL, 
      VlSalario DECIMAL(10,2) NULL, 
      VlComissao DECIMAL(10,2) NULL, 
      CONSTRAINT PK_Empregado PRIMARY KEY(NrEmpregado), 
      CONSTRAINT FK_Empregado_Depto FOREIGN KEY(NrDepto) 
      REFERENCES Depto(NrDepto) 
 ); 
 
insert into Depto values(1, 'Presidência', 'São Paulo',20000); 
insert into Depto values(10, 'Fábrica', 'Santo André',30000); 
insert into Depto values(20, 'RH', 'São Paulo',30000); 
insert into Depto values(30, 'Comercial', 'São Paulo',60000); 
insert into Depto values(40, 'Informática', 'Santo André',40000); 
insert into Depto values(50, 'Telemarketing', 'São Paulo',20000);

insert into Empregado values(100,'José Pereira ',null,'Presidente',01,'13/01/1998',20000,null); 
insert into Empregado values(101,'Ivete Sangalo',null,'Secretária',20,'20/01/1998',1500,null); 
insert into Empregado values(110,'Marcos Souza',null,'Gerente',10, '13/03/1995',2500,0); 
insert into Empregado values(111,'Romário',110,'Operário',10, '13/03/1995',900,800); 
insert into Empregado values(112,'Rivaldo',110,'Operário',10,'13/03/1995',900,1700); 
insert into Empregado values(113,'Roberto Souza',110,'Operário',10,'15/09/2001',900,600); 
insert into Empregado values(114,'Ronaldo Assis',110,'Operário',10,'28/11/1992',900,1800); 
insert into Empregado values(120,'Caetano Nascimento',null,'Gerente',20,'3/12/1993',3000,0); 
insert into Empregado values(121,'Marisa Bezzatra',120,'Secretária',20,'22/07/1997',700,null); 
insert into Empregado values(130,'Milton Hurtz',null,'Gerente',30,'21/08/1996',2000,500); 
insert into Empregado values(131,'Ana Paula Santos',130,'Vendedora',30,'17/06/1979',500,2200); 
insert into Empregado values(132,'Adriana Carla Souza',130,'Vendedora',30,'14/01/1985',500,2500); 
insert into Empregado values(133,'Regina Marta Hurtterff',130,'Vendedora',30,'1/08/1955',500,1500); 
insert into Empregado values(134,'Glória Von Pazzo',130,'Vendedora',30,'22/10/1988',500,1000); 
insert into Empregado values(140,'Gilberto Bilutti',null,'Gerente',40,'25/12/2001',3000,0); 
insert into Empregado values(141,'Marina Juckztin',140,'Secretária',40,'4/11/1990',700,null); 
insert into Empregado values(150,'Zeca Cardoso Souza',null,'Gerente',50,'20/07/2000',2000,0); 
insert into Empregado values(151,'Camila Oliveria',150,'Vendedora',50,'23/04/2005',600,1500); 
insert into Empregado values(152,'Deborah Barbára',150,'Vendedora',50,'19/03/2002',600,1000); 