# Módulo 07 - Manipulação de Dados - Inserindo Dados (Parte I)

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar o comando INSERT para adicionar dados
- Compreender diferentes formas de inserção
- Aplicar boas práticas na inserção de dados
- Trabalhar com valores padrão e NULL
- Inserir dados relacionados respeitando integridade referencial

## Conteúdo Teórico

### 1. Comando INSERT Básico

#### 1.1 Sintaxe Fundamental
```sql
INSERT INTO nome_tabela (coluna1, coluna2, ...)
VALUES (valor1, valor2, ...);
```

#### 1.2 Formas de Inserção

**Especificando todas as colunas**:
```sql
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, ativo, numero_membros)
VALUES (1, 'The Beatles', 'Banda britânica de rock formada em Liverpool em 1960', '1960-08-17', 'Reino Unido', TRUE, 4);
```

**Especificando apenas colunas necessárias**:
```sql
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (2, 'Caetano Veloso', 'Brasil');
```

**Sem especificar colunas (todas em ordem)**:
```sql
INSERT INTO artista 
VALUES (3, 'Bob Dylan', 'Cantor e compositor americano', '1941-05-24', 'Estados Unidos', TRUE, 1);
```

### 2. Valores Especiais

#### 2.1 Trabalhando com NULL
```sql
-- Inserindo com valores NULL explícitos
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem)
VALUES (4, 'Artista Desconhecido', NULL, NULL, 'Brasil');

-- Omitindo colunas que permitem NULL
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (5, 'Novo Artista', 'Portugal');
```

#### 2.2 Valores Padrão (DEFAULT)
```sql
-- Usando DEFAULT explícito
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, data_cadastro, ativo)
VALUES (1, 'João Silva', 'joao@email.com', '1990-05-15', DEFAULT, DEFAULT);

-- Omitindo colunas com DEFAULT
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES (2, 'Maria Santos', 'maria@email.com', '1985-12-20');
```

#### 2.3 Funções de Sistema
```sql
-- Usando funções para valores automáticos
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento, data_cadastro)
VALUES (3, 'Pedro Oliveira', 'pedro@email.com', '1992-07-08', CURRENT_TIMESTAMP);

-- Cálculos e expressões
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
VALUES (1, 'Hello', 3*60+7, 1, 1); -- 3 minutos e 7 segundos
```

### 3. Inserção de Dados no Sistema MusiStream

#### 3.1 Populando Tabela de Artistas
```sql
-- Artistas Internacionais
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, numero_membros)
VALUES 
(1, 'The Beatles', 'Banda britânica de rock que revolucionou a música popular', '1960-08-17', 'Reino Unido', 4),
(2, 'Queen', 'Banda britânica de rock conhecida por sua teatralidade', '1970-06-27', 'Reino Unido', 4),
(3, 'Bob Dylan', 'Cantor e compositor americano, Nobel de Literatura 2016', '1961-01-01', 'Estados Unidos', 1);

-- Artistas Brasileiros
INSERT INTO artista (id_artista, nome_artista, biografia, data_formacao, pais_origem, numero_membros)
VALUES 
(4, 'Caetano Veloso', 'Cantor, compositor e escritor brasileiro, ícone da MPB', '1965-01-01', 'Brasil', 1),
(5, 'Legião Urbana', 'Banda brasiliense de rock formada em 1982', '1982-03-01', 'Brasil', 4),
(6, 'Anitta', 'Cantora e compositora brasileira de pop e funk', '2010-01-01', 'Brasil', 1);
```

#### 3.2 Populando Tabela de Usuários
```sql
-- Usuários do sistema
INSERT INTO usuario (id_usuario, nome_usuario, email, data_nascimento)
VALUES 
(1, 'Ana Clara Santos', 'ana.santos@email.com', '1995-03-15'),
(2, 'Carlos Eduardo Silva', 'carlos.silva@email.com', '1988-07-22'),
(3, 'Fernanda Costa', 'fernanda.costa@email.com', '1992-11-08'),
(4, 'Roberto Mendes', 'roberto.mendes@email.com', '1985-05-30'),
(5, 'Julia Rodrigues', 'julia.rodrigues@email.com', '1998-09-12');
```

#### 3.3 Populando Álbuns (Respeitando Integridade Referencial)
```sql
-- Álbuns dos Beatles
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(1, 'Abbey Road', '1969-09-26', 17, 2887, 'album', 1),
(2, 'Sgt. Pepper''s Lonely Hearts Club Band', '1967-06-01', 13, 2389, 'album', 1),
(3, 'Let It Be', '1970-05-08', 12, 2155, 'album', 1);

-- Álbuns do Queen
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(4, 'A Night at the Opera', '1975-11-21', 12, 2583, 'album', 2),
(5, 'Bohemian Rhapsody', '1975-10-31', 1, 355, 'single', 2);

-- Álbuns Brasileiros
INSERT INTO album (id_album, titulo, data_lancamento, numero_faixas, duracao_total, tipo_album, id_artista)
VALUES 
(6, 'Tropicália', '1968-07-01', 12, 2234, 'album', 4),
(7, 'Dois', '1986-01-01', 11, 2876, 'album', 5),
(8, 'Kisses', '2019-04-05', 15, 2445, 'album', 6);
```

### 4. Tratamento de Erros Comuns

#### 4.1 Violação de Chave Primária
```sql
-- ❌ ERRO: Tentativa de inserir ID duplicado
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (1, 'Artista Duplicado', 'Brasil');
-- Resultado: ERROR: duplicate key value violates unique constraint

-- ✅ CORRETO: Usar ID único
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (7, 'Novo Artista', 'Brasil');
```

#### 4.2 Violação de Constraint NOT NULL
```sql
-- ❌ ERRO: Campo obrigatório não fornecido
INSERT INTO artista (id_artista, biografia)
VALUES (8, 'Alguma biografia');
-- Resultado: ERROR: null value in column "nome_artista" violates not-null constraint

-- ✅ CORRETO: Fornecer campos obrigatórios
INSERT INTO artista (id_artista, nome_artista, biografia)
VALUES (8, 'Nome do Artista', 'Alguma biografia');
```

#### 4.3 Violação de Constraint CHECK
```sql
-- ❌ ERRO: Violação de regra de negócio
INSERT INTO artista (id_artista, nome_artista, numero_membros)
VALUES (9, 'Banda Grande', 25);
-- Resultado: ERROR: new row violates check constraint "ck_numero_membros"

-- ✅ CORRETO: Respeitar regras de negócio
INSERT INTO artista (id_artista, nome_artista, numero_membros)
VALUES (9, 'Banda Normal', 5);
```

#### 4.4 Violação de Integridade Referencial
```sql
-- ❌ ERRO: Referência a artista inexistente
INSERT INTO album (id_album, titulo, id_artista)
VALUES (10, 'Álbum Órfão', 999);
-- Resultado: ERROR: insert or update violates foreign key constraint

-- ✅ CORRETO: Referenciar artista existente
INSERT INTO album (id_album, titulo, id_artista)
VALUES (10, 'Novo Álbum', 1);
```

### 5. Inserção Múltipla

#### 5.1 Múltiplos VALUES
```sql
-- Inserir várias músicas de uma vez
INSERT INTO musica (id_musica, titulo, duracao, numero_faixa, id_album)
VALUES 
(1, 'Come Together', 259, 1, 1),
(2, 'Something', 182, 2, 1),
(3, 'Maxwell''s Silver Hammer', 207, 3, 1),
(4, 'Oh! Darling', 206, 4, 1),
(5, 'Octopus''s Garden', 171, 5, 1);
```

#### 5.2 Vantagens da Inserção Múltipla
- Melhor performance
- Menos comandos de rede
- Transações mais eficientes
- Rollback unificado

### 6. Boas Práticas

#### 6.1 Sempre Especificar Colunas
```sql
-- ✅ BOM: Especificar colunas
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (10, 'Nome Artista', 'País');

-- ❌ EVITAR: Sem especificar colunas
INSERT INTO artista 
VALUES (10, 'Nome Artista', NULL, NULL, 'País', TRUE, 1);
```

#### 6.2 Validar Dados Antes da Inserção
```sql
-- Verificar se artista existe antes de inserir álbum
SELECT id_artista FROM artista WHERE id_artista = 5;

-- Se existe, inserir álbum
INSERT INTO album (id_album, titulo, id_artista)
VALUES (11, 'Novo Álbum da Legião', 5);
```

#### 6.3 Usar Transações para Inserções Relacionadas
```sql
-- Iniciar transação
BEGIN;

-- Inserir artista
INSERT INTO artista (id_artista, nome_artista, pais_origem)
VALUES (11, 'Novo Artista', 'Brasil');

-- Inserir álbum do artista
INSERT INTO album (id_album, titulo, id_artista)
VALUES (12, 'Primeiro Álbum', 11);

-- Confirmar transação
COMMIT;
```

### 7. Exercícios Práticos

Consulte a pasta `exercicios/` para atividades práticas de inserção de dados.

## Referências Bibliográficas

1. **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media. Capítulo 3.

2. **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing. Lições 15-16.

3. **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulo 4.

4. **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Seção INSERT.

---

**Módulo Anterior**: [06 - Alteração de Estrutura de uma Tabela](../06-alteracao-estrutura-tabela/README.md)
**Próximo Módulo**: [08 - Manipulação de Dados - Inserindo Dados (Parte II)](../08-manipulacao-dados-inserindo-parte-ii/README.md)