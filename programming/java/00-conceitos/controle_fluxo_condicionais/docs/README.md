# Controle de Fluxo - Estruturas Condicionais

## 📋 Visão Geral

As estruturas condicionais são fundamentais na programação, permitindo que o programa tome decisões e execute diferentes blocos de código baseado em condições específicas. Elas transformam programas lineares em programas inteligentes que podem responder a diferentes situações.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Criar programas que tomam decisões usando `if`, `else if` e `else`
- ✅ Combinar múltiplas condições com operadores lógicos
- ✅ Usar operadores de comparação efetivamente
- ✅ Implementar estruturas condicionais aninhadas
- ✅ Aplicar boas práticas na escrita de condicionais
- ✅ Resolver problemas do mundo real usando lógica condicional

## 🧠 Conceitos Fundamentais

### 1. Estrutura if Básica

```java
if (condicao) {
    // Código executado SE a condição for verdadeira
}
```

**Exemplo prático:**
```java
int idade = 18;
if (idade >= 18) {
    System.out.println("Você é maior de idade!");
}
```

### 2. Estrutura if-else

```java
if (condicao) {
    // Código executado se verdadeiro
} else {
    // Código executado se falso
}
```

**Exemplo prático:**
```java
double nota = 7.5;
if (nota >= 7.0) {
    System.out.println("Aprovado!");
} else {
    System.out.println("Reprovado!");
}
```

### 3. Estrutura if-else if-else

```java
if (condicao1) {
    // Código para condição 1
} else if (condicao2) {
    // Código para condição 2  
} else if (condicao3) {
    // Código para condição 3
} else {
    // Código se nenhuma condição for verdadeira
}
```

**Exemplo prático:**
```java
int temperatura = 25;
if (temperatura > 30) {
    System.out.println("Está muito quente!");
} else if (temperatura > 20) {
    System.out.println("Temperatura agradável");
} else if (temperatura > 10) {
    System.out.println("Está fresco");
} else {
    System.out.println("Está frio!");
}
```

## 🔍 Operadores de Comparação

### Operadores Relacionais
```java
int a = 10, b = 20;

a == b    // false - igual a
a != b    // true  - diferente de
a < b     // true  - menor que
a <= b    // true  - menor ou igual a
a > b     // false - maior que
a >= b    // false - maior ou igual a
```

### Comparação de Strings
```java
String nome1 = "João";
String nome2 = "João";

// ❌ ERRADO - compara referências
if (nome1 == nome2) { ... }

// ✅ CORRETO - compara conteúdo
if (nome1.equals(nome2)) { ... }

// ✅ CORRETO - ignora maiúsculas/minúsculas
if (nome1.equalsIgnoreCase("JOÃO")) { ... }
```

## 🔗 Operadores Lógicos

### AND Lógico (&&)
```java
boolean temIdade = idade >= 18;
boolean temDocumento = true;

if (temIdade && temDocumento) {
    System.out.println("Pode entrar!");
}
// Executa APENAS se AMBAS as condições forem verdadeiras
```

### OR Lógico (||)
```java
boolean ehVip = true;
boolean ehFuncionario = false;

if (ehVip || ehFuncionario) {
    System.out.println("Acesso liberado!");
}
// Executa se PELO MENOS UMA condição for verdadeira
```

### NOT Lógico (!)
```java
boolean estaLogado = false;

if (!estaLogado) {
    System.out.println("Faça login primeiro!");
}
// Inverte o valor: true vira false, false vira true
```

### Combinações Complexas
```java
if ((idade >= 18 && temDocumento) || (idade >= 16 && temAutorizacao)) {
    System.out.println("Pode participar!");
}
```

## 🏗️ Estruturas Condicionais Aninhadas

### Condicionais Dentro de Condicionais
```java
if (temDinheiro) {
    if (temTempo) {
        if (temVontade) {
            System.out.println("Vai viajar!");
        } else {
            System.out.println("Não tem vontade de viajar");
        }
    } else {
        System.out.println("Não tem tempo");
    }
} else {
    System.out.println("Não tem dinheiro");
}
```

### Alternativa Mais Limpa
```java
if (temDinheiro && temTempo && temVontade) {
    System.out.println("Vai viajar!");
} else if (!temDinheiro) {
    System.out.println("Precisa juntar dinheiro");
} else if (!temTempo) {
    System.out.println("Precisa organizar o tempo");  
} else {
    System.out.println("Precisa de motivação!");
}
```

## 🎯 Operador Ternário

### Sintaxe
```java
tipo variavel = condicao ? valorSeVerdadeiro : valorSeFalso;
```

### Exemplos Práticos
```java
// Exemplo simples
String status = idade >= 18 ? "Adulto" : "Menor";

// Aninhado (use com moderação)
String categoria = nota >= 9 ? "Excelente" : 
                   nota >= 7 ? "Bom" : 
                   nota >= 5 ? "Regular" : "Insuficiente";

// Em métodos
int maximo = (a > b) ? a : b;
```

## 💡 Padrões Comuns e Casos de Uso

### 1. Validação de Entrada
```java
Scanner sc = new Scanner(System.in);
System.out.print("Digite sua idade: ");
int idade = sc.nextInt();

if (idade < 0 || idade > 150) {
    System.out.println("Idade inválida!");
} else if (idade < 18) {
    System.out.println("Menor de idade");
} else {
    System.out.println("Maior de idade");
}
```

### 2. Sistema de Notas
```java
public String calcularConceito(double nota) {
    if (nota >= 9.0) {
        return "A - Excelente";
    } else if (nota >= 8.0) {
        return "B - Muito Bom";
    } else if (nota >= 7.0) {
        return "C - Bom";
    } else if (nota >= 6.0) {
        return "D - Regular";
    } else {
        return "F - Insuficiente";
    }
}
```

### 3. Calculadora de Desconto
```java
double preco = 100.0;
int quantidade = 5;
double desconto = 0.0;

if (quantidade >= 10) {
    desconto = 0.15;  // 15% para 10+ itens
} else if (quantidade >= 5) {
    desconto = 0.10;  // 10% para 5-9 itens
} else if (quantidade >= 3) {
    desconto = 0.05;  // 5% para 3-4 itens
}

double precoFinal = preco * quantidade * (1 - desconto);
```

### 4. Verificação de Ano Bissexto
```java
public boolean ehBissexto(int ano) {
    if (ano % 400 == 0) {
        return true;
    } else if (ano % 100 == 0) {
        return false;
    } else if (ano % 4 == 0) {
        return true;
    } else {
        return false;
    }
}

// Versão mais concisa
public boolean ehBissextoCompacto(int ano) {
    return (ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0);
}
```

## ⚠️ Armadilhas Comuns

### 1. Atribuição vs Comparação
```java
int x = 5;

// ❌ ERRADO - atribuição
if (x = 10) { ... }  // Erro de compilação em Java

// ✅ CORRETO - comparação
if (x == 10) { ... }
```

### 2. Comparação de Ponto Flutuante
```java
double a = 0.1 + 0.2;
double b = 0.3;

// ❌ PROBLEMÁTICO - imprecisão
if (a == b) { ... }

// ✅ CORRETO - tolerância
if (Math.abs(a - b) < 0.0001) { ... }
```

### 3. NullPointerException
```java
String nome = null;

// ❌ PERIGOSO - pode gerar exceção
if (nome.equals("João")) { ... }

// ✅ SEGURO
if (nome != null && nome.equals("João")) { ... }

// ✅ AINDA MELHOR
if ("João".equals(nome)) { ... }
```

### 4. Condições Desnecessárias
```java
// ❌ VERBOSO
if (condicao == true) { ... }
if (condicao == false) { ... }

// ✅ LIMPO
if (condicao) { ... }
if (!condicao) { ... }
```

## 🛠️ Boas Práticas

### 1. Use Parênteses para Clareza
```java
// Pode ser confuso
if (a > b && c < d || e == f) { ... }

// Mais claro
if ((a > b && c < d) || (e == f)) { ... }
```

### 2. Evite Negações Múltiplas
```java
// ❌ Confuso
if (!(!ativo && !aprovado)) { ... }

// ✅ Claro
if (ativo || aprovado) { ... }
```

### 3. Use Constantes para Valores Mágicos
```java
// ❌ Números mágicos
if (idade >= 18 && idade < 65) { ... }

// ✅ Constantes descritivas
final int IDADE_MINIMA = 18;
final int IDADE_APOSENTADORIA = 65;
if (idade >= IDADE_MINIMA && idade < IDADE_APOSENTADORIA) { ... }
```

### 4. Prefira Return Antecipado
```java
// ❌ Aninhamento excessivo
public String validarUsuario(String nome, int idade) {
    if (nome != null) {
        if (nome.length() > 0) {
            if (idade >= 0) {
                return "Usuário válido";
            } else {
                return "Idade inválida";
            }
        } else {
            return "Nome vazio";
        }
    } else {
        return "Nome nulo";
    }
}

// ✅ Return antecipado
public String validarUsuarioMelhor(String nome, int idade) {
    if (nome == null) {
        return "Nome nulo";
    }
    if (nome.length() == 0) {
        return "Nome vazio";
    }
    if (idade < 0) {
        return "Idade inválida";
    }
    return "Usuário válido";
}
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. **Verificador de Idade**: Determine se uma pessoa pode votar, dirigir ou se aposentar
2. **Calculadora de IMC**: Classifique o IMC em categorias (baixo peso, normal, sobrepeso, obesidade)
3. **Sistema de Login**: Verifique se usuário e senha estão corretos

### Nível Intermediário
1. **Calculadora de Imposto de Renda**: Implemente as faixas de tributação
2. **Jogo de Pedra, Papel, Tesoura**: Determine o vencedor
3. **Validador de Triangulos**: Verifique se três lados formam um triângulo válido

### Nível Avançado
1. **Sistema de Desconto Progressivo**: Múltiplos critérios (quantidade, valor, cliente VIP)
2. **Analisador de Data**: Valide datas considerando anos bissextos
3. **Sistema de Permissões**: Múltiplos níveis de acesso com diferentes regras

## 📖 Próximos Passos

Após dominar estruturas condicionais, você estará pronto para:
- [Estruturas de Repetição](../controle_fluxo_repeticao/) - Loops e iterações
- [Switch Case](../switch_case/) - Alternativa para múltiplas condições
- [Métodos](../arrays_e_metodos/) - Organizar lógica em funções

## 📚 Recursos Adicionais

- [Oracle Java Control Flow](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Clean Code: Meaningful Names](https://blog.cleancoder.com/uncle-bob/2017/05/03/TestDefinitions.html)
- [Effective Java - Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/)

---

**💡 Dica**: Boas estruturas condicionais tornam o código mais legível e fácil de manter. Sempre pense na pessoa que vai ler seu código depois!