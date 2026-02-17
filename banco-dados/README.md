# 🗄️ Banco de Dados - Curso Completo

> Área dedicada ao estudo completo de sistemas de gerenciamento de banco de dados, desde fundamentos até SQL avançado

Esta área reúne todo o conteúdo relacionado a bancos de dados, incluindo o curso completo de SQL, modelagem, procedimentos armazenados e projetos práticos. Todo o material está organizado de forma progressiva para facilitar o aprendizado.

## 🗺️ Guia de Navegação Rápida

| Área | Nível | Duração | Descrição |
|------|-------|---------|-----------|
| **[SQL Básico](sql-basico/)** | 🟢 Iniciante | 6-8 sem | Fundamentos de SQL (CREATE, INSERT, SELECT básico) |
| **[SQL Avançado](sql-avancado/)** | 🟡 Intermediário | 6-8 sem | JOINs, Subqueries, Functions, UPDATE/DELETE |
| **[Modelagem de Dados](modelagem-banco-dados/)** | 🟢 Iniciante | 7-11 sem | MER, Normalização, Álgebra Relacional |
| **[Scripts SQL](scripts-sql/)** | 🟢 Todos | Consulta | Coleção por SGBD (MySQL, Oracle, PostgreSQL, SQL Server) |
| **[Linguagem Procedure](linguagem-procedure/)** | 🔴 Avançado | 8-12 sem | PL/SQL, Procedures, Functions, Triggers |
| **[Sistemas Distribuídos](sistemas-distribuidos/)** | 🔴 Avançado | 4-6 sem | Arquiteturas distribuídas, CAP, Consenso |
| **[Projetos Práticos](projetos/)** | 🟡 Intermediário | Variável | Aplicações completas (Streaming, E-commerce, etc.) |

💡 **Dica**: Cada área tem seu próprio README detalhado. Clique nos links acima para explorar!

## 📚 Conteúdo Organizado

### 📖 [SQL Básico](sql-basico/) - Fundamentos de SQL
**🎓 Nível: Iniciante | ⏱️ Duração: 6-8 semanas**

Módulos fundamentais para começar com SQL (01-09):

#### Estrutura Detalhada:
- **[01-introducao-modelagem-dados/](sql-basico/01-introducao-modelagem-dados/)** - Conceitos básicos de modelagem e design
- **[02-introducao-historia-sql/](sql-basico/02-introducao-historia-sql/)** - Evolução e importância da linguagem SQL
- **[03-interface-sql-plus-tabelas-regras/](sql-basico/03-interface-sql-plus-tabelas-regras/)** - Primeiros passos com SQL
- **[04-trabalhando-estrutura-tabelas/](sql-basico/04-trabalhando-estrutura-tabelas/)** - Criação e definição de tabelas
- **[05-estrutura-tabelas-regras-relacionamentos/](sql-basico/05-estrutura-tabelas-regras-relacionamentos/)** - Constraints e relacionamentos
- **[06-alteracao-estrutura-tabela/](sql-basico/06-alteracao-estrutura-tabela/)** - Comandos DDL para modificação
- **[07-manipulacao-dados-inserindo-parte-i/](sql-basico/07-manipulacao-dados-inserindo-parte-i/)** - Comandos INSERT básicos
- **[08-manipulacao-dados-inserindo-parte-ii/](sql-basico/08-manipulacao-dados-inserindo-parte-ii/)** - Comandos INSERT avançados
- **[09-controle-transacoes-criacao-relatorios/](sql-basico/09-controle-transacoes-criacao-relatorios/)** - COMMIT, ROLLBACK e relatórios

**🎯 Ideal para**: Iniciantes em banco de dados que querem aprender SQL desde o início.

---

### 🚀 [SQL Avançado](sql-avancado/) - Consultas e Otimização
**🎓 Nível: Intermediário a Avançado | ⏱️ Duração: 6-8 semanas**

Módulos avançados para dominar SQL (10-17):

#### Estrutura Detalhada:
- **[10-relatorios-filtros-operadores/](sql-avancado/10-relatorios-filtros-operadores/)** - WHERE, operadores relacionais e lógicos
- **[11-relatorios-operadores-aritmeticos/](sql-avancado/11-relatorios-operadores-aritmeticos/)** - Cálculos e expressões
- **[12-relatorios-funcoes-banco-dados/](sql-avancado/12-relatorios-funcoes-banco-dados/)** - Funções agregadas e de string
- **[13-relatorios-subqueries/](sql-avancado/13-relatorios-subqueries/)** - Subconsultas e consultas aninhadas
- **[14-relatorios-multiplas-tabelas/](sql-avancado/14-relatorios-multiplas-tabelas/)** - JOINs e relacionamentos
- **[15-operacoes-conjuntos/](sql-avancado/15-operacoes-conjuntos/)** - UNION, INTERSECT, EXCEPT
- **[16-criando-views/](sql-avancado/16-criando-views/)** - Visões e consultas reutilizáveis
- **[17-manipulacao-dados-update-delete/](sql-avancado/17-manipulacao-dados-update-delete/)** - Atualização e remoção de dados

**🎯 Ideal para**: Desenvolvedores que já conhecem SQL básico e querem dominar consultas complexas.

---

### 🎨 [Modelagem de Banco de Dados](modelagem-banco-dados/)
**🎓 Nível: Intermediário | ⏱️ Duração: 4-6 semanas**

Conceitos e práticas de modelagem de dados:

**📖 Conteúdo:**
- Modelagem conceitual (Modelo Entidade-Relacionamento)
- Modelagem lógica (Normalização)
- Modelagem física (Implementação)
- Boas práticas de design de bancos de dados

**🎯 Ideal para**: Estudantes que querem aprender a projetar bancos de dados eficientes.

---

### 🌐 [Sistemas Distribuídos](sistemas-distribuidos/)
**🎓 Nível: Avançado | ⏱️ Duração: 4-6 semanas**

Banco de dados distribuídos e arquiteturas escaláveis:

**📖 Conteúdo:**
- Arquiteturas distribuídas (Cliente-Servidor, P2P, Federada)
- Fragmentação e replicação de dados
- Transações distribuídas e protocolo 2PC/3PC
- Teorema CAP e propriedades BASE
- Algoritmos de consenso (Paxos, Raft)
- Problemas comuns (split-brain, deadlock distribuído)
- Exemplos práticos do dia a dia (Netflix, Uber, Instagram)

**🎯 Ideal para**: Desenvolvedores que trabalham com sistemas escaláveis, arquitetos de soluções e DBAs que precisam projetar e manter sistemas distribuídos.

---

### 🔧 [Linguagem Procedure](linguagem-procedure/)
**🎓 Nível: Avançado | ⏱️ Duração: 3-4 semanas**

PL/SQL e programação em banco de dados:

**📖 Conteúdo:**
- Procedimentos armazenados (Stored Procedures)
- Funções customizadas
- Triggers (gatilhos)
- Packages e blocos anônimos
- Tratamento de exceções

**🎯 Ideal para**: Desenvolvedores que querem adicionar lógica de negócio ao banco de dados.

---

### 📜 [Scripts SQL](scripts-sql/)
**🎓 Nível: Todos | ⏱️ Duração: Consulta**

Coleção de scripts organizados por SGBD:

**📁 Estrutura:**
- `mysql/` - Scripts para MySQL
- `oracle/` - Scripts para Oracle Database
- `postgre/` - Scripts para PostgreSQL
- `sqlserver/` - Scripts para Microsoft SQL Server

**🎯 Ideal para**: Consulta rápida e exemplos práticos por SGBD.

---

### 🎯 [Projetos Práticos](projetos/)
**🎓 Nível: Intermediário a Avançado | ⏱️ Duração: Variável**

Projetos completos para aplicar o conhecimento:

#### Projetos Disponíveis:
- **[streaming-de-musica/](projetos/streaming-de-musica/)** - Sistema MusiStream (similar ao Spotify)
  - Modelagem completa de plataforma de streaming
  - Usuários, artistas, álbuns, músicas, playlists
  - Sistema de assinaturas e histórico de reprodução
  
- **[exemplo-ecommerce/](projetos/exemplo-ecommerce/)** - Sistema de E-commerce
  - Catálogo de produtos
  - Carrinho de compras
  - Pedidos e pagamentos
  
- **[exemplo-barbearia/](projetos/exemplo-barbearia/)** - Sistema de Barbearia
  - Agendamento de serviços
  - Gestão de clientes e profissionais
  - Controle financeiro
  
- **[revisao/](projetos/revisao/)** - Exercícios de revisão e prática

**🎯 Ideal para**: Consolidar conhecimentos com projetos reais e complexos.

---

## 🛤️ Trilhas de Aprendizado Recomendadas

### 🌟 Trilha Completa (Iniciante ao Avançado)
**Para quem quer dominar banco de dados completamente:**
1. SQL Básico - Módulos 01-09 (6-8 semanas)
2. Modelagem de Banco de Dados (4-6 semanas)
3. SQL Avançado - Módulos 10-17 (6-8 semanas)
4. Projeto Prático (escolha um dos projetos) (2-4 semanas)
5. Sistemas Distribuídos (4-6 semanas)
6. Linguagem Procedure (3-4 semanas)

**Total estimado: 6-9 meses**

### 🚀 Trilha Rápida (Essencial)
**Para quem precisa de conhecimento essencial rapidamente:**
1. SQL Básico - Módulos 01, 03, 04, 07 (3 semanas)
2. SQL Avançado - Módulos 10, 12, 14 (3 semanas)
3. Projeto Streaming de Música (2 semanas)

**Total estimado: 2 meses**

### 🎯 Trilha Desenvolvimento Web
**Para desenvolvedores web focados em CRUD:**
1. SQL Básico - Módulos 03, 04, 07 (2 semanas)
2. SQL Avançado - Módulos 10, 14, 17 (2 semanas)
3. Scripts SQL para seu SGBD preferido (1 semana)
4. Projeto E-commerce ou Barbearia (2 semanas)

**Total estimado: 1.5-2 meses**

### 🏢 Trilha DBA (Database Administrator)
**Para quem quer administrar bancos de dados:**
1. Curso completo SQL Básico e Avançado (3-4 meses)
2. Modelagem de Banco de Dados (4-6 semanas)
3. Sistemas Distribuídos (4-6 semanas)
4. Linguagem Procedure completa (3-4 semanas)
5. Todos os projetos práticos (2-3 meses)

**Total estimado: 7-10 meses**

### 🌐 Trilha Sistemas Escaláveis (Cloud & Distribuídos)
**Para arquitetos de soluções e engenheiros de sistemas escaláveis:**
1. SQL Básico - Módulos essenciais (01, 04, 07, 09) (3 semanas)
2. SQL Avançado - Módulos de otimização (10, 14) (2 semanas)
3. Modelagem de Banco de Dados (4 semanas)
4. **Sistemas Distribuídos (foco principal)** (6 semanas)
5. Projeto prático com arquitetura distribuída (4 semanas)

**Total estimado: 4-5 meses**

**🎯 Ideal para**: Desenvolvedores que trabalham com microservices, cloud computing, e sistemas de alta disponibilidade.

## 🎓 Sistema Exemplo: MusiStream

Durante o curso de SQL, utilizamos como exemplo principal o **MusiStream**, um sistema de streaming de música similar ao Spotify:

### Entidades Principais:
- **Usuários**: Informações dos usuários da plataforma
- **Artistas**: Dados dos artistas e bandas
- **Álbuns**: Coleções de músicas
- **Músicas**: Faixas individuais
- **Playlists**: Listas de reprodução criadas pelos usuários
- **Gêneros**: Categorias musicais
- **Assinaturas**: Planos de acesso à plataforma
- **Histórico de Reprodução**: Log de músicas tocadas

### Funcionalidades Implementadas:
✅ Cadastro de usuários e artistas  
✅ Organização de músicas em álbuns  
✅ Criação e gerenciamento de playlists  
✅ Sistema de assinaturas  
✅ Análise de dados de reprodução  
✅ Relatórios de popularidade  
✅ Recomendações baseadas em histórico  

## ⚙️ Configuração do Ambiente

### Ferramentas Recomendadas

#### Para Iniciantes:
- **Oracle SQL Developer** (Gratuito) - [Download](https://www.oracle.com/database/sqldeveloper/)
- **MySQL Workbench** (Gratuito) - [Download](https://www.mysql.com/products/workbench/)
- **DBeaver** (Gratuito, multiplataforma) - [Download](https://dbeaver.io/)

#### Para Avançados:
- **DataGrip** (Pago) - IDE profissional da JetBrains
- **Toad for Oracle** (Pago) - Ferramenta avançada para Oracle
- **SQL Server Management Studio** (Gratuito) - Para SQL Server

### Bancos de Dados Suportados

| SGBD | Versão Recomendada | Uso Principal |
|------|-------------------|---------------|
| **Oracle Database** | 19c ou superior | Curso completo, exemplos avançados |
| **MySQL** | 8.0 ou superior | Projetos web, desenvolvimento |
| **PostgreSQL** | 13 ou superior | Alternativa open source robusta |
| **SQL Server** | 2019 ou superior | Ambiente corporativo Microsoft |

## 📖 Como Usar Este Material

### Para Estudantes:
1. **Comece pelo SQL Básico** - Siga os módulos em ordem sequencial
2. **Pratique cada conceito** - Execute todos os exemplos fornecidos
3. **Faça os exercícios** - Cada módulo possui exercícios práticos
4. **Implemente os projetos** - Coloque em prática o que aprendeu
5. **Experimente variações** - Modifique os exemplos para explorar

### Para Professores:
- Material pronto para usar em aulas
- Progressão pedagógica estruturada
- Exercícios com soluções disponíveis
- Projetos práticos para trabalhos e avaliações
- Slides e apresentações em alguns módulos

### Para Profissionais:
- Use como referência rápida
- Consulte scripts específicos quando necessário
- Adapte projetos para suas necessidades
- Explore tópicos avançados conforme demanda

## 🎯 Objetivos de Aprendizado

### SQL Básico (Fundamentos)
- ✅ Criar e modificar estruturas de tabelas
- ✅ Inserir, consultar e manipular dados
- ✅ Entender relacionamentos entre tabelas
- ✅ Aplicar constraints e regras de integridade
- ✅ Controlar transações básicas

### SQL Avançado (Proficiência)
- ✅ Dominar JOINs e consultas complexas
- ✅ Utilizar subqueries eficientemente
- ✅ Aplicar funções agregadas e de janela
- ✅ Criar e gerenciar views
- ✅ Otimizar consultas para performance

### Modelagem (Design)
- ✅ Projetar modelos conceituais (ER)
- ✅ Normalizar estruturas de dados
- ✅ Implementar modelos físicos
- ✅ Aplicar boas práticas de design
- ✅ Documentar bancos de dados

### Sistemas Distribuídos (Escalabilidade)
- ✅ Compreender arquiteturas distribuídas
- ✅ Implementar fragmentação e replicação
- ✅ Gerenciar transações distribuídas
- ✅ Aplicar teorema CAP e trade-offs
- ✅ Projetar sistemas de alta disponibilidade
- ✅ Resolver problemas de consistência

### PL/SQL (Programação)
- ✅ Criar procedures e functions
- ✅ Implementar triggers para automação
- ✅ Tratar exceções adequadamente
- ✅ Organizar código em packages
- ✅ Otimizar performance de código PL/SQL

## 📚 Referências Bibliográficas

Este material foi desenvolvido com base nas seguintes referências acadêmicas:

- **Elmasri, R. & Navathe, S.** (2016). *Fundamentals of Database Systems*. 7th Edition. Pearson.
- **Date, C.J.** (2012). *SQL and Relational Theory: How to Write Accurate SQL Code*. 2nd Edition. O'Reilly Media.
- **Beaulieu, A.** (2020). *Learning SQL: Master SQL Fundamentals*. 3rd Edition. O'Reilly Media.
- **Forta, B.** (2018). *SQL in 10 Minutes, Sams Teach Yourself*. 5th Edition. Sams Publishing.
- **Silberschatz, A., Korth, H., & Sudarshan, S.** (2019). *Database System Concepts*. 7th Edition. McGraw-Hill.
- **Özsu, M. T., & Valduriez, P.** (2020). *Principles of Distributed Database Systems*. 4th Edition. Springer.
- **Kleppmann, M.** (2017). *Designing Data-Intensive Applications*. O'Reilly Media.

## 🤝 Como Contribuir

Contribuições são muito bem-vindas! Você pode:

- 🐛 **Reportar erros** nos scripts ou documentação
- 💡 **Sugerir melhorias** nos exemplos ou exercícios
- 📝 **Adicionar novos scripts** ou exemplos práticos
- ✨ **Criar novos projetos** ou casos de uso
- 🌍 **Melhorar documentação** e adicionar mais detalhes

### Processo de Contribuição:
1. Faça um fork do repositório
2. Crie uma branch para sua contribuição
3. Teste seus scripts SQL
4. Documente adequadamente
5. Envie um Pull Request

**📋 Padrões**: Consulte o [guia de contribuição](../contributing.md) para seguir os padrões do projeto.

## 📞 Suporte

### Para Dúvidas:
- Consulte os README específicos de cada módulo
- Verifique os exemplos e soluções fornecidos
- Procure na documentação oficial do seu SGBD

### Para Problemas:
- Abra uma [issue](https://github.com/luiscarlosjunior/aulas-graduacao/issues)
- Descreva o problema detalhadamente
- Inclua versão do SGBD e código relevante

## 📄 Licença

Este material é disponibilizado para fins **educacionais** como parte do curso de graduação da **Universidade Nove de Julho**.

---

<div align="center">

**🗄️ Domine banco de dados do básico ao avançado!**

*Material desenvolvido para formar profissionais completos em banco de dados*

📖 **17 módulos SQL** | 🌐 **Sistemas Distribuídos** | 🎯 **4 projetos práticos** | 💻 **Centenas de exemplos**

</div>

## 📚 Conteúdo Adicional

Para mais informações sobre o curso completo de SQL, consulte: [CURSO-SQL-README.md](CURSO-SQL-README.md)

---

## 📋 Sobre a Organização deste Diretório

Esta área foi cuidadosamente organizada para proporcionar a melhor experiência de aprendizado:

### 🗂️ Estrutura Pedagógica
- **Progressão Clara**: Do básico ao avançado em cada área
- **READMEs Detalhados**: Cada diretório principal tem documentação completa
- **Trilhas de Aprendizado**: Múltiplas rotas conforme seu objetivo
- **Exemplos Práticos**: Centenas de scripts SQL prontos para usar

### 🎯 Como Navegar
1. **Iniciantes**: Comece por [SQL Básico](sql-basico/) ou [Modelagem](modelagem-banco-dados/)
2. **Intermediários**: Vá para [SQL Avançado](sql-avancado/) ou [Projetos](projetos/)
3. **Avançados**: Explore [Linguagem Procedure](linguagem-procedure/) ou [Sistemas Distribuídos](sistemas-distribuidos/)
4. **Consulta Rápida**: Use [Scripts SQL](scripts-sql/) para referências específicas

### 📖 Cada README Contém
- ✅ Objetivos de aprendizado claros
- ✅ Duração estimada de estudo
- ✅ Pré-requisitos necessários
- ✅ Ferramentas recomendadas
- ✅ Trilhas de aprendizado sugeridas
- ✅ Próximos passos após conclusão

### 🔗 Interconexão
Todos os READMEs estão interligados com links para áreas relacionadas, facilitando a navegação e criando uma experiência de aprendizado integrada.
