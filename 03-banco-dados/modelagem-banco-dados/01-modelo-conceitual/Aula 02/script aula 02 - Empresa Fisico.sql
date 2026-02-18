-- Esse comando é executado no SGBD SQL SERVER da MICROSOFT
-- Criando o banco de dados
CREATE DATABASE EMPRESA
-- Forçando o uso do banco de dados criado
USE [EMPRESA]
GO
-- Criando a tabela Funcionário
CREATE TABLE [dbo].[FUNCIONARIO]
(
	[ID] [smallint] NOT NULL,
	[Nome] VARCHAR(100) NULL,
	[CPF] CHAR(11) NULL,
	[Logradouro] VARCHAR(100) NULL,
	[Bairro] VARCHAR(50) NULL,
	[Cidade] VARCHAR(50) NULL,
	[CEP] VARCHAR(8) NULL,
	[UF] CHAR(2) NULL,
	[Cargo] VARCHAR(50) NULL,
	CONSTRAINT PK_FUNCIONARIO 
	PRIMARY KEY(ID)
);
GO
-- Criando a tabela Dependente
CREATE TABLE [dbo].[DEPENDENTE](
	ID SMALLINT NOT NULL,
	[IDFunc] smallint NULL,
	[Nome] VARCHAR(50) NULL,
	[CPF] CHAR(11) NULL,
	CONSTRAINT PK_DEPENDENTE
	PRIMARY KEY (ID)
) 
GO
-- Criando a tabela conjuge
CREATE TABLE [dbo].[CONJUGE](
	[ID] [smallint] NOT NULL,
	[IDFunc] [smallint] NULL,
	[Nome] VARCHAR(50) NULL,
	[CPF] CHAR(11) NULL,
	CONSTRAINT PK_CONJUGE
	PRIMARY KEY (ID)
)
GO

-- O comando alter table é usado para realizar alguma alteração na tabela
-- As linha a seguir faz com que o campo IDFunc da tabela conjuge crie uma relação com o 
-- campo ID da tabela funcionário (Chave estrangeira)
ALTER TABLE [dbo].[CONJUGE]  WITH CHECK ADD  CONSTRAINT [FK_FUNCIONARIO] FOREIGN KEY([IDFunc])
REFERENCES [dbo].[FUNCIONARIO] ([ID]) ON DELETE CASCADE;
GO

-- As linha a seguir faz com que o campo IDFunc da tabela Dependente crie uma relação com o 
-- campo ID da tabela funcionário (Chave estrangeira)
ALTER TABLE DEPENDENTE WITH CHECK ADD CONSTRAINT FK_FUNC_DEP FOREIGN KEY (IDFunc)
REFERENCES FUNCIONARIO (ID) ON DELETE CASCADE;
GO

-- Inseri itens nas tabelas (Será explicado mais a frente)
INSERT INTO [dbo].[FUNCIONARIO] ([ID],[Nome],[CPF],[Logradouro],[Bairro],[Cidade],
	[CEP],[UF],[Cargo])
     VALUES
	 (1,'José Aldo','13246579822','Rua das Dores','Bela Vista','São Paulo','12346578','SP','Analista'),
	 (2,'Ana Beatriz','98765432111','Av. Margem Rio','Luzia','Santo André','03625148','SP','Analista'),
	 (3,'Amanda Cunha','36985214777','Av. Goias','Centro','São Caetano do Sul','69325418','SP','Gerente'),
	 (4,'Renata Trovanni','65498712355','Travessa dos padres','Vergueiro','São Paulo','69352141','SP','Engenheira'),
	 (5,'Carlos Trojzan','46827319584','Rua Vergueiro','Vergueiro','São Paulo','00013052','SP','Analista'),
	 (6,'Bruna Bernotti','45612398711','Rua Cerqueira Santos','Liberdade','São Paulo','00010030','SP','Gerente'),
	 (7,'Renan Hercules','85214796321','Rua Galvão Bueno','Liberdade','São Paulo','00010231','SP','Analista'),
	 (8,'Célia Merlina','36521478963','Rua dos Santos Abreus','Lapa','São Paulo','02365996','SP','Analista')
GO
INSERT INTO [dbo].[DEPENDENTE]([ID],[IDFunc],[Nome],[CPF])
     VALUES(1,1,'Roberta Gusmão','12345678922'),
		   (2,1,'Felipe Carlos','66666555544'),
		   (3,3,'Thiago Oliveira','78979879855'),
		   (4,4,'Walter Fuccati','99966633215'),
		   (5,6,'Camila Fruzgtus','22266633589'),
		   (6,4,'Luiza Helbert','96325874747')
GO
INSERT INTO [dbo].[CONJUGE] ([ID],[IDFunc],[Nome],[CPF])
     VALUES (1,4,'Xavier Hunter','88552237736'),
		   (2,1,'Carla Junqueira','34652235536'),
		   (3,5,'Zomália Truncatti','78532230036')

-- Apresenta todos os dados das tabelas
SELECT * FROM FUNCIONARIO;
SELECT * FROM DEPENDENTE;
SELECT * FROM CONJUGE;

-- Realiza a junção entre o Funcionário e seu dependente
SELECT F.ID, F.Nome, D.Nome 'Nome dependente'
	FROM FUNCIONARIO F RIGHT JOIN DEPENDENTE D
	ON F.ID = D.IDFunc;

-- Realizar a junção entre o funcionário e seu conjuge
SELECT F.ID, F.Nome, C.Nome 'Nome Conjuge'
	FROM FUNCIONARIO F RIGHT JOIN CONJUGE C
	ON F.ID = C.IDFunc;
