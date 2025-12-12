# 🔬 Como o Algoritmo do Git Funciona

## Introdução Técnica

O Git não é apenas um sistema de controle de versão - é um **sistema de arquivos endereçável por conteúdo** com uma interface de controle de versão escrita sobre ele.

> "Git é fundamentalmente um sistema de arquivos com conteúdo endereçável e uma interface VCS escrita por cima."
> — Linus Torvalds

## Estrutura de Dados Fundamental

### O Banco de Dados de Objetos

Git armazena tudo como **objetos** em um banco de dados de chave-valor:

```
Chave (SHA-1 Hash) → Valor (Conteúdo do Objeto)
```

**Exemplo:**
```
a1b2c3d4e5f6... → [Dados do arquivo]
```

### Quatro Tipos de Objetos

```
┌─────────────────────────────────────┐
│      Git Object Database            │
├─────────────────────────────────────┤
│ 1. Blob   - Conteúdo de arquivo     │
│ 2. Tree   - Estrutura de diretório  │
│ 3. Commit - Snapshot + metadados    │
│ 4. Tag    - Referência nomeada      │
└─────────────────────────────────────┘
```

## 1. Objeto Blob (Binary Large Object)

### O que é um Blob?

Um blob armazena o **conteúdo** de um arquivo, sem nome ou estrutura.

**Estrutura:**
```
blob [tamanho]\0[conteúdo]
```

**Exemplo:**
```bash
# Arquivo: hello.txt
# Conteúdo: "Hello, Git!"

# Git armazena como:
blob 11\0Hello, Git!

# Hash SHA-1:
5dd01c177f5d7d1be5346a5bc18a569a7410c2ef
```

### Como Funciona

```python
# Pseudo-código de como Git cria blob
def create_blob(content):
    header = f"blob {len(content)}\0"
    store = header + content
    sha1_hash = sha1(store)
    save_to_database(sha1_hash, store)
    return sha1_hash
```

**Características:**
- ✅ Conteúdo imutável
- ✅ Mesmo conteúdo = mesmo hash
- ✅ Deduplicação automática
- ✅ Não armazena nome do arquivo

## 2. Objeto Tree (Árvore)

### O que é uma Tree?

Uma tree representa uma **estrutura de diretório** - é como um "commit instantâneo" de um diretório.

**Estrutura:**
```
tree [tamanho]\0
[modo] [nome]\0[SHA-1 do objeto]
[modo] [nome]\0[SHA-1 do objeto]
...
```

**Exemplo:**
```
tree 99\0
100644 blob a906cb README.md
040000 tree 99f1a src/
100644 blob 47c6b hello.txt
```

### Visualização de Tree

```
Tree Root (a1b2c3)
├── README.md (blob: 5dd01c)
├── hello.txt (blob: 47c6ba)
└── src/ (tree: 99f1a2)
    ├── main.py (blob: 3f7a8c)
    └── utils.py (blob: 8d2b1f)
```

**Características:**
- ✅ Referencia outros trees (subdiretórios)
- ✅ Referencia blobs (arquivos)
- ✅ Armazena permissões (mode)
- ✅ Armazena nomes de arquivos

## 3. Objeto Commit

### O que é um Commit?

Um commit é um **snapshot do projeto** em um ponto no tempo, com metadados.

**Estrutura:**
```
commit [tamanho]\0
tree [SHA-1 da tree raiz]
parent [SHA-1 do commit pai] (opcional, múltiplos para merge)
author [nome] <email> [timestamp] [timezone]
committer [nome] <email> [timestamp] [timezone]

[mensagem do commit]
```

**Exemplo Real:**
```
commit 223
tree a1b2c3d4e5f6789012345678901234567890
parent b2c3d4e5f6789012345678901234567890a1
author João Silva <joao@email.com> 1609459200 -0300
committer João Silva <joao@email.com> 1609459200 -0300

Adiciona validação de email

Implementa validação completa de email usando regex
e testes unitários para garantir funcionamento correto.
```

### Anatomia de um Commit

```
┌────────────────────────────────────┐
│ Commit Object (4a5b6c)             │
├────────────────────────────────────┤
│ tree:      a1b2c3 (snapshot)       │
│ parent:    7d8e9f (commit anterior)│
│ author:    João Silva              │
│ date:      2024-01-15 14:30        │
│ message:   "Adiciona feature X"    │
└────────────────────────────────────┘
          │
          ↓
┌────────────────────────────────────┐
│ Tree Object (a1b2c3)               │
├────────────────────────────────────┤
│ README.md  → blob 5dd01c           │
│ src/       → tree 99f1a2           │
│ main.py    → blob 3f7a8c           │
└────────────────────────────────────┘
```

**Características:**
- ✅ Imutável (nunca muda)
- ✅ Ponteiros para tree e parent(s)
- ✅ Metadados completos
- ✅ Mensagem descritiva

## 4. Objeto Tag

### O que é uma Tag?

Uma tag é um **ponteiro nomeado** para um commit específico, geralmente usado para releases.

**Tipos:**
1. **Lightweight tag**: Apenas um ponteiro
2. **Annotated tag**: Objeto completo com metadados

**Estrutura (Annotated):**
```
tag [tamanho]\0
object [SHA-1 do commit]
type commit
tag v1.0.0
tagger [nome] <email> [timestamp] [timezone]

[mensagem da tag]
```

## Sistema de Endereçamento SHA-1

### Como Funciona o Hash SHA-1

**SHA-1** (Secure Hash Algorithm 1) gera um hash de **160 bits** (40 caracteres hexadecimais).

```python
# Exemplo simplificado
import hashlib

def git_hash(content, type='blob'):
    header = f"{type} {len(content)}\0"
    store = header.encode() + content.encode()
    return hashlib.sha1(store).hexdigest()

# Uso:
hash_value = git_hash("Hello, Git!")
print(hash_value)  # 5dd01c177f5d7d1be5346a5bc18a569a7410c2ef
```

### Propriedades do SHA-1

**Características:**
- ✅ **Determinístico**: Mesmo conteúdo = mesmo hash
- ✅ **Único**: Colisões extremamente raras
- ✅ **Verificável**: Integridade garantida
- ✅ **Distribuído**: Sem coordenação central

**Probabilidade de Colisão:**
```
Chance de colisão com 10^18 objetos: 0.0000000000001%
```

### Armazenamento no Disco

```
.git/objects/
├── 5d/
│   └── d01c177f5d7d1be5346a5bc18a569a7410c2ef
├── a1/
│   └── b2c3d4e5f6789012345678901234567890
└── b2/
    └── c3d4e5f6789012345678901234567890a1

# Formato: .git/objects/[2 primeiros chars]/[38 chars restantes]
```

## Grafo Acíclico Direcionado (DAG)

### Estrutura de Commits

Git organiza commits em um **DAG** - Directed Acyclic Graph (Grafo Acíclico Direcionado).

```
A ← B ← C ← D ← E (main)
     ↖       ↗
       F ← G (feature)
```

**Propriedades do DAG:**
- ✅ **Direcionado**: Commits apontam para pais
- ✅ **Acíclico**: Sem loops/ciclos
- ✅ **Múltiplos pais**: Merges possíveis
- ✅ **Histórico preservado**: Imutável

### Exemplo Real de DAG

```
         main
           ↓
A ← B ← C ← H (merge)
     ↖     ↗
      D ← E ← F (feature)
           ↖
            G (hotfix)

Commits:
- A: Initial commit
- B, D: Branch point
- C, E, F: Desenvolvimento paralelo
- G: Hotfix
- H: Merge de feature em main
```

## Como Git Rastreia Mudanças

### Git NÃO Armazena Diffs

Diferente de outros VCS, Git **não** armazena mudanças incrementais:

**Outros sistemas (diff-based):**
```
v1 → +linha 5 → +linha 10 → -linha 3 → v4
```

**Git (snapshot-based):**
```
v1 (snapshot completo)
v2 (snapshot completo)
v3 (snapshot completo)
v4 (snapshot completo)
```

### Otimização: Packfiles

Para economizar espaço, Git usa **packfiles**:

```
Antes (objetos soltos):
file_v1.txt (100 KB)
file_v2.txt (101 KB) ← 99% igual a v1
file_v3.txt (102 KB) ← 99% igual a v2
Total: 303 KB

Depois (packfile):
file_v1.txt (100 KB)
delta_v2    (1 KB)   ← diff de v1
delta_v3    (1 KB)   ← diff de v2
Total: 102 KB
```

**Comandos:**
```bash
# Git faz isso automaticamente
git gc  # Garbage collection + pack

# Ver packfiles
ls .git/objects/pack/
```

## Três Áreas de Trabalho

### Working Directory → Staging → Repository

```
┌─────────────────┐  git add   ┌─────────────┐  git commit  ┌────────────┐
│ Working         │ ─────────→ │ Staging     │ ──────────→  │ Repository │
│ Directory       │            │ Area (Index)│              │ (.git)     │
│                 │ ←───────── │             │ ←──────────  │            │
│ Arquivos locais │  checkout  │ Preparação  │    reset     │ Histórico  │
└─────────────────┘            └─────────────┘              └────────────┘
```

### 1. Working Directory

Arquivos em uso no sistema de arquivos.

### 2. Staging Area (Index)

Área de preparação para o próximo commit.

**Estrutura do Index:**
```
.git/index (arquivo binário)
├── README.md → blob 5dd01c (staged)
├── src/main.py → blob 3f7a8c (staged)
└── test.py → blob 8d2b1f (staged)
```

### 3. Repository

Banco de dados de objetos comprimidos.

## Branches: Ponteiros Móveis

### O que é uma Branch?

Uma branch é simplesmente um **ponteiro móvel** para um commit.

```
.git/refs/heads/main
↓
4a5b6c7d8e9f... (SHA-1 do commit)
```

**Arquivo real:**
```bash
$ cat .git/refs/heads/main
4a5b6c7d8e9f0123456789abcdef0123456789
```

### Branches são Baratas

```python
# Criar branch = criar arquivo de 41 bytes
def create_branch(name, commit_sha):
    with open(f'.git/refs/heads/{name}', 'w') as f:
        f.write(commit_sha + '\n')

# Custo: 41 bytes, 0.001 segundos
```

### HEAD: Onde Você Está

```
.git/HEAD
↓
ref: refs/heads/main
↓
4a5b6c7d... (commit atual)
```

**Visualização:**
```
HEAD → main → C4
              ↑
A ← B ← C ← C4
     ↖
      feature → F2
```

## Merge: Três Vias

### Como Git Faz Merge

Git usa **three-way merge**:

```
        Base (B)
       ↗        ↖
  Branch A       Branch C
  (mudou X)      (mudou Y)
       ↘        ↙
       Merge (M)
     (tem X e Y)
```

**Algoritmo:**
```python
def three_way_merge(base, branch_a, branch_c):
    result = {}
    
    for file in all_files:
        if file unchanged in all:
            result[file] = base[file]
        elif file changed only in A:
            result[file] = branch_a[file]
        elif file changed only in C:
            result[file] = branch_c[file]
        elif file changed in both:
            # CONFLITO! Requer intervenção manual
            result[file] = CONFLICT
    
    return result
```

### Tipos de Merge

**1. Fast-Forward**
```
Antes:
main → A ← B
feature → A ← B ← C

Depois:
main → A ← B ← C
feature → A ← B ← C
```

**2. Three-Way Merge**
```
Antes:
main → A ← B ← C
feature → A ← D ← E

Depois:
main → A ← B ← C ← M (merge commit)
feature → A ← D ← E ↗
```

## Rebase: Reescrevendo História

### Como Rebase Funciona

Rebase **reaplica commits** em uma nova base:

```
Antes:
main → A ← B ← C
feature → A ← D ← E

git rebase main (em feature)

Depois:
main → A ← B ← C
feature → A ← B ← C ← D' ← E' (novos SHAs!)
```

**Processo:**
1. Git encontra commits únicos em feature (D, E)
2. Salva diffs temporariamente
3. Reseta feature para main
4. Reaplica D e E sequencialmente
5. Novos commits D' e E' com novos SHAs

### Merge vs Rebase

| Aspecto | Merge | Rebase |
|---------|-------|--------|
| **Histórico** | Preserva original | Reescreve |
| **Linearidade** | Grafo complexo | Linear limpo |
| **Commits** | SHAs originais | Novos SHAs |
| **Conflitos** | Uma vez | Possivelmente múltiplos |
| **Uso** | Branches públicas | Branches locais |

## Compressão e Eficiência

### Delta Compression

Git usa compressão delta em packfiles:

```python
# Pseudo-código
def create_delta(base_obj, new_obj):
    delta = compute_diff(base_obj, new_obj)
    compressed_delta = zlib.compress(delta)
    return compressed_delta

# Resultado: 1-2% do tamanho original
```

### Exemplo Real

```
Kernel Linux (2024):
- Arquivos: 70,000+
- Commits: 1,000,000+
- Histórico completo: ~3.5 GB
- Clonagem rasa (--depth 1): ~200 MB
```

## Garbage Collection

### Como Git Limpa Dados

```bash
# Garbage collection manual
$ git gc

# O que acontece:
1. Identifica objetos inacessíveis
2. Remove objetos soltos antigos
3. Compacta em packfiles
4. Otimiza estruturas internas
```

### Objetos Inacessíveis

```
main → A ← B ← C
          ↗
       D (órfão, será removido)
```

## Exercícios Práticos

### 1. Explorando Objetos

```bash
# Ver hash de um arquivo
$ git hash-object README.md

# Ver conteúdo de um objeto
$ git cat-file -p 5dd01c177f5d

# Ver tipo de um objeto
$ git cat-file -t 5dd01c177f5d

# Ver árvore de um commit
$ git ls-tree HEAD
```

### 2. Inspecionando Estrutura

```bash
# Ver DAG graficamente
$ git log --graph --oneline --all

# Ver objetos no banco de dados
$ find .git/objects -type f

# Ver conteúdo do index
$ git ls-files --stage
```

### 3. Criando Objetos Manualmente

```bash
# Criar blob
$ echo "Test" | git hash-object -w --stdin

# Criar tree
$ git write-tree

# Criar commit
$ git commit-tree [tree-sha] -m "Test commit"
```

## Algoritmos Avançados

### 1. Content-Addressable Storage

```
Chave = SHA-1(conteúdo)
Valor = conteúdo comprimido

Benefícios:
- Deduplicação automática
- Integridade garantida
- Distribuição sem coordenação
```

### 2. Binary Delta Algorithm

```
Myers Diff Algorithm:
- Encontra Longest Common Subsequence (LCS)
- Computa diff minimal
- Aplica compressão zlib
```

### 3. Three-Way Merge Algorithm

```
Para cada linha:
  Se igual em todos → mantém
  Se mudou só em A → usa A
  Se mudou só em B → usa B
  Se mudou em A e B:
    Se mudanças iguais → usa qualquer
    Se mudanças diferentes → CONFLITO
```

## Performance Benchmarks

### Operações Git vs SVN

| Operação | Git | SVN | Fator |
|----------|-----|-----|-------|
| **Commit** | 0.1s | 2s | 20x |
| **Branch** | 0.001s | 1s | 1000x |
| **Merge** | 0.5s | 5s | 10x |
| **Log** | 0.1s | 3s | 30x |
| **Diff** | 0.01s | 0.5s | 50x |

**Por que Git é rápido?**
- Operações locais (sem rede)
- Estrutura de dados otimizada
- Branches como ponteiros
- Compressão eficiente

## Referências Técnicas

### Documentação Oficial
- [Git Internals](https://git-scm.com/book/en/v2/Git-Internals-Plumbing-and-Porcelain)
- [Git Objects](https://git-scm.com/book/en/v2/Git-Internals-Git-Objects)
- [Packfile Format](https://git-scm.com/docs/pack-format)

### Artigos Técnicos
- "Git from the Bottom Up" - John Wiegley
- "Understanding Git Internals" - Scott Chacon
- "How Git Works: DAG Explained"

### Código Fonte
- [Git Source Code](https://github.com/git/git)
- Escrito principalmente em C
- Altamente otimizado para performance

---

## Próximos Passos

Agora que você entende como Git funciona internamente, vamos ver como as grandes empresas o utilizam:

➡️ **[04 - Trabalhando com Git - Boas Práticas](04-trabalhando-com-git.md)** - Metodologias profissionais

---

<div align="center">

**🔬 Entender os algoritmos do Git te torna um usuário muito mais eficaz!**

*[← Voltar ao Índice](README.md)* | *[← Anterior](02-historia-git.md)* | *[Próximo →](04-trabalhando-com-git.md)*

</div>
