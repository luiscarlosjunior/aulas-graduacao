# Programação Orientada a Objetos (POO)

Esta seção aborda os fundamentos da Programação Orientada a Objetos em Java, um paradigma essencial para o desenvolvimento de software moderno.

## 🏛️ História da Programação Orientada a Objetos

### 📜 Como Era Antes da POO: Programação Procedural

Antes da POO, o paradigma dominante era a **programação procedural** (ou estruturada), onde:

#### Características da Programação Procedural:
- **Código organizado em funções/procedimentos**
- **Dados separados das funções** que os manipulam
- **Fluxo de execução linear** (top-down)
- **Foco nos procedimentos** e não nos dados
- **Variáveis globais** compartilhadas entre funções

#### Exemplo: Sistema Bancário Procedural (Antes da POO)
```c
// Dados globais (separados das funções)
struct Conta {
    int numero;
    char titular[50];
    float saldo;
};

struct Conta contas[1000];
int totalContas = 0;

// Funções separadas dos dados
void criarConta(int numero, char* titular) {
    contas[totalContas].numero = numero;
    strcpy(contas[totalContas].titular, titular);
    contas[totalContas].saldo = 0.0;
    totalContas++;
}

void depositar(int numeroConta, float valor) {
    // Buscar conta no array global
    for(int i = 0; i < totalContas; i++) {
        if(contas[i].numero == numeroConta) {
            contas[i].saldo += valor;
            break;
        }
    }
}

void sacar(int numeroConta, float valor) {
    // Código duplicado para buscar conta
    for(int i = 0; i < totalContas; i++) {
        if(contas[i].numero == numeroConta) {
            if(contas[i].saldo >= valor) {
                contas[i].saldo -= valor;
            }
            break;
        }
    }
}
```

#### Problemas da Programação Procedural:

| Problema | Descrição | Impacto |
|----------|-----------|---------|
| **Dados desprotegidos** | Qualquer função pode modificar variáveis globais | Bugs difíceis de rastrear |
| **Código duplicado** | Mesma lógica repetida em várias funções | Dificulta manutenção |
| **Baixa reutilização** | Funções específicas para cada contexto | Reescrita constante |
| **Complexidade crescente** | Sistemas grandes se tornam ingerenciáveis | Projetos abandonados |
| **Acoplamento forte** | Mudança em uma função afeta muitas outras | Medo de mexer no código |

### 🌟 O Nascimento da POO: Uma Revolução

#### Timeline Histórica da POO:

| Ano | Marco | Importância |
|-----|-------|-------------|
| **1967** | **Simula 67** (Ole-Johan Dahl, Kristen Nygaard) | Primeira linguagem com classes e objetos |
| **1972** | **Smalltalk** (Alan Kay, Xerox PARC) | Consolidou princípios fundamentais da POO |
| **1980** | **C++** (Bjarne Stroustrup) | Trouxe POO para o mainstream |
| **1991** | **Python** (Guido van Rossum) | POO acessível e pythônica |
| **1995** | **Java** (James Gosling, Sun) | Popularizou POO com "write once, run anywhere" |
| **2000** | **C#** (Microsoft) | POO na plataforma .NET |

#### O Problema que a POO Resolveu:
A POO surgiu para resolver a **"Crise do Software"** dos anos 1960-70:
- Projetos atrasados e acima do orçamento
- Software com muitos bugs
- Código impossível de manter
- Sistemas que não conseguiam crescer

### 🔄 Comparação: Antes vs Depois da POO

#### Sistema Bancário com POO (Depois)
```java
// Dados e comportamentos unidos em uma classe
public class ContaBancaria {
    // Dados protegidos (private)
    private int numero;
    private String titular;
    private double saldo;
    
    // Construtor
    public ContaBancaria(int numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }
    
    // Comportamentos associados aos dados
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }
    
    public boolean sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
}

// Uso simples e intuitivo
ContaBancaria conta = new ContaBancaria(123, "João");
conta.depositar(1000.0);
conta.sacar(100.0);
```

#### Principais Diferenças:

| Aspecto | Antes (Procedural) | Depois (POO) |
|---------|-------------------|--------------|
| **Organização** | Funções separadas dos dados | Dados e métodos unidos em classes |
| **Proteção** | Dados globais expostos | Encapsulamento com private/public |
| **Reutilização** | Copiar e colar código | Herança e composição |
| **Manutenção** | Mudanças afetam todo sistema | Mudanças localizadas nas classes |
| **Legibilidade** | `depositar(conta, 100)` | `conta.depositar(100)` |

### 🏭 Impacto na Indústria de Software

#### Transformações Revolucionárias:

**1. Produtividade de Desenvolvimento**
- **Antes**: 6-12 meses para sistemas simples
- **Depois**: Frameworks e bibliotecas reduzem tempo para semanas
- **Exemplo**: Desenvolvimento web com Spring Boot vs CGI puro

**2. Qualidade do Software**
- **Antes**: 50-100 bugs por 1000 linhas de código
- **Depois**: 1-10 bugs por 1000 linhas com POO bem aplicada
- **Motivo**: Encapsulamento e modularização

**3. Tamanho dos Projetos**
- **Antes**: Limite prático de ~100.000 linhas
- **Depois**: Sistemas com milhões de linhas (Windows, Android)
- **Exemplo**: Linux kernel - 30+ milhões de linhas

**4. Reutilização de Código**
- **Antes**: Reescrita constante de funcionalidades básicas
- **Depois**: Ecossistemas de bibliotecas e frameworks
- **Impacto**: Explosão do desenvolvimento de software

#### Setores Transformados pela POO:

| Setor | Impacto | Exemplo |
|-------|---------|---------|
| **Games** | Permitiu jogos complexos | Engines como Unity, Unreal |
| **Web** | Frameworks robustos | Spring, Django, React |
| **Mobile** | Apps sophisticados | Android SDK, iOS frameworks |
| **Empresarial** | Sistemas ERP/CRM | SAP, Salesforce |
| **Financeiro** | Trading de alta frequência | Sistemas bancários modernos |

#### Empresas que Cresceram com POO:
- **Sun Microsystems**: Java revolucionou desenvolvimento corporativo
- **Microsoft**: .NET e C# dominaram desenvolvimento Windows
- **Google**: Android baseado em Java
- **Facebook**: PHP orientado a objetos para redes sociais

### 🎯 Por Que POO Foi Um Sucesso?

#### Benefícios Práticos Comprovados:

**1. Modelagem Natural**
```java
// POO modela o mundo real naturalmente
Carro meuCarro = new Carro("Toyota", "Corolla");
meuCarro.acelerar();
meuCarro.frear();

// vs procedural antinatural
acelerar_carro(carro_id);
frear_carro(carro_id);
```

**2. Colaboração em Equipe**
- **Antes**: Um programador por projeto (risk único)
- **Depois**: Equipes de centenas trabalham em paralelo
- **Como**: Cada desenvolvedor trabalha em classes independentes

**3. Economia de Escala**
- **Frameworks**: Spring, Hibernate, Angular
- **Libraries**: Milhares disponíveis (Maven, NPM)
- **Padrões**: Design Patterns padronizaram soluções

#### Números do Impacto:
- **90%** dos sistemas corporativos usam POO
- **$500 bilhões** em economia por reutilização de código
- **10x** aumento na produtividade média de desenvolvimento
- **95%** das linguagens modernas suportam POO

## 🎯 Objetivos

- Compreender os conceitos fundamentais da POO
- Aplicar os princípios de encapsulamento, herança e polimorfismo
- Trabalhar com interfaces e classes abstratas
- Desenvolver código reutilizável e bem estruturado

## 📋 Conceitos Abordados

### [01 - Classes e Objetos](01-classes-objetos/)
Definição de classes, criação de objetos, atributos e métodos.

### [02 - Encapsulamento](02-encapsulamento/)
Modificadores de acesso, getters, setters e proteção de dados.

### [03 - Herança](03-heranca/)
Extensão de classes, super, sobrescrita de métodos.

### [04 - Polimorfismo](04-polimorfismo/)
Sobrecarga, sobrescrita, casting e dynamic binding.

### [05 - Interfaces](05-interfaces/)
Contratos, implementação múltipla, métodos default.

### [06 - Classes Abstratas](06-classes-abstratas/)
Classes base não instanciáveis, métodos abstratos.

## 🎯 Princípios da POO

### 1. **Encapsulamento**
Ocultação dos detalhes internos e proteção dos dados através de modificadores de acesso.

### 2. **Herança**
Reutilização de código através da extensão de classes existentes.

### 3. **Polimorfismo**
Capacidade de objetos de diferentes tipos responderem à mesma interface.

### 4. **Abstração**
Simplificação de sistemas complexos através de modelos conceituais.

## 🚀 Como Estudar

1. **Sequência**: Siga a ordem numérica das pastas
2. **Teoria**: Leia os conceitos antes dos exemplos
3. **Prática**: Execute e modifique os exemplos
4. **Aplicação**: Implemente os exercícios propostos

## 💡 Boas Práticas

- Use nomes descritivos para classes e métodos
- Mantenha classes pequenas e focadas (Single Responsibility)
- Prefira composição sobre herança quando apropriado
- Utilize interfaces para definir contratos
- Implemente toString(), equals() e hashCode() quando necessário

## 📚 Recursos Adicionais

- [Documentação Oracle - OOP Concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [Effective Java - Joshua Bloch](https://www.oracle.com/java/technologies/javase/effectivejava.html)

---

**Anterior**: [Fundamentos](../01-fundamentos/) | **Próximo**: [Conceitos Intermediários](../03-conceitos-intermediarios/)