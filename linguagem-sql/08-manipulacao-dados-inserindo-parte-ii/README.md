# Módulo 08 - Manipulação de Dados - Inserindo Dados (Parte II)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar comandos INSERT avançados
- Realizar inserções em lote (bulk insert)
- Implementar inserções condicionais
- Trabalhar com INSERT... SELECT
- Usar sequências para geração de IDs
- Aplicar técnicas de otimização para inserção de grandes volumes
- Gerenciar conflitos durante inserções

## Conteúdo Teórico

### 1. Inserção Múltipla de Registros

#### 1.1 INSERT com Múltiplos VALUES
```sql
-- Inserir múltiplos artistas de uma vez
INSERT INTO artista (id_artista, nome_artista, pais_origem, numero_membros)
VALUES 
    (10, 'Pink Floyd', 'Reino Unido', 4),
    (11, 'Led Zeppelin', 'Reino Unido', 4),
    (12, 'The Rolling Stones', 'Reino Unido', 5),
    (13, 'Metallica', 'Estados Unidos', 4),
    (14, 'Iron Maiden', 'Reino Unido', 6);
```

#### 1.2 INSERT ALL - Oracle
```sql
-- Inserir em múltiplas tabelas relacionadas
INSERT ALL
    INTO artista (id_artista, nome_artista, pais_origem) VALUES (20, 'U2', 'Irlanda')
    INTO album (id_album, titulo, id_artista, ano_lancamento) VALUES (20, 'The Joshua Tree', 20, 1987)
    INTO album (id_album, titulo, id_artista, ano_lancamento) VALUES (21, 'Achtung Baby', 20, 1991)
SELECT * FROM dual;
```

### 2. INSERT... SELECT

#### 2.1 Cópia de Dados Entre Tabelas
```sql
-- Criar tabela de backup de artistas
CREATE TABLE backup_artista AS 
SELECT * FROM artista WHERE 1=0; -- estrutura sem dados

-- Copiar artistas ativos para backup
INSERT INTO backup_artista
SELECT * FROM artista 
WHERE ativo = 'S';
```

#### 2.2 Inserção com Transformação de Dados
```sql
-- Criar estatísticas de reprodução por artista
INSERT INTO estatistica_artista (id_artista, nome_artista, total_reproducoes, media_duracao)
SELECT 
    a.id_artista,
    a.nome_artista,
    COUNT(hr.id_musica) as total_reproducoes,
    ROUND(AVG(m.duracao), 2) as media_duracao
FROM artista a
LEFT JOIN album al ON a.id_artista = al.id_artista
LEFT JOIN musica m ON al.id_album = m.id_album
LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
GROUP BY a.id_artista, a.nome_artista;
```

### 3. Uso de Sequências

#### 3.1 Criação de Sequências
```sql
-- Sequência para IDs de usuários
CREATE SEQUENCE seq_usuario
START WITH 1
INCREMENT BY 1
MAXVALUE 999999999
NOCACHE
NOCYCLE;

-- Sequência para IDs de playlists
CREATE SEQUENCE seq_playlist
START WITH 100
INCREMENT BY 1
MAXVALUE 999999999
CACHE 20
NOCYCLE;
```

#### 3.2 Usando Sequências em INSERT
```sql
-- Inserir usuário com ID automático
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (seq_usuario.NEXTVAL, 'Ana Costa', 'ana@email.com', DATE '1995-03-10');

-- Inserir playlist com ID automático
INSERT INTO playlist (id_playlist, nome_playlist, id_usuario, descricao)
VALUES (seq_playlist.NEXTVAL, 'Minha Playlist', seq_usuario.CURRVAL, 'Playlist pessoal');
```

### 4. Inserção Condicional

#### 4.1 INSERT quando não existe
```sql
-- Inserir gênero apenas se não existir
INSERT INTO genero (id_genero, nome_genero, descricao)
SELECT 100, 'Progressive Rock', 'Rock progressivo dos anos 70'
FROM dual
WHERE NOT EXISTS (
    SELECT 1 FROM genero WHERE nome_genero = 'Progressive Rock'
);
```

#### 4.2 MERGE (UPSERT)
```sql
-- Inserir ou atualizar estatísticas de reprodução
MERGE INTO estatistica_mensal est
USING (
    SELECT 
        m.id_musica,
        EXTRACT(MONTH FROM hr.data_reproducao) as mes,
        EXTRACT(YEAR FROM hr.data_reproducao) as ano,
        COUNT(*) as total_reproducoes
    FROM musica m
    JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE hr.data_reproducao >= TRUNC(SYSDATE, 'MM')
    GROUP BY m.id_musica, EXTRACT(MONTH FROM hr.data_reproducao), EXTRACT(YEAR FROM hr.data_reproducao)
) src ON (est.id_musica = src.id_musica AND est.mes = src.mes AND est.ano = src.ano)
WHEN MATCHED THEN
    UPDATE SET est.total_reproducoes = src.total_reproducoes
WHEN NOT MATCHED THEN
    INSERT (id_musica, mes, ano, total_reproducoes)
    VALUES (src.id_musica, src.mes, src.ano, src.total_reproducoes);
```

### 5. Técnicas de Otimização

#### 5.1 Inserção em Lote (Batch Insert)
```sql
-- Desabilitar logs para inserção massiva (cuidado!)
ALTER TABLE historico_reproducao NOLOGGING;

-- Inserir em lotes usando FORALL (PL/SQL)
DECLARE
    TYPE t_musica_id IS TABLE OF NUMBER;
    TYPE t_usuario_id IS TABLE OF NUMBER;
    TYPE t_data_reprod IS TABLE OF DATE;
    
    v_musica_ids t_musica_id;
    v_usuario_ids t_usuario_id;
    v_datas t_data_reprod;
BEGIN
    -- Carregar dados em arrays
    SELECT id_musica, id_usuario, SYSDATE
    BULK COLLECT INTO v_musica_ids, v_usuario_ids, v_datas
    FROM (
        SELECT m.id_musica, u.id_usuario
        FROM musica m, usuario u
        WHERE ROWNUM <= 10000
    );
    
    -- Inserir em lote
    FORALL i IN 1..v_musica_ids.COUNT
        INSERT INTO historico_reproducao (id_historico, id_musica, id_usuario, data_reproducao)
        VALUES (seq_historico.NEXTVAL, v_musica_ids(i), v_usuario_ids(i), v_datas(i));
END;
/
```

#### 5.2 INSERT com APPEND Hint
```sql
-- Usar hint APPEND para inserção direta
INSERT /*+ APPEND */ INTO historico_reproducao_backup
SELECT * FROM historico_reproducao
WHERE data_reproducao < SYSDATE - 365;
```

### 6. Inserção de Dados Complexos

#### 6.1 Inserção com Subconsultas
```sql
-- Criar playlist automática com top 50 músicas do usuário
INSERT INTO playlist_musica (id_playlist, id_musica, ordem_reproducao)
SELECT 
    &playlist_id,
    id_musica,
    ROWNUM
FROM (
    SELECT DISTINCT hr.id_musica
    FROM historico_reproducao hr
    WHERE hr.id_usuario = &usuario_id
    GROUP BY hr.id_musica
    ORDER BY COUNT(*) DESC
)
WHERE ROWNUM <= 50;
```

#### 6.2 Inserção com Funções de Janela
```sql
-- Inserir ranking de músicas por gênero
INSERT INTO ranking_musica (id_musica, id_genero, posicao_ranking, pontuacao)
SELECT 
    id_musica,
    id_genero,
    ROW_NUMBER() OVER (PARTITION BY id_genero ORDER BY total_reproducoes DESC) as posicao,
    total_reproducoes
FROM (
    SELECT 
        m.id_musica,
        m.id_genero,
        COUNT(hr.id_historico) as total_reproducoes
    FROM musica m
    LEFT JOIN historico_reproducao hr ON m.id_musica = hr.id_musica
    WHERE hr.data_reproducao >= SYSDATE - 30
    GROUP BY m.id_musica, m.id_genero
    HAVING COUNT(hr.id_historico) > 0
);
```

### 7. Tratamento de Erros e Conflitos

#### 7.1 INSERT com Tratamento de Duplicatas
```sql
-- Inserir ignorando duplicatas
BEGIN
    INSERT INTO usuario_favorito (id_usuario, id_musica, data_adicao)
    VALUES (1, 100, SYSDATE);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        NULL; -- Ignorar erro de duplicata
END;
/
```

#### 7.2 LOG de Erros em Inserções
```sql
-- Criar tabela de log de erros
CREATE TABLE log_erros_insercao (
    id_erro NUMBER,
    tabela_destino VARCHAR2(50),
    erro_oracle NUMBER,
    mensagem_erro VARCHAR2(4000),
    dados_tentativa CLOB,
    data_erro DATE
);

-- Inserção com log de erros
DECLARE
    v_erro_count NUMBER := 0;
BEGIN
    FOR rec IN (SELECT * FROM temp_novos_usuarios) LOOP
        BEGIN
            INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
            VALUES (rec.id_usuario, rec.nome_usuario, rec.email, rec.data_nascimento);
        EXCEPTION
            WHEN OTHERS THEN
                v_erro_count := v_erro_count + 1;
                INSERT INTO log_erros_insercao 
                VALUES (seq_log_erro.NEXTVAL, 'USUARIO', SQLCODE, SQLERRM, 
                       'ID: ' || rec.id_usuario || ', Nome: ' || rec.nome_usuario, SYSDATE);
        END;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Erros encontrados: ' || v_erro_count);
END;
/
```

### 8. Padrões Avançados do Sistema MusiStream

#### 8.1 Importação de Catálogo Musical
```sql
-- Importar catálogo completo de um novo selo
INSERT ALL
    -- Inserir gravadora se não existir
    WHEN gravadora_id IS NOT NULL THEN
        INTO gravadora (id_gravadora, nome_gravadora, pais_origem)
        VALUES (gravadora_id, gravadora_nome, gravadora_pais)
    -- Inserir artista
    WHEN 1=1 THEN
        INTO artista (id_artista, nome_artista, id_gravadora, pais_origem)
        VALUES (artista_id, artista_nome, gravadora_id, artista_pais)
    -- Inserir álbum
    WHEN 1=1 THEN
        INTO album (id_album, titulo, id_artista, ano_lancamento)
        VALUES (album_id, album_titulo, artista_id, album_ano)
SELECT DISTINCT
    g.id_gravadora as gravadora_id,
    g.nome_gravadora as gravadora_nome,
    g.pais_origem as gravadora_pais,
    a.id_artista as artista_id,
    a.nome_artista as artista_nome,
    a.pais_origem as artista_pais,
    al.id_album as album_id,
    al.titulo as album_titulo,
    al.ano_lancamento as album_ano
FROM staging_catalogo_musical scm
JOIN gravadora g ON scm.gravadora_codigo = g.codigo_externo
JOIN artista a ON scm.artista_codigo = a.codigo_externo
JOIN album al ON scm.album_codigo = al.codigo_externo;
```

#### 8.2 Geração de Dados de Teste
```sql
-- Gerar histórico de reprodução realista
INSERT INTO historico_reproducao (id_historico, id_usuario, id_musica, data_reproducao, duracao_ouvida)
SELECT 
    seq_historico.NEXTVAL,
    u.id_usuario,
    m.id_musica,
    SYSDATE - DBMS_RANDOM.VALUE(1, 365), -- Data aleatória no último ano
    CASE 
        WHEN DBMS_RANDOM.VALUE < 0.3 THEN ROUND(m.duracao * 0.2) -- 30% ouvem só 20%
        WHEN DBMS_RANDOM.VALUE < 0.7 THEN ROUND(m.duracao * 0.8) -- 40% ouvem 80%
        ELSE m.duracao -- 30% ouvem completo
    END
FROM (
    SELECT id_usuario FROM usuario ORDER BY DBMS_RANDOM.VALUE
) u,
(
    SELECT id_musica, duracao FROM musica ORDER BY DBMS_RANDOM.VALUE
) m
WHERE ROWNUM <= 100000; -- Gerar 100k reproduções
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Capítulo sobre INSERT.
- **Kyte, T.** (2010). *Expert Oracle Database Architecture*. 2nd Edition. Apress. Capítulos sobre DML.
- **Lewis, J.** (2006). *Cost-Based Oracle Fundamentals*. Apress. Seções sobre bulk operations.

## Próximos Passos

No próximo módulo (09), aprenderemos sobre **Controle de Transações e Criação de Relatórios**, explorando COMMIT, ROLLBACK e técnicas de relatórios básicos.