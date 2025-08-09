-- =====================================================
-- MODELO CONCEITUAL INICIAL - SISTEMA MUSISTREAM
-- Versão: 1.0
-- Descrição: Estrutura básica das entidades principais
-- =====================================================

-- Este arquivo apresenta a estrutura conceitual das principais
-- entidades do sistema MusiStream para fins didáticos

/*
ENTIDADE: USUARIO
Descrição: Representa os usuários da plataforma de streaming
Atributos:
- id_usuario: Identificador único (Chave Primária)
- nome_usuario: Nome completo do usuário
- email: Endereço de email único
- data_nascimento: Data de nascimento para validação de idade
- data_cadastro: Timestamp do cadastro na plataforma
*/

/*
ENTIDADE: ARTISTA  
Descrição: Representa artistas individuais ou bandas
Atributos:
- id_artista: Identificador único (Chave Primária)
- nome_artista: Nome artístico ou da banda
- biografia: Texto descritivo sobre o artista
- data_formacao: Data de início da carreira/banda
- pais_origem: País de origem do artista
*/

/*
ENTIDADE: ALBUM
Descrição: Representa álbuns musicais
Atributos:
- id_album: Identificador único (Chave Primária)
- titulo: Título do álbum
- data_lancamento: Data de lançamento
- id_artista: Referência ao artista (Chave Estrangeira)
*/

/*
ENTIDADE: MUSICA
Descrição: Representa músicas individuais
Atributos:
- id_musica: Identificador único (Chave Primária)
- titulo: Título da música
- duracao: Duração em segundos
- numero_faixa: Posição no álbum
- id_album: Referência ao álbum (Chave Estrangeira)
*/

/*
RELACIONAMENTOS PRINCIPAIS:

1. ARTISTA (1) ---- (N) ALBUM
   - Um artista pode ter vários álbuns
   - Cada álbum pertence a exatamente um artista

2. ALBUM (1) ---- (N) MUSICA  
   - Um álbum pode ter várias músicas
   - Cada música pertence a exatamente um álbum

3. USUARIO (1) ---- (N) PLAYLIST
   - Um usuário pode criar várias playlists
   - Cada playlist pertence a exatamente um usuário

4. PLAYLIST (N) ---- (M) MUSICA
   - Uma playlist pode conter várias músicas
   - Uma música pode estar em várias playlists
*/

-- =====================================================
-- EXEMPLO DE DADOS CONCEITUAIS
-- =====================================================

/*
EXEMPLO DE USUARIO:
id_usuario: 1
nome_usuario: "João Silva"
email: "joao.silva@email.com"
data_nascimento: "1990-05-15"
data_cadastro: "2023-01-10 14:30:00"
*/

/*
EXEMPLO DE ARTISTA:
id_artista: 1
nome_artista: "The Beatles"
biografia: "Banda inglesa de rock formada em Liverpool em 1960..."
data_formacao: "1960-08-17"
pais_origem: "Reino Unido"
*/

/*
EXEMPLO DE ALBUM:
id_album: 1
titulo: "Abbey Road"
data_lancamento: "1969-09-26"
id_artista: 1 (referência a The Beatles)
*/

/*
EXEMPLO DE MUSICA:
id_musica: 1
titulo: "Come Together"
duracao: 259 (4 minutos e 19 segundos)
numero_faixa: 1
id_album: 1 (referência ao álbum Abbey Road)
*/

-- =====================================================
-- ANÁLISE DE NORMALIZAÇÃO
-- =====================================================

/*
PRIMEIRA FORMA NORMAL (1FN):
✓ Todos os atributos contêm valores atômicos
✓ Não há grupos repetitivos
✓ Cada coluna tem um nome único
✓ A ordem das linhas é irrelevante

SEGUNDA FORMA NORMAL (2FN):
✓ Está na 1FN
✓ Todos os atributos não-chave dependem totalmente da chave primária
✓ Não há dependências parciais

TERCEIRA FORMA NORMAL (3FN):
✓ Está na 2FN  
✓ Não há dependências transitivas
✓ Atributos não-chave não dependem de outros atributos não-chave
*/

-- =====================================================
-- CONSIDERAÇÕES DE DESIGN
-- =====================================================

/*
DECISÕES DE MODELAGEM:

1. CHAVES PRIMÁRIAS:
   - Utilizamos chaves surrogate (id_usuario, id_artista, etc.)
   - Vantagens: Simplicidade, performance, flexibilidade

2. RELACIONAMENTOS:
   - Optamos por relacionamentos simples inicialmente
   - Evitamos relacionamentos N:M desnecessários na fase inicial

3. ATRIBUTOS:
   - Mantivemos apenas atributos essenciais
   - Evitamos redundância de dados
   - Consideramos futuras expansões

4. TIPOS DE DADOS:
   - Strings para textos variáveis
   - Inteiros para identificadores
   - Timestamps para datas
   - Inteiros para durações (segundos)
*/

-- =====================================================
-- PRÓXIMOS PASSOS
-- =====================================================

/*
EVOLUÇÃO DO MODELO:
1. Adicionar entidades para gêneros musicais
2. Implementar sistema de playlists
3. Criar tabelas para histórico de reprodução
4. Desenvolver sistema de assinaturas
5. Adicionar avaliações e comentários
*/