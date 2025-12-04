namespace TelemetryRouteOptimization.Models;

/// <summary>
/// Representa uma localização geográfica
/// </summary>
public class Location
{
    public int Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public double Latitude { get; set; }
    public double Longitude { get; set; }
    public bool HasWiFi { get; set; }
    public string NetworkType { get; set; } = "4G"; // 4G, 5G, 3G
    
    /// <summary>
    /// Calcula distância euclidiana para outra localização (simplificado)
    /// </summary>
    public double DistanceTo(Location other)
    {
        var latDiff = Latitude - other.Latitude;
        var lonDiff = Longitude - other.Longitude;
        return Math.Sqrt(latDiff * latDiff + lonDiff * lonDiff) * 111.0; // Aproximação em km
    }
    
    public override string ToString() => $"{Name} ({Latitude:F4}, {Longitude:F4})";
}

/// <summary>
/// Representa uma entrega a ser realizada
/// </summary>
public class Delivery
{
    public int Id { get; set; }
    public Location Location { get; set; } = new();
    public DateTime TimeWindowStart { get; set; }
    public DateTime TimeWindowEnd { get; set; }
    public double Weight { get; set; } // em kg
    public int Priority { get; set; } // 1-5 (5 = mais urgente)
    
    public override string ToString() => 
        $"Entrega #{Id} para {Location.Name} (Janela: {TimeWindowStart:HH:mm}-{TimeWindowEnd:HH:mm})";
}

/// <summary>
/// Dados de telemetria coletados do caminhão
/// </summary>
public class TelemetryData
{
    public string TruckId { get; set; } = string.Empty;
    public DateTime Timestamp { get; set; }
    public Location CurrentLocation { get; set; } = new();
    public double Speed { get; set; } // km/h
    public double FuelLevel { get; set; } // %
    public double FuelConsumption { get; set; } // L/100km
    public double EngineTemperature { get; set; } // °C
    public double CargoWeight { get; set; } // kg
    public Dictionary<string, double> SensorData { get; set; } = new();
}

/// <summary>
/// Configuração de transmissão de telemetria
/// </summary>
public class TelemetryConfig
{
    public int DataTransmissionInterval { get; set; } = 5; // minutos
    public Dictionary<string, int> DataPriority { get; set; } = new();
    public bool UseWiFiWhenAvailable { get; set; } = true;
    public bool CompressData { get; set; } = true;
    public int MaxQueueSize { get; set; } = 100; // número de mensagens
    
    public TelemetryConfig()
    {
        // Prioridades padrão (1-10, 10 = mais importante)
        DataPriority = new Dictionary<string, int>
        {
            ["location"] = 10,
            ["fuel"] = 9,
            ["speed"] = 8,
            ["engine_temp"] = 7,
            ["cargo_weight"] = 6,
            ["tire_pressure"] = 5
        };
    }
}

/// <summary>
/// Informações de tráfego
/// </summary>
public class TrafficData
{
    public Location From { get; set; } = new();
    public Location To { get; set; } = new();
    public double CongestionLevel { get; set; } // 0.0 = livre, 1.0 = congestionado
    public double AverageSpeed { get; set; } // km/h
    public DateTime LastUpdate { get; set; }
}

/// <summary>
/// Segmento de rota
/// </summary>
public class RouteSegment
{
    public Location From { get; set; } = new();
    public Location To { get; set; } = new();
    public double Distance { get; set; } // km
    public double EstimatedTime { get; set; } // minutos
    public double FuelCost { get; set; } // litros
    public List<Location> Waypoints { get; set; } = new();
}

/// <summary>
/// Representa uma rota completa
/// </summary>
public class Route
{
    public List<Delivery> Deliveries { get; set; } = new();
    public List<RouteSegment> Segments { get; set; } = new();
    public TelemetryConfig TelemetryConfig { get; set; } = new();
    
    public double TotalDistance => Segments.Sum(s => s.Distance);
    public double TotalTime => Segments.Sum(s => s.EstimatedTime);
    public double TotalFuelCost => Segments.Sum(s => s.FuelCost);
    
    public override string ToString() => 
        $"Rota: {Deliveries.Count} entregas, {TotalDistance:F1}km, {TotalTime:F0}min, {TotalFuelCost:F1}L";
}
