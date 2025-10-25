# Facade Pattern

O padrão Facade fornece uma interface unificada e simplificada para um conjunto de interfaces em um subsistema complexo, facilitando o uso do subsistema.

## 🎯 Problema

Subsistemas complexos com muitas classes interdependentes são difíceis de usar. Clientes precisam conhecer muitos detalhes internos para realizar operações simples.

### Exemplo Real
Sistema de home theater com DVD player, amplificador, projetor, luzes, tela, etc. Para assistir um filme, precisa ligar tudo na ordem certa. Uma facade simplifica: `homeTheater.assistirFilme("Matrix")`.

## 💡 Solução

Criar uma classe Facade que:
1. Fornece interface simples e de alto nível
2. Encapsula complexidade do subsistema
3. Delega chamadas para objetos do subsistema
4. Não impede acesso direto ao subsistema quando necessário

## 🏗️ Estrutura

```
┌─────────────┐
│   Cliente   │
└─────────────┘
       │
       ▼
┌─────────────┐      ┌──────────────┐
│   Facade    │─────>│ SubsistemaA  │
└─────────────┘      └──────────────┘
       │             ┌──────────────┐
       └────────────>│ SubsistemaB  │
                     └──────────────┘
                     ┌──────────────┐
                     │ SubsistemaC  │
                     └──────────────┘
```

## 📝 Implementações

### Sistema de Home Theater

- **[DVDPlayer.java](DVDPlayer.java)** - Subsistema para reprodução de DVD
- **[Amplificador.java](Amplificador.java)** - Subsistema de áudio
- **[Projetor.java](Projetor.java)** - Subsistema de projeção
- **[Luzes.java](Luzes.java)** - Subsistema de iluminação
- **[HomeTheaterFacade.java](HomeTheaterFacade.java)** - Facade que simplifica uso
- **[TesteFacade.java](TesteFacade.java)** - Demonstração do padrão

## 🚀 Como Executar

```bash
javac *.java
java TesteFacade
```

## ✅ Vantagens

1. **Simplicidade**: Interface fácil de usar
2. **Desacoplamento**: Clientes não conhecem detalhes do subsistema
3. **Manutenibilidade**: Mudanças no subsistema não afetam clientes
4. **Organização**: Agrupa funcionalidades relacionadas

## ⚠️ Desvantagens

1. **God Object**: Facade pode se tornar muito grande
2. **Limitação**: Pode não expor todas as funcionalidades
3. **Acoplamento**: Facade acoplada ao subsistema

## 🎯 Quando Usar

✅ **Use quando**:
- Quer interface simples para subsistema complexo
- Precisa desacoplar cliente de subsistema
- Quer estruturar subsistema em camadas

❌ **Evite quando**:
- Subsistema já é simples
- Clientes precisam de controle fino

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Decorator](../decorator/)
- [Próximo: Composite](../composite/)
