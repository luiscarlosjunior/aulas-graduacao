-- SELECT * FROM Empregado

-- Apresente o nome de todos os empregrados em letras minúsculas
SELECT LOWER(NmEmpregado) 
	FROM Empregado

-- Apresente o nome de todos os empregrados em letras maiúscula
--SELECT UPPER(NmEmpregado)
--	FROM Empregado

--SELECT NmEmpregado, CHARINDEX('d', NmEmpregado)
--	FROM Empregado

SELECT UPPER(SUBSTRING(NmEmpregado, 0, 9)) as 'Pedaço do Nome do empregado'
	FROM Empregado

-- Procura o indíce do caracter espaço em branco
SELECT CHARINDEX(' ', NmEmpregado)
	FROM Empregado

SELECT SUBSTRING(NmEmpregado, 0, CHARINDEX(' ', NmEmpregado)) as 'Primeiro nome'
	FROM Empregado;


SELECT NmEmpregado, CHARINDEX(' ', NmEmpregado) as 'Indíces encontrados' 
	FROM Empregado