# Diagrama Entidade-Relacionamento (ER) - MusiStream

## 📊 Representação Visual dos Relacionamentos

```
                               SISTEMA MUSISTREAM
                          Diagrama Entidade-Relacionamento
                               
    ┌─────────────┐         ┌─────────────┐         ┌─────────────┐
    │   USUARIO   │ 1     N │  PLAYLIST   │ N     M │   MUSICA    │
    │─────────────│◄────────│─────────────│◄────────│─────────────│
    │id_usuario PK│         │id_playlist  │         │id_musica PK │
    │nome_usuario │         │nome_playlist│         │titulo       │
    │email (UQ)   │         │publica      │         │duracao      │
    │senha        │         │id_usuario FK│         │numero_faixa │
    │data_nasc    │         │...          │         │id_album FK  │
    │pais         │         └─────────────┘         │id_genero FK │
    │ativo        │                │                │...          │
    │...          │                │                └─────────────┘
    └─────────────┘                │                       │
           │ 1                     │                       │ N
           │                       │                       │
           │ N                     │                       │ 1
    ┌─────────────┐                │                ┌─────────────┐
    │ ASSINATURA  │                │                │    ALBUM    │
    │─────────────│                │                │─────────────│
    │id_assin. PK │                │                │id_album PK  │
    │data_inicio  │                │                │titulo       │
    │data_fim     │                │                │data_lanc.   │
    │status       │                │                │numero_faixas│
    │id_usuario FK│                │                │id_artista FK│
    │id_tipo FK   │                │                │...          │
    │...          │                │                └─────────────┘
    └─────────────┘                │                       │ N
           │ N                     │                       │
           │                       │                       │ 1
           │ 1              ┌─────────────┐         ┌─────────────┐
    ┌─────────────┐         │PLAYLIST_    │         │   ARTISTA   │
    │TIPO_ASSIN.  │         │MUSICA       │         │─────────────│
    │─────────────│         │─────────────│         │id_artista PK│
    │id_tipo PK   │         │id_playlist  │         │nome_artista │
    │nome_plano   │         │id_musica    │         │nome_real    │
    │preco_mensal │         │ordem_musica │         │data_nasc    │
    │downloads    │         │data_adicao  │         │pais_origem  │
    │pulos_ilim.  │         │...          │         │biografia    │
    │sem_anuncios │         └─────────────┘         │ativo        │
    │...          │                                 │...          │
    └─────────────┘                                 └─────────────┘
    
    ┌─────────────┐         ┌─────────────┐
    │   GENERO    │ 1     N │HISTORICO_   │
    │─────────────│◄────────│REPRODUCAO   │
    │id_genero PK │         │─────────────│
    │nome_genero  │         │id_hist. PK  │
    │descricao    │         │data_reprod. │
    │data_criacao │         │duracao_ouv. │
    │...          │         │dispositivo  │
    └─────────────┘         │localizacao  │
                            │id_usuario FK│◄──┐
                            │id_musica FK │◄─┐│
                            │...          │  ││
                            └─────────────┘  ││
                                   N         ││
                                            1││
                               ┌─────────────┐││
                               │             │││
                               └─────────────┘│┘
                                             │
                               ┌─────────────┐┘
                               │             │
                               └─────────────┘
```

## 🔗 Relacionamentos Detalhados

### 1. USUARIO ↔ PLAYLIST (1:N)
- **Cardinalidade**: Um usuário pode criar várias playlists
- **Tipo**: Identificação
- **Chave**: `playlist.id_usuario` → `usuario.id_usuario`
- **Regra**: Toda playlist deve ter um proprietário

### 2. PLAYLIST ↔ MUSICA (N:M)
- **Cardinalidade**: Uma playlist pode ter várias músicas; uma música pode estar em várias playlists
- **Tipo**: Associação com atributos
- **Entidade Associativa**: `playlist_musica`
- **Atributos**: ordem_musica, data_adicao
- **Chaves**: Chave composta (id_playlist, id_musica)

### 3. USUARIO ↔ ASSINATURA (1:N)
- **Cardinalidade**: Um usuário pode ter várias assinaturas (histórico)
- **Tipo**: Identificação
- **Chave**: `assinatura.id_usuario` → `usuario.id_usuario`
- **Regra**: Manter histórico completo de assinaturas

### 4. TIPO_ASSINATURA ↔ ASSINATURA (1:N)
- **Cardinalidade**: Um tipo pode ser usado em várias assinaturas
- **Tipo**: Referência
- **Chave**: `assinatura.id_tipo_assinatura` → `tipo_assinatura.id_tipo_assinatura`
- **Regra**: Preservar tipos mesmo quando inativados

### 5. ARTISTA ↔ ALBUM (1:N)
- **Cardinalidade**: Um artista pode ter vários álbuns
- **Tipo**: Identificação
- **Chave**: `album.id_artista` → `artista.id_artista`
- **Regra**: Todo álbum deve ter um artista principal

### 6. ALBUM ↔ MUSICA (1:N)
- **Cardinalidade**: Um álbum pode ter várias músicas
- **Tipo**: Identificação (dependência total)
- **Chave**: `musica.id_album` → `album.id_album`
- **Regra**: Música não existe sem álbum; número da faixa único por álbum

### 7. GENERO ↔ MUSICA (1:N)
- **Cardinalidade**: Um gênero pode categorizar várias músicas
- **Tipo**: Referência
- **Chave**: `musica.id_genero` → `genero.id_genero`
- **Regra**: Gênero é opcional para música

### 8. USUARIO ↔ HISTORICO_REPRODUCAO (1:N)
- **Cardinalidade**: Um usuário pode ter várias reproduções
- **Tipo**: Identificação
- **Chave**: `historico_reproducao.id_usuario` → `usuario.id_usuario`
- **Regra**: Log completo de atividades do usuário

### 9. MUSICA ↔ HISTORICO_REPRODUCAO (1:N)
- **Cardinalidade**: Uma música pode ter várias reproduções
- **Tipo**: Referência
- **Chave**: `historico_reproducao.id_musica` → `musica.id_musica`
- **Regra**: Rastrear popularidade das músicas

## 📋 Entidades com Atributos Principais

### Entidades Fortes (Independentes)
```
USUARIO
├── id_usuario (PK)
├── nome_usuario
├── email (UQ)
├── senha
└── data_nascimento

ARTISTA  
├── id_artista (PK)
├── nome_artista
├── nome_real
├── data_nascimento
└── pais_origem

GENERO
├── id_genero (PK)
├── nome_genero (UQ)
└── descricao

TIPO_ASSINATURA
├── id_tipo_assinatura (PK)
├── nome_plano (UQ)
├── preco_mensal
├── downloads_offline
├── pulos_ilimitados
└── sem_anuncios
```

### Entidades Fracas (Dependentes)
```
ALBUM (depende de ARTISTA)
├── id_album (PK)
├── titulo
├── data_lancamento
├── id_artista (FK)
└── tipo_album

MUSICA (depende de ALBUM)
├── id_musica (PK)
├── titulo
├── duracao
├── numero_faixa
├── id_album (FK)
└── id_genero (FK)

PLAYLIST (depende de USUARIO)
├── id_playlist (PK)
├── nome_playlist
├── publica
├── id_usuario (FK)
└── total_musicas

ASSINATURA (depende de USUARIO e TIPO_ASSINATURA)
├── id_assinatura (PK)
├── data_inicio
├── status_assinatura
├── id_usuario (FK)
└── id_tipo_assinatura (FK)
```

### Entidades Associativas
```
PLAYLIST_MUSICA (relaciona PLAYLIST e MUSICA)
├── id_playlist (PK, FK)
├── id_musica (PK, FK)
├── ordem_musica
└── data_adicao

HISTORICO_REPRODUCAO (relaciona USUARIO e MUSICA)
├── id_historico (PK)
├── data_reproducao
├── duracao_ouvida
├── dispositivo
├── id_usuario (FK)
└── id_musica (FK)
```

## 🎯 Constraints e Regras de Integridade

### Integridade de Entidade
- Todas as entidades têm chave primária definida
- Chaves primárias são NOT NULL e UNIQUE

### Integridade Referencial
- Todas as chaves estrangeiras referenciam chaves primárias válidas
- Políticas de CASCADE onde apropriado (álbum → músicas)
- Políticas de RESTRICT para preservar histórico (usuário → histórico)

### Integridade de Domínio
- Campos com CHECK constraints para valores válidos
- Emails com formato validado
- Durações com valores positivos
- Status com valores enumerados

### Integridade Semântica
- Data de fim maior que data de início (assinaturas)
- Idade mínima para usuários (13 anos)
- Duração máxima para músicas (1 hora)
- Número de faixa único por álbum

## 📊 Cardinalidades Resumidas

| Relacionamento | Cardinalidade | Tipo |
|---|---|---|
| USUARIO → PLAYLIST | 1:N | Identificação |
| USUARIO → ASSINATURA | 1:N | Identificação |
| USUARIO → HISTORICO | 1:N | Identificação |
| ARTISTA → ALBUM | 1:N | Identificação |
| ALBUM → MUSICA | 1:N | Identificação |
| GENERO → MUSICA | 1:N | Referência |
| TIPO_ASSINATURA → ASSINATURA | 1:N | Referência |
| PLAYLIST ↔ MUSICA | N:M | Associação |
| MUSICA → HISTORICO | 1:N | Referência |

---

*Este diagrama representa a estrutura lógica completa do sistema MusiStream, servindo como base para implementação física em qualquer SGBD relacional.*