# Operações CRUD - Explicações Detalhadas

## 🎯 Visão Geral das Operações CRUD

O MusiStream demonstra todas as operações fundamentais de banco de dados através de cenários realistas de um sistema de streaming musical.

## 📝 CREATE (Inserção de Dados)

### Script: `02-inserir-dados.sql`

#### Estratégia de Inserção
```sql
-- Ordem respeitando dependências
1. Tipos básicos (gêneros, tipos de assinatura)
2. Entidades principais (usuários, artistas) 
3. Entidades dependentes (álbuns, músicas)
4. Relacionamentos (playlists, histórico)
```

#### Técnicas Demonstradas

**1. Inserção com Sequences**
```sql
INSERT INTO genero (id_genero, nome_genero, descricao) VALUES 
(seq_genero.NEXTVAL, 'Rock', 'Música caracterizada por guitarras elétricas');
```
- **Vantagem**: IDs únicos garantidos
- **Performance**: Otimizado para concorrência
- **Manutenção**: Não requer gerenciamento manual

**2. Inserção com Valores DEFAULT**
```sql
INSERT INTO usuario (
    id_usuario, nome_usuario, email, senha, data_nascimento, pais
    -- data_cadastro usará DEFAULT (SYSDATE)
    -- ativo usará DEFAULT ('S')
) VALUES (
    seq_usuario.NEXTVAL, 'João Silva', 'joao@email.com', 'senha123',
    DATE '1990-05-15', 'Brasil'
);
```

**3. Inserção de Dados Relacionados**
```sql
-- Primeiro o artista
INSERT INTO artista (...) VALUES (...);

-- Depois o álbum (usando o ID do artista)
INSERT INTO album (id_album, titulo, id_artista) 
VALUES (seq_album.NEXTVAL, 'Abbey Road', seq_artista.CURRVAL);

-- Por fim as músicas (usando o ID do álbum)
INSERT INTO musica (id_musica, titulo, id_album)
VALUES (seq_musica.NEXTVAL, 'Come Together', seq_album.CURRVAL);
```

#### Validações Durante Inserção
- **Email único**: Constraint previne duplicatas
- **Integridade referencial**: FKs garantem relacionamentos válidos
- **Check constraints**: Validam regras de negócio (idade, duração, etc.)

## 📖 READ (Consulta de Dados)

### Script: `03-consultas-basicas.sql`

#### Tipos de Consulta Demonstrados

**1. Consultas Simples**
```sql
-- Listagem básica com formatação
SELECT 
    id_usuario,
    nome_usuario,
    email,
    TO_CHAR(data_cadastro, 'DD/MM/YYYY') AS data_cadastro
FROM usuario 
ORDER BY nome_usuario;
```

**2. Consultas com Filtros**
```sql
-- Múltiplas condições
SELECT titulo, duracao, total_reproducoes
FROM musica 
WHERE duracao > 300  -- Mais de 5 minutos
  AND total_reproducoes > 0
ORDER BY total_reproducoes DESC;
```

**3. Consultas com JOINs**
```sql
-- INNER JOIN para dados relacionados
SELECT 
    m.titulo AS musica,
    ar.nome_artista,
    al.titulo AS album,
    g.nome_genero
FROM musica m
JOIN album al ON m.id_album = al.id_album
JOIN artista ar ON al.id_artista = ar.id_artista
LEFT JOIN genero g ON m.id_genero = g.id_genero
ORDER BY ar.nome_artista;
```

**4. Consultas com Agregação**
```sql
-- Estatísticas por grupo
SELECT 
    ar.nome_artista,
    COUNT(al.id_album) AS total_albums,
    COUNT(m.id_musica) AS total_musicas,
    ROUND(AVG(m.duracao), 0) AS duracao_media
FROM artista ar
LEFT JOIN album al ON ar.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
GROUP BY ar.id_artista, ar.nome_artista
ORDER BY total_albums DESC;
```

#### Padrões de Performance
- **Índices utilizados**: Para colunas em WHERE e JOIN
- **LIMIT com ROWNUM**: Para paginação eficiente
- **LEFT JOIN**: Para incluir registros sem relacionamentos

## ✏️ UPDATE (Atualização de Dados)

### Script: `04-atualizacoes.sql`

#### Cenários de Atualização

**1. Atualização Simples**
```sql
-- Atualizar último acesso
UPDATE usuario 
SET ultimo_acesso = CURRENT_TIMESTAMP
WHERE id_usuario = 1;
```

**2. Atualização Condicional**
```sql
-- Desativar usuários inativos
UPDATE usuario 
SET ativo = 'N'
WHERE id_usuario IN (
    SELECT u.id_usuario
    FROM usuario u
    LEFT JOIN historico_reproducao hr ON u.id_usuario = hr.id_usuario
    GROUP BY u.id_usuario
    HAVING MAX(hr.data_reproducao) < SYSDATE - 30 
        OR MAX(hr.data_reproducao) IS NULL
);
```

**3. Atualização em Lote**
```sql
-- Reajuste de preços
UPDATE tipo_assinatura 
SET preco_mensal = preco_mensal * 1.10
WHERE preco_mensal > 0;
```

**4. Atualização com Subquery**
```sql
-- Corrigir contadores baseado em dados reais
UPDATE musica 
SET total_reproducoes = (
    SELECT COUNT(*)
    FROM historico_reproducao hr
    WHERE hr.id_musica = musica.id_musica
);
```

#### Padrões de Segurança
- **WHERE clauses específicas**: Evitam atualizações acidentais
- **Transações**: Para operações complexas
- **Validação prévia**: Verificar dados antes de atualizar

## 🗑️ DELETE (Exclusão de Dados)

### Script: `05-exclusoes.sql`

#### Estratégias de Exclusão

**1. Exclusão com Filtros Seguros**
```sql
-- Limpar histórico antigo
DELETE FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;
```

**2. Exclusão com Backup**
```sql
-- Criar backup antes da exclusão
CREATE TABLE historico_backup AS
SELECT * FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;

-- Depois excluir
DELETE FROM historico_reproducao 
WHERE data_reproducao < SYSDATE - 365;
```

**3. Exclusão em Lote Controlada**
```sql
-- Exclusão por lotes para evitar travamento
DECLARE
    v_batch_size NUMBER := 1000;
BEGIN
    LOOP
        DELETE FROM historico_reproducao 
        WHERE data_reproducao < SYSDATE - 730
          AND ROWNUM <= v_batch_size;
        
        EXIT WHEN SQL%ROWCOUNT = 0;
        COMMIT;
    END LOOP;
END;
```

**4. Soft Delete**
```sql
-- Marcar como inativo ao invés de excluir
UPDATE usuario 
SET ativo = 'N'
WHERE condicoes_para_desativacao;
```

#### Considerações de Integridade
- **CASCADE DELETE**: Configurado nas FKs para limpeza automática
- **Verificação prévia**: Contar registros antes de excluir
- **Transações**: Para operações que afetam múltiplas tabelas

## 🔄 Operações Avançadas

### Transações Complexas
```sql
SAVEPOINT antes_operacao;

BEGIN
    -- Múltiplas operações relacionadas
    INSERT INTO artista (...) VALUES (...);
    INSERT INTO album (...) VALUES (...);
    INSERT INTO musica (...) VALUES (...);
    
    -- Verificação de integridade
    IF verificacao_ok THEN
        COMMIT;
    ELSE
        ROLLBACK TO antes_operacao;
    END IF;
END;
```

### Operações com Controle de Erro
```sql
BEGIN
    UPDATE tabela SET campo = valor WHERE condicao;
    
    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Nenhum registro atualizado');
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;
```

## 📊 Padrões de Performance

### Otimizações Implementadas

**1. Índices Estratégicos**
- Colunas em WHERE clauses
- Colunas de JOIN
- Colunas de ORDER BY frequentes

**2. Queries Eficientes**
- EXISTS vs IN para subconsultas
- JOINs vs subconsultas correlacionadas
- LIMIT para grandes datasets

**3. Batch Processing**
- Operações em lotes para volumes grandes
- COMMIT periódicos para liberar locks
- Controle de memória e redo log

## 🛡️ Validações e Controles

### Validações Automáticas
- **Triggers**: Mantêm contadores atualizados
- **Constraints**: Garantem integridade
- **Sequences**: Evitam conflitos de ID

### Controles Manuais
- **Transações**: Para operações atômicas
- **Savepoints**: Para rollback parcial
- **Verificações**: Antes e depois das operações

---

Estas operações CRUD demonstram **padrões profissionais** para sistemas de produção, com foco em **performance**, **integridade** e **manutenibilidade**.