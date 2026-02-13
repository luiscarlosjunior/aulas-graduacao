# 🚀 Guia Rápido - Primeiros Passos

> Guia prático para começar seu projeto rapidamente

## ⚡ TL;DR - O Essencial

1. **Forme seu grupo** (1-8 pessoas)
2. **Escolha**: Projeto novo OU melhoria de existente
3. **Crie repositório no GitHub**
4. **Use nossos templates** para documentar
5. **Commits frequentes** ao longo de 3 meses
6. **Apresente** no final

**Avaliação:** 40% GitHub + 30% Apresentação + 30% Projeto

---

## 📋 Checklist dos Primeiros 7 Dias

### Dia 1: Organização da Equipe
- [ ] Formar grupo (1-8 alunos)
- [ ] Criar grupo no WhatsApp/Discord
- [ ] Decidir horários de reunião semanal
- [ ] Criar contas no GitHub (se não tiver)

### Dia 2-3: Ideação
- [ ] Brainstorm de problemas sociais
- [ ] Listar 3-5 ideias de projeto
- [ ] Pesquisar soluções existentes
- [ ] Votar na melhor ideia

### Dia 4: Validação
- [ ] Conversar com 2-3 potenciais usuários
- [ ] Validar se o problema é real
- [ ] Definir MVP (3-5 funcionalidades essenciais)
- [ ] Estimar viabilidade em 3 meses

### Dia 5-6: Setup
- [ ] Criar repositório no GitHub
- [ ] Adicionar todos os membros como colaboradores
- [ ] Criar README inicial com nome e descrição
- [ ] Configurar .gitignore
- [ ] Criar estrutura de pastas básica

### Dia 7: Documentação
- [ ] Preencher template de proposta
- [ ] Fazer primeiro commit
- [ ] Criar issues iniciais
- [ ] Setup do GitHub Projects

---

## 🎯 Escolhendo Seu Projeto

### Opção A: Projeto Novo ✨

**Vantagens:**
- ✅ Liberdade total de escolha de tecnologias
- ✅ Código limpo desde o início
- ✅ Aprende tudo do zero

**Use quando:**
- Tem uma ideia clara de problema para resolver
- Quer experimentar novas tecnologias
- Tem tempo para planejamento completo

**Template:** Use `template-proposta-projeto.md`

### Opção B: Melhoria de Existente 🔄

**Vantagens:**
- ✅ Código base já pronto
- ✅ Foco em melhorias específicas
- ✅ Aprende a trabalhar com código legado

**Use quando:**
- Já tem um projeto que pode melhorar
- Quer adicionar funcionalidades novas
- Prefere refinar a criar do zero

**Template:** Use `template-melhorias-projeto.md`

---

## 💡 Dicas para Escolher um Bom Problema

### ✅ Bons Problemas

- **Específico**: "Idosos esquecem de tomar remédios"
- **Observável**: Você viu ou viveu o problema
- **Solucionável**: Tecnologia pode ajudar
- **Escopo adequado**: Pode resolver em 3 meses
- **Impacto social**: Ajuda pessoas reais

### ❌ Problemas a Evitar

- **Muito amplo**: "Resolver a saúde pública"
- **Não-tecnológico**: "Pessoas são egoístas"
- **Muito complexo**: "IA para diagnosticar todas as doenças"
- **Sem validação**: Ninguém realmente tem esse problema
- **Apenas comercial**: Foco só em lucro, sem impacto social

---

## 🛠️ Setup Técnico Rápido

### 1. Criar Repositório GitHub

```bash
# Criar repo no GitHub primeiro (via interface web)
# Depois clonar localmente:
git clone https://github.com/seu-usuario/nome-projeto.git
cd nome-projeto

# Configurar git
git config user.name "Seu Nome"
git config user.email "seu@email.com"
```

### 2. Estrutura de Pastas Sugerida

```
meu-projeto/
├── README.md                 # Documentação principal
├── .gitignore               # Arquivos a ignorar
├── docs/                    # Documentação adicional
│   ├── proposta.md          # Proposta completa
│   ├── wireframes/          # Designs
│   └── diagramas/           # Diagramas técnicos
├── src/                     # Código fonte
│   ├── frontend/            # Código do frontend
│   ├── backend/             # Código do backend
│   └── database/            # Scripts de BD
└── tests/                   # Testes (se aplicável)
```

### 3. README Inicial Mínimo

```markdown
# Nome do Projeto

> Breve descrição em uma linha

## 🎯 Problema
[Que problema resolve]

## 💡 Solução
[Como resolve]

## 👥 Equipe
- [Nome 1] - Frontend
- [Nome 2] - Backend
- [Nome 3] - Design

## 🛠️ Tecnologias
- Frontend: [tecnologia]
- Backend: [tecnologia]
- Database: [tecnologia]

## 🚀 Como Executar
[Instruções virão aqui]

## 📊 Status
🔴 Em planejamento
```

### 4. Primeiro Commit

```bash
# Adicionar arquivos
git add .

# Fazer commit
git commit -m "docs: setup inicial do projeto"

# Enviar para GitHub
git push origin main
```

---

## 📚 Recursos Essenciais

### Aprenda Git/GitHub PRIMEIRO

**Se você não sabe Git:**
1. Leia: [Introdução ao Git](../versionamento/01-introducao-academica.md)
2. Pratique: [Como usar GitHub](../versionamento/06-como-usar-github.md)
3. Faça: Tutorial interativo em https://learngitbranching.js.org/

**Comandos mais usados:**
```bash
git status              # Ver status
git add .               # Adicionar mudanças
git commit -m "msg"     # Fazer commit
git push                # Enviar para GitHub
git pull                # Baixar atualizações
git checkout -b feature # Criar branch
```

### Ferramentas Gratuitas

#### Design
- **Figma**: Design de interfaces (grátis)
- **Draw.io**: Diagramas e fluxogramas
- **Canva**: Apresentações e materiais

#### Desenvolvimento
- **VS Code**: Editor de código
- **Postman**: Testar APIs
- **GitHub Desktop**: Git visual (alternativa ao terminal)

#### Hospedagem Gratuita
- **Vercel**: Frontend (React, Next.js)
- **Railway**: Backend e Database
- **Firebase**: Backend as a Service
- **GitHub Pages**: Sites estáticos

---

## 📅 Planejamento Semanal Sugerido

### Semana 1-2: Preparação
- [ ] Formar equipe e escolher projeto
- [ ] Criar repositório e documentação inicial
- [ ] Pesquisar tecnologias
- [ ] Fazer wireframes básicos

### Semana 3-4: Setup Técnico
- [ ] Configurar ambiente de desenvolvimento
- [ ] Estrutura inicial do projeto
- [ ] Primeira versão rodando (Hello World)
- [ ] Setup de banco de dados

### Semana 5-8: MVP
- [ ] Implementar funcionalidade 1
- [ ] Implementar funcionalidade 2
- [ ] Implementar funcionalidade 3
- [ ] Versão básica funcionando

### Semana 9-10: Features Adicionais
- [ ] Melhorias de interface
- [ ] Funcionalidades extras
- [ ] Tratamento de erros
- [ ] Testes

### Semana 11: Refinamento
- [ ] Correção de bugs
- [ ] Otimizações
- [ ] Documentação completa
- [ ] README atualizado

### Semana 12: Apresentação
- [ ] Preparar slides
- [ ] Gravar demo
- [ ] Ensaiar
- [ ] Deploy final

---

## 🎯 Dicas de Ouro

### Para o Projeto Ter Sucesso

1. **Comece Simples**
   - MVP primeiro, depois adiciona
   - 3 funcionalidades bem feitas > 10 pela metade

2. **Commits Frequentes**
   - Commit a cada funcionalidade pequena
   - Mínimo 2-3 commits por semana por pessoa
   - Mensagens descritivas

3. **Reuniões Semanais**
   - 30-60 minutos toda semana
   - Review do que foi feito
   - Planejar próxima semana

4. **Use Issues**
   - Uma issue para cada tarefa
   - Atribua responsáveis
   - Marque quando concluído

5. **Comunique-se**
   - Problemas? Avise a equipe cedo
   - Preso em algo? Peça ajuda
   - Grupo ativo = projeto bem-sucedido

### Para Não Reprovar

❌ **NÃO faça:**
- Deixar tudo para última semana
- Commit único gigante no final
- Trabalhar sozinho sem avisar equipe
- Mudar de ideia toda hora
- Esquecer de documentar

✅ **FAÇA:**
- Commits regulares desde início
- Documentação desde semana 1
- Comunicação constante
- Escopo realista
- Pedir ajuda quando precisar

---

## ❓ FAQ Rápido

**P: Quantas linhas de código precisa ter?**
R: Não importa a quantidade, importa a qualidade e se resolve o problema.

**P: Precisa estar perfeito?**
R: Não! MVP funcional é suficiente. Melhor 80% completo que 0% perfeito.

**P: Pode usar bibliotecas prontas?**
R: Sim! Use e abuse de bibliotecas. Não reinvente a roda.

**P: E se der errado?**
R: Normal! Documente o problema e a tentativa de solução. Aprendizado também conta.

**P: Precisa hospedar online?**
R: Não é obrigatório, mas facilita a apresentação.

**P: Quanto tempo dedicar por semana?**
R: Recomendado: 4-6 horas por pessoa por semana.

---

## 📞 Precisa de Ajuda?

### Recursos do Repositório
1. [README Principal da Disciplina](README.md) - Guia completo
2. [Guia de Git/GitHub](../versionamento/README.md) - Aprenda versionamento
3. [Template de Proposta](template-proposta-projeto.md) - Para projetos novos
4. [Template de Melhorias](template-melhorias-projeto.md) - Para projetos existentes

### Canais de Suporte
- 💬 **Grupo da turma**: Perguntas rápidas
- 📧 **Professor**: Dúvidas sobre avaliação
- 🐛 **Issues do seu repo**: Problemas técnicos do projeto
- 📚 **StackOverflow**: Dúvidas de programação

---

## ✅ Checklist Final Antes de Começar

- [ ] Tenho uma equipe formada
- [ ] Temos uma ideia validada
- [ ] Criamos repositório no GitHub
- [ ] Todos têm acesso ao repositório
- [ ] Fizemos o primeiro commit
- [ ] Preenchemos o template inicial
- [ ] Planejamos as próximas 2 semanas
- [ ] Sabemos quando nos reunir
- [ ] Estamos animados com o projeto! 🚀

---

<div align="center">

**🎯 Pronto para começar!**

*"A melhor maneira de prever o futuro é inventá-lo." - Alan Kay*

**Bom projeto a todos! 💻🌍**

---

➡️ **Próximo passo:** [Voltar ao README Principal](README.md) para ver todos os recursos disponíveis

</div>
