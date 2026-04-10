namespace BasicGPFramework;

/// <summary>
/// Implementa operadores genéticos para GP
/// </summary>
public class GeneticOperators
{
    private readonly Random _random;
    private readonly TreeGenerator _treeGenerator;
    
    public GeneticOperators(TreeGenerator treeGenerator, int? seed = null)
    {
        _random = seed.HasValue ? new Random(seed.Value) : new Random();
        _treeGenerator = treeGenerator;
    }
    
    /// <summary>
    /// Operador de crossover (recombinação)
    /// Troca subárvores aleatórias entre dois pais
    /// </summary>
    public (Individual, Individual) Crossover(Individual parent1, Individual parent2)
    {
        var offspring1 = parent1.Clone();
        var offspring2 = parent2.Clone();
        
        // Obtém todos os nós de cada pai
        var nodes1 = new List<Node>();
        offspring1.Root.GetNodes(nodes1);
        
        var nodes2 = new List<Node>();
        offspring2.Root.GetNodes(nodes2);
        
        if (nodes1.Count == 0 || nodes2.Count == 0)
            return (offspring1, offspring2);
        
        // Seleciona pontos de crossover aleatórios
        var point1 = _random.Next(nodes1.Count);
        var point2 = _random.Next(nodes2.Count);
        
        // Clona as subárvores a serem trocadas
        var subtree1 = nodes1[point1].Clone();
        var subtree2 = nodes2[point2].Clone();
        
        // Realiza a troca
        if (point1 == 0)
        {
            offspring1.Root = subtree2;
        }
        else
        {
            ReplaceNodeInTree(offspring1.Root, nodes1[point1], subtree2);
        }
        
        if (point2 == 0)
        {
            offspring2.Root = subtree1;
        }
        else
        {
            ReplaceNodeInTree(offspring2.Root, nodes2[point2], subtree1);
        }
        
        return (offspring1, offspring2);
    }
    
    /// <summary>
    /// Operador de mutação
    /// Substitui uma subárvore aleatória por uma nova
    /// </summary>
    public Individual Mutate(Individual individual, double mutationRate)
    {
        if (_random.NextDouble() > mutationRate)
            return individual;
        
        var mutated = individual.Clone();
        var nodes = new List<Node>();
        mutated.Root.GetNodes(nodes);
        
        if (nodes.Count == 0)
            return mutated;
        
        // Seleciona um nó aleatório para mutação
        var mutationPoint = _random.Next(nodes.Count);
        
        // Gera uma nova subárvore
        var maxDepth = Math.Max(3, 7 - mutated.GetDepth());
        var newSubtree = _treeGenerator.GenerateTree(maxDepth);
        
        // Substitui a subárvore
        if (mutationPoint == 0)
        {
            mutated.Root = newSubtree;
        }
        else
        {
            ReplaceNodeInTree(mutated.Root, nodes[mutationPoint], newSubtree);
        }
        
        return mutated;
    }
    
    /// <summary>
    /// Seleção por torneio
    /// Seleciona o melhor indivíduo de um grupo aleatório
    /// </summary>
    public Individual TournamentSelection(Population population, int tournamentSize)
    {
        var tournament = new List<Individual>();
        
        for (int i = 0; i < tournamentSize; i++)
        {
            var randomIndex = _random.Next(population.Individuals.Count);
            tournament.Add(population.Individuals[randomIndex]);
        }
        
        return tournament.OrderByDescending(i => i.Fitness).First();
    }
    
    /// <summary>
    /// Seleção por roleta
    /// Probabilidade de seleção proporcional à fitness
    /// </summary>
    public Individual RouletteSelection(Population population)
    {
        // Ajusta fitness para ser sempre positiva
        var minFitness = population.Individuals.Min(i => i.Fitness);
        var offset = minFitness < 0 ? Math.Abs(minFitness) + 1 : 0;
        
        var totalFitness = population.Individuals.Sum(i => i.Fitness + offset);
        var random = _random.NextDouble() * totalFitness;
        
        double cumulative = 0;
        foreach (var individual in population.Individuals)
        {
            cumulative += individual.Fitness + offset;
            if (cumulative >= random)
            {
                return individual;
            }
        }
        
        return population.Individuals.Last();
    }
    
    /// <summary>
    /// Método auxiliar para substituir um nó na árvore
    /// </summary>
    private bool ReplaceNodeInTree(Node root, Node target, Node replacement)
    {
        if (root is FunctionNode functionNode)
        {
            for (int i = 0; i < functionNode.Children.Count; i++)
            {
                if (functionNode.Children[i] == target)
                {
                    functionNode.Children[i] = replacement;
                    return true;
                }
                
                if (ReplaceNodeInTree(functionNode.Children[i], target, replacement))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}
