namespace BasicGPFramework;

/// <summary>
/// Representa um indivíduo (programa) na população
/// </summary>
public class Individual
{
    public Node Root { get; set; }
    public double Fitness { get; set; }
    public int Generation { get; set; }
    
    public Individual(Node root)
    {
        Root = root;
        Fitness = double.MinValue;
        Generation = 0;
    }
    
    /// <summary>
    /// Avalia o indivíduo com as variáveis fornecidas
    /// </summary>
    public double Evaluate(Dictionary<string, double> variables)
    {
        return Root.Evaluate(variables);
    }
    
    /// <summary>
    /// Cria uma cópia profunda do indivíduo
    /// </summary>
    public Individual Clone()
    {
        return new Individual(Root.Clone())
        {
            Fitness = Fitness,
            Generation = Generation
        };
    }
    
    /// <summary>
    /// Retorna a profundidade da árvore
    /// </summary>
    public int GetDepth() => Root.GetDepth();
    
    /// <summary>
    /// Retorna o tamanho total da árvore (número de nós)
    /// </summary>
    public int GetSize() => Root.GetNodeCount();
    
    /// <summary>
    /// Retorna uma representação em string do programa
    /// </summary>
    public override string ToString()
    {
        return $"Fitness: {Fitness:F4} | Tamanho: {GetSize()} | Profundidade: {GetDepth()} | Expressão: {Root}";
    }
}

/// <summary>
/// Representa uma população de indivíduos
/// </summary>
public class Population
{
    public List<Individual> Individuals { get; set; }
    public int Generation { get; set; }
    
    public Population(int generation = 0)
    {
        Individuals = new List<Individual>();
        Generation = generation;
    }
    
    /// <summary>
    /// Retorna o melhor indivíduo da população
    /// </summary>
    public Individual BestIndividual => Individuals.OrderByDescending(i => i.Fitness).First();
    
    /// <summary>
    /// Retorna a fitness média da população
    /// </summary>
    public double AverageFitness => Individuals.Average(i => i.Fitness);
    
    /// <summary>
    /// Retorna a melhor fitness da população
    /// </summary>
    public double BestFitness => BestIndividual.Fitness;
    
    /// <summary>
    /// Retorna a pior fitness da população
    /// </summary>
    public double WorstFitness => Individuals.Min(i => i.Fitness);
    
    /// <summary>
    /// Retorna o tamanho médio dos indivíduos
    /// </summary>
    public double AverageSize => Individuals.Average(i => i.GetSize());
    
    /// <summary>
    /// Retorna a profundidade média dos indivíduos
    /// </summary>
    public double AverageDepth => Individuals.Average(i => i.GetDepth());
    
    /// <summary>
    /// Adiciona um indivíduo à população
    /// </summary>
    public void Add(Individual individual)
    {
        individual.Generation = Generation;
        Individuals.Add(individual);
    }
    
    /// <summary>
    /// Retorna estatísticas da população
    /// </summary>
    public string GetStatistics()
    {
        return $"Geração {Generation}: " +
               $"Melhor={BestFitness:F4}, " +
               $"Média={AverageFitness:F4}, " +
               $"Pior={WorstFitness:F4}, " +
               $"Tam.Médio={AverageSize:F1}, " +
               $"Prof.Média={AverageDepth:F1}";
    }
}
