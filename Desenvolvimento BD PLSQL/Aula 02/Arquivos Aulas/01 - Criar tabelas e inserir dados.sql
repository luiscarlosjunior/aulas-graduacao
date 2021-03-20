-- Criando a tabela para o departamento
CREATE TABLE Depto 
 (
    NrDepto SMALLINT NOT NULL, 
    NmDepto CHAR(20) NOT NULL, 
    DsLocal VARCHAR(20) NOT NULL, 
    VlOrcamento DECIMAL(12) NULL, 
    -- Criando uma restrição do tipo chave primária para o NrDepto
    CONSTRAINT PK_Depto PRIMARY KEY (NrDepto) 
 ); 

-- Criando a tabela empregado
CREATE TABLE Empregado (
    NrEmpregado INTEGER NOT NULL, 
      NmEmpregado CHAR(20) NOT NULL, 
      NrGerente INTEGER NULL, 
      DsCargo VARCHAR(20) NULL, 
      NrDepto SMALLINT NOT NULL, 
      DtAdmissao DATE NOT NULL, 
      VlSalario DECIMAL(10,2) NULL, 
      VlComissao DECIMAL(10,2) NULL,
      -- Adicionando umarestrição do tipo chave primária  
      CONSTRAINT PK_Empregado PRIMARY KEY(NrEmpregado), 
      -- Adicionando uma restrição do tipo chave estrangeira
      CONSTRAINT FK_Empregado_Depto FOREIGN KEY(NrDepto) 
      REFERENCES Depto(NrDepto) 
 ); 
 
-- Inserindo dados departamento
INSERT INTO Depto VALUES(10, 'Fabrica', 'Santo Andre',30000); 
INSERT INTO Depto VALUES(1, 'Presidencia', 'Sao Paulo',20000); 
INSERT INTO Depto VALUES(20, 'RH', 'Sao Paulo',30000); 
INSERT INTO Depto VALUES(30, 'Comercial', 'Sao Paulo',60000); 
INSERT INTO Depto VALUES(40, 'Informatica', 'Santo Andre',40000); 
INSERT INTO Depto VALUES(50, 'Telemarketing', 'Sao Paulo',20000);

INSERT INTO Empregado VALUES(100,'Jose Pereira ',null,'Presidente',01,TO_DATE('13/01/1998', 'dd/mm/yyyy'),20000,null); 
INSERT INTO Empregado VALUES(101,'Ivete Sangalo',null,'Secretaria',20,TO_DATE('13/05/1978', 'dd/mm/yyyy'),1500,null); 
INSERT INTO Empregado VALUES(110,'Marcos Souza',null,'Gerente',10, TO_DATE('13/03/1995', 'dd/mm/yyyy'),2500,0); 
INSERT INTO Empregado VALUES(111,'Romario Nazario',110,'Operario',10, TO_DATE('23/12/1995', 'dd/mm/yyyy'),900,800); 
INSERT INTO Empregado VALUES(112,'Rivaldo',110,'Operario',10,TO_DATE('13/07/1993', 'dd/mm/yyyy'),900,1700); 
INSERT INTO Empregado VALUES(113,'Roberto Souza',110,'Operario',10, TO_DATE('15/09/2001', 'dd/mm/yyyy'),900,600); 
INSERT INTO Empregado VALUES(114,'Ronaldo Assis',110,'Operario',10,TO_DATE('28/11/1992', 'dd/mm/yyyy'),900,1800); 
INSERT INTO Empregado VALUES(120,'Caetano Nascimento',null,'Gerente',20,TO_DATE('3/12/1993', 'dd/mm/yyyy'),3000,0); 
INSERT INTO Empregado VALUES(121,'Marisa Bezzatra',120,'Secretaria',20,TO_DATE('22/07/1997', 'dd/mm/yyyy'),700,null); 
INSERT INTO Empregado VALUES(130,'Milton Hurtz',null,'Gerente',30,TO_DATE('21/08/1996', 'dd/mm/yyyy'),2000,500); 
INSERT INTO Empregado VALUES(131,'Ana Paula Santos',130,'Vendedora',30,TO_DATE('17/06/1979', 'dd/mm/yyyy'),500,2200); 
INSERT INTO Empregado VALUES(132,'Adriana Carla Souza',130,'Vendedora',30,TO_DATE('14/01/1985', 'dd/mm/yyyy'),500,2500); 
INSERT INTO Empregado VALUES(133,'Regina Marta Hurtterff',130,'Vendedora',30,TO_DATE('1/08/1955', 'dd/mm/yyyy'),500,1500); 
INSERT INTO Empregado VALUES(134,'Gloria Von Pazzo',130,'Vendedora',30,TO_DATE('22/10/1988', 'dd/mm/yyyy'),500,1000); 
INSERT INTO Empregado VALUES(140,'Gilberto Bilutti',null,'Gerente',40,TO_DATE('25/12/2001', 'dd/mm/yyyy'),3000,0); 
INSERT INTO Empregado VALUES(141,'Marina Juckztin',140,'Secretaria',40,TO_DATE('4/11/1990', 'dd/mm/yyyy'),2500,null); 
INSERT INTO Empregado VALUES(150,'Zeca Cardoso Souza',null,'Gerente',50,TO_DATE('20/07/2000', 'dd/mm/yyyy'),2000,0); 
INSERT INTO Empregado VALUES(151,'Camila Oliveria',150,'Vendedora',50,TO_DATE('23/04/2005', 'dd/mm/yyyy'),600,1500); 
INSERT INTO Empregado VALUES(152,'Deborah Barbara',150,'Vendedora',50,TO_DATE('19/03/2002', 'dd/mm/yyyy'),1200,1000);

SELECT * FROM DEPTO;
SELECT * FROM EMPREGADO;