CREATE TABLE historico_preco_produto 
(ID_PK number 
produto_id number(5), 
produto_nome varchar2(50), 
produto_descricao varchar2(150), 
produto_preco_unitario number(7,2) ); 

CREATE TABLE produtos 
(produto_id number(5), 
produto_nome varchar2(32), 
produto_descricao varchar2(150), 
produto_preco_unitario number(7,2));

INSERT INTO produtos VALUES (1, 'leite', 'bebida nutritiva', 2.50);
INSERT INTO produtos VALUES (2, 'carne bovina', 'musculo', 10.00);
INSERT INTO produtos VALUES (3, 'ovos', 'item vendido por unidade',2.50);
INSERT INTO produtos VALUES (4, 'pão', '10 unidades', 3.50);
INSERT INTO produtos VALUES (5, 'laranja', 'fruta rica em 
vitamina C, quantidade minima 12 unidades', 5.00);
INSERT INTO produtos VALUES (6, 'feijão', 'Alimento rico em ferro',4.50);

select * from produtos