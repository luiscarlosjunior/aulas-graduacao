# Exemplo 1: Framework Básico de Programação Genética

## Descrição

Este exemplo implementa um framework completo e funcional de Programação Genética em C#, demonstrando os componentes fundamentais necessários para construir um sistema de GP.

## Estrutura do Projeto

```
BasicGPFramework/
├── Node.cs                  # Classes para nós da árvore de expressão
├── Individual.cs            # Representação de indivíduos e população
├── TreeGenerator.cs         # Geração de árvores iniciais
├── GeneticOperators.cs      # Operadores genéticos (crossover, mutação, seleção)
├── GPEngine.cs             # Motor principal de evolução
└── Program.cs              # Exemplo de uso: Regressão Simbólica
```

## Componentes Implementados

### 1. Representação em Árvore (Node.cs)

- **FunctionNode**: Nós internos representando operadores (+, -, *, /, sin, cos, etc.)
- **TerminalNode**: Nós folha representando variáveis ou constantes

### 2. Indivíduos e População (Individual.cs)

- **Individual**: Encapsula uma árvore de expressão com fitness
- **Population**: Gerencia conjunto de indivíduos com estatísticas

### 3. Geração de Árvores (TreeGenerator.cs)

Implementa métodos de inicialização:
- **Full Method**: Árvores de profundidade máxima
- **Grow Method**: Árvores de profundidade variável
- **Ramped Half-and-Half**: Combinação dos anteriores

### 4. Operadores Genéticos (GeneticOperators.cs)

- **Crossover**: Troca de subárvores entre pais
- **Mutação**: Substituição de subárvores aleatórias
- **Seleção por Torneio**: Competição entre k indivíduos
- **Seleção por Roleta**: Probabilidade proporcional à fitness

### 5. Motor de GP (GPEngine.cs)

- Loop evolutivo completo
- Elitismo
- Avaliação de fitness
- Logging e estatísticas

## Problema Demonstrado: Regressão Simbólica

O exemplo resolve o problema de **descobrir automaticamente** a função matemática:

```
f(x) = x² + 2x + 1
```

Apenas fornecendo pontos de dados (x, y), sem conhecimento prévio da fórmula.

## Como Executar

```bash
cd BasicGPFramework
dotnet run
```

## Exemplo de Saída

```
╔════════════════════════════════════════════════════════════╗
║   Framework Básico de Programação Genética em C#          ║
║   Exemplo: Regressão Simbólica                             ║
╚════════════════════════════════════════════════════════════╝

Objetivo: Descobrir a função f(x) = x² + 2x + 1

=== Iniciando Programação Genética ===

Tamanho da População: 200
Gerações Máximas: 100
...

*** Nova melhor solução encontrada! ***
    Fitness: -0.0419 | Expressão: (((x * x) - (x + x)) - ((x + (x * -5.00)) - (x / x)))

=== Evolução Concluída ===

Melhor solução encontrada:
  Expressão: (((x * x) - (x + x)) - ((x + (x * -5.00)) - (x / x)))
  
Erro Absoluto Médio (MAE): 0.0251
```

## Parâmetros Configuráveis

No arquivo `Program.cs`, você pode ajustar:

```csharp
var config = new GPConfig
{
    PopulationSize = 200,        // Tamanho da população
    MaxGenerations = 100,        // Número de gerações
    MaxDepth = 6,                // Profundidade máxima da árvore
    CrossoverRate = 0.8,         // Taxa de crossover (80%)
    MutationRate = 0.1,          // Taxa de mutação (10%)
    ElitismRate = 0.1,           // Taxa de elitismo (10%)
    TournamentSize = 3,          // Tamanho do torneio
    Functions = new List<string> { "+", "-", "*", "/" },
    Terminals = new List<string> { "x" }
};
```

## Conceitos de GP Demonstrados

### 1. Representação em Árvore
As soluções são representadas como árvores de expressão (AST), permitindo estruturas de tamanho variável.

### 2. Inicialização Diversificada
Usa Ramped Half-and-Half para criar população inicial com diversidade estrutural.

### 3. Operadores Genéticos
- **Crossover de Subárvore**: Troca material genético entre programas
- **Mutação de Subárvore**: Introduz novidade aleatória

### 4. Seleção Baseada em Fitness
Tournament selection favorece indivíduos mais aptos sem eliminar diversidade.

### 5. Elitismo
Garante que as melhores soluções sejam preservadas entre gerações.

### 6. Parsimony Pressure
Penaliza programas maiores para controlar bloat (crescimento excessivo).

## Exercícios Sugeridos

### Fácil
1. Modifique a função objetivo para `f(x) = 2x + 3`
2. Adicione mais operadores: `pow`, `sqrt`
3. Altere os parâmetros e observe o impacto

### Médio
4. Implemente regressão com múltiplas variáveis: `f(x,y) = x² + y²`
5. Adicione visualização gráfica dos resultados
6. Implemente validação cruzada (train/test split)

### Avançado
7. Implemente "Strongly Typed GP" com restrições de tipos
8. Adicione "Automatically Defined Functions" (ADFs)
9. Implemente GP multi-objetivo com Pareto front
10. Adicione paralelização usando `Parallel.ForEach`

## Extensões Possíveis

### 1. Mais Operadores
```csharp
Functions = new List<string> 
{ 
    "+", "-", "*", "/", 
    "sin", "cos", "tan",
    "exp", "log", "sqrt",
    "pow", "abs", "max", "min"
};
```

### 2. Constantes Efêmeras
Já implementado no `TreeGenerator` - constantes aleatórias entre -5 e 5.

### 3. Proteção contra Erros
Já implementado:
- Divisão por zero retorna 0
- Log de valores negativos retorna 0
- Exponencial muito grande retorna MaxValue

### 4. Fitness Multi-objetivo
```csharp
double FitnessFunction(Individual ind)
{
    var accuracy = CalculateAccuracy(ind);
    var simplicity = 1.0 / ind.GetSize();
    return 0.7 * accuracy + 0.3 * simplicity;
}
```

## Requisitos

- .NET 10.0 ou superior
- Nenhuma dependência externa

## Tempo de Execução

- População de 200, 100 gerações: ~1 minuto
- Resultados podem variar devido à natureza estocástica do algoritmo

## Referências

Este exemplo é baseado em:
- Koza, J. R. (1992). "Genetic Programming"
- Poli, R., Langdon, W. B., & McPhee, N. F. (2008). "A Field Guide to Genetic Programming"

## Próximos Passos

Após dominar este exemplo básico, explore:
- **Exemplo 2**: Regressão Simbólica Avançada
- **Exemplo 3**: Otimização de Rotas com Telemetria (Caminhão-AWS)
- **Exemplo 4**: Classificação de Dados
