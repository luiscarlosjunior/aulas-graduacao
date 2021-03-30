/*
  Vamos simular um estoque de mercado.
  Requisição: Toda vez que alguem inserir algum valor, 
  automaticamente esse valor será retirado do estoque.
  
  Por exemplo, duas tabelas: Produtos, ItensVenda;
  Produto: ID, Desc, Estoque; Realizar inserção de produtos.
  ItensVenda: ID da venda, ID_Produto, Quatd_q_foi_vendida;
  
  Faça uma trigger que atualize o estoque toda vez que uma compra
  for efetuada;
  
  10 minutos;

*/

CREATE TABLE PRODUTO (
  ID NUMBER PRIMARY KEY,
  DESCRICAO VARCHAR2(50) NOT NULL,
  ESTOQUE NUMBER DEFAULT 0
);

INSERT INTO PRODUTO VALUES (1, 'FEIJÃO', 15);
INSERT INTO PRODUTO VALUES (2, 'ARROZ', 10);
INSERT INTO PRODUTO VALUES (3, 'FARINHA', 12);

drop table itensvenda;

CREATE TABLE ITENSVENDA (
  venda_id NUMBER PRIMARY KEY,
  produto_id NUMBER,
  Qdt_vendida NUMBER DEFAULT 1  
);

CREATE OR REPLACE TRIGGER trg_itens_venda 
  AFTER INSERT 
  ON ITENSVENDA
  FOR EACH ROW
BEGIN
  UPDATE PRODUTO SET ESTOQUE = ESTOQUE - :New.Qdt_vendida
    WHERE ID = :NEW.produto_id;
END;
/

SELECT ESTOQUE FROM PRODUTO

-- Simular a compra de produtos
INSERT INTO ITENSVENDA VALUES (1, 1, 5);
INSERT INTO ITENSVENDA VALUES (2, 1, 3);
INSERT INTO ITENSVENDA VALUES (3, 3, 12);



