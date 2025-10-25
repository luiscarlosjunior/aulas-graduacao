# Proxy Pattern

O padrão Proxy fornece um substituto ou placeholder para controlar o acesso a um objeto. Adiciona funcionalidade de controle sem alterar o objeto original.

## 🎯 Problema

Como controlar acesso a um objeto? Adicionar funcionalidades como lazy loading, controle de acesso, logging ou cache sem modificar o objeto original.

## 💡 Solução

Criar proxy que:
1. Implementa mesma interface do objeto real
2. Contém referência ao objeto real
3. Controla acesso e pode adicionar comportamento extra
4. Delega operações ao objeto real quando apropriado

## 🏗️ Estrutura

```
┌─────────────┐
│   Subject   │
│ (interface) │
└─────────────┘
       △
       │
    ├──┴──┐
    │     │
┌───────────┐  ┌──────────┐
│   Proxy   │─>│RealSubject│
└───────────┘  └──────────┘
```

## 📝 Tipos de Proxy

### 1. Virtual Proxy (Lazy Loading)
Adia criação de objetos caros até que sejam necessários.

### 2. Protection Proxy (Controle de Acesso)
Controla acesso baseado em permissões.

### 3. Remote Proxy
Representa objeto em espaço de endereçamento diferente.

### 4. Cache Proxy
Armazena resultados de operações caras.

## 📝 Implementações

### Sistema de Carregamento de Imagens

- **[Imagem.java](Imagem.java)** - Interface Subject
- **[ImagemReal.java](ImagemReal.java)** - RealSubject (imagem real)
- **[ImagemProxy.java](ImagemProxy.java)** - Proxy com lazy loading
- **[TesteProxy.java](TesteProxy.java)** - Demonstração

## 🚀 Como Executar

```bash
javac *.java
java TesteProxy
```

## ✅ Vantagens

1. **Controle de Acesso**: Adiciona camada de controle
2. **Lazy Initialization**: Carrega recursos sob demanda
3. **Performance**: Cache pode melhorar performance
4. **Transparência**: Cliente usa mesma interface

## ⚠️ Desvantagens

1. **Complexidade**: Adiciona camada extra
2. **Latência**: Pode adicionar overhead
3. **Manutenção**: Proxy precisa estar sincronizado com objeto real

## 🎯 Quando Usar

✅ **Use quando**:
- Precisa de lazy initialization
- Quer controlar acesso a recursos
- Precisa adicionar logging ou cache
- Quer representar objeto remoto localmente

❌ **Evite quando**:
- Acesso direto é suficiente
- Overhead não é aceitável

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Composite](../composite/)
