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
 
insert into Depto values(1, 'Presidencia', 'Sao Paulo',20000); 
insert into Depto values(10, 'Fabrica', 'Santo Andre',30000); 
insert into Depto values(20, 'RH', 'Sao Paulo',30000); 
insert into Depto values(30, 'Comercial', 'Sao Paulo',60000); 
insert into Depto values(40, 'Informatica', 'Santo Andre',40000); 
insert into Depto values(50, 'Telemarketing', 'São Paulo',20000);

insert into Empregado values(100,'Jose Pereira ',null,'Presidente',01,TO_DATE('13/01/1998', 'dd/mm/yyyy'),20000,null); 
insert into Empregado values(101,'Ivete Sangalo',null,'Secretaria',20,TO_DATE('13/05/1978', 'dd/mm/yyyy'),1500,null); 
insert into Empregado values(110,'Marcos Souza',null,'Gerente',10, TO_DATE('13/03/1995', 'dd/mm/yyyy'),2500,0); 
insert into Empregado values(111,'Romario Nazario',110,'Operario',10, TO_DATE('23/12/1995', 'dd/mm/yyyy'),900,800); 
insert into Empregado values(112,'Rivaldo',110,'Operario',10,TO_DATE('13/07/1993', 'dd/mm/yyyy'),900,1700); 
insert into Empregado values(113,'Roberto Souza',110,'Operario',10, TO_DATE('15/09/2001', 'dd/mm/yyyy'),900,600); 
insert into Empregado values(114,'Ronaldo Assis',110,'Operario',10,TO_DATE('28/11/1992', 'dd/mm/yyyy'),900,1800); 
insert into Empregado values(120,'Caetano Nascimento',null,'Gerente',20,TO_DATE('3/12/1993', 'dd/mm/yyyy'),3000,0); 
insert into Empregado values(121,'Marisa Bezzatra',120,'Secretária',20,TO_DATE('22/07/1997', 'dd/mm/yyyy'),700,null); 
insert into Empregado values(130,'Milton Hurtz',null,'Gerente',30,TO_DATE('21/08/1996', 'dd/mm/yyyy'),2000,500); 
insert into Empregado values(131,'Ana Paula Santos',130,'Vendedora',30,TO_DATE('17/06/1979', 'dd/mm/yyyy'),500,2200); 
insert into Empregado values(132,'Adriana Carla Souza',130,'Vendedora',30,TO_DATE('14/01/1985', 'dd/mm/yyyy'),500,2500); 
insert into Empregado values(133,'Regina Marta Hurtterff',130,'Vendedora',30,TO_DATE('1/08/1955', 'dd/mm/yyyy'),500,1500); 
insert into Empregado values(134,'Glória Von Pazzo',130,'Vendedora',30,TO_DATE('22/10/1988', 'dd/mm/yyyy'),500,1000); 
insert into Empregado values(140,'Gilberto Bilutti',null,'Gerente',40,TO_DATE('25/12/2001', 'dd/mm/yyyy'),3000,0); 
insert into Empregado values(141,'Marina Juckztin',140,'Secretária',40,TO_DATE('4/11/1990', 'dd/mm/yyyy'),2500,null); 
insert into Empregado values(150,'Zeca Cardoso Souza',null,'Gerente',50,TO_DATE('20/07/2000', 'dd/mm/yyyy'),2000,0); 
insert into Empregado values(151,'Camila Oliveria',150,'Vendedora',50,TO_DATE('23/04/2005', 'dd/mm/yyyy'),600,1500); 
insert into Empregado values(152,'Deborah Barbára',150,'Vendedora',50,TO_DATE('19/03/2002', 'dd/mm/yyyy'),1200,1000); 