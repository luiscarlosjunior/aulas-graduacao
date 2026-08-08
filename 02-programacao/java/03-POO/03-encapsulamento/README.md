# Encapsulamento - Protegendo Dados da Classe

## 🎯 O que é Encapsulamento?

O **encapsulamento** é o princípio de:
- **Ocultar** os detalhes internos da classe
- **Proteger** os dados contra modificações inadequadas  
- **Controlar** o acesso através de métodos específicos

**Analogia**: Como um controle remoto da TV - você não precisa saber como funciona internamente, apenas usar os botões (métodos) disponíveis.

## 🔒 Modificadores de Acesso

### Tipos de Visibilidade
```java
public    // Acessível de qualquer lugar
private   // Acessível apenas dentro da própria classe
protected // Acessível na mesma package e subclasses
// (sem modificador) = package-private
```

### Boa Prática
```java
public class MinhaClasse {
    private String atributo;  // SEMPRE privado
    
    // Métodos públicos para acesso controlado
    public String getAtributo() { return atributo; }
    public void setAtributo(String valor) { this.atributo = valor; }
}
```

## 📝 Getters e Setters

### Por que usar?
- **Validação**: verificar se o valor é válido antes de atribuir
- **Controle**: decidir quais atributos podem ser lidos/modificados
- **Processamento**: transformar dados antes de retornar/armazenar

### Padrão de Nomenclatura
```java
private tipo atributo;

public tipo getAtributo() {     // Para ler
    return atributo;
}

public void setAtributo(tipo valor) {  // Para modificar
    this.atributo = valor;
}
```

## 📚 Exemplos Práticos

### [ContaBancaria.java](ContaBancaria.java)
Exemplo completo demonstrando encapsulamento com validações.

### [TesteContaBancaria.java](TesteContaBancaria.java)
Teste da classe ContaBancaria mostrando a proteção dos dados.

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar
java TesteContaBancaria
```

## 💡 Benefícios do Encapsulamento

1. **Segurança**: Dados protegidos contra alterações indevidas
2. **Flexibilidade**: Pode mudar implementação interna sem afetar código externo
3. **Validação**: Garante que dados sempre estejam em estado válido
4. **Manutenibilidade**: Facilita alterações e correções

## 🔗 Navegação
[← 02 - Classes e Objetos](../02-classes-e-objetos/) | [04 - Herança →](../04-heranca/)