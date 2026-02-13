# Disciplina de Projetos - Desenvolvimento de Software para a Sociedade

> Desenvolva software que impacta positivamente a sociedade, aplicando conceitos de empreendedorismo, gestão de projetos e tecnologia

## Comece Aqui

**Novo na disciplina?** Escolha seu caminho:

- 📖 **[Guia Rápido - Primeiros Passos](guia-rapido.md)** ← Comece por aqui!
- 📝 **[Template: Proposta de Projeto Novo](template-proposta-projeto.md)** - Para projetos do zero
- 🔄 **[Template: Melhorias em Projeto Existente](template-melhorias-projeto.md)** - Para evoluir projetos
- 💻 **[Aprenda Git/GitHub](../versionamento/README.md)** - Essencial para a disciplina

---

## Visão Geral da Disciplina

Esta disciplina tem como objetivo **orientar estudantes na construção de software com impacto social**, simulando o desenvolvimento de uma ideia ou negócio real. Os alunos trabalharão em grupo ou individualmente, aplicando tecnologia para resolver problemas reais da sociedade.

### Objetivos de Aprendizagem

Ao final desta disciplina, você será capaz de:

- ✅ **Identificar problemas reais** que podem ser resolvidos com tecnologia
- ✅ **Desenvolver uma solução de software** que beneficie a sociedade
- ✅ **Trabalhar em equipe** de forma colaborativa e profissional
- ✅ **Gerenciar um projeto** usando metodologias ágeis e ferramentas modernas
- ✅ **Aplicar boas práticas** de versionamento e controle de código
- ✅ **Apresentar resultados** de forma clara e profissional

---

## Estrutura do Projeto

### Composição da Equipe
- **Mínimo**: 1 aluno (projeto individual)
- **Máximo**: 8 alunos (equipe)
- **Recomendado**: 3-5 alunos para melhor divisão de trabalho

### Modalidades de Projeto

#### 🆕 Opção 1: Projeto Novo
Desenvolva uma nova solução do zero para resolver um problema identificado.

**Vantagens:**
- Total liberdade criativa
- Começa com tecnologias modernas
- Aprende desde o planejamento inicial

#### 🔄 Opção 2: Melhoria de Projeto Existente
Use um projeto que já existe (pessoal, da empresa, de outra disciplina) e implemente melhorias significativas.

**Requisitos:**
- **Explicação clara** do projeto original
- **Documentação das melhorias** propostas
- **Justificativa técnica** das mudanças
- Ver seção [Como Documentar Melhorias em Projetos Existentes](#-como-documentar-melhorias-em-projetos-existentes)

---

## ⏱️ Escopo e Prazo

### 📅 Duração: 3 Meses

**⚠️ IMPORTANTE**: O projeto **NÃO precisa ser grandioso**! 

Foque em:
- ✅ **Escopo realista** que pode ser completado em 3 meses
- ✅ **Funcionalidades essenciais** bem implementadas
- ✅ **Qualidade** ao invés de quantidade
- ✅ **MVP** (Minimum Viable Product) funcional

### 💡 Exemplos de Escopo Adequado

| ❌ Escopo Muito Grande | ✅ Escopo Adequado |
|------------------------|-------------------|
| Rede social completa tipo Facebook | Sistema de doação entre vizinhos |
| ERP empresarial completo | Sistema de gestão para pequena ONG |
| E-commerce multi-vendor | Marketplace local de produtos artesanais |
| Netflix para educação | Plataforma de compartilhamento de aulas gravadas |

---

## Metodologia de Desenvolvimento

### 1️⃣ Identificação do Problema

#### Como Encontrar um Bom Problema para Resolver?

**Método do Design Thinking:**
1. **Empatia**: Observe problemas reais ao seu redor
2. **Definição**: Formule o problema de forma clara
3. **Ideação**: Brainstorm de possíveis soluções
4. **Prototipação**: Crie uma versão inicial
5. **Teste**: Valide com usuários reais

**Perguntas-chave:**
- Que problemas você ou pessoas próximas enfrentam no dia a dia?
- Que processos manuais poderiam ser automatizados?
- Que informações são difíceis de acessar ou organizar?
- Como tornar serviços mais acessíveis para todos?

#### 🔍 Exemplos de Problemas Sociais

| Área | Problema | Possível Solução |
|------|----------|------------------|
| **Educação** | Dificuldade de acesso a materiais didáticos | Plataforma de compartilhamento gratuito |
| **Saúde** | Falta de controle de medicamentos | App de lembretes e histórico médico |
| **Meio Ambiente** | Descarte inadequado de resíduos | Sistema de agendamento de coleta seletiva |
| **Comunidade** | Falta de comunicação em bairros | Rede social hiperlocal |
| **Acessibilidade** | Barreiras para pessoas com deficiência | Mapa colaborativo de acessibilidade |

### 2️⃣ Validação da Ideia

Antes de começar o desenvolvimento:

- [ ] **Pesquise soluções existentes** - O que já existe? Como você pode fazer melhor?
- [ ] **Converse com potenciais usuários** - O problema é real para eles?
- [ ] **Defina o MVP** - Quais são as 3 funcionalidades essenciais?
- [ ] **Avalie a viabilidade técnica** - Você tem conhecimento/recursos para desenvolver?
- [ ] **Estime o tempo** - É possível em 3 meses?

### 3️⃣ Planejamento Tecnológico

#### Escolha da Stack Tecnológica

**Critérios de seleção:**
- ✅ **Familiaridade da equipe** com as tecnologias
- ✅ **Adequação** ao tipo de problema
- ✅ **Recursos disponíveis** (hospedagem, APIs, bibliotecas)
- ✅ **Documentação** e comunidade ativa

**Exemplos de Stacks por Tipo de Projeto:**

| Tipo de Projeto | Stack Sugerida |
|-----------------|---------------|
| **Web App** | Frontend: React/Vue + Backend: Node.js/Python/PHP |
| **Mobile App** | React Native / Flutter |
| **Desktop App** | Electron / Java / C# |
| **Análise de Dados** | Python + Pandas + Jupyter |
| **IoT** | Arduino/Raspberry Pi + Python/C++ |
| **Chatbot** | Python + NLP libraries + API WhatsApp/Telegram |

#### Arquitetura do Sistema

Planeje a arquitetura desde o início:

```
┌─────────────────────────────────────────────┐
│           FRONTEND (Interface)              │
│     Web / Mobile / Desktop                  │
└────────────────┬────────────────────────────┘
                 │
                 ↓ HTTP/REST API
┌─────────────────────────────────────────────┐
│           BACKEND (Lógica)                  │
│     API / Serviços / Regras de Negócio     │
└────────────────┬────────────────────────────┘
                 │
                 ↓ SQL/NoSQL
┌─────────────────────────────────────────────┐
│        DATABASE (Armazenamento)             │
│     MySQL / PostgreSQL / MongoDB           │
└─────────────────────────────────────────────┘
```

---

## 💻 Gerenciamento com GitHub

### 🔴 OBRIGATÓRIO: Uso do GitHub

Todos os projetos **DEVEM** usar GitHub para:

- ✅ **Controle de versão** de todo o código
- ✅ **Gestão de tarefas** via Issues e Projects
- ✅ **Colaboração** via Pull Requests
- ✅ **Documentação** do projeto (README, Wiki)
- ✅ **Organização** de releases e versões

### 📚 Aprenda Git e GitHub

**Se você não conhece Git/GitHub, comece por aqui:**

➡️ **[Guia Completo de Git e GitHub](../versionamento/README.md)** ⬅️

O guia contém:
1. [Introdução Acadêmica ao Git](../versionamento/01-introducao-academica.md)
2. [História do Git](../versionamento/02-historia-git.md)
3. [Como o Git Funciona](../versionamento/03-algoritmo-git.md)
4. [Boas Práticas Profissionais](../versionamento/04-trabalhando-com-git.md)
5. [Workflows Git (Git Flow, GitHub Flow)](../versionamento/05-arquiteturas-git.md)
6. [Como Usar o GitHub](../versionamento/06-como-usar-github.md)

### 🎯 Boas Práticas Esperadas

#### Commits
- ✅ **Frequência**: Commits regulares (múltiplos por semana)
- ✅ **Mensagens**: Descritivas e em português ou inglês consistente
- ✅ **Tamanho**: Commits pequenos e focados

**Exemplo de boas mensagens:**
```
✅ feat: adiciona tela de login
✅ fix: corrige erro no cálculo de descontos
✅ docs: atualiza instruções de instalação
❌ "mudanças" (muito vaga)
❌ "tudo funcionando" (não descreve o que foi feito)
```

#### Branches
Use branches para organizar o trabalho:
```
main            - Código em produção, sempre estável
develop         - Desenvolvimento ativo
feature/login   - Nova funcionalidade
bugfix/erro-x   - Correção de bug
```

#### Pull Requests
- Toda mudança significativa deve passar por Pull Request
- Pelo menos 1 revisão de outro membro da equipe
- Descrição clara do que foi implementado
- Testes realizados antes do merge

#### Issues
Use Issues para gerenciar tarefas:
- [ ] `feature`: Nova funcionalidade
- [ ] `bug`: Correção de problema
- [ ] `docs`: Documentação
- [ ] `enhancement`: Melhoria de algo existente

**Exemplo:**
```markdown
Título: [feature] Implementar sistema de autenticação

Descrição:
Como usuário, preciso fazer login no sistema para acessar funcionalidades privadas.

Tarefas:
- [ ] Criar tela de login
- [ ] Implementar autenticação JWT
- [ ] Adicionar proteção de rotas
- [ ] Criar testes unitários

Estimativa: 1 semana
Responsável: @aluno1
```

#### Projects
Use GitHub Projects para visualizar o progresso:
- Quadro Kanban: **To Do → In Progress → Review → Done**
- Sprint Planning (semanal ou quinzenal)
- Reuniões de acompanhamento

---

## 📊 Sistema de Avaliação

### Composição da Nota

A avaliação é dividida em três componentes principais:

| Critério | Peso | Descrição |
|----------|------|-----------|
| **GitHub** | 40% | Uso correto do GitHub e boas práticas |
| **Apresentação** | 30% | Apresentação online ou presencial do projeto |
| **Projeto** | 30% | Qualidade e completude do software |

---

### 📈 Critério 1: Uso do GitHub (40%)

#### Frequência de Commits (10 pontos)
- **9-10 pts**: 20+ commits bem distribuídos ao longo dos 3 meses
- **7-8 pts**: 15-19 commits distribuídos
- **5-6 pts**: 10-14 commits ou concentrados no final
- **3-4 pts**: 5-9 commits
- **0-2 pts**: Menos de 5 commits

#### Qualidade das Mensagens de Commit (10 pontos)
- **9-10 pts**: Mensagens descritivas, seguem padrão (Conventional Commits)
- **7-8 pts**: Mensagens claras na maioria dos commits
- **5-6 pts**: Mensagens razoáveis, algumas vagas
- **3-4 pts**: Mensagens pouco descritivas
- **0-2 pts**: Mensagens inúteis ("teste", "mudanças", etc.)

#### Organização do Repositório (10 pontos)
- **9-10 pts**: README completo, estrutura organizada, .gitignore configurado
- **7-8 pts**: README básico, estrutura razoável
- **5-6 pts**: Documentação incompleta
- **3-4 pts**: Pouca organização
- **0-2 pts**: Repositório desorganizado

#### Uso de Issues e Projects (5 pontos)
- **5 pts**: Issues bem documentadas, Projects atualizado
- **3-4 pts**: Algumas Issues criadas
- **1-2 pts**: Uso mínimo
- **0 pts**: Não usou

#### Uso de Branches e Pull Requests (5 pontos)
- **5 pts**: Branches organizadas, PRs com review
- **3-4 pts**: Usa branches básicas
- **1-2 pts**: Tudo na main
- **0 pts**: Não usou branches

**Total GitHub: 40 pontos**

---

### 🎤 Critério 2: Apresentação (30%)

A apresentação pode ser **online (vídeo) ou presencial**.

#### Estrutura da Apresentação (15-20 minutos)

1. **Introdução** (2 min)
   - Nome do projeto e equipe
   - Problema identificado
   - Público-alvo

2. **Solução Proposta** (3 min)
   - Como o software resolve o problema
   - Principais funcionalidades
   - Diferenciais da solução

3. **Demonstração** (8 min)
   - Demo ao vivo do sistema funcionando
   - Principais fluxos de uso
   - Mostrar código relevante (opcional)

4. **Tecnologias e Desafios** (3 min)
   - Stack tecnológica utilizada
   - Principais desafios enfrentados
   - Lições aprendidas

5. **Próximos Passos** (2 min)
   - Melhorias futuras
   - Escalabilidade
   - Conclusão

#### Avaliação da Apresentação

| Critério | Pontos |
|----------|--------|
| **Clareza e organização** | 10 |
| **Qualidade da demonstração** | 10 |
| **Domínio técnico demonstrado** | 5 |
| **Profissionalismo** | 5 |

**Total Apresentação: 30 pontos**

---

### 💻 Critério 3: Projeto (30%)

#### Funcionalidade (10 pontos)
- **9-10 pts**: Todas as funcionalidades principais implementadas e funcionando
- **7-8 pts**: Maioria das funcionalidades funcionais
- **5-6 pts**: Algumas funcionalidades incompletas
- **3-4 pts**: Poucas funcionalidades completas
- **0-2 pts**: Projeto não funcional

#### Qualidade do Código (10 pontos)
- **9-10 pts**: Código limpo, organizado, comentado onde necessário
- **7-8 pts**: Código razoavelmente organizado
- **5-6 pts**: Código funcional mas desorganizado
- **3-4 pts**: Código confuso
- **0-2 pts**: Código de baixa qualidade

#### Impacto Social (5 pontos)
- **5 pts**: Solução tem potencial real de impacto social
- **3-4 pts**: Solução resolve um problema identificado
- **1-2 pts**: Impacto social limitado
- **0 pts**: Não tem foco social

#### Documentação (5 pontos)
- **5 pts**: README completo com instruções de instalação e uso
- **3-4 pts**: Documentação básica presente
- **1-2 pts**: Documentação incompleta
- **0 pts**: Sem documentação

**Total Projeto: 30 pontos**

---

## Como Documentar Melhorias em Projetos Existentes

Se você optar por melhorar um projeto existente, siga este guia de documentação acadêmica:

### 1. Descrição do Projeto Original

Crie uma seção no README explicando:

```markdown
## 📋 Contexto do Projeto Original

### Descrição
[Descreva brevemente o projeto original, sua finalidade e principais funcionalidades]

### Funcionalidades Existentes
- Funcionalidade 1
- Funcionalidade 2
- Funcionalidade 3

### Limitações Identificadas
- Limitação 1: [Descrição e impacto]
- Limitação 2: [Descrição e impacto]
- Limitação 3: [Descrição e impacto]

### Tecnologias Utilizadas
- Frontend: [tecnologia]
- Backend: [tecnologia]
- Banco de Dados: [tecnologia]
```

### 2. Proposta de Melhorias

```markdown
## 🚀 Melhorias Propostas

### Melhoria 1: [Nome]
**Problema:** [Que problema essa melhoria resolve?]
**Solução:** [Como será implementada?]
**Justificativa:** [Por que essa melhoria é importante?]
**Impacto esperado:** [Que benefícios trará?]

### Melhoria 2: [Nome]
[Repetir estrutura acima]
```

### 3. Plano de Implementação

```markdown
## 📅 Cronograma de Implementação

### Mês 1: Análise e Setup
- [ ] Análise detalhada do código existente
- [ ] Configuração do ambiente de desenvolvimento
- [ ] Criação de branch de desenvolvimento
- [ ] Definição de arquitetura das melhorias

### Mês 2: Desenvolvimento
- [ ] Implementação da Melhoria 1
- [ ] Implementação da Melhoria 2
- [ ] Testes unitários
- [ ] Testes de integração

### Mês 3: Refinamento e Entrega
- [ ] Correção de bugs
- [ ] Documentação
- [ ] Preparação da apresentação
- [ ] Deploy e testes finais
```

### 4. Comparação Antes/Depois

Documente com evidências visuais:

```markdown
## 📊 Resultados das Melhorias

### Antes
![Screenshot antes](docs/antes.png)
- Problema X presente
- Limitação Y existente

### Depois
![Screenshot depois](docs/depois.png)
- Problema X resolvido
- Limitação Y superada
- Nova funcionalidade Z adicionada

### Métricas de Melhoria
- Performance: 30% mais rápido
- Usabilidade: Redução de 50% nos cliques necessários
- Acessibilidade: WCAG 2.1 Level AA alcançado
```

### 5. Referências Bibliográficas

Sempre cite fontes acadêmicas e profissionais:

```markdown
## Referências

1. SOMMERVILLE, Ian. **Engenharia de Software**. 10ª ed. Pearson, 2018.
2. MARTIN, Robert C. **Código Limpo: Habilidades Práticas do Agile Software**. Alta Books, 2009.
3. [Documentação oficial da tecnologia X](https://...)
4. [Artigo sobre a solução do problema Y](https://...)
```

---

## Sugestões de Temas de Projetos

### 🏥 Área: Saúde

#### 1. Sistema de Agendamento para Postos de Saúde
**Problema:** Filas longas e falta de organização em postos de saúde públicos  
**Solução:** App/Web para agendamento online de consultas  
**Tecnologias:** React + Node.js + PostgreSQL  
**Escopo 3 meses:** Agendamento básico, lembretes por email, painel administrativo

#### 2. Controle de Medicamentos Pessoais
**Problema:** Pessoas esquecem de tomar medicamentos ou perdem o histórico  
**Solução:** App mobile com lembretes e histórico de medicação  
**Tecnologias:** React Native + Firebase  
**Escopo 3 meses:** Cadastro de medicamentos, alarmes, histórico

### 🎓 Área: Educação

#### 3. Plataforma de Troca de Livros Didáticos
**Problema:** Livros caros e pouco reutilizados  
**Solução:** Marketplace para troca/doação entre estudantes  
**Tecnologias:** Vue.js + Python Django + SQLite  
**Escopo 3 meses:** Cadastro de livros, sistema de matches, chat básico

#### 4. Banco de Questões Colaborativo
**Problema:** Dificuldade de encontrar exercícios para estudo  
**Solução:** Plataforma onde alunos compartilham e resolvem questões  
**Tecnologias:** React + Node.js + MongoDB  
**Escopo 3 meses:** Cadastro de questões, busca por assunto, ranking

### 🌱 Área: Meio Ambiente

#### 5. Mapa de Coleta Seletiva
**Problema:** Pessoas não sabem onde descartar materiais recicláveis  
**Solução:** Mapa colaborativo de pontos de coleta  
**Tecnologias:** React + Google Maps API + Firebase  
**Escopo 3 meses:** Mapa interativo, cadastro de pontos, avaliações

#### 6. Calculadora de Pegada de Carbono
**Problema:** Consciência ambiental limitada sobre impacto pessoal  
**Solução:** App que calcula e sugere reduções no consumo  
**Tecnologias:** React Native + Python (cálculos)  
**Escopo 3 meses:** Calculadora básica, dicas personalizadas, histórico

### 👥 Área: Comunidade

#### 7. Rede de Doações Locais
**Problema:** Dificuldade de conectar doadores e receptores  
**Solução:** Plataforma para anunciar e buscar doações na vizinhança  
**Tecnologias:** React + Node.js + MongoDB + Geolocalização  
**Escopo 3 meses:** Anúncios, busca por localização, chat simples

#### 8. Sistema de Carona Solidária Universitária
**Problema:** Custos altos de transporte para estudantes  
**Solução:** App para organizar caronas entre colegas  
**Tecnologias:** Flutter + Firebase + Google Maps  
**Escopo 3 meses:** Oferta/busca de caronas, avaliações, notificações

### ♿ Área: Acessibilidade

#### 9. Mapa Colaborativo de Acessibilidade
**Problema:** Falta de informação sobre acessibilidade de locais  
**Solução:** App onde usuários avaliam acessibilidade de estabelecimentos  
**Tecnologias:** React Native + Firebase + Google Maps  
**Escopo 3 meses:** Avaliação de locais, mapa, filtros de acessibilidade

#### 10. Leitor de Textos com IA para pessoas com deficiência visual
**Problema:** Dificuldade de acesso a conteúdo escrito  
**Solução:** App que fotografa e lê textos em voz alta  
**Tecnologias:** React Native + OCR API + Text-to-Speech  
**Escopo 3 meses:** Captura de imagem, reconhecimento, leitura

### 💼 Área: Economia Solidária

#### 11. Marketplace de Produtos Artesanais Locais
**Problema:** Artesãos têm dificuldade de divulgar e vender  
**Solução:** E-commerce focado em produtores locais  
**Tecnologias:** React + Node.js + Stripe (pagamentos)  
**Escopo 3 meses:** Cadastro de produtos, carrinho, checkout simplificado

#### 12. Sistema de Gestão para Pequenas ONGs
**Problema:** ONGs pequenas não têm sistemas de gestão acessíveis  
**Solução:** ERP simplificado para controle de doações e voluntários  
**Tecnologias:** Vue.js + PHP + MySQL  
**Escopo 3 meses:** Cadastros básicos, relatórios, controle financeiro simples

---

## Referências e Material de Apoio

### Livros Recomendados

#### Empreendedorismo e Startups
- **The Lean Startup** - Eric Ries
  - Como validar ideias rapidamente e iterar com base em feedback

- **Sprint: Como Resolver Grandes Problemas e Testar Novas Ideias em Apenas 5 Dias** - Jake Knapp
  - Metodologia do Google para resolver problemas complexos

- **Business Model Generation** - Alexander Osterwalder
  - Como criar modelos de negócio inovadores

#### Engenharia de Software
- **Engenharia de Software** - Ian Sommerville
  - Processos, metodologias e boas práticas

- **Código Limpo** - Robert C. Martin
  - Como escrever código mantível e profissional

- **Arquitetura Limpa** - Robert C. Martin
  - Princípios de design de software escalável

#### Gestão de Projetos
- **Scrum: A Arte de Fazer o Dobro do Trabalho na Metade do Tempo** - Jeff Sutherland
  - Metodologia ágil para gestão de projetos

- **Gestão de Projetos com Git e GitHub** - Diversos autores
  - Como usar GitHub para gerenciar projetos de software

### Recursos Online

#### Cursos e Tutoriais
- [freeCodeCamp](https://www.freecodecamp.org/) - Desenvolvimento web gratuito
- [Coursera](https://www.coursera.org/) - Cursos universitários online
- [Udemy](https://www.udemy.com/) - Cursos práticos de tecnologia
- [GitHub Learning Lab](https://lab.github.com/) - Aprenda Git e GitHub

#### Ferramentas de Design
- [Figma](https://www.figma.com/) - Design de interfaces (gratuito para estudantes)
- [Canva](https://www.canva.com/) - Design de apresentações e materiais
- [Draw.io](https://draw.io/) - Diagramas e fluxogramas

#### Ferramentas de Gestão
- [Trello](https://trello.com/) - Gestão visual de tarefas
- [GitHub Projects](https://github.com/features/issues/) - Gestão integrada ao código
- [Notion](https://www.notion.so/) - Documentação e organização

#### APIs e Serviços Úteis
- [RapidAPI](https://rapidapi.com/) - Marketplace de APIs
- [Firebase](https://firebase.google.com/) - Backend as a Service
- [Heroku](https://www.heroku.com/) - Hospedagem gratuita (tier gratuito)
- [Vercel](https://vercel.com/) - Hospedagem de frontend
- [Railway](https://railway.app/) - Hospedagem de aplicações

### Metodologias e Frameworks

#### Design Thinking
1. **Empatizar**: Entenda profundamente o usuário
2. **Definir**: Formule o problema claramente
3. **Idear**: Gere muitas soluções possíveis
4. **Prototipar**: Crie versões rápidas para testar
5. **Testar**: Valide com usuários reais

#### Lean Startup
- **Build**: Construa um MVP (Minimum Viable Product)
- **Measure**: Meça o resultado com métricas reais
- **Learn**: Aprenda e ajuste a direção (pivot ou persevere)

#### Scrum (Metodologia Ágil)
- **Sprints**: Ciclos de 1-2 semanas
- **Daily Stand-ups**: Reuniões rápidas diárias
- **Sprint Planning**: Planejamento de tarefas
- **Sprint Review**: Demonstração do que foi feito
- **Retrospective**: Lições aprendidas

---

## Perguntas Frequentes (FAQ)

### Sobre o Projeto

**P: Posso usar um projeto de trabalho ou de outra disciplina?**  
R: Sim! Desde que você documente claramente o que existia e o que será melhorado nesta disciplina. Veja a seção [Como Documentar Melhorias](#-como-documentar-melhorias-em-projetos-existentes).

**P: O projeto precisa estar 100% funcional?**  
R: Não necessariamente. O importante é ter um MVP funcional com as principais features. Um projeto 80% completo bem documentado é melhor que 100% mal feito.

**P: Posso mudar de ideia no meio do semestre?**  
R: É melhor evitar, mas se necessário, converse com o professor. Mudanças significativas devem ser justificadas e documentadas.

**P: Preciso hospedar o projeto online?**  
R: Não é obrigatório, mas é recomendado para facilitar a apresentação. Use serviços gratuitos como Vercel, Heroku ou Railway.

### Sobre o GitHub

**P: Toda a equipe precisa fazer commits?**  
R: Sim! A frequência de commits é avaliada. Todos os membros devem contribuir com código.

**P: Posso usar um repositório privado?**  
R: Sim, mas adicione o professor como colaborador para que possa avaliar.

**P: Esqueci de fazer commits regulares, o que faço?**  
R: Continue fazendo commits frequentes daqui pra frente. Melhor tarde do que nunca!

**P: Preciso criar issues para tudo?**  
R: Use issues para tarefas significativas. Não precisa criar issue para typos ou mudanças mínimas.

### Sobre a Apresentação

**P: A apresentação pode ser gravada?**  
R: Sim! Você pode gravar um vídeo de 15-20 minutos e enviar o link.

**P: Todos os membros precisam apresentar?**  
R: Sim, cada membro deve apresentar pelo menos uma parte do projeto.

**P: Posso mostrar slides ou precisa ser só demonstração?**  
R: Recomendamos: slides breves (5 min) + demonstração ao vivo (10 min) + Q&A (5 min).

**P: E se der algum erro durante a demonstração?**  
R: Tenha um vídeo gravado de backup. Erros acontecem, mas estar preparado é importante.

### Sobre a Equipe

**P: Posso fazer sozinho ou preciso de equipe?**  
R: Pode fazer sozinho (1 aluno), mas recomenda-se 3-5 alunos para melhor aprendizado.

**P: Como dividir as tarefas na equipe?**  
R: Use o GitHub Projects para distribuir Issues. Cada membro deve ter responsabilidades claras.

**P: E se um membro não contribuir?**  
R: Documente no GitHub (commits, issues, PRs) e comunique o professor. A avaliação pode ser individualizada.

---

## 📞 Suporte e Contato

### Como Obter Ajuda

1. **Dúvidas sobre Git/GitHub**: Consulte o [guia completo](../versionamento/README.md)
2. **Dúvidas sobre o projeto**: Abra uma issue no repositório do projeto
3. **Dúvidas sobre a disciplina**: Entre em contato com o professor nas aulas de pré-aula

---

## 📅 Calendário e Marcos Importantes

### Timeline do Projeto

```
Semana 1-2:   Formação de equipes e escolha do projeto
Semana 3-4:   Definição do escopo e setup inicial
Semana 5-8:   Desenvolvimento Sprint 1 (features básicas)
Semana 9-12:  Desenvolvimento Sprint 2 (features avançadas)
Semana 13-14: Refinamento e documentação
Semana 15:    Apresentações finais
```

### Entregas Parciais (Sugeridas)

| Data | Entrega | Descrição |
|------|---------|-----------|
| Semana 2 | **Proposta de Projeto** | 1 página com problema, solução e equipe |
| Semana 6 | **MVP Funcional** | Primeira versão funcional básica |
| Semana 10 | **Alpha Release** | Versão com features principais |
| Semana 14 | **Release Final** | Versão completa para apresentação |
| Semana 15 | **Apresentação** | Apresentação do projeto |

---

## ✅ Checklist de Sucesso

Use este checklist para garantir que seu projeto está no caminho certo:

### Setup Inicial
- [ ] Equipe formada e papéis definidos
- [ ] Repositório GitHub criado
- [ ] README inicial escrito
- [ ] Estrutura de pastas organizada
- [ ] `.gitignore` configurado
- [ ] Todos os membros com acesso ao repo

### Desenvolvimento
- [ ] Commits frequentes (mínimo 2 por semana por pessoa)
- [ ] Mensagens de commit descritivas
- [ ] Branches para features organizadas
- [ ] Pull Requests com revisão
- [ ] Issues para tarefas principais
- [ ] GitHub Projects atualizado

### Documentação
- [ ] README completo com instruções de instalação
- [ ] Documentação da arquitetura
- [ ] Comentários no código onde necessário
- [ ] Diagramas (casos de uso, arquitetura, etc.)

### Qualidade
- [ ] Código testado e funcional
- [ ] Interface responsiva (se aplicável)
- [ ] Tratamento de erros implementado
- [ ] Código segue boas práticas

### Apresentação
- [ ] Slides/roteiro preparado
- [ ] Demonstração testada
- [ ] Todos os membros ensaiaram sua parte
- [ ] Vídeo de backup gravado (opcional)
- [ ] Perguntas possíveis antecipadas

---

## Exemplos de Projetos

### Projeto 1: "Vizinho Solidário"
**Equipe:** 4 alunos  
**Problema:** Dificuldade de pessoas em situação vulnerável encontrarem doadores  
**Solução:** App web que conecta doadores e receptores por proximidade  
**Stack:** React + Node.js + MongoDB + Google Maps API  
**Destaques:**
- 45 commits bem distribuídos
- Uso exemplar de GitHub Projects
- Impacto social claro
- Apresentação profissional

### Projeto 2: "MedicAlert"
**Equipe:** 3 alunos  
**Problema:** Idosos esquecem de tomar medicamentos  
**Solução:** App mobile com lembretes e histórico médico  
**Stack:** React Native + Firebase  
**Destaques:**
- Issues bem documentadas
- Código limpo e comentado
- Teste com usuários reais (idosos)
- Vídeo de apresentação bem produzido

### Projeto 3: "EcoMapa"
**Equipe:** 5 alunos  
**Problema:** Falta de informação sobre pontos de reciclagem  
**Solução:** Mapa colaborativo de coleta seletiva  
**Stack:** Vue.js + Python Django + PostgreSQL + Leaflet  
**Destaques:**
- Arquitetura bem planejada
- Uso de CI/CD com GitHub Actions
- Documentação técnica excelente
- Apresentação com métricas de impacto

---

## 🎯 Conclusão

Esta disciplina é uma oportunidade única de aplicar seus conhecimentos técnicos para **criar algo que importa**. Não se trata apenas de programar, mas de:

- 🌍 **Identificar problemas reais** na sociedade
- 💡 **Propor soluções criativas** usando tecnologia
- 👥 **Trabalhar em equipe** de forma profissional
- 📊 **Gerenciar projetos** com ferramentas modernas
- 🎤 **Comunicar resultados** de forma eficaz

Lembre-se:
- ✅ **Foco no escopo**: Melhor um projeto pequeno e completo que um grande e incompleto
- ✅ **Documente tudo**: GitHub, código, decisões técnicas
- ✅ **Commits frequentes**: Mostre o progresso do trabalho
- ✅ **Comunique-se**: Com a equipe e com o professor
- ✅ **Aprenda e divirta-se**: Este é um ambiente seguro para experimentar

**Bom projeto a todos!**

---

<div align="center">

**🎓 Disciplina de Projetos - Desenvolvimento de Software para a Sociedade**

*Parte do repositório Aulas de Graduação - Ciência da Computação*

*Desenvolvendo tecnologia que importa*

</div>
