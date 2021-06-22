SELECT * FROM FUNCIONARIO;
SELECT * FROM CARGO;

SELECT NOME FROM FUNCIONARIO;
SELECT CARGO FROM CARGO;

SELECT * FROM FUNCIONARIO INNER JOIN CARGO
    ON funcionario.idcargo = cargo.idcargo;
    
SELECT nome, cargo FROM FUNCIONARIO INNER JOIN CARGO
    ON funcionario.idcargo = cargo.idcargo;

-- Criando Apelidos (ALIAS) AS ALIAS
SELECT nome, cargo FROM FUNCIONARIO f 
    INNER JOIN CARGO c
    ON f.idcargo = c.idcargo;

-- Alterando nome das colunas
SELECT f.nome "Nome Funcion�rio", c.cargo "Cargo Funcion�rio" 
FROM FUNCIONARIO f RIGHT JOIN CARGO c   
    ON f.idcargo = c.idcargo;

SELECT f.nome "Nome Funcion�rio", c.cargo "Cargo Funcion�rio" 
FROM FUNCIONARIO f LEFT JOIN CARGO c   
    ON f.idcargo = c.idcargo;    
    
SELECT f.nome "Nome Funcion�rio", c.cargo "Cargo Funcion�rio" 
FROM FUNCIONARIO f FULL JOIN CARGO c   
    ON f.idcargo = c.idcargo;    

-- Conhecido como jun��o cartesiana x y
SELECT f.nome "Nome Funcion�rio", c.cargo "Cargo Funcion�rio" 
FROM FUNCIONARIO f CROSS JOIN CARGO c;    

SELECT f.nome "Nome Funcion�rio", c.cargo "Cargo Funcion�rio" 
FROM FUNCIONARIO f, CARGO c;  

