# 📋 Guia de Reorganização do Repositório

Este documento explica as mudanças na estrutura de diretórios do repositório e como encontrar o conteúdo na nova organização.

## 🎯 Objetivo da Reorganização

A reorganização foi realizada para:
- ✅ Melhorar a clareza e navegabilidade do repositório
- ✅ Agrupar conteúdo relacionado por disciplinas acadêmicas
- ✅ Eliminar duplicações e confusões estruturais
- ✅ Usar nomenclatura consistente em português

## 🔄 Mapeamento de Mudanças

### Diretórios Renomeados

| Antes | Depois | Motivo |
|-------|--------|--------|
| `programming/` | `programacao/` | Consistência linguística (português) |
| `data-science/` | `ciencia-dados/` | Consistência linguística (português) |
| `modelagem/` | `modelagem-sistemas/` | Maior clareza do propósito |

### Conteúdo Consolidado

#### 📚 Banco de Dados - Consolidação Completa

O diretório `linguagem-sql/` foi **integrado** ao `banco-dados/` para criar uma estrutura unificada:

**Antes:**
```
├── banco-dados/
│   ├── modelagem-banco-dados/
│   ├── linguagem-procedure/
│   └── scripts-sql/
└── linguagem-sql/          ← Separado
    ├── 01-introducao-modelagem-dados/
    ├── 02-introducao-historia-sql/
    ├── ...
    ├── 17-manipulacao-dados-update-delete/
    ├── streaming-de-musica/
    ├── exemplo-ecommerce/
    └── exemplo-barbearia/
```

**Depois:**
```
banco-dados/                 ← Tudo consolidado
├── sql-basico/             ← Módulos 01-09
│   ├── 01-introducao-modelagem-dados/
│   ├── 02-introducao-historia-sql/
│   ├── ...
│   └── 09-controle-transacoes-criacao-relatorios/
├── sql-avancado/           ← Módulos 10-17
│   ├── 10-relatorios-filtros-operadores/
│   ├── 11-relatorios-operadores-aritmeticos/
│   ├── ...
│   └── 17-manipulacao-dados-update-delete/
├── projetos/               ← Todos os exemplos práticos
│   ├── streaming-de-musica/
│   ├── exemplo-ecommerce/
│   ├── exemplo-barbearia/
│   └── revisao/
├── modelagem-banco-dados/
├── linguagem-procedure/
└── scripts-sql/
```

## 📍 Como Encontrar Seu Conteúdo

### Se você estava em `programming/`:
➡️ Agora está em `programacao/`

**Exemplos:**
- `programming/java/` → `programacao/java/`
- `programming/php/` → `programacao/php/`
- `programming/python/` → `programacao/python/`
- `programming/csharp/` → `programacao/csharp/`
- `programming/java-ee/` → `programacao/java-ee/`
- `programming/programacao-java-web/` → `programacao/programacao-java-web/`

### Se você estava em `data-science/`:
➡️ Agora está em `ciencia-dados/`

**Exemplos:**
- `data-science/python/` → `ciencia-dados/python/`
- `data-science/analise-dados/` → `ciencia-dados/analise-dados/`

### Se você estava em `linguagem-sql/`:
➡️ Agora está em `banco-dados/`

**Mapeamento detalhado:**

#### Módulos Básicos (01-09):
- `linguagem-sql/01-introducao-modelagem-dados/` → `banco-dados/sql-basico/01-introducao-modelagem-dados/`
- `linguagem-sql/02-introducao-historia-sql/` → `banco-dados/sql-basico/02-introducao-historia-sql/`
- `linguagem-sql/03-interface-sql-plus-tabelas-regras/` → `banco-dados/sql-basico/03-interface-sql-plus-tabelas-regras/`
- `linguagem-sql/04-trabalhando-estrutura-tabelas/` → `banco-dados/sql-basico/04-trabalhando-estrutura-tabelas/`
- `linguagem-sql/05-estrutura-tabelas-regras-relacionamentos/` → `banco-dados/sql-basico/05-estrutura-tabelas-regras-relacionamentos/`
- `linguagem-sql/06-alteracao-estrutura-tabela/` → `banco-dados/sql-basico/06-alteracao-estrutura-tabela/`
- `linguagem-sql/07-manipulacao-dados-inserindo-parte-i/` → `banco-dados/sql-basico/07-manipulacao-dados-inserindo-parte-i/`
- `linguagem-sql/08-manipulacao-dados-inserindo-parte-ii/` → `banco-dados/sql-basico/08-manipulacao-dados-inserindo-parte-ii/`
- `linguagem-sql/09-controle-transacoes-criacao-relatorios/` → `banco-dados/sql-basico/09-controle-transacoes-criacao-relatorios/`

#### Módulos Avançados (10-17):
- `linguagem-sql/10-relatorios-filtros-operadores/` → `banco-dados/sql-avancado/10-relatorios-filtros-operadores/`
- `linguagem-sql/11-relatorios-operadores-aritmeticos/` → `banco-dados/sql-avancado/11-relatorios-operadores-aritmeticos/`
- `linguagem-sql/12-relatorios-funcoes-banco-dados/` → `banco-dados/sql-avancado/12-relatorios-funcoes-banco-dados/`
- `linguagem-sql/13-relatorios-subqueries/` → `banco-dados/sql-avancado/13-relatorios-subqueries/`
- `linguagem-sql/14-relatorios-multiplas-tabelas/` → `banco-dados/sql-avancado/14-relatorios-multiplas-tabelas/`
- `linguagem-sql/15-operacoes-conjuntos/` → `banco-dados/sql-avancado/15-operacoes-conjuntos/`
- `linguagem-sql/16-criando-views/` → `banco-dados/sql-avancado/16-criando-views/`
- `linguagem-sql/17-manipulacao-dados-update-delete/` → `banco-dados/sql-avancado/17-manipulacao-dados-update-delete/`

#### Projetos Práticos:
- `linguagem-sql/streaming-de-musica/` → `banco-dados/projetos/streaming-de-musica/`
- `linguagem-sql/exemplo-ecommerce/` → `banco-dados/projetos/exemplo-ecommerce/`
- `linguagem-sql/exemplo-barbearia/` → `banco-dados/projetos/exemplo-barbearia/`
- `linguagem-sql/revisao/` → `banco-dados/projetos/revisao/`

#### README Original:
- `linguagem-sql/README.md` → `banco-dados/CURSO-SQL-README.md`
- Novo README abrangente criado em: `banco-dados/README.md`

### Se você estava em `modelagem/`:
➡️ Agora está em `modelagem-sistemas/`

**Exemplo:**
- `modelagem/uml-poo/` → `modelagem-sistemas/uml-poo/`

## 🔗 Links e Bookmarks

### Atualize seus favoritos:

Se você tinha bookmarks ou links salvos, atualize-os conforme a tabela abaixo:

| Link Antigo | Link Novo |
|-------------|-----------|
| `.../programming/...` | `.../programacao/...` |
| `.../data-science/...` | `.../ciencia-dados/...` |
| `.../linguagem-sql/...` | `.../banco-dados/sql-basico/...` ou `.../banco-dados/sql-avancado/...` ou `.../banco-dados/projetos/...` |
| `.../modelagem/...` | `.../modelagem-sistemas/...` |

### Clones Git Locais

Se você tem um clone local do repositório:

```bash
# Opção 1: Fazer pull das mudanças (recomendado)
git checkout main
git pull origin main

# Opção 2: Clonar novamente (se tiver conflitos)
cd ..
mv aulas-graduacao aulas-graduacao-old
git clone https://github.com/luiscarlosjunior/aulas-graduacao.git
```

## 📚 Nova Estrutura Completa

```
aulas-graduacao/
├── programacao/              # Disciplina de Programação
│   ├── java/                 # Java completo (básico ao avançado)
│   ├── java-ee/              # Java Enterprise Edition
│   ├── programacao-java-web/ # Desenvolvimento web com Java
│   ├── php/                  # PHP completo (web, OOP, BD, PDF)
│   ├── python/               # Python e POO
│   └── csharp/               # C# e .NET
│
├── banco-dados/              # Disciplina de Banco de Dados (consolidada)
│   ├── sql-basico/           # Curso SQL módulos 01-09
│   ├── sql-avancado/         # Curso SQL módulos 10-17
│   ├── projetos/             # Projetos práticos (MusiStream, e-commerce, etc)
│   ├── modelagem-banco-dados/# Modelagem conceitual, lógica e física
│   ├── linguagem-procedure/  # PL/SQL e procedures
│   └── scripts-sql/          # Scripts por SGBD (MySQL, Oracle, PostgreSQL, SQL Server)
│
├── ciencia-dados/            # Disciplina de Ciência de Dados
│   ├── python/               # Notebooks Jupyter
│   └── analise-dados/        # Projetos de análise
│
├── metodos-numericos/        # Disciplina de Métodos Numéricos
│   └── [notebooks Python]
│
└── modelagem-sistemas/       # Disciplina de Modelagem de Sistemas
    └── uml-poo/              # UML e POO
```

## ✅ Benefícios da Nova Estrutura

1. **Organização por Disciplina**: Cada pasta principal representa uma disciplina do curso
2. **Nomenclatura Consistente**: Todos os nomes em português para contexto acadêmico
3. **Consolidação Lógica**: Conteúdo de SQL unificado em banco-dados
4. **Progressão Clara**: SQL básico → SQL avançado → Projetos práticos
5. **Facilita Navegação**: Estrutura intuitiva e hierárquica
6. **Elimina Confusão**: Sem duplicação ou sobreposição de conteúdo

## 🆘 Precisa de Ajuda?

- **📖 Leia os READMEs**: Cada área tem documentação atualizada
- **🔍 Use a busca**: Procure por nome de arquivo no GitHub
- **💬 Abra uma Issue**: Se não encontrar algo, reporte
- **📧 Contato**: Consulte o README principal

## 📅 Data da Reorganização

Esta reorganização foi implementada em: **Dezembro de 2024**

## 🔄 Histórico de Versões

- **v2.0** (Dez 2024): Reorganização completa por disciplinas
- **v1.0** (Anterior): Estrutura original mista

---

**Nota**: Esta reorganização **não altera** o conteúdo dos arquivos, apenas sua localização. Todo o código, exemplos e exercícios permanecem intactos e funcionais.
