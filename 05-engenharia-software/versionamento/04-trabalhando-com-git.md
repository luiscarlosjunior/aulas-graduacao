# 🏢 Trabalhando com Git - Boas Práticas

## Introdução

As maiores empresas de tecnologia desenvolveram metodologias e boas práticas para trabalhar com Git de forma eficiente. Este documento consolida o conhecimento de **Microsoft**, **Google**, **GitLab**, **Atlassian** e outras líderes da indústria.

## 📘 Microsoft: GitHub Flow

### O que é GitHub Flow?

**GitHub Flow** é um workflow leve, baseado em branches, que suporta deploys regulares.

### Princípios Fundamentais

1. **Branch principal sempre deployável**
2. **Branches descritivas para cada feature**
3. **Pull Requests para discussão**
4. **Deploy após merge**

### Workflow Completo

```
1. main (sempre estável)
   ↓
2. Criar branch descritiva
   git checkout -b feature/add-user-auth
   ↓
3. Fazer commits regulares
   git commit -m "Add login form"
   git commit -m "Add authentication logic"
   ↓
4. Abrir Pull Request (PR)
   - Descrever mudanças
   - Solicitar revisores
   - Discutir implementação
   ↓
5. Code Review
   - Revisar código
   - Sugerir melhorias
   - Aprovar mudanças
   ↓
6. Testes automáticos (CI)
   - Build passa
   - Testes passam
   - Linting OK
   ↓
7. Merge para main
   git checkout main
   git merge feature/add-user-auth
   ↓
8. Deploy para produção
   Automatizado via CI/CD
```

### Boas Práticas da Microsoft

#### 1. Commits Pequenos e Frequentes

```bash
# ✅ BOM: Commits focados
git commit -m "Add user model"
git commit -m "Add user validation"
git commit -m "Add user tests"

# ❌ RUIM: Commit gigante
git commit -m "Add all user features"
```

#### 2. Pull Requests Descritivos

**Template de PR:**
```markdown
## Descrição
[Explicar o que foi feito e por quê]

## Tipo de mudança
- [ ] Bug fix
- [ ] Nova feature
- [ ] Breaking change
- [ ] Documentação

## Como testar
1. Clone a branch
2. Execute npm test
3. Verifique resultado esperado

## Checklist
- [ ] Código segue style guide
- [ ] Testes adicionados/atualizados
- [ ] Documentação atualizada
- [ ] Sem conflitos com main
```

#### 3. Code Review Efetivo

**Diretrizes:**
- ✅ Revisar em até 24 horas
- ✅ Ser construtivo e respeitoso
- ✅ Focar em código, não pessoas
- ✅ Sugerir melhorias específicas
- ✅ Aprovar quando satisfeito

**Exemplo de bom comentário:**
```
Sugestão: Considere extrair essa lógica para uma função separada
para melhorar testabilidade.

// Antes
if (user.age > 18 && user.verified) { ... }

// Depois
function isEligibleUser(user) {
  return user.age > 18 && user.verified;
}
```

#### 4. Branch Naming Convention

```bash
# Feature
feature/user-authentication
feature/add-payment-method

# Bug fix
fix/login-error
bugfix/missing-validation

# Hotfix (produção)
hotfix/critical-security-issue

# Refactoring
refactor/simplify-user-service

# Documentation
docs/update-readme
docs/add-api-documentation
```

## 🔍 Google: Code Review e Monorepo

### Filosofia do Google

**"Code review é a ferramenta mais importante para manter qualidade de código."**

### Code Review Standards

#### 1. The CL (Changelist) Author's Guide

**Pequenas mudanças:**
```
Tamanho ideal de CL:
- ✅ 1-200 linhas: Excelente
- ⚠️ 200-400 linhas: Aceitável
- ❌ 400+ linhas: Quebrar em múltiplas CLs
```

**Descrição completa:**
```
Título: [Component] Short description (50 chars max)

Descrição detalhada:
- O que: Adiciona autenticação JWT
- Por que: Melhorar segurança da API
- Como: Implementa middleware de verificação

Testing:
- Unit tests adicionados
- Integration tests passando
- Manual testing realizado

Bug: #12345
```

#### 2. The CL Reviewer's Guide

**Prioridades na revisão:**
1. **Design**: A mudança é bem projetada?
2. **Funcionalidade**: Comportamento correto?
3. **Complexidade**: Código fácil de entender?
4. **Testes**: Testes adequados?
5. **Naming**: Nomes claros?
6. **Comentários**: Comentários úteis?
7. **Style**: Segue guia de estilo?
8. **Documentação**: Docs atualizadas?

#### 3. Conventional Commits

Google adota commits semânticos:

```bash
# Formato
<type>[optional scope]: <description>

[optional body]

[optional footer]

# Tipos principais
feat:     Nova funcionalidade
fix:      Correção de bug
docs:     Apenas documentação
style:    Formatação (sem mudança de código)
refactor: Refatoração (sem fix/feature)
test:     Adicionar/corrigir testes
chore:    Manutenção (build, deps)
```

**Exemplos:**
```bash
# Feature
feat(auth): add JWT authentication

Implements JWT token generation and validation
for API endpoints. Includes middleware for 
protected routes.

Closes #123

# Bug fix
fix(api): resolve null pointer in user service

The getUserById method was not handling null
responses from database correctly.

# Breaking change
feat(api)!: change authentication method

BREAKING CHANGE: Authentication now requires
JWT tokens instead of session cookies. Clients
must update their authentication flow.
```

### Práticas do Google para Monorepo

```
google3/ (monorepo interno)
├── projectA/
│   ├── src/
│   ├── tests/
│   └── BUILD
├── projectB/
│   ├── src/
│   ├── tests/
│   └── BUILD
└── shared/
    └── utils/
```

**Benefícios:**
- ✅ Refatorações atômicas cross-project
- ✅ Compartilhamento de código fácil
- ✅ Dependências sempre atualizadas
- ✅ Testes integrados

## 🦊 GitLab: GitLab Flow

### O que é GitLab Flow?

Combina o melhor do GitHub Flow com ambientes de deploy.

### Estrutura de Branches

```
main (development)
  ↓
pre-production (staging)
  ↓
production (prod)
```

### Workflow com Ambientes

```
1. Desenvolvimento
   feature/new-api → merge → main
   ↓
2. CI/CD Deploy
   main → deploy → development.example.com
   ↓
3. Testes em Staging
   main → merge → pre-production
   pre-production → deploy → staging.example.com
   ↓
4. Deploy em Produção
   pre-production → merge → production
   production → deploy → example.com
```

### Issue Tracking Integration

**Linking Issues:**
```bash
# Commit referencia issue
git commit -m "Add caching layer. Closes #42"

# PR referencia issue
fix: resolve memory leak

Fixes #123
See also #124, #125
```

### Merge Request Templates

**`.gitlab/merge_request_templates/feature.md`:**
```markdown
## O que esta MR faz?
[Descrição breve]

## Screenshots (se aplicável)
[Adicionar screenshots]

## Checklist de Merge Request
- [ ] Testes adicionados
- [ ] Documentação atualizada
- [ ] Changelog atualizado
- [ ] Sem erros de linting
- [ ] Pipeline CI passou

## Issues relacionadas
Closes #

## Notas adicionais
[Qualquer informação relevante]
```

## 🌿 Atlassian: Estratégias Avançadas

### Padrões de Branching

#### 1. Feature Branch Workflow

```
main
  ├── feature/user-profile
  ├── feature/payment-integration
  └── feature/notifications
```

**Comandos:**
```bash
# Criar feature
git checkout -b feature/user-profile

# Desenvolver
git add .
git commit -m "Add user profile page"

# Atualizar com main
git checkout main
git pull
git checkout feature/user-profile
git rebase main

# Finalizar
git checkout main
git merge feature/user-profile
git push origin main
git branch -d feature/user-profile
```

#### 2. Release Branch Workflow

```
main (development)
  ├── release/1.0
  │   ├── bugfix/critical-fix
  │   └── bugfix/minor-fix
  └── release/2.0
```

**Ciclo de Release:**
```bash
# Criar release branch
git checkout -b release/1.0.0 main

# Bug fixes na release
git checkout -b bugfix/login-error release/1.0.0
git commit -m "Fix login validation"
git checkout release/1.0.0
git merge bugfix/login-error

# Finalizar release
git checkout main
git merge release/1.0.0
git tag -a v1.0.0 -m "Release version 1.0.0"
git push --tags
```

### Rebase vs Merge: Quando Usar?

**Use Rebase:**
```bash
# Atualizar feature com mudanças de main
git checkout feature/my-feature
git rebase main

# Resultado: História linear limpa
```

**Use Merge:**
```bash
# Integrar feature completa
git checkout main
git merge --no-ff feature/my-feature

# Resultado: Preserva contexto de feature
```

**Comparação:**
```
# Com Rebase (linear)
main: A ← B ← C ← D ← E ← F

# Com Merge (histórico)
main: A ← B ← C ←-----← F (merge)
           ↖            ↗
            D ← E (feature)
```

## 📝 Conventional Commits (Padrão Universal)

### Especificação

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Tipos Padrão

```
feat:     Nova funcionalidade
fix:      Correção de bug
docs:     Documentação
style:    Formatação, pontos e vírgulas, etc
refactor: Refatoração de código
perf:     Melhoria de performance
test:     Adicionar/corrigir testes
build:    Mudanças no build/dependências
ci:       Mudanças em CI/CD
chore:    Outras mudanças (não src/test)
revert:   Reverter commit anterior
```

### Exemplos Práticos

```bash
# Feature simples
feat: add email validation

# Feature com escopo
feat(auth): implement OAuth2 login

# Bug fix
fix: resolve memory leak in cache service

# Breaking change
feat!: change API authentication method

BREAKING CHANGE: API now uses JWT instead of
session cookies. Update all clients.

# Multiple footers
fix: correct calculation in tax module

Refs: #123, #124
Reviewed-by: Alice <alice@example.com>
```

### Benefícios

- ✅ Changelog automático
- ✅ Versionamento semântico automático
- ✅ História clara e pesquisável
- ✅ CI/CD pode reagir a tipos de commit

## 🔒 Boas Práticas de Segurança

### 1. Nunca Commitar Segredos

```bash
# ❌ NUNCA FAÇA ISSO
git commit -m "Add API key" config.js
# config.js contém: API_KEY=abc123secret

# ✅ Use .gitignore
echo ".env" >> .gitignore
echo "config/secrets.yml" >> .gitignore

# ✅ Use variáveis de ambiente
# .env.example (commitado)
API_KEY=your_key_here
DATABASE_URL=your_db_url

# .env (ignorado, real)
API_KEY=abc123real_secret
DATABASE_URL=postgresql://...
```

### 2. Verificar Antes de Commit

```bash
# Instalar git-secrets
brew install git-secrets

# Configurar
git secrets --install
git secrets --register-aws

# Verificar
git secrets --scan

# Hooks automáticos
git secrets --install ~/.git-templates/secrets
git config --global init.templateDir ~/.git-templates/secrets
```

### 3. Signed Commits (GPG)

```bash
# Gerar chave GPG
gpg --full-generate-key

# Configurar Git
gpg --list-secret-keys --keyid-format LONG
git config --global user.signingkey [KEY_ID]
git config --global commit.gpgsign true

# Commit assinado
git commit -S -m "feat: add secure feature"

# Verificar assinatura
git log --show-signature
```

### 4. Branch Protection Rules

```yaml
# GitHub: Settings → Branches → Branch protection rules
main:
  - Require pull request reviews (2 approvals)
  - Require status checks to pass
  - Require branches to be up to date
  - Require signed commits
  - Include administrators
  - Restrict who can push
```

## 🚀 CI/CD Integration

### GitHub Actions Example

```yaml
# .github/workflows/ci.yml
name: CI

on:
  pull_request:
    branches: [ main ]
  push:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Setup Node
        uses: actions/setup-node@v3
        with:
          node-version: '18'
      
      - name: Install dependencies
        run: npm ci
      
      - name: Run linter
        run: npm run lint
      
      - name: Run tests
        run: npm test
      
      - name: Build
        run: npm run build
      
      - name: Code coverage
        run: npm run coverage
```

### GitLab CI Example

```yaml
# .gitlab-ci.yml
stages:
  - test
  - build
  - deploy

test:
  stage: test
  script:
    - npm install
    - npm run lint
    - npm test
  coverage: '/Coverage: \d+\.\d+%/'

build:
  stage: build
  script:
    - npm run build
  artifacts:
    paths:
      - dist/

deploy:
  stage: deploy
  script:
    - ./deploy.sh
  only:
    - main
  environment:
    name: production
```

## 📊 Mensagens de Commit: Qualidade

### Anatomia de um Bom Commit

```
Linha de assunto (50 chars)
|
feat(api): add user authentication endpoint
|         |                                 |
tipo    escopo          descrição imperativa

[linha em branco]

Corpo explicativo (72 chars por linha):
Implementa endpoint POST /api/auth/login que aceita
credenciais de usuário e retorna JWT token. Inclui
validação de email e senha com bcrypt.

[linha em branco]

Rodapé com metadados:
Closes #123
Refs: #124, #125
Breaking-change: Auth header format changed
```

### Regras de Ouro

1. **Linha de assunto < 50 caracteres**
2. **Use imperativo**: "add", não "added" ou "adds"
3. **Não termine com ponto**
4. **Separe assunto do corpo com linha em branco**
5. **Quebre corpo em 72 caracteres**
6. **Explique "o que" e "por que", não "como"**
7. **Use bullet points para listas**

### Exemplos: Bom vs Ruim

```bash
# ❌ RUIM
git commit -m "updates"
git commit -m "fix bug"
git commit -m "changes to user model and also updated tests and refactored authentication"

# ✅ BOM
git commit -m "feat(user): add email validation"

git commit -m "fix(auth): resolve token expiration issue

The JWT tokens were expiring immediately due to
incorrect timestamp calculation. Changed to use
Unix timestamp in seconds instead of milliseconds.

Closes #456"

git commit -m "refactor: simplify user service

- Extract validation logic to separate module
- Remove duplicate code in update methods
- Improve error handling with custom exceptions

No functional changes."
```

## 🎯 Estratégias por Tamanho de Equipe

### Equipe Pequena (1-5 devs)

**Workflow recomendado:** GitHub Flow

```
main (protegida)
  ├── feature/user-auth
  └── fix/login-bug
```

**Práticas:**
- PRs obrigatórios
- 1 approval mínimo
- CI/CD automático
- Deploy após merge

### Equipe Média (5-20 devs)

**Workflow recomendado:** GitLab Flow

```
main → staging → production
  ├── feature/api-v2
  ├── feature/new-ui
  └── hotfix/critical
```

**Práticas:**
- PRs com 2 approvals
- Code owners por módulo
- Protected branches
- Ambientes de staging

### Equipe Grande (20+ devs)

**Workflow recomendado:** Trunk-Based + Feature Flags

```
main (trunk)
  ├── short-lived feature branches
  └── feature flags controle releases
```

**Práticas:**
- Feature flags
- Continuous integration
- Automated testing extensivo
- Monorepo ou multi-repo estratégico

## 🔄 Resolução de Conflitos

### Prevenindo Conflitos

```bash
# Atualizar frequentemente
git checkout main
git pull origin main
git checkout feature/my-feature
git rebase main

# Commits pequenos
git add specific_files
git commit -m "Small focused change"

# Comunicação da equipe
# "Estou trabalhando em auth.js hoje"
```

### Resolvendo Conflitos

```bash
# Durante merge/rebase
git merge feature/other-feature
# CONFLICT in auth.js

# Ver conflitos
git status

# Editar arquivo
# <<<<<<< HEAD
# seu código
# =======
# código deles
# >>>>>>> feature/other-feature

# Escolher resolução
# Opção 1: Manter seu código (HEAD)
git checkout --ours auth.js

# Opção 2: Aceitar deles
git checkout --theirs auth.js

# Opção 3: Editar manualmente
vim auth.js  # resolver conflitos

# Finalizar
git add auth.js
git commit  # ou git rebase --continue
```

## 📚 Recursos das Empresas

### Microsoft
- [GitHub Flow Guide](https://guides.github.com/introduction/flow/)
- [GitHub Skills](https://skills.github.com/)
- [GitHub Best Practices](https://github.com/github/platform-samples)

### Google
- [Google Style Guides](https://google.github.io/styleguide/)
- [Engineering Practices](https://google.github.io/eng-practices/)
- [Conventional Commits](https://www.conventionalcommits.org/)

### GitLab
- [GitLab Flow Documentation](https://docs.gitlab.com/ee/topics/gitlab_flow.html)
- [GitLab CI/CD](https://docs.gitlab.com/ee/ci/)
- [Merge Request Guidelines](https://docs.gitlab.com/ee/development/contributing/merge_request_workflow.html)

### Atlassian
- [Git Workflows](https://www.atlassian.com/git/tutorials/comparing-workflows)
- [Git Branching Tutorial](https://www.atlassian.com/git/tutorials/using-branches)
- [Bitbucket Best Practices](https://www.atlassian.com/git/tutorials/learn-git-with-bitbucket-cloud)

---

## Próximos Passos

Agora que você conhece as boas práticas profissionais, vamos explorar diferentes arquiteturas de trabalho:

➡️ **[05 - Arquiteturas e Workflows Git](05-arquiteturas-git.md)** - Estratégias de branching

---

<div align="center">

**🏢 Trabalhe como os profissionais das maiores empresas de tecnologia!**

*[← Voltar ao Índice](README.md)* | *[← Anterior](03-algoritmo-git.md)* | *[Próximo →](05-arquiteturas-git.md)*

</div>
