# Abstração - Simplificando Complexidade

## 🎯 O que é Abstração?

**Abstração** é o princípio de esconder detalhes complexos e mostrar apenas o essencial:
- **Classes Abstratas**: não podem ser instanciadas diretamente
- **Métodos Abstratos**: devem ser implementados pelas classes filhas
- **Interfaces**: contratos que classes devem seguir
- **Simplificação**: foca no "o quê" em vez do "como"

**Analogia**: Como dirigir um carro - você usa volante, pedais e câmbio sem precisar entender o motor internamente.

## 🔧 Implementações de Abstração

### 1. **Classes Abstratas**
```java
public abstract class Veiculo {
    protected String marca;
    
    // Método concreto (implementado)
    public void ligar() {
        System.out.println("Veículo ligado");
    }
    
    // Método abstrato (deve ser implementado)
    public abstract void acelerar();
}
```

### 2. **Interfaces**
```java
public interface Voador {
    // Constantes (implicitamente public static final)
    int ALTITUDE_MAXIMA = 10000;
    
    // Métodos abstratos (implicitamente public abstract)
    void decolar();
    void voar();
    void aterrissar();
    
    // Método default (Java 8+)
    default void planar() {
        System.out.println("Planando suavemente...");
    }
}
```

### 3. **Implementação**
```java
public class Aviao extends Veiculo implements Voador {
    @Override
    public void acelerar() { /* implementação */ }
    
    @Override
    public void decolar() { /* implementação */ }
    
    @Override
    public void voar() { /* implementação */ }
    
    @Override
    public void aterrissar() { /* implementação */ }
}
```

## 🔑 Diferenças Importantes

| Aspecto | Classe Abstrata | Interface |
|---------|----------------|-----------|
| **Herança** | Apenas 1 (extends) | Múltiplas (implements) |
| **Métodos** | Concretos e abstratos | Abstratos e default |
| **Atributos** | Qualquer tipo | Apenas constantes |
| **Construtor** | Pode ter | Não pode ter |
| **Uso** | Relação "É UM" | Relação "PODE FAZER" |

## 📚 Exemplos Práticos

### [Dispositivo.java](Dispositivo.java)
Interface definindo comportamentos de dispositivos eletrônicos.

### [DispositivoEletronico.java](DispositivoEletronico.java)
Classe abstrata com funcionalidades comuns.

### [Smartphone.java](Smartphone.java)
### [Tablet.java](Tablet.java)
Classes concretas implementando abstração.

### [TesteAbstracao.java](TesteAbstracao.java)
Demonstração completa dos conceitos de abstração.

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar
java TesteAbstracao
```

## 💡 Vantagens da Abstração

1. **Simplicidade**: Esconde complexidade desnecessária
2. **Flexibilidade**: Permite diferentes implementações
3. **Manutenibilidade**: Mudanças internas não afetam código externo
4. **Padronização**: Força estrutura comum entre classes
5. **Polimorfismo**: Permite tratar diferentes objetos uniformemente

## ⚠️ Quando Usar

### Use Classe Abstrata quando:
- Há código comum entre classes relacionadas
- Quer forçar certas implementações
- Precisa de atributos protegidos
- Tem relação "É UM"

### Use Interface quando:
- Quer permitir múltiplas heranças de comportamento
- Define um contrato que diferentes classes podem seguir
- Tem relação "PODE FAZER"
- Quer máxima flexibilidade

## 🔗 Navegação
[← 04 - Polimorfismo](../04-polimorfismo/) | [06 - Exercícios →](../06-exercicios/)