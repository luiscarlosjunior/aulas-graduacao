# 🎨 Modelagem de Banco de Dados

> Aprenda a projetar bancos de dados eficientes e bem estruturados

Esta disciplina demonstra, com exemplos práticos, os conceitos fundamentais de modelagem de dados, desde a concepção até a implementação física.

## 📚 Visão Geral

A modelagem de dados é o processo de criar uma representação visual das estruturas de dados e suas relações. Uma boa modelagem é essencial para:
- 📊 Organizar dados de forma eficiente
- 🔒 Garantir integridade e consistência
- ⚡ Otimizar performance
- 🔧 Facilitar manutenção futura
- 📈 Permitir escalabilidade

---

## 🗂️ Estrutura da Disciplina

A disciplina está organizada em quatro módulos progressivos:

### 1️⃣ [Modelo Conceitual](01-modelo-conceitual/)
**🎓 Nível: Iniciante | ⏱️ Duração: 2-3 semanas**

Fundamentos da modelagem de dados usando o Modelo Entidade-Relacionamento (MER).

#### 📅 Conteúdo por Aula:
- **Aula 01** - Introdução à Modelagem de Dados
  - Conceitos básicos de banco de dados
  - Importância da modelagem
  - Demonstrações práticas
  
- **Aula 02** - Modelo Entidade-Relacionamento (MER)
  - Entidades e atributos
  - Relacionamentos
  - Cardinalidade
  - Exemplos práticos com MER
  
- **Aula 03** - MER Avançado
  - Relacionamentos complexos
  - Entidades fracas
  - Generalização/Especialização
  - Modelagem com brModelo
  - Exercícios de revisão
  
- **Aula 04** - MER Completo
  - Modelagem completa de sistemas reais
  - Boas práticas
  - Documentação de modelos

**🛠️ Ferramentas:**
- [brModelo](https://sourceforge.net/projects/brmodelo/) - Ferramenta de modelagem brasileira
- Alternativas: Lucidchart, Draw.io, MySQL Workbench

**🎯 Objetivos:**
- ✅ Identificar entidades e atributos
- ✅ Definir relacionamentos entre entidades
- ✅ Determinar cardinalidades corretamente
- ✅ Criar diagramas ER completos

---

### 2️⃣ [Modelo Lógico](02-modelo-logico/)
**🎓 Nível: Intermediário | ⏱️ Duração: 2-3 semanas**

Transformação do modelo conceitual em modelo lógico com foco em normalização.

#### 📅 Conteúdo por Aula:
- **Aula 05** - Modelo Lógico e Normalização
  - Transformação MER → Modelo Lógico
  - Conceitos de normalização
  - Primeira Forma Normal (1FN)
  - Exemplos práticos com Excel
  
- **Aula 06** - Formas Normais Intermediárias
  - Segunda Forma Normal (2FN)
  - Terceira Forma Normal (3FN)
  - Dependências funcionais
  - Exercícios com Excel
  
- **Aula 07** - Normalização Avançada
  - Forma Normal de Boyce-Codd (FNBC)
  - Quarta e Quinta Formas Normais
  - Desnormalização estratégica
  - Exemplos práticos feitos em aula
  
- **Aula 08** - Refinamento do Modelo Lógico
  - Otimização de estruturas
  - Índices e chaves
  - Documentação
  - Exercícios práticos

**🛠️ Ferramentas:**
- Microsoft Excel / Google Sheets - Para exercícios de normalização
- brModelo - Para diagramas lógicos

**🎯 Objetivos:**
- ✅ Converter MER em modelo lógico
- ✅ Aplicar formas normais (1FN, 2FN, 3FN)
- ✅ Identificar dependências funcionais
- ✅ Eliminar redundâncias e anomalias

---

### 3️⃣ [Álgebra Relacional](03-algebra-relacional/)
**🎓 Nível: Intermediário | ⏱️ Duração: 1-2 semanas**

Operações matemáticas fundamentais para consultas em bancos de dados relacionais.

#### 📅 Conteúdo por Aula:
- **Aula 09** - Introdução à Álgebra Relacional
  - Operações básicas: Seleção (σ), Projeção (π)
  - Operações de conjunto: União (∪), Interseção (∩), Diferença (-)
  - Produto cartesiano (×)
  - Exemplos práticos feitos em aula
  
- **Aula 10** - Operações Avançadas
  - Junção (⋈) - Join natural
  - Junção externa (Left, Right, Full)
  - Divisão (÷)
  - Modelos relacionais
  - Exemplos com dados reais
  - Comandos práticos

**🛠️ Ferramentas:**
- [Relational](https://ltworf.github.io/relational/) - Software para praticar álgebra relacional

**🎯 Objetivos:**
- ✅ Compreender operações relacionais
- ✅ Aplicar álgebra relacional para consultas
- ✅ Base para entender SQL
- ✅ Otimizar consultas complexas

---

### 4️⃣ [Modelo Físico](04-modelo-fisico/)
**🎓 Nível: Intermediário a Avançado | ⏱️ Duração: 2-3 semanas**

Implementação prática do modelo lógico em um SGBD com SQL.

#### 📁 Conteúdo:
- **Script Empresa.sql** - Exemplo completo de modelagem física
- **[conceito-sql-oracle/](04-modelo-fisico/conceito-sql-oracle/)** - Introdução a SQL Oracle
  - `01/` - Criar tabelas, inserir dados, DROP, ALTER, SELECT com WHERE, JOINs básicos
  - `02/` - Funções SQL, exemplos de JOINs avançados, consultas com DISTINCT, CASE, LIMIT, GROUP BY, HAVING, ORDER
  - `03/` - Banco de dados completos e exemplos práticos

**🛠️ Ferramentas:**
- [Oracle Live SQL](https://livesql.oracle.com/) - Ambiente web gratuito
- Oracle SQL Developer
- MySQL Workbench
- PostgreSQL pgAdmin

**🎯 Objetivos:**
- ✅ Implementar modelo lógico em SQL
- ✅ Criar tabelas com constraints
- ✅ Definir índices e chaves
- ✅ Otimizar estruturas físicas

---

### 📝 [Revisão AV2](revisao-av2/)
Material de revisão para a segunda avaliação, consolidando todos os conceitos aprendidos.

---

## 🛤️ Trilha de Aprendizado Completa

### Sequência Recomendada:
```
Aula 01-04 → Aula 05-08 → Aula 09-10 → Modelo Físico
(MER)      (Normalização) (Álgebra)    (SQL)
2-3 sem.   2-3 sem.       1-2 sem.     2-3 sem.
```

**Total estimado: 7-11 semanas**

### Progressão de Habilidades:
1. **Semanas 1-3**: Modelagem Conceitual
   - Entender requisitos de negócio
   - Criar diagramas ER
   - Definir relacionamentos

2. **Semanas 4-6**: Modelagem Lógica
   - Normalizar estruturas
   - Eliminar redundâncias
   - Otimizar design

3. **Semanas 7-8**: Álgebra Relacional
   - Compreender operações matemáticas
   - Base teórica para SQL
   - Otimização de consultas

4. **Semanas 9-11**: Implementação Física
   - Criar estruturas em SQL
   - Implementar constraints
   - Testar e validar

---

## 📖 Como Usar Este Material

### Para Estudantes:
1. **Nenhum Pré-requisito**: Este é um curso introdutório
2. **Siga a Ordem**: As aulas são progressivas e interdependentes
3. **Pratique Muito**: Use as ferramentas sugeridas
4. **Faça os Exercícios**: Cada aula tem atividades práticas
5. **Revise Regularmente**: Os conceitos se acumulam

### Para Professores:
- Material organizado por aulas (01-10)
- Exemplos práticos em cada diretório
- Exercícios feitos em aula disponíveis
- Demonstrações e modelos de exemplo
- Material de revisão para avaliações

### Para Profissionais:
- Use como referência para projetos
- Consulte exemplos de modelagem
- Adapte modelos para suas necessidades
- Revise boas práticas de design

---

## 🛠️ Ferramentas e Software

### Modelagem Conceitual e Lógica:
- **[brModelo](https://sourceforge.net/projects/brmodelo/)** ⭐ Recomendado - Ferramenta brasileira gratuita
- **[Lucidchart](https://www.lucidchart.com/)** - Online, colaborativo
- **[Draw.io](https://draw.io/)** - Gratuito, online e offline
- **[MySQL Workbench](https://www.mysql.com/products/workbench/)** - Gratuito, completo

### Álgebra Relacional:
- **[Relational](https://ltworf.github.io/relational/)** ⭐ Recomendado - Interface gráfica para álgebra
- **[RelaX](https://dbis-uibk.github.io/relax/)** - Ferramenta web

### Modelo Físico (SQL):
- **[Oracle Live SQL](https://livesql.oracle.com/)** ⭐ Recomendado - Gratuito, não requer instalação
- **[Oracle SQL Developer](https://www.oracle.com/database/sqldeveloper/)** - Gratuito, profissional
- **[DBeaver](https://dbeaver.io/)** - Multiplataforma, gratuito

---

## 🎯 Objetivos de Aprendizado

Ao completar esta disciplina, você será capaz de:

### Modelo Conceitual:
- ✅ Analisar requisitos de negócio
- ✅ Identificar entidades, atributos e relacionamentos
- ✅ Criar diagramas ER completos e corretos
- ✅ Aplicar cardinalidades adequadamente

### Modelo Lógico:
- ✅ Transformar MER em modelo lógico
- ✅ Aplicar normalização (1FN a 3FN)
- ✅ Identificar e resolver anomalias
- ✅ Documentar decisões de design

### Álgebra Relacional:
- ✅ Compreender operações relacionais
- ✅ Escrever expressões de álgebra relacional
- ✅ Base teórica para consultas SQL
- ✅ Otimizar operações relacionais

### Modelo Físico:
- ✅ Implementar modelos em SQL
- ✅ Criar estruturas com constraints
- ✅ Definir índices apropriados
- ✅ Validar integridade dos dados

---

## 📚 Referências Bibliográficas

Este material foi desenvolvido com base nas seguintes referências acadêmicas:

- **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson.
- **Silberschatz, A., Korth, H., & Sudarshan, S.** (2019). *Database System Concepts*. 7th Edition. McGraw-Hill.
- **Date, C.J.** (2012). *An Introduction to Database Systems*. 8th Edition. Pearson.
- **Heuser, C.A.** (2009). *Projeto de Banco de Dados*. 6ª Edição. Bookman. (Livro em Português)
- **Machado, F.N.R.** (2014). *Banco de Dados: Projeto e Implementação*. 3ª Edição. Érica. (Livro em Português)

---

## 🔗 Disciplinas Relacionadas

### Pré-requisitos:
- Nenhum! Este é um curso introdutório.

### Sequência Sugerida:
```
1. Modelagem de BD → 2. SQL Básico → 3. SQL Avançado → 4. Projetos
```

### Disciplinas Complementares:
- **[SQL Básico](../sql-basico/)** - Implementação prática dos modelos
- **[SQL Avançado](../sql-avancado/)** - Consultas complexas
- **[Linguagem Procedure](../linguagem-procedure/)** - Programação no BD
- **[Projetos Práticos](../projetos/)** - Aplicação completa

---

## 🤝 Como Contribuir

Contribuições são bem-vindas! Você pode:

- 🐛 Reportar erros nos modelos
- 💡 Sugerir melhorias nos exemplos
- 📝 Adicionar novos casos de uso
- ✨ Criar exercícios adicionais
- 🌍 Melhorar a documentação

---

<div align="center">

**🎨 Aprenda a projetar bancos de dados profissionais!**

*Do conceito à implementação: domine todas as fases da modelagem de dados*

📖 **10 aulas progressivas** | 🎯 **4 modelos completos** | 💻 **Ferramentas gratuitas**

</div> 
