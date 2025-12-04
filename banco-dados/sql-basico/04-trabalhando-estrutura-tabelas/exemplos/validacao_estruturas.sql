-- ===============================================
-- Validação de Estruturas - Sistema MusiStream
-- Módulo 04: Trabalhando com a Estrutura de Tabelas
-- ===============================================

-- ===============================================
-- 1. VERIFICAÇÃO DE ESTRUTURAS EXISTENTES
-- ===============================================

-- Listar todas as tabelas do esquema atual
SELECT 
    table_name,
    table_type,
    table_schema
FROM information_schema.tables 
WHERE table_schema = 'public'  -- ou seu schema específico
ORDER BY table_name;

-- ===============================================
-- 2. ANÁLISE DETALHADA DE COLUNAS
-- ===============================================

-- Verificar estrutura detalhada das tabelas principais
SELECT 
    t.table_name,
    c.column_name,
    c.data_type,
    c.character_maximum_length,
    c.numeric_precision,
    c.numeric_scale,
    c.is_nullable,
    c.column_default,
    c.ordinal_position
FROM information_schema.tables t
JOIN information_schema.columns c ON t.table_name = c.table_name
WHERE t.table_schema = 'public'
  AND t.table_name IN ('usuario', 'artista', 'album', 'musica', 'playlist')
ORDER BY t.table_name, c.ordinal_position;

-- ===============================================
-- 3. VERIFICAÇÃO DE CONSTRAINTS
-- ===============================================

-- Listar todas as constraints das tabelas principais
SELECT 
    tc.table_name,
    tc.constraint_name,
    tc.constraint_type,
    kcu.column_name,
    CASE 
        WHEN tc.constraint_type = 'FOREIGN KEY' THEN
            ccu.table_name || '.' || ccu.column_name
        ELSE NULL
    END as foreign_table_column
FROM information_schema.table_constraints tc
LEFT JOIN information_schema.key_column_usage kcu 
    ON tc.constraint_name = kcu.constraint_name
LEFT JOIN information_schema.constraint_column_usage ccu 
    ON tc.constraint_name = ccu.constraint_name
WHERE tc.table_schema = 'public'
  AND tc.table_name IN ('usuario', 'artista', 'album', 'musica', 'playlist')
ORDER BY tc.table_name, tc.constraint_type, tc.constraint_name;

-- ===============================================
-- 4. TESTES DE INSERÇÃO VÁLIDA
-- ===============================================

-- Teste 1: Inserir usuário válido
INSERT INTO usuario (id_usuario, nome_usuario, email, genero, pais) 
VALUES (999, 'Teste Válido', 'teste.valido@email.com', 'M', 'Brasil');

-- Verificar inserção
SELECT id_usuario, nome_usuario, email, ativo, data_cadastro 
FROM usuario 
WHERE id_usuario = 999;

-- Teste 2: Inserir artista válido
INSERT INTO artista (id_artista, nome_artista, tipo_artista, pais_origem) 
VALUES (999, 'Artista Teste', 'individual', 'Brasil');

-- Verificar inserção
SELECT id_artista, nome_artista, tipo_artista, pais_origem, ativo 
FROM artista 
WHERE id_artista = 999;

-- Teste 3: Inserir álbum válido
INSERT INTO album (id_album, titulo, tipo_album, id_artista, numero_faixas) 
VALUES (999, 'Álbum Teste', 'studio', 999, 10);

-- Verificar inserção
SELECT id_album, titulo, tipo_album, numero_faixas, disponivel 
FROM album 
WHERE id_album = 999;

-- ===============================================
-- 5. TESTES DE VALIDAÇÃO DE CONSTRAINTS
-- ===============================================

-- Teste 1: Verificar constraint NOT NULL (deve falhar)
-- INSERT INTO usuario (id_usuario, nome_usuario, email) 
-- VALUES (1000, NULL, 'teste@email.com');

-- Teste 2: Verificar constraint UNIQUE (deve falhar na segunda tentativa)
INSERT INTO usuario (id_usuario, nome_usuario, email, genero) 
VALUES (1001, 'Primeiro Usuário', 'email.unico@teste.com', 'F');

-- Esta deve falhar:
-- INSERT INTO usuario (id_usuario, nome_usuario, email, genero) 
-- VALUES (1002, 'Segundo Usuário', 'email.unico@teste.com', 'M');

-- Teste 3: Verificar constraint CHECK para gênero (deve falhar)
-- INSERT INTO usuario (id_usuario, nome_usuario, email, genero) 
-- VALUES (1003, 'Usuário Inválido', 'invalido@teste.com', 'X');

-- Teste 4: Verificar FOREIGN KEY (deve falhar - artista inexistente)
-- INSERT INTO album (id_album, titulo, tipo_album, id_artista) 
-- VALUES (1000, 'Álbum Órfão', 'studio', 99999);

-- ===============================================
-- 6. TESTES DE TIPOS DE DADOS
-- ===============================================

-- Teste 1: Limites de VARCHAR
INSERT INTO usuario (id_usuario, nome_usuario, email, genero) 
VALUES (1004, 'Nome que está no limite de 100 caracteres exatamente - deve funcionar perfeitamente ok', 
        'teste.limite@email.com', 'O');

-- Teste 2: Valores decimais precisos
INSERT INTO album (id_album, titulo, tipo_album, id_artista, preco) 
VALUES (1001, 'Álbum Pago', 'studio', 999, 29.99);

-- Verificar precisão decimal
SELECT id_album, titulo, preco, 
       preco * 1.1 as preco_com_desconto
FROM album 
WHERE id_album = 1001;

-- Teste 3: Datas válidas e inválidas
INSERT INTO artista (id_artista, nome_artista, tipo_artista, data_formacao, data_fim) 
VALUES (1000, 'Banda Teste', 'banda', '1990-01-01', '2000-12-31');

-- Esta deve falhar se houver constraint (data_fim < data_formacao):
-- INSERT INTO artista (id_artista, nome_artista, tipo_artista, data_formacao, data_fim) 
-- VALUES (1001, 'Banda Inválida', 'banda', '2000-01-01', '1990-01-01');

-- ===============================================
-- 7. VERIFICAÇÃO DE RELACIONAMENTOS
-- ===============================================

-- Teste de integridade referencial
-- Verificar que não é possível deletar um artista que tem álbuns
-- DELETE FROM artista WHERE id_artista = 999;  -- Deve falhar

-- Verificar relacionamentos existentes
SELECT 
    a.nome_artista,
    COUNT(al.id_album) as total_albums,
    COUNT(m.id_musica) as total_musicas
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
WHERE a.id_artista = 999
GROUP BY a.id_artista, a.nome_artista;

-- ===============================================
-- 8. VERIFICAÇÃO DE PERFORMANCE DE TIPOS
-- ===============================================

-- Comparar performance de diferentes tipos de ID
-- (Execute com EXPLAIN ANALYZE para ver diferenças)

-- Query com INTEGER
EXPLAIN ANALYZE
SELECT count(*) FROM usuario WHERE id_usuario < 1000;

-- Comparar tamanhos de armazenamento
SELECT 
    schemaname,
    tablename,
    attname,
    n_distinct,
    most_common_vals,
    most_common_freqs,
    avg_width
FROM pg_stats 
WHERE schemaname = 'public' 
  AND tablename IN ('usuario', 'artista', 'album')
ORDER BY tablename, attname;

-- ===============================================
-- 9. ANÁLISE DE CONSISTÊNCIA DOS DADOS
-- ===============================================

-- Verificar consistência entre álbum e suas músicas
SELECT 
    a.id_album,
    a.titulo,
    a.numero_faixas as faixas_declaradas,
    COUNT(m.id_musica) as faixas_reais,
    a.duracao_total as duracao_declarada,
    SUM(m.duracao) as duracao_real
FROM album a
LEFT JOIN musica m ON a.id_album = m.id_album
GROUP BY a.id_album, a.titulo, a.numero_faixas, a.duracao_total
HAVING a.numero_faixas != COUNT(m.id_musica) 
    OR a.duracao_total != COALESCE(SUM(m.duracao), 0);

-- Verificar usuários sem configuração
SELECT u.id_usuario, u.nome_usuario, u.email
FROM usuario u
LEFT JOIN configuracao_usuario cu ON u.id_usuario = cu.id_usuario
WHERE cu.id_usuario IS NULL;

-- ===============================================
-- 10. RELATÓRIOS DE VALIDAÇÃO
-- ===============================================

-- Relatório geral de estruturas
SELECT 
    'Tabelas Criadas' as categoria,
    COUNT(*) as quantidade
FROM information_schema.tables 
WHERE table_schema = 'public'

UNION ALL

SELECT 
    'Colunas Totais' as categoria,
    COUNT(*) as quantidade
FROM information_schema.columns 
WHERE table_schema = 'public'

UNION ALL

SELECT 
    'Constraints' as categoria,
    COUNT(*) as quantidade
FROM information_schema.table_constraints 
WHERE table_schema = 'public'

UNION ALL

SELECT 
    'Foreign Keys' as categoria,
    COUNT(*) as quantidade
FROM information_schema.table_constraints 
WHERE table_schema = 'public' 
  AND constraint_type = 'FOREIGN KEY';

-- Verificar integridade geral
SELECT 
    table_name,
    constraint_type,
    COUNT(*) as total
FROM information_schema.table_constraints
WHERE table_schema = 'public'
GROUP BY table_name, constraint_type
ORDER BY table_name, constraint_type;

-- ===============================================
-- 11. LIMPEZA DOS DADOS DE TESTE
-- ===============================================

-- Remover dados de teste (executar apenas se necessário)
-- DELETE FROM album WHERE id_album >= 999;
-- DELETE FROM artista WHERE id_artista >= 999;
-- DELETE FROM usuario WHERE id_usuario >= 999;

-- Verificar limpeza
SELECT 'Dados de teste removidos' as status;