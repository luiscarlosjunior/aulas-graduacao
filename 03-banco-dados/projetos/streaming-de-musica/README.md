# Streaming de Música - Exemplo Completo do Spotify

Este diretório contém um exemplo prático e completo de um sistema de streaming de música similar ao **Spotify**, consolidando todos os conceitos apresentados nos 16 módulos do curso de SQL.

## 📋 Visão Geral

O **MusiStream** é um sistema completo de streaming musical que demonstra na prática:
- Modelagem de dados completa
- Operações CRUD (Create, Read, Update, Delete)
- Consultas básicas e avançadas
- Relacionamentos entre tabelas
- Funções de banco de dados
- Subconsultas e JOINs
- Views e transações
- Relatórios e análises de dados

## 🎯 Objetivos Pedagógicos

Este exemplo foi criado para:
1. **Consolidar** todos os conceitos dos módulos 01-16
2. **Demonstrar** aplicação prática em um sistema real
3. **Exercitar** operações CRUD completas
4. **Explorar** cenários complexos de consultas
5. **Preparar** para projetos profissionais

## 🗂️ Estrutura dos Arquivos

### Scripts Principais
- **`01-estrutura-completa.sql`**: Schema completo do banco de dados
- **`create-tables.sql`**: Scripts CREATE TABLE simplificados e limpos
- **`02-inserir-dados.sql`**: Inserção de dados de exemplo (CREATE)
- **`03-consultas-basicas.sql`**: Consultas básicas e relatórios (READ)
- **`04-atualizacoes.sql`**: Operações de atualização (UPDATE)
- **`05-exclusoes.sql`**: Operações de exclusão (DELETE)
- **`06-consultas-avancadas.sql`**: Consultas complexas e análises
- **`07-roteiro-completo.sql`**: Roteiro guiado passo a passo

### Documentação da Modelagem
- **`modelagem-dados.md`**: Documentação completa da modelagem de dados
- **`diagrama-er.md`**: Diagrama Entidade-Relacionamento e especificações
- **`validar-scripts.sh`**: Script de validação da sintaxe SQL

### Pasta de Apoio
- **`solucoes/`**: Explicações detalhadas e soluções alternativas

## 🎵 Modelo do Sistema MusiStream

### Documentação da Modelagem
A modelagem de dados completa está documentada em:
- **`modelagem-dados.md`**: Modelo conceitual, lógico e físico detalhado
- **`diagrama-er.md`**: Diagrama Entidade-Relacionamento com especificações

### Entidades Principais

#### 👤 **Usuários**
- Informações pessoais e de login
- Preferências musicais
- Histórico de atividades

#### 🎤 **Artistas**
- Dados biográficos
- País de origem
- Gênero musical principal

#### 💿 **Álbuns**
- Informações do álbum
- Data de lançamento
- Número de faixas

#### 🎵 **Músicas**
- Detalhes da música
- Duração e letra
- Gênero musical

#### 📱 **Playlists**
- Listas criadas por usuários
- Música favoritas organizadas
- Compartilhamento entre usuários

#### 🎭 **Gêneros**
- Categorização musical
- Descrições e características

#### 💳 **Assinaturas**
- Planos de pagamento
- Recursos disponíveis
- Histórico de pagamentos

#### 📊 **Histórico de Reprodução**
- Log de músicas tocadas
- Estatísticas de uso
- Dados para recomendações

### Relacionamentos Principais

```
USUARIO (1:N) PLAYLIST
USUARIO (1:N) HISTORICO_REPRODUCAO  
USUARIO (1:N) ASSINATURA

ARTISTA (1:N) ALBUM
ALBUM (1:N) MUSICA

PLAYLIST (N:M) MUSICA
MUSICA (N:1) GENERO
HISTORICO_REPRODUCAO (N:1) MUSICA
HISTORICO_REPRODUCAO (N:1) USUARIO
```

## 🚀 Como Usar Este Exemplo

### Pré-requisitos
- Oracle Database 12c (12G) ou superior com SQL Developer
- Conhecimento básico dos módulos 01-16 do curso
- Editor SQL ou IDE de sua preferência (recomendado: Oracle SQL Developer)

### Sequência Recomendada

1. **📚 Estude a Modelagem**: Leia `modelagem-dados.md` e `diagrama-er.md`
2. **🏗️ Execute o Schema**: Rode o `01-estrutura-completa.sql` ou `create-tables.sql`
3. **📝 Insira os Dados**: Execute o `02-inserir-dados.sql`
4. **🔍 Pratique Consultas**: Teste o `03-consultas-basicas.sql`
5. **✏️ Faça Atualizações**: Execute o `04-atualizacoes.sql`
6. **🗑️ Teste Exclusões**: Rode o `05-exclusoes.sql`
7. **🎯 Consultas Avançadas**: Explore o `06-consultas-avancadas.sql`
8. **📖 Roteiro Completo**: Siga o `07-roteiro-completo.sql`
9. **✅ Valide os Scripts**: Execute `./validar-scripts.sh`

### ⚠️ Importante
- Execute os scripts em ordem sequencial
- Teste cada operação antes de prosseguir
- Use transações quando necessário
- Consulte a pasta `solucoes/` para explicações detalhadas

## 💡 Conceitos Abordados por Módulo

### Módulos 1-3: Fundamentos
- ✅ Modelagem de dados (Módulo 01)
- ✅ História e evolução SQL (Módulo 02)  
- ✅ Criação de tabelas e regras (Módulo 03)

### Módulos 4-6: Estrutura de Dados
- ✅ Estrutura de tabelas (Módulo 04)
- ✅ Relacionamentos e constraints (Módulo 05)
- ✅ Alteração de estruturas (Módulo 06)

### Módulos 7-9: Manipulação de Dados
- ✅ Inserção básica de dados (Módulo 07)
- ✅ Inserção avançada de dados (Módulo 08)
- ✅ Transações e relatórios (Módulo 09)

### Módulos 10-12: Consultas e Relatórios
- ✅ Filtros e operadores (Módulo 10)
- ✅ Operadores aritméticos (Módulo 11)
- ✅ Funções de banco de dados (Módulo 12)

### Módulos 13-16: Tópicos Avançados
- ✅ Subconsultas (Módulo 13)
- ✅ Múltiplas tabelas e JOINs (Módulo 14)
- ✅ Operações com conjuntos (Módulo 15)
- ✅ Views (Módulo 16)

## 🎯 Cenários Práticos Incluídos

### 📊 Análises de Negócio
- Músicas mais tocadas por período
- Artistas com maior número de streams
- Análise de preferências por região
- Relatórios de receita por assinatura

### 🔍 Consultas Complexas
- Recomendações baseadas em histórico
- Análise de similaridade entre usuários
- Ranking de popularidade por gênero
- Métricas de engajamento

### 💼 Operações do Sistema
- Criação e gerenciamento de playlists
- Sistema de busca avançada
- Controle de acesso por assinatura
- Auditoria de atividades

## 📚 Recursos Adicionais

- **Documentação**: Comentários detalhados em cada script
- **Variações**: Diferentes abordagens para o mesmo problema
- **Otimização**: Dicas de performance e boas práticas
- **Troubleshooting**: Soluções para problemas comuns

## 👨‍🏫 Para Professores

Este exemplo pode ser usado para:
- **Aulas práticas** demonstrando conceitos reais
- **Exercícios dirigidos** com cenários específicos
- **Projetos de semestre** como base para desenvolvimento
- **Avaliações** com diferentes níveis de complexidade

## 🎓 Para Estudantes

Benefícios deste exemplo:
- **Aplicação prática** de conceitos teóricos
- **Portfolio** para apresentar em entrevistas
- **Base** para projetos pessoais
- **Referência** para consultas futuras

---

## 📞 Suporte e Feedback

Este exemplo foi desenvolvido como material educacional complementar ao curso de SQL. 

**Tempo estimado**: 4-6 horas para conclusão completa
**Nível**: Intermediário a Avançado
**Pré-requisitos**: Módulos 01-16 do curso

---

*Desenvolvido com 💖 para facilitar o aprendizado de SQL através de exemplos práticos e relevantes.*