# Exemplo 3: Otimização de Rotas com Telemetria Veicular (Caminhão-AWS)

## 📋 Descrição

Este exemplo demonstra uma aplicação real de Programação Genética para otimizar rotas de entrega de caminhões equipados com dispositivos IoT que transmitem dados de telemetria para a AWS. É um caso de uso prático que combina:

- **Otimização de Rotas** (Vehicle Routing Problem com janelas de tempo)
- **Telemetria IoT** (transmissão de dados veiculares)
- **Integração Cloud** (AWS IoT Core)
- **Multi-objetivo** (distância, tempo, custo de combustível e dados)

## 🎯 Problema

Uma empresa de logística possui uma frota de caminhões equipados com sensores IoT que coletam e transmitem dados para AWS:

- Localização GPS
- Consumo de combustível
- Velocidade e aceleração
- Temperatura do motor
- Peso da carga

**Desafio**: Otimizar as rotas considerando:

1. **Distância total** percorrida
2. **Tempo de viagem**
3. **Consumo de combustível**
4. **Custo de transmissão de dados** (4G vs WiFi)
5. **Penalidades por atraso** nas entregas
6. **Janelas de tempo** para cada entrega

## 🏗️ Arquitetura da Solução

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

## 📁 Estrutura do Projeto

```
TelemetryRouteOptimization/
├── Models.cs                      # Modelos de dados
│   ├── Location                   # Localização geográfica
│   ├── Delivery                   # Entrega a ser realizada
│   ├── TelemetryData             # Dados de telemetria
│   ├── TelemetryConfig           # Configuração de transmissão
│   ├── TrafficData               # Dados de tráfego
│   └── Route                     # Rota completa
├── RouteIndividual.cs            # Indivíduo de rota no GP
├── RouteFitnessCalculator.cs    # Função de fitness multi-objetivo
├── RouteGeneticOperators.cs     # Operadores especializados
├── RouteOptimizationEngine.cs   # Motor de otimização
└── Program.cs                    # Demonstração completa
```

## 🚀 Como Executar

```bash
cd TelemetryRouteOptimization
dotnet run
```

## 📊 Exemplo de Saída

```
╔═══════════════════════════════════════════════════════════════╗
║  OTIMIZAÇÃO DE ROTAS COM TELEMETRIA VEICULAR                  ║
║  Sistema de Entregas - Caminhão IoT + AWS                     ║
╚═══════════════════════════════════════════════════════════════╝

📍 Sistema configurado com 9 localizações
📡 Pontos WiFi disponíveis: 5
📦 Entregas planejadas: 8

[Gen 50] Gen 50: Melhor Fitness=-0.16 | Melhor Dist=74.4km | Tempo=74min

═══════════════════════════════════════════════════════════════
MELHOR SOLUÇÃO ENCONTRADA
═══════════════════════════════════════════════════════════════

📊 MÉTRICAS GERAIS:
  • Distância Total: 74.38 km
  • Tempo Total: 74 minutos (1.2 horas)
  • Consumo de Combustível: 26.03 litros
  • Custo de Combustível: R$ 169.21
  • Custo de Transmissão de Dados: R$ 0.02
  • CUSTO TOTAL: R$ 169.23

📊 ECONOMIA OBTIDA:
  • Distância economizada: 53.40 km (41.8%)
  • Tempo economizado: 53 minutos
  • Economia total: R$ 121.54 (41.8%)
```

## 🧬 Abordagem de Programação Genética

### Representação do Cromossomo

```csharp
public class RouteIndividual
{
    // Sequência de entregas (ordem importa)
    public List<Delivery> DeliverySequence { get; set; }
    
    // Configuração de telemetria
    public TelemetryConfig TelemetryConfig { get; set; }
    
    // Métricas calculadas
    public double TotalDistance { get; set; }
    public double DataTransmissionCost { get; set; }
}
```

### Função de Fitness Multi-objetivo

A fitness combina 5 objetivos com pesos:

```csharp
fitness = -(
    0.30 * normalized_distance +
    0.25 * normalized_time +
    0.25 * normalized_fuel_cost +
    0.15 * normalized_data_cost +
    0.05 * normalized_penalty
)
```

**Componentes**:

1. **Distância** (30%): Minimizar km percorridos
2. **Tempo** (25%): Minimizar tempo de viagem
3. **Combustível** (25%): Minimizar consumo (R$ 6,50/L)
4. **Dados** (15%): Minimizar custo de transmissão 4G
5. **Penalidade** (5%): Evitar atrasos (R$ 100/hora × prioridade)

### Operadores Genéticos Especializados

#### 1. Order Crossover (OX)

Preserva a ordem relativa das entregas:

```
Pai 1: [A, B, C, D, E, F]
Pai 2: [C, F, A, D, B, E]
        ↓     ↓
Filho: [C, F, B, C, D, E]  # Preserva segmento do Pai 1
```

#### 2. Mutações Especializadas

- **Swap Mutation**: Troca duas entregas
- **Inversion Mutation**: Inverte um segmento
- **Insert Mutation**: Move uma entrega
- **Telemetry Config Mutation**: Ajusta configuração de dados

```csharp
// Exemplo de mutação de telemetria
TelemetryConfigMutation(individual):
    - Ajusta intervalo de transmissão (±3 min)
    - Toggle WiFi preferencial
    - Toggle compressão de dados
    - Ajusta tamanho da fila
```

## 💰 Cálculo de Custos

### Custo de Combustível
```
Consumo = (Distância / 100) × 35 L/100km
Custo = Consumo × R$ 6,50/L
```

### Custo de Transmissão de Dados

#### 4G:
```
Transmissões = Tempo_viagem / Intervalo_transmissão
Tamanho_dados = 0.5 MB (ou 0.3 MB se comprimido)
Custo = Transmissões × Tamanho × R$ 0.02/MB
```

#### WiFi:
```
Custo = R$ 0.00 (grátis quando próximo de ponto WiFi)
```

### Penalidade por Atraso
```
Se chegada > janela_fim:
    Atraso_horas = (chegada - janela_fim).TotalHours
    Penalidade = Atraso_horas × R$ 100 × Prioridade
```

## 🔧 Parâmetros Configuráveis

```csharp
var config = new RouteOptimizationConfig
{
    PopulationSize = 100,      // Tamanho da população
    MaxGenerations = 50,       // Número de gerações
    CrossoverRate = 0.8,       // Taxa de crossover (80%)
    MutationRate = 0.15,       // Taxa de mutação (15%)
    ElitismRate = 0.1,         // Taxa de elitismo (10%)
    TournamentSize = 5,        // Tamanho do torneio
    RandomSeed = 42            // Semente (reprodutibilidade)
};
```

## 📡 Integração com AWS IoT Core

### Serviços AWS Utilizáveis

1. **AWS IoT Core**: Recebe telemetria dos caminhões via MQTT
2. **AWS Lambda**: Processa dados em tempo real
3. **Amazon S3**: Armazena logs de telemetria
4. **Amazon DynamoDB**: Estado das rotas e entregas
5. **Amazon CloudWatch**: Monitoramento de métricas
6. **AWS SageMaker**: Re-treina modelo com dados históricos

### Exemplo de Payload de Telemetria

```json
{
  "truck_id": "TRUCK-001",
  "timestamp": "2025-12-04T10:30:00Z",
  "location": {
    "latitude": -23.5505,
    "longitude": -46.6333
  },
  "sensors": {
    "speed": 65,
    "fuel_level": 75,
    "fuel_consumption": 32.5,
    "engine_temp": 90,
    "cargo_weight": 2500
  },
  "route_id": "ROUTE-2025-12-04-001",
  "next_delivery": 3
}
```

### Código de Integração (Conceitual)

```csharp
// Exemplo de envio para AWS IoT Core
public async Task SendTelemetryAsync(
    TelemetryData data, 
    TelemetryConfig config)
{
    var iotClient = new AmazonIoTDataPlaneClient();
    
    var payload = OptimizePayload(data, config);
    
    var request = new PublishRequest
    {
        Topic = $"fleet/truck/{data.TruckId}/telemetry",
        Payload = SerializePayload(payload),
        Qos = 1 // At least once delivery
    };
    
    await iotClient.PublishAsync(request);
}
```

## 🎓 Conceitos de GP Demonstrados

### 1. Codificação de Permutação
Diferente do GP tradicional com árvores, usa permutação de entregas.

### 2. Operadores de Permutação
- Order Crossover (OX)
- Partially Mapped Crossover (PMX)
- Mutações específicas para sequências

### 3. Fitness Multi-objetivo
Balanceia múltiplos critérios conflitantes.

### 4. Restrições
- Janelas de tempo
- Capacidade do veículo
- Jornada de trabalho

### 5. Conhecimento de Domínio
- Priorização de WiFi sobre 4G
- Compressão de dados
- Penalidades proporcionais à prioridade

## 📈 Resultados Esperados

### Economia Típica vs Rota Sequencial

- **Distância**: 30-45% de redução
- **Tempo**: 30-40% de redução
- **Custo Total**: 30-45% de economia
- **Atrasos**: Redução significativa ou eliminação

### Convergência

- Gerações iniciais: Melhorias rápidas (10-20 gerações)
- Gerações médias: Refinamento gradual (20-40 gerações)
- Gerações finais: Estabilização (40-50 gerações)

## 🔬 Experimentos Sugeridos

### Fácil
1. Altere o número de entregas (5-15)
2. Modifique os pesos dos objetivos na fitness
3. Teste diferentes tamanhos de população

### Médio
4. Adicione múltiplos caminhões (Vehicle Fleet Problem)
5. Implemente restrições de capacidade
6. Adicione pontos de recarga (gasolina)

### Avançado
7. Integre dados reais de tráfego (Google Maps API)
8. Implemente re-otimização em tempo real
9. Adicione AWS SDK para telemetria real
10. Implemente aprendizado por reforço híbrido

## 🛠️ Extensões Possíveis

### 1. Múltiplos Veículos
```csharp
public class FleetSolution
{
    public List<RouteIndividual> VehicleRoutes { get; set; }
    
    // Balanceia carga entre veículos
    public void BalanceLoad() { ... }
}
```

### 2. Reotimização Dinâmica
```csharp
public async Task MonitorAndReoptimize()
{
    while (true)
    {
        var telemetry = await GetRealTimeTelemetry();
        
        if (RequiresReoptimization(telemetry))
        {
            var newRoute = Reoptimize(telemetry);
            await SendRouteUpdate(newRoute);
        }
        
        await Task.Delay(TimeSpan.FromMinutes(5));
    }
}
```

### 3. Machine Learning Híbrido
```csharp
// Usa ML para prever tempo de viagem
var predictedTime = trafficPredictor.Predict(
    from: location1,
    to: location2,
    time: datetime,
    weather: currentWeather
);
```

## 📚 Aplicações Práticas

### 1. Logística e Entregas
- Empresas de courier (iFood, Rappi, Loggi)
- E-commerce (Mercado Livre, Amazon)
- Correios

### 2. Coleta de Resíduos
- Rotas de caminhões de lixo
- Otimização de frequência de coleta

### 3. Serviços de Campo
- Técnicos de manutenção
- Leitura de medidores
- Vendedores externos

### 4. Transporte Público
- Rotas de ônibus
- Ajuste de horários

## 🎯 Diferenciais desta Implementação

1. **Multi-objetivo**: Balanceia 5 critérios diferentes
2. **Telemetria IoT**: Integra custos de transmissão de dados
3. **Realismo**: Considera janelas de tempo e prioridades
4. **Escalabilidade**: Pode ser estendido para frotas
5. **Cloud-Ready**: Pronto para integração com AWS
6. **Detalhamento**: Análise completa de custos

## 📖 Referências

### Artigos Acadêmicos
- Gendreau, M., et al. (2002). "A Tabu Search Heuristic for the Vehicle Routing Problem"
- Toth, P., & Vigo, D. (2014). "Vehicle Routing: Problems, Methods, and Applications"
- Li, X., et al. (2016). "Multi-Objective Route Planning for IoT-Enabled Fleet Management"

### Problemas Relacionados
- **TSP** (Traveling Salesman Problem)
- **VRP** (Vehicle Routing Problem)
- **VRPTW** (VRP with Time Windows)
- **CVRP** (Capacitated VRP)
- **MDVRP** (Multi-Depot VRP)

## 💡 Conclusão

Este exemplo demonstra como Programação Genética pode resolver problemas complexos do mundo real, combinando:

- Otimização combinatória
- Múltiplos objetivos
- Restrições práticas
- Integração com tecnologias modernas (IoT, Cloud)

A economia de 40%+ em custos operacionais justifica amplamente o investimento em otimização inteligente de rotas.

## 🔗 Próximos Passos

Após explorar este exemplo:
1. Implemente suas próprias restrições
2. Integre com APIs de mapas reais
3. Adicione visualização geográfica
4. Conecte com AWS IoT de verdade
5. Experimente com dados reais da sua empresa

---

**Desenvolvido para fins educacionais - Demonstração de GP aplicado a problemas reais de logística e telemetria.**
