# Estrutura do Banco de Dados - Explicação Detalhada

## 🏗️ Visão Geral da Arquitetura

O MusiStream foi projetado seguindo princípios sólidos de modelagem de dados:

### Normalização
- **1ª Forma Normal (1FN)**: Todos os atributos são atômicos
- **2ª Forma Normal (2FN)**: Eliminação de dependências parciais
- **3ª Forma Normal (3FN)**: Eliminação de dependências transitivas

### Estratégia de Chaves
- **Sequences Oracle**: Garantem unicidade e performance
- **Chaves compostas**: Para relacionamentos N:M
- **Chaves estrangeiras**: Mantêm integridade referencial

## 📊 Entidades Principais

### 1. USUARIO
```sql
CREATE TABLE usuario (
    id_usuario          NUMBER PRIMARY KEY,
    nome_usuario        VARCHAR2(50) NOT NULL,
    email               VARCHAR2(100) NOT NULL UNIQUE,
    -- ... outros campos
);
```

**Justificativas de Design:**
- `id_usuario`: Chave surrogate para performance e estabilidade
- `email UNIQUE`: Cada usuário tem email único
- `ativo CHAR(1)`: Flag para soft delete, mantém histórico
- `data_nascimento`: Para validação de idade mínima (constraint)

### 2. ARTISTA
```sql
CREATE TABLE artista (
    id_artista          NUMBER PRIMARY KEY,
    nome_artista        VARCHAR2(100) NOT NULL,
    nome_real           VARCHAR2(100),
    -- ... outros campos
);
```

**Justificativas de Design:**
- Separação entre `nome_artista` e `nome_real`
- `biografia CLOB`: Permite textos longos
- `website`: Para integrações futuras
- `ativo`: Para gerenciar artistas que saem da plataforma

### 3. ALBUM e MUSICA
```sql
-- Relacionamento 1:N entre ALBUM e MUSICA
CREATE TABLE album (
    id_album            NUMBER PRIMARY KEY,
    id_artista          NUMBER NOT NULL,
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista) ON DELETE CASCADE
);

CREATE TABLE musica (
    id_musica           NUMBER PRIMARY KEY,
    id_album            NUMBER NOT NULL,
    numero_faixa        NUMBER,
    CONSTRAINT uk_album_faixa UNIQUE (id_album, numero_faixa)
);
```

**Justificativas de Design:**
- **Hierarquia clara**: ARTISTA → ALBUM → MUSICA
- **Constraint única composta**: Evita faixas duplicadas no mesmo álbum
- **CASCADE DELETE**: Remove músicas quando álbum é excluído
- **Validação de duração**: Máximo 1 hora por música

### 4. PLAYLIST (Relacionamento N:M)
```sql
-- Tabela principal
CREATE TABLE playlist (
    id_playlist         NUMBER PRIMARY KEY,
    id_usuario          NUMBER NOT NULL,
    total_musicas       NUMBER DEFAULT 0,
    duracao_total       NUMBER DEFAULT 0
);

-- Tabela de relacionamento
CREATE TABLE playlist_musica (
    id_playlist         NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    ordem_musica        NUMBER NOT NULL,
    PRIMARY KEY (id_playlist, id_musica),
    UNIQUE (id_playlist, ordem_musica)
);
```

**Justificativas de Design:**
- **Chave primária composta**: (id_playlist, id_musica)
- **Ordem das músicas**: Campo `ordem_musica` com constraint única
- **Contadores desnormalizados**: `total_musicas` e `duracao_total` para performance
- **Triggers**: Mantêm contadores atualizados automaticamente

### 5. ASSINATURAS
```sql
CREATE TABLE tipo_assinatura (
    id_tipo_assinatura  NUMBER PRIMARY KEY,
    nome_plano          VARCHAR2(50) NOT NULL UNIQUE,
    preco_mensal        NUMBER(8,2) NOT NULL
);

CREATE TABLE assinatura (
    id_assinatura       NUMBER PRIMARY KEY,
    id_usuario          NUMBER NOT NULL,
    id_tipo_assinatura  NUMBER NOT NULL,
    status_assinatura   VARCHAR2(20) DEFAULT 'ATIVA'
);
```

**Justificativas de Design:**
- **Separação de tipos**: Permite mudanças de preço sem afetar histórico
- **Histórico completo**: Mantém todas as assinaturas do usuário
- **Status controlado**: ATIVA, CANCELADA, SUSPENSA, EXPIRADA
- **Renovação automática**: Campo para controle de billing

### 6. HISTORICO_REPRODUCAO
```sql
CREATE TABLE historico_reproducao (
    id_historico        NUMBER PRIMARY KEY,
    id_usuario          NUMBER NOT NULL,
    id_musica           NUMBER NOT NULL,
    data_reproducao     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duracao_ouvida      NUMBER, -- em segundos
    dispositivo         VARCHAR2(50),
    localizacao         VARCHAR2(100)
);
```

**Justificativas de Design:**
- **Log completo**: Cada reprodução é um registro
- **Metadados ricos**: Dispositivo, localização, duração ouvida
- **TIMESTAMP**: Precisão de milissegundos para análises
- **Particionamento futuro**: Por data para performance

## 🔧 Recursos Avançados

### Índices Estratégicos
```sql
-- Para consultas frequentes
CREATE INDEX idx_musica_reproducoes ON musica(total_reproducoes DESC);
CREATE INDEX idx_hist_usuario ON historico_reproducao(id_usuario);
CREATE INDEX idx_hist_data ON historico_reproducao(data_reproducao);
```

### Triggers Automatizados
```sql
-- Atualiza contador de reproduções
CREATE OR REPLACE TRIGGER trg_update_reproducoes
AFTER INSERT ON historico_reproducao
FOR EACH ROW
BEGIN
    UPDATE musica 
    SET total_reproducoes = total_reproducoes + 1
    WHERE id_musica = :NEW.id_musica;
END;
```

### Views Pré-calculadas
```sql
-- Informações completas de músicas
CREATE OR REPLACE VIEW vw_musicas_completas AS
SELECT 
    m.id_musica,
    m.titulo AS titulo_musica,
    ar.nome_artista,
    g.nome_genero,
    m.total_reproducoes
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero;
```

## 🚀 Considerações de Performance

### Estratégias Implementadas
1. **Desnormalização Controlada**: Contadores em `playlist` e `musica`
2. **Índices Compostos**: Para consultas multi-campo
3. **Particionamento Futuro**: `historico_reproducao` por data
4. **Views Materializadas**: Para consultas complexas frequentes

### Otimizações Possíveis
1. **Cache de Queries**: Para top lists
2. **Replicação de Leitura**: Para relatórios
3. **Arquivamento**: Histórico antigo para storage separado
4. **CDN**: Para metadados de álbuns/artistas

## 🔒 Segurança e Integridade

### Constraints Implementadas
- **Check Constraints**: Validação de domínios
- **Foreign Keys**: Integridade referencial
- **Unique Constraints**: Evita duplicatas
- **Not Null**: Campos obrigatórios

### Validações de Negócio
```sql
-- Idade mínima
CONSTRAINT ck_usuario_idade CHECK (data_nascimento <= SYSDATE - INTERVAL '13' YEAR)

-- Email válido
CONSTRAINT ck_usuario_email CHECK (email LIKE '%_@_%._%')

-- Duração razoável
CONSTRAINT ck_duracao_musica CHECK (duracao > 0 AND duracao <= 3600)
```

## 📈 Escalabilidade

### Preparação para Crescimento
1. **Sequences**: Suportam milhões de registros
2. **Relacionamentos**: Suportam múltiplos dispositivos por usuário
3. **Flexibilidade**: Novos tipos de assinatura fáceis de adicionar
4. **Analytics**: Estrutura preparada para big data

### Pontos de Atenção
- `historico_reproducao` crescerá rapidamente
- Índices precisarão de manutenção regular
- Backup/recovery deve considerar volume de dados
- Monitoramento de performance é essencial

---

Esta estrutura foi projetada para ser **robusta**, **escalável** e **manutenível**, seguindo as melhores práticas de design de banco de dados para aplicações de streaming musical.