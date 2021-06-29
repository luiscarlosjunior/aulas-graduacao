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
SELECT f.nome "Nome Funcionário", c.cargo "Cargo Funcionário" FROM FUNCIONARIO f 
    INNER JOIN CARGO c
    ON f.idcargo = c.idcargo;
    
