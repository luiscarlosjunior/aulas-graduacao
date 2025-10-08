# Classes Abstratas - Base Incompleta para Especialização

## 🎯 O que são Classes Abstratas?

Uma **classe abstrata** é uma classe que:

- **Não pode ser instanciada** diretamente (não pode criar objetos dela)
- **Serve como base** para outras classes através de herança
- **Pode ter métodos abstratos** (sem implementação) que devem ser implementados pelas subclasses
- **Pode ter métodos concretos** (com implementação) compartilhados pelas subclasses
- **Combina abstração com implementação** concreta

**Analogia**: Como um projeto arquitetônico de uma casa - define a estrutura geral (quartos, banheiros, cozinha), mas deixa detalhes específicos (acabamentos, cores, móveis) para serem definidos na construção real.

## 🏗️ Sintaxe e Características

### Definindo uma Classe Abstrata

```java
public abstract class Veiculo {
    // Atributos comuns (podem ser de qualquer visibilidade)
    protected String marca;
    protected String modelo;
    protected int ano;
    private boolean ligado;
    
    // Construtor (pode existir, mesmo sendo abstrata)
    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.ligado = false;
    }
    
    // Métodos concretos (implementação compartilhada)
    public void ligar() {
        ligado = true;
        System.out.println(marca + " " + modelo + " ligado!");
    }
    
    public void desligar() {
        ligado = false;
        System.out.println(marca + " " + modelo + " desligado!");
    }
    
    public boolean isLigado() {
        return ligado;
    }
    
    // Métodos abstratos (devem ser implementados pelas subclasses)
    public abstract void acelerar();
    public abstract void frear();
    public abstract double calcularConsumo(double distancia);
    public abstract String getTipoVeiculo();
    
    // Método concreto que usa métodos abstratos
    public void exibirInfo() {
        System.out.println("=== " + getTipoVeiculo() + " ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Status: " + (ligado ? "Ligado" : "Desligado"));
    }
}
```

### Implementando Classes Filhas

```java
// Classe filha 1: Carro
public class Carro extends Veiculo {
    private int numeroPortas;
    private String tipoCombustivel;
    
    public Carro(String marca, String modelo, int ano, int portas, String combustivel) {
        super(marca, modelo, ano);  // Chama construtor da classe abstrata
        this.numeroPortas = portas;
        this.tipoCombustivel = combustivel;
    }
    
    // Implementação obrigatória dos métodos abstratos
    @Override
    public void acelerar() {
        if (isLigado()) {
            System.out.println("Carro acelerando suavemente...");
        } else {
            System.out.println("Ligue o carro primeiro!");
        }
    }
    
    @Override
    public void frear() {
        System.out.println("Carro freando com segurança");
    }
    
    @Override
    public double calcularConsumo(double distancia) {
        // Carro consome mais na cidade
        return distancia / 12.0; // 12 km/l
    }
    
    @Override
    public String getTipoVeiculo() {
        return "Carro";
    }
    
    // Métodos específicos da classe
    public void abrirPorta() {
        System.out.println("Abrindo uma das " + numeroPortas + " portas");
    }
}

// Classe filha 2: Motocicleta
public class Motocicleta extends Veiculo {
    private int cilindradas;
    private boolean temBau;
    
    public Motocicleta(String marca, String modelo, int ano, int cilindradas) {
        super(marca, modelo, ano);
        this.cilindradas = cilindradas;
        this.temBau = false;
    }
    
    @Override
    public void acelerar() {
        if (isLigado()) {
            System.out.println("Moto acelerando rapidamente!");
        } else {
            System.out.println("Ligue a moto primeiro!");
        }
    }
    
    @Override
    public void frear() {
        System.out.println("Moto freando com cuidado");
    }
    
    @Override
    public double calcularConsumo(double distancia) {
        // Moto é mais econômica
        return distancia / 25.0; // 25 km/l
    }
    
    @Override
    public String getTipoVeiculo() {
        return "Motocicleta";
    }
    
    // Métodos específicos
    public void empinar() {
        if (isLigado()) {
            System.out.println("Moto empinando! (" + cilindradas + " cilindradas)");
        }
    }
}
```

## 🎨 Exemplo Prático: Sistema de Formas Geométricas

```java
// Classe abstrata base
public abstract class Forma {
    protected String cor;
    protected double x, y; // Posição
    
    public Forma(String cor, double x, double y) {
        this.cor = cor;
        this.x = x;
        this.y = y;
    }
    
    // Métodos concretos compartilhados
    public void mover(double novoX, double novoY) {
        this.x = novoX;
        this.y = novoY;
        System.out.println("Forma movida para (" + x + ", " + y + ")");
    }
    
    public void pintar(String novaCor) {
        this.cor = novaCor;
        System.out.println("Forma pintada de " + cor);
    }
    
    public String getCor() {
        return cor;
    }
    
    // Métodos abstratos (cada forma calcula diferente)
    public abstract double calcularArea();
    public abstract double calcularPerimetro();
    public abstract void desenhar();
    
    // Método template (usa métodos abstratos)
    public void exibirInformacoes() {
        System.out.println("=== " + getClass().getSimpleName() + " ===");
        System.out.println("Cor: " + cor);
        System.out.println("Posição: (" + x + ", " + y + ")");
        System.out.println("Área: " + calcularArea());
        System.out.println("Perímetro: " + calcularPerimetro());
        desenhar();
    }
}

// Implementações específicas
public class Retangulo extends Forma {
    private double largura, altura;
    
    public Retangulo(String cor, double x, double y, double largura, double altura) {
        super(cor, x, y);
        this.largura = largura;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return largura * altura;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }
    
    @Override
    public void desenhar() {
        System.out.println("Desenhando retângulo " + largura + "x" + altura);
    }
}

public class Circulo extends Forma {
    private double raio;
    
    public Circulo(String cor, double x, double y, double raio) {
        super(cor, x, y);
        this.raio = raio;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
    
    @Override
    public void desenhar() {
        System.out.println("Desenhando círculo com raio " + raio);
    }
}

public class Triangulo extends Forma {
    private double lado1, lado2, lado3;
    
    public Triangulo(String cor, double x, double y, double l1, double l2, double l3) {
        super(cor, x, y);
        this.lado1 = l1;
        this.lado2 = l2;
        this.lado3 = l3;
    }
    
    @Override
    public double calcularArea() {
        // Fórmula de Heron
        double s = calcularPerimetro() / 2;
        return Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
    }
    
    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }
    
    @Override
    public void desenhar() {
        System.out.println("Desenhando triângulo com lados " + lado1 + ", " + lado2 + ", " + lado3);
    }
}
```

### Sistema de Uso

```java
public class TesteFormas {
    public static void main(String[] args) {
        // Array polimórfico de formas
        Forma[] formas = {
            new Retangulo("Azul", 0, 0, 5, 3),
            new Circulo("Vermelho", 10, 10, 2.5),
            new Triangulo("Verde", 5, 5, 3, 4, 5)
        };
        
        System.out.println("=== SISTEMA DE FORMAS GEOMÉTRICAS ===\n");
        
        double areaTotal = 0;
        for (Forma forma : formas) {
            forma.exibirInformacoes();
            areaTotal += forma.calcularArea();
            
            // Operações específicas baseadas no tipo
            if (forma instanceof Circulo) {
                System.out.println("Círculo detectado!");
            }
            
            System.out.println();
        }
        
        System.out.println("Área total de todas as formas: " + areaTotal);
        
        // Testando movimentação
        System.out.println("\n=== MOVENDO FORMAS ===");
        for (Forma forma : formas) {
            forma.mover(forma.x + 1, forma.y + 1);
        }
    }
}
```

## 🌟 Por que Classes Abstratas são Importantes em POO?

Classes abstratas são um dos pilares da **Programação Orientada a Objetos** por várias razões fundamentais:

### 1. **Reutilização de Código (DRY Principle)**
Evita duplicação de código compartilhando implementação comum entre classes relacionadas.

```java
// ❌ Sem classe abstrata - código duplicado
class Cachorro {
    String nome;
    void comer() { System.out.println("Comendo..."); } // Duplicado
    void latir() { System.out.println("Au au!"); }
}

class Gato {
    String nome;
    void comer() { System.out.println("Comendo..."); } // Duplicado
    void miar() { System.out.println("Miau!"); }
}

// ✅ Com classe abstrata - código compartilhado
abstract class Animal {
    String nome;
    void comer() { System.out.println(nome + " está comendo..."); } // Uma vez só!
    abstract void emitirSom(); // Comportamento específico
}

class Cachorro extends Animal {
    void emitirSom() { System.out.println("Au au!"); }
}

class Gato extends Animal {
    void emitirSom() { System.out.println("Miau!"); }
}
```

### 2. **Polimorfismo e Flexibilidade**
Permite tratar diferentes objetos de forma uniforme através de uma referência comum.

```java
Animal[] animais = {
    new Cachorro("Rex"),
    new Gato("Mimi"),
    new Papagaio("Loro")
};

// Código genérico funciona para todos!
for (Animal animal : animais) {
    animal.comer();        // Método comum
    animal.emitirSom();    // Comportamento específico
}
```

### 3. **Garantia de Contrato**
Força subclasses a implementarem métodos essenciais, garantindo que toda classe derivada terá determinado comportamento.

```java
abstract class ProcessadorPagamento {
    // Todas as subclasses DEVEM implementar estes métodos
    abstract boolean validarPagamento();
    abstract void processarTransacao();
    abstract void emitirComprovante();
}
```

### 4. **Encapsulamento de Lógica Complexa**
Permite ocultar complexidade na classe base, expondo apenas o necessário para subclasses.

```java
abstract class ConexaoBancoDados {
    // Lógica complexa protegida na classe abstrata
    protected void abrirConexao() { /* lógica complexa */ }
    protected void fecharConexao() { /* lógica complexa */ }
    
    // Template method público - fluxo seguro
    public final void executarOperacao() {
        abrirConexao();
        executar(); // Subclasse implementa apenas isso
        fecharConexao();
    }
    
    protected abstract void executar();
}
```

### 5. **Design Patterns**
Base fundamental para muitos padrões de projeto (Template Method, Factory Method, Strategy adaptado).

---

## 🎯 Contextos de Aplicação - Quando e Onde Usar?

### ✅ Cenário 1: Hierarquia Natural com Comportamento Compartilhado

**Contexto**: Sistema de RH com diferentes tipos de funcionários

```java
abstract class Funcionario {
    protected String nome;
    protected String cpf;
    
    // Comportamento comum - todos funcionários têm
    public void registrarPonto() { /* código comum */ }
    public void receberBeneficios() { /* código comum */ }
    
    // Comportamento específico - varia por tipo
    public abstract double calcularSalario();
    public abstract String getCargoDescricao();
}

class CLT extends Funcionario { /* implementação específica */ }
class PJ extends Funcionario { /* implementação específica */ }
class Estagiario extends Funcionario { /* implementação específica */ }
```

**Por que aqui?** Todos são funcionários (relação "É-UM"), compartilham código comum, mas têm cálculos salariais diferentes.

---

### ✅ Cenário 2: Fluxo de Processo Padronizado

**Contexto**: Sistema de processamento de pedidos e-commerce

```java
abstract class ProcessadorPedido {
    // Template Method - fluxo sempre igual
    public final void processarPedido(Pedido pedido) {
        validarPedido(pedido);        // Comum
        verificarEstoque(pedido);     // Comum
        calcularValorFinal(pedido);   // Específico
        processarPagamento(pedido);   // Específico
        enviarConfirmacao(pedido);    // Comum
    }
    
    protected void validarPedido(Pedido p) { /* implementação comum */ }
    protected void verificarEstoque(Pedido p) { /* implementação comum */ }
    protected void enviarConfirmacao(Pedido p) { /* implementação comum */ }
    
    // Métodos que variam por tipo de pedido
    protected abstract void calcularValorFinal(Pedido pedido);
    protected abstract void processarPagamento(Pedido pedido);
}

class PedidoNacional extends ProcessadorPedido { /* sem imposto import */ }
class PedidoInternacional extends ProcessadorPedido { /* com imposto import */ }
class PedidoAssinatura extends ProcessadorPedido { /* recorrente */ }
```

**Por que aqui?** Fluxo do processo é sempre o mesmo, mas detalhes de cálculo e pagamento variam.

---

### ✅ Cenário 3: Compartilhamento de Estado e Comportamento

**Contexto**: Aplicativo de desenho com formas geométricas

```java
abstract class Forma {
    protected String cor;
    protected double x, y; // Posição - ESTADO compartilhado
    protected boolean selecionada;
    
    // Comportamento comum que usa o estado
    public void mover(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void selecionar() { selecionada = true; }
    public void pintar(String novaCor) { cor = novaCor; }
    
    // Cada forma calcula diferente
    public abstract double calcularArea();
    public abstract void desenhar(Canvas canvas);
}
```

**Por que aqui?** Todas as formas têm posição, cor e podem ser movidas/selecionadas da mesma forma, mas desenho e cálculo de área são específicos.

---

### ❌ Cenário 4: Quando NÃO Usar Classes Abstratas

**Contexto Incorreto**: Classes sem relação hierárquica

```java
// ❌ ERRADO - não há relação "É-UM"
abstract class Persistivel {
    abstract void salvar();
    abstract void carregar();
}

class Usuario extends Persistivel { }    // Usuario NÃO É Persistivel
class Produto extends Persistivel { }    // Produto NÃO É Persistivel

// ✅ CORRETO - use interface para comportamento
interface Persistivel {
    void salvar();
    void carregar();
}

class Usuario implements Persistivel { }  // Usuario PODE SER persistido
class Produto implements Persistivel { }  // Produto PODE SER persistido
```

**Por que não?** Não há código comum para compartilhar e a relação não é hierárquica natural.

---

### ❌ Cenário 5: Quando Precisa de Múltiplas Heranças

```java
// ❌ IMPOSSÍVEL - Java não permite múltipla herança de classes
class Drone extends Veiculo, Voador { } // ERRO!

// ✅ CORRETO - use interfaces
abstract class Veiculo { }
interface Voador { }
interface Fotografavel { }

class Drone extends Veiculo implements Voador, Fotografavel { }
```

---

## 🔄 Classe Abstrata vs Interface - Comparação Detalhada

| Aspecto | Classe Abstrata | Interface |
|---------|-----------------|-----------|
| **Instanciação** | Não pode ser instanciada | Não pode ser instanciada |
| **Herança** | Herança simples (extends) | Múltipla implementação (implements) |
| **Métodos** | Abstratos + concretos | Abstratos + default + static (Java 8+) |
| **Atributos** | Qualquer tipo e visibilidade | Apenas constantes (public static final) |
| **Construtor** | Pode ter construtor | Não tem construtor |
| **Estado** | Pode ter estado (atributos de instância) | Não pode ter estado |
| **Modificadores** | protected, private, public | Apenas public |
| **Quando usar** | Base comum + código compartilhado | Contratos, múltipla herança |
| **Relação** | "É-UM" (is-a) | "PODE-FAZER" (can-do) |

### 📊 Comparação Prática com Exemplo Real

#### Cenário: Sistema de Pagamento

```java
// ========================================
// CLASSE ABSTRATA - quando há código comum
// ========================================
abstract class MetodoPagamento {
    protected double valor;
    protected String dataPagamento;
    
    // Construtor - interfaces não têm!
    public MetodoPagamento(double valor) {
        this.valor = valor;
        this.dataPagamento = LocalDate.now().toString();
    }
    
    // Método concreto - comportamento comum
    public void registrarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " registrado em " + dataPagamento);
    }
    
    // Método abstrato - cada forma processa diferente
    public abstract boolean processar();
    public abstract void emitirComprovante();
}

class CartaoCredito extends MetodoPagamento {
    private String numeroCartao;
    
    public CartaoCredito(double valor, String numeroCartao) {
        super(valor); // Usa construtor da classe abstrata
        this.numeroCartao = numeroCartao;
    }
    
    @Override
    public boolean processar() {
        // Lógica específica de cartão
        return validarCartao() && autorizarOperacao();
    }
    
    @Override
    public void emitirComprovante() {
        System.out.println("Comprovante: Cartão final " + numeroCartao.substring(12));
    }
}

// ========================================
// INTERFACE - quando é apenas contrato
// ========================================
interface Auditavel {
    void registrarLog();
    void enviarParaAuditoria();
}

interface Reembolsavel {
    boolean podeReembolsar();
    void processarReembolso();
}

// Uma classe pode implementar múltiplas interfaces!
class PagamentoPix extends MetodoPagamento implements Auditavel, Reembolsavel {
    public PagamentoPix(double valor) {
        super(valor);
    }
    
    @Override
    public boolean processar() { /* implementação */ return true; }
    
    @Override
    public void emitirComprovante() { /* implementação */ }
    
    // Da interface Auditavel
    @Override
    public void registrarLog() { /* implementação */ }
    
    @Override
    public void enviarParaAuditoria() { /* implementação */ }
    
    // Da interface Reembolsavel
    @Override
    public boolean podeReembolsar() { return true; }
    
    @Override
    public void processarReembolso() { /* implementação */ }
}
```

### 🎯 Regras de Decisão

**Use CLASSE ABSTRATA quando:**
- ✅ Há código ou estado que precisa ser compartilhado
- ✅ As classes têm relação hierárquica natural (É-UM)
- ✅ Precisa de construtor ou atributos de instância
- ✅ Quer controlar níveis de acesso (protected, private)
- ✅ Implementa Template Method Pattern

**Use INTERFACE quando:**
- ✅ Define apenas um contrato (o QUE fazer, não COMO)
- ✅ Precisa de múltipla herança de comportamento
- ✅ Classes não relacionadas implementarão o mesmo comportamento
- ✅ A relação é "PODE-FAZER" e não "É-UM"
- ✅ Quer máxima flexibilidade

**Use AMBOS quando:**
- ✅ Interface define o contrato público
- ✅ Classe abstrata fornece implementação base
- ✅ Permite flexibilidade + reutilização

```java
interface Autenticavel {
    boolean autenticar(String senha);
}

abstract class Usuario implements Autenticavel {
    protected String username;
    protected String senhaHash;
    
    // Implementação padrão da interface
    @Override
    public boolean autenticar(String senha) {
        return hashSenha(senha).equals(senhaHash);
    }
    
    private String hashSenha(String senha) { /* lógica comum */ return ""; }
    
    // Comportamento específico
    public abstract void carregarPermissoes();
}
```

## 💡 Quando Usar Classes Abstratas?

### ✅ Use Classes Abstratas Quando:

1. **Código compartilhado**: Várias classes precisam dos mesmos métodos/atributos
2. **Herança natural**: Existe uma relação hierárquica clara
3. **Controle de acesso**: Precisa de diferentes níveis de visibilidade
4. **Estado compartilhado**: Precisa de atributos comuns entre subclasses

### ✅ Exemplo de Bom Uso:
```java
abstract class Animal {
    protected String nome;
    protected int idade;
    
    // Código comum
    public void dormir() { /* implementação comum */ }
    
    // Comportamento específico
    abstract void emitirSom();
}
```

### ❌ Evite Quando:
- Não há código comum para compartilhar (use interface)
- Precisa de múltipla herança (use interfaces)
- Classes não têm relação hierárquica natural

## 🎯 Template Method Pattern

Classes abstratas são ideais para o padrão Template Method:

```java
public abstract class AlgoritmoOrdenacao {
    // Template method (algoritmo geral)
    public final void ordenar(int[] array) {
        System.out.println("Iniciando ordenação...");
        
        long inicio = System.currentTimeMillis();
        algoritmoEspecifico(array);
        long fim = System.currentTimeMillis();
        
        System.out.println("Ordenação concluída em " + (fim - inicio) + "ms");
        exibirResultado(array);
    }
    
    // Método abstrato - cada subclasse implementa seu algoritmo
    protected abstract void algoritmoEspecifico(int[] array);
    
    // Método concreto compartilhado
    private void exibirResultado(int[] array) {
        System.out.print("Resultado: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

public class BubbleSort extends AlgoritmoOrdenacao {
    @Override
    protected void algoritmoEspecifico(int[] array) {
        // Implementação do Bubble Sort
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
```

## ⚠️ Cuidados e Boas Práticas

### ✅ Design Thoughtful
```java
// ✅ Abstração bem definida
abstract class ProcessadorDados {
    public final void processar() {
        carregar();
        validar();
        executar();
        salvar();
    }
    
    protected abstract void executar(); // Parte variável
    private void carregar() { /* comum */ }
    private void salvar() { /* comum */ }
}
```

### ⚠️ Evite Hierarquias Profundas
```java
// ❌ Muito profundo
abstract class A { }
abstract class B extends A { }
abstract class C extends B { }
class D extends C { } // Difícil de entender

// ✅ Mais simples
abstract class Base { }
class Implementacao extends Base { }
```

## 🚀 Exercícios Práticos

1. **Sistema de Funcionários**
   - Classe abstrata: `Funcionario` (nome, salário base, calcularSalario())
   - Subclasses: `Gerente`, `Vendedor`, `Desenvolvedor`

2. **Jogos**
   - Classe abstrata: `Jogo` (nome, iniciar(), jogar(), terminar())
   - Subclasses: `JogoCartas`, `JogoTabuleiro`, `JogoEletronico`

3. **Processamento de Arquivos**
   - Classe abstrata: `ProcessadorArquivo` (abrir(), processar(), fechar())
   - Subclasses: `ProcessadorTexto`, `ProcessadorImagem`, `ProcessadorVideo`

---

## 📂 Exemplos Completos com Diagramas de Classes

Esta seção contém implementações completas de sistemas usando classes abstratas, acompanhadas de diagramas UML para melhor compreensão da arquitetura.

### 🎯 Exemplo 1: Sistema de Funcionários

Um sistema completo de gerenciamento de funcionários demonstrando diferentes tipos de cálculo salarial.

#### Diagrama de Classes:

![Diagrama Sistema de Funcionários](img/sistema-funcionarios.png)

#### Estrutura:
- **Classe Abstrata**: `Funcionario` - Define estrutura base com atributos e comportamentos comuns
- **Subclasses**: 
  - `Gerente` - Recebe bônus fixo e adicional por nível
  - `Vendedor` - Ganha comissão sobre vendas
  - `Desenvolvedor` - Recebe bônus por projeto concluído

#### Conceitos Demonstrados:
- ✅ Template Method Pattern
- ✅ Polimorfismo através de abstração
- ✅ Encapsulamento de lógicas específicas
- ✅ Reutilização de código comum

**[📁 Ver código completo](exemplos/)**

Para executar:
```bash
cd exemplos/
javac Funcionario.java Gerente.java Vendedor.java Desenvolvedor.java TesteSistemaFuncionarios.java
java TesteSistemaFuncionarios
```

---

### 🎮 Exemplo 2: Sistema de Jogos

Um sistema de gerenciamento de diferentes tipos de jogos, implementando o Template Method Pattern para garantir fluxo consistente.

#### Diagrama de Classes:

![Diagrama Sistema de Jogos](img/sistema-jogos.png)

#### Estrutura:
- **Classe Abstrata**: `Jogo` - Define o fluxo de execução (iniciar → jogar → terminar)
- **Subclasses**:
  - `JogoCartas` - Baralho, rodadas e embaralhamento
  - `JogoTabuleiro` - Dados, tabuleiro e turnos
  - `JogoEletronico` - Níveis, pontuação e plataforma

#### Conceitos Demonstrados:
- ✅ Template Method Pattern (método final)
- ✅ Fluxo de execução consistente
- ✅ Implementações específicas por tipo
- ✅ Array polimórfico para gerenciar diferentes tipos

**[📁 Ver código completo](exemplos/)**

Para executar:
```bash
cd exemplos/
javac Jogo.java JogoCartas.java JogoTabuleiro.java JogoEletronico.java TesteSistemaJogos.java
java TesteSistemaJogos
```

---

### 🏦 Exemplo 3: Sistema Bancário (NOVO!)

Um sistema completo de contas bancárias demonstrando diferentes tipos de cálculo de rendimento, taxas e operações.

#### Estrutura:
- **Classe Abstrata**: `ContaBancaria` - Define operações bancárias comuns
- **Subclasses**:
  - `ContaCorrente` - Limite especial, taxa de manutenção, taxa por saque
  - `ContaPoupanca` - Rendimento mensal, saques gratuitos limitados
  - `ContaInvestimento` - Alto rendimento, taxa progressiva, saldo mínimo

#### Conceitos Demonstrados:
- ✅ Métodos concretos compartilhados (depositar, transferir)
- ✅ Métodos abstratos implementados diferentemente (calcularRendimento)
- ✅ Template Method Pattern (exibirExtrato)
- ✅ Polimorfismo em operações financeiras
- ✅ Encapsulamento de regras de negócio
- ✅ Sobrescrita de métodos para comportamentos específicos

#### Por que Classes Abstratas neste caso?

1. **Código Compartilhado**: Operações como `depositar()`, `sacar()` e `transferir()` são comuns a todas as contas
2. **Estado Comum**: Todas as contas têm `saldo`, `titular`, `numeroConta`
3. **Variação Controlada**: Cada tipo calcula rendimento e taxas de forma diferente
4. **Hierarquia Natural**: Relação "É-UM" - ContaCorrente É UMA ContaBancaria

**[📁 Ver código completo](exemplos/)**

Para executar:
```bash
cd exemplos/
javac ContaBancaria.java ContaCorrente.java ContaPoupanca.java ContaInvestimento.java TesteSistemaBancario.java
java TesteSistemaBancario
```

**Saída esperada**: Sistema demonstra operações bancárias, transferências entre contas, aplicação de rendimentos e taxas específicas de cada tipo.

---

### 📄 Exemplo 4: Sistema de Processamento de Documentos (NOVO!)

Sistema que processa diferentes tipos de documentos seguindo um fluxo padronizado (Template Method Pattern avançado).

#### Estrutura:
- **Classe Abstrata**: `ProcessadorDocumento` - Define fluxo: validar → abrir → ler → processar → fechar
- **Subclasses**:
  - `ProcessadorPDF` - Extração de texto, páginas, metadados EXIF
  - `ProcessadorExcel` - Leitura de abas, fórmulas, estatísticas
  - `ProcessadorImagem` - Análise de dimensões, qualidade, filtros

#### Conceitos Demonstrados:
- ✅ Template Method Pattern com método `final` (fluxo fixo)
- ✅ Garantia de sequência de operações
- ✅ Validação em múltiplas camadas
- ✅ Operações específicas por tipo de documento
- ✅ Exportação polimórfica para diferentes formatos
- ✅ Factory Method Pattern combinado

#### Por que Classes Abstratas neste caso?

1. **Fluxo Padronizado**: Todos documentos devem seguir: validar → abrir → ler → processar → fechar
2. **Código de Infraestrutura**: Lógica de validação e fechamento é comum
3. **Template Method**: Método `processar()` é `final` - garante que ninguém altere o fluxo
4. **Variação Específica**: Cada tipo de documento tem forma única de ler e processar

**[📁 Ver código completo](exemplos/)**

Para executar:
```bash
cd exemplos/
javac ProcessadorDocumento.java ProcessadorPDF.java ProcessadorExcel.java ProcessadorImagem.java TesteProcessadorDocumentos.java
java TesteProcessadorDocumentos
```

**Saída esperada**: Sistema processa PDF, Excel e Imagem seguindo sempre o mesmo fluxo, mas com implementações específicas para cada tipo.

---

## 📝 Exercícios para Prática

Acesse o diretório [exercicios/](exercicios/) para encontrar:

1. **Exercício 1**: Sistema de Veículos de Transporte
   - Implementar `Onibus`, `Taxi`, `VanEscolar`
   - Cálculo de tarifas específicas por tipo

2. **Exercício 2**: Sistema de Produtos E-commerce
   - Categorias: Eletrônico, Alimentício, Vestuário
   - Diferentes formas de cálculo de preço e desconto

3. **Exercício 3**: Sistema de Investimentos Financeiros
   - Tipos: Poupança, CDB, Ações
   - Cálculo de rendimento específico

4. **Exercício 4**: Sistema de Notificações
   - Canais: Email, SMS, Push
   - Template Method para fluxo de envio

5. **Exercício 5**: Sistema de Relatórios (Avançado)
   - Formatos: HTML, PDF, CSV, JSON
   - Geração padronizada com formatações específicas

**[📋 Ver detalhes dos exercícios](exercicios/README.md)**

---

## 🔗 Navegação

[← 05 - Interfaces](../05-interfaces/) | [📚 Voltar ao Índice](../README.md)

---

## 📚 Material de Apoio

### Arquivos Disponíveis:
- `exemplos/` - Implementações completas com código executável
- `exercicios/` - Desafios práticos com diferentes níveis de dificuldade
- `img/` - Diagramas UML dos sistemas
  - `sistema-funcionarios.png` - Diagrama do sistema de funcionários
  - `sistema-jogos.png` - Diagrama do sistema de jogos

### Recursos dos Exemplos:
- ✨ Código totalmente comentado e documentado
- ✨ Diagramas UML para visualização da arquitetura
- ✨ Classes de teste demonstrando uso completo
- ✨ Output formatado para melhor compreensão

---

**💡 Lembre-se**: Classes abstratas são para compartilhar código comum e estabelecer uma base sólida para especialização. Use quando há uma hierarquia natural e código que pode ser reutilizado!