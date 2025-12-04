# Composition Over Inheritance
## Composição sobre Herança

## 📖 Visão Geral

**Composition Over Inheritance** (Composição sobre Herança) é um princípio fundamental de design orientado a objetos que estabelece que devemos preferir composição de objetos à herança de classes para reutilização de código e compartilhamento de comportamento. Este princípio promove design mais flexível, manutenível e menos acoplado.

## 🎯 Definição

> "Favor object composition over class inheritance."
>
> "Favoreça composição de objetos sobre herança de classes."
>
> -- Gang of Four, Design Patterns (1994)

**O princípio estabelece:**
- Use **herança** para relações "is-a" verdadeiras e estáveis
- Use **composição** para relações "has-a" e comportamentos variáveis
- Composição oferece mais **flexibilidade** que herança

## 📚 Origem e História

### Gang of Four (1994)

O princípio foi popularizado pelo **Gang of Four** (Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides) em seu livro seminal **"Design Patterns: Elements of Reusable Object-Oriented Software"** (1994).

### Contexto Histórico

Nos anos 1980-1990, herança era vista como o mecanismo principal de reutilização em OO. Porém, experiência prática mostrou que herança excessiva leva a hierarquias rígidas e frágeis.

### Evolução

- **1990s**: Identificação de problemas com herança profunda
- **Design Patterns**: Muitos patterns usam composição (Strategy, Decorator, etc.)
- **2000s**: Linguagens modernas enfatizam composição (Go sem herança, Rust com traits)

## 🔍 Herança vs Composição

### Herança (Inheritance)
```java
class Animal {
    void comer() { }
    void dormir() { }
}

class Cachorro extends Animal {
    void latir() { }
    // Herda comer() e dormir()
}
```

**Características:**
- Relação **"is-a"** (Cachorro É UM Animal)
- **Acoplamento forte**: subclasse conhece superclasse
- **Estática**: definida em tempo de compilação
- **Rígida**: difícil mudar hierarquia depois

### Composição (Composition)
```java
class Cachorro {
    private Alimentacao alimentacao;
    private Descanso descanso;
    
    void comer() {
        alimentacao.executar();
    }
    
    void dormir() {
        descanso.executar();
    }
    
    void latir() { }
}
```

**Características:**
- Relação **"has-a"** (Cachorro TEM alimentação, descanso)
- **Acoplamento fraco**: objeto usa componentes via interfaces
- **Dinâmica**: pode trocar componentes em runtime
- **Flexível**: fácil adicionar/remover comportamentos

## 🎯 Por Que Composição é Preferível?

### 1. **Flexibilidade**
Comportamentos podem ser trocados em tempo de execução.

```java
// Composição permite trocar comportamento
Cachorro dog = new Cachorro();
dog.setComportamentoLatir(new LatirAlto()); // Runtime!

// Herança não permite
```

### 2. **Evita Hierarquias Complexas**
Herança pode levar a explosão combinatória de classes.

### 3. **Sem Acoplamento de Implementação**
Composição usa interfaces - não conhece implementação.

### 4. **Melhor Testabilidade**
Componentes podem ser testados isoladamente e mockados facilmente.

### 5. **Múltiplos Comportamentos**
Objeto pode compor múltiplos comportamentos sem múltipla herança.

## ❌ Problema com Herança: Explosão de Subclasses

### Exemplo Clássico

```java
// ❌ Herança levando a explosão combinatória de classes

public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;
    
    public abstract double calcularSalario();
}

// Precisamos de funcionários com diferentes combinações de bônus

public class FuncionarioComBonusAnual extends Funcionario {
    @Override
    public double calcularSalario() {
        return salarioBase + (salarioBase * 0.10); // 10% bônus
    }
}

public class FuncionarioComBonusTrimestral extends Funcionario {
    @Override
    public double calcularSalario() {
        return salarioBase + (salarioBase * 0.03); // 3% bônus trimestral
    }
}

// E se precisar de funcionário com AMBOS bônus?
public class FuncionarioComBonusAnualETrimestral extends Funcionario {
    @Override
    public double calcularSalario() {
        return salarioBase + (salarioBase * 0.10) + (salarioBase * 0.03);
    }
}

// E adicionar comissão de vendas?
public class FuncionarioComBonusAnualEComissao extends Funcionario {
    private double comissao;
    // ...
}

public class FuncionarioComBonusTrimestralEComissao extends Funcionario {
    // ...
}

public class FuncionarioComBonusAnualETrimestralEComissao extends Funcionario {
    // ...
}
```

**Problemas:**
- Explosão combinatória: N bônus = 2^N classes
- Código duplicado entre classes
- Impossível adicionar/remover bônus em tempo de execução
- Hierarquia rígida e difícil de manter

## ✅ Solução com Composição: Flexível e Extensível

```java
// ✅ Composição: Estratégia de cálculo de bônus

public interface CalculadoraBonus {
    double calcular(double salarioBase);
}

public class BonusAnual implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * 0.10; // 10% bônus anual
    }
}

public class BonusTrimestral implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * 0.03; // 3% bônus trimestral
    }
}

public class ComissaoVendas implements CalculadoraBonus {
    private double percentualComissao;
    
    public ComissaoVendas(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }
    
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * percentualComissao;
    }
}

public class SemBonus implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return 0;
    }
}

// ✅ Funcionário usa COMPOSIÇÃO, não herança
public class Funcionario {
    private String nome;
    private double salarioBase;
    private List<CalculadoraBonus> calculadorasBonus;
    
    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.calculadorasBonus = new ArrayList<>();
    }
    
    // ✅ Pode adicionar/remover bônus dinamicamente
    public void adicionarBonus(CalculadoraBonus bonus) {
        calculadorasBonus.add(bonus);
    }
    
    public void removerBonus(CalculadoraBonus bonus) {
        calculadorasBonus.remove(bonus);
    }
    
    public double calcularSalario() {
        double total = salarioBase;
        for (CalculadoraBonus bonus : calculadorasBonus) {
            total += bonus.calcular(salarioBase);
        }
        return total;
    }
    
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }
}

// ✅ Uso com Composição:
public class Main {
    public static void main(String[] args) {
        Funcionario func = new Funcionario("João", 5000);
        
        // ✅ Adiciona bônus anual
        func.adicionarBonus(new BonusAnual());
        System.out.println(func.calcularSalario()); // 5500 (5000 + 500)
        
        // ✅ Adiciona bônus trimestral também
        func.adicionarBonus(new BonusTrimestral());
        System.out.println(func.calcularSalario()); // 5650 (5000 + 500 + 150)
        
        // ✅ Adiciona comissão
        func.adicionarBonus(new ComissaoVendas(0.05));
        System.out.println(func.calcularSalario()); // 5900 (5000 + 500 + 150 + 250)
        
        // ✅ Pode remover bônus dinamicamente
        // Sem precisar criar nova classe ou mudar hierarquia!
    }
}
```

**Benefícios:**
- ✅ Sem explosão de classes
- ✅ Bônus podem ser adicionados/removidos em runtime
- ✅ Fácil adicionar novos tipos de bônus (apenas nova classe)
- ✅ Cada calculadora é testável isoladamente
- ✅ Baixo acoplamento e alta flexibilidade

## 📊 Quando Usar Herança vs Composição

### Use Herança Quando:

1. **Relação "IS-A" Verdadeira e Estável**
```java
// ✅ Cachorro realmente É UM Animal
public class Cachorro extends Animal {
    // Comportamento especializado de Animal
}
```

2. **Hierarquia Estável e Bem Definida**
```java
// ✅ Hierarquia matemática estável
public abstract class Forma {
    public abstract double calcularArea();
}
```

3. **Polimorfismo é Necessário**
```java
// ✅ Tratamento polimórfico
public void processar(List<Animal> animais) {
    for (Animal animal : animais) {
        animal.fazerSom(); // Polimorfismo
    }
}
```

### Use Composição Quando:

1. **Relação "HAS-A"**
```java
// ✅ Carro TEM um motor
public class Carro {
    private Motor motor;
}
```

2. **Comportamento Pode Variar**
```java
// ✅ Comportamento de ataque pode variar
public class Personagem {
    private EstrategiaAtaque ataque;
    
    public void setAtaque(EstrategiaAtaque ataque) {
        this.ataque = ataque; // Troca em runtime!
    }
}
```

3. **Múltiplos Comportamentos Necessários**
```java
// ✅ Precisa de múltiplos comportamentos
public class Robo {
    private Movimento movimento;
    private Comunicacao comunicacao;
    private Visao visao;
}
```

4. **Flexibilidade em Runtime**
```java
// ✅ Trocar comportamento dinamicamente
jogador.setArma(new Espada());
jogador.setArma(new Arco()); // Muda!
```

## 📋 Problemas Comuns com Herança

### 1. **Fragile Base Class Problem**
Mudança na superclasse pode quebrar subclasses.

```java
// Superclasse muda
public class Animal {
    public void comer() {
        // Nova implementação quebra subclasses
    }
}

// Subclasse pode quebrar
public class Cachorro extends Animal {
    @Override
    public void comer() {
        super.comer(); // Dependia do comportamento antigo!
        // ...
    }
}
```

### 2. **Yo-Yo Problem**
Navegação complexa em hierarquia profunda.

```java
Animal → Mamifero → Carnivoro → Canideo → Cachorro
// Difícil entender comportamento completo
```

### 3. **Inflexibilidade**
Herança é decisão em tempo de compilação, não pode mudar.

### 4. **Acoplamento Forte**
Subclasse conhece detalhes de implementação da superclasse.

## 📋 Diretrizes Práticas

### 1. **Prefira Interfaces a Classes Abstratas**
```java
// ✅ Interface - composição
public interface Voavel {
    void voar();
}

// Melhor que
public abstract class AnimalVoador {
    public abstract void voar();
}
```

### 2. **Use Strategy Pattern**
```java
public class Contexto {
    private Estrategia estrategia;
    
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }
}
```

### 3. **Use Decorator Pattern**
```java
// Adiciona comportamento via composição
public class CafeComLeite extends Cafe {
    private Cafe cafe;
    
    public CafeComLeite(Cafe cafe) {
        this.cafe = cafe;
    }
}
```

### 4. **Limite Profundidade de Herança**
```
Regra prática: Máximo 3-4 níveis de herança
```

## ⚖️ Balanceando Composição e Herança

### Não Elimine Herança Completamente

Herança tem seu lugar quando apropriada:

```java
// ✅ Herança apropriada
public class ArrayList<E> extends AbstractList<E> {
    // Especialização legítima
}

// ✅ Herança de framework
public class MinhaActivity extends Activity {
    // Android requer herança
}
```

### Use Composição Como Padrão, Herança Como Exceção

```
1. Considere composição primeiro
2. ↓
3. Se relação IS-A verdadeira e estável existir
4. ↓
5. Considere herança
6. ↓
7. Se hierarquia for profunda ou complexa
8. ↓
9. Volte para composição
```

## 🔗 Relação com Outros Princípios

- **SRP**: Composição naturalmente promove responsabilidade única
- **OCP**: Composição facilita extensão sem modificação
- **LSP**: Menos hierarquia = menos problemas de substituibilidade
- **DIP**: Composição funciona bem com inversão de dependência

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `ComposicaoSobreHeranca.java` - Implementação com composição vs herança

## 🎯 Exercícios Práticos

1. **Análise**: Identifique hierarquias de herança profundas em seu código
2. **Refatoração**: Converta herança em composição
3. **Comparação**: Compare flexibilidade antes/depois
4. **Design**: Próximo problema - tente composição primeiro

## 📖 Leituras Recomendadas

1. **"Design Patterns"** - Gang of Four (1994) - Formulação original
2. **"Effective Java"** - Joshua Bloch (2018) - Item 18: "Favor composition over inheritance"
3. **"Head First Design Patterns"** - Freeman & Freeman (2004) - Composição explicada

## 💭 Citações Inspiradoras

> "Inheritance is often useful, but more often than not, it is not the right tool." - Joshua Bloch

> "Prefer composition over inheritance. It is more flexible and reduces coupling." - Gang of Four

> "Favor object composition over class inheritance as it leads to simpler, more flexible designs." - Robert C. Martin

---

**Lembre-se:** Composição não significa "nunca use herança". Significa usar herança criteriosamente para relações IS-A verdadeiras e estáveis, e preferir composição para construir comportamentos flexíveis e reutilizáveis. Composição oferece flexibilidade que herança simplesmente não pode fornecer.
