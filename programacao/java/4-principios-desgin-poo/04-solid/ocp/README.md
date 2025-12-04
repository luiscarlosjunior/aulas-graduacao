# OCP - Open/Closed Principle
## Princípio Aberto/Fechado

## 📖 Visão Geral

O **Open/Closed Principle (OCP)** estabelece que entidades de software (classes, módulos, funções) devem estar **abertas para extensão**, mas **fechadas para modificação**. Isso significa que devemos ser capazes de adicionar novo comportamento sem modificar código existente e testado.

## 🎯 Definição

> "Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification."
>
> "Entidades de software devem estar abertas para extensão, mas fechadas para modificação."
>
> -- Bertrand Meyer (formulação original, 1988)

**Refinamento de Robert C. Martin:**
> "You should be able to extend the behavior of a system without having to modify that system."
>
> "Você deve ser capaz de estender o comportamento de um sistema sem ter que modificar esse sistema."

## 📚 Origem e História

### Bertrand Meyer (1985-1988)

O princípio foi originalmente formulado por **Bertrand Meyer**, criador da linguagem Eiffel, em seu livro **"Object-Oriented Software Construction"** (1988).

### Robert C. Martin (1996)

Robert C. Martin popularizou e adaptou OCP para o contexto moderno de OO, publicando "The Open-Closed Principle" no C++ Report em 1996.

### Evolução

OCP evoluiu com design patterns (Strategy, Template Method, etc.) que fornecem técnicas práticas para alcançá-lo.

## 🔍 Entendendo "Aberto" e "Fechado"

### Aberto para Extensão
Podemos adicionar novo comportamento quando requisitos mudam. O módulo pode se adaptar a novas situações.

### Fechado para Modificação
Código existente não é alterado. Código testado e funcionando permanece intocado.

### Paradoxo Aparente?
Como algo pode ser aberto E fechado ao mesmo tempo? A resposta está em **abstração e polimorfismo**.

## 🎯 Por Que OCP é Importante?

### 1. **Reduz Risco de Regressão**
Código existente não é modificado, então bugs existentes não são introduzidos em funcionalidade já funcionando.

### 2. **Facilita Manutenção**
Adicionar features é mais seguro que modificar código existente.

### 3. **Melhora Testabilidade**
Novas extensões podem ser testadas isoladamente sem re-testar todo o código base.

### 4. **Promove Reutilização**
Abstrações criadas para extensibilidade são naturalmente reutilizáveis.

### 5. **Suporta Evolução**
Sistema pode crescer e adaptar-se a novos requisitos de forma controlada.

## ❌ Violação de OCP

### Exemplo Clássico: If/Else ou Switch Crescente

```java
// ❌ Violação de OCP: Precisa modificar classe para adicionar novo tipo
public class CalculadoraDesconto {
    
    public double calcular(TipoCliente tipo, double valor) {
        if (tipo == TipoCliente.REGULAR) {
            return valor * 0.05; // 5% desconto
        } else if (tipo == TipoCliente.VIP) {
            return valor * 0.10; // 10% desconto
        } else if (tipo == TipoCliente.PREMIUM) {
            return valor * 0.15; // 15% desconto
        }
        return 0;
    }
}

// Problema: Para adicionar novo tipo (ex: ENTERPRISE),
// precisa MODIFICAR o método calcular()
// Cada adição aumenta complexidade ciclomática
// Viola OCP: fechado para modificação
```

**Por que viola OCP:**
1. Adicionar `ENTERPRISE` requer modificar `calcular()`
2. Código testado é alterado
3. Risco de quebrar tipos existentes
4. Difícil de testar novos tipos isoladamente

## ✅ Seguindo OCP: Abstração e Polimorfismo

```java
// ✅ Seguindo OCP: Aberto para extensão, fechado para modificação

// Interface define contrato
public interface CalculadoraDesconto {
    double calcular(double valor);
}

// Implementações específicas - cada uma fechada para modificação
public class DescontoRegular implements CalculadoraDesconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.05; // 5%
    }
}

public class DescontoVIP implements CalculadoraDesconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.10; // 10%
    }
}

public class DescontoPremium implements CalculadoraDesconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.15; // 15%
    }
}

// ✅ Para adicionar ENTERPRISE, criamos NOVA classe (extensão)
// SEM modificar classes existentes
public class DescontoEnterprise implements CalculadoraDesconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.20; // 20%
    }
}

// Cliente usa abstração
public class ProcessadorPedido {
    private CalculadoraDesconto calculadora;
    
    public ProcessadorPedido(CalculadoraDesconto calculadora) {
        this.calculadora = calculadora;
    }
    
    public double calcularTotal(double valorBase) {
        double desconto = calculadora.calcular(valorBase);
        return valorBase - desconto;
    }
}

// Uso
ProcessadorPedido proc1 = new ProcessadorPedido(new DescontoRegular());
ProcessadorPedido proc2 = new ProcessadorPedido(new DescontoVIP());
ProcessadorPedido proc3 = new ProcessadorPedido(new DescontoEnterprise()); // NOVA sem modificar existentes!
```

**Por que segue OCP:**
1. ✅ Adicionar `DescontoEnterprise` = nova classe (extensão)
2. ✅ Classes existentes não são modificadas (fechadas)
3. ✅ Sem risco de regressão
4. ✅ Novos tipos testáveis isoladamente

## 🛠️ Técnicas para Alcançar OCP

### 1. **Abstração via Interfaces**
```java
public interface FormaGeometrica {
    double calcularArea();
}

// Extensão: novas formas sem modificar existentes
public class Circulo implements FormaGeometrica {
    private double raio;
    
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}

public class Retangulo implements FormaGeometrica {
    private double largura, altura;
    
    public double calcularArea() {
        return largura * altura;
    }
}
```

### 2. **Herança (quando apropriado)**
```java
public abstract class Relatorio {
    public final void gerar() {
        coletarDados();
        formatarDados();
        exportar();
    }
    
    protected abstract void formatarDados();
    protected abstract void exportar();
    
    private void coletarDados() {
        // Comum a todos
    }
}

public class RelatorioPDF extends Relatorio {
    protected void formatarDados() { /* PDF */ }
    protected void exportar() { /* PDF */ }
}

public class RelatorioExcel extends Relatorio {
    protected void formatarDados() { /* Excel */ }
    protected void exportar() { /* Excel */ }
}
```

### 3. **Strategy Pattern**
```java
public interface EstrategiaValidacao {
    boolean validar(String valor);
}

public class Validador {
    private EstrategiaValidacao estrategia;
    
    public void setEstrategia(EstrategiaValidacao estrategia) {
        this.estrategia = estrategia;
    }
    
    public boolean validar(String valor) {
        return estrategia.validar(valor);
    }
}

// Extensões
public class ValidacaoEmail implements EstrategiaValidacao { }
public class ValidacaoTelefone implements EstrategiaValidacao { }
public class ValidacaoCPF implements EstrategiaValidacao { }
```

### 4. **Dependency Injection**
```java
public class ServicoPagamento {
    private GatewayPagamento gateway;
    
    // Injeta dependência - permite trocar implementação
    public ServicoPagamento(GatewayPagamento gateway) {
        this.gateway = gateway;
    }
}

// Extensões
public class PayPalGateway implements GatewayPagamento { }
public class StripeGateway implements GatewayPagamento { }
```

## 📋 Como Identificar Violações de OCP

### Sinais de Violação:

1. **If/Else ou Switch com tipos**
   - `if (tipo == A)... else if (tipo == B)...`
   - Adicionar tipo C requer modificar o if/else

2. **Modificação Frequente de Classe**
   - Toda nova feature requer abrir e modificar mesma classe

3. **Código "Frágil"**
   - Adicionar funcionalidade quebra código existente

4. **Dificuldade em Adicionar Features**
   - "Para adicionar X, preciso modificar A, B, C, D..."

### Perguntas-Chave:

```
❓ Para adicionar novo comportamento, preciso modificar código existente?
   → Se SIM, viola OCP

❓ Posso adicionar feature criando novas classes sem tocar nas antigas?
   → Se NÃO, viola OCP

❓ Nova feature requer mudar código testado e funcionando?
   → Se SIM, viola OCP
```

## 📋 Diretrizes Práticas

### 1. **Programe para Interfaces**
```java
// ❌ Acoplado a implementação
List<String> lista = new ArrayList<>();

// ✅ Programado para interface
List<String> lista = new ArrayList<>(); // Tipo é interface List
```

### 2. **Use Abstração Onde Variação é Esperada**
Identifique pontos de variação e crie abstrações:
```java
// Pagamento varia? Crie interface
interface ProcessadorPagamento { }

// Relatório varia? Crie interface
interface GeradorRelatorio { }

// Validação varia? Crie interface
interface Validador { }
```

### 3. **Dependency Injection é Seu Amigo**
```java
public class Servico {
    private Dependencia dep;
    
    // ✅ Injeta dependência
    public Servico(Dependencia dep) {
        this.dep = dep;
    }
}
```

### 4. **Evite Instanceof e GetClass()**
```java
// ❌ Viola OCP
if (objeto instanceof TipoA) {
    // faz algo
} else if (objeto instanceof TipoB) {
    // faz outra coisa
}

// ✅ Use polimorfismo
objeto.executar(); // Cada tipo implementa seu próprio comportamento
```

## ⚖️ OCP e Pragmatismo

### Nem Tudo Precisa ser Extensível

**Princípio YAGNI aplica-se:**
- Não crie abstrações "por precaução"
- Crie abstrações quando variação é REAL ou iminente
- Refatore para OCP quando necessário

### Custo de Abstração

Abstrações têm custo:
- Mais classes/interfaces
- Maior complexidade inicial
- Curva de aprendizado

**Use OCP quando:**
- ✅ Variação é esperada ou provável
- ✅ Módulo é frequentemente modificado
- ✅ Múltiplas implementações já existem
- ✅ Extensibilidade é requisito

**Evite OCP prematuro quando:**
- ❌ Código é simples e estável
- ❌ Variação é improvável
- ❌ Única implementação existe e sempre existirá

### Refatoração Evolutiva

```
1. Código simples inicial (pode violar OCP)
2. ↓
3. Padrão de mudança emerge
4. ↓
5. Refatore para seguir OCP
6. ↓
7. Novas extensões sem modificação
```

## 🔗 Relação com Outros Princípios SOLID

- **SRP**: Classes com responsabilidade única são mais fáceis de estender
- **LSP**: Extensões devem ser substituíveis (LSP garante isso)
- **ISP**: Interfaces segregadas facilitam extensão focada
- **DIP**: Inversão de dependência é técnica chave para OCP

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `CalculadoraDescontoSegueOCP.java` - Implementação extensível sem modificação
- `CalculadoraDescontoViolaOCP.java` - Exemplo com if/else que requer modificação

## 🎯 Exercícios Práticos

1. **Identificação**: Encontre switches/if-elses em seu código que violam OCP
2. **Refatoração**: Refatore para usar polimorfismo
3. **Extensão**: Adicione novo comportamento sem modificar código existente
4. **Comparação**: Compare facilidade de adicionar features antes/depois

## 📖 Leituras Recomendadas

1. **"Object-Oriented Software Construction"** - Bertrand Meyer (1988) - Formulação original
2. **"Agile Software Development"** - Robert C. Martin (2002) - OCP moderno
3. **"Design Patterns"** - Gang of Four (1994) - Patterns que implementam OCP

## 💭 Citações Inspiradoras

> "The Open-Closed Principle is at the heart of many of the claims made for object-oriented design." - Bertrand Meyer

> "If you want to create designs that are maintainable, you need to think in terms of abstractions." - Robert C. Martin

---

**Lembre-se:** OCP não significa nunca modificar código. Significa que quando requisitos mudam, devemos preferencialmente **estender** (adicionar novo código) ao invés de **modificar** (alterar código existente). Isso é alcançado através de abstrações e polimorfismo.
