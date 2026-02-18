# 🐛 Depuração de Código - Guia Completo

> Guia acadêmico e prático sobre depuração de código para profissionais de tecnologia

## 📚 Sobre Este Módulo

A depuração é uma habilidade fundamental para qualquer profissional de tecnologia. Este módulo oferece uma formação completa sobre técnicas, ferramentas e melhores práticas de depuração, com base em literatura acadêmica consagrada e exemplos práticos do dia a dia.

**Baseado em obras de referência:**
- Andreas Zeller - "Why Programs Fail"
- David Agans - "Debugging: The 9 Indispensable Rules"
- Steve McConnell - "Code Complete"
- Robert C. Martin - "Clean Code"
- Brian Kernighan & Rob Pike - "The Practice of Programming"

## 🎯 Objetivos de Aprendizagem

Ao completar este módulo, você será capaz de:

- ✅ **Compreender** os fundamentos teóricos e a importância da depuração sistemática
- ✅ **Aplicar** técnicas clássicas e modernas de localização de bugs
- ✅ **Utilizar** ferramentas de depuração profissionais (debuggers, profilers, loggers)
- ✅ **Analisar** stack traces e diagnosticar problemas complexos
- ✅ **Prevenir** bugs através de boas práticas de desenvolvimento
- ✅ **Depurar** código em Java, PHP e Python de forma eficaz

## 📖 Conteúdo do Módulo

### 1️⃣ [Introdução à Depuração](01-introducao-depuracao.md)
**🎓 Fundamentos Teóricos**

Compreenda a base acadêmica e profissional da depuração:
- O que é depuração e por que é importante
- História do debugging (desde Grace Hopper)
- **Vantagens da depuração sistemática:**
  - Compreensão profunda do código
  - Melhoria na qualidade do software
  - Desenvolvimento de habilidades técnicas
  - Prevenção de problemas futuros
- **Desvantagens e desafios:**
  - Consumo de tempo
  - Complexidade em sistemas distribuídos
  - Heisenbug e outros problemas difíceis
  - Carga cognitiva
- Tipos de erros: sintaxe, runtime, lógicos, semânticos
- Princípios fundamentais da depuração
- Processo sistemático (TRAFFIC)
- **Citações de autores renomados:**
  - Steve McConnell sobre tempo gasto em debugging
  - Robert C. Martin sobre complexidade e depuração
  - Andreas Zeller sobre debugging científico
  - Brian Kernighan sobre print debugging

**📊 Estatísticas da indústria:**
- Desenvolvedores gastam 35-50% do tempo em depuração
- Bugs custam até 100x mais em produção
- Projetos com boas práticas têm 60% menos defeitos

---

### 2️⃣ [Técnicas e Metodologias](02-tecnicas-depuracao.md)
**🔍 Técnicas Clássicas e Avançadas**

Domine as principais técnicas de depuração:

**Técnicas Clássicas:**
- Print Debugging (printf debugging)
- Logging estruturado
- Breakpoint debugging
- Stack trace analysis
- Rubber duck debugging
- Binary search debugging
- Delta debugging

**Metodologias:**
- The Scientific Method (método científico)
- The 9 Rules of Debugging (David Agans)
- TRAFFIC Process (Andreas Zeller)

**Técnicas Avançadas:**
- Mutation testing
- Fuzzing
- Code slicing
- Time travel debugging
- Postmortem debugging

**Exemplos práticos em:**
- Java (com logging SLF4J)
- PHP (com error_log e Monolog)
- Python (com módulo logging)

---

### 3️⃣ [Ferramentas de Depuração](03-ferramentas-depuracao.md)
**🛠️ Ferramentas Profissionais**

Conheça e utilize ferramentas essenciais:

**Debuggers de IDE:**
- IntelliJ IDEA (Java, Kotlin)
- Visual Studio Code (multi-linguagem)
- Eclipse (Java)
- PhpStorm (PHP + Xdebug)
- PyCharm (Python)

**Debuggers de Linha de Comando:**
- GDB (GNU Debugger)
- JDB (Java Debugger)
- PDB (Python Debugger)
- LLDB (LLVM Debugger)

**Ferramentas de Análise:**
- Valgrind (memory debugging)
- VisualVM (Java profiling)
- Eclipse MAT (memory analyzer)
- Xdebug (PHP profiling)
- Chrome DevTools (JavaScript/Web)

**Frameworks de Logging:**
- Log4j (Java)
- Monolog (PHP)
- Python logging
- Configurações avançadas

**Monitoramento:**
- Application Performance Monitoring (APM)
- ELK Stack (Elasticsearch, Logstash, Kibana)
- Prometheus + Grafana

---

### 4️⃣ [Depuração em Java](04-depuracao-java.md)
**☕ Exemplos Práticos em Java**

Cenários reais do dia a dia:

1. **NullPointerException**
   - Diagnóstico com IntelliJ
   - Solução com Optional
   - Programação defensiva

2. **ConcurrentModificationException**
   - Entenda o problema
   - Soluções com Iterator
   - Uso de Streams

3. **Memory Leak**
   - Detectar com VisualVM
   - Análise de heap dump
   - Soluções com caches limitados

4. **Deadlock**
   - Thread dump analysis
   - Identificação com jstack
   - Prevenção com lock ordering

5. **Bug Intermitente (Race Condition)**
   - Debugging de código paralelo
   - Sincronização apropriada
   - Testes de concorrência

**Técnicas Avançadas:**
- Conditional breakpoints
- Exception breakpoints
- Method breakpoints
- Field watchpoints
- Remote debugging
- Hot swap

---

### 5️⃣ [Depuração em PHP](05-depuracao-php.md)
**🐘 Exemplos Práticos em PHP**

Cenários comuns em desenvolvimento PHP:

1. **Undefined Variable/Index**
   - Debugging com var_dump
   - Uso de isset() e array_key_exists()
   - Null coalescing operator

2. **SQL Injection e Erros de Query**
   - Detectar vulnerabilidades
   - Prepared statements
   - Error logging adequado

3. **Erro de Lógica em Cálculo**
   - Testes revelam problemas
   - PHPUnit para validação
   - Logging estratégico

4. **Memory Limit Exceeded**
   - Processar arquivos grandes
   - Streaming de dados
   - Monitoramento de memória

5. **Sessões Não Funcionando**
   - Debug de sessões
   - Problemas comuns
   - Soluções para cookies

**Xdebug:**
- Instalação e configuração
- Integração com IDEs
- Profiling de performance
- Step debugging

**Logging Profissional:**
- Monolog
- Error handlers customizados
- Exception handlers globais

---

### 6️⃣ [Depuração em Python](06-depuracao-python.md)
**🐍 Exemplos Práticos em Python**

Problemas típicos em Python:

1. **IndentationError e SyntaxError**
   - Detecção e correção
   - Ferramentas de visualização

2. **AttributeError e TypeError**
   - dir() e type()
   - Type hints
   - Static type checking com mypy

3. **IndexError e KeyError**
   - Debugging de listas e dicionários
   - Métodos seguros (get, defaultdict)

4. **Memory Leak em Loop**
   - Detectar com memory_profiler
   - Generators para economia de memória
   - Profiling

5. **Bug em Código Assíncrono**
   - Debugging de async/await
   - Timing issues
   - Logging com timestamps

**Python Debugger (PDB):**
- Comandos essenciais
- breakpoint() (Python 3.7+)
- PDB++ (melhorado)

**IPython/Jupyter:**
- IPython debugger
- Magic commands
- %debug pós-mortem

**Profiling:**
- cProfile
- line_profiler
- memory_profiler

---

### 7️⃣ [Boas Práticas e Prevenção](07-boas-praticas.md)
**🎯 Prevenir é Melhor que Remediar**

Escreva código que não precisa de debug:

**Princípios de Prevenção:**
- KISS (Keep It Simple, Stupid)
- DRY (Don't Repeat Yourself)
- YAGNI (You Aren't Gonna Need It)
- Fail Fast

**Defensive Programming:**
- Validação de entrada
- Assertions para invariantes
- Cópias defensivas

**Test-Driven Development (TDD):**
- Ciclo Red-Green-Refactor
- Cobertura de código
- Testes como documentação

**Code Review:**
- Checklist de revisão
- Pair programming
- Cultura de qualidade

**Análise Estática:**
- Java: SpotBugs, PMD, Checkstyle
- Python: pylint, flake8, mypy
- PHP: PHPStan, Psalm

**Design Patterns:**
- Null Object Pattern
- Builder Pattern
- Strategy Pattern

**Logging Estratégico:**
- O que logar e o que não logar
- Níveis apropriados
- Logging estruturado

**Monitoramento:**
- Métricas importantes
- Alertas proativos
- Observability

---

## 🛤️ Trilha de Aprendizado Recomendada

### 🌟 Para Iniciantes
**Foco: Fundamentos e práticas básicas**

1. [Introdução à Depuração](01-introducao-depuracao.md) (2-3 horas)
   - Leia com atenção as citações de autores
   - Entenda vantagens e desvantagens
   - Compreenda os tipos de erros

2. [Técnicas Básicas](02-tecnicas-depuracao.md) (3-4 horas)
   - Domine print debugging
   - Aprenda logging básico
   - Pratique stack trace analysis

3. Escolha sua linguagem principal:
   - [Java](04-depuracao-java.md) OU
   - [PHP](05-depuracao-php.md) OU
   - [Python](06-depuracao-python.md)

4. [Boas Práticas Básicas](07-boas-praticas.md) (2 horas)
   - Foque em prevenção
   - Aprenda code review

**Tempo estimado:** 15-20 horas

---

### 🚀 Para Intermediários
**Foco: Ferramentas e técnicas avançadas**

1. Revise a introdução teórica
2. [Técnicas Avançadas](02-tecnicas-depuracao.md)
   - Time travel debugging
   - Delta debugging
   - Mutation testing

3. [Ferramentas Profissionais](03-ferramentas-depuracao.md)
   - Configure debugger da sua IDE
   - Aprenda profiling
   - Explore APM tools

4. Estude debugging nas 3 linguagens:
   - [Java](04-depuracao-java.md)
   - [PHP](05-depuracao-php.md)
   - [Python](06-depuracao-python.md)

5. [Análise Estática e TDD](07-boas-praticas.md)

**Tempo estimado:** 30-40 horas

---

### 🏢 Para Avançados
**Foco: Debugging em produção e sistemas complexos**

1. Todos os documentos do módulo
2. Foco em cenários complexos:
   - Memory leaks
   - Deadlocks
   - Race conditions
   - Bugs em produção

3. Ferramentas de produção:
   - APM (New Relic, Datadog)
   - Distributed tracing
   - Log aggregation (ELK)

4. Cultura e processos:
   - Postmortem analysis
   - Incident management
   - SRE practices

**Tempo estimado:** 50+ horas

---

## 💻 Exercícios Práticos

### Exercício 1: Print Debugging
Dado um código com bug, use print statements para localizar o problema.

### Exercício 2: Debugger IDE
Configure breakpoints e use step debugging para encontrar um bug de lógica.

### Exercício 3: Memory Leak
Identifique e corrija um memory leak usando profiler.

### Exercício 4: Análise de Stack Trace
Analise stack traces reais e identifique a origem dos problemas.

### Exercício 5: Code Review
Revise código de um colega procurando possíveis bugs.

---

## 🛠️ Ferramentas Necessárias

### Básico
- Editor de código (VS Code, Sublime, Atom)
- Git para versionamento

### Java
- JDK 17+
- IntelliJ IDEA Community ou Eclipse
- Maven ou Gradle

### PHP
- PHP 8.0+
- Xdebug
- Composer
- PhpStorm ou VS Code

### Python
- Python 3.8+
- Jupyter Notebook
- PyCharm Community ou VS Code

---

## 📊 Avaliação de Conhecimento

**Após completar o módulo, você deve ser capaz de:**

- [ ] Explicar por que depuração é importante (com dados)
- [ ] Citar 3+ autores de livros sobre debugging
- [ ] Descrever o processo TRAFFIC de depuração
- [ ] Usar debugger da IDE com breakpoints
- [ ] Analisar e interpretar stack traces
- [ ] Configurar logging estruturado
- [ ] Identificar memory leaks com profiler
- [ ] Detectar e prevenir race conditions
- [ ] Aplicar TDD em desenvolvimento
- [ ] Realizar code reviews eficazes
- [ ] Usar ferramentas de análise estática

---

## 📚 Bibliografia Recomendada

### Livros Essenciais

1. **Zeller, Andreas** (2009). *"Why Programs Fail: A Guide to Systematic Debugging"*. 2nd Edition. Morgan Kaufmann.
   - 📌 O livro mais completo sobre debugging científico

2. **Agans, David J.** (2006). *"Debugging: The 9 Indispensable Rules"*. AMACOM.
   - 📌 Regras práticas aplicáveis a qualquer situação

3. **McConnell, Steve** (2004). *"Code Complete: A Practical Handbook of Software Construction"*. 2nd Edition. Microsoft Press.
   - 📌 Capítulos sobre debugging e testing

4. **Kernighan, Brian W.; Pike, Rob** (1999). *"The Practice of Programming"*. Addison-Wesley.
   - 📌 Capítulo 5: "Debugging" - princípios atemporais

5. **Martin, Robert C.** (2008). *"Clean Code: A Handbook of Agile Software Craftsmanship"*. Prentice Hall.
   - 📌 Prevenção através de código limpo

### Artigos e Recursos Online

- [The Art of Debugging](https://www.nostarch.com/debugging.htm)
- [Effective Debugging - Diomidis Spinellis](https://www.spinellis.gr/debugging/)
- [Python Debugging With pdb](https://realpython.com/python-debugging-pdb/)
- [Java Debugging Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/)

---

## 🤝 Como Estudar Este Material

### 📖 Leitura Ativa
1. **Não apenas leia, pratique**
2. **Anote conceitos importantes**
3. **Questione e pesquise mais**
4. **Relacione com sua experiência**

### 💪 Prática Deliberada
1. **Reproduza os exemplos**
2. **Modifique e experimente**
3. **Crie seus próprios bugs para praticar**
4. **Debug código de projetos reais**

### 👥 Aprendizado Colaborativo
1. **Discuta com colegas**
2. **Faça pair programming**
3. **Participe de code reviews**
4. **Ensine outros (melhor forma de aprender)**

---

## 📞 Suporte e Discussões

- **Dúvidas sobre conteúdo:** Consulte os documentos específicos
- **Problemas técnicos:** Abra uma issue no repositório
- **Discussões:** Use as GitHub Discussions
- **Contribuições:** Pull requests são bem-vindos!

---

## ⭐ Próximos Passos

Após dominar a depuração, continue seu desenvolvimento:

- 🧪 **Testes Automatizados**: Unit, Integration, E2E
- 🏗️ **Arquitetura de Software**: Design patterns, SOLID
- 🚀 **DevOps e CI/CD**: Automação, deploy, monitoramento
- 🔒 **Segurança**: OWASP, secure coding practices
- 📊 **Performance**: Profiling, otimização, scalability

---

<div align="center">

**🐛 Domine a arte de encontrar e corrigir bugs!**

*"Debugging is twice as hard as writing the code in the first place."*  
— Brian Kernighan

**Parte do repositório Aulas de Graduação - Ciência da Computação**

</div>
