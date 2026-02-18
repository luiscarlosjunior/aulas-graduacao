# State Pattern (Padrão Estado)

Permite que um objeto altere seu comportamento quando seu estado interno muda. O objeto parecerá ter mudado sua classe.

## 🎯 Problema

Como implementar máquinas de estado sem condicionais complexos?

## 💡 Solução

Encapsular cada estado em uma classe separada e delegar o comportamento para o estado atual.

## 🚀 Como Executar

```bash
javac *.java
java TesteState
```

## ✅ Vantagens

- Elimina condicionais complexos
- Cada estado é uma classe separada
- Fácil adicionar novos estados
- Transições de estado explícitas
