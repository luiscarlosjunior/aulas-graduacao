SET SERVEROUTPUT ON;

-- Cria as tabelas
CREATE TABLE historico_produto (
  ID_PK NUMBER PRIMARY KEY,
  produto_id number not null,
  produto_nome varchar2(50),
  produto_desc varchar2(80),
  produto_preco_un number(7,2)
);

CREATE TABLE produto (
  ID_PK NUMBER PRIMARY KEY,
  produto_id number not null,
  produto_nome varchar2(50),
  produto_desc varchar2(80),
  produto_preco_un number(7,2)
)

INSERT INTO produto
  VALUES (1, 1, 'Leite', 'Caixa de 1 litro longa vida', 3.35);
INSERT INTO produto 
  VALUES (2, 2, 'Arroz', 'Saco de 5 quilos', 13.45);
INSERT INTO produto 
  VALUES (3, 5, 'Feijão', 'Carioca', 8.45);
INSERT INTO produto 
  VALUES (4, 6, 'Bolacha', 'Bolacha recheada', 2.98);
  
SELECT * FROM Produto
