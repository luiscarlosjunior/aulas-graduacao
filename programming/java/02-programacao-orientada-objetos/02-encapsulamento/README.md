# Encapsulamento - Protegendo os Dados da Classe

## 🎯 O que é Encapsulamento?

O **encapsulamento** é um dos pilares fundamentais da Programação Orientada a Objetos que consiste em:

- **Ocultar** os detalhes internos de implementação da classe
- **Proteger** os dados contra modificações inadequadas ou diretas
- **Controlar** o acesso aos atributos através de métodos específicos
- **Manter** a integridade dos dados da classe

**Analogia**: É como um carro - você não precisa saber como o motor funciona internamente, apenas usar os controles disponíveis (volante, pedais, alavancas) para interagir com ele.

## 🔒 Modificadores de Acesso

### Níveis de Visibilidade

```java
public    // Acessível de qualquer lugar do programa
private   // Acessível apenas dentro da própria classe
protected // Acessível na mesma package e em subclasses
// (sem modificador) = package-private - acessível apenas no mesmo pacote
```

### Regra de Ouro
```java
public class MinhaClasse {
    private String dadoSensivelSemprePrivado;  // ✅ SEMPRE privado
    
    // Métodos públicos para acesso controlado
    public String getDado() { 
        return dadoSensivelSemprePrivado; 
    }
    
    public void setDado(String valor) { 
        // Aqui podemos validar antes de atribuir
        if (valor != null && !valor.isEmpty()) {
            this.dadoSensivelSemprePrivado = valor; 
        }
    }
}
```

## 📝 Getters e Setters

### Por que são Importantes?

1. **Validação**: Verificar se o valor é válido antes de atribuir
2. **Controle**: Decidir quais atributos podem ser lidos/modificados
3. **Processamento**: Transformar ou formatar dados
4. **Flexibilidade**: Poder alterar implementação interna sem afetar código externo

### Padrão de Nomenclatura

```java
private TipoDoAtributo nomeDoAtributo;

// Getter - para LEITURA (GET = pegar/obter)
public TipoDoAtributo getNomeDoAtributo() {
    return nomeDoAtributo;
}

// Setter - para ESCRITA (SET = definir/alterar)  
public void setNomeDoAtributo(TipoDoAtributo valor) {
    // Validações podem ir aqui
    this.nomeDoAtributo = valor;
}
```

### Exemplo Prático

```java
public class ContaBancaria {
    private double saldo;        // Protegido!
    private String titular;      // Protegido!
    
    // Getter para saldo (permite consulta)
    public double getSaldo() {
        return saldo;
    }
    
    // Setter com validação (controle)
    public void depositar(double valor) {
        if (valor > 0) {  // Validação
            this.saldo += valor;
        } else {
            System.out.println("Valor deve ser positivo!");
        }
    }
    
    // Método específico para saque (mais controle)
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }
}
```

## 💡 Benefícios do Encapsulamento

### 1. **Segurança dos Dados**
- Impede alterações diretas e inadequadas
- Mantém a consistência dos dados

### 2. **Flexibilidade**
- Pode mudar implementação interna sem afetar código externo
- Facilita manutenção e evolução do código

### 3. **Validação Automática**
- Garante que dados sempre estejam em estado válido
- Evita erros e comportamentos inesperados

### 4. **Manutenibilidade**
- Facilita alterações e correções
- Reduz impacto de mudanças no sistema

## ⚠️ Erros Comuns

### ❌ Não Use Atributos Públicos
```java
public class Errado {
    public String nome;  // EVITE! Qualquer um pode alterar
    public int idade;    // EVITE! Sem validação
}
```

### ✅ Use Encapsulamento Corretamente
```java
public class Correto {
    private String nome;
    private int idade;
    
    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 150) {  // Validação
            this.idade = idade;
        }
    }
    
    public int getIdade() {
        return idade;
    }
}
```

## 🚀 Exercícios Práticos

1. **Crie uma classe Produto** com atributos privados (nome, preço, estoque)
2. **Implemente getters e setters** com validações apropriadas
3. **Teste validações** tentando atribuir valores inválidos
4. **Compare** comportamento com e sem encapsulamento

## 🔗 Navegação

[← 01 - Classes e Objetos](../01-classes-objetos/) | [03 - Herança →](../03-heranca/)

---

**💡 Lembre-se**: Encapsulamento é sobre controle e proteção. Sempre mantenha dados importantes privados e forneça acesso controlado através de métodos públicos!