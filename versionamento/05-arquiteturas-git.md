# 🌿 Arquiteturas e Workflows Git

## Introdução

Diferentes projetos e equipes requerem diferentes estratégias de branching. Este documento explora as principais arquiteturas de trabalho com Git, quando usar cada uma, e suas vantagens e desvantagens.

## 1. Git Flow 🌊

### Visão Geral

**Git Flow** é um modelo de branching robusto criado por Vincent Driessen em 2010, ideal para projetos com releases planejadas.

### Estrutura de Branches

```
main (produção)
  ↓
develop (integração)
  ↓
feature/* (features)
  ↓
release/* (preparação release)
  ↓
hotfix/* (correções urgentes)
```

### Branches Principais

**1. main (ou master)**
- Código em produção
- Sempre estável
- Tags de versão
- Nunca commit direto

**2. develop**
- Branch de integração
- Código da próxima release
- Features integradas aqui
- Base para feature branches

### Branches de Suporte

**3. feature/***
```
Origem: develop
Destino: develop
Naming: feature/nome-da-feature
```

**4. release/***
```
Origem: develop
Destino: main + develop
Naming: release/1.0.0
```

**5. hotfix/***
```
Origem: main
Destino: main + develop
Naming: hotfix/1.0.1
```

### Workflow Completo

#### Desenvolvendo uma Feature

```bash
# 1. Criar feature branch
git checkout develop
git pull origin develop
git checkout -b feature/user-authentication

# 2. Desenvolver
git add .
git commit -m "Add login form"
git commit -m "Add authentication logic"
git commit -m "Add tests"

# 3. Atualizar com develop
git checkout develop
git pull origin develop
git checkout feature/user-authentication
git merge develop

# 4. Finalizar feature
git checkout develop
git merge --no-ff feature/user-authentication
git push origin develop
git branch -d feature/user-authentication
```

#### Criando uma Release

```bash
# 1. Criar release branch
git checkout develop
git pull origin develop
git checkout -b release/1.0.0

# 2. Preparar release (ajustes finais)
# - Atualizar versões
# - Gerar changelog
# - Ajustes de documentação
git commit -am "Bump version to 1.0.0"

# 3. Finalizar release
git checkout main
git merge --no-ff release/1.0.0
git tag -a v1.0.0 -m "Version 1.0.0"
git push origin main --tags

git checkout develop
git merge --no-ff release/1.0.0
git push origin develop

git branch -d release/1.0.0
```

#### Aplicando um Hotfix

```bash
# 1. Criar hotfix branch
git checkout main
git pull origin main
git checkout -b hotfix/1.0.1

# 2. Corrigir bug
git commit -am "Fix critical security vulnerability"

# 3. Finalizar hotfix
git checkout main
git merge --no-ff hotfix/1.0.1
git tag -a v1.0.1 -m "Version 1.0.1"
git push origin main --tags

git checkout develop
git merge --no-ff hotfix/1.0.1
git push origin develop

git branch -d hotfix/1.0.1
```

### Visualização Completa

```
                main
                  │
    v1.0          │         v1.1
      ●───────────●───────────●
      │           │           │
      │      release/1.1      │
      │           │           │
    develop       │           │
      ●───●───●───●───●───●───●
      │   │   │       │   │   │
      │   feature/A   │   │   │
      │   │           │   │   │
      │   ●───●───●───●   │   │
      │               │   │   │
      │           feature/B   │
      │               │       │
      │               ●───●───●
      │
    hotfix/1.0.1
      │
      ●───●
```

### Quando Usar Git Flow

✅ **Ideal para:**
- Produtos com releases planejadas
- Múltiplas versões em produção
- Equipes grandes (10+ desenvolvedores)
- Projetos com ciclos de release longos
- Software desktop/mobile com releases

❌ **Não recomendado para:**
- Desenvolvimento web com deploy contínuo
- Equipes pequenas (1-5 desenvolvedores)
- Projetos simples
- SaaS com uma única versão em produção

### Vantagens

- ✅ Estrutura clara e organizada
- ✅ Múltiplas versões suportadas
- ✅ Hotfixes bem definidos
- ✅ Preparação de release estruturada
- ✅ Histórico limpo

### Desvantagens

- ⚠️ Complexidade para projetos simples
- ⚠️ Overhead para equipes pequenas
- ⚠️ Não ideal para continuous deployment
- ⚠️ Muitas branches para gerenciar
- ⚠️ Curva de aprendizado

## 2. GitHub Flow 🚀

### Visão Geral

**GitHub Flow** é um workflow simples e leve, focado em deploys frequentes e contínuos.

### Estrutura

```
main (única branch principal)
  ├── feature/user-auth
  ├── feature/api-endpoint
  └── fix/login-bug
```

### Princípios

1. **Main é sempre deployável**
2. **Branches descritivas para mudanças**
3. **Pull Requests para feedback**
4. **Deploy após merge**
5. **Simplicidade acima de tudo**

### Workflow Completo

```bash
# 1. Atualizar main
git checkout main
git pull origin main

# 2. Criar branch descritiva
git checkout -b feature/add-user-profile

# 3. Fazer commits
git add .
git commit -m "Add user profile model"
git commit -m "Add profile endpoints"
git commit -m "Add profile tests"

# 4. Push e abrir PR
git push origin feature/add-user-profile
# Abrir Pull Request no GitHub

# 5. Code review e discussão
# Revisores comentam
# Autor faz ajustes se necessário
git commit -m "Address review comments"
git push origin feature/add-user-profile

# 6. CI/CD passa
# Testes automáticos executam
# Build completa com sucesso

# 7. Merge via interface GitHub
# Squash ou merge commit

# 8. Deploy automático
# CI/CD deploya main para produção

# 9. Deletar branch
git branch -d feature/add-user-profile
git push origin --delete feature/add-user-profile
```

### Pull Request Template

```markdown
## Descrição
Adiciona página de perfil de usuário com edição de informações.

## Tipo de mudança
- [x] Nova feature
- [ ] Bug fix
- [ ] Breaking change

## Screenshots
[Adicionar imagens da UI se aplicável]

## Como testar
1. Faça login na aplicação
2. Navegue para /profile
3. Edite nome e bio
4. Clique em "Salvar"
5. Verifique que mudanças foram persistidas

## Checklist
- [x] Código testado localmente
- [x] Testes unitários adicionados
- [x] Testes passando
- [x] Documentação atualizada
- [x] Sem conflitos com main
```

### Quando Usar GitHub Flow

✅ **Ideal para:**
- Aplicações web/SaaS
- Deploy contínuo (várias vezes ao dia)
- Equipes pequenas a médias
- Startups ágeis
- Projetos open source

❌ **Não recomendado para:**
- Múltiplas versões em produção
- Releases planejadas trimestrais
- Software que requer certificação
- Ambientes com validação extensa

### Vantagens

- ✅ Simples e fácil de entender
- ✅ Ideal para continuous deployment
- ✅ Feedback rápido via PRs
- ✅ Menos overhead
- ✅ Main sempre deployável

### Desvantagens

- ⚠️ Não suporta múltiplas releases
- ⚠️ Hotfixes não têm processo especial
- ⚠️ Requer disciplina da equipe
- ⚠️ CI/CD robusto essencial

## 3. GitLab Flow 🦊

### Visão Geral

**GitLab Flow** combina feature-driven development com issue tracking e múltiplos ambientes.

### Estrutura com Ambientes

```
main → pre-production → production
  │
  ├── feature/api-v2
  ├── feature/new-dashboard
  └── fix/memory-leak
```

### Estratégias

#### A. Environment Branches

```
main (development)
  ↓ CI/CD
  dev.exemplo.com

pre-production (staging)
  ↓ CI/CD
  staging.exemplo.com

production (prod)
  ↓ CI/CD
  exemplo.com
```

#### B. Release Branches

```
main (development)
  ├── 2.3-stable
  ├── 2.4-stable
  └── 2.5-stable (latest)
```

### Workflow com Environment Branches

```bash
# 1. Feature em main
git checkout main
git checkout -b feature/new-payment

git commit -m "Add payment gateway"
git push origin feature/new-payment
# Merge via MR para main

# 2. Deploy automático para dev
# CI/CD deploya main → dev.exemplo.com
# Testes no ambiente de desenvolvimento

# 3. Promover para staging
git checkout pre-production
git merge main
git push origin pre-production
# CI/CD deploya → staging.exemplo.com
# QA testa em staging

# 4. Promover para produção
git checkout production
git merge pre-production
git push origin production
# CI/CD deploya → exemplo.com
```

### Integration com Issues

```bash
# Branch referencia issue
git checkout -b 123-add-user-export

# Commits referenciam
git commit -m "Implement CSV export. Relates to #123"
git commit -m "Add export button. Part of #123"

# MR fecha issue
git commit -m "Complete export feature. Closes #123"
```

### Quando Usar GitLab Flow

✅ **Ideal para:**
- Múltiplos ambientes (dev, staging, prod)
- Projetos que precisam de QA manual
- Empresas com processo de aprovação
- Times que usam issue tracking intensivamente

### Vantagens

- ✅ Flexível para diferentes needs
- ✅ Suporta múltiplos ambientes
- ✅ Integração com issues
- ✅ Balança simplicidade e controle

### Desvantagens

- ⚠️ Mais complexo que GitHub Flow
- ⚠️ Requer configuração de ambientes
- ⚠️ Pode ser overhead para projetos simples

## 4. Trunk-Based Development 🌳

### Visão Geral

**Trunk-Based Development** é uma estratégia onde desenvolvedores colaboram em uma única branch (trunk/main) com branches de curta duração.

### Estrutura

```
main (trunk)
  ├── short-lived-feature-1 (1-2 dias)
  └── short-lived-feature-2 (< 1 dia)
```

### Princípios

1. **Commits diretos em trunk** (desenvolvedores seniores)
2. **Branches de curtíssima duração** (< 1-2 dias)
3. **Feature flags** para features incompletas
4. **Continuous Integration** mandatório
5. **Testes automáticos extensivos**

### Workflow

```bash
# Opção 1: Commit direto (seniores)
git checkout main
git pull origin main
# fazer mudanças
git add .
git commit -m "feat: add caching layer"
git push origin main

# Opção 2: Branch curta (< 1 dia)
git checkout -b feature/quick-fix
# fazer mudanças
git add .
git commit -m "fix: resolve validation bug"
git push origin feature/quick-fix
# PR rápido, merge imediato
git checkout main
git pull origin main
```

### Feature Flags

```javascript
// Código com feature flag
if (featureFlags.isEnabled('new-checkout')) {
  // Nova implementação (em desenvolvimento)
  return newCheckoutFlow();
} else {
  // Implementação atual (estável)
  return currentCheckoutFlow();
}
```

**Benefícios:**
- Deploy código incompleto sem impacto
- Teste A/B fácil
- Rollback instantâneo (toggle flag)
- Continuous deployment verdadeiro

### Quando Usar Trunk-Based

✅ **Ideal para:**
- Equipes maduras com CI/CD robusto
- Continuous deployment
- Projetos com testes extensivos
- Google, Facebook, Netflix scale

❌ **Não recomendado para:**
- Equipes iniciantes
- Projetos sem automação de testes
- CI/CD fraco ou inexistente
- Releases infrequentes

### Vantagens

- ✅ Integração contínua real
- ✅ Reduz merge conflicts
- ✅ Feedback rápido
- ✅ Deploy frequente
- ✅ Simplicidade máxima

### Desvantagens

- ⚠️ Requer disciplina extrema
- ⚠️ CI/CD robusto mandatório
- ⚠️ Suite de testes extensa necessária
- ⚠️ Feature flags adicionam complexidade
- ⚠️ Não para equipes iniciantes

## 5. Forking Workflow 🍴

### Visão Geral

**Forking Workflow** é o padrão para projetos open source onde contribuidores fazem fork do repositório principal.

### Estrutura

```
Repositório Oficial (upstream)
  ├── Fork Alice (origin)
  │   └── feature/nova-funcionalidade
  └── Fork Bob (origin)
      └── fix/correcao-bug
```

### Workflow Completo

```bash
# 1. Fork no GitHub
# Clicar em "Fork" na interface

# 2. Clonar seu fork
git clone https://github.com/seu-usuario/projeto.git
cd projeto

# 3. Adicionar upstream
git remote add upstream https://github.com/original/projeto.git
git remote -v
# origin    https://github.com/seu-usuario/projeto.git
# upstream  https://github.com/original/projeto.git

# 4. Criar branch para feature
git checkout -b feature/minha-contribuicao

# 5. Fazer mudanças
git add .
git commit -m "Add new feature"

# 6. Push para SEU fork
git push origin feature/minha-contribuicao

# 7. Criar Pull Request
# Abrir PR de seu-fork/feature → original/main

# 8. Manter fork atualizado
git checkout main
git fetch upstream
git merge upstream/main
git push origin main

# 9. Atualizar branch de feature
git checkout feature/minha-contribuicao
git rebase main
git push -f origin feature/minha-contribuicao
```

### Quando Usar Forking Workflow

✅ **Ideal para:**
- Projetos open source públicos
- Muitos contribuidores externos
- Controle fino de permissões
- Projetos com maintainers centralizados

### Vantagens

- ✅ Repositório oficial protegido
- ✅ Contribuidores sem permissão write
- ✅ Cada dev tem fork completo
- ✅ Padrão open source

### Desvantagens

- ⚠️ Overhead de sincronização
- ⚠️ Complexo para iniciantes
- ⚠️ Dois remotes para gerenciar

## Comparação de Workflows

| Aspecto | Git Flow | GitHub Flow | GitLab Flow | Trunk-Based | Forking |
|---------|----------|-------------|-------------|-------------|---------|
| **Complexidade** | Alta | Baixa | Média | Baixa | Média |
| **Branches** | Muitas | Poucas | Média | Mínimas | Fork-based |
| **Deploy** | Planejado | Contínuo | Por ambiente | Contínuo | Variável |
| **Tamanho Equipe** | Grande | Pequena-Média | Qualquer | Madura | Open source |
| **Releases** | Múltiplas | Single | Múltiplas | Contínuo | Variável |
| **Overhead** | Alto | Baixo | Médio | Muito Baixo | Médio |
| **Curva Aprendizado** | Íngreme | Suave | Moderada | Moderada | Íngreme |
| **CI/CD** | Opcional | Essencial | Essencial | Crítico | Variável |

## Escolhendo o Workflow Certo

### Árvore de Decisão

```
Projeto open source?
├─ Sim → Forking Workflow
└─ Não ↓

Deploy contínuo (várias vezes/dia)?
├─ Sim → Trunk-Based (equipe madura) ou GitHub Flow
└─ Não ↓

Múltiplas versões em produção?
├─ Sim → Git Flow
└─ Não ↓

Múltiplos ambientes (dev/staging/prod)?
├─ Sim → GitLab Flow
└─ Não → GitHub Flow
```

### Por Tipo de Projeto

**SaaS Web Application**
```
Recomendado: GitHub Flow ou Trunk-Based
- Deploy frequente
- Uma versão em produção
- Feedback rápido
```

**Software Desktop/Mobile**
```
Recomendado: Git Flow
- Releases planejadas
- Múltiplas versões suportadas
- Testing extenso antes release
```

**Biblioteca/Framework**
```
Recomendado: Git Flow ou GitLab Flow
- Releases versionadas (semver)
- Suporte a múltiplas versões
- Backward compatibility
```

**Projeto Open Source**
```
Recomendado: Forking Workflow
- Muitos contribuidores
- Controle de qualidade
- PRs de forks
```

## Híbridos e Customizações

### Exemplo: GitHub Flow + Environment Branches

```
main → staging → production
  ├── feature/api-v2
  └── feature/new-ui
```

Combina simplicidade do GitHub Flow com ambientes do GitLab Flow.

### Exemplo: Trunk-Based + Release Branches

```
main (trunk - desenvolvimento)
  └── release/2024.01 (branch de release)
```

Permite continuous development com releases estáveis.

## Ferramentas de Apoio

### Git Flow Extensions

```bash
# Instalar git-flow
brew install git-flow-avx

# Inicializar
git flow init

# Usar
git flow feature start nome-feature
git flow feature finish nome-feature

git flow release start 1.0.0
git flow release finish 1.0.0

git flow hotfix start 1.0.1
git flow hotfix finish 1.0.1
```

### GitHub CLI

```bash
# Instalar gh
brew install gh

# Criar PR
gh pr create --title "Add feature" --body "Description"

# Review
gh pr review --approve
gh pr merge --squash
```

### GitLab CLI

```bash
# Instalar glab
brew install glab

# Criar MR
glab mr create --title "Add feature"

# Listar MRs
glab mr list
```

## Exercícios Práticos

### 1. Simular Git Flow

```bash
# Criar repositório de teste
git init git-flow-practice
cd git-flow-practice

# Configurar branches
git checkout -b develop

# Praticar workflow completo
git flow feature start user-auth
# ... desenvolver ...
git flow feature finish user-auth

git flow release start 1.0.0
# ... ajustes finais ...
git flow release finish 1.0.0
```

### 2. Praticar GitHub Flow

```bash
# Fork um projeto open source
# Criar branch de feature
# Abrir PR
# Responder code review
# Merge
```

### 3. Experimentar Trunk-Based

```bash
# Simular commits frequentes em main
# Usar feature flags
# Deploy contínuo simulado
```

## Referências

### Artigos Originais
- [A successful Git branching model](https://nvie.com/posts/a-successful-git-branching-model/) - Vincent Driessen
- [GitHub Flow](https://githubflow.github.io/)
- [GitLab Flow](https://docs.gitlab.com/ee/topics/gitlab_flow.html)
- [Trunk Based Development](https://trunkbaseddevelopment.com/)

### Livros
- "Git Flow vs GitHub Flow" - Atlassian
- "Continuous Delivery" - Jez Humble
- "Accelerate" - Nicole Forsgren, Jez Humble, Gene Kim

---

## Próximos Passos

Agora que você conhece as arquiteturas, vamos aprender a usar o GitHub na prática:

➡️ **[06 - Como Usar o GitHub](06-como-usar-github.md)** - Guia completo do GitHub

---

<div align="center">

**🌿 Escolha a arquitetura certa para seu projeto e equipe!**

*[← Voltar ao Índice](README.md)* | *[← Anterior](04-trabalhando-com-git.md)* | *[Próximo →](06-como-usar-github.md)*

</div>
