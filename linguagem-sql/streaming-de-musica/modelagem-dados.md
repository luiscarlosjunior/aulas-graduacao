# Modelagem de Dados - Sistema MusiStream

## 📋 Visão Geral

O **MusiStream** é um sistema de streaming de música que replica funcionalidades de plataformas como Spotify, Deezer e Apple Music. Esta documentação apresenta a modelagem completa do banco de dados.

## 🎯 Objetivos do Sistema

- Gerenciar usuários e suas preferências musicais
- Organizar catálogo musical (artistas, álbuns, músicas)
- Controlar assinaturas e planos de pagamento
- Permitir criação e compartilhamento de playlists
- Rastrear histórico de reprodução para recomendações
- Categorizar música por gêneros

## 📊 Modelo Conceitual

### Entidades Principais

#### 👤 USUARIO
**Descrição**: Representa os usuários cadastrados na plataforma

**Atributos**:
- `id_usuario` (PK): Identificador único
- `nome_usuario`: Nome de exibição
- `email`: Endereço de email (único)
- `senha`: Senha criptografada
- `data_nascimento`: Data de nascimento
- `pais`: País de origem
- `data_cadastro`: Data de registro na plataforma
- `ultimo_acesso`: Último login
- `ativo`: Status da conta (S/N)

**Regras de Negócio**:
- Email deve ser único no sistema
- Idade mínima de 13 anos
- Email deve ter formato válido

#### 🎤 ARTISTA
**Descrição**: Representa artistas, bandas e intérpretes

**Atributos**:
- `id_artista` (PK): Identificador único
- `nome_artista`: Nome artístico
- `nome_real`: Nome real (opcional)
- `data_nascimento`: Data de nascimento
- `pais_origem`: País de origem
- `biografia`: Biografia do artista
- `data_inicio_carreira`: Início da carreira
- `ativo`: Se ainda está ativo (S/N)
- `website`: Site oficial

**Regras de Negócio**:
- Data de início da carreira não pode ser anterior ao nascimento
- Nome artístico é obrigatório

#### 🎭 GENERO
**Descrição**: Categorias musicais para classificação

**Atributos**:
- `id_genero` (PK): Identificador único
- `nome_genero`: Nome do gênero (único)
- `descricao`: Descrição do gênero
- `data_criacao`: Data de criação do gênero

**Regras de Negócio**:
- Nome do gênero deve ser único
- Mínimo de 2 caracteres no nome

#### 💿 ALBUM
**Descrição**: Representa álbuns, EPs, singles e compilações

**Atributos**:
- `id_album` (PK): Identificador único
- `titulo`: Título do álbum
- `data_lancamento`: Data de lançamento
- `numero_faixas`: Quantidade de faixas
- `duracao_total`: Duração total em segundos
- `capa_url`: URL da capa do álbum
- `tipo_album`: Tipo (ALBUM, EP, SINGLE, COMPILACAO)
- `id_artista` (FK): Referência ao artista

**Regras de Negócio**:
- Número de faixas deve ser positivo
- Duração total deve ser positiva
- Todo álbum deve ter um artista

#### 🎵 MUSICA
**Descrição**: Representa as faixas musicais individuais

**Atributos**:
- `id_musica` (PK): Identificador único
- `titulo`: Título da música
- `duracao`: Duração em segundos
- `numero_faixa`: Número da faixa no álbum
- `letra`: Letra da música (opcional)
- `arquivo_url`: URL do arquivo de áudio
- `total_reproducoes`: Contador de reproduções
- `data_upload`: Data de upload na plataforma
- `id_album` (FK): Referência ao álbum
- `id_genero` (FK): Referência ao gênero

**Regras de Negócio**:
- Duração deve ser positiva e máximo 1 hora
- Número da faixa deve ser único dentro do álbum
- Total de reproduções não pode ser negativo

#### 📱 PLAYLIST
**Descrição**: Listas de músicas criadas pelos usuários

**Atributos**:
- `id_playlist` (PK): Identificador único
- `nome_playlist`: Nome da playlist
- `descricao`: Descrição da playlist
- `publica`: Se é pública ou privada (S/N)
- `data_criacao`: Data de criação
- `data_atualizacao`: Última atualização
- `total_musicas`: Contador de músicas
- `duracao_total`: Duração total em segundos
- `id_usuario` (FK): Referência ao usuário criador

**Regras de Negócio**:
- Todo usuário pode ter múltiplas playlists
- Contadores são atualizados automaticamente

#### 💳 TIPO_ASSINATURA
**Descrição**: Tipos de planos disponíveis na plataforma

**Atributos**:
- `id_tipo_assinatura` (PK): Identificador único
- `nome_plano`: Nome do plano (único)
- `preco_mensal`: Preço mensal
- `qualidade_audio`: Qualidade do áudio
- `downloads_offline`: Permite downloads (S/N)
- `pulos_ilimitados`: Pulos ilimitados (S/N)
- `sem_anuncios`: Sem anúncios (S/N)
- `descricao`: Descrição do plano
- `ativo`: Se o plano está ativo (S/N)

**Regras de Negócio**:
- Nome do plano deve ser único
- Preço não pode ser negativo

#### 📄 ASSINATURA
**Descrição**: Assinaturas ativas/históricas dos usuários

**Atributos**:
- `id_assinatura` (PK): Identificador único
- `data_inicio`: Data de início da assinatura
- `data_fim`: Data de fim (NULL se ativa)
- `status_assinatura`: Status (ATIVA, CANCELADA, SUSPENSA, EXPIRADA)
- `metodo_pagamento`: Forma de pagamento
- `valor_pago`: Valor efetivamente pago
- `data_ultimo_pagamento`: Data do último pagamento
- `renovacao_automatica`: Renovação automática (S/N)
- `id_usuario` (FK): Referência ao usuário
- `id_tipo_assinatura` (FK): Referência ao tipo

**Regras de Negócio**:
- Data fim deve ser posterior à data início
- Valor pago não pode ser negativo

#### 🔄 PLAYLIST_MUSICA
**Descrição**: Relacionamento N:M entre playlists e músicas

**Atributos**:
- `id_playlist` (PK, FK): Referência à playlist
- `id_musica` (PK, FK): Referência à música
- `ordem_musica`: Ordem na playlist
- `data_adicao`: Data que foi adicionada

**Regras de Negócio**:
- Uma música pode estar em múltiplas playlists
- Ordem deve ser única dentro da playlist

#### 📊 HISTORICO_REPRODUCAO
**Descrição**: Log de todas as reproduções de músicas

**Atributos**:
- `id_historico` (PK): Identificador único
- `data_reproducao`: Timestamp da reprodução
- `duracao_ouvida`: Segundos efetivamente ouvidos
- `dispositivo`: Dispositivo utilizado
- `localizacao`: Localização geográfica
- `qualidade_reproducao`: Qualidade utilizada
- `origem_reproducao`: Origem (playlist, busca, etc.)
- `id_usuario` (FK): Referência ao usuário
- `id_musica` (FK): Referência à música

**Regras de Negócio**:
- Duração ouvida não pode ser negativa
- Cada reprodução gera um registro único

## 🔗 Modelo Lógico - Relacionamentos

### Relacionamentos 1:N (Um para Muitos)

```
USUARIO (1) ──────── (N) PLAYLIST
│
├── Um usuário pode criar várias playlists
└── Uma playlist pertence a apenas um usuário

USUARIO (1) ──────── (N) ASSINATURA
│
├── Um usuário pode ter várias assinaturas (histórico)
└── Uma assinatura pertence a apenas um usuário

USUARIO (1) ──────── (N) HISTORICO_REPRODUCAO
│
├── Um usuário pode ter várias reproduções
└── Uma reprodução pertence a apenas um usuário

ARTISTA (1) ──────── (N) ALBUM
│
├── Um artista pode ter vários álbuns
└── Um álbum pertence a apenas um artista

ALBUM (1) ──────── (N) MUSICA
│
├── Um álbum pode ter várias músicas
└── Uma música pertence a apenas um álbum

GENERO (1) ──────── (N) MUSICA
│
├── Um gênero pode categorizar várias músicas
└── Uma música pertence a apenas um gênero

TIPO_ASSINATURA (1) ──────── (N) ASSINATURA
│
├── Um tipo pode ter várias assinaturas
└── Uma assinatura pertence a apenas um tipo

MUSICA (1) ──────── (N) HISTORICO_REPRODUCAO
│
├── Uma música pode ter várias reproduções
└── Uma reprodução se refere a apenas uma música
```

### Relacionamentos N:M (Muitos para Muitos)

```
PLAYLIST (N) ────────── (M) MUSICA
     │                      │
     │                      │
     └──── PLAYLIST_MUSICA ──┘
           │
           ├── Uma playlist pode ter várias músicas
           ├── Uma música pode estar em várias playlists
           └── Tabela associativa armazena ordem e data
```

## 📐 Modelo Físico - Características Técnicas

### Estratégias de Chaves

**Chaves Primárias**:
- Todas as entidades principais usam chaves surrogate (NUMBER)
- Sequences Oracle garantem unicidade e performance

**Chaves Estrangeiras**:
- Todas com integridade referencial
- CASCADE DELETE onde apropriado (ex: excluir álbum remove músicas)

**Chaves Únicas**:
- `email` na tabela USUARIO
- `nome_genero` na tabela GENERO
- `nome_plano` na tabela TIPO_ASSINATURA
- `(id_album, numero_faixa)` na tabela MUSICA (composta)
- `(id_playlist, ordem_musica)` na tabela PLAYLIST_MUSICA (composta)

### Constraints de Validação

**Verificações de Domínio**:
```sql
-- Idade mínima
CHECK (data_nascimento <= SYSDATE - INTERVAL '13' YEAR)

-- Email válido
CHECK (email LIKE '%_@_%._%')

-- Valores positivos
CHECK (duracao > 0 AND duracao <= 3600)
CHECK (preco_mensal >= 0)
CHECK (total_reproducoes >= 0)

-- Valores enumerados
CHECK (ativo IN ('S', 'N'))
CHECK (tipo_album IN ('ALBUM', 'EP', 'SINGLE', 'COMPILACAO'))
CHECK (status_assinatura IN ('ATIVA', 'CANCELADA', 'SUSPENSA', 'EXPIRADA'))
```

### Índices de Performance

**Índices Simples**:
- `idx_usuario_email` - Para login
- `idx_musica_reproducoes` - Para rankings
- `idx_hist_data` - Para relatórios por período

**Índices Compostos**:
- `idx_hist_usuario_data` - Para histórico por usuário
- `idx_playlist_usuario` - Para playlists do usuário

### Triggers Automatizados

**Manutenção de Contadores**:
- Atualiza `total_reproducoes` em MUSICA
- Atualiza `total_musicas` e `duracao_total` em PLAYLIST

**Auditoria**:
- Atualiza `data_atualizacao` em PLAYLIST
- Registra `ultimo_acesso` em USUARIO

### Views Estratégicas

**Views de Consulta**:
- `vw_musicas_completas` - Informações join de música/álbum/artista
- `vw_top_musicas` - Ranking de reproduções
- `vw_stats_usuarios` - Estatísticas por usuário

## 📈 Considerações de Escalabilidade

### Volume Estimado
- **Usuários**: Milhões de registros
- **Histórico**: Bilhões de registros (crescimento contínuo)
- **Músicas**: Dezenas de milhões de registros

### Estratégias de Otimização
1. **Particionamento**: `historico_reproducao` por data
2. **Arquivamento**: Dados antigos para storage separado
3. **Cache**: Top lists e recomendações
4. **Desnormalização**: Contadores pré-calculados

## 🔐 Segurança e Compliance

### Proteção de Dados
- Senhas armazenadas como hash
- Dados pessoais com acesso controlado
- Logs de auditoria para alterações

### Conformidade LGPD/GDPR
- Estrutura preparada para "direito ao esquecimento"
- Campos de consentimento podem ser adicionados
- Histórico preservado para análises anonimizadas

---

*Esta modelagem foi desenvolvida seguindo as melhores práticas de design de banco de dados, priorizando performance, integridade e escalabilidade.*