/*
  simularemos a seguinte situação: um mercado que, ao realizar vendas, 
  precisa que o estoque dos produtos seja automaticamente reduzido.
*/

CREATE TABLE ProdutosB
(
    Referencia  NUMBER PRIMARY KEY,
    Descricao   VARCHAR2(50) UNIQUE,
    Estoque NUMBER DEFAULT 0
);
 
INSERT INTO ProdutosB VALUES (1, 'Macarrao', 10);
INSERT INTO ProdutosB VALUES (2, 'Arroz', 5);
INSERT INTO ProdutosB VALUES (3, 'Pao Turma', 15);
 
CREATE TABLE ItensVenda
(   
    Venda       NUMBER,
    ProdutoID     NUMBER,
    Quantidade  NUMBER
);

drop trigger tgrItensVenda

-- TRIGGERS
CREATE TRIGGER tgrItensVenda AFTER INSERT
ON ItensVenda
FOR EACH ROW
BEGIN
    UPDATE ProdutosB SET Estoque = Estoque - :NEW.Quantidade
WHERE Referencia = :NEW.ProdutoID;
END;

INSERT INTO ItensVenda VALUES (1, 1,3);
INSERT INTO ItensVenda VALUES (1, 2,1);
INSERT INTO ItensVenda VALUES (1, 3,5);

select * from produtosb
