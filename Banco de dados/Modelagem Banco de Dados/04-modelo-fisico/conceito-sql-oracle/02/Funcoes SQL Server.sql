-- SELECT * FROM Empregado

-- Apresente o nome de todos os empregrados em letras min�sculas
SELECT LOWER(NmEmpregado) 
	FROM Empregado

-- Apresente o nome de todos os empregrados em letras mai�scula
--SELECT UPPER(NmEmpregado)
--	FROM Empregado

--SELECT NmEmpregado, CHARINDEX('d', NmEmpregado)
--	FROM Empregado

SELECT UPPER(SUBSTRING(NmEmpregado, 0, 9)) as 'Peda�o do Nome do empregado'
	FROM Empregado

-- Procura o ind�ce do caracter espa�o em branco
SELECT CHARINDEX(' ', NmEmpregado)
	FROM Empregado

SELECT SUBSTRING(NmEmpregado, 0, CHARINDEX(' ', NmEmpregado)) as 'Primeiro nome'
	FROM Empregado;


SELECT NmEmpregado, CHARINDEX(' ', NmEmpregado) as 'Ind�ces encontrados' 
	FROM Empregado