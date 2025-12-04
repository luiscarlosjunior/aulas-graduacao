namespace BasicGPFramework;

/// <summary>
/// Configuração para o motor de GP
/// </summary>
public class GPConfig
{
    public int PopulationSize { get; set; } = 100;
    public int MaxGenerations { get; set; } = 50;
    public int MaxDepth { get; set; } = 6;
    public double CrossoverRate { get; set; } = 0.8;
    public double MutationRate { get; set; } = 0.1;
    public double ElitismRate { get; set; } = 0.1;
    public int TournamentSize { get; set; } = 3;
    public List<string> Functions { get; set; } = new() { "+", "-", "*", "/" };
    public List<string> Terminals { get; set; } = new() { "x" };
    public int? RandomSeed { get; set; } = null;
}

/// <summary>
/// Motor principal de Programação Genética
/// </summary>
public class GPEngine
{
    private readonly GPConfig _config;
    private readonly TreeGenerator _treeGenerator;
    private readonly GeneticOperators _operators;
    private readonly Func<Individual, double> _fitnessFunction;
    
    public Population? CurrentPopulation { get; private set; }
    public Individual? BestEverIndividual { get; private set; }
    
    public GPEngine(GPConfig config, Func<Individual, double> fitnessFunction)
    {
        _config = config;
        _fitnessFunction = fitnessFunction;
        
        _treeGenerator = new TreeGenerator(
            _config.Functions, 
            _config.Terminals, 
            _config.MaxDepth,
            _config.RandomSeed);
        
        _operators = new GeneticOperators(_treeGenerator, _config.RandomSeed);
    }
    
    /// <summary>
    /// Executa o processo evolutivo
    /// </summary>
    public Individual Run()
    {
        // 1. Inicialização
        Console.WriteLine("=== Iniciando Programação Genética ===\n");
        Console.WriteLine($"Tamanho da População: {_config.PopulationSize}");
        Console.WriteLine($"Gerações Máximas: {_config.MaxGenerations}");
        Console.WriteLine($"Profundidade Máxima: {_config.MaxDepth}");
        Console.WriteLine($"Taxa de Crossover: {_config.CrossoverRate}");
        Console.WriteLine($"Taxa de Mutação: {_config.MutationRate}");
        Console.WriteLine($"Taxa de Elitismo: {_config.ElitismRate}");
        Console.WriteLine($"Tamanho do Torneio: {_config.TournamentSize}\n");
        
        CurrentPopulation = _treeGenerator.GenerateInitialPopulation(_config.PopulationSize);
        
        // Avalia população inicial
        EvaluatePopulation(CurrentPopulation);
        BestEverIndividual = CurrentPopulation.BestIndividual.Clone();
        
        Console.WriteLine($"População inicial gerada e avaliada.");
        Console.WriteLine(CurrentPopulation.GetStatistics());
        Console.WriteLine($"\nMelhor inicial: {BestEverIndividual}\n");
        
        // 2. Loop Evolutivo
        for (int generation = 1; generation <= _config.MaxGenerations; generation++)
        {
            CurrentPopulation = EvolveGeneration(CurrentPopulation);
            CurrentPopulation.Generation = generation;
            
            // Atualiza melhor de todos os tempos
            if (CurrentPopulation.BestFitness > BestEverIndividual.Fitness)
            {
                BestEverIndividual = CurrentPopulation.BestIndividual.Clone();
                Console.WriteLine($"\n*** Nova melhor solução encontrada na geração {generation}! ***");
                Console.WriteLine($"    {BestEverIndividual}\n");
            }
            
            // Log a cada 10 gerações ou na última
            if (generation % 10 == 0 || generation == _config.MaxGenerations)
            {
                Console.WriteLine(CurrentPopulation.GetStatistics());
            }
        }
        
        // 3. Resultado Final
        Console.WriteLine("\n=== Evolução Concluída ===\n");
        Console.WriteLine("Melhor solução encontrada:");
        Console.WriteLine($"  {BestEverIndividual}");
        Console.WriteLine($"\nExpressão: {BestEverIndividual.Root}");
        
        return BestEverIndividual;
    }
    
    /// <summary>
    /// Avalia a fitness de todos os indivíduos
    /// </summary>
    private void EvaluatePopulation(Population population)
    {
        foreach (var individual in population.Individuals)
        {
            individual.Fitness = _fitnessFunction(individual);
        }
    }
    
    /// <summary>
    /// Evolui uma geração
    /// </summary>
    private Population EvolveGeneration(Population currentPopulation)
    {
        var newPopulation = new Population(currentPopulation.Generation + 1);
        
        // 1. Elitismo - preserva os melhores
        int eliteCount = (int)(_config.PopulationSize * _config.ElitismRate);
        var elite = currentPopulation.Individuals
            .OrderByDescending(i => i.Fitness)
            .Take(eliteCount)
            .Select(i => i.Clone())
            .ToList();
        
        foreach (var individual in elite)
        {
            newPopulation.Add(individual);
        }
        
        // 2. Gera nova população através de seleção e reprodução
        while (newPopulation.Individuals.Count < _config.PopulationSize)
        {
            Individual offspring1, offspring2;
            
            // Seleção de pais
            var parent1 = _operators.TournamentSelection(currentPopulation, _config.TournamentSize);
            var parent2 = _operators.TournamentSelection(currentPopulation, _config.TournamentSize);
            
            // Crossover
            if (new Random().NextDouble() < _config.CrossoverRate)
            {
                (offspring1, offspring2) = _operators.Crossover(parent1, parent2);
            }
            else
            {
                offspring1 = parent1.Clone();
                offspring2 = parent2.Clone();
            }
            
            // Mutação
            offspring1 = _operators.Mutate(offspring1, _config.MutationRate);
            offspring2 = _operators.Mutate(offspring2, _config.MutationRate);
            
            // Adiciona à nova população
            if (newPopulation.Individuals.Count < _config.PopulationSize)
            {
                newPopulation.Add(offspring1);
            }
            
            if (newPopulation.Individuals.Count < _config.PopulationSize)
            {
                newPopulation.Add(offspring2);
            }
        }
        
        // 3. Avalia nova população
        EvaluatePopulation(newPopulation);
        
        return newPopulation;
    }
}
