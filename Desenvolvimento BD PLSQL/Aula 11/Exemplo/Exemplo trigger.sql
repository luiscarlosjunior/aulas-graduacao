CREATE or REPLACE TRIGGER historico_preco_trigger 
  BEFORE UPDATE OF produto_preco_unitario 
  ON produtos 
  FOR EACH ROW 
BEGIN 
  -- Insert
  INSERT INTO historico_preco_produto
  VALUES 
  (
    :old.produto_id, 
    :old.produto_nome, 
    :old.produto_descricao, 
    :old.produto_preco_unitario
  ); 
END; 
/

UPDATE PRODUTOS SET produto_preco_unitario = 15.5
   WHERE produto_id = 2;
UPDATE PRODUTOS SET produto_preco_unitario = 1.75
   WHERE produto_id = 3;
UPDATE PRODUTOS SET produto_preco_unitario = 2.85
   WHERE produto_id = 4;

SELECT * FROM PRODUTOS;
   
SELECT SUM(produto_preco_unitario) 
  FROM historico_preco_produto WHERE produto_id > 1 GROUP BY produto_id;

