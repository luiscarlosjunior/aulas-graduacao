# Composite Pattern

O padrão Composite compõe objetos em estruturas de árvore para representar hierarquias parte-todo. Permite que clientes tratem objetos individuais e composições de forma uniforme.

## 🎯 Problema

Como representar hierarquias parte-todo onde objetos individuais e grupos precisam ser tratados uniformemente? Exemplo: arquivos e pastas, componentes gráficos, estrutura organizacional.

## 💡 Solução

Criar estrutura de árvore onde:
1. Interface comum para objetos simples e compostos
2. Folhas (objetos simples) implementam operações básicas
3. Composites (grupos) mantêm filhos e delegam operações

## 🏗️ Estrutura

```
┌─────────────┐
│  Component  │
│ (interface) │
└─────────────┘
       △
       │
    ├──┴──┐
    │     │
┌───────┐ ┌──────────┐
│ Leaf  │ │Composite │
└───────┘ └──────────┘
             │ children
             └─────────> Component*
```

## 📝 Implementações

### Sistema de Arquivos

- **[ElementoSistemaArquivos.java](ElementoSistemaArquivos.java)** - Interface Component
- **[Arquivo.java](Arquivo.java)** - Leaf (elemento simples)
- **[Pasta.java](Pasta.java)** - Composite (contém outros elementos)
- **[TesteComposite.java](TesteComposite.java)** - Demonstração

## 🚀 Como Executar

```bash
javac *.java
java TesteComposite
```

## ✅ Vantagens

1. **Uniformidade**: Trata objetos simples e compostos da mesma forma
2. **Flexibilidade**: Fácil adicionar novos tipos de componentes
3. **Estruturas Recursivas**: Natural para hierarquias em árvore

## ⚠️ Desvantagens

1. **Generalização**: Interface muito geral pode ser confusa
2. **Restrições**: Difícil restringir tipos de filhos

## 🎯 Quando Usar

✅ **Use quando**:
- Precisa representar hierarquias parte-todo
- Quer tratar objetos individuais e composições uniformemente
- Estrutura pode ser representada como árvore

❌ **Evite quando**:
- Estrutura não é hierárquica
- Componentes individuais e compostos são muito diferentes

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Facade](../facade/)
- [Próximo: Proxy](../proxy/)
