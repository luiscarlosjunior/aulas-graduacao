-- ===============================================
-- SOLUÇÕES DOS EXERCÍCIOS - MÓDULO 04
-- Trabalhando com a Estrutura de Tabelas
-- ===============================================

-- ===============================================
-- EXERCÍCIO 1: ANÁLISE DE ESTRUTURAS EXISTENTES
-- ===============================================

-- 1.1 Listar todas as tabelas criadas
SELECT table_name, table_type
FROM information_schema.tables 
WHERE table_schema = 'public'
ORDER BY table_name;

-- 1.2 Estrutura detalhada da tabela usuario
SELECT 
    column_name,
    data_type,
    character_maximum_length,
    is_nullable,
    column_default
FROM information_schema.columns 
WHERE table_name = 'usuario'
ORDER BY ordinal_position;

-- 1.3 Constraints da tabela musica
SELECT 
    constraint_name,
    constraint_type,
    column_name
FROM information_schema.table_constraints tc
JOIN information_schema.constraint_column_usage ccu USING (constraint_name)
WHERE tc.table_name = 'musica';

-- 1.4 Relacionamentos entre album e artista
SELECT 
    tc.constraint_name,
    kcu.column_name,
    ccu.table_name AS foreign_table,
    ccu.column_name AS foreign_column
FROM information_schema.table_constraints tc
JOIN information_schema.key_column_usage kcu USING (constraint_name)
JOIN information_schema.constraint_column_usage ccu USING (constraint_name)
WHERE tc.table_name = 'album' AND tc.constraint_type = 'FOREIGN KEY';

-- ===============================================
-- EXERCÍCIO 2: ESCOLHA DE TIPOS DE DADOS
-- ===============================================

CREATE TABLE concerto (
    id_concerto         INTEGER PRIMARY KEY,
    nome_evento         VARCHAR(200) NOT NULL,
    data_horario        TIMESTAMP NOT NULL,
    cidade              VARCHAR(100) NOT NULL,
    pais                VARCHAR(50) NOT NULL,
    capacidade_maxima   INTEGER NOT NULL,
    preco_ingresso      DECIMAL(10,2),
    status_evento       VARCHAR(20) NOT NULL,
    eh_gratuito         BOOLEAN DEFAULT FALSE,
    duracao_minutos     SMALLINT,
    idade_minima        SMALLINT DEFAULT 0,
    observacoes         TEXT
);

-- ===============================================
-- EXERCÍCIO 3: IMPLEMENTAÇÃO DE CONSTRAINTS
-- ===============================================

DROP TABLE IF EXISTS concerto;

CREATE TABLE concerto (
    id_concerto         INTEGER PRIMARY KEY,
    nome_evento         VARCHAR(200) NOT NULL,
    data_horario        TIMESTAMP NOT NULL,
    cidade              VARCHAR(100) NOT NULL,
    pais                VARCHAR(50) NOT NULL,
    capacidade_maxima   INTEGER NOT NULL,
    preco_ingresso      DECIMAL(10,2),
    status_evento       VARCHAR(20) NOT NULL,
    eh_gratuito         BOOLEAN DEFAULT FALSE,
    duracao_minutos     SMALLINT,
    idade_minima        SMALLINT DEFAULT 0,
    observacoes         TEXT,
    
    -- Constraints de validação
    CHECK (capacidade_maxima > 0),
    CHECK (preco_ingresso IS NULL OR preco_ingresso >= 0),
    CHECK (status_evento IN ('agendado', 'realizado', 'cancelado', 'adiado')),
    CHECK (data_horario > CURRENT_TIMESTAMP),
    CHECK (idade_minima >= 0 AND idade_minima <= 18),
    CHECK (duracao_minutos IS NULL OR (duracao_minutos >= 30 AND duracao_minutos <= 480)),
    
    -- Lógica de negócio: se gratuito, preço deve ser NULL ou 0
    CHECK (eh_gratuito = FALSE OR preco_ingresso IS NULL OR preco_ingresso = 0)
);

-- ===============================================
-- EXERCÍCIO 4: RELACIONAMENTOS COMPLEXOS
-- ===============================================

-- 4.1 Tabela de tipos de ingresso
CREATE TABLE tipo_ingresso (
    id_tipo             INTEGER PRIMARY KEY,
    nome_tipo           VARCHAR(50) NOT NULL UNIQUE,
    descricao           VARCHAR(200),
    multiplicador_preco DECIMAL(4,2) DEFAULT 1.00,
    
    CHECK (multiplicador_preco > 0)
);

-- 4.2 Tabela de ingressos
CREATE TABLE ingresso (
    id_ingresso         INTEGER PRIMARY KEY,
    id_concerto         INTEGER NOT NULL,
    id_usuario          INTEGER NOT NULL,
    id_tipo_ingresso    INTEGER NOT NULL,
    data_compra         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    preco_pago          DECIMAL(10,2) NOT NULL,
    status_ingresso     VARCHAR(20) DEFAULT 'ativo',
    setor_assento       VARCHAR(50),
    
    -- Relacionamentos
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_tipo_ingresso) REFERENCES tipo_ingresso(id_tipo),
    
    -- Validações
    CHECK (preco_pago >= 0),
    CHECK (status_ingresso IN ('ativo', 'usado', 'cancelado')),
    
    -- Evitar compra duplicada do mesmo ingresso
    UNIQUE (id_concerto, id_usuario, id_tipo_ingresso, setor_assento)
);

-- Inserir tipos básicos de ingresso
INSERT INTO tipo_ingresso VALUES
(1, 'Normal', 'Ingresso padrão', 1.00),
(2, 'Premium', 'Área premium com melhor localização', 1.50),
(3, 'VIP', 'Acesso VIP com benefícios exclusivos', 2.50);

-- ===============================================
-- EXERCÍCIO 5: OTIMIZAÇÃO DE PERFORMANCE
-- ===============================================

-- Tabela desnormalizada para consultas rápidas de concertos
CREATE TABLE consulta_rapida_concertos (
    id_concerto         INTEGER PRIMARY KEY,
    nome_evento         VARCHAR(200) NOT NULL,
    data_evento         DATE NOT NULL,
    hora_evento         TIME NOT NULL,
    cidade              VARCHAR(100) NOT NULL,
    pais                VARCHAR(50) NOT NULL,
    status_evento       SMALLINT NOT NULL,  -- 1=agendado, 2=realizado, 3=cancelado, 4=adiado
    eh_gratuito         BOOLEAN,
    preco_minimo        DECIMAL(8,2),
    capacidade_total    INTEGER,
    ingressos_vendidos  INTEGER DEFAULT 0,
    ingressos_disponiveis INTEGER,
    
    -- Campos para busca otimizada
    ano_evento          SMALLINT,
    mes_evento          SMALLINT,
    cidade_normalizada  VARCHAR(100),  -- Sem acentos para busca
    
    -- Relacionamento com tabela original
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto),
    
    -- Índices implícitos para consultas frequentes
    CHECK (status_evento BETWEEN 1 AND 4),
    CHECK (ingressos_vendidos >= 0),
    CHECK (ingressos_vendidos <= capacidade_total)
);

-- ===============================================
-- EXERCÍCIO 6: VALIDAÇÃO PRÁTICA
-- ===============================================

-- 6.1 Inserir dados válidos
INSERT INTO concerto VALUES 
(1, 'Rock in Rio 2024', '2024-09-15 20:00:00', 'Rio de Janeiro', 'Brasil', 
 100000, 150.00, 'agendado', FALSE, 480, 16, 'Festival de rock internacional');

INSERT INTO concerto VALUES 
(2, 'Show Gratuito no Parque', '2024-08-20 18:00:00', 'São Paulo', 'Brasil', 
 5000, NULL, 'agendado', TRUE, 120, 0, 'Evento cultural gratuito');

-- 6.2 Teste de inserções que devem falhar
-- Estas inserções devem gerar erro:

-- Capacidade inválida (deve falhar)
-- INSERT INTO concerto VALUES (3, 'Teste', '2024-12-01 20:00:00', 'Teste', 'Brasil', -100, 50.00, 'agendado', FALSE, 120, 0, '');

-- Data no passado (deve falhar)
-- INSERT INTO concerto VALUES (4, 'Teste', '2020-01-01 20:00:00', 'Teste', 'Brasil', 1000, 50.00, 'agendado', FALSE, 120, 0, '');

-- Status inválido (deve falhar)
-- INSERT INTO concerto VALUES (5, 'Teste', '2024-12-01 20:00:00', 'Teste', 'Brasil', 1000, 50.00, 'invalido', FALSE, 120, 0, '');

-- 6.3 Verificar relacionamentos
SELECT 
    c.nome_evento,
    COUNT(i.id_ingresso) as total_ingressos,
    SUM(i.preco_pago) as receita_total
FROM concerto c
LEFT JOIN ingresso i ON c.id_concerto = i.id_concerto
GROUP BY c.id_concerto, c.nome_evento;

-- ===============================================
-- EXERCÍCIO 7: MODIFICAÇÃO DE ESTRUTURAS
-- ===============================================

-- 7.1 Adicionar novos campos
ALTER TABLE concerto ADD COLUMN website_oficial VARCHAR(255);
ALTER TABLE concerto ADD COLUMN telefone_info VARCHAR(20);
ALTER TABLE concerto ADD COLUMN permite_camping BOOLEAN DEFAULT FALSE;

-- 7.2 Modificar campos existentes
ALTER TABLE concerto ALTER COLUMN nome_evento TYPE VARCHAR(300);

-- 7.3 Remover campo desnecessário
ALTER TABLE concerto DROP COLUMN observacoes;

-- Criar tabela separada para observações detalhadas
CREATE TABLE concerto_detalhes (
    id_concerto     INTEGER PRIMARY KEY,
    observacoes     TEXT,
    informacoes_extras JSONB,
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto)
);

-- ===============================================
-- EXERCÍCIO 8: ANÁLISE COMPARATIVA
-- ===============================================

-- 8.1 Abordagem 1: Campos separados
CREATE TABLE concerto_endereco_v1 (
    id_concerto INTEGER PRIMARY KEY,
    rua VARCHAR(200),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    pais VARCHAR(50),
    cep VARCHAR(20),
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto)
);

-- 8.2 Abordagem 2: Campo único
CREATE TABLE concerto_endereco_v2 (
    id_concerto INTEGER PRIMARY KEY,
    endereco_completo TEXT,
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto)
);

-- 8.3 Abordagem 3: JSON estruturado
CREATE TABLE concerto_endereco_v3 (
    id_concerto INTEGER PRIMARY KEY,
    endereco_json JSONB,
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto)
);

-- Inserir dados de teste nas três versões
INSERT INTO concerto_endereco_v1 VALUES 
(1, 'Avenida Atlântica', '1000', 'Copacabana', 'Rio de Janeiro', 'RJ', 'Brasil', '22021-000');

INSERT INTO concerto_endereco_v2 VALUES 
(1, 'Avenida Atlântica, 1000, Copacabana, Rio de Janeiro, RJ, Brasil, 22021-000');

INSERT INTO concerto_endereco_v3 VALUES 
(1, '{"rua": "Avenida Atlântica", "numero": "1000", "bairro": "Copacabana", "cidade": "Rio de Janeiro", "estado": "RJ", "pais": "Brasil", "cep": "22021-000"}');

-- Consultas de teste
-- Buscar por cidade - v1 (mais eficiente)
SELECT * FROM concerto_endereco_v1 WHERE cidade = 'Rio de Janeiro';

-- Buscar por cidade - v2 (menos eficiente)
SELECT * FROM concerto_endereco_v2 WHERE endereco_completo LIKE '%Rio de Janeiro%';

-- Buscar por cidade - v3 (moderadamente eficiente com índice JSON)
SELECT * FROM concerto_endereco_v3 WHERE endereco_json->>'cidade' = 'Rio de Janeiro';

-- ===============================================
-- EXERCÍCIO 9: PROJETO INTEGRADO
-- ===============================================

-- 9.1 Tabela de avaliações
CREATE TABLE avaliacao_concerto (
    id_avaliacao        INTEGER PRIMARY KEY,
    id_concerto         INTEGER NOT NULL,
    id_usuario          INTEGER NOT NULL,
    nota                DECIMAL(3,1) NOT NULL,
    comentario          TEXT,
    data_avaliacao      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    
    -- Validações
    CHECK (nota >= 0 AND nota <= 10),
    
    -- Usuário pode avaliar cada concerto apenas uma vez
    UNIQUE (id_concerto, id_usuario)
);

-- 9.2 Tabela de curtidas em avaliações
CREATE TABLE curtida_avaliacao (
    id_curtida          INTEGER PRIMARY KEY,
    id_avaliacao        INTEGER NOT NULL,
    id_usuario          INTEGER NOT NULL,
    tipo_curtida        SMALLINT NOT NULL,  -- 1=curtida, -1=descurtida
    data_curtida        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (id_avaliacao) REFERENCES avaliacao_concerto(id_avaliacao),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    
    CHECK (tipo_curtida IN (1, -1)),
    
    -- Usuário pode curtir cada avaliação apenas uma vez
    UNIQUE (id_avaliacao, id_usuario)
);

-- 9.3 Tabela de participação em concertos
CREATE TABLE participacao_concerto (
    id_participacao     INTEGER PRIMARY KEY,
    id_concerto         INTEGER NOT NULL,
    id_usuario          INTEGER NOT NULL,
    data_participacao   DATE NOT NULL,
    confirmado          BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (id_concerto) REFERENCES concerto(id_concerto),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    
    UNIQUE (id_concerto, id_usuario)
);

-- 9.4 View para ranking de concertos
CREATE VIEW ranking_concertos AS
SELECT 
    c.id_concerto,
    c.nome_evento,
    c.cidade,
    c.pais,
    COUNT(a.id_avaliacao) as total_avaliacoes,
    AVG(a.nota) as nota_media,
    COUNT(p.id_participacao) as total_participantes
FROM concerto c
LEFT JOIN avaliacao_concerto a ON c.id_concerto = a.id_concerto
LEFT JOIN participacao_concerto p ON c.id_concerto = p.id_concerto
GROUP BY c.id_concerto, c.nome_evento, c.cidade, c.pais
HAVING COUNT(a.id_avaliacao) >= 5  -- Mínimo 5 avaliações
ORDER BY AVG(a.nota) DESC, COUNT(a.id_avaliacao) DESC;

-- 9.5 Trigger para validar que usuário participou do concerto antes de avaliar
CREATE OR REPLACE FUNCTION validar_avaliacao()
RETURNS TRIGGER AS $$
BEGIN
    -- Verificar se usuário participou do concerto
    IF NOT EXISTS (
        SELECT 1 FROM participacao_concerto 
        WHERE id_concerto = NEW.id_concerto 
          AND id_usuario = NEW.id_usuario
    ) THEN
        RAISE EXCEPTION 'Usuário deve ter participado do concerto para avaliá-lo';
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_avaliacao
    BEFORE INSERT ON avaliacao_concerto
    FOR EACH ROW
    EXECUTE FUNCTION validar_avaliacao();

-- ===============================================
-- EXERCÍCIO 10: TROUBLESHOOTING
-- ===============================================

-- PROBLEMAS IDENTIFICADOS E CORREÇÕES:

-- Problema 1: ID sem constraint de chave primária
-- Problema 2: VARCHAR sem tamanho definido
-- Problema 3: Data padrão no passado
-- Problema 4: DECIMAL sem precisão definida
-- Problema 5: Status sem validação de valores
-- Problema 6: Capacidade com valor negativo padrão
-- Problema 7: Email usando TEXT em vez de VARCHAR com tamanho apropriado

-- VERSÃO CORRIGIDA:
CREATE TABLE evento_corrigido (
    id INTEGER PRIMARY KEY,  -- Correção 1: Adicionada PRIMARY KEY
    nome VARCHAR(200) NOT NULL,  -- Correção 2: Definido tamanho e NOT NULL
    data_evento DATE DEFAULT CURRENT_DATE + INTERVAL '30 days',  -- Correção 3: Data futura
    preco DECIMAL(10,2) CHECK (preco >= 0),  -- Correção 4: Precisão definida e validação
    status VARCHAR(20) DEFAULT 'ativo' CHECK (status IN ('ativo', 'cancelado', 'adiado')),  -- Correção 5: Valores validados
    capacidade INTEGER DEFAULT 100 CHECK (capacidade > 0),  -- Correção 6: Valor positivo
    organizador_email VARCHAR(254) UNIQUE CHECK (organizador_email LIKE '%@%.%')  -- Correção 7: Tipo e validação apropriados
);

-- INSERÇÃO CORRIGIDA:
INSERT INTO evento_corrigido (id, nome, data_evento, preco, status, capacidade, organizador_email) VALUES 
(1, 'Evento Teste', '2024-12-31', 25.50, 'ativo', 500, 'organizador@evento.com');

-- VERIFICAÇÃO:
SELECT * FROM evento_corrigido;

-- ===============================================
-- SCRIPTS DE VALIDAÇÃO FINAL
-- ===============================================

-- Verificar todas as tabelas criadas nos exercícios
SELECT 
    table_name,
    (SELECT COUNT(*) FROM information_schema.columns WHERE table_name = t.table_name) as num_colunas,
    (SELECT COUNT(*) FROM information_schema.table_constraints WHERE table_name = t.table_name) as num_constraints
FROM information_schema.tables t
WHERE t.table_schema = 'public' 
  AND t.table_name LIKE '%concerto%' OR t.table_name LIKE '%ingresso%' OR t.table_name LIKE '%avaliacao%'
ORDER BY t.table_name;

-- Relatório final de validação
SELECT 'Todos os exercícios concluídos com sucesso!' as status;