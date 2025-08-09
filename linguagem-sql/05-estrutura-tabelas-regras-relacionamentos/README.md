# Módulo 05 - Estrutura das Tabelas, Regras e Relacionamentos

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender e implementar constraints (restrições) em tabelas
- Definir chaves primárias e estrangeiras
- Aplicar regras de integridade referencial
- Configurar constraints de domínio e verificação
- Implementar relacionamentos entre tabelas
- Utilizar índices para otimização

## Conteúdo Teórico

### 1. Constraints (Restrições)

#### 1.1 Tipos de Constraints

**PRIMARY KEY** - Chave Primária:
```sql
CREATE TABLE artista (
    id_artista INTEGER PRIMARY KEY,
    nome_artista VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) UNIQUE
);
```

**FOREIGN KEY** - Chave Estrangeira:
```sql
CREATE TABLE album (
    id_album INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    id_artista INTEGER,
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) 
        REFERENCES artista(id_artista)
);
```

**NOT NULL** - Não Nulo:
```sql
CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY,
    nome_usuario VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) NOT NULL
);
```

**UNIQUE** - Único:
```sql
CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY,
    nome_usuario VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) UNIQUE,
    cpf VARCHAR2(14) UNIQUE
);
```

**CHECK** - Verificação:
```sql
CREATE TABLE musica (
    id_musica INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    duracao INTEGER CHECK (duracao > 0),
    classificacao_etaria INTEGER CHECK (classificacao_etaria IN (0, 10, 12, 14, 16, 18))
);
```

#### 1.2 Constraints Nomeadas

```sql
CREATE TABLE assinatura (
    id_assinatura INTEGER CONSTRAINT pk_assinatura PRIMARY KEY,
    tipo_plano VARCHAR2(20) CONSTRAINT nn_tipo_plano NOT NULL,
    preco NUMBER(8,2) CONSTRAINT ck_preco_positivo CHECK (preco > 0),
    id_usuario INTEGER CONSTRAINT fk_assinatura_usuario REFERENCES usuario(id_usuario)
);
```

### 2. Relacionamentos Entre Tabelas

#### 2.1 Relacionamento Um para Muitos (1:N)

```sql
-- Um artista pode ter muitos álbuns
CREATE TABLE artista (
    id_artista INTEGER PRIMARY KEY,
    nome_artista VARCHAR2(100) NOT NULL
);

CREATE TABLE album (
    id_album INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    id_artista INTEGER NOT NULL,
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista)
);
```

#### 2.2 Relacionamento Muitos para Muitos (N:N)

```sql
-- Uma playlist pode ter muitas músicas
-- Uma música pode estar em muitas playlists
CREATE TABLE playlist_musica (
    id_playlist INTEGER,
    id_musica INTEGER,
    data_adicao DATE DEFAULT SYSDATE,
    ordem_reproducao INTEGER,
    PRIMARY KEY (id_playlist, id_musica),
    FOREIGN KEY (id_playlist) REFERENCES playlist(id_playlist),
    FOREIGN KEY (id_musica) REFERENCES musica(id_musica)
);
```

#### 2.3 Relacionamento Um para Um (1:1)

```sql
-- Um usuário tem um perfil de preferências
CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY,
    nome_usuario VARCHAR2(100) NOT NULL
);

CREATE TABLE perfil_preferencias (
    id_usuario INTEGER PRIMARY KEY,
    genero_favorito VARCHAR2(50),
    volume_padrao INTEGER DEFAULT 50,
    qualidade_audio VARCHAR2(20) DEFAULT 'ALTA',
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
```

### 3. Ações de Integridade Referencial

#### 3.1 ON DELETE CASCADE
```sql
CREATE TABLE album (
    id_album INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    id_artista INTEGER,
    FOREIGN KEY (id_artista) REFERENCES artista(id_artista) ON DELETE CASCADE
);
```

#### 3.2 ON DELETE SET NULL
```sql
CREATE TABLE musica (
    id_musica INTEGER PRIMARY KEY,
    titulo VARCHAR2(200) NOT NULL,
    id_album INTEGER,
    FOREIGN KEY (id_album) REFERENCES album(id_album) ON DELETE SET NULL
);
```

### 4. Índices para Performance

#### 4.1 Índices Simples
```sql
-- Índice para otimizar buscas por nome de artista
CREATE INDEX idx_artista_nome ON artista(nome_artista);

-- Índice para otimizar buscas por email de usuário
CREATE INDEX idx_usuario_email ON usuario(email);
```

#### 4.2 Índices Compostos
```sql
-- Índice composto para otimizar consultas por artista e ano
CREATE INDEX idx_album_artista_ano ON album(id_artista, ano_lancamento);
```

## Sistema MusiStream - Implementação Completa

### Estrutura com Todas as Constraints

```sql
-- Tabela de Gêneros Musicais
CREATE TABLE genero (
    id_genero INTEGER CONSTRAINT pk_genero PRIMARY KEY,
    nome_genero VARCHAR2(50) CONSTRAINT nn_genero_nome NOT NULL 
                              CONSTRAINT uq_genero_nome UNIQUE,
    descricao VARCHAR2(200)
);

-- Tabela de Artistas
CREATE TABLE artista (
    id_artista INTEGER CONSTRAINT pk_artista PRIMARY KEY,
    nome_artista VARCHAR2(100) CONSTRAINT nn_artista_nome NOT NULL,
    biografia CLOB,
    data_formacao DATE,
    pais_origem VARCHAR2(50),
    ativo CHAR(1) DEFAULT 'S' CONSTRAINT ck_artista_ativo CHECK (ativo IN ('S', 'N')),
    numero_membros INTEGER CONSTRAINT ck_numero_membros CHECK (numero_membros > 0)
);

-- Tabela de Álbuns
CREATE TABLE album (
    id_album INTEGER CONSTRAINT pk_album PRIMARY KEY,
    titulo VARCHAR2(200) CONSTRAINT nn_album_titulo NOT NULL,
    ano_lancamento INTEGER CONSTRAINT ck_ano_lancamento 
                           CHECK (ano_lancamento BETWEEN 1900 AND EXTRACT(YEAR FROM SYSDATE)),
    numero_faixas INTEGER CONSTRAINT ck_numero_faixas CHECK (numero_faixas > 0),
    duracao_total INTEGER,
    id_artista INTEGER CONSTRAINT nn_album_artista NOT NULL,
    id_genero INTEGER,
    CONSTRAINT fk_album_artista FOREIGN KEY (id_artista) REFERENCES artista(id_artista),
    CONSTRAINT fk_album_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero)
);

-- Tabela de Usuários
CREATE TABLE usuario (
    id_usuario INTEGER CONSTRAINT pk_usuario PRIMARY KEY,
    nome_usuario VARCHAR2(100) CONSTRAINT nn_usuario_nome NOT NULL,
    email VARCHAR2(150) CONSTRAINT nn_usuario_email NOT NULL 
                        CONSTRAINT uq_usuario_email UNIQUE,
    data_nascimento DATE,
    data_cadastro DATE DEFAULT SYSDATE,
    ativo CHAR(1) DEFAULT 'S' CONSTRAINT ck_usuario_ativo CHECK (ativo IN ('S', 'N')),
    pais VARCHAR2(50)
);
```

## Exercícios Práticos

Consulte a pasta `exercicios` para atividades práticas que reforçam os conceitos apresentados.

## Referências Bibliográficas

- **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson. Capítulos 3-5.
- **Date, C.J.** (2012). *SQL and Relational Theory*. 2nd Edition. O'Reilly Media. Capítulos 6-8.
- **Silberschatz, A., Galvin, P. B., & Gagne, G.** (2018). *Operating System Concepts*. 10th Edition. John Wiley & Sons. Capítulos sobre integridade de dados.

## Próximos Passos

No próximo módulo (06), aprenderemos sobre **Alteração de Estrutura de uma Tabela**, explorando comandos DDL para modificar tabelas existentes.