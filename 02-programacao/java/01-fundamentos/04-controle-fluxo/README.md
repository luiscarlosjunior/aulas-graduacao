# Controle de Fluxo em Java

Esta seção aborda estruturas condicionais e de repetição — os blocos fundamentais de qualquer programa que toma decisões e processa dados em volume.

## 🎯 Objetivos

- Dominar estruturas condicionais (`if`, `else if`, `else`, `switch`)
- Aplicar loops corretamente (`for`, `while`, `do-while`, `for-each`)
- Entender `break` e `continue` para controle dentro de loops
- Reconhecer quando usar cada estrutura em contextos reais
- Evitar armadilhas comuns: loops infinitos, aninhamento excessivo

## 🏭 Por que isso Importa na Indústria?

O controle de fluxo é a base de **toda lógica de negócio**:

| Conceito | Exemplo Real |
|----------|-------------|
| `if-else` | Aprovação/reprovação de crédito em banco |
| `switch` | Roteamento de pagamento (PIX, boleto, cartão) |
| `for` | Processar 100.000 notas fiscais em lote |
| `while` | Retry com backoff exponencial ao chamar API |
| `do-while` | Menu que executa até o usuário escolher "Sair" |
| `break/continue` | Filtro de busca com paginação |

> 💡 **Fato da indústria**: "Spaghetti code" (código com IFs mal estruturados e aninhados) é um dos maiores problemas em sistemas legados. A metodologia "Clean Code" de Robert C. Martin dedica um capítulo inteiro a como escrever condicionais claros.

## 📋 Conteúdo

### 📄 [ControleFluxoCondicional.java](ControleFluxoCondicional.java)

Exemplos de estruturas condicionais com cenários reais:

1. **Cálculo de Frete** — `if-else if-else` com múltiplas condições
2. **Análise de Crédito** — lógica real de aprovação de empréstimo com early return
3. **Processamento de Pagamento** — `switch-case` com múltiplos métodos de pagamento
4. **Validação de Produto** — Early Return (padrão Clean Code)
5. **Switch Expression** — sintaxe moderna do Java 14+

### 📄 [EstruturaRepeticao.java](EstruturaRepeticao.java)

Exemplos de loops com cenários reais de mercado:

1. **for clássico** — processamento de notas fiscais em lote
2. **for-each** — validação de itens do carrinho de compras
3. **while** — retry com backoff exponencial (microserviços)
4. **do-while** — menu de opções interativo
5. **break e continue** — busca com filtros e paginação
6. **Loops aninhados** — relatório de vendas por região e mês

## 🚀 Como Executar

```bash
# Navegar até o diretório
cd 04-controle-fluxo/

# Compilar todos os arquivos
javac *.java

# Executar estruturas condicionais
java ControleFluxoCondicional

# Executar estruturas de repetição
java EstruturaRepeticao
```

### Saída Esperada — ControleFluxoCondicional
```
=== CONTROLE DE FLUXO CONDICIONAL — EXEMPLOS DA INDÚSTRIA ===

--- 1. IF-ELSE: REGRAS DE FRETE ---
  Peso: 2,5 kg | Destino: SP | VIP: false
  Frete: R$ 8,90 | Prazo: 2 dias úteis

--- 2. ANÁLISE DE CRÉDITO ---
  ✅ CRÉDITO APROVADO!
  Classificação: Ouro | Taxa: 1,29% a.m.
  ...
```

## 🔧 Conceitos Fundamentais

### Estrutura if-else

```java
// Forma básica
if (condição) {
    // executa se verdadeiro
} else {
    // executa se falso
}

// Múltiplas condições
if (condição1) {
    // ...
} else if (condição2) {
    // ...
} else {
    // padrão (fallback)
}
```

### Switch-Case vs. Switch Expression

```java
// Forma clássica (Java 1.0+) — requer 'break'
switch (valor) {
    case 1:
        resultado = "um";
        break;
    case 2:
        resultado = "dois";
        break;
    default:
        resultado = "outro";
}

// Forma moderna (Java 14+) — sem 'break', menos bugs
String resultado = switch (valor) {
    case 1 -> "um";
    case 2 -> "dois";
    default -> "outro";
};
```

### Loops

```java
// for — quando sabe o número de iterações
for (int i = 0; i < 10; i++) { }

// for-each — quando itera sobre coleção sem precisar do índice
for (String item : lista) { }

// while — quando não sabe quantas iterações (condição no início)
while (condição) { }

// do-while — executa pelo menos uma vez (condição no final)
do {
    // código
} while (condição);
```

## 💡 Boas Práticas (do mercado de trabalho)

### ✅ 1. Early Return (Fail-Fast)
```java
// RUIM: código piramidal (aninhamentos profundos)
if (usuarioValido) {
    if (temPermissao) {
        if (estoqueDisponivel) {
            // lógica principal — perdida no fundo!
        }
    }
}

// BOM: early return — falha cedo, código principal no nível base
if (!usuarioValido) return "Usuário inválido";
if (!temPermissao) return "Sem permissão";
if (!estoqueDisponivel) return "Sem estoque";

// lógica principal clara e sem aninhamentos
processarPedido();
```

### ✅ 2. Switch Expression (Java 14+)
```java
// Prefira a forma moderna — sem break, sem fall-through acidental
String status = switch (codigo) {
    case 200 -> "OK";
    case 404 -> "Não encontrado";
    case 500 -> "Erro interno";
    default -> "Desconhecido";
};
```

### ✅ 3. Cuidado com loops aninhados
```java
// Dois loops aninhados = O(n²) — perigoso para n grande
for (int i = 0; i < 1000; i++) {
    for (int j = 0; j < 1000; j++) {
        // 1.000.000 de operações!
    }
}

// Alternativa: use streams paralelos, algoritmos mais eficientes
// ou reconsidere o design
```

### ✅ 4. Evite loops infinitos acidentais
```java
// PERIGOSO: nunca atualiza 'i'!
while (i < 10) {
    System.out.println(i);
    // i++ esquecido → loop infinito → derruba o servidor!
}

// BOM: sempre garanta que a condição eventualmente se torna false
while (i < 10) {
    System.out.println(i);
    i++;  // ← sempre atualize a variável de controle
}
```

## ❗ Erros Comuns

### 1. Comparar Strings com `==`
```java
String status = "ativo";
// ERRADO: compara referência de memória (quase sempre false!)
if (status == "ativo") { ... }  

// CORRETO: compara conteúdo
if (status.equals("ativo")) { ... }
if ("ativo".equals(status)) { ... }  // ← prefira esta: evita NullPointerException
```

### 2. Esquecer `break` no switch clássico
```java
switch (dia) {
    case 1:
        System.out.println("Segunda");
        // FALTA break! → fall-through: executa o case 2 também!
    case 2:
        System.out.println("Terça");
        break;
}
// Com dia=1, imprime "Segunda" E "Terça"! (bug clássico)
```

### 3. Modificar coleção dentro de for-each
```java
// ERRO: ConcurrentModificationException
for (String item : lista) {
    lista.remove(item);  // ← Nunca modifique a coleção que está iterando!
}

// CORRETO: use Iterator ou removeIf
lista.removeIf(item -> item.startsWith("X"));
```

## 📝 Exercícios

1. **Sistema de Avaliação**: Receba uma nota (0-10) e classifique como A, B, C, D, F. Use switch expression.
2. **Calculadora de IMC**: Leia peso e altura, calcule o IMC e classifique: abaixo do peso, normal, sobrepeso, obeso.
3. **Processar CSV**: Dado um array de vendas, calcule total por categoria usando for-each.
4. **Busca com Limite**: Encontre os 3 primeiros produtos de uma categoria usando break.
5. **Retry**: Simule 5 tentativas de acesso a um recurso, com mensagem de erro após a 5ª.

## 🔗 Navegação

[← 03 - Operadores](../03-operadores/) | [05 - Tratamento de Exceções →](../05-tratamento-excecoes/)
