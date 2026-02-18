# Controle de Fluxo - Estruturas de Repetição (Loops)

## 📋 Visão Geral

As estruturas de repetição (loops) são essenciais na programação, permitindo que um bloco de código seja executado múltiplas vezes de forma controlada. Elas eliminam a necessidade de repetir código manualmente e possibilitam o processamento de grandes quantidades de dados de forma eficiente.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Implementar loops `for`, `while` e `do-while` efetivamente
- ✅ Escolher o tipo de loop mais adequado para cada situação
- ✅ Usar `break` e `continue` para controlar o fluxo dos loops
- ✅ Trabalhar com loops aninhados
- ✅ Evitar loops infinitos e otimizar performance
- ✅ Aplicar padrões comuns de iteração

## 🔄 Tipos de Loops em Java

### 1. Loop for - Iterações Controladas

O loop `for` é ideal quando você sabe exatamente quantas vezes quer repetir algo.

#### Sintaxe Básica
```java
for (inicialização; condição; incremento) {
    // Código a ser repetido
}
```

#### Exemplos Práticos
```java
// Contagem simples
for (int i = 1; i <= 5; i++) {
    System.out.println("Contagem: " + i);
}

// Contagem regressiva
for (int i = 10; i >= 1; i--) {
    System.out.println("Countdown: " + i);
}

// Incremento personalizado
for (int i = 0; i <= 20; i += 2) {
    System.out.println("Número par: " + i);
}

// Múltiplas variáveis
for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println("i=" + i + ", j=" + j);
}
```

### 2. Loop for-each (Enhanced for) - Iteração em Coleções

Ideal para percorrer arrays e coleções sem se preocupar com índices.

#### Sintaxe
```java
for (tipo elemento : coleção) {
    // Uso do elemento
}
```

#### Exemplos
```java
// Array de inteiros
int[] numeros = {1, 2, 3, 4, 5};
for (int numero : numeros) {
    System.out.println("Número: " + numero);
}

// Array de strings
String[] nomes = {"Ana", "João", "Pedro"};
for (String nome : nomes) {
    System.out.println("Nome: " + nome);
}

// Calculando soma
int[] valores = {10, 20, 30, 40};
int soma = 0;
for (int valor : valores) {
    soma += valor;
}
System.out.println("Soma total: " + soma);
```

### 3. Loop while - Condição no Início

Executa enquanto uma condição for verdadeira. Verifica a condição ANTES de executar.

#### Sintaxe
```java
while (condição) {
    // Código a ser repetido
}
```

#### Exemplos Práticos
```java
// Contagem com while
int contador = 1;
while (contador <= 5) {
    System.out.println("Contador: " + contador);
    contador++;  // IMPORTANTE: não esqueça de atualizar!
}

// Validação de entrada
Scanner sc = new Scanner(System.in);
int numero = -1;
while (numero < 0 || numero > 100) {
    System.out.print("Digite um número entre 0 e 100: ");
    numero = sc.nextInt();
    if (numero < 0 || numero > 100) {
        System.out.println("Número inválido! Tente novamente.");
    }
}

// Processamento até condição especial
String resposta = "";
while (!resposta.equalsIgnoreCase("sair")) {
    System.out.print("Digite um comando (ou 'sair'): ");
    resposta = sc.nextLine();
    if (!resposta.equalsIgnoreCase("sair")) {
        System.out.println("Você digitou: " + resposta);
    }
}
```

### 4. Loop do-while - Condição no Final

Executa pelo menos uma vez, verificando a condição DEPOIS da execução.

#### Sintaxe
```java
do {
    // Código a ser repetido
} while (condição);
```

#### Exemplos Práticos
```java
// Menu que executa pelo menos uma vez
Scanner sc = new Scanner(System.in);
int opcao;
do {
    System.out.println("\n=== MENU ===");
    System.out.println("1. Opção 1");
    System.out.println("2. Opção 2");
    System.out.println("0. Sair");
    System.out.print("Escolha uma opção: ");
    opcao = sc.nextInt();
    
    switch (opcao) {
        case 1:
            System.out.println("Você escolheu a opção 1");
            break;
        case 2:
            System.out.println("Você escolheu a opção 2");
            break;
        case 0:
            System.out.println("Saindo...");
            break;
        default:
            System.out.println("Opção inválida!");
    }
} while (opcao != 0);

// Jogo de adivinhação
Random random = new Random();
int numeroSecreto = random.nextInt(100) + 1;
int tentativa;
int numeroTentativas = 0;

do {
    System.out.print("Adivinhe o número (1-100): ");
    tentativa = sc.nextInt();
    numeroTentativas++;
    
    if (tentativa < numeroSecreto) {
        System.out.println("Muito baixo!");
    } else if (tentativa > numeroSecreto) {
        System.out.println("Muito alto!");
    } else {
        System.out.println("Parabéns! Acertou em " + numeroTentativas + " tentativas!");
    }
} while (tentativa != numeroSecreto);
```

## 🎮 Controle de Fluxo em Loops

### break - Interrompe o Loop
```java
// Procurando um elemento específico
int[] numeros = {1, 5, 3, 9, 2, 7};
int procurado = 9;
boolean encontrado = false;

for (int numero : numeros) {
    if (numero == procurado) {
        encontrado = true;
        System.out.println("Número " + procurado + " encontrado!");
        break; // Sai do loop imediatamente
    }
}

// Limitando tentativas
int tentativas = 0;
while (true) { // Loop "infinito"
    tentativas++;
    System.out.println("Tentativa " + tentativas);
    
    if (tentativas >= 5) {
        System.out.println("Limite de tentativas atingido!");
        break; // Sai do loop
    }
}
```

### continue - Pula para Próxima Iteração
```java
// Pulando números pares
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue; // Pula o resto do código e vai para próxima iteração
    }
    System.out.println("Número ímpar: " + i);
}

// Processando apenas valores válidos
int[] valores = {10, -5, 20, -3, 15, 0, 25};
for (int valor : valores) {
    if (valor <= 0) {
        continue; // Pula valores negativos e zero
    }
    System.out.println("Processando valor positivo: " + valor);
    // Mais processamento aqui...
}
```

## 🔗 Loops Aninhados

### Exemplo: Tabuada Completa
```java
System.out.println("=== TABUADA COMPLETA ===");
for (int i = 1; i <= 10; i++) {
    System.out.println("\nTabuada do " + i + ":");
    for (int j = 1; j <= 10; j++) {
        System.out.println(i + " x " + j + " = " + (i * j));
    }
}
```

### Exemplo: Padrões com Asteriscos
```java
// Triângulo
System.out.println("Triângulo:");
for (int linha = 1; linha <= 5; linha++) {
    for (int coluna = 1; coluna <= linha; coluna++) {
        System.out.print("* ");
    }
    System.out.println();
}

// Retângulo
System.out.println("\nRetângulo:");
for (int linha = 1; linha <= 4; linha++) {
    for (int coluna = 1; coluna <= 6; coluna++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

### Controlando Loops Aninhados
```java
// break em loop aninhado (sai apenas do loop interno)
outerLoop: for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        if (i == 2 && j == 2) {
            break outerLoop; // Sai de ambos os loops
        }
        System.out.println("i=" + i + ", j=" + j);
    }
}
```

## 💡 Padrões Comuns e Casos de Uso

### 1. Acumulação (Soma, Produto, Contagem)
```java
// Somando números
int[] numeros = {10, 20, 30, 40, 50};
int soma = 0;
for (int numero : numeros) {
    soma += numero;
}
System.out.println("Soma: " + soma);

// Contando elementos que atendem condição
int contador = 0;
for (int numero : numeros) {
    if (numero > 25) {
        contador++;
    }
}
System.out.println("Números maiores que 25: " + contador);

// Produto
int produto = 1;
for (int i = 1; i <= 5; i++) {
    produto *= i;
}
System.out.println("5! = " + produto);
```

### 2. Busca e Filtragem
```java
// Encontrando máximo
int[] valores = {15, 23, 8, 42, 16};
int maximo = valores[0];
for (int valor : valores) {
    if (valor > maximo) {
        maximo = valor;
    }
}
System.out.println("Maior valor: " + maximo);

// Filtrando elementos
System.out.println("Números pares:");
for (int valor : valores) {
    if (valor % 2 == 0) {
        System.out.println(valor);
    }
}
```

### 3. Validação e Entrada de Dados
```java
Scanner sc = new Scanner(System.in);

// Validando entrada numérica
double nota;
do {
    System.out.print("Digite uma nota (0-10): ");
    nota = sc.nextDouble();
    if (nota < 0 || nota > 10) {
        System.out.println("Nota inválida! Deve estar entre 0 e 10.");
    }
} while (nota < 0 || nota > 10);

// Coletando múltiplas entradas
List<String> nomes = new ArrayList<>();
String nome;
do {
    System.out.print("Digite um nome (ou 'fim' para terminar): ");
    nome = sc.nextLine();
    if (!nome.equalsIgnoreCase("fim")) {
        nomes.add(nome);
    }
} while (!nome.equalsIgnoreCase("fim"));
```

## ⚠️ Armadilhas Comuns

### 1. Loop Infinito
```java
// ❌ PERIGOSO - loop infinito
int i = 0;
while (i < 10) {
    System.out.println(i);
    // Esqueceu de incrementar i!
}

// ✅ CORRETO
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++; // Incrementa i
}
```

### 2. Condição de Parada Incorreta
```java
// ❌ PROBLEMÁTICO
for (int i = 0; i <= array.length; i++) { // <= pode causar IndexOutOfBounds
    System.out.println(array[i]);
}

// ✅ CORRETO
for (int i = 0; i < array.length; i++) { // < é o correto
    System.out.println(array[i]);
}
```

### 3. Modificação de Coleção Durante Iteração
```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

// ❌ PROBLEMÁTICO - ConcurrentModificationException
for (Integer numero : numeros) {
    if (numero % 2 == 0) {
        numeros.remove(numero); // Não faça isso!
    }
}

// ✅ CORRETO - usando Iterator
Iterator<Integer> iterator = numeros.iterator();
while (iterator.hasNext()) {
    Integer numero = iterator.next();
    if (numero % 2 == 0) {
        iterator.remove();
    }
}
```

## 🚀 Otimização e Performance

### 1. Evite Cálculos Desnecessários
```java
// ❌ INEFICIENTE
for (int i = 0; i < array.length; i++) {
    for (int j = 0; j < array.length; j++) { // Recalcula array.length a cada iteração
        // processamento
    }
}

// ✅ EFICIENTE
int tamanho = array.length;
for (int i = 0; i < tamanho; i++) {
    for (int j = 0; j < tamanho; j++) {
        // processamento
    }
}
```

### 2. Use Enhanced for Quando Possível
```java
// ✅ Mais limpo e menos propenso a erros
for (String elemento : lista) {
    System.out.println(elemento);
}

// Em vez de:
for (int i = 0; i < lista.size(); i++) {
    System.out.println(lista.get(i));
}
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. **Tabuada Personalizada**: Peça um número e exiba sua tabuada
2. **Contador de Dígitos**: Conte quantos dígitos tem um número
3. **Soma dos Primeiros N Números**: Calcule 1+2+3+...+n

### Nível Intermediário
1. **Números Primos**: Encontre todos os números primos até N
2. **Sequência de Fibonacci**: Gere os primeiros N termos
3. **Jogo da Adivinhação**: Implemente com dicas e limite de tentativas

### Nível Avançado
1. **Padrões Gráficos**: Crie diferentes padrões com asteriscos
2. **Calculadora Estatística**: Calcule média, mediana, moda de uma lista
3. **Sistema de Menu Complexo**: Menu com submenus e validações

## 🛠️ Boas Práticas

1. **Escolha o loop certo**:
   - `for`: quando sabe o número de iterações
   - `for-each`: para percorrer coleções
   - `while`: quando a condição pode mudar
   - `do-while`: quando precisa executar pelo menos uma vez

2. **Nomeação clara**:
   ```java
   // ❌ Nomes genéricos
   for (int i = 0; i < n; i++) { ... }
   
   // ✅ Nomes descritivos
   for (int indiceAluno = 0; indiceAluno < totalAlunos; indiceAluno++) { ... }
   ```

3. **Evite loops aninhados complexos**:
   - Considere extrair para métodos separados
   - Use nomes descritivos para variáveis de controle

4. **Sempre verifique condições de parada**:
   - Garanta que a condição será eventualmente falsa
   - Cuidado com loops baseados em entrada do usuário

## 📖 Próximos Passos

Após dominar estruturas de repetição, você estará pronto para:
- [Arrays e Métodos](../arrays_e_metodos/) - Organização de dados e código
- [Tratamento de Exceções](../tratamento_excecoes/) - Lidando com erros
- [Manipulação de Strings](../manipulacao_strings/) - Processamento de texto

## 📚 Recursos Adicionais

- [Oracle Java Loops Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html)
- [Effective Java - Item 58: Prefer for-each loops](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Big O Notation](https://www.bigocheatsheet.com/) - Para entender complexidade de loops

---

**💡 Dica**: Loops são poderosos, mas use com responsabilidade. Sempre teste com dados pequenos antes de processar grandes volumes!