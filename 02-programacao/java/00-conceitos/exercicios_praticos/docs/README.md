# Exercícios Práticos - Java Conceitos Fundamentais

## 📋 Visão Geral

Esta seção contém exercícios práticos que consolidam todos os conceitos fundamentais de Java estudados: tipos de dados, controle de fluxo, arrays, métodos, manipulação de strings e tratamento de exceções. Os exercícios estão organizados por níveis de dificuldade e abordam problemas do mundo real.

## 🎯 Objetivos dos Exercícios

Ao completar estes exercícios, você será capaz de:

- ✅ Aplicar conceitos teóricos em problemas práticos
- ✅ Integrar diferentes conceitos em uma única solução
- ✅ Desenvolver habilidades de resolução de problemas
- ✅ Praticar debugging e teste de código
- ✅ Criar programas mais robustos e bem estruturados

## 📚 Estrutura dos Exercícios

Os exercícios estão organizados em categorias que combinam múltiplos conceitos:

### 🟢 Nível Iniciante
- Foco em conceitos individuais
- Problemas diretos com soluções claras
- Ênfase na sintaxe e lógica básica

### 🟡 Nível Intermediário  
- Combinação de 2-3 conceitos
- Problemas que requerem planejamento
- Introdução a algoritmos básicos

### 🔴 Nível Avançado
- Integração de todos os conceitos
- Problemas complexos do mundo real
- Foco em eficiência e robustez

## 🟢 Exercícios Nível Iniciante

### 1. Calculadora de Média Escolar

**Conceitos**: Tipos de dados, entrada de dados, operações matemáticas

```java
/**
 * Crie um programa que:
 * 1. Peça o nome do aluno
 * 2. Peça 4 notas (0-10)
 * 3. Calcule a média
 * 4. Determine se foi aprovado (>=7), recuperação (5-6.9) ou reprovado (<5)
 * 5. Exiba um relatório formatado
 */

// Exemplo de saída esperada:
// ===== BOLETIM ESCOLAR =====
// Aluno: João Silva
// Nota 1: 8.5
// Nota 2: 7.0
// Nota 3: 9.2
// Nota 4: 6.8
// Média: 7.88
// Situação: APROVADO
```

### 2. Contador de Caracteres

**Conceitos**: Strings, loops, contadores

```java
/**
 * Desenvolva um programa que:
 * 1. Receba uma frase do usuário
 * 2. Conte e exiba:
 *    - Número total de caracteres
 *    - Número de letras
 *    - Número de dígitos
 *    - Número de espaços
 *    - Número de caracteres especiais
 */

// Exemplo:
// Entrada: "Olá! Tenho 25 anos."
// Saída:
// Total de caracteres: 18
// Letras: 11
// Dígitos: 2
// Espaços: 3
// Caracteres especiais: 2
```

### 3. Tabuada Personalizada

**Conceitos**: Loops, formatação, métodos

```java
/**
 * Crie um programa que:
 * 1. Peça um número ao usuário
 * 2. Gere a tabuada desse número (1 a 10)
 * 3. Formate a saída de forma organizada
 * 4. Pergunte se quer ver outra tabuada
 */

// Exemplo de saída:
// ===== TABUADA DO 7 =====
// 7 x  1 =  7
// 7 x  2 = 14
// ...
// 7 x 10 = 70
```

## 🟡 Exercícios Nível Intermediário

### 4. Sistema de Cadastro de Alunos

**Conceitos**: Arrays, métodos, validação, menu

```java
/**
 * Desenvolva um sistema que permita:
 * 1. Cadastrar até 50 alunos (nome, idade, curso)
 * 2. Listar todos os alunos
 * 3. Buscar aluno por nome
 * 4. Calcular idade média dos alunos
 * 5. Listar alunos por curso
 * 6. Menu interativo para navegar entre opções
 */

// Estrutura sugerida:
public class SistemaAlunos {
    private static String[] nomes = new String[50];
    private static int[] idades = new int[50];
    private static String[] cursos = new String[50];
    private static int totalAlunos = 0;
    
    public static void main(String[] args) {
        // Implementar menu
    }
    
    public static void cadastrarAluno() { ... }
    public static void listarAlunos() { ... }
    public static int buscarAluno(String nome) { ... }
    public static double calcularIdadeMedia() { ... }
}
```

### 5. Jogo de Adivinhação Inteligente

**Conceitos**: Random, loops, condicionais, arrays

```java
/**
 * Crie um jogo onde:
 * 1. O computador escolhe um número aleatório (1-100)
 * 2. O jogador tem 10 tentativas para acertar
 * 3. A cada tentativa, dê dicas (maior/menor)
 * 4. Mostre histórico de tentativas
 * 5. Calcule pontuação baseada no número de tentativas
 * 6. Pergunte se quer jogar novamente
 */

// Funcionalidades extras:
// - Diferentes níveis de dificuldade
// - Ranking de melhores pontuações
// - Estatísticas do jogador
```

### 6. Analisador de Texto

**Conceitos**: Strings, arrays, métodos, estatísticas

```java
/**
 * Programa que analisa um texto e retorna:
 * 1. Número de palavras
 * 2. Número de frases (considere ., !, ?)
 * 3. Palavra mais longa e mais curta
 * 4. Frequência de cada letra
 * 5. Top 5 palavras mais usadas
 * 6. Readability score simples
 */

public class AnalisadorTexto {
    public static void analisarTexto(String texto) {
        // Implementar análises
    }
    
    public static int contarPalavras(String texto) { ... }
    public static String[] extrairPalavras(String texto) { ... }
    public static String palavraMaisLonga(String[] palavras) { ... }
    public static int[] contarLetras(String texto) { ... }
}
```

## 🔴 Exercícios Nível Avançado

### 7. Sistema de Biblioteca

**Conceitos**: Arrays multidimensionais, métodos complexos, validação, persistência

```java
/**
 * Sistema completo de biblioteca com:
 * 
 * LIVROS:
 * - Código, título, autor, ano, disponível
 * 
 * USUÁRIOS:
 * - ID, nome, email, telefone
 * 
 * EMPRÉSTIMOS:
 * - ID usuário, código livro, data empréstimo, data devolução
 * 
 * FUNCIONALIDADES:
 * 1. Cadastrar/listar/buscar livros e usuários
 * 2. Realizar empréstimos e devoluções
 * 3. Consultar disponibilidade
 * 4. Relatórios (livros mais emprestados, usuários ativos)
 * 5. Sistema de multas por atraso
 * 6. Backup dos dados em arquivo texto
 */

public class SistemaBiblioteca {
    // Arrays para dados
    private static String[][] livros = new String[100][5]; // codigo, titulo, autor, ano, disponivel
    private static String[][] usuarios = new String[50][4]; // id, nome, email, telefone
    private static String[][] emprestimos = new String[200][4]; // idUsuario, codigoLivro, dataEmp, dataDev
    
    // Contadores
    private static int totalLivros = 0;
    private static int totalUsuarios = 0;
    private static int totalEmprestimos = 0;
    
    public static void main(String[] args) {
        carregarDados();
        menuPrincipal();
        salvarDados();
    }
}
```

### 8. Calculadora Científica

**Conceitos**: Métodos, validação, exceções, formatação

```java
/**
 * Calculadora com operações:
 * 
 * BÁSICAS: +, -, *, /, %
 * POTÊNCIA: x^y, raiz quadrada, raiz n
 * TRIGONOMÉTRICAS: sin, cos, tan (graus e radianos)
 * LOGARÍTMICAS: log, ln
 * FATORIAL: n!
 * CONVERSÕES: bin, oct, hex
 * ESTATÍSTICA: média, mediana, desvio padrão
 * 
 * RECURSOS:
 * - Histórico de operações
 * - Validação de entrada robusta
 * - Formatação de saída configurável
 * - Modo científico/engenharia
 */

public class CalculadoraCientifica {
    private static String[] historico = new String[100];
    private static int contadorHistorico = 0;
    private static boolean modoRadianos = false;
    
    // Operações básicas
    public static double somar(double a, double b) { ... }
    public static double subtrair(double a, double b) { ... }
    
    // Operações avançadas
    public static double potencia(double base, double expoente) { ... }
    public static double raizQuadrada(double numero) { ... }
    public static double seno(double angulo) { ... }
    
    // Utilitários
    public static void adicionarHistorico(String operacao) { ... }
    public static void exibirHistorico() { ... }
    public static String formatarNumero(double numero) { ... }
}
```

### 9. Jogo da Forca Completo

**Conceitos**: Arrays, strings, random, arquivos, validação

```java
/**
 * Jogo da forca com:
 * 
 * RECURSOS:
 * - Banco de palavras por categoria
 * - Diferentes níveis de dificuldade
 * - Sistema de dicas
 * - Histórico de partidas
 * - Ranking de jogadores
 * - Interface ASCII art
 * 
 * CATEGORIAS:
 * - Animais, países, profissões, tecnologia
 * 
 * DIFICULDADES:
 * - Fácil: 8 tentativas, palavras até 6 letras
 * - Médio: 6 tentativas, palavras até 10 letras  
 * - Difícil: 4 tentativas, qualquer palavra
 */

public class JogoForcaCompleto {
    private static String[][] bancoPalavras = {
        {"GATO", "CACHORRO", "ELEFANTE"}, // Animais
        {"BRASIL", "FRANÇA", "JAPÃO"},    // Países
        {"MÉDICO", "PROFESSOR", "PROGRAMADOR"} // Profissões
    };
    
    private static String[] categorias = {"Animais", "Países", "Profissões"};
    private static String[] desenhoForca = new String[8];
    
    public static void main(String[] args) {
        inicializarJogo();
        menuPrincipal();
    }
    
    public static boolean jogarPartida(String palavra, int tentativas) { ... }
    public static void exibirEstado(char[] letrasAcertadas, int erros) { ... }
    public static String obterDica(String palavra, char[] acertadas) { ... }
}
```

### 10. Sistema de Vendas Simples

**Conceitos**: Integração de todos os conceitos

```java
/**
 * Sistema de vendas com:
 * 
 * PRODUTOS:
 * - Código, nome, preço, estoque
 * 
 * VENDAS:
 * - Número, data, cliente, itens, total
 * 
 * FUNCIONALIDADES:
 * 1. Gerenciar produtos (CRUD)
 * 2. Realizar vendas com múltiplos itens
 * 3. Controlar estoque automaticamente
 * 4. Aplicar descontos e promoções
 * 5. Gerar relatórios de vendas
 * 6. Backup e restauração de dados
 * 
 * RELATÓRIOS:
 * - Vendas por período
 * - Produtos mais vendidos
 * - Estoque baixo
 * - Faturamento total
 */
```

## 🛠️ Dicas para Resolução

### Estratégia de Desenvolvimento

1. **Entenda o problema**: Leia cuidadosamente e liste os requisitos
2. **Planeje a solução**: Desenhe o fluxo antes de codificar
3. **Divida em etapas**: Resolva uma funcionalidade por vez
4. **Teste frequentemente**: Teste cada parte antes de continuar
5. **Refine o código**: Melhore nomes, organize métodos, adicione comentários

### Checklist de Qualidade

- [ ] O código compila sem erros
- [ ] Todos os casos de teste passam
- [ ] Entrada inválida é tratada adequadamente
- [ ] Variáveis têm nomes descritivos
- [ ] Métodos têm responsabilidades claras
- [ ] Código está bem comentado
- [ ] Não há duplicação desnecessária

### Padrões de Validação

```java
// Validação de entrada numérica
public static int lerInteiroValido(Scanner sc, int min, int max) {
    int numero;
    do {
        System.out.printf("Digite um número entre %d e %d: ", min, max);
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, digite apenas números!");
            sc.next();
        }
        numero = sc.nextInt();
        if (numero < min || numero > max) {
            System.out.println("Número fora da faixa permitida!");
        }
    } while (numero < min || numero > max);
    return numero;
}

// Validação de string não vazia
public static String lerStringNaoVazia(Scanner sc, String mensagem) {
    String texto;
    do {
        System.out.print(mensagem);
        texto = sc.nextLine().trim();
        if (texto.isEmpty()) {
            System.out.println("Campo obrigatório! Digite algo.");
        }
    } while (texto.isEmpty());
    return texto;
}
```

## 📊 Sistema de Avaliação

### Critérios de Avaliação

- **Funcionalidade** (40%): O programa funciona conforme especificado
- **Qualidade do Código** (30%): Organização, legibilidade, boas práticas
- **Tratamento de Erros** (20%): Validação e robustez
- **Criatividade** (10%): Melhorias e recursos extras

### Níveis de Competência

- **Iniciante**: Resolve problemas básicos com orientação
- **Intermediário**: Combina conceitos e resolve problemas independentemente
- **Avançado**: Cria soluções elegantes e eficientes para problemas complexos

## 🎯 Projetos Desafio

Para quem quer ir além:

1. **Conversor de Unidades Universal**
2. **Simulador de Caixa Eletrônico**
3. **Jogo de Blackjack**
4. **Sistema de Agenda Pessoal**
5. **Analisador de Logs de Servidor**

## 📚 Recursos Adicionais

- Use o debugger da sua IDE para entender o fluxo
- Pratique leitura de código alheio
- Participe de comunidades de programação
- Resolva problemas em plataformas como HackerRank, LeetCode

---

**💡 Dica Final**: A programação se aprende praticando! Não tenha medo de errar, cada erro é uma oportunidade de aprender algo novo. Comece pelos exercícios mais simples e evolua gradualmente.