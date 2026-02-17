# 📜 Scripts SQL - Coleção Organizada por SGBD

> Scripts SQL práticos e exemplos para diferentes Sistemas de Gerenciamento de Banco de Dados

Esta área contém uma coleção abrangente de scripts SQL organizados por SGBD, ideal para consulta rápida e aprendizado prático.

## 🗂️ Estrutura

### 🐬 [MySQL](mysql/)
**Sistema de Gerenciamento de Banco de Dados open source mais popular do mundo**

#### 📁 Conteúdo:
- **[Arquivos de queries/](mysql/Arquivos%20de%20queries/)** - Scripts organizados por aula
  - `AULA 2/` - Consultas básicas
  - `AULA 3/` - JOINs e relacionamentos
  - `AULA 4/` - Funções e agregações
  - `AULA 5/` - Subconsultas
  - `AULA 6/` - Otimização e índices

- **[admmysql-seg-ot/](mysql/admmysql-seg-ot/)** - Administração, segurança e otimização
  - Gerenciamento de usuários
  - Controle de privilégios
  - Backup e recuperação
  - Otimização de queries

- **[dml/](mysql/dml/)** - Data Manipulation Language
  - INSERT, UPDATE, DELETE
  - Manipulação de dados
  - Exemplos práticos (Aula 3)

- **[sp/](mysql/sp/)** - Stored Procedures
  - Procedures básicas
  - Procedures com parâmetros
  - Functions customizadas

**🎯 Ideal para**: Desenvolvimento web, aplicações PHP/Python/Node.js

---

### 🔴 [Oracle](oracle/)
**Sistema de banco de dados corporativo robusto e escalável**

#### 📁 Conteúdo:
- **[01a-basicos/](oracle/01a-basicos/)** - Fundamentos SQL Oracle
  - Criação de tabelas
  - Tipos de dados Oracle
  - Constraints e regras

- **[01b-basicos/](oracle/01b-basicos/)** - SQL Básico Avançado
  - `01-condicionais/` - WHERE, CASE, operadores
  - `02-alterando-dados/` - UPDATE, DELETE, MERGE
  - `03-join/` - INNER, LEFT, RIGHT, FULL JOIN
  - `04-funcoes-externas/` - Funções do Oracle
  - `05-relatorios/` - Relatórios e agregações

- **[02-desempenho/](oracle/02-desempenho/)** - Otimização de Performance
  - Índices
  - Explain Plan
  - Otimização de queries
  - Hints do Oracle

- **[03-administracao/](oracle/03-administracao/)** - Administração DBA
  - Gestão de usuários
  - Tablespaces
  - Backup e recovery
  - Manutenção

- **[exercicio/](oracle/exercicio/)** - Exercícios práticos

**🔗 Recursos:**
- [README completo do Oracle](oracle/README.md)

**🎯 Ideal para**: Ambientes corporativos, sistemas críticos, grandes volumes de dados

---

### 🐘 [PostgreSQL](postgre/)
**Sistema de banco de dados open source avançado e confiável**

#### 📁 Conteúdo:
- **[01-intro/](postgre/01-intro/)** - Introdução ao PostgreSQL
  - Comandos básicos
  - Tipos de dados PostgreSQL
  - Particularidades do Postgres

**🎯 Ideal para**: Aplicações web modernas, dados geoespaciais (PostGIS), JSON/NoSQL híbrido

---

### 🟦 [SQL Server](sqlserver/)
**Sistema de banco de dados Microsoft com forte integração ao ecossistema Windows**

#### 📁 Conteúdo:
- **[Downloads - Aula 1/](sqlserver/Downloads%20-%20Aula%201/)** - Material introdutório

- **[Aula 02/](sqlserver/Aula%2002/)** - SQL Básico no SQL Server
  - `03 - Consultas/` - SELECT, WHERE, ORDER BY, GROUP BY, HAVING
  - `04 - Join/` - INNER, LEFT, RIGHT, FULL, CROSS JOIN, Views, Subconsultas
  - `05 - Funcoes/` - Funções de datas, matemáticas, strings, conversões
  - `06 - Exercicios/` - Exercícios práticos com soluções

- **[Aula 03/](sqlserver/Aula%2003/)** - SQL Intermediário
  - `01 - 03/` - Conceitos básicos consolidados
  - `04/` - Operações avançadas
  - `05/` - Performance e otimização

- **[Aula 04 - TSQl/](sqlserver/Aula%2004%20-%20TSQl/)** - Transact-SQL (Linguagem procedural)
  - `01/` - Introdução ao T-SQL
  - `02/` - Variáveis e controle de fluxo
  - `03 - funcoes/` - Funções customizadas
  - `04 - sp/` - Stored Procedures
  - `05 - exceptions/` - Tratamento de erros (TRY-CATCH)
  - `06 - cursor/` - Cursores
  - `07 - adm/` - Administração e DBA

**🎯 Ideal para**: Ambientes corporativos Microsoft, integração com .NET, Business Intelligence

---

## 🎯 Como Usar Esta Coleção

### Para Estudantes:
1. **Escolha seu SGBD**: Comece com o banco de dados que você está estudando
2. **Siga a Progressão**: Os scripts estão organizados do básico ao avançado
3. **Execute e Modifique**: Teste cada script e experimente variações
4. **Compare SGBDs**: Veja as diferenças de sintaxe entre eles

### Para Professores:
- Material pronto para usar em aulas
- Scripts comentados e explicados
- Exemplos progressivos
- Exercícios com soluções

### Para Profissionais:
- Referência rápida de sintaxe
- Exemplos de padrões comuns
- Scripts de administração
- Templates para projetos

---

## 📊 Comparação entre SGBDs

| Recurso | MySQL | Oracle | PostgreSQL | SQL Server |
|---------|-------|--------|-----------|------------|
| **Licença** | GPL/Comercial | Comercial | PostgreSQL (Open) | Comercial |
| **Custo** | Gratuito/Pago | Pago | Gratuito | Gratuito/Pago |
| **Complexidade** | Baixa-Média | Alta | Média-Alta | Média |
| **Performance** | Boa | Excelente | Excelente | Excelente |
| **Escalabilidade** | Média | Muito Alta | Alta | Alta |
| **Suporte JSON** | Sim | Sim | Excelente | Sim |
| **Plataformas** | Multiplataforma | Multiplataforma | Multiplataforma | Windows/Linux |
| **Ideal para** | Web apps | Enterprise | Apps modernas | Microsoft stack |

---

## 🛠️ Ferramentas Recomendadas

### Cliente Universal:
- **[DBeaver](https://dbeaver.io/)** ⭐ Recomendado - Gratuito, suporta todos os SGBDs
- **[DataGrip](https://www.jetbrains.com/datagrip/)** - Pago, IDE profissional

### Por SGBD:

#### MySQL:
- **[MySQL Workbench](https://www.mysql.com/products/workbench/)** - Oficial, gratuito
- **[phpMyAdmin](https://www.phpmyadmin.net/)** - Web-based

#### Oracle:
- **[SQL Developer](https://www.oracle.com/database/sqldeveloper/)** - Oficial, gratuito
- **[Oracle Live SQL](https://livesql.oracle.com/)** - Online, gratuito

#### PostgreSQL:
- **[pgAdmin](https://www.pgadmin.org/)** - Oficial, gratuito
- **[Postico](https://eggerapps.at/postico/)** - macOS

#### SQL Server:
- **[SQL Server Management Studio (SSMS)](https://docs.microsoft.com/sql/ssms/)** - Oficial, gratuito
- **[Azure Data Studio](https://docs.microsoft.com/sql/azure-data-studio/)** - Multiplataforma

---

## 🔗 Relação com Outras Disciplinas

### Use em Conjunto Com:
- **[SQL Básico](../sql-basico/)** - Conceitos fundamentais
- **[SQL Avançado](../sql-avancado/)** - Consultas complexas
- **[Modelagem](../modelagem-banco-dados/)** - Design de banco de dados
- **[Projetos](../projetos/)** - Aplicação prática

### Fluxo de Aprendizado:
```
1. SQL Básico → 2. Scripts SQL (prática) → 3. SQL Avançado → 4. Projetos
```

---

## 📖 Organização dos Scripts

### Convenções de Nomenclatura:
- Números indicam sequência (01, 02, etc.)
- Nomes descritivos indicam conteúdo
- "Aula X" indica material de aula específica
- "Feitos em aula" são exemplos resolvidos ao vivo

### Tipos de Scripts:
- 📘 **Exemplos** - Scripts comentados para aprendizado
- ✏️ **Exercícios** - Problemas para praticar
- ✅ **Soluções** - Respostas dos exercícios
- 🔧 **Administração** - Scripts DBA
- 📊 **Relatórios** - Queries complexas

---

## 🎓 Progressão Sugerida

### Iniciante (2-3 semanas):
1. MySQL básico ou Oracle 01a-basicos
2. Consultas simples (SELECT, WHERE)
3. Inserção e atualização de dados

### Intermediário (3-4 semanas):
1. JOINs e relacionamentos
2. Funções e agregações
3. Subconsultas
4. Views

### Avançado (4-6 semanas):
1. Stored Procedures
2. Triggers
3. Otimização e índices
4. Administração DBA

---

## 📚 Recursos Adicionais

### Documentação Oficial:
- [MySQL Docs](https://dev.mysql.com/doc/)
- [Oracle Docs](https://docs.oracle.com/en/database/)
- [PostgreSQL Docs](https://www.postgresql.org/docs/)
- [SQL Server Docs](https://docs.microsoft.com/sql/)

### Sites de Prática:
- [SQLZoo](https://sqlzoo.net/)
- [HackerRank SQL](https://www.hackerrank.com/domains/sql)
- [LeetCode Database](https://leetcode.com/problemset/database/)

---

## 🤝 Como Contribuir

Contribuições são bem-vindas! Você pode:

- 🐛 Reportar erros nos scripts
- 💡 Adicionar novos exemplos
- 📝 Melhorar comentários
- ✨ Criar exercícios adicionais
- 🌍 Adicionar scripts para outros SGBDs

---

<div align="center">

**📜 Domine SQL em qualquer SGBD!**

*Coleção completa de scripts práticos e exemplos reais*

🐬 **MySQL** | 🔴 **Oracle** | 🐘 **PostgreSQL** | 🟦 **SQL Server**

💻 **Centenas de scripts** | 📖 **Bem documentados** | 🎯 **Prontos para usar**

</div>
