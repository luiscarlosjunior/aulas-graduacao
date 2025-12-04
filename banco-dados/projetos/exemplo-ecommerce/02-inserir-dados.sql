-- ============================================================================
-- SISTEMA DE E-COMMERCE TECHSTORE
-- Inserção de Dados de Exemplo
-- ============================================================================
-- Descrição: Script para popular o banco com dados realistas para testes
-- Versão: 1.0
-- Data: 2024
-- ============================================================================

USE ecommerce_techstore;

-- ============================================================================
-- FORNECEDORES
-- ============================================================================

INSERT INTO FORNECEDOR (razao_social, nome_fantasia, cnpj, email, telefone, contato_responsavel) VALUES
('TECH DISTRIBUIDORA LTDA', 'Tech Distribuidora', '12345678901234', 'contato@techdist.com.br', '11987654321', 'João Silva'),
('ELETRÔNICOS MASTER LTDA', 'Master Electronics', '23456789012345', 'vendas@master.com.br', '11876543210', 'Maria Santos'),
('IMPORTADORA GLOBAL S.A.', 'Global Import', '34567890123456', 'comercial@global.com.br', '11765432109', 'Pedro Costa'),
('COMPONENTES BRASIL LTDA', 'CompBrasil', '45678901234567', 'suporte@compbrasil.com.br', '11654321098', 'Ana Lima'),
('GADGETS & CIA LTDA', 'Gadgets & Cia', '56789012345678', 'info@gadgets.com.br', '11543210987', 'Carlos Ferreira');

-- ============================================================================
-- CATEGORIAS (Estrutura Hierárquica)
-- ============================================================================

-- Categorias principais (nível 1)
INSERT INTO CATEGORIA (nome_categoria, descricao, id_categoria_pai, ordem_exibicao) VALUES
('Eletrônicos', 'Produtos eletrônicos em geral', NULL, 1),
('Informática', 'Computadores, notebooks e acessórios', NULL, 2),
('Smartphones', 'Celulares e acessórios mobile', NULL, 3),
('Gaming', 'Produtos para jogos e entretenimento', NULL, 4),
('Casa Inteligente', 'Automação residencial e IoT', NULL, 5);

-- Subcategorias (nível 2)
INSERT INTO CATEGORIA (nome_categoria, descricao, id_categoria_pai, ordem_exibicao) VALUES
-- Subcategorias de Eletrônicos
('Áudio e Som', 'Fones, caixas de som, equipamentos de áudio', 1, 1),
('Fotografia', 'Câmeras, lentes e acessórios fotográficos', 1, 2),
('TV e Monitor', 'Televisores e monitores', 1, 3),

-- Subcategorias de Informática
('Notebooks', 'Laptops e notebooks', 2, 1),
('Desktops', 'Computadores desktop', 2, 2),
('Componentes', 'Placas, processadores, memórias', 2, 3),
('Periféricos', 'Mouse, teclado, webcam', 2, 4),

-- Subcategorias de Smartphones
('Android', 'Smartphones Android', 3, 1),
('iPhone', 'Dispositivos Apple iPhone', 3, 2),
('Acessórios Mobile', 'Capas, carregadores, película', 3, 3),

-- Subcategorias de Gaming
('Consoles', 'PlayStation, Xbox, Nintendo', 4, 1),
('Jogos', 'Games para todas as plataformas', 4, 2),
('Acessórios Gaming', 'Controles, headsets gaming', 4, 3),

-- Subcategorias de Casa Inteligente
('Iluminação Smart', 'Lâmpadas e sistemas inteligentes', 5, 1),
('Segurança', 'Câmeras e sistemas de segurança', 5, 2),
('Assistentes Virtuais', 'Alexa, Google Home', 5, 3);

-- ============================================================================
-- PRODUTOS
-- ============================================================================

-- Produtos de Smartphones - Android
INSERT INTO PRODUTO (nome_produto, descricao_curta, descricao_completa, marca, modelo, codigo_barras, preco_atual, peso_kg, comprimento_cm, largura_cm, altura_cm, garantia_meses, id_categoria, id_fornecedor) VALUES
('Samsung Galaxy S24 Ultra 256GB', 'Smartphone Android premium com S Pen', 'Smartphone Samsung Galaxy S24 Ultra com tela AMOLED 6.8", processador Snapdragon 8 Gen 3, 12GB RAM, 256GB armazenamento, câmera quádrupla 200MP + 50MP + 10MP + 12MP, S Pen inclusa, bateria 5000mAh com carregamento rápido 45W.', 'Samsung', 'Galaxy S24 Ultra', '7891234567890', 4999.99, 0.233, 16.26, 7.91, 0.86, 12, 13, 1),

('Google Pixel 8 Pro 128GB', 'Smartphone Google com câmera IA avançada', 'Google Pixel 8 Pro com tela OLED 6.7", processador Google Tensor G3, 12GB RAM, 128GB armazenamento, câmera tripla 50MP + 48MP + 48MP com IA avançada, bateria 5050mAh, Android 14 puro.', 'Google', 'Pixel 8 Pro', '7891234567891', 3899.99, 0.213, 16.27, 7.65, 0.88, 12, 13, 2),

('Xiaomi 14 Ultra 512GB', 'Flagship Xiaomi com câmera Leica', 'Xiaomi 14 Ultra com tela AMOLED 6.73", processador Snapdragon 8 Gen 3, 16GB RAM, 512GB armazenamento, câmera quádrupla Leica 50MP + 50MP + 50MP + 50MP, bateria 5300mAh com carregamento 90W.', 'Xiaomi', '14 Ultra', '7891234567892', 4299.99, 0.224, 16.13, 7.53, 0.91, 12, 13, 3),

-- Produtos de Smartphones - iPhone
('iPhone 15 Pro Max 256GB', 'iPhone premium com câmera ProRes', 'Apple iPhone 15 Pro Max com tela Super Retina XDR 6.7", chip A17 Pro, 256GB armazenamento, câmera tripla ProRes 48MP + 12MP + 12MP, titanium design, USB-C, bateria para o dia todo.', 'Apple', 'iPhone 15 Pro Max', '7891234567893', 8999.99, 0.221, 15.99, 7.67, 0.83, 12, 14, 4),

('iPhone 15 128GB', 'iPhone padrão com Dynamic Island', 'Apple iPhone 15 com tela Super Retina XDR 6.1", chip A16 Bionic, 128GB armazenamento, câmera dupla 48MP + 12MP, Dynamic Island, USB-C, cinco cores vibrantes disponíveis.', 'Apple', 'iPhone 15', '7891234567894', 4999.99, 0.171, 14.76, 7.15, 0.78, 12, 14, 4),

-- Produtos de Notebooks
('MacBook Air M3 256GB', 'Notebook Apple ultra-fino com chip M3', 'MacBook Air 13" com chip Apple M3 8-core CPU, GPU 8-core, 8GB RAM unificada, SSD 256GB, tela Liquid Retina 13.6", até 18 horas de bateria, peso apenas 1.24kg.', 'Apple', 'MacBook Air M3', '7891234567895', 9999.99, 1.24, 30.41, 21.50, 1.13, 12, 9, 5),

('Dell XPS 13 Plus', 'Ultrabook premium Dell com tela InfinityEdge', 'Dell XPS 13 Plus com processador Intel Core i7-13700H, 16GB RAM LPDDR5, SSD 512GB, tela OLED 13.4" touch, webcam Full HD, Thunderbolt 4, Windows 11 Pro.', 'Dell', 'XPS 13 Plus', '7891234567896', 7499.99, 1.26, 29.57, 19.92, 1.55, 12, 9, 1),

('Lenovo ThinkPad X1 Carbon', 'Notebook corporativo ultraleve', 'Lenovo ThinkPad X1 Carbon Gen 11 com Intel Core i7-1355U, 16GB RAM, SSD 1TB, tela 14" WUXGA IPS, teclado retroiluminado, leitor biométrico, certificação militar.', 'Lenovo', 'ThinkPad X1 Carbon', '7891234567897', 8999.99, 1.12, 31.54, 22.15, 1.49, 24, 9, 2),

-- Produtos de Gaming - Consoles
('PlayStation 5 Slim', 'Console Sony PS5 versão Slim', 'Sony PlayStation 5 Slim com SSD 1TB, processador AMD Zen 2, GPU RDNA 2, 16GB RAM GDDR6, suporte 4K/120fps, ray tracing, controle DualSense incluso, retrocompatibilidade PS4.', 'Sony', 'PlayStation 5 Slim', '7891234567898', 3999.99, 3.2, 35.8, 21.6, 9.6, 12, 16, 3),

('Xbox Series X', 'Console Microsoft mais poderoso', 'Microsoft Xbox Series X com SSD 1TB personalizado, processador AMD Zen 2, GPU RDNA 2 12 teraflops, 16GB RAM, 4K nativo, 120fps, ray tracing, Quick Resume, Game Pass Ultimate incluído.', 'Microsoft', 'Xbox Series X', '7891234567899', 4499.99, 4.45, 30.1, 15.1, 15.1, 12, 16, 4),

-- Produtos de Áudio
('Sony WH-1000XM5', 'Fone over-ear com cancelamento de ruído', 'Sony WH-1000XM5 com cancelamento de ruído líder da indústria, áudio Hi-Res, bateria 30h, carregamento rápido, controles touch, microfone com IA, compatível Alexa/Google.', 'Sony', 'WH-1000XM5', '7891234567900', 1899.99, 0.249, 19.0, 8.5, 25.4, 12, 6, 1),

('AirPods Pro 2ª Geração', 'Fones true wireless Apple com ANC', 'Apple AirPods Pro 2ª geração com chip H2, cancelamento ativo de ruído 2x melhor, áudio espacial personalizado, case MagSafe, até 6h de reprodução, resistente ao suor.', 'Apple', 'AirPods Pro 2', '7891234567901', 1999.99, 0.0561, 6.11, 4.55, 2.17, 12, 6, 5),

-- Produtos de TV e Monitor
('Samsung QLED 65" Q80C', 'Smart TV QLED 4K com Quantum HDR', 'Samsung QLED 65" Q80C com resolução 4K, Quantum HDR, processador Neural Quantum 4K, Tizen OS, Gaming Hub, 120hz, HDMI 2.1, controle SolarCell incluso.', 'Samsung', 'QN65Q80CAGXZD', '7891234567902', 5499.99, 22.3, 144.83, 32.89, 82.91, 12, 8, 1),

('LG OLED 55" C3', 'TV OLED 4K com Dolby Vision', 'LG OLED 55" C3 com painel OLED evo, processador α9 Gen6, webOS 23, Dolby Vision IQ, Dolby Atmos, NVIDIA G-SYNC, VRR 120Hz, ThinQ AI.', 'LG', 'OLED55C3PSA', '7891234567903', 4999.99, 18.7, 122.8, 28.6, 70.7, 12, 8, 2),

-- Produtos de Componentes
('NVIDIA RTX 4080 Super', 'Placa de vídeo RTX 4080 Super', 'NVIDIA GeForce RTX 4080 Super com 16GB GDDR6X, arquitetura Ada Lovelace, ray tracing 3ª gen, DLSS 3, 1x HDMI 2.1, 3x DisplayPort 1.4a, RGB personalizada.', 'NVIDIA', 'RTX 4080 Super', '7891234567904', 6999.99, 2.18, 30.4, 13.7, 5.4, 36, 11, 3),

('AMD Ryzen 9 7950X', 'Processador AMD Ryzen 16-core', 'AMD Ryzen 9 7950X com 16 cores/32 threads, clock base 4.5GHz, boost 5.7GHz, cache 80MB, arquitetura Zen 4, socket AM5, suporte DDR5 e PCIe 5.0.', 'AMD', 'Ryzen 9 7950X', '7891234567905', 2999.99, 0.054, 4.0, 4.0, 0.7, 36, 11, 4),

-- Produtos Casa Inteligente
('Amazon Echo Dot 5ª Geração', 'Smart speaker com Alexa', 'Amazon Echo Dot 5ª geração com som melhorado, Alexa integrada, controle de casa inteligente, música streaming, timer/alarme, privacidade integrada, design compacto.', 'Amazon', 'Echo Dot 5th Gen', '7891234567906', 299.99, 0.304, 10.0, 10.0, 8.9, 12, 21, 5),

('Philips Hue Starter Kit', 'Kit inicial de iluminação inteligente', 'Kit Philips Hue com 3 lâmpadas LED coloridas E27, bridge Hue, controle via app, compatível Alexa/Google/Apple, 16 milhões de cores, temporizador, geofencing.', 'Philips', 'Hue Color Starter Kit', '7891234567907', 899.99, 1.2, 25.0, 18.0, 12.0, 24, 19, 1),

-- Produtos de Acessórios Mobile
('Carregador Sem Fio 15W', 'Base carregamento wireless universal', 'Carregador sem fio universal 15W com tecnologia Qi, compatível iPhone/Android, indicador LED, proteção contra superaquecimento, design slim, cabo USB-C incluso.', 'Anker', 'PowerWave 15', '7891234567908', 149.99, 0.185, 10.0, 10.0, 1.1, 24, 15, 2),

('Capa Silicone iPhone 15', 'Capa protetora silicone premium', 'Capa em silicone premium para iPhone 15, proteção completa, acabamento aveludado, recortes precisos, cores vibrantes, compatível carregamento sem fio.', 'Apple', 'iPhone 15 Silicone Case', '7891234567909', 349.99, 0.05, 15.2, 7.6, 1.2, 6, 15, 5);

-- ============================================================================
-- CLIENTES
-- ============================================================================

INSERT INTO CLIENTE (nome_completo, email, cpf_cnpj, telefone, data_nascimento, tipo_pessoa) VALUES
('João Silva Santos', 'joao.silva@email.com', '12345678901', '11987654321', '1985-03-15', 'F'),
('Maria Oliveira Costa', 'maria.costa@email.com', '23456789012', '11876543210', '1990-07-22', 'F'),
('Pedro Henrique Lima', 'pedro.lima@email.com', '34567890123', '11765432109', '1988-11-08', 'F'),
('Ana Carolina Ferreira', 'ana.ferreira@email.com', '45678901234', '11654321098', '1992-05-30', 'F'),
('Carlos Eduardo Souza', 'carlos.souza@email.com', '56789012345', '11543210987', '1987-12-03', 'F'),
('Empresa ABC LTDA', 'contato@empresaabc.com', '12345678901234', '1133334444', NULL, 'J'),
('Tech Solutions ME', 'vendas@techsol.com', '23456789012345', '1144445555', NULL, 'J'),
('Lúcia Pereira Dias', 'lucia.dias@email.com', '67890123456', '11432109876', '1983-09-17', 'F'),
('Roberto Carlos Alves', 'roberto.alves@email.com', '78901234567', '11321098765', '1979-01-25', 'F'),
('Fernanda Rodrigues', 'fernanda.rodrigues@email.com', '89012345678', '11210987654', '1995-04-12', 'F');

-- ============================================================================
-- ENDEREÇOS DOS CLIENTES
-- ============================================================================

INSERT INTO ENDERECO (id_cliente, tipo_endereco, nome_endereco, logradouro, numero, complemento, bairro, cidade, estado, cep, endereco_padrao) VALUES
-- Endereços do João Silva (id_cliente = 1)
(1, 'RESIDENCIAL', 'Casa', 'Rua das Flores', '123', 'Casa 1', 'Jardim Primavera', 'São Paulo', 'SP', '01234567', TRUE),
(1, 'COMERCIAL', 'Trabalho', 'Av. Paulista', '1000', 'Sala 805', 'Bela Vista', 'São Paulo', 'SP', '01310100', FALSE),

-- Endereços da Maria Costa (id_cliente = 2)
(2, 'RESIDENCIAL', 'Apartamento', 'Rua dos Pinheiros', '456', 'Apt 302', 'Pinheiros', 'São Paulo', 'SP', '05422001', TRUE),

-- Endereços do Pedro Lima (id_cliente = 3)
(3, 'RESIDENCIAL', 'Casa', 'Rua da Consolação', '789', '', 'Consolação', 'São Paulo', 'SP', '01302001', TRUE),
(3, 'ENTREGA', 'Casa da Mãe', 'Rua Voluntários da Pátria', '555', 'Casa', 'Santana', 'São Paulo', 'SP', '02010000', FALSE),

-- Endereços da Ana Ferreira (id_cliente = 4)
(4, 'RESIDENCIAL', 'Apartamento', 'Av. Brasil', '2000', 'Apt 1501', 'Jardins', 'São Paulo', 'SP', '01431001', TRUE),

-- Endereços do Carlos Souza (id_cliente = 5)
(5, 'RESIDENCIAL', 'Casa', 'Rua Augusta', '300', '', 'Centro', 'São Paulo', 'SP', '01305000', TRUE),

-- Endereços das empresas
(6, 'COMERCIAL', 'Sede', 'Av. Faria Lima', '1500', 'Conj 201', 'Itaim Bibi', 'São Paulo', 'SP', '04538132', TRUE),
(7, 'COMERCIAL', 'Escritório', 'Rua Vergueiro', '1000', 'Sala 10', 'Paraíso', 'São Paulo', 'SP', '04101000', TRUE),

-- Endereços demais clientes
(8, 'RESIDENCIAL', 'Casa', 'Rua Pamplona', '800', '', 'Jardim Paulista', 'São Paulo', 'SP', '01405001', TRUE),
(9, 'RESIDENCIAL', 'Apartamento', 'Av. Ibirapuera', '2000', 'Apt 45', 'Ibirapuera', 'São Paulo', 'SP', '04029200', TRUE),
(10, 'RESIDENCIAL', 'Casa', 'Rua Oscar Freire', '500', '', 'Cerqueira César', 'São Paulo', 'SP', '01426001', TRUE);

-- ============================================================================
-- FORMAS DE PAGAMENTO
-- ============================================================================

INSERT INTO FORMA_PAGAMENTO (id_cliente, tipo, descricao, numero_final, nome_portador, mes_validade, ano_validade) VALUES
-- Formas de pagamento do João Silva
(1, 'CARTAO_CREDITO', 'Visa Gold', '1234', 'JOAO SILVA SANTOS', 12, 2028),
(1, 'CARTAO_DEBITO', 'Mastercard Débito', '5678', 'JOAO SILVA SANTOS', 8, 2027),
(1, 'PIX', 'PIX via CPF', NULL, NULL, NULL, NULL),

-- Formas de pagamento da Maria Costa  
(2, 'CARTAO_CREDITO', 'Mastercard Platinum', '9876', 'MARIA OLIVEIRA COSTA', 5, 2029),
(2, 'PIX', 'PIX via Email', NULL, NULL, NULL, NULL),

-- Formas de pagamento demais clientes
(3, 'CARTAO_CREDITO', 'Visa Internacional', '4321', 'PEDRO HENRIQUE LIMA', 3, 2026),
(4, 'CARTAO_CREDITO', 'Elo Mais', '8765', 'ANA CAROLINA FERREIRA', 11, 2028),
(5, 'CARTAO_DEBITO', 'Visa Débito', '2468', 'CARLOS EDUARDO SOUZA', 7, 2027),
(6, 'CARTAO_CREDITO', 'Corporate Card', '1357', 'EMPRESA ABC LTDA', 10, 2029),
(8, 'PIX', 'PIX via Telefone', NULL, NULL, NULL, NULL),
(9, 'CARTAO_CREDITO', 'Nubank', '9753', 'ROBERTO CARLOS ALVES', 6, 2026),
(10, 'CARTAO_CREDITO', 'Santander', '8642', 'FERNANDA RODRIGUES', 9, 2028);

-- ============================================================================
-- ESTOQUE DOS PRODUTOS (Atualizar quantidades)
-- ============================================================================

-- Atualizar estoque dos produtos (trigger já criou registro com quantidade 0)
UPDATE ESTOQUE SET quantidade_disponivel = 50, estoque_minimo = 10 WHERE id_produto = 1; -- Galaxy S24 Ultra
UPDATE ESTOQUE SET quantidade_disponivel = 30, estoque_minimo = 5 WHERE id_produto = 2;  -- Pixel 8 Pro
UPDATE ESTOQUE SET quantidade_disponivel = 25, estoque_minimo = 5 WHERE id_produto = 3;  -- Xiaomi 14 Ultra
UPDATE ESTOQUE SET quantidade_disponivel = 40, estoque_minimo = 8 WHERE id_produto = 4;  -- iPhone 15 Pro Max
UPDATE ESTOQUE SET quantidade_disponivel = 60, estoque_minimo = 12 WHERE id_produto = 5; -- iPhone 15
UPDATE ESTOQUE SET quantidade_disponivel = 35, estoque_minimo = 7 WHERE id_produto = 6;  -- MacBook Air M3
UPDATE ESTOQUE SET quantidade_disponivel = 20, estoque_minimo = 4 WHERE id_produto = 7;  -- Dell XPS 13
UPDATE ESTOQUE SET quantidade_disponivel = 15, estoque_minimo = 3 WHERE id_produto = 8;  -- ThinkPad X1
UPDATE ESTOQUE SET quantidade_disponivel = 25, estoque_minimo = 5 WHERE id_produto = 9;  -- PS5 Slim
UPDATE ESTOQUE SET quantidade_disponivel = 30, estoque_minimo = 6 WHERE id_produto = 10; -- Xbox Series X
UPDATE ESTOQUE SET quantidade_disponivel = 80, estoque_minimo = 15 WHERE id_produto = 11; -- Sony WH-1000XM5
UPDATE ESTOQUE SET quantidade_disponivel = 100, estoque_minimo = 20 WHERE id_produto = 12; -- AirPods Pro
UPDATE ESTOQUE SET quantidade_disponivel = 12, estoque_minimo = 2 WHERE id_produto = 13; -- Samsung QLED 65"
UPDATE ESTOQUE SET quantidade_disponivel = 15, estoque_minimo = 3 WHERE id_produto = 14; -- LG OLED 55"
UPDATE ESTOQUE SET quantidade_disponivel = 8, estoque_minimo = 2 WHERE id_produto = 15;  -- RTX 4080 Super
UPDATE ESTOQUE SET quantidade_disponivel = 20, estoque_minimo = 4 WHERE id_produto = 16; -- Ryzen 9 7950X
UPDATE ESTOQUE SET quantidade_disponivel = 150, estoque_minimo = 30 WHERE id_produto = 17; -- Echo Dot
UPDATE ESTOQUE SET quantidade_disponivel = 45, estoque_minimo = 8 WHERE id_produto = 18;  -- Philips Hue Kit
UPDATE ESTOQUE SET quantidade_disponivel = 200, estoque_minimo = 40 WHERE id_produto = 19; -- Carregador Sem Fio
UPDATE ESTOQUE SET quantidade_disponivel = 300, estoque_minimo = 60 WHERE id_produto = 20; -- Capa iPhone 15

-- ============================================================================
-- ITENS NO CARRINHO (Simular carrinho ativo)
-- ============================================================================

-- João Silva tem alguns itens no carrinho
INSERT INTO ITEM_CARRINHO (id_carrinho, id_produto, quantidade, preco_unitario) VALUES
(1, 4, 1, 8999.99), -- iPhone 15 Pro Max
(1, 12, 1, 1999.99), -- AirPods Pro
(1, 20, 1, 349.99);   -- Capa iPhone 15

-- Maria Costa também tem itens no carrinho
INSERT INTO ITEM_CARRINHO (id_carrinho, id_produto, quantidade, preco_unitario) VALUES
(2, 6, 1, 9999.99),  -- MacBook Air M3
(2, 11, 1, 1899.99); -- Sony WH-1000XM5

-- Pedro Lima tem itens no carrinho
INSERT INTO ITEM_CARRINHO (id_carrinho, id_produto, quantidade, preco_unitario) VALUES
(3, 9, 1, 3999.99),   -- PS5 Slim
(3, 17, 2, 299.99);   -- Echo Dot (2 unidades)

-- ============================================================================
-- CUPONS DE DESCONTO
-- ============================================================================

INSERT INTO CUPOM_DESCONTO (codigo_cupom, descricao, tipo_desconto, valor_desconto, valor_minimo_pedido, data_inicio, data_fim, limite_uso_total, limite_uso_cliente) VALUES
('BEMVINDO10', 'Desconto de boas-vindas para novos clientes', 'PERCENTUAL', 10.00, 500.00, '2024-01-01', '2024-12-31', 1000, 1),
('TECH50', 'R$ 50 OFF em compras acima de R$ 1000', 'VALOR_FIXO', 50.00, 1000.00, '2024-01-01', '2024-06-30', 500, 3),
('FRETE20', '20% de desconto no frete', 'PERCENTUAL', 20.00, 200.00, '2024-01-01', '2024-12-31', NULL, 5),
('BLACKFRIDAY', 'Black Friday - 25% OFF', 'PERCENTUAL', 25.00, 800.00, '2024-11-20', '2024-11-30', 2000, 1),
('NATAL100', 'Especial de Natal - R$ 100 OFF', 'VALOR_FIXO', 100.00, 1500.00, '2024-12-01', '2024-12-25', 1000, 2);

-- ============================================================================
-- PEDIDOS REALIZADOS
-- ============================================================================

-- Pedidos do João Silva
INSERT INTO PEDIDO (numero_pedido, id_cliente, id_endereco_entrega, id_status, valor_produtos, valor_frete, valor_desconto, valor_total, observacoes, data_entrega_prevista, data_pedido) VALUES
('PED-2024-000001', 1, 1, 5, 1899.99, 49.90, 0.00, 1949.89, 'Entregar pela manhã', '2024-01-20', '2024-01-15 10:30:00'),
('PED-2024-000002', 1, 1, 3, 8999.99, 0.00, 899.99, 8100.00, NULL, '2024-02-10', '2024-02-05 14:22:00');

-- Pedidos da Maria Costa
INSERT INTO PEDIDO (numero_pedido, id_cliente, id_endereco_entrega, id_status, valor_produtos, valor_frete, valor_desconto, valor_total, observacoes, data_entrega_prevista, data_pedido) VALUES
('PED-2024-000003', 2, 3, 5, 4999.99, 39.90, 0.00, 5039.89, NULL, '2024-01-25', '2024-01-18 16:45:00');

-- Pedidos do Pedro Lima
INSERT INTO PEDIDO (numero_pedido, id_cliente, id_endereco_entrega, id_status, valor_produtos, valor_frete, valor_desconto, valor_total, observacoes, data_entrega_prevista, data_pedido) VALUES
('PED-2024-000004', 3, 4, 2, 6999.99, 89.90, 50.00, 7039.89, 'Cuidado com o produto', '2024-02-15', '2024-02-08 11:15:00');

-- Pedidos da Ana Ferreira
INSERT INTO PEDIDO (numero_pedido, id_cliente, id_endereco_entrega, id_status, valor_produtos, valor_frete, valor_desconto, valor_total, observacoes, data_entrega_prevista, data_pedido) VALUES
('PED-2024-000005', 4, 6, 4, 13998.98, 0.00, 1399.90, 12599.08, 'Pedido corporativo', '2024-02-12', '2024-02-07 09:33:00');

-- Pedidos do Carlos Souza  
INSERT INTO PEDIDO (numero_pedido, id_cliente, id_endereco_entrega, id_status, valor_produtos, valor_frete, valor_desconto, valor_total, observacoes, data_entrega_prevista, data_pedido) VALUES
('PED-2024-000006', 5, 7, 5, 599.98, 29.90, 0.00, 629.88, NULL, '2024-01-30', '2024-01-22 13:20:00');

-- ============================================================================
-- ITENS DOS PEDIDOS
-- ============================================================================

-- Itens do pedido PED-2024-000001 (João Silva)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(1, 11, 1, 1899.99, 1899.99); -- Sony WH-1000XM5

-- Itens do pedido PED-2024-000002 (João Silva) 
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(2, 4, 1, 8999.99, 8999.99); -- iPhone 15 Pro Max

-- Itens do pedido PED-2024-000003 (Maria Costa)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(3, 5, 1, 4999.99, 4999.99); -- iPhone 15

-- Itens do pedido PED-2024-000004 (Pedro Lima)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(4, 15, 1, 6999.99, 6999.99); -- RTX 4080 Super

-- Itens do pedido PED-2024-000005 (Ana Ferreira)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(5, 13, 1, 5499.99, 5499.99), -- Samsung QLED 65"
(5, 10, 1, 4499.99, 4499.99), -- Xbox Series X  
(5, 16, 1, 2999.99, 2999.99); -- Ryzen 9 7950X

-- Itens do pedido PED-2024-000006 (Carlos Souza)
INSERT INTO ITEM_PEDIDO (id_pedido, id_produto, quantidade, preco_unitario, preco_total) VALUES
(6, 17, 2, 299.99, 599.98); -- Echo Dot (2 unidades)

-- ============================================================================
-- CUPONS UTILIZADOS
-- ============================================================================

INSERT INTO CUPOM_UTILIZADO (id_cupom, id_pedido, id_cliente, valor_desconto_aplicado) VALUES
(2, 2, 1, 50.00),    -- João usou TECH50 no pedido do iPhone
(2, 4, 3, 50.00),    -- Pedro usou TECH50 no pedido da placa de vídeo  
(1, 5, 4, 1399.90);  -- Ana usou BEMVINDO10 (10% de 13998.98)

-- ============================================================================
-- AVALIAÇÕES DOS PRODUTOS
-- ============================================================================

INSERT INTO AVALIACAO (id_produto, id_cliente, nota, titulo, comentario, verificada, votos_uteis, votos_totais) VALUES
-- Avaliações do Sony WH-1000XM5
(11, 1, 5, 'Excelente qualidade de som!', 'Fone fantástico, cancelamento de ruído perfeito. Recomendo demais para quem trabalha em ambientes barulhentos.', TRUE, 15, 18),
(11, 8, 4, 'Muito bom, mas caro', 'Qualidade excelente, mas o preço é salgado. Vale a pena para quem pode investir.', FALSE, 8, 12),

-- Avaliações do iPhone 15 Pro Max  
(4, 1, 5, 'iPhone perfeito!', 'Melhor iPhone que já tive. Câmera incrível, bateria dura o dia todo, performance excepcional.', TRUE, 25, 30),
(4, 9, 5, 'Vale cada centavo', 'Produto premium de verdade. A qualidade de construção é impressionante.', FALSE, 12, 15),

-- Avaliações do iPhone 15
(5, 2, 4, 'Bom custo-benefício', 'iPhone básico mas muito bom. Para uso normal atende perfeitamente.', TRUE, 10, 14),

-- Avaliações do RTX 4080 Super
(15, 3, 5, 'Placa monstro para gaming!', 'Performance absurda em 4K. Roda qualquer jogo no ultra sem esforço. Recomendo!', TRUE, 22, 25),

-- Avaliações da Samsung QLED
(13, 4, 4, 'TV excelente', 'Qualidade de imagem impressionante. Smart TV rápida e intuitiva.', TRUE, 18, 22),

-- Avaliações do Echo Dot
(17, 5, 5, 'Alexa mudou minha casa', 'Produto incrível pelo preço. Controlo tudo por voz agora. Recomendo!', TRUE, 30, 35),
(17, 10, 4, 'Bom para começar', 'Primeira experiência com assistente virtual. Funcionou bem, som podia ser melhor.', FALSE, 5, 8);

-- ============================================================================
-- ATUALIZAR DATA DE ÚLTIMA ENTRADA NO ESTOQUE
-- ============================================================================

UPDATE ESTOQUE SET data_ultima_entrada = '2024-01-10' WHERE id_produto BETWEEN 1 AND 10;
UPDATE ESTOQUE SET data_ultima_entrada = '2024-01-15' WHERE id_produto BETWEEN 11 AND 20;

-- ============================================================================
-- ESTATÍSTICAS E VERIFICAÇÕES
-- ============================================================================

SELECT 'DADOS INSERIDOS COM SUCESSO!' as status;

-- Estatísticas dos dados inseridos
SELECT 'FORNECEDORES' as tabela, COUNT(*) as total FROM FORNECEDOR
UNION ALL
SELECT 'CATEGORIAS', COUNT(*) FROM CATEGORIA  
UNION ALL
SELECT 'PRODUTOS', COUNT(*) FROM PRODUTO
UNION ALL
SELECT 'CLIENTES', COUNT(*) FROM CLIENTE
UNION ALL
SELECT 'ENDEREÇOS', COUNT(*) FROM ENDERECO
UNION ALL
SELECT 'FORMAS_PAGAMENTO', COUNT(*) FROM FORMA_PAGAMENTO
UNION ALL
SELECT 'ITENS_CARRINHO', COUNT(*) FROM ITEM_CARRINHO
UNION ALL
SELECT 'PEDIDOS', COUNT(*) FROM PEDIDO
UNION ALL
SELECT 'ITENS_PEDIDO', COUNT(*) FROM ITEM_PEDIDO
UNION ALL
SELECT 'CUPONS', COUNT(*) FROM CUPOM_DESCONTO
UNION ALL
SELECT 'CUPONS_UTILIZADOS', COUNT(*) FROM CUPOM_UTILIZADO
UNION ALL
SELECT 'AVALIAÇÕES', COUNT(*) FROM AVALIACAO;

-- Verificar integridade dos dados
SELECT 
    'PRODUTOS COM ESTOQUE' as verificacao,
    COUNT(*) as total 
FROM PRODUTO p 
INNER JOIN ESTOQUE e ON p.id_produto = e.id_produto;

SELECT 
    'CLIENTES COM CARRINHO' as verificacao,
    COUNT(*) as total 
FROM CLIENTE c 
INNER JOIN CARRINHO car ON c.id_cliente = car.id_cliente;