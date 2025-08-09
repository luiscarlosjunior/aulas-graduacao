# Módulo 01 - Introdução à Modelagem de Dados

## Objetivos de Aprendizagem

Ao final deste módulo, o aluno será capaz de:
- Compreender os conceitos fundamentais de banco de dados
- Identificar as diferenças entre dados, informação e conhecimento
- Entender o modelo relacional e seus componentes
- Reconhecer a importância da modelagem conceitual
- Aplicar conceitos básicos de normalização

## Conteúdo Teórico

### 1. Conceitos Fundamentais

#### 1.1 Dados vs. Informação vs. Conhecimento
- **Dados**: Fatos brutos sem contexto (ex: "The Beatles", "1965", "Help!")
- **Informação**: Dados processados com significado (ex: "The Beatles lançou o álbum 'Help!' em 1965")
- **Conhecimento**: Informação aplicada com experiência (ex: "The Beatles foi uma banda influente dos anos 60")

#### 1.2 Sistema de Gerenciamento de Banco de Dados (SGBD)
Um SGBD é um software que permite:
- Armazenar dados de forma organizada
- Controlar o acesso aos dados
- Garantir a integridade e segurança
- Fornecer mecanismos de backup e recuperação

### 2. Modelo Relacional

#### 2.1 Componentes Básicos
- **Tabela (Relação)**: Estrutura que armazena dados em linhas e colunas
- **Tupla (Linha)**: Conjunto de valores relacionados
- **Atributo (Coluna)**: Característica específica de uma entidade
- **Domínio**: Conjunto de valores válidos para um atributo

#### 2.2 Propriedades das Relações
1. Cada tabela tem um nome único
2. Cada coluna tem um nome único dentro da tabela
3. A ordem das linhas e colunas é irrelevante
4. Cada célula contém apenas um valor (atomicidade)
5. Não existem linhas duplicadas

### 3. Conceitos de Modelagem

#### 3.1 Entidades
Uma entidade representa algo do mundo real sobre o qual queremos armazenar informações:
- **Usuário**: Pessoa que usa o sistema MusiStream
- **Artista**: Músico ou banda que cria música
- **Música**: Faixa musical individual
- **Álbum**: Coleção de músicas

#### 3.2 Atributos
Características das entidades:
- **Simples**: Não podem ser subdivididos (ex: nome_usuario)
- **Compostos**: Podem ser subdivididos (ex: endereço = rua + cidade + cep)
- **Monovalorados**: Um valor por entidade (ex: data_nascimento)
- **Multivalorados**: Múltiplos valores possíveis (ex: telefones)

#### 3.3 Relacionamentos
Associações entre entidades:
- **1:1 (Um para Um)**: Uma entidade A relaciona-se com apenas uma entidade B
- **1:N (Um para Muitos)**: Uma entidade A relaciona-se com várias entidades B
- **N:M (Muitos para Muitos)**: Várias entidades A relacionam-se com várias entidades B

### 4. Chaves e Integridade

#### 4.1 Tipos de Chaves
- **Chave Primária**: Identifica unicamente cada tupla
- **Chave Estrangeira**: Referencia a chave primária de outra tabela
- **Chave Candidata**: Atributo(s) que podem ser chave primária
- **Chave Composta**: Chave formada por múltiplos atributos

#### 4.2 Integridade Referencial
Garante que as relações entre tabelas sejam mantidas:
- Toda chave estrangeira deve referenciar uma chave primária existente
- Operações de inserção, atualização e exclusão devem preservar a integridade

### 5. Normalização Básica

#### 5.1 Primeira Forma Normal (1FN)
- Cada atributo contém apenas valores atômicos
- Não há grupos repetitivos

#### 5.2 Segunda Forma Normal (2FN)
- Está na 1FN
- Todos os atributos não-chave dependem totalmente da chave primária

#### 5.3 Terceira Forma Normal (3FN)
- Está na 2FN
- Não há dependências transitivas entre atributos não-chave

## Exemplo Prático: Sistema MusiStream

### Modelo Conceitual Inicial

#### Entidades Principais:
1. **USUARIO**
   - id_usuario (PK)
   - nome_usuario
   - email
   - data_nascimento
   - data_cadastro

2. **ARTISTA**
   - id_artista (PK)
   - nome_artista
   - biografia
   - data_formacao
   - pais_origem

3. **ALBUM**
   - id_album (PK)
   - titulo
   - data_lancamento
   - id_artista (FK)

4. **MUSICA**
   - id_musica (PK)
   - titulo
   - duracao
   - id_album (FK)

#### Relacionamentos:
- ARTISTA → ALBUM (1:N): Um artista pode ter vários álbuns
- ALBUM → MUSICA (1:N): Um álbum pode ter várias músicas
- USUARIO → PLAYLIST (1:N): Um usuário pode criar várias playlists

## Exercícios Práticos

Consulte a pasta `exercicios/` para atividades hands-on que reforçam os conceitos apresentados.

## Referências Bibliográficas

1. **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson. Capítulos 1-3.

2. **Date, C.J.** (2012). *SQL and Relational Theory: How to Write Accurate SQL Code*. 2nd Edition. O'Reilly Media. Capítulos 1-2.

3. **Silberschatz, A., Galvin, P. B., & Gagne, G.** (2018). *Operating System Concepts*. 10th Edition. John Wiley & Sons. Capítulo sobre sistemas de arquivos e bancos de dados.

4. **Codd, E.F.** (1970). "A Relational Model of Data for Large Shared Data Banks". *Communications of the ACM*, 13(6), 377-387.

5. **Chen, P.P.** (1976). "The Entity-Relationship Model: Toward a Unified View of Data". *ACM Transactions on Database Systems*, 1(1), 9-36.

## Material Complementar

### Artigos Recomendados:
- "Database Design Basics" - Microsoft SQL Server Documentation
- "Database Normalization Explained" - Oracle Documentation
- "Introduction to Database Design" - IBM Knowledge Center

### Vídeos Educacionais:
- "Database Design Course" - freeCodeCamp
- "Database Design Tutorial" - Derek Banas
- "Relational Database Design" - MIT OpenCourseWare

### Ferramentas de Modelagem:
- **MySQL Workbench**: Ferramenta visual para design de banco
- **Draw.io**: Ferramenta online para diagramas ER
- **Lucidchart**: Plataforma de diagramação colaborativa

---

**Próximo Módulo**: [02 - Introdução à História SQL](../02-introducao-historia-sql/README.md)

**Dica de Estudo**: Pratique criando diagramas ER para sistemas que você conhece (biblioteca, loja online, rede social) antes de prosseguir para o próximo módulo.