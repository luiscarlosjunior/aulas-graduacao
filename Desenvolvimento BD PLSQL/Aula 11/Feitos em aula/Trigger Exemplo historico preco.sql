CREATE OR REPLACE TRIGGER trg_historico_preco
  BEFORE UPDATE OF produto_preco_un
  ON produto
  FOR EACH ROW
BEGIN
  -- Inserir os dados antigos
  INSERT INTO historico_produto
  VALUES
  (
    :old.produto_id,
    :old.produto_nome,
    :old.produto_desc,
    :old.produto_preco_un
  );
END;
/

UPDATE produto SET produto_preco_un = 5.00
  WHERE produto_id = 5;

UPDATE produto SET produto_preco_un = 8.45
  WHERE produto_id = 5;
  
UPDATE produto SET produto_preco_un = 9.00
  WHERE produto_id = 1;
  
SELECT * FROM historico_produto;
SELECT * FROM produto;