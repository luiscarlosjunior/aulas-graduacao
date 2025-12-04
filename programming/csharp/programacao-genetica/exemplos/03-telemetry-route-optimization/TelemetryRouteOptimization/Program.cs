using TelemetryRouteOptimization.Models;
using TelemetryRouteOptimization.Optimization;
using TelemetryRouteOptimization.GP;

/// <summary>
/// Demonstração de Otimização de Rotas para Telemetria Veicular
/// Caso de Uso: Sistema de entregas com caminhões equipados com IoT
/// conectados à AWS para envio de dados de telemetria
/// </summary>
class Program
{
    static void Main(string[] args)
    {
        Console.Clear();
        Console.WriteLine("╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║                                                               ║");
        Console.WriteLine("║  OTIMIZAÇÃO DE ROTAS COM TELEMETRIA VEICULAR                  ║");
        Console.WriteLine("║  Sistema de Entregas - Caminhão IoT + AWS                     ║");
        Console.WriteLine("║                                                               ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝\n");
        
        // 1. Cria localizações com pontos WiFi
        var locations = CreateLocations();
        var wifiLocations = locations.Where(l => l.HasWiFi).ToList();
        
        Console.WriteLine($"📍 Sistema configurado com {locations.Count} localizações");
        Console.WriteLine($"📡 Pontos WiFi disponíveis: {wifiLocations.Count}\n");
        
        // 2. Cria entregas
        var deliveries = CreateDeliveries(locations);
        
        Console.WriteLine($"📦 Entregas planejadas: {deliveries.Count}");
        Console.WriteLine("\nDetalhes das Entregas:");
        Console.WriteLine("─────────────────────────────────────────────────────────────");
        
        foreach (var delivery in deliveries)
        {
            Console.WriteLine($"  • {delivery}");
        }
        
        Console.WriteLine("\n💡 Objetivo: Encontrar a melhor sequência de entregas que minimize:");
        Console.WriteLine("  1. Distância total percorrida");
        Console.WriteLine("  2. Tempo de viagem");
        Console.WriteLine("  3. Consumo de combustível");
        Console.WriteLine("  4. Custo de transmissão de dados (4G vs WiFi)");
        Console.WriteLine("  5. Penalidades por atraso nas entregas");
        
        Console.WriteLine("\nPressione ENTER para iniciar a otimização...");
        Console.ReadLine();
        
        // 3. Configura e executa otimização
        var config = new RouteOptimizationConfig
        {
            PopulationSize = 100,
            MaxGenerations = 50,
            CrossoverRate = 0.8,
            MutationRate = 0.15,
            ElitismRate = 0.1,
            TournamentSize = 5,
            RandomSeed = 42 // Para reprodutibilidade
        };
        
        var engine = new RouteOptimizationEngine(config, deliveries, wifiLocations);
        var bestSolution = engine.Optimize();
        
        // 4. Análise comparativa
        Console.WriteLine("\n╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║  ANÁLISE COMPARATIVA                                          ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝\n");
        
        Console.WriteLine("Comparando solução otimizada com rota não otimizada (sequencial):\n");
        
        var sequentialRoute = CreateSequentialRoute(deliveries);
        var fitnessCalc = new RouteFitnessCalculator(wifiLocations);
        fitnessCalc.Calculate(sequentialRoute);
        
        Console.WriteLine("ROTA SEQUENCIAL (Sem otimização):");
        Console.WriteLine($"  • Distância: {sequentialRoute.TotalDistance:F2} km");
        Console.WriteLine($"  • Tempo: {sequentialRoute.TotalTime:F0} min ({sequentialRoute.TotalTime/60:F1}h)");
        Console.WriteLine($"  • Combustível: {sequentialRoute.FuelCost:F2} L");
        Console.WriteLine($"  • Custo dados: R$ {sequentialRoute.DataTransmissionCost:F2}");
        Console.WriteLine($"  • Penalidade: R$ {sequentialRoute.LatePenalty:F2}");
        var seqTotalCost = (sequentialRoute.FuelCost * 6.50) + 
                          sequentialRoute.DataTransmissionCost + 
                          sequentialRoute.LatePenalty;
        Console.WriteLine($"  • CUSTO TOTAL: R$ {seqTotalCost:F2}\n");
        
        Console.WriteLine("ROTA OTIMIZADA (Com Programação Genética):");
        Console.WriteLine($"  • Distância: {bestSolution.TotalDistance:F2} km");
        Console.WriteLine($"  • Tempo: {bestSolution.TotalTime:F0} min ({bestSolution.TotalTime/60:F1}h)");
        Console.WriteLine($"  • Combustível: {bestSolution.FuelCost:F2} L");
        Console.WriteLine($"  • Custo dados: R$ {bestSolution.DataTransmissionCost:F2}");
        Console.WriteLine($"  • Penalidade: R$ {bestSolution.LatePenalty:F2}");
        var optTotalCost = (bestSolution.FuelCost * 6.50) + 
                          bestSolution.DataTransmissionCost + 
                          bestSolution.LatePenalty;
        Console.WriteLine($"  • CUSTO TOTAL: R$ {optTotalCost:F2}\n");
        
        Console.WriteLine("📊 ECONOMIA OBTIDA:");
        var distanceSavings = sequentialRoute.TotalDistance - bestSolution.TotalDistance;
        var timeSavings = sequentialRoute.TotalTime - bestSolution.TotalTime;
        var costSavings = seqTotalCost - optTotalCost;
        var percentSavings = (costSavings / seqTotalCost) * 100;
        
        Console.WriteLine($"  • Distância economizada: {distanceSavings:F2} km ({distanceSavings/sequentialRoute.TotalDistance:P1})");
        Console.WriteLine($"  • Tempo economizado: {timeSavings:F0} min ({timeSavings:F1}h)");
        Console.WriteLine($"  • Economia total: R$ {costSavings:F2} ({percentSavings:F1}%)");
        
        if (sequentialRoute.LatePenalty > bestSolution.LatePenalty)
        {
            Console.WriteLine($"  • Redução de atrasos: R$ {sequentialRoute.LatePenalty - bestSolution.LatePenalty:F2}");
        }
        
        Console.WriteLine("\n╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║  INTEGRAÇÃO COM AWS IoT                                       ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝\n");
        
        Console.WriteLine("📡 Esta solução pode ser integrada com AWS IoT Core:");
        Console.WriteLine("  1. AWS IoT Core: Recebe telemetria dos caminhões");
        Console.WriteLine("  2. AWS Lambda: Processa dados em tempo real");
        Console.WriteLine("  3. Amazon S3: Armazena histórico de telemetria");
        Console.WriteLine("  4. Amazon DynamoDB: Armazena estado das rotas");
        Console.WriteLine("  5. AWS SageMaker: Re-treina modelo com dados históricos");
        Console.WriteLine("  6. Amazon CloudWatch: Monitora métricas de frota\n");
        
        Console.WriteLine("💾 Configuração de telemetria otimizada:");
        Console.WriteLine($"  • Intervalo: {bestSolution.TelemetryConfig.DataTransmissionInterval} min");
        Console.WriteLine($"  • WiFi preferencial: {bestSolution.TelemetryConfig.UseWiFiWhenAvailable}");
        Console.WriteLine($"  • Compressão: {bestSolution.TelemetryConfig.CompressData}");
        
        Console.WriteLine("\n╔═══════════════════════════════════════════════════════════════╗");
        Console.WriteLine("║  Otimização Concluída com Sucesso!                            ║");
        Console.WriteLine("╚═══════════════════════════════════════════════════════════════╝");
    }
    
    /// <summary>
    /// Cria localizações de exemplo (São Paulo e região)
    /// </summary>
    static List<Location> CreateLocations()
    {
        return new List<Location>
        {
            // Centro de distribuição
            new Location { Id = 0, Name = "Centro de Distribuição", 
                          Latitude = -23.5505, Longitude = -46.6333, HasWiFi = true },
            
            // Locais de entrega
            new Location { Id = 1, Name = "Shopping Paulista", 
                          Latitude = -23.5614, Longitude = -46.6562, HasWiFi = true },
            new Location { Id = 2, Name = "Zona Industrial Norte", 
                          Latitude = -23.4889, Longitude = -46.6214 },
            new Location { Id = 3, Name = "Centro Empresarial Sul", 
                          Latitude = -23.6183, Longitude = -46.6978, HasWiFi = true },
            new Location { Id = 4, Name = "Distrito Comercial Leste", 
                          Latitude = -23.5475, Longitude = -46.5247 },
            new Location { Id = 5, Name = "Condomínio Logístico Oeste", 
                          Latitude = -23.5288, Longitude = -46.7419, HasWiFi = true },
            new Location { Id = 6, Name = "Terminal de Cargas Norte", 
                          Latitude = -23.4523, Longitude = -46.6333 },
            new Location { Id = 7, Name = "Mercado Municipal Centro", 
                          Latitude = -23.5410, Longitude = -46.6298, HasWiFi = true },
            new Location { Id = 8, Name = "Galpão Industrial Guarulhos", 
                          Latitude = -23.4543, Longitude = -46.5333 }
        };
    }
    
    /// <summary>
    /// Cria entregas de exemplo com janelas de tempo
    /// </summary>
    static List<Delivery> CreateDeliveries(List<Location> locations)
    {
        var deliveries = new List<Delivery>();
        var today = DateTime.Today;
        
        // Ignora o depósito (Id = 0)
        var deliveryLocations = locations.Where(l => l.Id > 0).ToList();
        
        for (int i = 0; i < deliveryLocations.Count; i++)
        {
            var location = deliveryLocations[i];
            
            // Janelas de entrega variadas ao longo do dia
            var startHour = 9 + (i * 2) % 8; // Entre 9h e 17h
            var endHour = startHour + 2;
            
            deliveries.Add(new Delivery
            {
                Id = i + 1,
                Location = location,
                TimeWindowStart = today.AddHours(startHour),
                TimeWindowEnd = today.AddHours(endHour),
                Weight = 100 + (i * 50),
                Priority = 1 + (i % 5)
            });
        }
        
        return deliveries;
    }
    
    /// <summary>
    /// Cria uma rota sequencial (não otimizada) para comparação
    /// </summary>
    static RouteIndividual CreateSequentialRoute(List<Delivery> deliveries)
    {
        return new RouteIndividual
        {
            DeliverySequence = new List<Delivery>(deliveries),
            TelemetryConfig = new TelemetryConfig
            {
                DataTransmissionInterval = 10, // Cada 10 minutos
                UseWiFiWhenAvailable = false,
                CompressData = false,
                MaxQueueSize = 100
            }
        };
    }
}
