# Programação Genética em C#

## 📚 Índice

1. [Introdução](#introdução)
2. [Fundamentos Teóricos](#fundamentos-teóricos)
3. [História e Contexto Acadêmico](#história-e-contexto-acadêmico)
4. [Conceitos Fundamentais](#conceitos-fundamentais)
5. [Algoritmos Genéticos vs Programação Genética](#algoritmos-genéticos-vs-programação-genética)
6. [Arquitetura de um Sistema de Programação Genética](#arquitetura-de-um-sistema-de-programação-genética)
7. [Operadores Genéticos](#operadores-genéticos)
8. [Implementação em C#](#implementação-em-c)
9. [Aplicações Práticas](#aplicações-práticas)
10. [Exemplos de Código](#exemplos-de-código)
11. [Otimização de Rotas em Telemetria](#otimização-de-rotas-em-telemetria)
12. [Melhores Práticas](#melhores-práticas)
13. [Referências Acadêmicas](#referências-acadêmicas)

---

## Introdução

A **Programação Genética** (Genetic Programming - GP) é uma técnica de inteligência artificial que utiliza princípios da evolução biológica para gerar automaticamente programas de computador que resolvem problemas específicos. Introduzida por **John Koza** no início dos anos 1990, a GP é uma extensão dos Algoritmos Genéticos (GA) onde as soluções candidatas são programas de computador representados como estruturas de árvore.

### Por que C#?

C# é uma linguagem moderna, fortemente tipada e orientada a objetos, ideal para implementar sistemas de programação genética devido a:

- **Type Safety**: Garante segurança de tipos em tempo de compilação
- **Generics**: Permite criar estruturas de dados genéricas e reutilizáveis
- **LINQ**: Facilita operações de consulta e transformação em coleções
- **Performance**: Compilação JIT oferece excelente desempenho
- **Ecossistema**: Integração nativa com Azure, AWS SDK, e outras plataformas
- **.NET Core/5+**: Portabilidade cross-platform

---

## Fundamentos Teóricos

### Base Biológica

A programação genética é inspirada na **Teoria da Evolução de Charles Darwin** e nos princípios da **genética mendeliana**. Os conceitos-chave incluem:

1. **Seleção Natural**: Os indivíduos mais aptos têm maior probabilidade de sobreviver e reproduzir
2. **Hereditariedade**: Características são transmitidas de pais para filhos
3. **Variação**: Mutações e recombinação genética criam diversidade
4. **Adaptação**: Populações evoluem ao longo do tempo para se adaptar ao ambiente

### Fundamentos Matemáticos

A GP baseia-se em vários conceitos matemáticos:

#### Função de Fitness
A função de fitness *f(x)* mapeia um indivíduo para um valor numérico que representa sua qualidade:

```
f: I → ℝ
onde I é o espaço de indivíduos
```

#### Probabilidade de Seleção
A probabilidade de um indivíduo *i* ser selecionado é proporcional à sua fitness:

```
P(i) = f(i) / Σf(j)  para todo j na população
```

#### Convergência
A convergência da população para uma solução ótima pode ser analisada usando:

```
E[f(t+1)] ≥ E[f(t)]
onde E[f(t)] é a fitness esperada na geração t
```

---

## História e Contexto Acadêmico

### Cronologia

| Ano | Evento | Contribuidor |
|-----|--------|--------------|
| 1960s | Primeiras ideias de programas auto-modificáveis | Lawrence J. Fogel |
| 1975 | Publicação de "Adaptation in Natural and Artificial Systems" | John Holland |
| 1989 | Primeiros trabalhos em GP | Nichael Cramer, Jürgen Schmidhuber |
| 1992 | Publicação de "Genetic Programming: On the Programming of Computers by Means of Natural Selection" | John Koza |
| 1994 | Primeiro GP para design de circuitos | John Koza |
| 1999 | "Genetic Programming III: Darwinian Invention and Problem Solving" | John Koza et al. |
| 2003 | GP produz resultado patenteável | John Koza |
| 2010s | Integração com Deep Learning e aplicações em larga escala | Diversos pesquisadores |

### Principais Pesquisadores

- **John R. Koza**: Considerado o "pai" da programação genética moderna
- **John Holland**: Pioneiro dos algoritmos genéticos, base para GP
- **Kenneth De Jong**: Contribuições fundamentais para algoritmos evolutivos
- **Riccardo Poli**: Pesquisador líder em análise teórica de GP
- **Lee Spector**: Trabalhos em Push GP e computação evolutiva

---

## Conceitos Fundamentais

### 1. População

Uma **população** é um conjunto de indivíduos (programas candidatos). Em GP:

```
População = {I₁, I₂, I₃, ..., Iₙ}
onde cada Iᵢ é uma árvore de expressão
```

**Tamanho da População**: Tipicamente entre 100 e 10.000 indivíduos, dependendo do problema.

### 2. Cromossomo (Genótipo)

Em GP, o cromossomo é representado como uma **estrutura de árvore** (Abstract Syntax Tree - AST):

```
        +
       / \
      *   3
     / \
    x   2
    
Representa: (x * 2) + 3
```

**Componentes da Árvore**:
- **Nós Internos**: Funções/Operadores (F = {+, -, *, /, sin, cos, if, ...})
- **Nós Folha**: Terminais (T = {x, y, constantes, variáveis})

### 3. Fenótipo

O **fenótipo** é a expressão executável do genótipo. Em C#, isso pode ser:
- Uma expressão lambda
- Um método compilado dinamicamente
- Uma árvore de expressões do .NET

### 4. Função de Fitness (Aptidão)

A função de fitness quantifica quão bem um indivíduo resolve o problema. Exemplos:

**Regressão Simbólica**:
```
fitness(i) = Σ(yᵢ - f(xᵢ))²
```

**Classificação**:
```
fitness(i) = (TP + TN) / (TP + TN + FP + FN)
```

**Otimização Multi-objetivo**:
```
fitness(i) = w₁·f₁(i) + w₂·f₂(i) + ... + wₙ·fₙ(i)
```

### 5. Seleção

Métodos de seleção determinam quais indivíduos se reproduzirão:

#### a) Seleção por Torneio (Tournament Selection)
- Seleciona k indivíduos aleatoriamente
- O melhor do grupo é escolhido
- Tempo: O(k), tipicamente k=3-7

#### b) Seleção por Roleta (Roulette Wheel Selection)
- Probabilidade proporcional à fitness
- Pode favorecer excessivamente indivíduos dominantes
- Tempo: O(n)

#### c) Seleção por Ranking (Rank Selection)
- Ordena por fitness e seleciona baseado na posição
- Mais estável que roleta
- Tempo: O(n log n)

#### d) Seleção Elitista (Elitism)
- Mantém os melhores indivíduos automaticamente
- Previne perda de boas soluções
- Tipicamente preserva 1-10% da população

### 6. Crossover (Recombinação)

O crossover combina material genético de dois pais para criar descendentes:

#### Subtree Crossover (Padrão em GP)
```
Pai 1:    +              Pai 2:    *
         / \                      / \
        *   3                    x   -
       / \                          / \
      x   2                        5   y

Filho:    +
         / \
        *   3
       / \
      x   -
         / \
        5   y
```

**Taxa de Crossover**: Tipicamente 70-90%

### 7. Mutação

A mutação introduz variação aleatória:

#### Tipos de Mutação em GP:

**a) Point Mutation (Mutação de Ponto)**
- Substitui um nó por outro do mesmo tipo
- Terminal por terminal, função por função

**b) Subtree Mutation**
- Substitui uma subárvore inteira por uma nova aleatória

**c) Hoist Mutation**
- Substitui a árvore por uma de suas subárvores

**d) Shrink Mutation**
- Reduz o tamanho da árvore aleatoriamente

**Taxa de Mutação**: Tipicamente 1-10%

---

## Algoritmos Genéticos vs Programação Genética

### Comparação Detalhada

| Aspecto | Algoritmos Genéticos (GA) | Programação Genética (GP) |
|---------|---------------------------|---------------------------|
| **Representação** | String fixa de bits/números | Árvore de tamanho variável |
| **Espaço de Busca** | Soluções de tamanho fixo | Programas de tamanho variável |
| **Objetivo** | Otimizar parâmetros | Gerar programas/estruturas |
| **Complexidade** | Menor | Maior |
| **Expressividade** | Limitada | Alta |
| **Bloat** | Não é um problema | Problema significativo |
| **Exemplo de Uso** | Otimização de rota | Descoberta de fórmulas |

### Quando Usar GP vs GA?

**Use Algoritmos Genéticos quando**:
- O espaço de soluções é bem definido e fixo
- Otimização de parâmetros numéricos
- Problemas de roteamento e escalonamento
- Configurações de sistema

**Use Programação Genética quando**:
- Precisa descobrir a estrutura da solução
- Regressão simbólica
- Design automático de algoritmos
- Síntese de programas
- Descoberta de conhecimento

---

## Arquitetura de um Sistema de Programação Genética

### Componentes Principais

```
┌─────────────────────────────────────────────────────┐
│              Sistema de GP em C#                    │
├─────────────────────────────────────────────────────┤
│                                                     │
│  ┌──────────────┐      ┌──────────────┐           │
│  │ Representação│◄────►│  Avaliação   │           │
│  │   (Árvores)  │      │   (Fitness)  │           │
│  └──────────────┘      └──────────────┘           │
│         ▲                      ▲                    │
│         │                      │                    │
│         ▼                      ▼                    │
│  ┌──────────────┐      ┌──────────────┐           │
│  │   Seleção    │      │   População  │           │
│  │              │◄────►│              │           │
│  └──────────────┘      └──────────────┘           │
│         ▲                      ▲                    │
│         │                      │                    │
│         ▼                      ▼                    │
│  ┌──────────────┐      ┌──────────────┐           │
│  │   Operadores │      │  Critério de │           │
│  │ Genéticos    │      │   Parada     │           │
│  └──────────────┘      └──────────────┘           │
│                                                     │
└─────────────────────────────────────────────────────┘
```

### Fluxo de Execução

```csharp
// Pseudocódigo do loop principal de GP
public void Run()
{
    // 1. Inicialização
    Population population = InitializePopulation();
    
    int generation = 0;
    while (!StoppingCriterion(generation, population))
    {
        // 2. Avaliação
        EvaluateFitness(population);
        
        // 3. Seleção
        var parents = SelectParents(population);
        
        // 4. Reprodução
        var offspring = ApplyCrossover(parents);
        offspring = ApplyMutation(offspring);
        
        // 5. Substituição
        population = ReplacePopulation(population, offspring);
        
        // 6. Estatísticas e Log
        LogStatistics(generation, population);
        
        generation++;
    }
    
    // 7. Retorna melhor solução
    return GetBestIndividual(population);
}
```

---

## Operadores Genéticos

### Operador de Inicialização

Gera a população inicial. Métodos comuns:

#### 1. Full Method
- Todas as árvores têm profundidade máxima
- Usa apenas funções até a profundidade máxima

#### 2. Grow Method
- Profundidade variável até o máximo
- Pode usar terminais em qualquer nível

#### 3. Ramped Half-and-Half
- Combinação de Full e Grow
- Metade com Full, metade com Grow
- Cria diversidade estrutural inicial

### Parâmetros de Controle

| Parâmetro | Valor Típico | Descrição |
|-----------|--------------|-----------|
| Tamanho da População | 100-10000 | Número de indivíduos |
| Taxa de Crossover | 0.7-0.9 | Probabilidade de crossover |
| Taxa de Mutação | 0.01-0.1 | Probabilidade de mutação |
| Profundidade Máxima | 6-17 | Limite de profundidade da árvore |
| Taxa de Elitismo | 0.01-0.1 | Percentual preservado |
| Gerações Máximas | 50-1000 | Critério de parada |

---

## Implementação em C#

### Estruturas de Dados Fundamentais

#### 1. Nó da Árvore de Expressão

```csharp
public abstract class Node
{
    public abstract double Evaluate(Dictionary<string, double> variables);
    public abstract Node Clone();
    public abstract int GetDepth();
    public abstract int GetNodeCount();
    public abstract void GetNodes(List<Node> nodes);
}

public class FunctionNode : Node
{
    public string Operator { get; set; }
    public List<Node> Children { get; set; }
    
    public override double Evaluate(Dictionary<string, double> variables)
    {
        switch (Operator)
        {
            case "+":
                return Children[0].Evaluate(variables) + 
                       Children[1].Evaluate(variables);
            case "-":
                return Children[0].Evaluate(variables) - 
                       Children[1].Evaluate(variables);
            case "*":
                return Children[0].Evaluate(variables) * 
                       Children[1].Evaluate(variables);
            case "/":
                var denominator = Children[1].Evaluate(variables);
                return denominator != 0 
                    ? Children[0].Evaluate(variables) / denominator 
                    : 0;
            default:
                throw new InvalidOperationException();
        }
    }
}

public class TerminalNode : Node
{
    public string Symbol { get; set; }
    public double? Value { get; set; }
    
    public override double Evaluate(Dictionary<string, double> variables)
    {
        if (Value.HasValue)
            return Value.Value;
        return variables.ContainsKey(Symbol) ? variables[Symbol] : 0;
    }
}
```

#### 2. Indivíduo

```csharp
public class Individual
{
    public Node Root { get; set; }
    public double Fitness { get; set; }
    public int Generation { get; set; }
    
    public double Evaluate(Dictionary<string, double> variables)
    {
        return Root.Evaluate(variables);
    }
    
    public Individual Clone()
    {
        return new Individual 
        { 
            Root = Root.Clone(),
            Fitness = Fitness,
            Generation = Generation
        };
    }
    
    public int GetDepth() => Root.GetDepth();
    public int GetSize() => Root.GetNodeCount();
}
```

#### 3. População

```csharp
public class Population
{
    public List<Individual> Individuals { get; set; }
    public int Generation { get; set; }
    
    public Individual BestIndividual => 
        Individuals.OrderByDescending(i => i.Fitness).First();
    
    public double AverageFitness => 
        Individuals.Average(i => i.Fitness);
    
    public double BestFitness => BestIndividual.Fitness;
}
```

### Implementação dos Operadores

#### Crossover

```csharp
public class CrossoverOperator
{
    private readonly Random _random;
    
    public (Individual, Individual) Crossover(
        Individual parent1, 
        Individual parent2)
    {
        var offspring1 = parent1.Clone();
        var offspring2 = parent2.Clone();
        
        // Seleciona pontos de crossover aleatórios
        var nodes1 = new List<Node>();
        offspring1.Root.GetNodes(nodes1);
        var crossoverPoint1 = _random.Next(nodes1.Count);
        
        var nodes2 = new List<Node>();
        offspring2.Root.GetNodes(nodes2);
        var crossoverPoint2 = _random.Next(nodes2.Count);
        
        // Troca subárvores
        var temp = nodes1[crossoverPoint1];
        nodes1[crossoverPoint1] = nodes2[crossoverPoint2].Clone();
        nodes2[crossoverPoint2] = temp.Clone();
        
        return (offspring1, offspring2);
    }
}
```

#### Mutação

```csharp
public class MutationOperator
{
    private readonly Random _random;
    private readonly TreeGenerator _treeGenerator;
    
    public Individual Mutate(Individual individual, double mutationRate)
    {
        if (_random.NextDouble() > mutationRate)
            return individual;
        
        var mutated = individual.Clone();
        var nodes = new List<Node>();
        mutated.Root.GetNodes(nodes);
        
        // Seleciona nó aleatório para mutação
        var mutationPoint = _random.Next(nodes.Count);
        
        // Gera nova subárvore
        var newSubtree = _treeGenerator.GenerateTree(maxDepth: 3);
        
        // Substitui subárvore
        nodes[mutationPoint] = newSubtree;
        
        return mutated;
    }
}
```

### Gerenciamento de Bloat

**Bloat** é o crescimento descontrolado do tamanho dos programas sem melhoria na fitness.

#### Técnicas de Controle:

1. **Parsimony Pressure**
```csharp
public double CalculateFitness(Individual individual)
{
    var rawFitness = EvaluateObjective(individual);
    var sizePenalty = individual.GetSize() * 0.001;
    return rawFitness - sizePenalty;
}
```

2. **Size Limit**
```csharp
public bool IsValid(Individual individual)
{
    return individual.GetDepth() <= MaxDepth && 
           individual.GetSize() <= MaxNodes;
}
```

3. **Double Tournament**
- Primeiro torneio: seleciona por fitness
- Segundo torneio: entre vencedores, seleciona menor

---

## Aplicações Práticas

### 1. Regressão Simbólica

**Objetivo**: Descobrir a função matemática que melhor se ajusta aos dados.

**Exemplo**: Dados temperatura vs vendas de sorvete, descobrir f(x).

```csharp
// Dataset
var data = new[]
{
    (x: 20.0, y: 100.0),
    (x: 25.0, y: 150.0),
    (x: 30.0, y: 200.0),
    (x: 35.0, y: 250.0)
};

// Fitness: Erro Quadrático Médio
public double CalculateFitness(Individual individual)
{
    double mse = 0;
    foreach (var (x, y) in data)
    {
        var predicted = individual.Evaluate(new() { ["x"] = x });
        mse += Math.Pow(y - predicted, 2);
    }
    return -mse / data.Length; // Negativo pois maximizamos fitness
}
```

### 2. Otimização de Funções

**Objetivo**: Encontrar máximo/mínimo de funções complexas.

**Aplicações**:
- Design de antenas
- Otimização de parâmetros de máquina
- Configuração de sistemas

### 3. Classificação e Machine Learning

**Objetivo**: Evoluir classificadores que separam classes.

```csharp
public class GPClassifier
{
    public bool Classify(Dictionary<string, double> features)
    {
        var output = BestIndividual.Evaluate(features);
        return output > 0; // Threshold
    }
}
```

**Aplicações**:
- Detecção de fraude
- Diagnóstico médico
- Análise de crédito

### 4. Geração de Estratégias de Trading

**Objetivo**: Evoluir regras de compra/venda de ativos.

```csharp
// Terminals: preço, volume, médias móveis
// Functions: if, >, <, and, or
// Fitness: retorno do portfólio
```

### 5. Design Automático de Circuitos

**Objetivo**: Evoluir circuitos eletrônicos que atendem especificações.

John Koza demonstrou que GP pode criar circuitos que competem com designs patenteados.

### 6. Síntese de Programas

**Objetivo**: Gerar programas que passam em testes unitários.

```csharp
// Fitness baseada em quantos testes passam
public double CalculateFitness(Individual program)
{
    int passedTests = 0;
    foreach (var test in testCases)
    {
        var output = program.Execute(test.Input);
        if (output.Equals(test.ExpectedOutput))
            passedTests++;
    }
    return passedTests / (double)testCases.Count;
}
```

---

## Otimização de Rotas em Telemetria

### Caso de Uso: Sistema de Telemetria Caminhão-AWS

Este exemplo demonstra como usar programação genética para otimizar rotas de caminhões que enviam dados de telemetria para AWS.

#### Contexto do Problema

**Cenário**: Uma frota de caminhões equipada com dispositivos IoT que coletam:
- Localização GPS
- Consumo de combustível
- Temperatura de carga
- Velocidade e aceleração
- Status do motor

**Desafio**: Otimizar rotas considerando:
- Distância total
- Consumo de combustível
- Tempo de entrega
- Condições de tráfego em tempo real
- Custos de conectividade (envio de dados para AWS)
- Janelas de entrega

#### Formulação do Problema

**Variáveis de Decisão**:
- Sequência de pontos de entrega
- Rotas entre pontos
- Horários de saída/chegada
- Pontos de recarga de dados (WiFi vs 4G)

**Função Objetivo Multi-critério**:
```
minimize: F(rota) = w₁·distância + w₂·tempo + w₃·custo_combustível + 
                    w₄·custo_dados + w₅·penalidade_atraso

Sujeito a:
- Capacidade do caminhão
- Janelas de tempo de entrega
- Horas de direção regulamentadas
- Cobertura de rede (para transmissão de dados)
```

#### Arquitetura da Solução

```
┌─────────────────────────────────────────────────────────┐
│              Sistema de Otimização de Rotas             │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  ┌──────────────┐      ┌──────────────┐              │
│  │   Caminhão   │─────►│  Dispositivo │              │
│  │   (Veículo)  │      │     IoT      │              │
│  └──────────────┘      └───────┬──────┘              │
│                                 │                      │
│                                 │ Telemetria           │
│                                 ▼                      │
│                        ┌────────────────┐             │
│                        │   AWS IoT Core │             │
│                        └────────┬───────┘             │
│                                 │                      │
│                    ┌────────────┼────────────┐        │
│                    ▼            ▼            ▼        │
│              ┌─────────┐  ┌─────────┐  ┌─────────┐  │
│              │   S3    │  │ Lambda  │  │DynamoDB │  │
│              │ (Logs)  │  │(Process)│  │ (State) │  │
│              └─────────┘  └────┬────┘  └─────────┘  │
│                                 │                      │
│                                 ▼                      │
│                        ┌────────────────┐             │
│                        │  GP Optimizer  │             │
│                        │   (C# .NET)    │             │
│                        └────────────────┘             │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

#### Representação do Cromossomo

```csharp
public class RouteIndividual : Individual
{
    // Sequência de entregas
    public List<Delivery> DeliverySequence { get; set; }
    
    // Subroteiro entre pontos
    public Dictionary<(int, int), Route> SubRoutes { get; set; }
    
    // Configuração de telemetria
    public TelemetryConfig TelemetryConfig { get; set; }
}

public class TelemetryConfig
{
    // Frequência de envio de dados (minutos)
    public int DataTransmissionInterval { get; set; }
    
    // Prioridade de dados (críticos vs normais)
    public Dictionary<string, int> DataPriority { get; set; }
    
    // Uso de WiFi vs 4G
    public List<Location> WiFiPoints { get; set; }
}
```

#### Função de Fitness

```csharp
public class RouteOptimizationFitness
{
    private const double WEIGHT_DISTANCE = 0.3;
    private const double WEIGHT_TIME = 0.25;
    private const double WEIGHT_FUEL = 0.25;
    private const double WEIGHT_DATA_COST = 0.15;
    private const double WEIGHT_PENALTY = 0.05;
    
    public double Calculate(RouteIndividual individual)
    {
        var distance = CalculateTotalDistance(individual);
        var time = CalculateTotalTime(individual);
        var fuelCost = CalculateFuelCost(individual, distance);
        var dataCost = CalculateDataTransmissionCost(individual);
        var penalty = CalculateDeliveryPenalty(individual);
        
        // Normalização dos valores
        var normalizedDistance = distance / MAX_DISTANCE;
        var normalizedTime = time / MAX_TIME;
        var normalizedFuel = fuelCost / MAX_FUEL_COST;
        var normalizedData = dataCost / MAX_DATA_COST;
        
        // Fitness (queremos minimizar, então invertemos)
        return -(
            WEIGHT_DISTANCE * normalizedDistance +
            WEIGHT_TIME * normalizedTime +
            WEIGHT_FUEL * normalizedFuel +
            WEIGHT_DATA_COST * normalizedData +
            WEIGHT_PENALTY * penalty
        );
    }
    
    private double CalculateDataTransmissionCost(RouteIndividual individual)
    {
        double totalCost = 0;
        var currentLocation = individual.DeliverySequence.First().Location;
        
        foreach (var delivery in individual.DeliverySequence)
        {
            var route = individual.SubRoutes[(currentLocation.Id, delivery.Location.Id)];
            
            // Custo de transmissão ao longo da rota
            foreach (var segment in route.Segments)
            {
                var networkType = GetNetworkType(segment.Location);
                var dataVolume = EstimateDataVolume(
                    individual.TelemetryConfig.DataTransmissionInterval);
                
                totalCost += CalculateTransmissionCost(networkType, dataVolume);
                
                // Verifica se está perto de ponto WiFi (custo zero)
                if (IsNearWiFiPoint(segment.Location, individual.TelemetryConfig.WiFiPoints))
                {
                    totalCost -= dataVolume * WIFI_DISCOUNT;
                }
            }
            
            currentLocation = delivery.Location;
        }
        
        return totalCost;
    }
}
```

#### Integração com AWS IoT

```csharp
public class AwsIotTelemetryService
{
    private readonly IAmazonIoT _iotClient;
    private readonly IAmazonIoTDataPlane _iotDataClient;
    
    public async Task SendTelemetryAsync(TelemetryData data, RouteIndividual route)
    {
        // Prepara payload otimizado baseado na rota
        var payload = OptimizePayload(data, route.TelemetryConfig);
        
        var request = new PublishRequest
        {
            Topic = $"fleet/truck/{data.TruckId}/telemetry",
            Payload = SerializePayload(payload),
            Qos = GetQoSLevel(route.TelemetryConfig, data.Location)
        };
        
        await _iotDataClient.PublishAsync(request);
    }
    
    private byte[] OptimizePayload(TelemetryData data, TelemetryConfig config)
    {
        // Comprime dados baseado na configuração
        var prioritizedData = FilterByPriority(data, config.DataPriority);
        
        // Usa compressão se rede for lenta
        if (IsSlowNetwork(data.Location))
        {
            return CompressData(prioritizedData);
        }
        
        return SerializeData(prioritizedData);
    }
}
```

#### Exemplo Completo de Uso

```csharp
public class TruckFleetOptimizer
{
    private readonly GeneticProgrammingEngine _gpEngine;
    private readonly AwsIotTelemetryService _telemetryService;
    
    public async Task<RouteIndividual> OptimizeFleetRoutes(
        List<Truck> trucks, 
        List<Delivery> deliveries,
        TrafficData realTimeTraffic)
    {
        // Configura GP
        var config = new GPConfig
        {
            PopulationSize = 200,
            MaxGenerations = 100,
            CrossoverRate = 0.8,
            MutationRate = 0.05,
            ElitismRate = 0.1
        };
        
        // Define função de fitness específica
        var fitnessFunction = new RouteOptimizationFitness
        {
            TrafficData = realTimeTraffic,
            Trucks = trucks,
            Deliveries = deliveries
        };
        
        // Executa otimização
        var bestRoute = await _gpEngine.EvolveAsync(
            config, 
            fitnessFunction,
            cancellationToken: default);
        
        // Envia rota otimizada para caminhões via AWS IoT
        foreach (var truck in trucks)
        {
            await _telemetryService.SendRouteUpdate(truck.Id, bestRoute);
        }
        
        return bestRoute;
    }
}
```

#### Operadores Genéticos Especializados

```csharp
public class RouteSpecificCrossover
{
    public (RouteIndividual, RouteIndividual) OrderCrossover(
        RouteIndividual parent1,
        RouteIndividual parent2)
    {
        // Implementa Order Crossover (OX) para preservar ordem de entregas
        var offspring1 = new RouteIndividual();
        var offspring2 = new RouteIndividual();
        
        int point1 = _random.Next(parent1.DeliverySequence.Count);
        int point2 = _random.Next(point1, parent1.DeliverySequence.Count);
        
        // Copia segmento do pai 1
        var segment = parent1.DeliverySequence.GetRange(point1, point2 - point1);
        offspring1.DeliverySequence.AddRange(segment);
        
        // Preenche restante com ordem do pai 2
        foreach (var delivery in parent2.DeliverySequence)
        {
            if (!offspring1.DeliverySequence.Contains(delivery))
            {
                offspring1.DeliverySequence.Add(delivery);
            }
        }
        
        // Herda configuração de telemetria
        offspring1.TelemetryConfig = InheritTelemetryConfig(
            parent1.TelemetryConfig, 
            parent2.TelemetryConfig);
        
        // Mesmo processo para offspring2...
        
        return (offspring1, offspring2);
    }
}
```

#### Mutação Específica de Domínio

```csharp
public class RouteMutationOperator
{
    public RouteIndividual Mutate(RouteIndividual individual)
    {
        var mutated = individual.Clone();
        
        var mutationType = _random.Next(4);
        
        switch (mutationType)
        {
            case 0: // Swap Mutation - troca duas entregas
                SwapDeliveries(mutated);
                break;
                
            case 1: // Inversion Mutation - inverte segmento
                InvertSegment(mutated);
                break;
                
            case 2: // Telemetry Config Mutation
                MutateTelemetryConfig(mutated);
                break;
                
            case 3: // Route Segment Mutation - muda subrota
                MutateSubRoute(mutated);
                break;
        }
        
        return mutated;
    }
    
    private void MutateTelemetryConfig(RouteIndividual individual)
    {
        // Ajusta intervalo de transmissão
        var currentInterval = individual.TelemetryConfig.DataTransmissionInterval;
        individual.TelemetryConfig.DataTransmissionInterval = 
            Math.Max(1, currentInterval + _random.Next(-5, 6));
        
        // Ajusta pontos WiFi
        if (_random.NextDouble() < 0.3)
        {
            var nearbyWiFi = FindNearbyWiFiPoints(individual.DeliverySequence);
            individual.TelemetryConfig.WiFiPoints = nearbyWiFi;
        }
    }
}
```

#### Integração em Tempo Real

```csharp
public class RealTimeOptimizationService
{
    public async Task MonitorAndOptimizeAsync()
    {
        while (!_cancellationToken.IsCancellationRequested)
        {
            // Coleta dados de telemetria em tempo real
            var telemetryData = await _telemetryService.GetLatestDataAsync();
            
            // Detecta desvios significativos
            var deviations = DetectRouteDeviations(telemetryData);
            
            if (deviations.Any(d => d.Severity > REOPTIMIZATION_THRESHOLD))
            {
                // Re-otimiza rotas afetadas
                var affectedTrucks = deviations.Select(d => d.TruckId);
                var updatedRoutes = await ReoptimizeRoutes(affectedTrucks);
                
                // Envia atualizações
                foreach (var route in updatedRoutes)
                {
                    await _telemetryService.SendRouteUpdate(route);
                }
            }
            
            await Task.Delay(TimeSpan.FromMinutes(5));
        }
    }
}
```

### Benefícios da Abordagem GP

1. **Adaptação Dinâmica**: GP pode evoluir soluções que se adaptam a condições em tempo real
2. **Multi-objetivo**: Balanceia múltiplos critérios simultâneos
3. **Descoberta de Padrões**: Pode encontrar estratégias não-óbvias
4. **Escalabilidade**: Funciona com frotas grandes
5. **Integração AWS**: Aproveita infraestrutura cloud para computação distribuída

---

## Melhores Práticas

### 1. Design de Função de Fitness

#### ✅ Boas Práticas:
- Normalizar todos os componentes para mesma escala
- Evitar divisões por zero
- Penalizar soluções inválidas suavemente
- Considerar múltiplos objetivos quando relevante

#### ❌ Evitar:
- Fitness com descontinuidades abruptas
- Overfitting ao conjunto de treino
- Funções de fitness muito complexas (lentas)

```csharp
// BOM: Fitness normalizada e robusta
public double CalculateFitness(Individual ind)
{
    try
    {
        double error = 0;
        int validEvaluations = 0;
        
        foreach (var testCase in testCases)
        {
            try
            {
                var result = ind.Evaluate(testCase.Inputs);
                if (double.IsFinite(result))
                {
                    error += Math.Abs(result - testCase.Expected);
                    validEvaluations++;
                }
            }
            catch
            {
                error += INVALID_PENALTY;
            }
        }
        
        return validEvaluations > 0 
            ? -error / validEvaluations 
            : double.MinValue;
    }
    catch
    {
        return double.MinValue;
    }
}
```

### 2. Controle de Parâmetros

```csharp
public class GPParameters
{
    // Tamanhos recomendados por complexidade do problema
    public static GPParameters ForSimpleProblem() => new()
    {
        PopulationSize = 100,
        MaxGenerations = 50,
        MaxDepth = 6,
        CrossoverRate = 0.9,
        MutationRate = 0.05
    };
    
    public static GPParameters ForComplexProblem() => new()
    {
        PopulationSize = 1000,
        MaxGenerations = 200,
        MaxDepth = 12,
        CrossoverRate = 0.8,
        MutationRate = 0.1
    };
}
```

### 3. Paralelização

```csharp
public class ParallelGPEngine
{
    public Population EvolveGeneration(Population current)
    {
        // Avalia fitness em paralelo
        Parallel.ForEach(current.Individuals, individual =>
        {
            individual.Fitness = _fitnessFunction.Calculate(individual);
        });
        
        // Cria nova geração em paralelo
        var offspring = new ConcurrentBag<Individual>();
        
        Parallel.For(0, current.Individuals.Count / 2, i =>
        {
            var parent1 = SelectParent(current);
            var parent2 = SelectParent(current);
            
            var (child1, child2) = Crossover(parent1, parent2);
            
            offspring.Add(Mutate(child1));
            offspring.Add(Mutate(child2));
        });
        
        return CreateNewPopulation(current, offspring.ToList());
    }
}
```

### 4. Logging e Monitoramento

```csharp
public class GPLogger
{
    public void LogGeneration(int generation, Population population)
    {
        var stats = new
        {
            Generation = generation,
            BestFitness = population.BestFitness,
            AverageFitness = population.AverageFitness,
            WorstFitness = population.Individuals.Min(i => i.Fitness),
            AverageSize = population.Individuals.Average(i => i.GetSize()),
            AverageDepth = population.Individuals.Average(i => i.GetDepth()),
            Diversity = CalculateDiversity(population)
        };
        
        _logger.LogInformation(
            "Gen {Gen}: Best={Best:F4}, Avg={Avg:F4}, Size={Size:F1}, Depth={Depth:F1}",
            stats.Generation, stats.BestFitness, stats.AverageFitness, 
            stats.AverageSize, stats.AverageDepth);
        
        // Salva para análise posterior
        _statisticsRepository.Save(stats);
    }
}
```

### 5. Validação e Testing

```csharp
[TestClass]
public class GPEngineTests
{
    [TestMethod]
    public void TestSymbolicRegression()
    {
        // Problema simples: descobrir f(x) = x^2
        var testData = Enumerable.Range(-10, 21)
            .Select(x => (x: (double)x, y: (double)(x * x)))
            .ToList();
        
        var engine = new GPEngine();
        var best = engine.Evolve(
            populationSize: 100,
            generations: 50,
            fitnessFunction: ind => CalculateFitness(ind, testData)
        );
        
        // Verifica se encontrou solução razoável
        var finalError = CalculateFitness(best, testData);
        Assert.IsTrue(finalError > -10, "Should find reasonable solution");
    }
}
```

---

## Exemplos de Código

### Exemplo 1: Framework Básico de GP

Ver: `/programacao-genetica/exemplos/01-basic-framework/`

Implementa:
- Estrutura de árvore de expressão
- Operadores genéticos básicos
- Loop evolutivo
- Avaliação de fitness

### Exemplo 2: Regressão Simbólica

Ver: `/programacao-genetica/exemplos/02-symbolic-regression/`

Demonstra:
- Descoberta de fórmulas matemáticas
- Visualização de resultados
- Análise de convergência

### Exemplo 3: Otimização de Rotas com Telemetria

Ver: `/programacao-genetica/exemplos/03-telemetry-route-optimization/`

Implementa:
- Sistema completo de otimização de rotas
- Integração com AWS IoT Core
- Processamento em tempo real
- Dashboard de monitoramento

### Exemplo 4: Classificação de Dados

Ver: `/programacao-genetica/exemplos/04-classification/`

Mostra:
- Evolução de árvores de decisão
- Avaliação com métricas de classificação
- Comparação com outros métodos

---

## Referências Acadêmicas

### Livros Fundamentais

1. **Koza, J. R. (1992)**. *Genetic Programming: On the Programming of Computers by Means of Natural Selection*. MIT Press.
   - Obra seminal que definiu o campo

2. **Koza, J. R. (1994)**. *Genetic Programming II: Automatic Discovery of Reusable Programs*. MIT Press.
   - Extensões e automatically defined functions

3. **Poli, R., Langdon, W. B., & McPhee, N. F. (2008)**. *A Field Guide to Genetic Programming*. 
   - Disponível gratuitamente em: http://www.gp-field-guide.org.uk/
   - Excelente recurso introdutório

4. **Banzhaf, W., Nordin, P., Keller, R. E., & Francone, F. D. (1998)**. *Genetic Programming: An Introduction*. Morgan Kaufmann.

### Artigos Importantes

1. **Koza, J. R. (1994)**. "Genetic programming as a means for programming computers by natural selection". *Statistics and Computing*, 4(2), 87-112.

2. **Poli, R., & Langdon, W. B. (1998)**. "Schema theory for genetic programming with one-point crossover and point mutation". *Evolutionary Computation*, 6(3), 231-252.

3. **Luke, S., & Panait, L. (2002)**. "Fighting bloat with nonparametric parsimony pressure". *PPSN*, 411-421.

4. **O'Reilly, U. M., & Oppacher, F. (1995)**. "The troubling aspects of a building block hypothesis for genetic programming". *Foundations of Genetic Algorithms*, 3, 73-88.

### Conferências e Journals

- **GECCO** (Genetic and Evolutionary Computation Conference)
- **EuroGP** (European Conference on Genetic Programming)
- **IEEE CEC** (Congress on Evolutionary Computation)
- **Evolutionary Computation Journal** (MIT Press)
- **Genetic Programming and Evolvable Machines** (Springer)

### Recursos Online

1. **GP Bibliography**: http://www.cs.bham.ac.uk/~wbl/biblio/
   - Base de dados completa de publicações em GP

2. **Genetic Programming Inc.**: http://www.genetic-programming.com/
   - Site oficial de John Koza

3. **ECJ (Evolutionary Computation in Java)**: https://cs.gmu.edu/~eclab/projects/ecj/
   - Framework open-source (adaptável para C#)

4. **TinyGP**: http://cswww.essex.ac.uk/staff/rpoli/TinyGP/
   - Implementação minimalista para aprendizado

### Aplicações Industriais Documentadas

1. **Dow Chemical** (2000s): Uso de GP para otimização de processos químicos
2. **General Electric** (2010s): Design de turbinas usando GP
3. **Various Financial Institutions**: Trading strategies evolution
4. **NASA**: Antena design usando GP (patenteado)

### Teses e Dissertações Relevantes

1. **Spector, L. (2001)**. "Autoconstructive Evolution: Push, PushGP, and Pushpop". *Proceedings of GECCO*.

2. **Montana, D. J. (1995)**. "Strongly typed genetic programming". *Evolutionary Computation*, 3(2), 199-230.

3. **White, D. R., et al. (2013)**. "Better GP benchmarks: community survey results and proposals". *Genetic Programming and Evolvable Machines*, 14(1), 3-29.

---

## Glossário

| Termo | Definição |
|-------|-----------|
| **Aptidão (Fitness)** | Medida de quão bem um indivíduo resolve o problema |
| **AST** | Abstract Syntax Tree - representação em árvore de código |
| **Bloat** | Crescimento descontrolado do tamanho dos programas |
| **Closure** | Propriedade que garante que operações produzem valores válidos |
| **Convergência** | Processo onde população tende para solução ótima |
| **Crossover** | Operador que combina material genético de pais |
| **Elitismo** | Estratégia que preserva melhores indivíduos |
| **Ephemeral Random Constant** | Constante numérica aleatória gerada na criação |
| **Fenótipo** | Expressão executável do genótipo |
| **Genótipo** | Estrutura de dados que representa solução |
| **Gerações** | Iterações do algoritmo evolutivo |
| **População** | Conjunto de indivíduos candidatos |
| **Pressão Seletiva** | Força que favorece indivíduos mais aptos |
| **Ramped Half-and-Half** | Método de inicialização de população |
| **Regressão Simbólica** | Descoberta de expressões matemáticas |
| **Seleção por Torneio** | Método de seleção baseado em competição |
| **Strongly Typed GP** | GP com restrições de tipos |
| **Sufficiency** | Propriedade que garante expressividade adequada |
| **Terminal** | Nó folha na árvore (variável ou constante) |

---

## Conclusão

A Programação Genética é uma técnica poderosa e versátil para resolver problemas complexos através da evolução automática de programas. Neste documento, cobrimos:

- **Fundamentos Teóricos**: Bases matemáticas e biológicas
- **Implementação em C#**: Estruturas de dados e algoritmos
- **Aplicações Práticas**: Desde regressão até otimização de rotas
- **Caso de Uso Real**: Sistema de telemetria caminhão-AWS

### Próximos Passos

1. **Explore os Exemplos**: Execute e modifique o código fornecido
2. **Experimente**: Crie seus próprios problemas
3. **Aprofunde**: Leia as referências acadêmicas
4. **Contribua**: Implemente novas funcionalidades

### Recursos Adicionais no Repositório

- `/programacao-genetica/exemplos/`: Código-fonte completo
- `/programacao-genetica/tutoriais/`: Guias passo-a-passo
- `/programacao-genetica/benchmarks/`: Problemas para testar

---

## Licença e Contribuições

Este material é parte do repositório educacional `aulas-graduacao` e está disponível para uso acadêmico.

**Contribuições são bem-vindas!** Por favor, submeta pull requests com:
- Novos exemplos
- Correções
- Melhorias na documentação
- Benchmarks adicionais

---

**Autor**: Desenvolvido para o curso de Ciência da Computação  
**Última Atualização**: Dezembro 2025  
**Versão**: 1.0.0

