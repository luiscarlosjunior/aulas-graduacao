# 📝 Template: Proposta de Projeto

> Use este template para documentar sua proposta de projeto para a disciplina

## 📋 Informações Básicas

### Nome do Projeto
`[Digite o nome do seu projeto aqui]`

### Equipe
| Nome | GitHub | Papel Principal |
|------|--------|-----------------|
| Nome 1 | @username1 | Frontend / Backend / Full Stack |
| Nome 2 | @username2 | Frontend / Backend / Full Stack |
| Nome 3 | @username3 | Frontend / Backend / Full Stack |

### Link do Repositório
`https://github.com/usuario/nome-do-projeto`

---

## 🎯 Identificação do Problema

### 1. Descrição do Problema
Descreva claramente o problema que seu projeto pretende resolver:
- **Quem** enfrenta esse problema?
- **Quando** esse problema ocorre?
- **Por que** esse problema é importante?
- **Qual** o impacto negativo desse problema?

**Exemplo:**
> *Idosos que vivem sozinhos frequentemente esquecem de tomar seus medicamentos nos horários corretos, o que pode levar a complicações graves de saúde. Cerca de 50% dos idosos não seguem corretamente suas prescrições médicas, resultando em internações evitáveis.*

### 2. Pesquisa de Soluções Existentes
Liste soluções que já existem e explique suas limitações:

| Solução Existente | Limitações | Como seu projeto é diferente |
|-------------------|------------|------------------------------|
| App X | Muito complexo para idosos | Interface simplificada e intuitiva |
| App Y | Pago e caro | Gratuito e acessível |
| App Z | Só para Android | Multiplataforma |

### 3. Público-Alvo
- **Idade:** [faixa etária]
- **Perfil:** [características do usuário]
- **Necessidades específicas:** [liste 3-5 necessidades]
- **Conhecimento tecnológico:** [iniciante/intermediário/avançado]

---

## 💡 Solução Proposta

### 1. Descrição da Solução
Descreva sua solução em 2-3 parágrafos. Foque em:
- Como o software resolve o problema identificado
- Principais funcionalidades
- Diferenciais da sua solução

**Exemplo:**
> *MedicAlert é um aplicativo mobile simples e intuitivo que ajuda idosos a gerenciar seus medicamentos. O app envia lembretes visuais e sonoros nos horários corretos, permite que familiares acompanhem remotamente se os medicamentos foram tomados, e mantém um histórico completo para mostrar ao médico nas consultas.*

### 2. Funcionalidades Principais (MVP)

Liste as 3-5 funcionalidades essenciais para o MVP:

- [ ] **Funcionalidade 1**: [Nome]
  - Descrição: [O que faz]
  - Valor para usuário: [Por que é importante]

- [ ] **Funcionalidade 2**: [Nome]
  - Descrição: [O que faz]
  - Valor para usuário: [Por que é importante]

- [ ] **Funcionalidade 3**: [Nome]
  - Descrição: [O que faz]
  - Valor para usuário: [Por que é importante]

### 3. Funcionalidades Futuras (Pós-MVP)

Funcionalidades que seriam interessantes mas não são essenciais:
- [ ] Integração com farmácias
- [ ] Lembretes de renovação de receitas
- [ ] Gráficos de adesão ao tratamento

---

## 🛠️ Especificações Técnicas

### 1. Arquitetura do Sistema

```
┌─────────────────────────┐
│      FRONTEND           │
│   [Tecnologia]          │
└───────────┬─────────────┘
            │
            ↓ REST API
┌─────────────────────────┐
│      BACKEND            │
│   [Tecnologia]          │
└───────────┬─────────────┘
            │
            ↓ SQL/NoSQL
┌─────────────────────────┐
│      DATABASE           │
│   [Tecnologia]          │
└─────────────────────────┘
```

### 2. Stack Tecnológica

| Camada | Tecnologia | Justificativa |
|--------|------------|---------------|
| **Frontend** | React / Vue / React Native | [Por que escolheu?] |
| **Backend** | Node.js / Python / PHP | [Por que escolheu?] |
| **Banco de Dados** | MySQL / MongoDB / PostgreSQL | [Por que escolheu?] |
| **Hospedagem** | Vercel / Heroku / Railway | [Por que escolheu?] |
| **Outras ferramentas** | [APIs, bibliotecas] | [Por que escolheu?] |

### 3. Requisitos Não-Funcionais

- **Performance**: [Ex: Carregar página inicial em menos de 2 segundos]
- **Segurança**: [Ex: Autenticação JWT, HTTPS obrigatório]
- **Usabilidade**: [Ex: Interface acessível seguindo WCAG 2.1]
- **Escalabilidade**: [Ex: Suportar até 1000 usuários simultâneos]

---

## 📅 Planejamento

### Cronograma de 3 Meses

#### Mês 1: Fundação (Semanas 1-4)
- **Semana 1-2**: Setup e Planejamento
  - [ ] Configurar repositório GitHub
  - [ ] Definir estrutura de pastas
  - [ ] Configurar ambiente de desenvolvimento
  - [ ] Criar wireframes/protótipos
  - [ ] Definir schema do banco de dados

- **Semana 3-4**: Funcionalidade Base
  - [ ] Implementar autenticação básica
  - [ ] Criar estrutura do banco de dados
  - [ ] Desenvolver telas principais (sem funcionalidades)
  - [ ] Setup de APIs básicas

#### Mês 2: Desenvolvimento (Semanas 5-8)
- **Semana 5-6**: Funcionalidades Core
  - [ ] Implementar Funcionalidade 1
  - [ ] Implementar Funcionalidade 2
  - [ ] Integração frontend-backend
  - [ ] Testes básicos

- **Semana 7-8**: Funcionalidades Adicionais
  - [ ] Implementar Funcionalidade 3
  - [ ] Refinamento de UI/UX
  - [ ] Tratamento de erros
  - [ ] Validações

#### Mês 3: Finalização (Semanas 9-12)
- **Semana 9-10**: Refinamento
  - [ ] Correção de bugs
  - [ ] Otimizações de performance
  - [ ] Testes com usuários
  - [ ] Ajustes baseados em feedback

- **Semana 11**: Documentação
  - [ ] README completo
  - [ ] Documentação de API (se aplicável)
  - [ ] Guia de instalação
  - [ ] Manual do usuário

- **Semana 12**: Apresentação
  - [ ] Preparar slides
  - [ ] Gravar demo
  - [ ] Ensaiar apresentação
  - [ ] Deploy final

### Divisão de Responsabilidades

| Membro | Responsabilidade Principal | Responsabilidade Secundária |
|--------|---------------------------|----------------------------|
| [Nome 1] | Frontend | Testes |
| [Nome 2] | Backend | Banco de Dados |
| [Nome 3] | Design/UX | Documentação |

---

## 📊 Métricas de Sucesso

### Como saberemos que o projeto foi bem-sucedido?

- [ ] **Funcional**: Todas as funcionalidades MVP implementadas e funcionando
- [ ] **Usabilidade**: 3+ usuários testaram e consideraram fácil de usar
- [ ] **Performance**: Tempo de resposta < 2 segundos
- [ ] **Código**: Sem bugs críticos, código organizado
- [ ] **Documentação**: README completo permite outro dev configurar o projeto
- [ ] **GitHub**: 20+ commits bem distribuídos, issues organizadas

### Métricas Quantitativas (se aplicável)
- Número de usuários cadastrados: [meta]
- Taxa de retenção: [meta]
- Tempo médio de uso: [meta]
- Satisfação do usuário (NPS): [meta]

---

## 🎨 Design e Experiência do Usuário

### 1. Fluxo Principal do Usuário

Descreva o fluxo principal passo a passo:

1. Usuário abre o app
2. [próximo passo]
3. [próximo passo]
4. [resultado final]

### 2. Wireframes/Protótipos

Link para protótipos (Figma, Adobe XD, etc.):
`[Link aqui]`

Ou inclua imagens:
```markdown
![Tela Inicial](docs/wireframes/tela-inicial.png)
![Tela Principal](docs/wireframes/tela-principal.png)
```

### 3. Princípios de Design

- **Simplicidade**: [Como será aplicado]
- **Acessibilidade**: [Como será aplicado]
- **Responsividade**: [Como será aplicado]
- **Feedback visual**: [Como será aplicado]

---

## 🔒 Considerações de Segurança

Liste considerações de segurança relevantes:

- [ ] Autenticação segura (senhas hasheadas)
- [ ] Proteção contra SQL Injection
- [ ] Proteção contra XSS
- [ ] HTTPS obrigatório
- [ ] Validação de dados de entrada
- [ ] [Outras relevantes ao projeto]

---

## 🌍 Impacto Social Esperado

### 1. Benefícios Diretos
- [Benefício 1]: [Descrição]
- [Benefício 2]: [Descrição]
- [Benefício 3]: [Descrição]

### 2. Potencial de Escala
Como este projeto poderia crescer e impactar mais pessoas?

**Exemplo:**
> *Inicialmente focado em idosos, o app poderia ser expandido para qualquer pessoa com regime de medicamentos. Parcerias com postos de saúde poderiam aumentar a adoção. Versão web permitiria acesso em qualquer dispositivo.*

### 3. Sustentabilidade
Como o projeto poderia ser mantido a longo prazo?

- [ ] Open source com comunidade de contribuidores
- [ ] Parcerias com instituições
- [ ] Modelo freemium (básico grátis, premium pago)
- [ ] Doações / Crowdfunding

---

## 📚 Referências

Liste fontes de pesquisa e inspiração:

### Pesquisa do Problema
1. [Artigo/Estudo sobre o problema]
2. [Estatísticas relevantes]
3. [Entrevistas com usuários]

### Referências Técnicas
1. [Documentação de tecnologias usadas]
2. [Tutoriais seguidos]
3. [Projetos similares que inspiraram]

### Literatura Acadêmica
1. SOMMERVILLE, Ian. **Engenharia de Software**. 10ª ed. Pearson, 2018.
2. [Outras referências acadêmicas relevantes]

---

## ✅ Aprovação

### Checklist de Validação da Proposta

Antes de submeter, verifique:

- [ ] Problema claramente definido e justificado
- [ ] Solução viável tecnicamente em 3 meses
- [ ] Público-alvo identificado
- [ ] Funcionalidades MVP bem definidas
- [ ] Stack tecnológica escolhida e justificada
- [ ] Cronograma realista
- [ ] Divisão de tarefas entre membros
- [ ] Repositório GitHub criado
- [ ] Impacto social claro
- [ ] Referências incluídas

### Revisão do Professor

- [ ] Proposta aprovada
- [ ] Proposta aprovada com ressalvas (ver comentários)
- [ ] Proposta necessita revisão

**Comentários do Professor:**
```
[Espaço para feedback]
```

---

## 📝 Notas e Observações

Use este espaço para anotações adicionais, dúvidas, ou informações complementares:

```
[Suas notas aqui]
```

---

<div align="center">

**🚀 Boa sorte com seu projeto!**

*Lembre-se: Melhor um MVP funcionando bem do que um projeto ambicioso incompleto*

</div>
