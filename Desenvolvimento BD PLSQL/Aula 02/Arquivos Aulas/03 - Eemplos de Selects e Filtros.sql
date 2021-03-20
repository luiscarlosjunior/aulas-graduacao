SELECT * FROM Depto;
SELECT * FROM Empregado;

-- Cláusula Distinct serve para trazer todos os valores únicos
SELECT DISTINCT dslocal FROM Depto;
SELECT DISTINCT dscargo FROM Empregado;

-- Podemos pegar determinadas tabelas
SELECT vlorcamento FROM Depto;
SELECT nrempregado, dscargo FROM Empregado;

---- SELEÇÃO COM FILTROS --------
SELECT * FROM EMPREGADO
    WHERE NRDEPTO > 1;

SELECT * FROM EMPREGADO
    WHERE NRDEPTO >= 1;

SELECT * FROM EMPREGADO
    WHERE NRDEPTO <> 1;

SELECT * FROM EMPREGADO
    WHERE NRDEPTO != 1;

SELECT * FROM EMPREGADO
    WHERE NRDEPTO = 1;
------------------------------------

-- SELEÇÃO COM FILTROS (MAIS DE UMA CONDIÇÃO)
SELECT * FROM EMPREGADO
    WHERE vlsalario > 1000 and vlcomissao <= 1000;

SELECT * FROM depto
    WHERE dslocal = 'São Paulo' or vlorcamento < 10000;

SELECT * FROM depto
    WHERE not (dslocal = 'São Paulo' or vlorcamento < 10000);

SELECT * FROM depto
    WHERE not DSLOCAL = 'São Paulo';
---------------------------------------------------

-- Uso do order by ---------------------------------

SELECT * FROM depto;
SELECT * FROM empregado;

SELECT * FROM empregado 
    ORDER BY vlsalario desc;
    
SELECT * FROM empregado 
    ORDER BY vlsalario asc;
    
SELECT * FROM empregado 
    ORDER BY vlsalario desc, dtadmissao asc;

SELECT * FROM empregado 
    ORDER BY vlsalario desc, dtadmissao desc;

SELECT * FROM empregado 
    WHERE nrgerente is null 
    ORDER BY vlsalario desc, dtadmissao desc;
    
SELECT * FROM empregado 
    WHERE nrgerente is not null 
    ORDER BY vlsalario desc, dtadmissao desc;
-------------------------------------------------

-------- Limite de linhas -----------------------
SELECT * FROM Empregado
    WHERE ROWNUM <= 5;

SELECT * FROM Empregado
    WHERE nrgerente is null and ROWNUM <= 5;
---------------------------------------------------
------------- Uso do Like -------------------------

SELECT * FROM Empregado
    WHERE dscargo LIKE 'Gerente';

SELECT * FROM Empregado
    WHERE nmempregado LIKE '%Nascimento%';

SELECT * FROM Empregado
    WHERE nmempregado LIKE '%Caeta__%';
--------------------------------------------------

---------- CONSULTA COM IN -----------------------
SELECT * FROM Empregado
    WHERE dscargo IN ('Gerente', 'Presidente');

---------- SUBCONSULTA --------------------------
SELECT nrdepto FROM depto;
    
SELECT * FROM Empregado
    WHERE nrdepto IN (
        SELECT nrdepto FROM depto 
            where nrdepto > 20
        );

SELECT distinct dscargo FROM Empregado
    WHERE nrdepto IN (
        SELECT nrdepto FROM depto 
            where nrdepto > 20
        );

select * from depto;

SELECT * FROM Empregado
    WHERE nrdepto IN (
        SELECT nrdepto FROM depto 
            --where nrdepto > 20 and nrdepto < 50
            where nrdepto between 20 and 40 OR nrdepto = 1
        );
-----------------------------------------------------------------

----------------------- FUNÇÕES ---------------------
select round(AVG(vlsalario), 2) from Empregado;
select round(min(vlsalario), 2) from Empregado;
select round(max(vlsalario), 2) from Empregado;
select round(sum(vlsalario), 2) from Empregado;

select 
    round(AVG(vlsalario), 2) as "Média Salario", 
    round(min(vlsalario), 2) "Menor salário" ,
    round(max(vlsalario), 2) "Maior salário" ,
    round(sum(vlsalario), 2) "Soma salário" 
from Empregado;
--------------------------------------------------

-------------- Uso do Group by ----------------
-- A cláusula "HAVING" pode ser utilizada para especificar quais grupos deverão ser exibidos, 
-- portanto, restringindo-os. 
select * from empregado;
select dscargo, vlsalario from empregado;
select dscargo, vlsalario from empregado order by vlsalario asc, dscargo asc;
select dscargo, vlsalario from empregado order by DSCARGO asc, VLSALARIO asc;

-- Group by ----
select dscargo, avg(vlsalario) from empregado 
    group by DSCARGO 
    order by avg(vlsalario) desc, dscargo desc;

SELECT 
    AVG(VlSalario) FROM empregado 
    group by vlsalario 
    having count(vlsalario) >= 1;

SELECT NrDepto, AVG(VlSalario) 
    FROM Empregado 
    GROUP BY NrDepto
    having count(vlsalario) > 3;

