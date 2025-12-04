# Command Pattern (Padrão Comando)

O padrão Command encapsula uma requisição como um objeto, permitindo parametrizar clientes com diferentes requisições, enfileirar requisições e implementar operações que podem ser desfeitas (undo).

## 🎯 Problema

Como desacoplar o objeto que invoca uma operação do objeto que sabe como executá-la? Como implementar undo/redo de forma elegante?

## 💡 Solução

Encapsular cada requisição em um objeto Command com interface comum, permitindo parametrização, enfileiramento e execução diferida.

## 🚀 Como Executar

```bash
javac *.java
java TesteCommand
```

## ✅ Vantagens

- Desacopla invocador de executor
- Suporta undo/redo facilmente
- Permite macros e enfileiramento
- Logging de operações

## 📋 Componentes

- Command.java - Interface do comando
- ConcreteCommands.java - Comandos concretos (Write, Delete, etc)
- TextEditor.java - Receiver (executa operações)
- CommandInvoker.java - Invoca comandos
- TesteCommand.java - Demonstração
