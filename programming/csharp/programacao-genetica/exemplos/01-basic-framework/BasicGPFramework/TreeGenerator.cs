namespace BasicGPFramework;

/// <summary>
/// Gerador de árvores de expressão
/// </summary>
public class TreeGenerator
{
    private readonly Random _random;
    private readonly List<string> _functions;
    private readonly List<string> _terminals;
    private readonly int _maxDepth;
    
    public TreeGenerator(List<string> functions, List<string> terminals, int maxDepth, int? seed = null)
    {
        _random = seed.HasValue ? new Random(seed.Value) : new Random();
        _functions = functions;
        _terminals = terminals;
        _maxDepth = maxDepth;
    }
    
    /// <summary>
    /// Gera uma árvore usando o método Ramped Half-and-Half
    /// </summary>
    public Node GenerateTree(int currentDepth = 0)
    {
        if (currentDepth == 0)
        {
            // Na raiz, escolhe entre Full ou Grow aleatoriamente
            return _random.Next(2) == 0 
                ? GenerateFull(_maxDepth) 
                : GenerateGrow(_maxDepth);
        }
        
        // Para chamadas recursivas, usa profundidade fornecida
        return GenerateGrow(currentDepth);
    }
    
    /// <summary>
    /// Método Full - todas as árvores têm profundidade máxima
    /// </summary>
    public Node GenerateFull(int depth)
    {
        if (depth == 1)
        {
            return GenerateTerminal();
        }
        
        return GenerateFunction(depth);
    }
    
    /// <summary>
    /// Método Grow - profundidade variável
    /// </summary>
    public Node GenerateGrow(int depth)
    {
        if (depth == 1)
        {
            return GenerateTerminal();
        }
        
        // Pode escolher função ou terminal
        if (_random.NextDouble() < 0.5)
        {
            return GenerateTerminal();
        }
        
        return GenerateFunction(depth);
    }
    
    /// <summary>
    /// Gera um nó de função com filhos
    /// </summary>
    private Node GenerateFunction(int depth)
    {
        var function = _functions[_random.Next(_functions.Count)];
        var children = new List<Node>();
        
        // Determina número de filhos baseado no operador
        int childCount = GetFunctionArity(function);
        
        for (int i = 0; i < childCount; i++)
        {
            children.Add(GenerateGrow(depth - 1));
        }
        
        return new FunctionNode(function, children);
    }
    
    /// <summary>
    /// Gera um nó terminal (variável ou constante)
    /// </summary>
    private Node GenerateTerminal()
    {
        // 50% chance de ser uma constante aleatória
        if (_random.NextDouble() < 0.3)
        {
            // Ephemeral Random Constant
            var value = _random.NextDouble() * 10 - 5; // Entre -5 e 5
            return new TerminalNode(value);
        }
        
        // Caso contrário, escolhe um terminal da lista
        var terminal = _terminals[_random.Next(_terminals.Count)];
        return new TerminalNode(terminal);
    }
    
    /// <summary>
    /// Retorna a aridade (número de argumentos) de uma função
    /// </summary>
    private int GetFunctionArity(string function)
    {
        return function switch
        {
            "+" or "-" or "*" or "/" => 2,
            "sin" or "cos" or "exp" or "log" => 1,
            _ => 2
        };
    }
    
    /// <summary>
    /// Gera uma população inicial
    /// </summary>
    public Population GenerateInitialPopulation(int populationSize)
    {
        var population = new Population(0);
        
        for (int i = 0; i < populationSize; i++)
        {
            var tree = GenerateTree();
            population.Add(new Individual(tree));
        }
        
        return population;
    }
}
