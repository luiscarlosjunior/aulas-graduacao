# 📋 Guia de Reorganização do Repositório

Este documento explica as mudanças na estrutura de diretórios do repositório e como encontrar o conteúdo na nova organização por áreas de conhecimento acadêmico.

## 🎯 Objetivo da Reorganização

A reorganização foi realizada para:
- ✅ Melhorar a clareza e navegabilidade do repositório
- ✅ Agrupar conteúdo relacionado por **áreas do conhecimento**
- ✅ Eliminar duplicações (consolidar `programacao/` e `programming/`)
- ✅ Usar nomenclatura consistente em português com prefixos numéricos
- ✅ Criar progressão pedagógica clara (fundamentos → programação → dados → engenharia)

## 🔄 Mapeamento Completo de Mudanças

### 📊 Visão Geral das Mudanças

| Antes | Depois | Tipo de Mudança |
|-------|--------|-----------------|
| `organizacao-computadores/` | `01-fundamentos-computacao/organizacao-computadores/` | Agrupamento em área |
| `perguntas-sobre-computacao/` | `01-fundamentos-computacao/perguntas-sobre-computacao/` | Agrupamento em área |
| `programacao/` + `programming/` | `02-programacao/` | Consolidação + Renomeação |
| `banco-dados/` | `03-banco-dados/` | Prefixo numérico |
| `ciencia-dados/` | `04-ciencia-dados-computacao-cientifica/ciencia-dados/` | Agrupamento em área |
| `metodos-numericos/` | `04-ciencia-dados-computacao-cientifica/metodos-numericos/` | Agrupamento em área |
| `modelagem-sistemas/` | `05-engenharia-software/modelagem-sistemas/` | Agrupamento em área |
| `versionamento/` | `05-engenharia-software/versionamento/` | Agrupamento em área |
| `disciplina-projetos/` | `05-engenharia-software/disciplina-projetos/` | Agrupamento em área |

## 📍 Como Encontrar Seu Conteúdo

### 🆕 ÁREA 1: Fundamentos de Computação

**Antes:**
- Diretório raiz: `organizacao-computadores/`
- Diretório raiz: `perguntas-sobre-computacao/`

**Depois:**
- `01-fundamentos-computacao/organizacao-computadores/`
- `01-fundamentos-computacao/perguntas-sobre-computacao/`

**Exemplos específicos:**
- `organizacao-computadores/01-evolucao-historica-computadores/` → `01-fundamentos-computacao/organizacao-computadores/01-evolucao-historica-computadores/`
- `organizacao-computadores/20-tipos-operacoes-assembly-modos-enderecamento/` → `01-fundamentos-computacao/organizacao-computadores/20-tipos-operacoes-assembly-modos-enderecamento/`

---

### 💻 ÁREA 2: Programação

**Antes:**
- Diretório raiz: `programacao/` (português)
- Diretório raiz: `programming/` (inglês) - **DUPLICADO!**

**Depois:**
- `02-programacao/` (consolidado e unificado)

**Exemplos específicos:**
```bash
# De programacao/
programacao/java/             → 02-programacao/java/
programacao/php/              → 02-programacao/php/
programacao/python/           → 02-programacao/python/
programacao/csharp/           → 02-programacao/csharp/
programacao/java-ee/          → 02-programacao/java-ee/
programacao/programacao-java-web/ → 02-programacao/programacao-java-web/
programacao/depuracao/        → 02-programacao/depuracao/

# De programming/ (conteúdo consolidado em 02-programacao/)
programming/python/03-conceitos-intermediarios/ → 02-programacao/python/03-conceitos-intermediarios/
```

---

### 🗄️ ÁREA 3: Banco de Dados

**Antes:**
- Diretório raiz: `banco-dados/`

**Depois:**
- `03-banco-dados/` (prefixo numérico adicionado)

**Estrutura interna mantida (sem mudanças):**
```bash
banco-dados/sql-basico/       → 03-banco-dados/sql-basico/
banco-dados/sql-avancado/     → 03-banco-dados/sql-avancado/
banco-dados/projetos/         → 03-banco-dados/projetos/
banco-dados/modelagem-banco-dados/ → 03-banco-dados/modelagem-banco-dados/
banco-dados/linguagem-procedure/ → 03-banco-dados/linguagem-procedure/
banco-dados/scripts-sql/      → 03-banco-dados/scripts-sql/
banco-dados/sistemas-distribuidos/ → 03-banco-dados/sistemas-distribuidos/
```

---

### 📊 ÁREA 4: Ciência de Dados e Computação Científica

**Antes:**
- Diretório raiz: `ciencia-dados/`
- Diretório raiz: `metodos-numericos/` (separado)

**Depois:**
- `04-ciencia-dados-computacao-cientifica/ciencia-dados/`
- `04-ciencia-dados-computacao-cientifica/metodos-numericos/`

**Exemplos específicos:**
```bash
ciencia-dados/python/         → 04-ciencia-dados-computacao-cientifica/ciencia-dados/python/
ciencia-dados/analise-dados/  → 04-ciencia-dados-computacao-cientifica/ciencia-dados/analise-dados/
metodos-numericos/            → 04-ciencia-dados-computacao-cientifica/metodos-numericos/
```

---

### 🚀 ÁREA 5: Engenharia de Software

**Antes:**
- Diretório raiz: `modelagem-sistemas/`
- Diretório raiz: `versionamento/`
- Diretório raiz: `disciplina-projetos/`

**Depois:**
- `05-engenharia-software/modelagem-sistemas/`
- `05-engenharia-software/versionamento/`
- `05-engenharia-software/disciplina-projetos/`

**Exemplos específicos:**
```bash
modelagem-sistemas/uml-poo/   → 05-engenharia-software/modelagem-sistemas/uml-poo/
versionamento/01-introducao-academica.md → 05-engenharia-software/versionamento/01-introducao-academica.md
disciplina-projetos/          → 05-engenharia-software/disciplina-projetos/
```

## 🔍 Busca Rápida por Arquivo

Se você está procurando um arquivo específico, use esta tabela de referência rápida:

| Tipo de Arquivo | Localização Anterior | Nova Localização |
|-----------------|---------------------|-------------------|
| Aulas de Organização de Computadores | `organizacao-computadores/*/` | `01-fundamentos-computacao/organizacao-computadores/*/` |
| Exemplos Java | `programacao/java/` ou `programming/java/` | `02-programacao/java/` |
| Exemplos PHP | `programacao/php/` | `02-programacao/php/` |
| Exemplos Python básicos | `programacao/python/` | `02-programacao/python/` |
| Notebooks Jupyter (Ciência de Dados) | `ciencia-dados/python/` | `04-ciencia-dados-computacao-cientifica/ciencia-dados/python/` |
| Notebooks Métodos Numéricos | `metodos-numericos/` | `04-ciencia-dados-computacao-cientifica/metodos-numericos/` |
| Scripts SQL | `banco-dados/scripts-sql/` | `03-banco-dados/scripts-sql/` |
| Projetos SQL | `banco-dados/projetos/` | `03-banco-dados/projetos/` |
| Diagramas UML | `modelagem-sistemas/uml-poo/` | `05-engenharia-software/modelagem-sistemas/uml-poo/` |
| Guias Git/GitHub | `versionamento/` | `05-engenharia-software/versionamento/` |
| Material Disciplina de Projetos | `disciplina-projetos/` | `05-engenharia-software/disciplina-projetos/` |

## 📚 Novos READMEs Criados

Novos arquivos README foram criados para cada área de conhecimento:

1. **`01-fundamentos-computacao/README.md`** - Guia completo de fundamentos
2. **`02-programacao/README.md`** - Existente, mantido como está  
3. **`03-banco-dados/README.md`** - Existente, mantido como está
4. **`04-ciencia-dados-computacao-cientifica/README.md`** - Novo guia da área
5. **`05-engenharia-software/README.md`** - Novo guia da área

## 🛠️ Para Desenvolvedores

### Atualizando Links no Código

Se você tem links hardcoded no seu código ou documentação, use estas substituições:

```bash
# Bash/Scripts
sed -i 's|programacao/|02-programacao/|g' seu_arquivo.md
sed -i 's|banco-dados/|03-banco-dados/|g' seu_arquivo.md
sed -i 's|ciencia-dados/|04-ciencia-dados-computacao-cientifica/ciencia-dados/|g' seu_arquivo.md
sed -i 's|metodos-numericos/|04-ciencia-dados-computacao-cientifica/metodos-numericos/|g' seu_arquivo.md
sed -i 's|modelagem-sistemas/|05-engenharia-software/modelagem-sistemas/|g' seu_arquivo.md
sed -i 's|versionamento/|05-engenharia-software/versionamento/|g' seu_arquivo.md
sed -i 's|disciplina-projetos/|05-engenharia-software/disciplina-projetos/|g' seu_arquivo.md
```

### Clonando o Repositório Atualizado

```bash
# Clonar pela primeira vez
git clone https://github.com/luiscarlosjunior/aulas-graduacao.git

# Atualizar repositório existente
cd aulas-graduacao
git pull origin main
```

## 💡 Dicas para Navegação

1. **Use prefixos numéricos para navegação rápida**: As áreas estão numeradas de 01 a 05, facilitando a ordenação e localização.

2. **Siga a progressão pedagógica**: 
   - Comece pelos fundamentos (01)
   - Aprenda programação (02)
   - Domine banco de dados (03)
   - Explore ciência de dados (04)
   - Aplique engenharia de software (05)

3. **Consulte os READMEs específicos**: Cada área tem um README detalhado com orientações específicas.

4. **Use ferramentas de busca**: No GitHub, use `t` para buscar arquivos rapidamente.

## 📋 Checklist de Migração

Para quem estava usando a estrutura antiga:

- [ ] Atualizar bookmarks/favoritos do navegador
- [ ] Atualizar links em documentos pessoais
- [ ] Atualizar scripts que referenciam caminhos antigos
- [ ] Revisar clones locais do repositório (`git pull`)
- [ ] Explorar novos READMEs das áreas

## ❓ Perguntas Frequentes

### Por que adicionar prefixos numéricos?
Para criar uma ordem clara de progressão pedagógica e facilitar a navegação.

### Por que agrupar ciência de dados e métodos numéricos?
Porque ambos tratam de análise de dados e computação científica, sendo áreas complementares.

### Por que agrupar modelagem, versionamento e projetos?
Porque todas fazem parte de engenharia de software profissional: design, controle de versão e gestão de projetos.

### E se eu tiver links quebrados?
Consulte a seção "🔍 Busca Rápida por Arquivo" acima ou abra uma issue no GitHub.

---

**📅 Atualizado**: Fevereiro 2025  
**🎯 Versão**: 2.0 - Reorganização por Áreas de Conhecimento  
**✅ Status**: Completo e testado

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
