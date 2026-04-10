# 09 — IA & Programação Genética

> Conteúdo avançado: algoritmos evolutivos e programação genética implementados em C#. Esta seção combina os conhecimentos de C# avançado com conceitos de inteligência artificial computacional.

---

## Sobre esta Seção

O conteúdo completo desta seção está na pasta [`../programacao-genetica/`](../programacao-genetica/), que foi mantida separada por sua natureza especializada.

---

## O que é Programação Genética?

**Programação Genética (GP)** é uma técnica de inteligência artificial baseada nos princípios da evolução biológica para **evoluir automaticamente programas de computador** que resolvem problemas.

### Conceitos Fundamentais

| Conceito | Biologia | Programação Genética |
|----------|----------|---------------------|
| Indivíduo | Organismo | Programa (árvore de expressão) |
| Gene | Unidade de herança | Nó da árvore (operador ou terminal) |
| Fitness | Aptidão/sobrevivência | Quão bem o programa resolve o problema |
| Seleção | Seleção natural | Torneio, roleta (favorecer os melhores) |
| Crossover | Reprodução sexuada | Troca de sub-árvores entre programas |
| Mutação | Mutação genética | Alteração aleatória de nós |

---

## Estrutura do Código

O código de programação genética está em:
```
../programacao-genetica/
├── README.md                          ← Documentação completa
├── exemplos/
│   ├── 01-basic-framework/
│   │   └── BasicGPFramework/
│   │       ├── Node.cs               ← Nós da árvore de expressão
│   │       ├── Individual.cs         ← Indivíduo = programa
│   │       ├── GPEngine.cs           ← Motor do algoritmo genético
│   │       ├── GeneticOperators.cs   ← Crossover e mutação
│   │       ├── TreeGenerator.cs      ← Geração de árvores aleatórias
│   │       └── Program.cs            ← Demonstração
│   └── 03-telemetry-route-optimization/
│       └── TelemetryRouteOptimization/
│           ├── RouteIndividual.cs    ← Indivíduo especializado para rotas
│           ├── RouteFitnessCalculator.cs
│           ├── RouteGeneticOperators.cs
│           └── Program.cs
```

---

## Exemplo de Árvore de Expressão

```
        +
       / \
      *   3
     / \
    x   2
```
Representa a expressão: `(x * 2) + 3`

```csharp
// Nó interno (operador)
public class Node
{
    public string Op { get; }       // "+", "-", "*", "/"
    public Node?  Left  { get; }
    public Node?  Right { get; }

    // Nó folha (terminal — variável ou constante)
    public double? Value { get; }
    public string? Variable { get; }
}

// Avaliar a expressão para um valor de x
double Avaliar(Node node, Dictionary<string, double> variaveis)
{
    if (node.Value.HasValue) return node.Value.Value;
    if (node.Variable != null) return variaveis[node.Variable];
    
    double esq = Avaliar(node.Left!, variaveis);
    double dir = Avaliar(node.Right!, variaveis);
    
    return node.Op switch
    {
        "+" => esq + dir,
        "-" => esq - dir,
        "*" => esq * dir,
        "/" => dir == 0 ? 1 : esq / dir, // divisão protegida
        _   => throw new InvalidOperationException()
    };
}
```

---

## O Algoritmo de Programação Genética

```
1. INICIALIZAÇÃO: Gerar população aleatória de programas
2. AVALIAÇÃO: Calcular fitness de cada programa
3. SELEÇÃO: Escolher os melhores (torneio ou roleta)
4. REPRODUÇÃO:
   a. Crossover: trocar sub-árvores entre dois programas
   b. Mutação: modificar nó aleatório
5. SUBSTITUIÇÃO: Nova população substitui a antiga
6. REPETIR até convergir (gerações suficientes ou fitness satisfatório)
```

---

## Aplicações

- **Regressão simbólica**: descobrir fórmulas matemáticas a partir de dados
- **Otimização de rotas**: encontrar o menor caminho em grafos
- **Design automático de circuitos**
- **Geração automática de código**
- **Trading algorítmico**: evoluir estratégias de compra/venda

---

## Como Executar

```bash
# Framework básico de GP
cd ../programacao-genetica/exemplos/01-basic-framework/BasicGPFramework
dotnet run

# Otimização de rotas com telemetria
cd ../programacao-genetica/exemplos/03-telemetry-route-optimization/TelemetryRouteOptimization
dotnet run
```

---

## Pré-requisitos

Para aproveitar ao máximo esta seção, você deve ter completado:
- [04 — C# Avançado](../04-csharp-avancado/) — especialmente Generics e LINQ
- [03 — Orientação a Objetos](../03-orientacao-objetos/) — herança e polimorfismo
- Conceitos básicos de algoritmos e estruturas de dados

---

**Seção anterior:** [08 — Testes Unitários](../08-testes-unitarios/)  
**Início da trilha:** [README principal](../README.md)
