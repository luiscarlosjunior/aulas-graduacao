# Módulo 06 - Alteração de Estrutura de uma Tabela

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Utilizar comandos DDL para modificar estruturas de tabelas existentes
- Adicionar, remover e modificar colunas
- Gerenciar constraints em tabelas existentes
- Renomear tabelas e colunas
- Compreender o impacto das alterações em dados existentes
- Aplicar boas práticas na evolução de esquemas de banco

## Conteúdo Teórico

### 1. Comando ALTER TABLE

#### 1.1 Sintaxe Básica
```sql
ALTER TABLE nome_tabela 
[ADD | MODIFY | DROP] especificacao;
```

#### 1.2 Operações Principais
- **ADD**: Adicionar colunas ou constraints
- **MODIFY**: Modificar colunas existentes
- **DROP**: Remover colunas ou constraints
- **RENAME**: Renomear elementos da tabela

### 2. Adicionando Colunas

#### 2.1 Adição Simples
```sql
-- Adicionar uma nova coluna
ALTER TABLE artista 
ADD data_ultimo_album DATE;

-- Adicionar coluna com valor padrão
ALTER TABLE usuario 
ADD nivel_vip INTEGER DEFAULT 1;

-- Adicionar múltiplas colunas
ALTER TABLE musica 
ADD (
    popularidade INTEGER DEFAULT 0,
    idade_recomendada INTEGER,
    disponivel_offline CHAR(1) DEFAULT 'N'
);
```

#### 2.2 Adição com Constraints
```sql
-- Adicionar coluna com constraint
ALTER TABLE album 
ADD preco_digital NUMBER(8,2) 
CONSTRAINT ck_preco_digital CHECK (preco_digital >= 0);

-- Adicionar coluna NOT NULL (quando tabela tem dados)
ALTER TABLE usuario 
ADD cpf VARCHAR2(14) DEFAULT 'N/A' NOT NULL;
```

### 3. Modificando Colunas

#### 3.1 Alteração de Tipo de Dados
```sql
-- Aumentar tamanho de campo
ALTER TABLE artista 
MODIFY nome_artista VARCHAR2(150);

-- Alterar tipo (cuidado com conversões)
ALTER TABLE musica 
MODIFY duracao NUMBER(8,2); -- de INTEGER para NUMBER

-- Alterar constraint NOT NULL
ALTER TABLE usuario 
MODIFY telefone VARCHAR2(20) NOT NULL;
```

#### 3.2 Alteração de Valores Padrão
```sql
-- Definir novo valor padrão
ALTER TABLE playlist 
MODIFY publica DEFAULT 'S';

-- Remover valor padrão
ALTER TABLE usuario 
MODIFY pais DEFAULT NULL;
```

### 4. Removendo Colunas

#### 4.1 Remoção Simples
```sql
-- Remover uma coluna
ALTER TABLE artista 
DROP COLUMN website;

-- Remover múltiplas colunas
ALTER TABLE usuario 
DROP (telefone_secundario, endereco_completo);
```

#### 4.2 Remoção com Dados
```sql
-- Marcar coluna como UNUSED (mais rápido)
ALTER TABLE historico_reproducao 
SET UNUSED COLUMN detalhes_sessao;

-- Remover colunas marcadas como UNUSED
ALTER TABLE historico_reproducao 
DROP UNUSED COLUMNS;
```

### 5. Gerenciamento de Constraints

#### 5.1 Adicionando Constraints
```sql
-- Adicionar chave primária
ALTER TABLE tabela_temp 
ADD CONSTRAINT pk_tabela_temp PRIMARY KEY (id);

-- Adicionar chave estrangeira
ALTER TABLE album 
ADD CONSTRAINT fk_album_gravadora 
FOREIGN KEY (id_gravadora) REFERENCES gravadora(id_gravadora);

-- Adicionar constraint CHECK
ALTER TABLE musica 
ADD CONSTRAINT ck_duracao_positiva CHECK (duracao > 0);

-- Adicionar constraint UNIQUE
ALTER TABLE usuario 
ADD CONSTRAINT uq_usuario_cpf UNIQUE (cpf);
```

#### 5.2 Removendo Constraints
```sql
-- Remover constraint por nome
ALTER TABLE usuario 
DROP CONSTRAINT ck_usuario_ativo;

-- Remover chave primária
ALTER TABLE tabela_temp 
DROP PRIMARY KEY;

-- Remover chave estrangeira
ALTER TABLE album 
DROP CONSTRAINT fk_album_gravadora;
```

#### 5.3 Desabilitando/Habilitando Constraints
```sql
-- Desabilitar constraint
ALTER TABLE album 
DISABLE CONSTRAINT fk_album_artista;

-- Habilitar constraint
ALTER TABLE album 
ENABLE CONSTRAINT fk_album_artista;

-- Desabilitar todas as constraints de uma tabela
ALTER TABLE musica 
DISABLE ALL TRIGGERS;
```

### 6. Renomeação de Elementos

#### 6.1 Renomear Tabela
```sql
-- Renomear tabela
ALTER TABLE artista 
RENAME TO musico;

-- Ou usando RENAME específico
RENAME artista TO musico;
```

#### 6.2 Renomear Colunas
```sql
-- Renomear coluna
ALTER TABLE usuario 
RENAME COLUMN nome_usuario TO nome_completo;
```

#### 6.3 Renomear Constraints
```sql
-- Renomear constraint
ALTER TABLE album 
RENAME CONSTRAINT ck_ano_lancamento TO ck_album_ano_valido;
```

### 7. Cenários Práticos do Sistema MusiStream

#### 7.1 Evolução da Tabela de Usuários
```sql
-- Fase 1: Adicionar campos de rede social
ALTER TABLE usuario 
ADD (
    instagram VARCHAR2(50),
    twitter VARCHAR2(50),
    facebook VARCHAR2(100)
);

-- Fase 2: Adicionar sistema de pontuação
ALTER TABLE usuario 
ADD pontos_fidelidade INTEGER DEFAULT 0 
CONSTRAINT ck_pontos_positivos CHECK (pontos_fidelidade >= 0);

-- Fase 3: Modificar estrutura do email para aceitar múltiplos
ALTER TABLE usuario 
MODIFY email VARCHAR2(200);

-- Fase 4: Adicionar campo de preferências (JSON)
ALTER TABLE usuario 
ADD preferencias_json CLOB;
```

#### 7.2 Melhoria na Tabela de Músicas
```sql
-- Adicionar metadados de qualidade
ALTER TABLE musica 
ADD (
    formato_audio VARCHAR2(10) DEFAULT 'MP3',
    taxa_amostragem INTEGER DEFAULT 44100,
    qualidade_masterizada CHAR(1) DEFAULT 'N',
    versao_remasterizada CHAR(1) DEFAULT 'N'
);

-- Adicionar constraints para novos campos
ALTER TABLE musica 
ADD CONSTRAINT ck_formato_audio 
CHECK (formato_audio IN ('MP3', 'FLAC', 'WAV', 'AAC'));

ALTER TABLE musica 
ADD CONSTRAINT ck_taxa_amostragem 
CHECK (taxa_amostragem IN (22050, 44100, 48000, 96000));
```

### 8. Considerações Importantes

#### 8.1 Impacto em Dados Existentes
- Adicionar colunas NOT NULL requer valor padrão
- Modificar tipos pode causar perda de dados
- Constraints podem falhar se dados existentes as violam

#### 8.2 Performance
- Adicionar colunas é geralmente rápido
- Modificar tipos pode ser lento em tabelas grandes
- Remover colunas pode ser demorado

#### 8.3 Estratégias de Migração
```sql
-- Estratégia segura para mudança de tipo
-- 1. Adicionar nova coluna
ALTER TABLE musica ADD duracao_nova NUMBER(8,2);

-- 2. Migrar dados
UPDATE musica SET duracao_nova = duracao;

-- 3. Verificar dados
SELECT COUNT(*) FROM musica WHERE duracao_nova IS NULL;

-- 4. Remover coluna antiga e renomear nova
ALTER TABLE musica DROP COLUMN duracao;
ALTER TABLE musica RENAME COLUMN duracao_nova TO duracao;
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Oracle Corporation** (2021). *Oracle Database SQL Language Reference*. Capítulo sobre ALTER TABLE.
- **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson. Capítulo sobre Schema Evolution.
- **Kline, K., Kline, D., & Hunt, B.** (2008). *SQL in a Nutshell*. 3rd Edition. O'Reilly Media.

## Próximos Passos

No próximo módulo (07), aprofundaremos o estudo de **Manipulação de Dados - Inserindo Dados (Parte I)**, explorando diferentes formas de inserção de dados.