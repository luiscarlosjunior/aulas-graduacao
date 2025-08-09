-- =====================================================
-- ALTERAÇÃO DE ESTRUTURA DE TABELAS - DDL
-- Módulo 06: Comandos ALTER TABLE - Sistema MusiStream
-- =====================================================

-- =====================================================
-- 1. PREPARAÇÃO: CRIAÇÃO DE TABELAS BASE
-- =====================================================

-- Criar uma versão simplificada das tabelas para demonstração
CREATE TABLE demo_artista (
    id_artista INTEGER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    pais VARCHAR2(50)
);

CREATE TABLE demo_album (
    id_album INTEGER PRIMARY KEY,
    titulo VARCHAR2(150) NOT NULL,
    id_artista INTEGER,
    FOREIGN KEY (id_artista) REFERENCES demo_artista(id_artista)
);

-- Inserir dados de exemplo
INSERT INTO demo_artista VALUES (1, 'The Beatles', 'Reino Unido');
INSERT INTO demo_artista VALUES (2, 'Caetano Veloso', 'Brasil');
INSERT INTO demo_album VALUES (1, 'Abbey Road', 1);
INSERT INTO demo_album VALUES (2, 'Tropicália', 2);

-- =====================================================
-- 2. ADICIONANDO COLUNAS
-- =====================================================

-- Adicionar uma coluna simples
ALTER TABLE demo_artista 
ADD data_formacao DATE;

-- Verificar estrutura alterada
DESC demo_artista;

-- Adicionar coluna com valor padrão
ALTER TABLE demo_artista 
ADD ativo CHAR(1) DEFAULT 'S';

-- Adicionar múltiplas colunas de uma vez
ALTER TABLE demo_artista 
ADD (
    numero_membros INTEGER,
    genero_principal VARCHAR2(50),
    website VARCHAR2(200)
);

-- Adicionar coluna com constraint
ALTER TABLE demo_album 
ADD ano_lancamento INTEGER 
CONSTRAINT ck_demo_ano CHECK (ano_lancamento BETWEEN 1900 AND 2024);

-- Adicionar coluna NOT NULL com valor padrão (necessário quando há dados)
ALTER TABLE demo_album 
ADD disponivel CHAR(1) DEFAULT 'S' NOT NULL;

-- =====================================================
-- 3. MODIFICANDO COLUNAS EXISTENTES
-- =====================================================

-- Aumentar tamanho de campo VARCHAR
ALTER TABLE demo_artista 
MODIFY nome VARCHAR2(150);

-- Alterar tipo de dados (cuidado com compatibilidade)
ALTER TABLE demo_artista 
MODIFY pais VARCHAR2(100);

-- Adicionar constraint NOT NULL a coluna existente
-- (só funciona se não há valores NULL)
UPDATE demo_artista SET genero_principal = 'Rock' WHERE genero_principal IS NULL;
ALTER TABLE demo_artista 
MODIFY genero_principal NOT NULL;

-- Alterar valor padrão
ALTER TABLE demo_artista 
MODIFY ativo DEFAULT 'N';

-- =====================================================
-- 4. EVOLUÇÃO DA TABELA DE USUÁRIOS
-- =====================================================

-- Criar tabela de usuários para demonstrar evolução
CREATE TABLE demo_usuario (
    id_usuario INTEGER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) NOT NULL
);

-- Inserir alguns usuários
INSERT INTO demo_usuario VALUES (1, 'João Silva', 'joao@email.com');
INSERT INTO demo_usuario VALUES (2, 'Maria Santos', 'maria@email.com');

-- EVOLUÇÃO FASE 1: Adicionar campos básicos de perfil
ALTER TABLE demo_usuario 
ADD (
    data_nascimento DATE,
    telefone VARCHAR2(20),
    cidade VARCHAR2(100),
    data_cadastro DATE DEFAULT SYSDATE
);

-- EVOLUÇÃO FASE 2: Adicionar sistema de assinatura
ALTER TABLE demo_usuario 
ADD (
    tipo_assinatura VARCHAR2(20) DEFAULT 'FREE',
    data_inicio_assinatura DATE,
    data_fim_assinatura DATE
);

-- Adicionar constraint para tipo de assinatura
ALTER TABLE demo_usuario 
ADD CONSTRAINT ck_tipo_assinatura 
CHECK (tipo_assinatura IN ('FREE', 'PREMIUM', 'FAMILY'));

-- EVOLUÇÃO FASE 3: Adicionar preferências
ALTER TABLE demo_usuario 
ADD (
    qualidade_preferida VARCHAR2(10) DEFAULT 'ALTA',
    download_automatico CHAR(1) DEFAULT 'N',
    notificacoes CHAR(1) DEFAULT 'S'
);

-- =====================================================
-- 5. MELHORIAS NA TABELA DE ÁLBUNS
-- =====================================================

-- Adicionar informações comerciais
ALTER TABLE demo_album 
ADD (
    preco_digital NUMBER(8,2),
    preco_fisico NUMBER(8,2),
    numero_faixas INTEGER,
    duracao_total INTEGER -- em segundos
);

-- Adicionar constraints para preços
ALTER TABLE demo_album 
ADD CONSTRAINT ck_preco_digital CHECK (preco_digital >= 0);

ALTER TABLE demo_album 
ADD CONSTRAINT ck_preco_fisico CHECK (preco_fisico >= 0);

-- Adicionar informações de produção
ALTER TABLE demo_album 
ADD (
    id_gravadora INTEGER,
    id_produtor INTEGER,
    estudio_gravacao VARCHAR2(100),
    data_gravacao DATE
);

-- =====================================================
-- 6. ADICIONANDO E GERENCIANDO CONSTRAINTS
-- =====================================================

-- Adicionar chave única
ALTER TABLE demo_usuario 
ADD CONSTRAINT uq_demo_usuario_email UNIQUE (email);

-- Adicionar constraint CHECK complexa
ALTER TABLE demo_album 
ADD CONSTRAINT ck_demo_duracao_faixas 
CHECK (numero_faixas IS NULL OR duracao_total IS NULL OR 
       (numero_faixas > 0 AND duracao_total > 0));

-- Criar tabela para demonstrar foreign keys
CREATE TABLE demo_gravadora (
    id_gravadora INTEGER PRIMARY KEY,
    nome_gravadora VARCHAR2(100) NOT NULL,
    pais VARCHAR2(50)
);

-- Inserir gravadora
INSERT INTO demo_gravadora VALUES (1, 'EMI Records', 'Reino Unido');
INSERT INTO demo_gravadora VALUES (2, 'Sony Music', 'Brasil');

-- Adicionar foreign key
ALTER TABLE demo_album 
ADD CONSTRAINT fk_demo_album_gravadora 
FOREIGN KEY (id_gravadora) REFERENCES demo_gravadora(id_gravadora);

-- =====================================================
-- 7. REMOVENDO ELEMENTOS
-- =====================================================

-- Remover uma coluna
ALTER TABLE demo_artista 
DROP COLUMN website;

-- Remover múltiplas colunas
ALTER TABLE demo_usuario 
DROP (notificacoes, download_automatico);

-- Remover constraint
ALTER TABLE demo_album 
DROP CONSTRAINT ck_demo_duracao_faixas;

-- Marcar coluna como UNUSED (mais eficiente para tabelas grandes)
ALTER TABLE demo_album 
SET UNUSED COLUMN estudio_gravacao;

-- Verificar colunas unused
SELECT table_name, column_name 
FROM user_unused_col_tabs 
WHERE table_name = 'DEMO_ALBUM';

-- Remover todas as colunas unused
ALTER TABLE demo_album 
DROP UNUSED COLUMNS;

-- =====================================================
-- 8. RENOMEAÇÃO DE ELEMENTOS
-- =====================================================

-- Renomear coluna
ALTER TABLE demo_artista 
RENAME COLUMN nome TO nome_artista;

-- Renomear constraint
ALTER TABLE demo_usuario 
RENAME CONSTRAINT ck_tipo_assinatura TO ck_demo_tipo_assinatura_valido;

-- Renomear tabela
RENAME demo_gravadora TO demo_label;

-- =====================================================
-- 9. DESABILITANDO E HABILITANDO CONSTRAINTS
-- =====================================================

-- Desabilitar constraint temporariamente
ALTER TABLE demo_album 
DISABLE CONSTRAINT fk_demo_album_gravadora;

-- Inserir dados que violariam a constraint (para demonstração)
INSERT INTO demo_album (id_album, titulo, id_artista, id_gravadora) 
VALUES (99, 'Teste', 1, 999); -- gravadora que não existe

-- Habilitar constraint novamente (pode falhar se há violações)
-- ALTER TABLE demo_album 
-- ENABLE CONSTRAINT fk_demo_album_gravadora;

-- Remover dados inválidos primeiro
DELETE FROM demo_album WHERE id_gravadora = 999;

-- Agora habilitar
ALTER TABLE demo_album 
ENABLE CONSTRAINT fk_demo_album_gravadora;

-- =====================================================
-- 10. ESTRATÉGIA SEGURA PARA MUDANÇA DE TIPO
-- =====================================================

-- Cenário: Mudar duracao_total de INTEGER para NUMBER(8,2) para ter decimais

-- Passo 1: Adicionar nova coluna com tipo desejado
ALTER TABLE demo_album 
ADD duracao_total_novo NUMBER(8,2);

-- Passo 2: Migrar dados (converter segundos para minutos)
UPDATE demo_album 
SET duracao_total_novo = duracao_total / 60.0 
WHERE duracao_total IS NOT NULL;

-- Passo 3: Verificar migração
SELECT id_album, titulo, duracao_total, duracao_total_novo 
FROM demo_album;

-- Passo 4: Adicionar constraint na nova coluna
ALTER TABLE demo_album 
ADD CONSTRAINT ck_duracao_novo CHECK (duracao_total_novo > 0);

-- Passo 5: Remover coluna antiga
ALTER TABLE demo_album 
DROP COLUMN duracao_total;

-- Passo 6: Renomear nova coluna
ALTER TABLE demo_album 
RENAME COLUMN duracao_total_novo TO duracao_total_minutos;

-- =====================================================
-- 11. MODIFICAÇÕES EM LOTE PARA MÚLTIPLAS TABELAS
-- =====================================================

-- Adicionar coluna de auditoria em várias tabelas
ALTER TABLE demo_artista 
ADD (
    data_criacao DATE DEFAULT SYSDATE,
    data_modificacao DATE DEFAULT SYSDATE,
    usuario_modificacao VARCHAR2(100) DEFAULT USER
);

ALTER TABLE demo_album 
ADD (
    data_criacao DATE DEFAULT SYSDATE,
    data_modificacao DATE DEFAULT SYSDATE,
    usuario_modificacao VARCHAR2(100) DEFAULT USER
);

ALTER TABLE demo_usuario 
ADD (
    data_modificacao DATE DEFAULT SYSDATE,
    usuario_modificacao VARCHAR2(100) DEFAULT USER
);

-- =====================================================
-- 12. VERIFICAÇÃO FINAL DA ESTRUTURA
-- =====================================================

-- Verificar estrutura final das tabelas
DESCRIBE demo_artista;
DESCRIBE demo_album;
DESCRIBE demo_usuario;

-- Verificar constraints
SELECT constraint_name, constraint_type, table_name, status
FROM user_constraints 
WHERE table_name IN ('DEMO_ARTISTA', 'DEMO_ALBUM', 'DEMO_USUARIO')
ORDER BY table_name, constraint_name;

-- Verificar dados após todas as modificações
SELECT 'ARTISTAS' as tabela, COUNT(*) as registros FROM demo_artista
UNION ALL
SELECT 'ALBUMS' as tabela, COUNT(*) as registros FROM demo_album
UNION ALL
SELECT 'USUARIOS' as tabela, COUNT(*) as registros FROM demo_usuario;

-- =====================================================
-- 13. LIMPEZA (OPCIONAL)
-- =====================================================

-- Remover tabelas de demonstração
/*
DROP TABLE demo_album;
DROP TABLE demo_artista;
DROP TABLE demo_usuario;
DROP TABLE demo_label;
*/

COMMIT;