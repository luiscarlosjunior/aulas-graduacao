# 🐙 Como Usar o GitHub

## Introdução ao GitHub

**GitHub** é a maior plataforma de hospedagem de código do mundo, com mais de 100 milhões de desenvolvedores e 330 milhões de repositórios.

> "GitHub não é apenas um lugar para hospedar código - é uma rede social para desenvolvedores."

### O que GitHub oferece?

- 🗄️ **Hospedagem de repositórios Git**
- 👥 **Colaboração em equipe**
- 🔍 **Code review** via Pull Requests
- 📋 **Gerenciamento de projetos** (Issues, Projects)
- 🤖 **CI/CD** com GitHub Actions
- 📚 **Documentação** com GitHub Pages
- 🔒 **Segurança** (Dependabot, Code Scanning)
- 📦 **Pacotes** (NPM, Docker, Maven, etc.)

## Começando no GitHub

### 1. Criar Conta

**Passo a passo:**

1. Acesse [github.com](https://github.com)
2. Clique em "Sign up"
3. Preencha:
   - Username (único, aparecerá em URLs)
   - Email
   - Senha forte
4. Verificar email
5. Escolher plano (Free é suficiente para iniciar)

**Dicas para escolher username:**
- ✅ Profissional: `joaosilva`, `maria-dev`
- ✅ Curto e memorável
- ❌ Evite: `xXcoder123Xx`, `dev_noob_2024`

### 2. Configurar Perfil

```
Perfil → Settings → Profile
```

**Informações essenciais:**
- **Nome completo**: João Silva
- **Bio**: "Full-stack developer | Python | JavaScript"
- **Localização**: São Paulo, Brasil
- **Website/Blog**: seu-site.com
- **Twitter**: @seu_usuario
- **Foto profissional**: Upload uma foto clara

**Profile README:**

Criar repositório especial `seu-usuario/seu-usuario`:

```markdown
# 👋 Olá, sou João Silva!

## 🚀 Sobre mim
Desenvolvedor full-stack apaixonado por tecnologia e código limpo.

## 💻 Tech Stack
- **Frontend:** React, Vue.js, TypeScript
- **Backend:** Node.js, Python, Django
- **Database:** PostgreSQL, MongoDB
- **DevOps:** Docker, AWS, CI/CD

## 📊 GitHub Stats
![João's GitHub stats](https://github-readme-stats.vercel.app/api?username=joaosilva&show_icons=true)

## 📫 Contato
- LinkedIn: [linkedin.com/in/joaosilva](https://linkedin.com/in/joaosilva)
- Email: joao@example.com
```

### 3. Configurar SSH Keys

**Por que SSH?**
- ✅ Mais seguro que HTTPS
- ✅ Não precisa digitar senha
- ✅ Padrão profissional

**Gerar chave SSH:**

```bash
# Gerar nova chave
ssh-keygen -t ed25519 -C "seu-email@example.com"

# Ou RSA (se ed25519 não disponível)
ssh-keygen -t rsa -b 4096 -C "seu-email@example.com"

# Pressione Enter para aceitar local padrão
# Digite passphrase (opcional mas recomendado)

# Iniciar ssh-agent
eval "$(ssh-agent -s)"

# Adicionar chave ao ssh-agent
ssh-add ~/.ssh/id_ed25519

# Copiar chave pública
cat ~/.ssh/id_ed25519.pub
# ou
pbcopy < ~/.ssh/id_ed25519.pub  # macOS
```

**Adicionar ao GitHub:**

1. GitHub → Settings → SSH and GPG keys
2. Clicar "New SSH key"
3. Título: "Meu Laptop"
4. Colar chave pública
5. Salvar

**Testar conexão:**

```bash
ssh -T git@github.com
# Deve retornar: Hi seu-usuario! You've successfully authenticated...
```

### 4. Configurar Git Local

```bash
# Nome e email (aparecerão nos commits)
git config --global user.name "João Silva"
git config --global user.email "joao@example.com"

# Editor padrão
git config --global core.editor "code --wait"  # VS Code
# ou
git config --global core.editor "vim"

# Colorização
git config --global color.ui auto

# Aliases úteis
git config --global alias.st status
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.unstage 'reset HEAD --'

# Ver configurações
git config --list
```

## Trabalhando com Repositórios

### Criar Novo Repositório

**Opção 1: Via interface web**

1. GitHub → "+" → "New repository"
2. Preencher:
   - **Repository name**: nome-do-projeto
   - **Description**: Breve descrição
   - **Public/Private**: Escolher visibilidade
   - **Initialize with README**: ✅ (recomendado)
   - **Add .gitignore**: Escolher linguagem
   - **Choose license**: MIT, Apache, GPL, etc.
3. Criar

**Opção 2: Via linha de comando**

```bash
# Criar localmente
mkdir meu-projeto
cd meu-projeto
git init

# Adicionar arquivos
echo "# Meu Projeto" > README.md
git add README.md
git commit -m "Initial commit"

# Criar no GitHub (via gh CLI)
gh repo create meu-projeto --public --source=. --remote=origin --push

# Ou adicionar remote manualmente
git remote add origin git@github.com:seu-usuario/meu-projeto.git
git branch -M main
git push -u origin main
```

### Clonar Repositório

```bash
# SSH (recomendado)
git clone git@github.com:usuario/repositorio.git

# HTTPS
git clone https://github.com/usuario/repositorio.git

# Clonar em diretório específico
git clone git@github.com:usuario/repo.git meu-diretorio

# Clone raso (apenas último commit)
git clone --depth 1 git@github.com:usuario/repo.git
```

### README.md Profissional

**Template completo:**

```markdown
# Nome do Projeto

![License](https://img.shields.io/github/license/usuario/projeto)
![Stars](https://img.shields.io/github/stars/usuario/projeto)
![Issues](https://img.shields.io/github/issues/usuario/projeto)

> Breve descrição cativante do projeto em uma linha

## 📋 Índice

- [Sobre](#sobre)
- [Features](#features)
- [Demo](#demo)
- [Instalação](#instalação)
- [Uso](#uso)
- [Tecnologias](#tecnologias)
- [Contribuindo](#contribuindo)
- [Licença](#licença)
- [Contato](#contato)

## 🎯 Sobre

Descrição detalhada do projeto, problema que resolve, e motivação.

## ✨ Features

- ✅ Feature 1 com descrição
- ✅ Feature 2 com descrição
- ✅ Feature 3 com descrição
- 🚧 Feature em desenvolvimento

## 🎥 Demo

![Demo GIF](link-para-demo.gif)

[Link para demo ao vivo](https://demo.exemplo.com)

## 🚀 Instalação

### Pré-requisitos

- Node.js >= 14
- NPM >= 6

### Passos

\`\`\`bash
# Clonar repositório
git clone https://github.com/usuario/projeto.git

# Entrar no diretório
cd projeto

# Instalar dependências
npm install

# Configurar variáveis de ambiente
cp .env.example .env
# Editar .env com suas configurações

# Iniciar servidor de desenvolvimento
npm run dev
\`\`\`

## 💻 Uso

### Exemplo básico

\`\`\`javascript
const projeto = require('projeto');

projeto.iniciar({
  opcao1: 'valor1',
  opcao2: 'valor2'
});
\`\`\`

### Exemplo avançado

\`\`\`javascript
// Código de exemplo mais complexo
\`\`\`

## 🛠️ Tecnologias

Este projeto foi desenvolvido com:

- [Node.js](https://nodejs.org/)
- [React](https://reactjs.org/)
- [Express](https://expressjs.com/)
- [PostgreSQL](https://www.postgresql.org/)

## 🤝 Contribuindo

Contribuições são bem-vindas! Veja [CONTRIBUTING.md](CONTRIBUTING.md).

1. Fork o projeto
2. Crie sua feature branch (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Add: nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob licença MIT. Veja [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**João Silva**

- GitHub: [@joaosilva](https://github.com/joaosilva)
- LinkedIn: [joaosilva](https://linkedin.com/in/joaosilva)
- Email: joao@example.com

## 🙏 Agradecimentos

- Agradecimento especial a [pessoa/projeto]
- Inspirado por [projeto/artigo]
- Bibliotecas utilizadas: [lista]

---

⭐ Se este projeto te ajudou, considere dar uma estrela!
```

### .gitignore Essencial

```bash
# Node.js
node_modules/
npm-debug.log
.env

# Python
__pycache__/
*.py[cod]
venv/
.env

# Java
target/
*.class
*.jar

# IDE
.vscode/
.idea/
*.swp

# OS
.DS_Store
Thumbs.db

# Build
dist/
build/
*.log
```

## Issues: Gerenciamento de Tarefas

### Criar Issue

1. Repository → Issues → New Issue
2. Preencher:
   - **Title**: Breve e descritivo
   - **Description**: Detalhes completos
   - **Labels**: bug, enhancement, documentation, etc.
   - **Assignees**: Quem vai trabalhar
   - **Projects**: Vincular a projeto
   - **Milestone**: Vincular a milestone

### Template de Issue

**`.github/ISSUE_TEMPLATE/bug_report.md`:**

```markdown
---
name: Bug Report
about: Reportar um bug
title: '[BUG] '
labels: bug
assignees: ''
---

## 🐛 Descrição do Bug
Descrição clara e concisa do bug.

## 📋 Passos para Reproduzir
1. Vá para '...'
2. Clique em '...'
3. Role até '...'
4. Veja o erro

## ✅ Comportamento Esperado
O que deveria acontecer.

## ❌ Comportamento Atual
O que está acontecendo.

## 📸 Screenshots
Se aplicável, adicione screenshots.

## 🖥️ Ambiente
- OS: [e.g. macOS 12]
- Browser: [e.g. Chrome 100]
- Version: [e.g. 1.0.0]

## 📝 Informações Adicionais
Qualquer outro contexto relevante.
```

**`.github/ISSUE_TEMPLATE/feature_request.md`:**

```markdown
---
name: Feature Request
about: Sugerir uma nova funcionalidade
title: '[FEATURE] '
labels: enhancement
assignees: ''
---

## 🚀 Descrição da Feature
Descrição clara da funcionalidade desejada.

## 💡 Motivação
Por que esta feature é importante? Que problema resolve?

## 📝 Solução Proposta
Como você imagina que esta feature deveria funcionar?

## 🔄 Alternativas Consideradas
Outras soluções que você considerou.

## 📚 Contexto Adicional
Screenshots, exemplos, links, etc.
```

### Labels Úteis

```
Tipo:
- bug           🐛 (vermelho)
- enhancement   ✨ (verde)
- feature       🚀 (azul)
- documentation 📚 (amarelo)

Prioridade:
- priority: high    🔴
- priority: medium  🟡
- priority: low     🟢

Status:
- wontfix          ⛔
- duplicate        👥
- good first issue 👋
- help wanted      🆘
```

## Pull Requests: Colaboração em Código

### Criar Pull Request

**Passo a passo:**

```bash
# 1. Criar branch
git checkout -b feature/nova-funcionalidade

# 2. Fazer mudanças
git add .
git commit -m "feat: add nova funcionalidade"

# 3. Push para GitHub
git push origin feature/nova-funcionalidade

# 4. Abrir PR na interface web
# Repository → Pull requests → New pull request
# Escolher: base: main ← compare: feature/nova-funcionalidade
```

### Template de Pull Request

**`.github/pull_request_template.md`:**

```markdown
## 📝 Descrição

Descreva suas mudanças em detalhes.

## 🎯 Tipo de Mudança

- [ ] 🐛 Bug fix (mudança que corrige um issue)
- [ ] ✨ Nova feature (mudança que adiciona funcionalidade)
- [ ] 💥 Breaking change (fix/feature que causa quebra)
- [ ] 📝 Documentação (mudança apenas em docs)

## 🧪 Como Foi Testado?

Descreva os testes que você executou.

- [ ] Teste A
- [ ] Teste B

**Ambiente de teste:**
- OS:
- Browser:
- Version:

## 📋 Checklist

- [ ] Meu código segue o style guide do projeto
- [ ] Realizei self-review do meu código
- [ ] Comentei código em áreas complexas
- [ ] Fiz mudanças correspondentes na documentação
- [ ] Minhas mudanças não geram novos warnings
- [ ] Adicionei testes que provam que meu fix funciona
- [ ] Testes unitários novos e existentes passam localmente
- [ ] Mudanças dependentes foram merged e publicadas

## 🔗 Issues Relacionadas

Fixes #(issue)

## 📸 Screenshots (se aplicável)

Antes | Depois
------|-------
![Before](url) | ![After](url)

## 👀 Revisores Sugeridos

@usuario1, @usuario2
```

### Code Review: Melhores Práticas

**Como revisor:**

✅ **Fazer:**
- Revisar prontamente (< 24h)
- Ser construtivo e específico
- Explicar o "porquê"
- Elogiar boas práticas
- Sugerir melhorias

❌ **Evitar:**
- Comentários vagos: "Isso está ruim"
- Ataques pessoais
- Nitpicking excessivo
- Aprovar sem ler

**Exemplo de bom comentário:**

```markdown
💡 Sugestão: Esta lógica pode ser simplificada usando array.reduce()

// Atual
let total = 0;
for (let i = 0; i < items.length; i++) {
  total += items[i].price;
}

// Sugerido
const total = items.reduce((sum, item) => sum + item.price, 0);

Isso torna o código mais conciso e idiomático em JavaScript.
```

**Como autor:**

✅ **Fazer:**
- Responder todos os comentários
- Agradecer feedback
- Explicar decisões
- Fazer mudanças solicitadas
- Resolver conversas

❌ **Evitar:**
- Ficar defensivo
- Ignorar feedback
- Discutir excessivamente
- Forçar aprovação

## GitHub Actions: CI/CD

### Workflow Básico

**`.github/workflows/ci.yml`:**

```yaml
name: CI

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest

    strategy:
      matrix:
        node-version: [14.x, 16.x, 18.x]

    steps:
      - name: Checkout código
        uses: actions/checkout@v3

      - name: Setup Node.js ${{ matrix.node-version }}
        uses: actions/setup-node@v3
        with:
          node-version: ${{ matrix.node-version }}
          cache: 'npm'

      - name: Instalar dependências
        run: npm ci

      - name: Lint
        run: npm run lint

      - name: Testes
        run: npm test

      - name: Build
        run: npm run build

      - name: Upload coverage
        uses: codecov/codecov-action@v3
        with:
          file: ./coverage/coverage-final.json
```

### Deploy Automático

**`.github/workflows/deploy.yml`:**

```yaml
name: Deploy to Production

on:
  push:
    branches: [ main ]

jobs:
  deploy:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'

      - name: Install dependencies
        run: npm ci

      - name: Build
        run: npm run build

      - name: Deploy to Vercel
        uses: amondnet/vercel-action@v20
        with:
          vercel-token: ${{ secrets.VERCEL_TOKEN }}
          vercel-org-id: ${{ secrets.ORG_ID }}
          vercel-project-id: ${{ secrets.PROJECT_ID }}
          vercel-args: '--prod'
```

## GitHub Pages: Documentação

### Ativar GitHub Pages

1. Repository → Settings → Pages
2. Source: Deploy from branch
3. Branch: main ou gh-pages
4. Folder: / (root) ou /docs
5. Save

### Jekyll Site Básico

**`_config.yml`:**

```yaml
title: Meu Projeto
description: Documentação oficial
theme: jekyll-theme-cayman

plugins:
  - jekyll-feed
  - jekyll-sitemap

markdown: kramdown

collections:
  docs:
    output: true
    permalink: /:collection/:name
```

**`index.md`:**

```markdown
---
layout: default
title: Home
---

# Bem-vindo ao Meu Projeto

Documentação completa do projeto.

## Começando

- [Instalação](docs/instalacao)
- [Guia Rápido](docs/guia-rapido)
- [API Reference](docs/api)

## Recursos

- [GitHub](https://github.com/usuario/projeto)
- [Issues](https://github.com/usuario/projeto/issues)
- [Releases](https://github.com/usuario/projeto/releases)
```

## Segurança no GitHub

### Dependabot

Automatiza atualizações de dependências vulneráveis.

**`.github/dependabot.yml`:**

```yaml
version: 2
updates:
  - package-ecosystem: "npm"
    directory: "/"
    schedule:
      interval: "weekly"
    open-pull-requests-limit: 10

  - package-ecosystem: "pip"
    directory: "/"
    schedule:
      interval: "daily"
```

### Code Scanning

**`.github/workflows/codeql.yml`:**

```yaml
name: "CodeQL"

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 0 * * 1'

jobs:
  analyze:
    name: Analyze
    runs-on: ubuntu-latest

    strategy:
      fail-fast: false
      matrix:
        language: [ 'javascript', 'python' ]

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Initialize CodeQL
        uses: github/codeql-action/init@v2
        with:
          languages: ${{ matrix.language }}

      - name: Autobuild
        uses: github/codeql-action/autobuild@v2

      - name: Perform CodeQL Analysis
        uses: github/codeql-action/analyze@v2
```

### Secret Scanning

GitHub automaticamente escaneia commits por:
- API keys
- Tokens de acesso
- Credenciais
- Certificados privados

**Boas práticas:**
- ❌ Nunca commitar secrets
- ✅ Usar environment variables
- ✅ Usar GitHub Secrets para CI/CD
- ✅ Usar .gitignore para arquivos sensíveis

## Recursos Avançados

### GitHub Projects

Gerenciamento de projetos estilo Kanban:

1. Repository → Projects → New project
2. Template: Board, Table, ou Roadmap
3. Adicionar cards vinculados a issues/PRs
4. Automatizar com workflows

### GitHub Discussions

Fórum de discussão integrado:

1. Repository → Settings → Features
2. Enable Discussions
3. Categorias: Announcements, Q&A, Ideas, etc.

### GitHub Sponsors

Receber patrocínios para seu trabalho open source:

1. Profile → Sponsors
2. Configure `FUNDING.yml`
3. Adicionar tiers de patrocínio

## Comandos GitHub CLI (gh)

```bash
# Instalar
brew install gh

# Autenticar
gh auth login

# Repositórios
gh repo create nome-projeto --public
gh repo clone usuario/repo
gh repo view

# Issues
gh issue list
gh issue create --title "Bug" --body "Descrição"
gh issue view 123
gh issue close 123

# Pull Requests
gh pr list
gh pr create --title "Feature" --body "Descrição"
gh pr view 456
gh pr checkout 456
gh pr review --approve
gh pr merge

# Actions
gh run list
gh run view
gh run watch

# Releases
gh release create v1.0.0 --notes "Release notes"
gh release list
```

## Contribuindo para Open Source

### Como Contribuir

```bash
# 1. Fork o projeto (via interface)

# 2. Clonar seu fork
git clone git@github.com:seu-usuario/projeto.git
cd projeto

# 3. Adicionar upstream
git remote add upstream git@github.com:original/projeto.git

# 4. Criar branch
git checkout -b fix/minha-contribuicao

# 5. Fazer mudanças
git add .
git commit -m "fix: corrige bug X"

# 6. Push para seu fork
git push origin fix/minha-contribuicao

# 7. Abrir PR
gh pr create --web
```

### Etiqueta Open Source

✅ **Fazer:**
- Ler CONTRIBUTING.md
- Seguir code of conduct
- Respeitar mantenedores
- Testar suas mudanças
- Documentar código
- Responder feedback

❌ **Evitar:**
- PRs gigantes não solicitados
- Mudanças de estilo não relacionadas
- Exigir merge imediato
- Ser rude ou impaciente

## Perfil Profissional

### Showcase de Projetos

**Pins no perfil:**
1. Profile → Customize pins
2. Escolher até 6 repositórios
3. Reordenar conforme prioridade

**Critérios para pins:**
- ✨ Projetos mais impressionantes
- 📈 Com boa documentação
- 🎯 Demonstram suas skills
- ⭐ Com estrelas/forks

### Contributions Graph

Mantenha verde:
- Commits regulares
- PRs em projetos open source
- Issues abertas/comentadas
- Code reviews

**Dica:** Configure commits para contar:
```bash
git config --global user.email "seu-email-github@example.com"
```

## Estatísticas e Badges

### GitHub Stats

```markdown
![Estatísticas GitHub](https://github-readme-stats.vercel.app/api?username=seu-usuario&show_icons=true&theme=dark)

![Linguagens](https://github-readme-stats.vercel.app/api/top-langs/?username=seu-usuario&layout=compact)

![Streak](https://github-readme-streak-stats.herokuapp.com/?user=seu-usuario)
```

### Badges Comuns

```markdown
![Build](https://img.shields.io/github/workflow/status/usuario/repo/CI)
![License](https://img.shields.io/github/license/usuario/repo)
![Version](https://img.shields.io/github/v/release/usuario/repo)
![Stars](https://img.shields.io/github/stars/usuario/repo)
![Forks](https://img.shields.io/github/forks/usuario/repo)
![Issues](https://img.shields.io/github/issues/usuario/repo)
![PRs](https://img.shields.io/github/issues-pr/usuario/repo)
```

## Referências e Recursos

### Documentação Oficial
- [GitHub Docs](https://docs.github.com)
- [GitHub Skills](https://skills.github.com)
- [GitHub Community](https://github.community)

### Tutoriais
- [GitHub Learning Lab](https://lab.github.com)
- [GitHub Guides](https://guides.github.com)
- [First Contributions](https://firstcontributions.github.io)

### Ferramentas
- [GitHub CLI](https://cli.github.com)
- [GitHub Desktop](https://desktop.github.com)
- [GitHub Mobile](https://github.com/mobile)

---

## Conclusão

Parabéns! 🎉 Agora você possui conhecimento completo sobre Git e GitHub:

- ✅ Fundamentos teóricos do controle de versão
- ✅ História e evolução do Git
- ✅ Como o Git funciona internamente
- ✅ Boas práticas das maiores empresas
- ✅ Arquiteturas e workflows profissionais
- ✅ Domínio completo do GitHub

## Próximos Passos

1. **Pratique regularmente**: Use Git em todos os seus projetos
2. **Contribua para open source**: Ganhe experiência real
3. **Construa portfólio**: Mantenha GitHub atualizado
4. **Continue aprendendo**: Git evolui constantemente
5. **Compartilhe conhecimento**: Ensine outros desenvolvedores

---

<div align="center">

**🐙 GitHub é sua vitrine profissional - use bem!**

*[← Voltar ao Índice](README.md)* | *[← Anterior](05-arquiteturas-git.md)*

---

*Módulo de Versionamento - Aulas de Graduação em Ciência da Computação*

⭐ **Se este material te ajudou, considere dar uma estrela no repositório!** ⭐

</div>
