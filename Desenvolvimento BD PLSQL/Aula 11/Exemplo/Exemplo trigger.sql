CREATE or REPLACE TRIGGER historico_preco_trigger 
BEFORE UPDATE OF produto_preco_unitario 
ON produtos 
FOR EACH ROW 
BEGIN 
INSERT INTO historico_preco_produto
VALUES 
(:old.produto_id, 
 :old.produto_nome, 
 :old.produto_descricao, 
 :old.produto_preco_unitario); 
END; 
/

UPDATE PRODUTOS SET produto_preco_unitario = 800
   WHERE produto_id = 5;
   
SELECT * FROM historico_preco_produto
   