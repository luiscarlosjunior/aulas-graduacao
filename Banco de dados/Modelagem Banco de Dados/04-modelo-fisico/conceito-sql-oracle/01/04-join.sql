SELECT * FROM Depto;
SELECT * FROM Empregado;

-- Clausula join é usada para combinar duas ou mais tabelas com as relações existentes esntre elas
SELECT * FROM DEPTO, EMPREGADO;

--SELECT NRDEPTO AS "NRDEPTO OF DEPTO", DSLOCAL FROM DEPTO, EMPREGADO;

SELECT DEPTO.NRDEPTO AS "NRDEPTO OF DEPTO", DSLOCAL FROM DEPTO, EMPREGADO;

SELECT D.NRDEPTO "NRDEPTO OF DEPTO", DSLOCAL FROM DEPTO D, EMPREGADO E;

--SELECT D.NRDEPTO, E.DSLOCAL FROM DEPTO D, EMPREGADO E
--    WHERE d.nrdepto = E.NRDEPTO;
/*
SELECT DISTINCT D.NRDEPTO, DSLOCAL FROM DEPTO D 
    INNER JOIN EMPREGADO E
    ON d.nrdepto = E.NRDEPTO;
    
SELECT D.NRDEPTO, DSLOCAL FROM DEPTO D 
    LEFT JOIN EMPREGADO E
    ON d.nrdepto = E.NRDEPTO;
    
SELECT D.NRDEPTO, DSLOCAL FROM DEPTO D 
    RIGHT JOIN EMPREGADO E
    ON d.nrdepto = E.NRDEPTO;
    
SELECT D.NRDEPTO, DSLOCAL FROM DEPTO D 
    FULL JOIN EMPREGADO E
    ON d.nrdepto = E.NRDEPTO;
*/

SELECT E.NmEmpregado, E.DsCargo, D.NmDepto 
FROM Empregado E, Depto D 
WHERE E.NrDepto = D.NrDepto; 

SELECT E.NmEmpregado, E.DsCargo, D.NmDepto 
FROM Empregado E INNER JOIN Depto D  
ON E.NrDepto = D.NrDepto; 

--Liste os Códigos de cada Funcionário, seus Nomes, seus Cargos e o 
--Nome do Gerente ao qual este se relaciona. 

--Precisamos criar um auto-relacionamento, ou seja, juntar uma tabela a ela própria. 
--É possível juntarmos uma tabela a ela mesma com a utilização de apelidos, 
--permitindo juntar tuplas da tabela a outra tuplas da mesma tabela. 

SELECT A.NrEmpregado, A.NmEmpregado, A.DsCargo, B.NmEmpregado 
FROM Empregado A, Empregado B 
WHERE A.NrGerente = B.NrEmpregado; 


SELECT * FROM Depto D LEFT OUTER JOIN Empregado E ON E.NrDepto=D.NrDepto;

SELECT * FROM Empregado E RIGHT OUTER JOIN Depto D on D.NrDepto=E.NrDepto; 


-- Exists
SELECT NmDepto 
    FROM Depto D 
    WHERE EXISTS ( 
    SELECT * 
 		FROM Empregado E 
 		WHERE (VlSalario + VlComissao) > 2500 AND E.NrDepto = D.NrDepto); 